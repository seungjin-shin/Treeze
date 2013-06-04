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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import freemind.json.ArrayLecture;
import freemind.json.FreemindGson;
import freemind.json.Lecture;
import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapController;
import java.lang.reflect.Type;

public class LoggedInFrame extends JFrame {
	private Container ct;

	LecturePanel lecturePanel;
	private Image profileImg;
	private URL profileImgURL = getClass().getClassLoader().getResource("minsuk.jpg");
	private Image logo;
	private URL tmp;// = getClass().getClassLoader().getResource("profile.png");
	URL logoURL = getClass().getClassLoader().getResource("treezeLogo.png");
	CreateBtn createLs;
	MindMapController mc;
	public LoggedInFrame(MindMapController mc) {
		this.mc = mc;
		lecturePanel = new LecturePanel(this, mc);
		JScrollPane sPanel = new JScrollPane(lecturePanel);
		createLs = new CreateBtn(lecturePanel, sPanel);
		
		profileImg = new ImageIcon(profileImgURL).getImage();
		logo = new ImageIcon(logoURL).getImage();
		
		setSize(950, 800);
		setLayout(null);//#afd679
		
		//getContentPane().setBackground(new Color(175, 230, 121, 255));142, 214, 63  
		getContentPane().setBackground(new Color(141, 198, 63));
		JLabel name = new JLabel("Minsuk Lee");
		JLabel address = new JLabel("Department of Computer Engineering,");
		JLabel address2 = new JLabel("Hansung University, Professor");
		
		Font lagF = new Font("Serif", Font.BOLD, 30);
		Font midF = new Font("Serif", Font.BOLD, 16);
		Font smaF = new Font("Serif", Font.BOLD, 10);
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
		tmp = getClass().getClassLoader().getResource("CreateLecture.png");
		
		JButton tmpBtn = new JButton(new ImageIcon(tmp));
		tmpBtn.setSize(132, 45);
		tmpBtn.setLocation(450, 75);
		tmpBtn.setFocusable(false);
		tmpBtn.addActionListener(createLs);
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
		
		
		sPanel.setBounds(290, 130, 600, 498);
//		lecturePanel.setSize(600, 500);
//		lecturePanel.setLocation(290, 130);
		//connectPanel.setBackground(Color.white);
		
		//add(lecturePanel);
		add(sPanel);
		
		setTitle("Select your lecture");
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

class CreateBtn implements ActionListener{
	LecturePanel sp;
	JScrollPane sPanel;
	public CreateBtn(LecturePanel frame, JScrollPane sPanel){
		this.sp = frame;
		this.sPanel = sPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new InputLectureFrame(sp, sPanel);
	}
	
}

class InputLectureFrame extends JFrame implements ActionListener{
	JTextField lecturetf;
	LecturePanel frame;
	JScrollPane sPanel;
	public InputLectureFrame(LecturePanel fr, JScrollPane sPanel) {
		frame = fr;
		this.sPanel = sPanel;
		setSize(400, 100);
		setLayout(null);
		setTitle("Input your lecture title");
		setVisible(true);
		setLocation(350, 200);
		
		getContentPane().setBackground(new Color(141, 198, 63));
		
		lecturetf = new JTextField();
		JLabel inputLb = new JLabel("Title :");
		inputLb.setSize(50, 30);
		inputLb.setLocation(10, 10);
		
		lecturetf.setSize(150, 30);
		lecturetf.setLocation(60, 10);
		JButton input = new JButton("Create lecture");
		input.addActionListener(this);
		input.setSize(130, 30);
		input.setLocation(230, 10);
		add(lecturetf);
		add(inputLb);
		add(input);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String lectureTitle = lecturetf.getText();
		lectureTitle = lectureTitle.trim();
		JDialog dlg;
		
		if(lectureTitle.equals("")){
			dlg = new JDialog(this, "Error", true);
			JLabel errLb = new JLabel("Input your lecture!");
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
			UTS.lecturePost(lectureTitle, "minsuk@hansung.ac.kr", "false");
			
//			//UTS.doFileUpload("C:\\test\\양식있음 수학의 정석\\지수.jpg","http://localhost:8080/ImageUploadTest/file.jsp");
//			//UTS.doFileUpload(mmFilePath + ".mm","http://localhost:8080/ImageUploadTest/file.jsp");

			
			this.setVisible(false);
			frame.init();
			
//			frame.validate();
//			frame.repaint();
//			frame.invalidate();
			frame.update(frame.getGraphics());
			sPanel.updateUI();
//			frame.print(getGraphics());
//			frame.printAll(getGraphics());
//			frame.printComponents(getGraphics());
//			frame.validate();
//			frame.repaint();
//			frame.invalidate();
			//frame.
			//frame.
		}
			
	}
}

class LecturePanel extends JPanel implements ActionListener{
	JLabel tmpLb;
	Image onBookMark, offBookMark;
	Image onState, offState;
	//URL onBookMarkURL = getClass().getClassLoader().getResource("onBookMark.png");
	JFrame frame;
	MindMapController mc;
	final int TOPPADDING = 100;
	final int LECTUREHGAP = 60;
	int lectureCnt = 0;
	public int getLectureCnt() {
		return lectureCnt;
	}
	String[] latestDay = {"2013.4.11", "2013.5.21", "2013.1.22", "2012.12.23", "2012.1.3", "2013.4.23", "2013.3.3", "2012.11.23", "2012.10.1"
			, "2012.5.23", "2013.5.23", "2012.2.23", "2012.1.28", "2012.6.23", "2012.7.23", "2011.7.23", "2012.8.15", "2011.7.2"}; // 18개
	String[] registered = {"21","30","31","40","41","15","22","26","33","19",
			               "24","22","41","42","35","37","41","22"}; 
	
	Font lagf;
	Font midF = new Font("Serif", Font.BOLD, 20);
	HashMap<String, String> lectureMap;
	public LecturePanel(JFrame frame, MindMapController mc) {
		this.mc = mc;
		this.frame = frame;
		setSize(450, 500);
		setLayout(null);
		
		lagf = new Font("Serif", Font.BOLD, 25);
		tmpLb = new JLabel("Lecture Title");
		tmpLb.setSize(200, 50);
		tmpLb.setFont(lagf);
		tmpLb.setLocation(70, 20);
		add(tmpLb);
		
		tmpLb = new JLabel("Registered");
		tmpLb.setSize(200, 50);
		tmpLb.setFont(lagf);
		tmpLb.setLocation(290, 20);
		add(tmpLb);
		
		tmpLb = new JLabel("Latest day");
		tmpLb.setSize(250, 50);
		tmpLb.setFont(lagf);
		tmpLb.setLocation(440, 20);
		add(tmpLb);
		
		init();
		
	}
	public void init(){
		String sHtml = "";
		BufferedReader in = null;
		String buf = "";
		try
		{
		    URL url = new URL("http://61.43.139.10:8080/treeze/getMyLectures?professorEmail=" + "minsuk@hansung.ac.kr");
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
		ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
		Lecture tmpLecture;
		FreemindGson myGson = new FreemindGson();
		//lectureList = (ArrayLecture) myGson.fromJson(sHtml, "ArrayLecture");
		Gson gson = new Gson();
		
		Type type = new TypeToken<ArrayLecture>() {
		}.getType();
		ArrayLecture jonResultlecturelist = (ArrayLecture) gson
				.fromJson(sHtml, type);
		lectureList = jonResultlecturelist.getLectures();
		//lectureList.get(0).get
//		System.out.println("dd");
//		
//		JsonParser parser = new JsonParser();
//
//		JsonArray array = parser.parse(sHtml).getAsJsonArray();
//
//		for(JsonElement element : array)
//		{
//		  // ...
//		}
		
		
		Font midF = new Font("Serif", Font.BOLD, 30);
		JButton tmpBtn;
		lectureMap = new HashMap<String, String>();
		for(lectureCnt = 0; lectureCnt < lectureList.size(); lectureCnt++){
			tmpLecture = lectureList.get(lectureCnt);
			tmpBtn = new JButton(tmpLecture.getLectureName());
			lectureMap.put(tmpLecture.getLectureName(), tmpLecture.getLectureId() + "");
			tmpBtn.addActionListener(this);
			tmpBtn.setFont(lagf);
			tmpBtn.setSize(220, 50);
			tmpBtn.setLocation(25, TOPPADDING + LECTUREHGAP * lectureCnt);
			add(tmpBtn);
			
			tmpLb = new JLabel(registered[lectureCnt % 18]);
			tmpLb.setSize(100, 50);
			tmpLb.setFont(midF);
			tmpLb.setLocation(325, TOPPADDING + LECTUREHGAP * lectureCnt);
			add(tmpLb);
			
			tmpLb = new JLabel(latestDay[lectureCnt % 18]);
			tmpLb.setSize(140, 50);
			tmpLb.setFont(midF);
			tmpLb.setLocation(440, TOPPADDING + LECTUREHGAP * lectureCnt);
			add(tmpLb);
		}
		setPreferredSize(new Dimension(550, 20 + TOPPADDING + LECTUREHGAP * lectureCnt));
	}
		
//		JButton logic = new JButton("Logic Circuit");
//		logic.addActionListener(this);
//		logic.setFont(lagf);
//		logic.setSize(220, 50);
//		logic.setLocation(25, TOPPADDING + LECTUREHGAP * lectureCnt);
//		lectureCnt++;
//		
//		JButton system = new JButton("System Programming");
//		system.addActionListener(this);
//		system.setFont(lagf);
//		system.setSize(220, 50);
//		system.setLocation(25, TOPPADDING + LECTUREHGAP * lectureCnt);
//		
//		lectureCnt = 0;
//		
//		lagf = new Font("Serif", Font.BOLD, 30);
//		tmpLb = new JLabel(registered[0]);
//		tmpLb.setSize(100, 50);
//		tmpLb.setFont(lagf);
//		tmpLb.setLocation(325, TOPPADDING + LECTUREHGAP * lectureCnt);
//		add(tmpLb);
//		lectureCnt++;
//		
//		tmpLb = new JLabel(registered[1]);
//		tmpLb.setSize(100, 50);
//		tmpLb.setFont(lagf);
//		tmpLb.setLocation(325, TOPPADDING + LECTUREHGAP * lectureCnt);
//		add(tmpLb);
//		lectureCnt++;
//		
//		tmpLb = new JLabel(registered[2]);
//		tmpLb.setSize(100, 50);
//		tmpLb.setFont(lagf);
//		tmpLb.setLocation(325, TOPPADDING + LECTUREHGAP * lectureCnt);
//		add(tmpLb);
//		
//		lectureCnt = 0;
//		
//		tmpLb = new JLabel(latestDay[0]);
//		tmpLb.setSize(140, 50);
//		tmpLb.setFont(lagf);
//		tmpLb.setLocation(440, TOPPADDING + LECTUREHGAP * lectureCnt);
//		add(tmpLb);
//		lectureCnt++;
//		
//		tmpLb = new JLabel(latestDay[1]);
//		tmpLb.setSize(160, 50);
//		tmpLb.setFont(lagf);
//		tmpLb.setLocation(440,  TOPPADDING + LECTUREHGAP * lectureCnt);
//		add(tmpLb);
//		lectureCnt++;
//		
//		tmpLb = new JLabel(latestDay[2]);
//		tmpLb.setSize(180, 50);
//		tmpLb.setFont(lagf);
//		//tmpLb.setLocation(440, 220);
//		tmpLb.setLocation(440,  TOPPADDING + LECTUREHGAP * lectureCnt);
//		add(tmpLb);
//		lectureCnt++;
		
		
		
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
		
//		add(embedded);
//		add(logic);
//		add(system);
		
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(20, 80, 560, 80);
		if(lectureCnt != 0){
			g.drawLine(270, 100, 270, TOPPADDING + LECTUREHGAP * lectureCnt);
			g.drawLine(420, 100, 420, TOPPADDING + LECTUREHGAP * lectureCnt);
		}
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
		System.out.println("classId = " + event);
		String lectureIdStr = lectureMap.get(event);
		frame.setVisible(false);
		new LecturePageFrame(mc, event, lectureIdStr);
	}
}


	
