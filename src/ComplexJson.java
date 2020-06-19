import io.restassured.path.json.JsonPath;
import jsonInpuApis.Paylod;

public class ComplexJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Using mocked response from CourseDetails method to parse and solve scenarios
		System.out.println("1.Print No of courses returned by API");
		JsonPath js= new JsonPath(Paylod.CourseDetails());
		int CourseCount=js.getInt("courses.size()");
		System.out.println(CourseCount);
		
		System.out.println("2.Print Purchase Amount");
		int TotalAmt=js.getInt("dashboard.purchaseAmount");
		System.out.println(TotalAmt);
		
		System.out.println("3.Print Title of the first course");
		String FirstCourse=js.getString("courses[0].title");
		System.out.println(FirstCourse);
		
		System.out.println("4.Print All course titles and their respective Prices");
		//How to deal if response is dynamic ,that is if on daily basis, no of copies sold keep changes,
		for(int i=0; i<CourseCount;i++) {
			System.out.println(js.get("courses["+i+"].title").toString());
			System.out.println(js.get("courses["+i+"].price").toString());
		}
		
		System.out.println("5.Print no of copies sold by RPA Course");
		//System.out.println(js.get("courses[2].copies").toString()); 
		//what if RPA course index changes next day , then hard-coding as above do not work all time
		for(int i=0; i<CourseCount;i++) {
			String courseTitle= js.get("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA"))
			{
				//copies sold
				int copiesRPA=js.get("courses["+i+"].copies");
				System.out.println(copiesRPA);
				break;
			}
			
		}
		
		System.out.println("6.Verify if Sum of all Course prices matches with Purchase Amount");
		for(int i=0;i<CourseCount;i++) {
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			int pricePerCourse= price*copies;
			System.out.println(pricePerCourse);
		}
	}

}
