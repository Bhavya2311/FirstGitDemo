import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import jsonInpuApis.Paylod;
import jsonInpuApis.jsonParsing;



public class Sample {

	public static void main(String[] args) {
 //testing Rahul's Add place API
		//Rest Assured works on 3 principle
		//given - all input details for the request
		//when - submit or hit the API (with HTTP methods and resource name)
		//then - validations on response
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Paylod.Addplace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).
		header("Server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath jsonparse =jsonParsing.inputToJson(response);
		//JsonPath jsonparse = new JsonPath(response);
		String placeID=jsonparse.getString("place_id");
		System.out.println(placeID);
		System.out.println("-----------------End of Add API Test----------------------------------------");
		
//update the place added with new address - automation PUT (update place) API
		
		
		String newAddress = "170 IndraNagar,IND";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeID+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + "").when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		System.out.println("-----------------End of Update API Test----------------------------------------");
		
		// GetAPI to get the place to see if new address is added to the Place
		String newPlace=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		
		JsonPath jsparse =jsonParsing.inputToJson(newPlace);
		//JsonPath jsparse = new JsonPath(newPlace);
		String actualAddress=jsparse.getString("address");
		System.out.println(actualAddress);
		//cucumber junit , testng testing framework - to add assertions -we have to use one of this frmwork,
		//since in java we do not have such assertion scenarios
		Assert.assertEquals(actualAddress,newAddress);
		
		
		
	}

}
