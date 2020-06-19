import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonInpuApis.jsonParsing;

import static io.restassured.RestAssured.*;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OAuthTest {

	public static void main(String[] args) throws InterruptedException {
		
//Step1 - Hitting Auth URL to get auth code

	
		String Code="4%2FzAENxmZD_GYnsHtivuTZiSBSNmI7izW3mRyJST-t7FwRAR4TZpRtz5CidzCwOOkY8_fS10nTO_GyM4pMv6Ay304";
//formatting the URL String to retrieve Code using Java methods
//		String Partial_Code=CodeUrl.split("code=")[1];
//		String AuthCode=Partial_Code.split("&scope=")[0];

		
	//Step2 - Hitting AccessToken URl for AccessToken
	
	String getAcessToknRes=given().urlEncodingEnabled(false).queryParams("code",Code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		System.out.println(getAcessToknRes);
		JsonPath jsparse1= jsonParsing.inputToJson(getAcessToknRes);
		String acc_token=jsparse1.getString("access_token");
		
//Step3 Hitting GetCourse API of Client
		String getCourseRes=given().queryParam("access_token",acc_token).
		when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println(getCourseRes);
	}

}

/*Getting Auth Code should be done on the browser - it is not like Rest Assured given when then
 * We have to automate the browser action of signing into Google and then extract the URL response
 *and parse to get Code - For this we have to Use Selenium
 *Rest Assured is to hit APIS, but we have to construct URL and hit and login into browser*/
