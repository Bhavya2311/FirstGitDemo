import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.pojoAddApi;
import pojo.pojoAddApiLocation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilder {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		pojoAddApi obj=new pojoAddApi();
		obj.setAccuracy(45);
		obj.setName("Shanthi Nivasa");
		obj.setPhone_number("(+91) 111 893 3937");
		obj.setAddress("771, 1 Main layout, MD Nagar 006");
		obj.setWebsite("http://www.udemuy.com");
		obj.setLanguage("English");
		
		List<String> mytype= new ArrayList<String>();
		mytype.add("Hotel Udupi");
		mytype.add("HP Petrol pump");
		obj.setTypes(mytype);

		pojoAddApiLocation locObj = new pojoAddApiLocation();
		locObj.setLat(-38.383494);
		locObj.setLng(33.427362);
		obj.setLocation(locObj);
		
		// RequestSpecBuilder
		RequestSpecification  reqSpec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		//ResponseSpecBuilder
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		//request
		RequestSpecification req=given().spec(reqSpec)
		.body(obj);
		
		//when on req object
		Response res=  req.when().post("/maps/api/place/add/json")
		.then().log().all().spec(resSpec).extract().response();
		
		String resAsString = res.asString();
		System.out.println(resAsString);
	}

}
