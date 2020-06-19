import io.restassured.RestAssured;
import pojo.pojoAddApi;
import pojo.pojoAddApiLocation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

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
		
		
		String Response=given().queryParam("key", "qaclick123")
		.body(obj)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(Response);
	}

}
