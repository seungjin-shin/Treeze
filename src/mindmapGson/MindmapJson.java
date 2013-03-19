package mindmapGson;

import java.util.ArrayList;

public class MindmapJson {
	private ArrayList<String> id = new ArrayList<String>();
	private ArrayList<String> title = new ArrayList<String>();
	private String cnt;

	public ArrayList<String> getId() {
		return id;
	}

	public ArrayList<String> getTitle() {
		return title;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public MindmapJson(){}
	
}
