package com.hansung.treeze;

import java.util.ArrayList;

public class TreezeData {

	final String NAVI = "navi";
	final String TICKET = "ticket";

	final String SURVEY = "survey";
	final String SURVEYVALUE = "surveyValue";
	final String SURVEYRESULT = "surveyResult";

	private String dataType;
	private ArrayList<String> argList;
	
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public ArrayList<String> getArgList() {
		return argList;
	}
	public void setArgList(ArrayList<String> argList) {
		this.argList = argList;
	}

	
}