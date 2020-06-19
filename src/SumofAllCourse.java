
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import jsonInpuApis.Paylod;

public class SumofAllCourse {
	
	@Test
	public void sumOfCourses() {
		int sum=0;
		JsonPath js= new JsonPath(Paylod.CourseDetails());
		int CourseCount=js.getInt("courses.size()");
		
		for(int i=0;i<CourseCount;i++) {
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			int percoursAmt=  price*copies;
			System.out.println(percoursAmt);
			sum= sum+percoursAmt;
	}
		
		System.out.println(sum);
		int expectdAmnt=js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, expectdAmnt);
		System.out.println("Assert passed");
	}
}
