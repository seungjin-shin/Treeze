package com.hansung.treeze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hansung.treeze.model.ClassInfo;
import com.hansung.treeze.model.User;

@SuppressWarnings("serial")
public class ClassManager extends HttpServlet implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassManager.class);
	private Thread classManager;
	private User professorInfo;
	private Socket professorSocket;
	private ClassInfo classInfo;
	private ArrayList <ClassManager> classManagerList; // this is necessary to function "destroyClassManager"

	private ArrayList <StudentSocketManager> studentSocketManagerList = new  ArrayList <StudentSocketManager>();

	private BufferedReader in;
	private PrintWriter out; 

	public ClassManager(User professorInfo, Socket professorSocket,ClassInfo classInfo,ArrayList <ClassManager> classManagerList){

		this.professorInfo = professorInfo;
		this.professorSocket = professorSocket;
		this.classInfo = classInfo;
		this.classManagerList = classManagerList;

		try {
			in = new BufferedReader(new InputStreamReader(professorSocket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(professorSocket.getOutputStream()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	final String QUIT = "quit";
	
	public void init()throws ServletException{
		classManager = new Thread(this);
		classManager.start();
	}
	public void destroy(){
		classManager.interrupt();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			logger.info("==========================");
			logger.info("Treeze Class Manager ("+classInfo.getClassName()+") RUN" );
			logger.info("==========================");
			startClassManager();
		} catch(Exception e){
			logger.info("Class Manager failed : " + e.getMessage());
		}
		
	}

	public void startClassManager(){
		String reqMsg = "";
		
		while (!(reqMsg.equals(QUIT))) {
			try {

				reqMsg = in.readLine();

				logger.info("Client Request Message : " + reqMsg);

				broadcast(reqMsg);
				 
				out.println(reqMsg);
				out.flush();

			} catch (IOException e) {
					// TODO: handle exception
				e.printStackTrace();
			}

		}

		destroyClassManager();
	}

	public void destroyClassManager(){

		int count = studentSocketManagerList.size() -1;

		try {
			professorSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int i = count; i >= 0 ; i--){
			studentSocketManagerList.get(i).socketClose();
			studentSocketManagerList.remove(i);
		}

		logger.info("==========================");
		logger.info("Treeze Class Manager ("+classInfo.getClassName()+") CLOSE" );
		logger.info("==========================");

		classManagerList.remove(this);
	}
	
	public void broadcast(String treezeData){

		out.println(treezeData); // first : to professor
		out.flush();

		for(int i = 0; i < studentSocketManagerList.size(); i++) // second: to student
			 studentSocketManagerList.get(i).send(treezeData);
		
	}
	
	public ArrayList<StudentSocketManager> getStudentSocketManagerList() {
		return studentSocketManagerList;
	}
	public void setStudentSocketManagerList(
			ArrayList<StudentSocketManager> studentSocketManagerList) {
		this.studentSocketManagerList = studentSocketManagerList;
	}
	
	public ClassInfo getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	
	
}
