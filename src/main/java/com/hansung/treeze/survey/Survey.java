package com.hansung.treeze.survey;

public class Survey {
	
	private int totalNumberOfStudents;
	String contents = new String();
	SurveyTrueFalseType surveyType;

	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public SurveyTrueFalseType getSurveyType() {
		return surveyType;
	}

	public void setSurveyType(SurveyTrueFalseType surveyType) {
		this.surveyType = surveyType;
	}

	public int getTotalNumberOfStudents() {
		return totalNumberOfStudents;
	}

	public void increaseNumberOfStudents() {
		totalNumberOfStudents++;
	}
}
