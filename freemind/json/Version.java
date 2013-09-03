package freemind.json;

public class Version {
	public static final String STUDENT = "student";
	public static final String PROFESSOR = "professor";
	String userType;
	String versionId;
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public static String getStudent() {
		return STUDENT;
	}
	public static String getProfessor() {
		return PROFESSOR;
	}
}
