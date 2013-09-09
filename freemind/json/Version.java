package freemind.json;

public class Version {
	public static final String STUDENT = "student";
	public static final String PROFESSOR = "professor";
	String userType;
	String version;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public static String getStudent() {
		return STUDENT;
	}
	public static String getProfessor() {
		return PROFESSOR;
	}
}
