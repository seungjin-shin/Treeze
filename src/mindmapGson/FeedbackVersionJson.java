package mindmapGson;

import java.util.ArrayList;

public class FeedbackVersionJson {
	private ArrayList<String> version = new ArrayList<String>();
	private String cnt;
	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public ArrayList<String> getVersion() {
		return version;
	}

	public void setVersion(ArrayList<String> version) {
		this.version = version;
	}

	public FeedbackVersionJson(){}
	
}
