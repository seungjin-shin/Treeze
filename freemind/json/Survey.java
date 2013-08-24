package freemind.json;
public class Survey {
	
	private static int totalNumberOfStudents;
	String contents = new String();
	SurveyTrueFalseType surveyType;

	
	public SurveyTrueFalseType getSurveyType() {
		return surveyType;
	}

	public void setSurveyType(SurveyTrueFalseType surveyType) {
		this.surveyType = surveyType;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public static int getTotalNumberOfStudents() {
		return totalNumberOfStudents;
	}

	public static void increaseNumberOfStudents() {
		Survey.totalNumberOfStudents++;
	}
}
