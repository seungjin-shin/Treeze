package freemind.json;
public class Mindmap{
	/**
	 */ 
	
	private String mindmapName;
	private int classId;
	private String mindmapXML;
	
	public Mindmap(){
		
	}
	
	public Mindmap(String mindmapXML){
		this.mindmapXML = mindmapXML;
	}
	
	public String getmindmapXML() {
		return mindmapXML;
	}
	public void setmindmap(String mindmapXML) {
		this.mindmapXML = mindmapXML;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getMindmapName() {
		return mindmapName;
	}

	public void setMindmapName(String mindmapName) {
		this.mindmapName = mindmapName;
	}

}
