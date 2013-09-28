package com.hansung.treeze;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.hansung.treeze.model.User;

@SuppressWarnings("serial")
public class UserSocketThread extends HttpServlet implements Runnable {
	// 유저 (교수, 학생)소켓 하나 Thread 하나

	private ClassManager classManager;
	private static final Logger logger = LoggerFactory
			.getLogger(UserSocketThread.class);
	private Thread userSocketThread;
	Socket userSocket = null;
	User user ;
	private InputStream in;
	private OutputStream out;
	Gson gson = new Gson();
	String reqMsg = null;

	// 유저소켓스레드 생성자
	public UserSocketThread(ClassManager classManager,Socket userSocket, User user) {
		this.classManager = classManager;
		this.userSocket = userSocket;
		this.user = user;
	}

	public void init() throws ServletException {
		userSocketThread = new Thread(this);
		userSocketThread.start();
	}

	public void destroy() {
		userSocketThread.interrupt();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			logger.info("==========================");
			logger.info("User Socket (" + user.getUserName()
					+ ") 입장 ");
			logger.info("==========================");
			startUserSocketThread();
		} catch (Exception e) {
			logger.info("User Socket 오류 : " + e.getMessage());
			destroy();
		}
		removeThis();
	}

	public void startUserSocketThread() {

		try {
			in = userSocket.getInputStream();
			out = userSocket.getOutputStream();

			while (true) {
				int cnt = -1;
				byte[] buffer = new byte[1024];

				cnt = in.read(buffer);

				if (cnt == -1) {
					// 소켓 갑자기 상대방에서 끊어지면
					// 이 소켓 닫아줘야해
					logger.info("===================Good bye===================");
					logger.info(classManager.getClassInfo().getClassName()+"에서 "+user.getUserName()+"(이)가 "+"퇴장합니다.");
					logger.info("===================++++++++===================");
					userSocket.close();
					reqMsg = null;
					return;
				} else {
					// 소켓에서 정상적으로 메시지가 날라오면 받아야해
					reqMsg = new String(buffer, 0, cnt, "UTF-8");
					logger.info(classManager.getClassInfo().getClassName()+"에서 "+user.getUserName()+"(이)가 "+"Client Request Message : " + reqMsg);
					classManager.broadcast(reqMsg);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				userSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

	}
	
	public void sendTreezeData(String treezeData){
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
	
	public void removeThis(){
		classManager.userSocketThreadList.remove(this);
	}
}
