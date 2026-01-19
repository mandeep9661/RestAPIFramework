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
import resources.APIResourses;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinations extends Utils {

	RequestSpecification res;
	ResponseSpecification resp;
	Response response;
	TestDataBuild dataSetup = new TestDataBuild();
	JsonPath jsonPath;
	static String place_id;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(dataSetup.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resourse, String method) {

		// Constructor will be called with value of resource which you pass
		APIResourses resourseAPI = APIResourses.valueOf(resourse);

		resp = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		if (method.equalsIgnoreCase("POST")) {
			response = res.when().post(resourseAPI.getResourse());
		} else if (method.equalsIgnoreCase("GET")) {
			response = res.when().get(resourseAPI.getResourse());
		}
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int code) {

		Assert.assertEquals(response.getStatusCode(), code);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

		response = response.then().spec(resp).extract().response();
		Assert.assertEquals(getJsonPath(response, keyValue), expectedValue);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

		// requestSpec
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		Assert.assertEquals(actualName, expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {

		res = given().spec(requestSpecification()).body(dataSetup.deletePlacePayload(place_id));

	}

}
