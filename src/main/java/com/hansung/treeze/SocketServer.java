package com.hansung.treeze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
		BufferedReader in;
		PrintWriter out;
		String reqMsg;
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

					in = new BufferedReader(new InputStreamReader(
							userSocket.getInputStream()));
					out = new PrintWriter(new OutputStreamWriter(
							userSocket.getOutputStream()));

					reqMsg = in.readLine();
					
					if(reqMsg == null){
						System.out.println("Client Socket failed");
						continue;
					}
					logger.info("Client Request Message : " + reqMsg);

					TreezeData treezedata =  gson.fromJson(reqMsg,TreezeData.class);
					
					User user = gson.fromJson(treezedata.getArgList().get(0), User.class);
					ClassInfo classInfo = gson.fromJson(treezedata.getArgList().get(1), ClassInfo.class);
					
					out.println(reqMsg);
					out.flush();
					
					/*int count = classManagerList.size();
					
					for (int i = 0; i < count; i++) {
						if(classInfo.equals(classManagerList.get(i)))
					}*/

					if (user.getUserType().equals(User.PROFESSOR)) {

						classManager = new ClassManager(user, userSocket,
								classInfo, classManagerList);
						classManagerList.add(classManager);

						try {
							classManager.init();
						} catch (ServletException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (user.getUserType().equals(User.STUDENT)) {
						int count = classManagerList.size();
						
						for (int i = 0; i < count; i++) {
							classManager = classManagerList.get(i);
							if (classManagerList.get(i).getClassInfo()
									.equals(classInfo))
								break;
						}

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
