package mindmapGson;

import java.util.ArrayList;

public class FeedbackJson {
	private ArrayList<String> user = new ArrayList<String>();
	private ArrayList<String> count = new ArrayList<String>();
	private String cnt;
	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public ArrayList<String> getUser() {
		return user;
	}

	public void setUser(ArrayList<String> user) {
		this.user = user;
	}

	public ArrayList<String> getCount() {
		return count;
	}

	public void setCount(ArrayList<String> count) {
		this.count = count;
	}

	public FeedbackJson(){}
	
}
