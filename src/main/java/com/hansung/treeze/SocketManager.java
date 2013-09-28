package com.hansung.treeze;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
public class SocketManager extends HttpServlet implements Runnable {
	// 서버 메인 소켓 Thread 하나

	private static final Logger logger = LoggerFactory
			.getLogger(SocketManager.class);
	private Thread daemon;
	private ArrayList<ClassManager> classManagerList = new ArrayList<ClassManager>();
	InputStream in;
	OutputStream out;
	Socket userSocket = null;
	Gson gson = new Gson();
	String reqMsg = null;

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
			startSocketManager();
		} catch (Exception e) {
			logger.info("ServerSocket failed : " + e.getMessage());
			destroy();
		}

	}

	// 소켓을 받는다.
	public void startSocketManager() {

		@SuppressWarnings("resource")
		ServerSocket SocketServer = null;
		try {
			SocketServer = new ServerSocket(2141);

			while (true) {
				logger.info("Watting");
				userSocket = SocketServer.accept();

				logger.info("Client IP : " + userSocket.getInetAddress());

				in = userSocket.getInputStream();
				out = userSocket.getOutputStream();

				int cnt = -1;
				byte[] buffer = new byte[1024];

				cnt = in.read(buffer);

				if (cnt == -1) {
					// 소켓 갑자기 상대방에서 끊어지면
					// 이 소켓 닫아줘야해
					logger.info("Warning - Must close user socket !! ");
					userSocket.close();
					reqMsg = null;
					continue;
				} else {
					// 소켓에서 정상적으로 메시지가 날라오면 받아야해
					reqMsg = new String(buffer, 0, cnt, "UTF-8");
					logger.info("Client Request Message : " + reqMsg);

				}

				// TreezeData로 ClassInfo와 User 정보를 확인한다.
				TreezeData treezedata = gson.fromJson(reqMsg, TreezeData.class);

				User user = gson.fromJson(treezedata.getArgList().get(0),
						User.class);
				ClassInfo classInfo = gson.fromJson(treezedata.getArgList()
						.get(1), ClassInfo.class);

				// 확실히 연결되었음을 확인하기 위해서 유저에게 받은정보를 되돌려준다.
				out.write(reqMsg.getBytes("UTF-8"));
				out.flush();

				// 학생인지 교수인지 확인한다.
				if (user.getUserType().equals(User.PROFESSOR)) {

				} else if (user.getUserType().equals(User.STUDENT)) {

				} else {
					logger.info("Error - This is Another User");
				}

				// 클래스 매니저를 중복되지 않게 생성한다.
				boolean existClassInfo = false;
				int count = classManagerList.size();
				ClassManager existentClassManager = null;

				for (int i = 0; i < count; i++) {
					existentClassManager = classManagerList.get(i);
					if (classInfo.getClassId() == existentClassManager
							.getClassInfo().getClassId()) {
						existClassInfo = true;
						break;
					}
				}

				ClassManager classManager = null;
				// 최초의 클래스라면 클래스 매니저를 생성한다.
				if (!existClassInfo) {
					classManager = new ClassManager(classInfo);
					classManagerList.add(classManager);
				}
				// 이미 존재하는 클래스 라면 새로 생성하지 않는다.
				else {
					classManager = existentClassManager;
				}

				// 클래스 매니저에 유저(교수,학생)을 추가한다.
				classManager.addUserSocketThread(userSocket,user);
				
				logger.info("==========================================");
				logger.info("총 클래스 매니저 수 : "+ classManagerList.size());
				logger.info("현재 ClassManager 상태 ");
				logger.info("클래스 이름 : "+classManager.getClassInfo().getClassName());
				logger.info("유저 인원수 : " + classManager.getUserSocketThreadList().size());
				logger.info("==========================================");
				
				//클래스 매니저에 사람이 없으면 클래스매니저를 종료시킨다.
				for (int i = 0; i < classManagerList.size(); i++) {
					ClassManager thisClassManager = classManagerList.get(i);
					if(thisClassManager.userSocketThreadList.size() == 0)
						classManagerList.remove(thisClassManager);
				}
			}
		} catch (IOException e) {
			// 메인 스레드에서 오류나면 서버 소켓 닫고, 유저소켓 닫고 끝

			e.printStackTrace();

			try {
				SocketServer.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			try {
				userSocket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
	
	
}
