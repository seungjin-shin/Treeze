package com.treeze.data.Survey;
import java.util.ArrayList;


public class SurveyTrueFalseType implements SurveyType{
	
	private static int trueCountSum;
	private static int falseCountSum;
	private static ArrayList<Integer> resultOfSurvey = new ArrayList<Integer>();
	
	private final static String TRUE = "예";
	private final static String FALSE = "아니오";
	
	private ArrayList<String> surveyTypeInfo = new ArrayList<String>();
	private int trueCount = 0;
	private int falseCount = 0;
	
	
	
	SurveyTrueFalseType(){
		surveyTypeInfo.add(SurveyTrueFalseType.TRUE);
		surveyTypeInfo.add(SurveyTrueFalseType.FALSE);
	}
	
	@Override
	public void fillOutSurvey(String answerOfSurvey) {
		// TODO Auto-generated method stub
		if(answerOfSurvey.equals(SurveyTrueFalseType.TRUE))
			trueCount++;
		else if(answerOfSurvey.equals(SurveyTrueFalseType.FALSE))
			falseCount++;
		else
			System.out.println("Wrong Answer!!");
	}
	
	@Override
	public ArrayList<String> getSurveyExamplesInfo() {
		// TODO Auto-generated method stub
		return surveyTypeInfo;
	}
	@Override
	public ArrayList<Integer> getReultOfSurvey() {
		// TODO Auto-generated method stub
		resultOfSurvey.add(trueCountSum);
		resultOfSurvey.add(falseCountSum);
		
		return resultOfSurvey;
	}
	@Override
	public void collectAnswerofSurvey() {
		// TODO Auto-generated method stub
		trueCountSum += trueCount;
		falseCountSum += falseCount;
	}
	
	
	
	public static int getTrueCountSum() {
		return trueCountSum;
	}

	public static void setTrueCountSum(int trueCountSum) {
		SurveyTrueFalseType.trueCountSum = trueCountSum;
	}

	public static int getFalseCountSum() {
		return falseCountSum;
	}

	public static void setFalseCountSum(int falseCountSum) {
		SurveyTrueFalseType.falseCountSum = falseCountSum;
	}

	public int getTrueCount() {
		return trueCount;
	}

	public void setTrueCount(int trueCount) {
		this.trueCount = trueCount;
	}

	public int getFalseCount() {
		return falseCount;
	}

	public void setFalseCount(int falseCount) {
		this.falseCount = falseCount;
	}

}
