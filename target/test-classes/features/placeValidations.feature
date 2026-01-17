Feature: Validating Place API's

Scenario Outline: Verify if Place is being succesfully added suing AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with Post http request 
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	
Examples:
	| name        | language  | address                   |
	| KK House	  | Hindi	  | Noida Gaur City 2		  |