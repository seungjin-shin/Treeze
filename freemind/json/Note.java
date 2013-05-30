package freemind.json;
public class Note{

	/**
	 * 
	 */

	private int classId;
	private String position;
	private int locationX;
	private int locationY;
	private String contents;
	private String userEmail;
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getLocationX() {
		return locationX;
	}
	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getLocationY() {
		return locationY;
	}
	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
