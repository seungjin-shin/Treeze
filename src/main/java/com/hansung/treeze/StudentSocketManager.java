

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
public class StudentSocketManager extends HttpServlet implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassManager.class);
	private Thread studentSocketManager;
	private ClassInfo classInfo;
	private User studentInfo;
	private Socket studentSocket;
	private ArrayList <StudentSocketManager> studentSocketManagerList; // this is necessary to function "destroyStudentSocketManager"
	private ClassManager classManager;

	private BufferedReader in;
	private PrintWriter out; 

	final String QUIT = "quit";

	public StudentSocketManager(User studentInfo, Socket studentSocket,ClassInfo classInfo,ArrayList <StudentSocketManager> studentSocketManagerList,ClassManager classManager){

		this.studentInfo = studentInfo;
		this.studentSocket = studentSocket;
		this.classInfo = classInfo;
		this.studentSocketManagerList = studentSocketManagerList;
		this.classManager = classManager;

		try {
			in = new BufferedReader(new InputStreamReader(this.studentSocket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(this.studentSocket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void init()throws ServletException{
		studentSocketManager = new Thread(this);
		studentSocketManager.start();
	}
	public void destroy(){
		studentSocketManager.interrupt();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			logger.info("==========================");
			logger.info("Treeze Student Socket Manager ("+studentInfo.getUserEmail()+") RUN" );
			logger.info("==========================");
			startStudentSocketManager();
		} catch(Exception e){
			logger.info(" Student Socket Manager failed : " + e.getMessage());
		}
		
	}

	public void startStudentSocketManager(){
		String reqMsg = "";

		while (!(reqMsg.equals(QUIT))) {
			try {

				reqMsg = in.readLine();

				logger.info("Client Request Message : " + reqMsg);

				classManager.broadcast(reqMsg);

				out.println(reqMsg);
				out.flush();

			} catch (IOException e) {
					// TODO: handle exception
				e.printStackTrace();
			}

		}

	}


	public void destroyStudentSocketManager(){

		try {
			studentSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		studentSocketManagerList.remove(this);

		logger.info("==========================");
		logger.info("Treeze Student Socket Manager ("+classInfo.getClassName()+") CLOSE" );
		logger.info("==========================");
	}
	
	public void send(String treezeData){
		
		out.println(treezeData); 
		out.flush();
	}
	
	public void socketClose(){
		
		try {
			studentSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
