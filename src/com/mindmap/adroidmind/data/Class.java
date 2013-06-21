package com.mindmap.adroidmind.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Class implements Parcelable {

	private long classId; /* 꺼내서 다시 저장해야함 */
	private String classIP;
	private String className;
	private int port;

	private String professorEmail;
	public Class(){
		
	}
	public Class(Parcel in) {
		// TODO Auto-generated constructor stub
		readFromParcel(in);
	}

	public String getProfessorEmail() {
		return professorEmail;
	}

	public void setProfessorEmail(String professorEmail) {
		this.professorEmail = professorEmail;
	}

	public String getClassIP() {
		return classIP;
	}

	public void setClassIP(String classIP) {
		this.classIP = classIP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(classId);
		dest.writeString(classIP);
		dest.writeString(className);
		dest.writeInt(port);
		dest.writeString(professorEmail);

	}

	private void readFromParcel(Parcel in) {
		classId = in.readLong();
		classIP = in.readString();
		className = in.readString();
		port = in.readInt();
		professorEmail = in.readString();

	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Class createFromParcel(Parcel in) {
			return new Class(in);
		}

		public Class[] newArray(int size) {
			return new Class[size];
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
}
