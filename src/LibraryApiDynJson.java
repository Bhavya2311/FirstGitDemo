import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import jsonInpuApis.Paylod;
import jsonInpuApis.jsonParsing;

public class LibraryApiDynJson {
	
	@Test()
	public void AddBook() {
		
		//RestAssured.baseURI="http://216.10.245.166";
		RestAssured.baseURI="https://www.rahulshettyacademy.com";
		String AddRes=given().log().all().header("Content-Type","application/json")
		.body(Paylod.AddBook("BSG","2302"))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		 JsonPath jsparse=jsonParsing.inputToJson(AddRes);
		 String BookID = jsparse.getString("ID");
		System.out.println(BookID);
	}
	

}

