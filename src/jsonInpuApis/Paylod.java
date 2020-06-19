package jsonInpuApis;

public class Paylod {


		public static String Addplace() {
			
			return "{\r\n" + 
					"  \"location\": {\r\n" + 
					"    \"lat\": -21.383492,\r\n" + 
					"    \"lng\": 22.417361\r\n" + 
					"  },\r\n" + 
					"  \"accuracy\": 45,\r\n" + 
					"  \"name\": \"CV RAMAN NAGAR\",\r\n" + 
					"  \"phone_number\": \"(+91) 900 711 3937\",\r\n" + 
					"  \"address\": \"56, Main layout, KR PURAM \",\r\n" + 
					"  \"types\": [\r\n" + 
					"    \"Hotel park\",\r\n" + 
					"    \"Parrots\"\r\n" + 
					"  ],\r\n" + 
					"  \"website\": \"http://google.com\",\r\n" + 
					"  \"language\": \"English-IN\"\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"" ;
		}
		//this is mocked response
	public static String CourseDetails() {
		return "{\r\n" + 
				"  \"dashboard\": {\r\n" + 
				"    \"purchaseAmount\": 910,\r\n" + 
				"    \"website\": \"rahulshettyacademy.com\"\r\n" + 
				"  },\r\n" + 
				"  \"courses\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Selenium Python\",\r\n" + 
				"      \"price\": 50,\r\n" + 
				"      \"copies\": 6\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Cypress\",\r\n" + 
				"      \"price\": 40,\r\n" + 
				"      \"copies\": 4\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"RPA\",\r\n" + 
				"      \"price\": 45,\r\n" + 
				"      \"copies\": 10\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";
	}
	
	public static String AddBook(String isbn, String aisle) {
		String AddReq= "{\r\n" + 
				"\r\n" + 
				"\"name\":\"RestAssured with Java\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"Rahul Shetty\"\r\n" + 
				"}\r\n" + 
				"";
		return AddReq;
	}
	
	public static String DeleteBook(String id) {
		return "{\r\n" + 
				" \r\n" + 
				"\"ID\" : \""+id+"\"\r\n" + 
				" \r\n" + 
				"}\r\n" + 
				"";
	}
		
		
}
			
	

