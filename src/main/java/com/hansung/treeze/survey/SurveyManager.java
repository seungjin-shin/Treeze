package com.hansung.treeze.survey;

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
import com.hansung.treeze.StudentSocketManager;
import com.hansung.treeze.TreezeData;

@SuppressWarnings("serial")
public class SurveyManager extends HttpServlet implements Runnable {

	private static final Logger logger = LoggerFactory
			.getLogger(SurveyManager.class);
	private Thread surveyManager;
	private Socket professorSocket = null;
	private ArrayList<StudentSocketManager> studentSocketManagerList = new ArrayList<StudentSocketManager>();

	private Survey survey;
	// private BufferedReader in;
	// private PrintWriter out;
	private InputStream in;
	private OutputStream out;
	private final static int LIMITTIME = 60;

	public void init() throws ServletException {
		surveyManager = new Thread(this);
		surveyManager.start();
	}

	public void destroy() {
		surveyManager.interrupt();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			logger.info("==========================");
			logger.info("Treeze Survey Manager  RUN");
			logger.info("==========================");
			startClassManager();
		} catch (Exception e) {
			logger.info("Survey Manager failed 오류 : " + e.getMessage());
			destroySurveyManager();
		}

	}

	public void startClassManager() throws IOException {

		String reqMsg = "";
		Gson gson = new Gson();
		int timeOut = 0;

		while (timeOut < LIMITTIME) {

			try {
				Thread.sleep(1000);
				++timeOut;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (studentSocketManagerList.size() == survey
					.getTotalNumberOfStudents()) {
				logger.info("==========================");
				logger.info("설문인원이 다 응답했으므로 끝");
				logger.info("==========================");
				break;
			}
		}
	
		logger.info("==========================");
		logger.info("설문 끝");
		logger.info("==========================");
		
		TreezeData treezeData = new TreezeData();
		treezeData.setDataType(TreezeData.SURVEYRESULT);
		
		treezeData.getArgList().add(gson.toJson(survey,Survey.class));
		sendResultOfSurvey(gson.toJson(treezeData,TreezeData.class));
		
		destroySurveyManager();
		return;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public void destroySurveyManager() {

		logger.info("==========================");
		logger.info("Treeze Survey Manager CLOSE");
		logger.info("==========================");

		destroy();
	}

	public void sendResultOfSurvey(String treezeData) {

		// first : to professor
		if (professorSocket != null) {
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
			logger.info("==========================");
			logger.info("Treeze Survey 교수에게 결과 전송 " + treezeData);
			logger.info("==========================");
		}

	}

	public ArrayList<StudentSocketManager> getStudentSocketManagerList() {
		return studentSocketManagerList;
	}

	public void setStudentSocketManagerList(
			ArrayList<StudentSocketManager> studentSocketManagerList) {
		this.studentSocketManagerList = studentSocketManagerList;
	}

	public Socket getProfessorSocket() {
		return professorSocket;
	}

	public void setProfessorSocket(Socket professorSocket) {
		this.professorSocket = professorSocket;

		logger.info("점검 : 교수 소켓 연결됨 ");

		try {
			in = professorSocket.getInputStream();
			out = professorSocket.getOutputStream();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
