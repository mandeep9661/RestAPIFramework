package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinations extends Utils {

	RequestSpecification res;
	ResponseSpecification resp;
	Response response;
	TestDataBuild dataSetup = new TestDataBuild();

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(dataSetup.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
		
		resp = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		response = res.when().post("/maps/api/place/add/json").then().spec(resp).extract().response();
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int code) {
		
		Assert.assertEquals(response.getStatusCode(), code);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		
		String rep = response.asString();
		JsonPath jsonPath = new JsonPath(rep);
		System.out.println(jsonPath);
		Assert.assertEquals(jsonPath.get(keyValue).toString(), expectedValue);
	}

}
