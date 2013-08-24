package com.treeze.data;




public class Ticket extends MindNode {
	    private long id;
     	private long classId;
   
     	
     	private String parentNodeId;
     	private String contents;
     	private String userName;
     	
     	public long getId() {
     		return id;
     	}
     	
     	public void setId(long id) {
     		this.id = id;
     	}
     	
     	public String getParentNodeId() {
     		return parentNodeId;
     	}
     	
     	public void setParentNodeId(String parentNodeId) {
     		this.parentNodeId = parentNodeId;
     	}
     	
     	public String getUserName() {
     		return userName;
     	}
     	
     	public void setUserName(String userName) {
     		this.userName = userName;
     	}
	

	public Ticket() {
		// TODO Auto-generated constructor stub
		
	}

	public Ticket(MindNode parent,  String nodeID ,String contents,String userName) {
		// TODO Auto-generated constructor stub
		
		this.contents = contents;
		this.parentNode = parent;
		this.userName = userName;
		this.parentNode = parent;
		parentNode.getChildeNodes().add(this);
		this.nodeID = nodeID;
		
		this.id =Integer.parseInt(nodeID); 
		
		parent.ChildCount++;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}



	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}


}
