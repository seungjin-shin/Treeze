package freemind.json;

public class Ticket{
	
	private long id;
	private Long classId;
	private String parentNodeId;
	private String contents;
	private String userName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getParentNodeId() {
		return parentNodeId;
	}
	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
