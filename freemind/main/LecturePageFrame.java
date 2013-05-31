package freemind.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapController;


public class LecturePageFrame extends JFrame {
	private Container ct;

	JPanel classPanel;
	private Image profileImg;
	private URL profileImgURL = getClass().getClassLoader().getResource("minsuk.jpg");
	private Image logo;
	URL logoURL = getClass().getClassLoader().getResource("treezeLogo.png");
	ActionListener btnListener;
	MindMapController mc;
	private URL tmp;
	public LecturePageFrame(MindMapController mc) {
		this.mc = mc;
		profileImg = new ImageIcon(profileImgURL).getImage();
		logo = new ImageIcon(logoURL).getImage();
		btnListener = new BtnListener(this);
		classPanel = new ClassPanel(this, mc);
		
		setSize(950, 800);
		setLayout(null);//#afd679
		
		getContentPane().setBackground(new Color(141, 198, 63));

		Font lagF = new Font("Serif", Font.BOLD, 30);
		Font midF = new Font("Serif", Font.BOLD, 16);
		Font smaF = new Font("Serif", Font.BOLD, 10);
		
		JLabel name = new JLabel("Minsuk Lee");
		JLabel address = new JLabel("Department of Computer Engineering,");
		JLabel address2 = new JLabel("Hansung University, Professor");

		JLabel ltName = new JLabel("Embedded System");
		ltName.setSize(300, 40);
		ltName.setLocation(400, 20);
		ltName.setFont(lagF);
		add(ltName);
		
		name.setSize(300, 40);
		name.setFont(lagF);
		name.setLocation(70, 300);
		add(name);
		
		address.setSize(300, 40);
		address.setFont(midF);
		address.setLocation(20, 350);
		add(address);
		
		address2.setSize(300, 40);
		address2.setFont(midF);
		address2.setLocation(50, 370);
		add(address2);
		
//		JButton newLecture = new JButton("New Class");
//		newLecture.setSize(150, 40);
//		newLecture.setLocation(300, 85);
//		newLecture.setFont(midF);
//		newLecture.addActionListener(btnListener);
		
		//add(newLecture);
		
		tmp = getClass().getClassLoader().getResource("CreateLecture.png");
		
		JButton tmpBtn = new JButton(new ImageIcon(tmp));
		tmpBtn.setSize(132, 45);
		tmpBtn.setLocation(450, 75);
		tmpBtn.setFocusable(false);
		add(tmpBtn);
		
		tmp = getClass().getClassLoader().getResource("profile.png");
		
		tmpBtn = new JButton(new ImageIcon(tmp));
		tmpBtn.setSize(130, 45);
		tmpBtn.setLocation(300, 75);
		tmpBtn.setFocusable(false);
		add(tmpBtn);
		
		tmp = getClass().getClassLoader().getResource("deleteLecture.png");
		
		tmpBtn = new JButton(new ImageIcon(tmp));
		tmpBtn.setSize(135, 42);
		tmpBtn.setLocation(600, 75);
		tmpBtn.setFocusable(false);
		add(tmpBtn);
		
		tmp = getClass().getClassLoader().getResource("logout.png");
		
		tmpBtn = new JButton(new ImageIcon(tmp));
		tmpBtn.setSize(132, 45);
		tmpBtn.setLocation(750, 75);
		tmpBtn.setFocusable(false);
		add(tmpBtn);
		
		
		JScrollPane sPanel = new JScrollPane(classPanel);
		sPanel.setBounds(290, 130, 600, 498);
		
//		classPanel.setSize(600, 500);
//		classPanel.setLocation(290, 130);
		//connectPanel.setBackground(Color.white);
		
		add(sPanel);
		
		setTitle("Select your Class");
		setVisible(true);
		setLocation(200, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.drawImage(profileImg, 85, 150, 130, 170, null);
		g.drawImage(logo, 40, 50, 150, 30, null);
		g.drawLine(30, 90, 900, 90);
		
		g.setColor(Color.white);

		g.drawLine(40, 380, 270, 380);
		g.drawLine(40, 450, 270, 450);
		//g.drawImage(logo, 20, 30, null);
//		g.setColor(Color.white);
//		g.fillRoundRect(300, 150, 650, 500, 30, 30);
		g.setColor(Color.black);
		g.drawRect(298, 158, 600, 500);
		g.drawRect(297, 157, 602, 502);
		//g.drawRoundRect(299, 149, 652, 502, 30, 30);
//		g.drawRoundRect(299, 149, 653, 503, 30, 30);
//		g.drawRoundRect(298, 148, 654, 504, 30, 30);
		
	}
	
}

class ClassTopBarPanel extends JPanel implements ActionListener{
	JFrame frame;
	URL tmpURL;
	
	public ClassTopBarPanel(JFrame frame) {
		this.frame = frame;
		setSize(450, 500);
		setLayout(null);
		setBackground(new Color(141, 198, 63));
		
		tmpURL = getClass().getClassLoader().getResource("addClass.png");
		JButton tmpBtn = new JButton(new ImageIcon(tmpURL));
		tmpBtn.setSize(88, 30);
		tmpBtn.setLocation(360, 10);
		tmpBtn.setFocusable(false);
		tmpBtn.addActionListener(this);
		add(tmpBtn);
		
		tmpURL = getClass().getClassLoader().getResource("deleteClass.png");
		tmpBtn = new JButton(new ImageIcon(tmpURL));
		tmpBtn.setSize(89, 30);
		tmpBtn.setLocation(460, 10);
		tmpBtn.setFocusable(false);
		add(tmpBtn);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new InputClassFrame(frame);
	}
}

class InputClassFrame extends JFrame implements ActionListener{
	JFrame frame;
	JTextField classtf;
	
	public InputClassFrame(JFrame frame) {
		this.frame = frame;
		
		setSize(380, 100);
		setLayout(null);
		setTitle("Input your class title");
		setVisible(true);
		setLocation(350, 200);
		
		getContentPane().setBackground(new Color(141, 198, 63));
		JLabel inputLb = new JLabel("Title :");
		inputLb.setSize(50, 30);
		inputLb.setLocation(10, 10);
//		JLabel inputFileLb = new JLabel("File  :");
//		inputFileLb.setSize(100, 30);
//		inputFileLb.setLocation(10, 40);
//		add(inputFileLb);
		
//		JButton fileBtn = new JButton("select PDF");
//		fileBtn.addActionListener(btnListener);
//		fileBtn.setSize(100, 30);
//		fileBtn.setLocation(40, 40);
//		add(fileBtn);
		classtf = new JTextField();
		classtf.setSize(150, 25);
		classtf.setLocation(60, 10);
		JButton input = new JButton("Create class");
		input.addActionListener(this);
		input.setSize(110, 25);
		input.setLocation(240, 10);
		add(inputLb);
		add(classtf);
		add(input);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String classTitle = classtf.getText();
		classTitle = classTitle.trim();
		JDialog dlg;
		
		if(classTitle.equals("")){
			dlg = new JDialog(this, "Error", true);
			JLabel errLb = new JLabel("Input your class!");
			dlg.setLayout(new FlowLayout());
			dlg.add(errLb);
			dlg.setBounds(150,200,200,100);
			dlg.setVisible(true);
			return;
		}
		else{
//			String jsonStr;
//			FreemindGson myGson = new FreemindGson();
//			Lecture createLecture = new Lecture();
//			createLecture.setLectureName(lectureTitle);
//			createLecture.setProfessorEmail("minsuk@hansung.ac.kr");
//			createLecture.setStateOfLecture(false);
//			jsonStr = myGson.toJson(createLecture);
			
			UploadToServer UTS = new UploadToServer();
			UTS.classPost("Embedded System", "minsuk@hansung.ac.kr", classTitle);
			//UTS.lecturePost(classTitle, "minsuk@hansung.ac.kr", "false");
			
//			//UTS.doFileUpload("C:\\test\\양식있음 수학의 정석\\지수.jpg","http://localhost:8080/ImageUploadTest/file.jsp");
//			//UTS.doFileUpload(mmFilePath + ".mm","http://localhost:8080/ImageUploadTest/file.jsp");
			
			this.setVisible(false);
		}
	}
}


class ClassPanel extends JPanel implements ActionListener{
	JLabel tmpLb, professor, state;
	Image onBookMark, offBookMark;
	Image onState, offState;
	//URL onBookMarkURL = getClass().getClassLoader().getResource("onBookMark.png");
	JFrame frame;
	URL slideShowURL, mindmapURL;
	MindMapController mc;
	final int TOPPADDING = 130;
	final int CLASSHGAP = 60;
	int classCnt = 0;
	String[] latestDay = {"2013.4.11", "2013.5.21", "2013.1.22", "2012.12.23", "2012.1.3", "2013.4.23", "2013.3.3", "2012.11.23", "2012.10.1"
			, "2012.5.23", "2013.5.23", "2012.2.23", "2012.1.28", "2012.6.23", "2012.7.23", "2011.7.23", "2012.8.15", "2011.7.2"}; // 18개
	public ClassPanel(JFrame frame, MindMapController mc) {
		this.frame = frame;
		this.mc = mc;
		setBackground(Color.white);
		setSize(450, 500);
		setLayout(null);
		
		Font lagf = new Font("Serif", Font.BOLD, 30);
		tmpLb = new JLabel("Class Title");
		tmpLb.setSize(200, 50);
		tmpLb.setFont(lagf);
		tmpLb.setLocation(60, 60);
		add(tmpLb);
		
		tmpLb = new JLabel("View");
		tmpLb.setSize(100, 50);
		tmpLb.setFont(lagf);
		tmpLb.setLocation(490, 60);
		add(tmpLb);
		
		tmpLb = new JLabel("Latest class day");
		tmpLb.setSize(250, 50);
		tmpLb.setFont(lagf);
		tmpLb.setLocation(240, 60);
		add(tmpLb);
		
		JLabel embedded = new JLabel("First Class");
		embedded.setFont(lagf);
		embedded.setSize(240, 50);
		embedded.setLocation(40, TOPPADDING + classCnt * CLASSHGAP);
		classCnt++;
		add(embedded);
		
		JLabel logic = new JLabel("Second Class");
		logic.setFont(lagf);
		logic.setSize(240, 50);
		logic.setLocation(40, TOPPADDING + classCnt * CLASSHGAP);
		
		classCnt = 0;
		
		lagf = new Font("Serif", Font.BOLD, 20);
		
		JLabel tmpLb = new JLabel(latestDay[0]);
		tmpLb.setFont(lagf);
		tmpLb.setSize(240, 50);
		tmpLb.setLocation(295, TOPPADDING + classCnt * CLASSHGAP);
		add(tmpLb);
		classCnt++;
		
		tmpLb = new JLabel(latestDay[1]);
		tmpLb.setFont(lagf);
		tmpLb.setSize(240, 50);
		tmpLb.setLocation(295, TOPPADDING + classCnt * CLASSHGAP);
		add(tmpLb);
		
		classCnt = 0;
		
		slideShowURL = getClass().getClassLoader().getResource("slideShow.png");
		mindmapURL = getClass().getClassLoader().getResource("mindmap.png");
		
		JButton slideBtn = new JButton(new ImageIcon(slideShowURL));
		JButton mindmapBtn = new JButton("123412341234", new ImageIcon(mindmapURL));
		
		slideBtn.setSize(100, 24);
		slideBtn.setLocation(472, TOPPADDING + classCnt * CLASSHGAP);
		slideBtn.setFocusable(false);
		add(slideBtn);
		
		mindmapBtn.setSize(100, 24);
		mindmapBtn.setLocation(472, TOPPADDING + classCnt * CLASSHGAP + 25);
		mindmapBtn.setFocusable(false);
		mindmapBtn.addActionListener(this);
		add(mindmapBtn);
		classCnt++;

		
		slideBtn = new JButton(new ImageIcon(slideShowURL));
		mindmapBtn = new JButton("456456456456", new ImageIcon(mindmapURL));
		
		slideBtn.setSize(100, 24);
		slideBtn.setLocation(472, TOPPADDING + classCnt * CLASSHGAP);
		slideBtn.setFocusable(false);
		add(slideBtn);
		
		mindmapBtn.setSize(100, 24);
		mindmapBtn.setLocation(472, TOPPADDING + classCnt * CLASSHGAP + 25);
		mindmapBtn.setFocusable(false);
		mindmapBtn.addActionListener(this);
		add(mindmapBtn);
		classCnt++;
		
		setPreferredSize(new Dimension(550, 20 + TOPPADDING + classCnt * CLASSHGAP));
		
		ClassTopBarPanel classToppn = new ClassTopBarPanel(null);
		classToppn.setSize(580, 50);
		classToppn.setLocation(10, 10);
		add(classToppn);
		
//		JLabel prof = new JLabel("이 민석");
//		prof.setFont(lagf);
//		prof.setSize(200, 50);
//		prof.setLocation(360, 100);
//		add(prof);
//		
//		JLabel prof2 = new JLabel("이 민석");
//		prof2.setFont(lagf);
//		prof2.setSize(200, 50);
//		prof2.setLocation(360, 160);
//		add(prof2);
//		
//		JLabel prof3 = new JLabel("이 민석");
//		prof3.setFont(lagf);
//		prof3.setSize(200, 50);
//		prof3.setLocation(360, 220);
		//add(prof3);
		
		
		add(logic);
		//add(system);
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(20, 120, 580, 120);
		
		g.drawLine(220, 140, 220, TOPPADDING + CLASSHGAP * classCnt);
		g.drawLine(450, 140, 450, TOPPADDING + CLASSHGAP * classCnt);
		
//		g.setColor(Color.white);
//
//		g.drawLine(40, 380, 250, 380);
//		g.drawLine(40, 450, 250, 450);
//		g.setColor(Color.white);
		//g.drawRect(300, 150, 650, 500, 30, 30);
//		g.setColor(Color.black);
//		g.drawRoundRect(299, 149, 652, 502, 30, 30);
//		g.drawRoundRect(299, 149, 653, 503, 30, 30);
//		g.drawRoundRect(298, 148, 654, 504, 30, 30);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		System.out.println(event);
		mc.open(mc, event);
		frame.setVisible(false);
	}
}

	
