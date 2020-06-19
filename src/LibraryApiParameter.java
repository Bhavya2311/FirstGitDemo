import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import jsonInpuApis.Paylod;
import jsonInpuApis.jsonParsing;

public class LibraryApiParameter {
	
	@Test(dataProvider="BooksData")
	public void AddBook(String isbn, String aisle) {
		
		//RestAssured.baseURI="http://216.10.245.166";
		RestAssured.baseURI="https://www.rahulshettyacademy.com";
		String AddRes=given().log().all().header("Content-Type","application/json")
		.body(Paylod.AddBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		 JsonPath jsparse=jsonParsing.inputToJson(AddRes);
		 String BookID = jsparse.getString("ID");
		System.out.println(BookID);
	}
	
	//below method used to generate data needed for the test in array format
	@DataProvider(name="BooksData")
	public Object[][] generateData() {
		//return new Object[][] {{"LSA","2303"},{"DSR","2304"},{"RCD","2305"}};// 3 arrays or Data sets of books
//here in arrays we are sending 2 parameters, so to Addbook method in test, we have to send 2 parameters too
		return new Object[][] {{"TSD","211"}};
	}

	@Test(dependsOnMethods="AddBook")
	public void DeleteBook(String BookID) {
		given().log().all().header("Content-Type","text/plain")
		.body(Paylod.DeleteBook(BookID))
		.when().post("Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200);
	}
	
}

