package com.treeze.data.Survey;

public class Survey {
	
	private static int totalNumberOfStudents;
	String contents = new String();
	SurveyTrueFalseType surveyType = new SurveyTrueFalseType();

	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public SurveyType getSurveyType() {
		return surveyType;
	}

	public void setSurveyType(SurveyTrueFalseType surveyType) {
		this.surveyType = surveyType;
	}

	public static int getTotalNumberOfStudents() {
		return totalNumberOfStudents;
	}

	public static void increaseNumberOfStudents() {
		Survey.totalNumberOfStudents++;
	}
}
