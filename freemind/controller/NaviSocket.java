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

import freemind.modes.MindIcon;
import freemind.modes.MindMapNode;
import freemind.modes.UploadToServer;

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
		final String SURVEYYES = "0";
		final String SURVEYNO = "1";
		final String QUESTION = "2";
		int yesCnt = 0;
		int noCnt = 0;
		
		String folderName = "c://myweb//sPad/";// 기본 폴더 지정
		int cnt = -1;
		
		String chkStr;
		String rcvStr;
		
		try {
			int i = 0;
			String OK = "OK";
			String END = "E#N#D#";
			String[] userInfo;
			final String SUBSTR = "";
			
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			//InputStream fileIs = socket2.getInputStream();
			c.getNaviOs().add(os);
			
			write("------- A user is connect. --------");
			while (true) {
				cnt = is.read(b); // 받는 부분 // 프로토콜 정해서 해
				if(cnt == -1){
					System.out.println("socket end");
					write("socket end");
					c.getNaviOs().remove(os);
					break;
				}
				else{
					String str = new String(b, 0, cnt);
					chkStr = str.substring(0, 1);
					System.out.println(chkStr);
					
					if(chkStr.equals(SURVEYYES)){
						c.setYesCnt(c.getYesCnt() + 1);
						c.setTotalCnt(c.getTotalCnt() + 1);
					}
					else if(chkStr.equals(SURVEYNO)){
						c.setNoCnt(c.getNoCnt() + 1);
						c.setTotalCnt(c.getTotalCnt() + 1);
					}
					else if(chkStr.equals(QUESTION)){
						rcvStr = str.substring(1, str.length()); // 질문 처리
						
						//61.43.139.10:8080/treeze/createTicket
						//String ticketTitle, String classId, String position, String contents, String userEmail
						UploadToServer uts = new UploadToServer();
						//uts.ticketPost(ticketTitle, classId, position, contents, userEmail)
						
						String idxStr = "root";
						String[] splitStr;
						splitStr = idxStr.split("/");
						MindMapNode tmp = c.getModel().getRootNode(); // 소켓 받는 부분
						// idxStr == "root" 면 root
						//아니면 찾아
						if(!idxStr.equals("root")){
							for(i = 0; i < splitStr.length; i++) {
								tmp = (MindMapNode) tmp.getChildAt(Integer
										.parseInt(splitStr[i]));
							}
						}
						
						MindMapNode questionNode = tmp;
						System.out.println(questionNode.getText());
						MindIcon icon = MindIcon.factory("help");
						if(!questionNode.isQuestion()){
							questionNode.addIcon(icon, -1); // ? 아이콘 한번만
							questionNode.setQuestion(true);
						}
						c.getModeController().nodeChanged(questionNode);
						
						
						
						//c.getModeController().getSelected().getText()
					}
					
					if(c.getTotalCnt() == c.getNaviOs().size()){
						new SurveyResultFrame(c.getYesCnt(), c.getNoCnt());
						c.setYesCnt(0);
						c.setNoCnt(0);
						c.setTotalCnt(0);
					}
				}
				
			}
			
			is.close();
			os.close();
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
