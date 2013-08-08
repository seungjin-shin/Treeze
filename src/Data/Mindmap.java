package Data;




public class Mindmap   {

	private Long classId; 
	//@Column(columnDefinition="LONGTEXT") 
	private String mindmapXML;
	Mindmap mindmap;
	public Mindmap getMindmap() {
		return mindmap;
	}
	public void setMindmap(Mindmap mindmap) {
		this.mindmap = mindmap;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getMindmapXML() {
		return mindmapXML;
	}
	public void setMindmapXML(String mindmapXML) {
		this.mindmapXML = mindmapXML;
	}
}
