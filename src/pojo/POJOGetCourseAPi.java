package pojo;

public class POJOGetCourseAPi {
	
	//alt+shift+s to create auto get and set methods 
	private String url;//key-value
	private String services;//key-value
	private String expertise;//key-value
	private String linkedIn; //key-value
	private String instructor; // key -value
	private POJOCourses courses; // is a nested json  - Create Sub POJO class for nested Json
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public POJOCourses getCourses() {
		return courses;
	}
	public void setCourses(POJOCourses courses) {
		this.courses = courses;
	}

}


