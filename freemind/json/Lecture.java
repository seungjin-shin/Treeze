package freemind.json;

public class Lecture{

	/**
	 * 
	 */
	private String professorEmail;
	private String lectureName;
	private Boolean stateOfLecture;
	private Long lectureId;
	
	public Long getLectureId() {
		return lectureId;
	}
	public void setLectureId(Long lectureId) {
		this.lectureId = lectureId;
	}
	public String getProfessorEmail() {
		return professorEmail;
	}
	public void setProfessorEmail(String professorEmail) {
		this.professorEmail = professorEmail;
	}
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public Boolean getStateOfLecture() {
		return stateOfLecture;
	}
	public void setStateOfLecture(Boolean stateOfLecture) {
		this.stateOfLecture = stateOfLecture;
	}
}
