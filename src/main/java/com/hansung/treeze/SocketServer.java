package com.hansung.treeze;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.hansung.treeze.model.ClassInfo;
import com.hansung.treeze.model.User;

@SuppressWarnings("serial")
public class SocketServer extends HttpServlet implements Runnable {

	private static final Logger logger = LoggerFactory
			.getLogger(SocketServer.class);
	private Thread daemon;
	private ArrayList<ClassManager> classManagerList = new ArrayList<ClassManager>();

	public void init() throws ServletException {
		daemon = new Thread(this);
		daemon.start();
	}

	public void destroy() {
		daemon.interrupt();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			logger.info("==========================");
			logger.info("Treeze SocketServer RUN");
			logger.info("==========================");
			startServerSocket();
		} catch (Exception e) {
			logger.info("ServerSocket failed : " + e.getMessage());
		}

	}

	public void startServerSocket() {
		// BufferedReader in;
		// PrintWriter out;
		InputStream in;
		OutputStream out;

		String reqMsg = "";
		Socket userSocket;
		ClassManager classManager = null;
		Gson gson = new Gson();

		try {
			@SuppressWarnings("resource")
			ServerSocket SocketServer = new ServerSocket(2141);

			while (true) {
				try {

					logger.info("Watting");
					userSocket = SocketServer.accept();

					logger.info("Client IP : " + userSocket.getInetAddress());

					// in = new BufferedReader(new InputStreamReader(
					// userSocket.getInputStream()));
					// out = new PrintWriter(new OutputStreamWriter(
					// userSocket.getOutputStream()));

					in = userSocket.getInputStream();
					out = userSocket.getOutputStream();

					int cnt = -1;
					byte[] b = new byte[1024];

					cnt = in.read(b);

					if (cnt == -1) {
						System.out.println("Client Socket failed");
						continue;
						// c.getNaviOs().remove(os); // remove Client at Err
					} else {
						try {
							reqMsg = new String(b, 0, cnt, "UTF-8");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					/*
					 * reqMsg = in.readLine();
					 * 
					 * if(reqMsg == null){
					 * System.out.println("Client Socket failed"); continue; }
					 */
					logger.info("Client Request Message : " + reqMsg);

					// setting data..
					TreezeData treezedata = gson.fromJson(reqMsg,
							TreezeData.class);

					User user = gson.fromJson(treezedata.getArgList().get(0),
							User.class);
					ClassInfo classInfo = gson.fromJson(treezedata.getArgList()
							.get(1), ClassInfo.class);

					// out.println(reqMsg);
					// out.flush();
					out.write(reqMsg.getBytes("UTF-8"));
					out.flush();

					// setting classManager..
					classManager = null; // initialization
					int count = classManagerList.size();
					for (int i = 0; i < count; i++) {
						ClassManager existentClassManager = classManagerList
								.get(i);

						if (classInfo.getClassId() == (existentClassManager
								.getClassInfo().getClassId())) {
							classManager = existentClassManager;
							// Because classManager already exist,
							// 'existentClassManager'must substitute for current
							// 'classManager'
							logger.info("==========================");
							logger.info("존재하는 클래스"
									+ classManager.getClassInfo()
											.getClassName()
									+ "[교수 수:"
									+ (classManager.getProfessorSocket() != null)
									+ "][학생수 :"
									+ classManager
											.getStudentSocketManagerList()
											.size() + "]" + "매니저에 입장 :"
									+ "(현재 클래스 매니저 개수  :" + count + ")");
							logger.info("==========================");
						}
					}

					// When classManager must be first made.
					if (classManager == null) {
						classManager = new ClassManager(classInfo,
								classManagerList);
						classManagerList.add(classManager);

						try {
							classManager.init();
						} catch (ServletException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						logger.info("==========================");
						logger.info("클래스 매니저 새로 생성");
						logger.info("==========================");

					}

					// When User to be in classManger is professor.
					if (user.getUserType().equals(User.PROFESSOR)) {
						classManager.setProfessorInfo(user);
						classManager.setProfessorSocket(userSocket);
						logger.info("==========================");
						logger.info("교수 입장"
								+ classManager.getClassInfo().getClassName());
						logger.info("==========================");

						// When User to be in classManger is student.
					} else if (user.getUserType().equals(User.STUDENT)) {

						ArrayList<StudentSocketManager> studentSocketManagerList = classManager
								.getStudentSocketManagerList();

						StudentSocketManager studentSocketManager = new StudentSocketManager(
								user, userSocket, classInfo,
								studentSocketManagerList, classManager);
						studentSocketManagerList.add(studentSocketManager);

						try {
							studentSocketManager.init();
						} catch (ServletException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						logger.info("==========================");
						logger.info("학생 입장 :" + "("
								+ classManager.getClassInfo().getClassName()
								+ "// 현재 접속중인 학생 수 : "
								+ studentSocketManagerList.size() + ")");
						logger.info("==========================");
					}

				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		} catch (MalformedURLException e) {
			// TODO: handle exception
			logger.info(e.toString());
		} catch (IOException e) {
			// TODO: handle exception
			logger.info(e.toString());
		}
	}
}
