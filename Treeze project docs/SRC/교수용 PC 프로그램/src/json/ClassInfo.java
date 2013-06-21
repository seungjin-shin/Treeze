package freemind.json;


public class ClassInfo{

	/**
	 * 
	 */
	private int classId; /*꺼내서 다시 저장해야함 */
	private String className;
	private String classIP;
	private int port;
	private String lectureName;
	private String professorEmail;
	
	

	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public String getProfessorEmail() {
		return professorEmail;
	}
	public void setProfessorEmail(String professorEmail) {
		this.professorEmail = professorEmail;
	}
	public String getClassIP() {
		return classIP;
	}
	public void setClassIP(String classIP) {
		this.classIP = classIP;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
}
