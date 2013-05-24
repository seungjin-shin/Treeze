package freemind.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NaviSocket extends JFrame implements Runnable{
	private JTextArea textArea;
	private JScrollPane pane;
	Controller c;
	Socket s;
	public NaviSocket(Controller c) {
		this.c = c;
		setTitle("Navi Server");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int h = Toolkit.getDefaultToolkit().getScreenSize().height / 3;
		int w = Toolkit.getDefaultToolkit().getScreenSize().width / 3;
		textArea = new JTextArea();
		pane = new JScrollPane(textArea);
		pane.setAutoscrolls(true);
		textArea.setEditable(false);
		add(pane);
		setBounds(w, h, w, h);
		setVisible(true);
	}

	public void init() {

		try {
			ServerSocket ss = new ServerSocket(2141);
			textArea.append("########## Server Start ##########\n");
			pane.getVerticalScrollBar().setValue(
					pane.getVerticalScrollBar().getMaximum());
			while (true) {
				s = ss.accept();
				Start go = new Start(c, s, textArea, pane);
				go.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		NaviSocket server = new NaviSocket();
//		server.init();
//	}
}

class Start extends Thread {
	private Socket socket;
	private byte[] b = new byte[1024];
	private JTextArea text;
	private JScrollPane pane;
	private Controller c;
	
	public Start(Controller c, Socket s, JTextArea text, JScrollPane pane) {
		this.c = c;
		socket = s;
		this.text = text;
		this.pane = pane;
	}

	public void write(String content) {
		text.append(content + "\n");
		pane.getVerticalScrollBar().setValue(
				pane.getVerticalScrollBar().getMaximum());
	}

	public void run() {
		int slideNum = 0;
		String fileName = "";
		String folderName = "c://myweb//sPad/";// 기본 폴더 지정
		String pptName = "";
		int cnt = -1;

		try {
			int i = 0;
			int fileLen;
			String OK = "OK";
			String END = "E#N#D#";
			String[] userInfo;
			final String SUBSTR = ".ppt";
			String[] pptNameSplit;
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			//InputStream fileIs = socket2.getInputStream();
			c.getNaviOs().add(os);
			
			write("------- A user is connect. --------");
			while (true) {
				cnt = is.read(b); // 받는 부분 // 프로토콜 정해서 해
				System.out.println(cnt);
				System.out.println(b.toString());
				if(cnt == -1){
					System.out.println("socket end");
					write("socket end");
					c.getNaviOs().remove(os);
					return;
				}
				
				String str = new String(b, 0, cnt);
				userInfo = str.split(END);
				write(str);
				write(userInfo[0]+","+userInfo[1]); // 0은 폰번호, 1은 ppt이름
				if (str.indexOf(END) != -1) {
					File folder = new File(folderName + userInfo[0]);
					folder.mkdirs();
					break;
				}
			}

			// 이미지화
			write("To make images is completed.");
			//이미지 수 보내
			os.write((slideNum+END).getBytes());
			os.flush();

			while (true) {
				cnt = is.read(b);
				String str = new String(b, 0, cnt);
				write("User has to down slides number is : "+str.substring(0, str.indexOf(END)));
				if (str.indexOf(END) != -1) {
					break;
				}
			}
			
			write("------- A user is completed. --------");
			is.close();
			os.close();
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
