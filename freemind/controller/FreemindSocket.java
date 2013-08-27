//package freemind.controller;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics2D;
//import java.awt.Toolkit;
//import java.awt.event.KeyEvent;
//import java.awt.geom.Rectangle2D;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.lang.reflect.Type;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//
//import javax.swing.JFrame;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import freemind.json.ArrayLecture;
//import freemind.json.FreemindGson;
//import freemind.json.Lecture;
//import freemind.json.TicketInfo;
//import freemind.modes.MindIcon;
//import freemind.modes.MindMapNode;
//import freemind.modes.UploadToServer;
//
//public class FreemindSocket extends JFrame implements Runnable{
//	private JTextArea textArea;
//	private JScrollPane pane;
//	Controller c;
//	Socket s;
//	public FreemindSocket(Controller c) {
//		this.c = c;
//		setTitle("Navi Server");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		int h = Toolkit.getDefaultToolkit().getScreenSize().height / 3;
//		int w = Toolkit.getDefaultToolkit().getScreenSize().width / 3;
//		textArea = new JTextArea();
//		pane = new JScrollPane(textArea);
//		pane.setAutoscrolls(true);
//		textArea.setEditable(false);
//		add(pane);
//		setBounds(w, h, w, h);
//		setVisible(true);
//	}
//
//	public void write(String content) {
//		textArea.append(content + "\n");
//		pane.getVerticalScrollBar().setValue(
//				pane.getVerticalScrollBar().getMaximum());
//	}
//
//	public void init() {
//
//		try {
//			ServerSocket ss = new ServerSocket(2141);
//			textArea.append("########## Server Start ##########\n");
//			pane.getVerticalScrollBar().setValue(
//					pane.getVerticalScrollBar().getMaximum());
//			while (true) {
//				s = ss.accept();
//				Start go = new Start(c, s, textArea, pane);
//				go.start();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		init();
//	}
//}
//
//class Start extends Thread {
//	private Socket socket;
//	private byte[] b = new byte[1024];
//	private JTextArea text;
//	private JScrollPane pane;
//	private Controller c;
//	
//	public Start(Controller c, Socket s, JTextArea text, JScrollPane pane) {
//		this.c = c;
//		socket = s;
//		this.text = text;
//		this.pane = pane;
//	}
//
//	public void write(String content) {
//		text.append(content + "\n");
//		pane.getVerticalScrollBar().setValue(
//				pane.getVerticalScrollBar().getMaximum());
//	}
//
//	public void run() {
//		final String SURVEYYES = "0";
//		final String SURVEYNO = "1";
//		final String QUESTION = "2";
//
//		int cnt = -1;
//		
//		//dewlit
//		String testStr;
//		//dewlit
//
//		String chkStr;
//		String rcvStr;
//		
//		try {
//			int i = 0;
//			
//			InputStream is = socket.getInputStream();
//			OutputStream os = socket.getOutputStream();
//			c.getNaviOs().add(os);
//			
//			write("------- A user is connect. --------");
//			System.out.println("------- A user is connect. --------");
//			while (true) {
//				cnt = is.read(b); // 받는 부분
//				if(cnt == -1){
//					System.out.println("socket end");
//					write("socket end");
//					c.getNaviOs().remove(os);
//					break;
//				}
//				else{
//					String str = new String(b, 0, cnt, "UTF-8");
//					chkStr = str.substring(0, 1);
//					System.out.println("구분자 : " + chkStr);
//					
//					if(chkStr.equals(SURVEYYES)){
//						c.setYesCnt(c.getYesCnt() + 1);
//						c.setTotalCnt(c.getTotalCnt() + 1);
//					}
//					else if(chkStr.equals(SURVEYNO)){
//						c.setNoCnt(c.getNoCnt() + 1);
//						c.setTotalCnt(c.getTotalCnt() + 1);
//					}
//					else if(chkStr.equals(QUESTION)){
//						rcvStr = str.substring(1, str.length());
//						System.out.println(rcvStr);
//						TicketInfo ticket = new TicketInfo();
//						FreemindGson myGson = new FreemindGson();
//						Gson gson = new Gson();
//						boolean betweenStu = false;
//						boolean twoAnswer = false;
//						Type type = new TypeToken<TicketInfo>() {
//						}.getType();
//						ticket = (TicketInfo) gson.fromJson(rcvStr, type);
//						
//							String idxStr = ticket.getPosition(); 
//							String[] splitStr;
//							splitStr = idxStr.split("/");
//							MindMapNode tmp = c.getModel().getRootNode(); 
//
//							int parentPositionCnt = 0;
//							if(!idxStr.equals("root")){
//								for (i = 0; i < splitStr.length; i++) {
//									if(Integer.parseInt(splitStr[i]) == tmp.getChildCount() || tmp.getChildCount() == 0){
//										if(i + 2 <= splitStr.length){
//											parentPositionCnt = i;
//											twoAnswer = true; // 답글에 답글
//										}
//										betweenStu = true;
//										break;
//									}
//									tmp = (MindMapNode) tmp.getChildAt(Integer
//											.parseInt(splitStr[i]));
//								}
//							}
//							if(twoAnswer){
//							if (parentPositionCnt > 0) {
//								idxStr = ticket.getPosition().substring(0,
//										(parentPositionCnt * 2) - 1);
//								splitStr = idxStr.split("/");
//								tmp = c.getModel().getRootNode(); 
//
//								for (i = 0; i < splitStr.length; i++) {
//									tmp = (MindMapNode) tmp.getChildAt(Integer
//											.parseInt(splitStr[i]));
//								}
//							}
//							}
//							else if(betweenStu){
//								idxStr = ticket.getPosition().substring(0, ticket.getPosition().length() - 2); 
//								splitStr = idxStr.split("/");
//								tmp = c.getModel().getRootNode(); 
//								
//								for (i = 0; i < splitStr.length; i++) {
//									tmp = (MindMapNode) tmp.getChildAt(Integer
//											.parseInt(splitStr[i]));
//								}
//								
//							}
//							MindMapNode questionNode = tmp;
//							System.out.println(questionNode.getText() + "에 질문 받음");
//							MindIcon icon = MindIcon.factory("help");
////							if(!questionNode.isQuestion()){
////								questionNode.addIcon(icon, -1); // ? 아이콘 한번만
////								questionNode.setQuestion(true);
////							}
//							c.getModeController().nodeChanged(questionNode);
//							
//							UploadToServer uts = new UploadToServer();
//							uts.ticketPost(ticket.getTicketTitle(), c.getClassId() + "", idxStr, ticket.getContents(), ticket.getUserName(), ticket.getTicketPosition());
//						
//							OutputStream tmpOs;
//							for(i = 0; i < c.getNaviOs().size(); i++){
//								tmpOs = c.getNaviOs().get(i);
//								try {
//									if(tmpOs != null){
//										if(!os.equals(tmpOs)){
//											tmpOs.write((QUESTION + rcvStr).getBytes("UTF-8"));
//										}
//									}
//								} catch (IOException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//							}
//					}
//					
//					if(c.getTotalCnt() == c.getNaviOs().size()){
//						
//						LectureInfo lectureInfo;
//			    		lectureInfo = FreemindLectureManager.getInstance();
//			    		
//						new SurveyResultFrame(c.getYesCnt(), c.getNoCnt(), lectureInfo.getSurverTitle());
//						c.setYesCnt(0);
//						c.setNoCnt(0);
//						c.setTotalCnt(0);
//					}
//				}
//				
//			}
//			
//			is.close();
//			os.close();
//			socket.close();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}

package freemind.controller;

import java.awt.FlowLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;

import com.google.gson.Gson;

import freemind.Frame.SurveyFrame;
import freemind.Frame.SurveyResultFrame;
import freemind.json.ArrayLecture;
import freemind.json.Survey;
import freemind.json.Ticket;
import freemind.json.TmpTicket;
import freemind.json.TreezeData;
import freemind.modes.mindmapmode.MindMapController;

public class FreemindSocket extends Thread {
	Controller c;
	MindMapController mc;
	InputStream in;
	FreemindManager fManager = FreemindManager.getInstance();

	public FreemindSocket(Controller c, MindMapController mc, InputStream in) {
		this.c = c;
		this.mc = mc;
		this.in = in;
	}

	@Override
	public void run() {
		int cnt = -1;
		byte[] b = new byte[1024];
		TreezeData treezeData;
		Gson gson = new Gson();

		while (true) {
			try {
				cnt = in.read(b);
				
				if (cnt == -1) {
					// c.getNaviOs().remove(os); // remove Client at Err
					break;
				} else {
					String rcvStr = null;
					try {
						rcvStr = new String(b, 0, cnt, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("FindMindSocket - rcvStr : " + rcvStr);
					
					treezeData = gson.fromJson(rcvStr, TreezeData.class);
					
					if(treezeData.getDataType().equals(TreezeData.TICKET)){
						
						new SurveyFrame();
						
						TmpTicket tmpTicket = gson.fromJson(treezeData.getArgList().get(0), TmpTicket.class);
						Ticket ticket = tmpTicket.getTicket();
						Thread addTicketThread = new AddTicketThread(ticket);
						addTicketThread.start();
//						fManager.setTicket(ticket);
//						c.recurAddTicketNode((NodeAdapter) c.getMc().getRootNode());
						
						
						
					}
					else if(treezeData.getDataType().equals(TreezeData.SURVEYRESULT)){
						Survey survey = gson.fromJson(treezeData.getArgList().get(0), Survey.class);
						ArrayList<Integer> resultArray;
						resultArray = survey.getSurveyType().getReultOfSurvey();
						new SurveyResultFrame(survey.getTotalNumberOfStudents(), resultArray.get(0), resultArray.get(1), survey.getContents());
					}
					
					else{
						System.out.println(treezeData.getDataType() + " : TreezeData is not define");
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			} // 받는 부분
			
		}
		System.out.println("socket end");

	}

}
