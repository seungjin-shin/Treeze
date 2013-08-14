package freemind.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class tmpSocketThread extends Thread{
	ServerSocket ss;
	Socket s;
	FreemindManager fManager;
	
	public void run() {
		
		fManager = FreemindManager.getInstance();
		
		try {
			ss = new ServerSocket(2141);
			s = ss.accept();
			
			
//			System.out.println(s.get);
			
			fManager.setOs(s.getOutputStream());
			fManager.setIn(s.getInputStream());
			fManager.getC().startFreemindSocket();
			fManager.getSlideShow().setOs(s.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
