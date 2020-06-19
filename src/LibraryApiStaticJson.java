import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import jsonInpuApis.jsonParsing;

public class LibraryApiStaticJson {
	
	@Test()
	public void AddBook() throws IOException {
		
		//RestAssured.baseURI="http://216.10.245.166";
		RestAssured.baseURI="https://www.rahulshettyacademy.com";
		String AddRes=given().log().all().header("Content-Type","application/json")
		.body(GenerateStringFromResource("E:\\Udemy\\RESTAPI_restassuredRahul\\AddBook.json"))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		 JsonPath jsparse=jsonParsing.inputToJson(AddRes);
		 String BookID = jsparse.getString("ID");
		System.out.println(BookID);
	}
	
	public static String GenerateStringFromResource(String path) throws IOException{
		
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	

}

