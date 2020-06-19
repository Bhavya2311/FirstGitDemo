package jsonInpuApis;

import io.restassured.path.json.JsonPath;

public class jsonParsing {

	public static JsonPath inputToJson(String response) {
		
		JsonPath jsonparse = new JsonPath(response);
		return jsonparse;
	}
}


