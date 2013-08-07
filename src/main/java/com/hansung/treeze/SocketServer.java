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

@SuppressWarnings("serial")
public class SocketServer extends HttpServlet implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);
	private Thread daemon;
	private ArrayList <Socket> socketList;
	
	public void init()throws ServletException{
		daemon = new Thread(this);
		daemon.start();
	}
	public void destroy(){
		daemon.interrupt();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			logger.info("==========================");
			logger.info("Treeze SocketServer RUN");
			logger.info("==========================");
			warningInit();
		} catch(Exception e){
			logger.info("ServerSocket failed : " + e.getMessage());
		}
		
	}
	public void warningInit(){
		
		try {
			@SuppressWarnings("resource")
			ServerSocket SocketServer = new ServerSocket(8888);
			while (true) {
				try {
					logger.info("Watting Client");
					Socket warningSocket = SocketServer.accept(); // Ŭ���̾������Ҷ����� ���
					
					logger.info("Client IP : " + warningSocket.getInetAddress());
					
					BufferedReader in = new BufferedReader(new InputStreamReader(warningSocket.getInputStream()));
					PrintWriter out = new PrintWriter(new OutputStreamWriter(warningSocket.getOutputStream()));
					
					String reqMsg = in.readLine();
					
					logger.info("Client Request Message : " + reqMsg);
					
					out.println(reqMsg);
					out.flush();
					socketList.add(warningSocket);
					
					//warningSocket.close();
					
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		} catch (MalformedURLException e) {
			// TODO: handle exception
			logger.info(e.toString());
		}catch (IOException e) {
			// TODO: handle exception
			logger.info(e.toString());
		}
	}
	
	
	
}
