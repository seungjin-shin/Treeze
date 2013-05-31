package freemind.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import freemind.main.BtnListener;
import freemind.modes.UploadToServer;



class QuestionFrame extends JFrame{
	BtnListener btnListener = new BtnListener(this);
	
	public QuestionFrame() {
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

		QuestionPanel questPn = new QuestionPanel();
		
		JScrollPane sPanel = new JScrollPane(questPn);
		sPanel.setBounds(20, 80, 460, 260);
		
		add(sPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void paint(Graphics g){
		
		super.paint(g);
		g.setColor(new Color(100, 100, 121, 255));
		g.drawLine(20, 85, 500, 85);
		g.drawLine(20, 86, 500, 86);
		//g.drawLine(20, 87, 600, 87);
		//g.fillRect(20, 150, 480, 200);
	}
}
class QuestionPanel extends JPanel implements ActionListener{
	final int TOPPADDING = 60;
	final int TOPNUMPADDING = 50;
	final int QUESTIONHGAP = 30;
	final int LINETOPPADDING = 85;
	int questionCnt = 0;
	int questionReverse = 3;
	
	public QuestionPanel() {
		setSize(480, 360);
		setLayout(null);
		setVisible(true);
		setBackground(Color.white);
		
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
		
		JLabel no3 = new JLabel("3");
		no3.setSize(50, 40);
		no3.setLocation(20, TOPNUMPADDING + questionCnt * QUESTIONHGAP);
		add(no3);
		questionCnt++;

		JLabel no1 = new JLabel("2");
		no1.setSize(50, 40);
		no1.setLocation(20, TOPNUMPADDING + questionCnt * QUESTIONHGAP);
		add(no1);
		questionCnt++;
		
		JLabel no2 = new JLabel("1");
		no2.setSize(50, 40);
		no2.setLocation(20, TOPNUMPADDING + questionCnt * QUESTIONHGAP);
		add(no2);
		
		questionCnt = 0;
		
		JButton title1 = new JButton("What is the meaning 'HSM'?");
		title1.setSize(260, 20);
		title1.setLocation(60, TOPPADDING + questionCnt * QUESTIONHGAP);
		title1.addActionListener(this);
		title1.setFocusable(false);
		add(title1);
		questionCnt++;
		
		JButton reply = new JButton("[Re] What is the meaning 'HSM'?");
		reply.setSize(260, 20);
		reply.setLocation(60, TOPPADDING + questionCnt * QUESTIONHGAP);
		reply.addActionListener(this);
		reply.setFocusable(false);
		add(reply);
		questionCnt++;
		
		JButton title2 = new JButton("About SafeNet...");
		title2.setSize(260, 20);
		title2.setLocation(60, TOPPADDING + questionCnt * QUESTIONHGAP);
		title2.setFocusable(false);
		title2.addActionListener(this);
		add(title2);
		questionCnt++;
		
		setPreferredSize(new Dimension(420, 20 + TOPPADDING + questionCnt * QUESTIONHGAP));
		questionCnt = 0;
		
		JLabel writer1 = new JLabel("0892070");
		writer1.setSize(60, 40);
		writer1.setLocation(380, TOPNUMPADDING + questionCnt * QUESTIONHGAP);
		add(writer1);
		questionCnt++;
		
		JLabel writer2 = new JLabel("prof");
		writer2.setSize(60, 40);
		writer2.setLocation(380, TOPNUMPADDING + questionCnt * QUESTIONHGAP);
		add(writer2);
		questionCnt++;
		
		JLabel writer3 = new JLabel("0892051");
		writer3.setSize(60, 40);
		writer3.setLocation(380, TOPNUMPADDING + questionCnt * QUESTIONHGAP);
		add(writer3);
		
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.black);
		
		g.drawLine(20, 50, 430, 50);
		g.drawLine(20, 51, 430, 51);
		
		for(int i = 0; i < questionCnt; i++)
			g.drawLine(20, LINETOPPADDING + i * QUESTIONHGAP, 430, LINETOPPADDING + i * QUESTIONHGAP);
		
		//g.drawRoundRect(10, 40, 460, 100, 30, 30);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new InputReplyFrame();
	}
	
	class InputReplyFrame extends JFrame implements ActionListener{
		JTextField classtf;
		JTextArea replyArea;
		JTextArea contentsArea;
		public InputReplyFrame() {
			
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
			contentsArea.setText("질문이 들어ㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓ간ㄷ다ㅏㅏㅏㅏㅏ다ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ 수정못하지롱");
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
				
//				UploadToServer UTS = new UploadToServer();
//				UTS.classPost("Embedded System", "minsuk@hansung.ac.kr", classTitle);
				//UTS.lecturePost(classTitle, "minsuk@hansung.ac.kr", "false");
				
//				//UTS.doFileUpload("C:\\test\\양식있음 수학의 정석\\지수.jpg","http://localhost:8080/ImageUploadTest/file.jsp");
//				//UTS.doFileUpload(mmFilePath + ".mm","http://localhost:8080/ImageUploadTest/file.jsp");
				
				this.setVisible(false);
			}
		}
	}
	
}

