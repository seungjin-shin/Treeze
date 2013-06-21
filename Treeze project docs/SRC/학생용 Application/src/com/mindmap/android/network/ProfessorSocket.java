package com.mindmap.android.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ProfessorSocket {
	private static Socket socket;
	private static String ipAddress;
	private static int portNum;

	public static Socket getSocket() {
		return socket;
	}

	public static void endSocket() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		socket = null;
	}

	public static void setSocket(String ipAddress, int portNum) {
		if (socket == null)
			try {
				socket = new Socket(ipAddress, portNum);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
