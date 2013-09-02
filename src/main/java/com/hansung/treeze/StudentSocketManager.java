

package com.hansung.treeze;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.hansung.treeze.model.ClassInfo;
import com.hansung.treeze.model.User;
import com.hansung.treeze.survey.Survey;

@SuppressWarnings("serial")
public class StudentSocketManager extends HttpServlet implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassManager.class);
	private Thread studentSocketManager;
	private ClassInfo classInfo;
	private User studentInfo;
	private Socket studentSocket;
	private ArrayList <StudentSocketManager> studentSocketManagerList; // this is necessary to function "destroyStudentSocketManager"
	private ClassManager classManager;

	//private BufferedReader in;
//	private PrintWriter out; 
	private InputStream in;
	private OutputStream out; 

	final String QUIT = "quit";

	public StudentSocketManager(User studentInfo, Socket studentSocket,ClassInfo classInfo,ArrayList <StudentSocketManager> studentSocketManagerList,ClassManager classManager){

		this.studentInfo = studentInfo;
		this.studentSocket = studentSocket;
		this.classInfo = classInfo;
		this.studentSocketManagerList = studentSocketManagerList;
		this.classManager = classManager;

		try {
			//in = new BufferedReader(new InputStreamReader(this.studentSocket.getInputStream()));
			//out = new PrintWriter(new OutputStreamWriter(this.studentSocket.getOutputStream()));
			in = this.studentSocket.getInputStream();
			out  = this.studentSocket.getOutputStream();
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
			logger.info(" Student Socket Manager failed 오류 : " + e.getMessage());
			destroyStudentSocketManager();
		}
		
	}

	public void startStudentSocketManager() throws IOException{
		String reqMsg = "";
		Gson gson  = new Gson();
		
		do{
			try {
				//reqMsg = in.readLine();
				int cnt = -1;
				byte[] b = new byte[1024];
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cnt = in.read(b);

				if (cnt == -1) {
					destroyStudentSocketManager();
					System.out.println("Student Socket Manager failed 접속종료  ");
					return;

				} else {
					try {
						reqMsg = new String(b, 0, cnt, "UTF-8");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				logger.info("Student Request Message : " + reqMsg);

				TreezeData treezedata = gson.fromJson(reqMsg,
						TreezeData.class);
				
				if(treezedata.getDataType().equals(TreezeData.SURVEYVALUE)){
					logger.info("==========================");
					logger.info("학생의 답변이 도착");
					logger.info("==========================");
					Survey surveyOfStudent = gson.fromJson(treezedata.getArgList().get(0),Survey.class);
					Survey survey = classManager.getSurveyManager().getSurvey();
					
					survey.getSurveyType().setTrueCount(surveyOfStudent.getSurveyType().getTrueCount());
					survey.getSurveyType().setFalseCount(surveyOfStudent.getSurveyType().getFalseCount());
					survey.getSurveyType().collectAnswerofSurvey();
					survey.increaseNumberOfStudents();
					
				}else {
					classManager.broadcast(reqMsg);
				}

				
			} catch (IOException e) {
					// TODO: handle exception
				e.printStackTrace();
				destroyStudentSocketManager();
				return;
			}

		}while (!(reqMsg.equals(QUIT)));

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
		logger.info("존재하는 클래스" + classManager.getClassInfo().getClassName()
				+ "[교수 수:" + (classManager.getProfessorSocket() != null)
				+ "][학생수 :" + classManager.getStudentSocketManagerList().size()
				+ "]");
		logger.info("==========================");
		destroy();
	}
	
	public void send(String treezeData){
		
		//out.println(treezeData); 
		//out.flush();
		try {
			out.write(treezeData.getBytes("UTF-8"));
			out.flush();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
