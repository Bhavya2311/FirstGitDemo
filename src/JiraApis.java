import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import jsonInpuApis.jsonParsing;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;




public class JiraApis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="http://localhost:8080";
		
		SessionFilter session = new SessionFilter();
		//login for authorization and sessionID
		given().log().all().relaxedHTTPSValidation().header("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"username\": \"bhavigowda23\",\r\n" + 
				"    \"password\": \"23Saijesus\"\r\n" + 
				"}").filter(session).when().post("/rest/auth/1/session").
		then().log().all().extract().response().asString();
		System.out.println("___________-End of Login-___________");
		
		//creating issue
		String CreatIssRes=given().log().all().header("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"fields\": {\r\n" + 
				"       \"project\":\r\n" + 
				"       {\r\n" + 
				"          \"key\": \"JIR\"\r\n" + 
				"       },\r\n" + 
				"       \"summary\": \"Eighth Defect Fighting.\",\r\n" + 
				"       \"description\": \"Verifying Get Issue API to validate the comment\",\r\n" + 
				"       \"issuetype\": {\r\n" + 
				"          \"name\": \"Bug\"\r\n" + 
				"       }\r\n" + 
				"   }\r\n" + 
				"}").filter(session).when().post("/rest/api/2/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath jsparse = jsonParsing.inputToJson(CreatIssRes);
		String IssueId = jsparse.getString("id");
		//String IssueKey = jsparse.getString("key");
		System.out.println("___________-End of Create Issue-___________");

		//for adding comments to the issue
		String ExpectdCommnt="This is One and Only Last Expected Comment!";
		String AddCmmntRes=given().log().all().pathParam("id",IssueId).header("Content-Type","application/json")
				.body("{\r\n" + 
						"    \"body\": \""+ExpectdCommnt+"\",\r\n" + 
						"    \"visibility\": {\r\n" + 
						"        \"type\": \"role\",\r\n" + 
						"        \"value\": \"Administrators\"\r\n" + 
						"    }\r\n" + 
						"}").filter(session).when().post("/rest/api/2/issue/{id}/comment")	
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath jsparse1=jsonParsing.inputToJson(AddCmmntRes);
		String EpCommntID=jsparse1.get("id");
		System.out.println("___________End of Final or Last Comment______________");
		

		//Adding attachments to the issue
				given().header("X-Atlassian-Token","no-check").header("Content-Type","multipart/form-data").
				pathParam("id",IssueId).filter(session).multiPart("file",new File("MyAttchment.txt")).
				when().post("/rest/api/2/issue/{id}/attachments").then().log().all().
				assertThat().statusCode(200);
				System.out.println("______________End of Adding Attachment_____________");

		
		//Get the Comments  only GetISsue Response using Query Param(limiting huge response)
		String GetComntRes=given().log().all().pathParam("id",IssueId).queryParam("fields","comment")
		.header("Content-Type","application/json")
		.filter(session).when().get("/rest/api/2/issue/{id}").then().log().all()
		.assertThat().statusCode(200).extract().response().asString();
		System.out.println(GetComntRes);
		System.out.println("___________End of Get Comment______________");
		
		//validate if comment added (expected) is present in comment array of response
		JsonPath jsparse2= jsonParsing.inputToJson(GetComntRes);
		int CommntsCount=jsparse2.get("fields.comment.comments.size()");
		for(int i=0;i<CommntsCount;i++) 
		{
		   String ActCommntID= jsparse2.get("fields.comment.comments["+i+"].id").toString();
		   if(ActCommntID.equalsIgnoreCase(EpCommntID))
		   {
			   String ActCommnt=jsparse2.get("fields.comment.comments["+i+"].body").toString();
			   System.out.println("This is retrieved from response:"+ActCommnt);
			   Assert.assertEquals(ActCommnt, ExpectdCommnt);
			   break;
		   }
	}
	}
}

