import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.POJOGetCourseAPi;
import pojo.pojoAPI;
import pojo.pojoWebAutomation;

public class OAuth2 {

	public static void main(String[] args) {
		
		// code keeps on changing and can be generated every time using 
//https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
		 
		String courseTitle[]= {"Selenium Webdriver Java","Cypress","Protractor"};
		String code ="4%2FzAGjdo2xW1UuS8sFqooHUvVoy7nyOgRshZHZ-SN18w-7KOrQfB7PR2HrAtjt99OYqplCjoZcDyrHQvD7NvBscuI";
		
		// Hit AuthToken URL to get Access token
		String accTokenRes=given().urlEncodingEnabled(false)
		.queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		
		System.out.println(accTokenRes);
		JsonPath jsparse = new JsonPath(accTokenRes);
		String accessToken=jsparse.getString("access_token");
				
		//Hit GetCourse API using access token
		POJOGetCourseAPi getCourseRes=
		given().queryParams("access_token",accessToken).expect().defaultParser(Parser.JSON).
		when().get("https://rahulshettyacademy.com/getCourse.php").as(POJOGetCourseAPi.class);
		//here, sending response to PojoGetCourse class to Deserialize
		
		
		//accessing JSon response - after DeSerialization using Java object
		System.out.println(getCourseRes.getLinkedIn());
		System.out.println(getCourseRes.getInstructor());
		System.out.println(getCourseRes.getUrl());
		System.out.println(getCourseRes.getServices());
		System.out.println(getCourseRes.getExpertise());
		System.out.println(getCourseRes.getCourses().getApi());

		//1. get SoapUI course price from API course
		System.out.println(getCourseRes.getCourses().getApi().get(1).getCourseTitle());
		
		List<pojoAPI> apicourses= getCourseRes.getCourses().getApi();
		for(int i=0;i<apicourses.size();i++) {
			if (apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
					{
						System.out.println(apicourses.get(i).getPrice());
					}
		}
		
		//2. Get course title of all courses from WebAutomation
		ArrayList<String> ActCourses= new ArrayList<String>();
		
		List<pojoWebAutomation> webAutoCourses=getCourseRes.getCourses().getWebAutomation();
		for(int i=0;i<webAutoCourses.size();i++) {
			System.out.println(webAutoCourses.get(i).getCourseTitle());
			ActCourses.add((webAutoCourses.get(i).getCourseTitle()));
		}
		
		//comparing both array and arrayList by converting arrays to arrayList
		List<String> ExpectedCourse=  Arrays.asList(courseTitle); //converting courseTitle array to array list
		Assert.assertTrue(ActCourses.equals(ExpectedCourse)); //comparing two array list
		System.out.println(ExpectedCourse);
		
		
		System.out.println("Changes to test Git");
		
		System.out.println("Changes from GitX");

	}

}
