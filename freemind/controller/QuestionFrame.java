package freemind.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import freemind.json.ArrayClass;
import freemind.json.ArrayTicket;
import freemind.json.Ticket;
import freemind.main.BtnListener;
import freemind.modes.UploadToServer;



class QuestionFrame extends JFrame{
	BtnListener btnListener = new BtnListener(this);
	JScrollPane sPanel;
	QuestionPanel questPn;
	public QuestionFrame(String classId, String idxStr) {
		setSize(550, 400);
		setLayout(null);
		setTitle("Survey result");
		setVisible(true);
		setLocation(350, 200);
		
		//getContentPane().setBackground(new Color(175, 230, 121, 255));
		getContentPane().setBackground(new Color(141, 198, 63));
		Font f = new Font("Serif", Font.BOLD, 30);
		
		//JTextField lecturetf = new JTextField();
		JLabel titleLb = new JLabel("Hardware security");
		titleLb.setFont(f);
		titleLb.setSize(250, 50);
		titleLb.setLocation(20, 10);
		add(titleLb);

		questPn = new QuestionPanel(classId, idxStr);
		
		sPanel = new JScrollPane(questPn);
		sPanel.setBounds(20, 80, 460, 260);
		
		add(sPanel);
		
	}
	public void paint(Graphics g){
		
		super.paint(g);
		g.setColor(new Color(100, 100, 121, 255));
		g.drawLine(20, 85, 500, 85);
		g.drawLine(20, 86, 500, 86);
		//g.drawLine(20, 87, 600, 87);
		//g.fillRect(20, 150, 480, 200);
	}
	class QuestionPanel extends JPanel implements ActionListener{
		final int TOPPADDING = 60;
		final int TOPNUMPADDING = 50;
		final int QUESTIONHGAP = 30;
		final int LINETOPPADDING = 85;
		int questionCnt = 0;
		int questionReverse = 3;
		String idxStr;
		String classId;
		ArrayList<Ticket> ticketList;
		int childTotalCnt = 0;
		public QuestionPanel(String classId, String idxStr) {
			this.classId = classId;
			this.idxStr = idxStr;
			setSize(480, 360);
			setLayout(null);
			setVisible(true);
			setBackground(Color.white);
			
			
			init();
		}
		public void init(){
			
			JLabel noLb = new JLabel("No.");
			noLb.setSize(50, 40);
			noLb.setLocation(20, 10);
			add(noLb);
			JLabel titleLb = new JLabel("Title");
			titleLb.setSize(60, 40);
			titleLb.setLocation(180, 10);
			add(titleLb);
			JLabel writerLb = new JLabel("Writer");
			writerLb.setSize(50, 40);
			writerLb.setLocation(380, 10);
			add(writerLb);
			
			String sHtml = "";
			BufferedReader in = null;
			String buf = "";
			
			try
			{//http://61.43.139.10:8080/treeze/getClasses/?lectureName=LogicCircuit&professorEmail=minsuk@hansung.ac.kr
				//URL url = new URL("http://61.43.139.10:8080/treeze/getMyLectures?professorEmail=" + "minsuk@hansung.ac.kr");
				URL url = new URL("http://61.43.139.10:8080/treeze/getTickets/?classId=" + classId + "&position=" + idxStr);
				URLConnection urlconn = url.openConnection();
				in = new BufferedReader(new InputStreamReader(urlconn.getInputStream(),"UTF-8"));
				
				while((buf = in.readLine()) != null)
				{
					sHtml += buf;
				}
			}
			catch(Exception e)
			{
				System.out.println("연결 에러");
			}
			finally
			{
				if(sHtml.equals("")) sHtml = "Data가 존재하지 않습니다";
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(sHtml);
			ticketList = new ArrayList<Ticket>();
			Ticket tmpTicket;
			Gson gson = new Gson();
			
			Type type = new TypeToken<ArrayTicket>() {
			}.getType();
			ArrayTicket jonResultClasslist = (ArrayTicket) gson
					.fromJson(sHtml, type);
			ticketList = jonResultClasslist.getTickets();
			JLabel tmpLb;
			JButton tmpBtn;
			childTotalCnt = 0;
			ArrayList<Ticket> tmpTicketList = new ArrayList<Ticket>();
			for(questionCnt = 0; questionCnt < ticketList.size(); questionCnt++){
				tmpTicket = ticketList.get(questionCnt);
				tmpTicketList.add(tmpTicket);
				
				for(int i = 0; i < tmpTicketList.size(); i++){
					if(tmpTicket.getTicketPosition().substring(0, tmpTicket.getTicketPosition().length() - 2).equals(tmpTicketList.get(i).getTicketPosition())){
						tmpTicketList.get(i).setHaveChild(true);
						tmpTicketList.get(i).setChild(tmpTicket);
						ticketList.remove(tmpTicket);
						childTotalCnt++;
					}
				}
			}
			
			int childCnt = 0;
			for(questionCnt = 0; questionCnt < ticketList.size(); questionCnt++){
				tmpTicket = ticketList.get(questionCnt);
				tmpLb = new JLabel((ticketList.size() + childTotalCnt - questionCnt - childCnt) + "");
				tmpLb.setSize(50, 40);
				tmpLb.setLocation(20, TOPNUMPADDING + (questionCnt + childCnt) * QUESTIONHGAP);
				add(tmpLb);
				
				tmpBtn = new JButton(tmpTicket.getTicketTitle());
				tmpBtn.setSize(260, 20);
				tmpBtn.setLocation(60, TOPPADDING + (questionCnt + childCnt) * QUESTIONHGAP);
				tmpBtn.addActionListener(this);
				tmpBtn.setFocusable(false);
				add(tmpBtn);
				
				tmpLb = new JLabel(tmpTicket.getUserName());
				tmpLb.setSize(60, 40);
				tmpLb.setLocation(380, TOPNUMPADDING + (questionCnt + childCnt) * QUESTIONHGAP);
				add(tmpLb);
				
				if(tmpTicket.isHaveChild()){
					childCnt++;
					tmpTicket = tmpTicket.getChild();
					tmpLb = new JLabel((ticketList.size() + childTotalCnt - questionCnt - childCnt) + "");
					tmpLb.setSize(50, 40);
					tmpLb.setLocation(20, TOPNUMPADDING + (questionCnt + childCnt) * QUESTIONHGAP);
					add(tmpLb);
					
					tmpBtn = new JButton(tmpTicket.getTicketTitle());
					tmpBtn.setSize(260, 20);
					tmpBtn.setLocation(60, TOPPADDING + (questionCnt + childCnt) * QUESTIONHGAP);
					tmpBtn.addActionListener(this);
					tmpBtn.setFocusable(false);
					add(tmpBtn);
					
					tmpLb = new JLabel(tmpTicket.getUserName());
					tmpLb.setSize(60, 40);
					tmpLb.setLocation(380, TOPNUMPADDING + (questionCnt + childCnt) * QUESTIONHGAP);
					add(tmpLb);
				}
				
			}
			setPreferredSize(new Dimension(420, 20 + TOPPADDING + questionCnt * QUESTIONHGAP));
		}
		
		public void paint(Graphics g){
			super.paint(g);
			g.setColor(Color.black);
			
			g.drawLine(20, 50, 430, 50);
			g.drawLine(20, 51, 430, 51);
			
			for(int i = 0; i < (questionCnt + childTotalCnt); i++)
				g.drawLine(20, LINETOPPADDING + i * QUESTIONHGAP, 430, LINETOPPADDING + i * QUESTIONHGAP);
			
			//g.drawRoundRect(10, 40, 460, 100, 30, 30);
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String ticketTitle = e.getActionCommand();
			for(int i = 0; i < ticketList.size(); i++){
				if(ticketTitle.equals(ticketList.get(i).getTicketTitle())){
					new InputReplyFrame(ticketList.get(i));
					break;
				}
				else if(ticketTitle.equals(ticketList.get(i).getChild().getTicketTitle())){
					new InputReplyFrame(ticketList.get(i).getChild());
					break;
				}
			}
		}
		
		class InputReplyFrame extends JFrame implements ActionListener{
			JTextField classtf;
			JTextArea replyArea;
			JTextArea contentsArea;
			Ticket ticket;
			public InputReplyFrame(Ticket t) {
				ticket = t;
				setSize(400, 540);
				setLayout(null);
				setTitle("Input your reply");
				setVisible(true);
				setLocation(350, 200);
				
				getContentPane().setBackground(new Color(141, 198, 63));
				
				JLabel contentsLb = new JLabel("contents :");
				contentsLb.setSize(60, 30);
				contentsLb.setLocation(10, 10);
				add(contentsLb);
				
				contentsArea = new JTextArea();
				contentsArea.setText(ticket.getContents());
				contentsArea.setEditable(false);
				//replyArea.set
				contentsArea.setLineWrap(true);
				JScrollPane sPane = new JScrollPane(contentsArea);
				sPane.setBounds(80, 10, 200, 170);
//			replyArea.setSize(200, 300);
//			replyArea.setLocation(60, 10);
				add(sPane);
				
				
				JLabel inputLb = new JLabel("Reply :");
				inputLb.setSize(50, 30);
				inputLb.setLocation(10, 210);
//			JLabel inputFileLb = new JLabel("File  :");
//			inputFileLb.setSize(100, 30);
//			inputFileLb.setLocation(10, 40);
//			add(inputFileLb);
				
//			JButton fileBtn = new JButton("select PDF");
//			fileBtn.addActionListener(btnListener);
//			fileBtn.setSize(100, 30);
//			fileBtn.setLocation(40, 40);
//			add(fileBtn);
				replyArea = new JTextArea();
				//replyArea.set
				replyArea.setLineWrap(true);
				sPane = new JScrollPane(replyArea);
				sPane.setBounds(80, 210, 200, 250);
//			replyArea.setSize(200, 300);
//			replyArea.setLocation(60, 10);
				add(sPane);
				
//			classtf = new JTextField();
//			classtf.setSize(150, 25);
//			classtf.setLocation(60, 10);
				JButton input = new JButton("Reply");
				input.addActionListener(this);
				input.setSize(80, 25);
				input.setLocation(290, 434);
				add(inputLb);
				//add(classtf);
				add(input);
			}
			
			public void paint(Graphics g){
				super.paint(g);
				
				g.drawRect(86, 39, 202, 172);
				g.drawRect(87, 40, 200, 170);
				
				g.drawRect(86, 239, 202, 252);
				g.drawRect(87, 240, 200, 250);
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String classTitle = replyArea.getText();
				classTitle = classTitle.trim();
				JDialog dlg;
				
				if(classTitle.equals("")){
					dlg = new JDialog(this, "Error", true);
					JLabel errLb = new JLabel("Input your reply!");
					dlg.setLayout(new FlowLayout());
					dlg.add(errLb);
					dlg.setBounds(150,200,200,100);
					dlg.setVisible(true);
					return;
				}
				else{
//				String jsonStr;
//				FreemindGson myGson = new FreemindGson();
//				Lecture createLecture = new Lecture();
//				createLecture.setLectureName(lectureTitle);
//				createLecture.setProfessorEmail("minsuk@hansung.ac.kr");
//				createLecture.setStateOfLecture(false);
//				jsonStr = myGson.toJson(createLecture);
					
				UploadToServer UTS = new UploadToServer();
				UTS.ticketPost("[Re]" + ticket.getTicketTitle(), ticket.getClassId() + "", ticket.getPosition(), classTitle, "prof", ticket.getTicketPosition() + "/0");
//				UTS.classPost("Embedded System", "minsuk@hansung.ac.kr", classTitle);
					//UTS.lecturePost(classTitle, "minsuk@hansung.ac.kr", "false");
					
//				//UTS.doFileUpload("C:\\test\\양식있음 수학의 정석\\지수.jpg","http://localhost:8080/ImageUploadTest/file.jsp");
//				//UTS.doFileUpload(mmFilePath + ".mm","http://localhost:8080/ImageUploadTest/file.jsp");
				questPn.removeAll(); // 이상하게 No가 겹치네
				questPn.init();
				//questPn.update(questPn.getGraphics());
//				questPn.repaint();
//				sPanel.repaint();
					sPanel.updateUI();
					this.setVisible(false);
				}
			}
		}
		
	}
}

