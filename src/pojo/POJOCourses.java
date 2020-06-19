package pojo;

import java.util.List;

public class POJOCourses {

	private List<pojoWebAutomation> webAutomation; //expect returning of multiple items - list of web automation items
	private List<pojoAPI> api;
	private List<pojoMobile> mobile;
	
	public List<pojoWebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<pojoWebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<pojoAPI> getApi() {
		return api;
	}
	public void setApi(List<pojoAPI> api) {
		this.api = api;
	}
	public List<pojoMobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<pojoMobile> mobile) {
		this.mobile = mobile;
	}
	
}
