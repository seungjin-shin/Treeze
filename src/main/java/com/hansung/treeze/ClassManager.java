package com.hansung.treeze;

import java.net.Socket;
import java.util.ArrayList;

import javax.servlet.ServletException;

import com.hansung.treeze.model.ClassInfo;
import com.hansung.treeze.model.User;


public class ClassManager {
	
	ClassInfo classInfo;
	ArrayList<UserSocketThread> userSocketThreadList = new ArrayList<UserSocketThread>();
	
	public ClassManager(ClassInfo classInfo){
		this.classInfo = classInfo;
	}
	//전체에 broadcast 하기
	public void broadcast(String treezeData){

		for (int i = 0; i < userSocketThreadList.size(); i++) {
			userSocketThreadList.get(i).sendTreezeData(treezeData);
		}
	}
	
	//클래스 매니저에 유저(교수, 학생) 추가하기 + Thread 생성
	public void addUserSocketThread(Socket userSocket,User user){
		UserSocketThread userSocketThread = new UserSocketThread(this, userSocket, user);
		try {
			userSocketThread.init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userSocketThreadList.add(userSocketThread);
	}
	
	public ClassInfo getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public ArrayList<UserSocketThread> getUserSocketThreadList() {
		return userSocketThreadList;
	}

}
