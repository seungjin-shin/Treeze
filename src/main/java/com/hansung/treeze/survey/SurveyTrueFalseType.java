package com.hansung.treeze.survey;

import java.util.ArrayList;


public class SurveyTrueFalseType implements SurveyType{
	
	private int trueCountSum;
	private int falseCountSum;
	private ArrayList<Integer> resultOfSurvey = new ArrayList<Integer>();
	
	public final static String TRUE = "��";
	public final static String FALSE = "�ƴϿ�";
	
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
	
	
	
	public int getTrueCountSum() {
		return trueCountSum;
	}

	public  void setTrueCountSum(int trueCountSum) {
		this.trueCountSum = trueCountSum;
	}

	public int getFalseCountSum() {
		return falseCountSum;
	}

	public void setFalseCountSum(int falseCountSum) {
		this.falseCountSum = falseCountSum;
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
