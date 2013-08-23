package com.hansung.treeze.survey;

public class SurveyTypeFactory {
	
	public final static String TRUEFALSETYPE = "TrueFalseType";
	public final static String TRUEFALSETYPE3 = "TrueFalseType3";
	public final static String TRUEFALSETYPE5 = "TrueFalseType5";
	public final static String ESSAYTYPE = "EssayType";
	public final static String MATCHINGTYPE = "MatchingType";
	public final static String COMPLETIONTYPE = "CompletionType";
	
	public static SurveyType createSurveyType(String type) {
		
		SurveyType surveyType = null;
		
		if(type.equals(SurveyTypeFactory.TRUEFALSETYPE)){
			surveyType = new SurveyTrueFalseType();
			
		} else if(type.equals(SurveyTypeFactory.TRUEFALSETYPE3)){
			surveyType = new SurveyTrueFalseType3();
			
		} else if(type.equals(SurveyTypeFactory.TRUEFALSETYPE5)){
			surveyType = new SurveyTrueFalseType5();
			
		} else if(type.equals(SurveyTypeFactory.ESSAYTYPE)){
			surveyType = new SurveyEssayType();
			
		} else if(type.equals(SurveyTypeFactory.MATCHINGTYPE)){
			surveyType = new SurveyMatchingType();
			
		} else if(type.equals(SurveyTypeFactory.COMPLETIONTYPE)){
			surveyType = new SurveyCompletionType();
		} else{
			surveyType = null;
		}
		
		return surveyType;
	}
}
