package freemind.json;

import java.util.ArrayList;

public class TreezeData {

	public static final String CONNECTIONINFO = "connectionInfo"; //User + ClassInfo
	public static final String NAVI = "navi";
	public static final String TICKET = "ticket";
    
	public static final String SURVEY = "survey";
	public static final String SURVEYVALUE = "surveyValue";
	public static final String SURVEYRESULT = "surveyResult";
    
	private String dataType;
	private ArrayList<String> argList = new ArrayList<String>();
	
	
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