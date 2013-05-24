package freemind.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapController;


public class LoggedInFrame extends JFrame {
	private Container ct;

	JPanel lecturePanel;
	private Image profileImg;
	private URL profileImgURL = getClass().getClassLoader().getResource("minsuk.jpg");
	private Image logo;
	URL logoURL = getClass().getClassLoader().getResource("treezeLogo.png");
	ActionListener btnListener = new BtnListener();
	MindMapController mc;
	public LoggedInFrame(MindMapController mc) {
		this.mc = mc;
		lecturePanel = new LecturePanel(this, mc);
		profileImg = new ImageIcon(profileImgURL).getImage();
		logo = new ImageIcon(logoURL).getImage();
		
		setSize(800, 800);
		setLayout(null);//#afd679
		
		getContentPane().setBackground(new Color(175, 230, 121, 255));
		
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
		
		JButton newLecture = new JButton("New Lecture");
		newLecture.setSize(150, 40);
		newLecture.setLocation(300, 85);
		newLecture.setFont(midF);
		newLecture.addActionListener(btnListener);
		
		add(newLecture);
		
		lecturePanel.setSize(450, 500);
		lecturePanel.setLocation(290, 130);
		//connectPanel.setBackground(Color.white);
		
		add(lecturePanel);
		
		setTitle("Select your lecture");
		setVisible(true);
		setLocation(200, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.drawImage(profileImg, 85, 150, 130, 170, null);
		g.drawImage(logo, 40, 50, 200, 50, null);
		g.drawLine(30, 110, 850, 110);
		
		g.setColor(Color.white);

		g.drawLine(40, 380, 270, 380);
		g.drawLine(40, 450, 270, 450);
		//g.drawImage(logo, 20, 30, null);
//		g.setColor(Color.white);
//		g.fillRoundRect(300, 150, 650, 500, 30, 30);
		g.setColor(Color.black);
		g.drawRect(298, 158, 450, 500);
		g.drawRect(297, 157, 452, 502);
		//g.drawRoundRect(299, 149, 652, 502, 30, 30);
//		g.drawRoundRect(299, 149, 653, 503, 30, 30);
//		g.drawRoundRect(298, 148, 654, 504, 30, 30);
		
	}
	
}
class BtnListener implements ActionListener{
	JFrame frame;
	MindMapController mc;
	public BtnListener(){}
	public BtnListener(MindMapController mc){
		this.mc = mc;
	}
	public BtnListener(JFrame f) {
		frame = f;
	}
	public BtnListener(JFrame f, MindMapController mc) {
		this.mc = mc;
		frame = f;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String btn = e.getActionCommand();
		if(btn.equals("New Lecture"))
			new InputLectureFrame();
		else if(btn.equals("Create lecture")){
			UploadToServer UTS = new UploadToServer();
//			//UTS.doFileUpload("C:\\test\\양식있음 수학의 정석\\지수.jpg","http://localhost:8080/ImageUploadTest/file.jsp");
//			//UTS.doFileUpload(mmFilePath + ".mm","http://localhost:8080/ImageUploadTest/file.jsp");
			frame.setVisible(false);
		}
		else if(btn.equals("New Class")){
			new InputClassFrame(mc);
		}
		else if(btn.equals("Create class")){
			frame.setVisible(false);
		}
		else if(btn.equals("select PDF")){
			mc.open(null);
		}
	}
}
class InputLectureFrame extends JFrame{
	ActionListener btnListener = new BtnListener(this);
	
	public InputLectureFrame() {
		setSize(400, 100);
		setLayout(null);
		setTitle("Input your lecture title");
		setVisible(true);
		setLocation(350, 200);
		
		getContentPane().setBackground(new Color(175, 230, 121, 255));
		
		JTextField lecturetf = new JTextField();
		JLabel inputLb = new JLabel("Title :");
		inputLb.setSize(30, 30);
		inputLb.setLocation(10, 10);
		
		lecturetf.setSize(150, 30);
		lecturetf.setLocation(40, 10);
		JButton input = new JButton("Create lecture");
		input.addActionListener(btnListener);
		input.setSize(130, 30);
		input.setLocation(230, 10);
		add(lecturetf);
		add(inputLb);
		add(input);
	}
}

class InputClassFrame extends JFrame{
	ActionListener btnListener;
	MindMapController mc;
	public InputClassFrame(MindMapController mc) {
		this.mc = mc;
		
		btnListener = new BtnListener(this, mc);
		setSize(380, 120);
		setLayout(null);
		setTitle("Input your class title");
		setVisible(true);
		setLocation(350, 200);
		
		getContentPane().setBackground(new Color(175, 230, 121, 255));
		JLabel inputLb = new JLabel("Title :");
		inputLb.setSize(30, 30);
		inputLb.setLocation(10, 10);
		JLabel inputFileLb = new JLabel("File  :");
		inputFileLb.setSize(100, 30);
		inputFileLb.setLocation(10, 40);
		add(inputFileLb);
		
		JButton fileBtn = new JButton("select PDF");
		fileBtn.addActionListener(btnListener);
		fileBtn.setSize(100, 30);
		fileBtn.setLocation(40, 40);
		add(fileBtn);
		JTextField lecturetf = new JTextField();
		lecturetf.setSize(150, 25);
		lecturetf.setLocation(40, 10);
		JButton input = new JButton("Create class");
		input.addActionListener(btnListener);
		input.setSize(130, 25);
		input.setLocation(210, 10);
		add(inputLb);
		add(lecturetf);
		add(input);
	}
}

class LecturePanel extends JPanel implements ActionListener{
	JLabel course, professor, state;
	Image onBookMark, offBookMark;
	Image onState, offState;
	//URL onBookMarkURL = getClass().getClassLoader().getResource("onBookMark.png");
	JFrame frame;
	MindMapController mc;
	public LecturePanel(JFrame frame, MindMapController mc) {
		this.mc = mc;
		this.frame = frame;
		setSize(450, 500);
		setLayout(null);
		
		Font lagf = new Font("Serif", Font.BOLD, 30);
		course = new JLabel("Lecture Title");
		course.setSize(200, 50);
		course.setFont(lagf);
		course.setLocation(150, 20);
		
		JButton embedded = new JButton("Embedded System");
		embedded.addActionListener(this);
		embedded.setFont(lagf);
		embedded.setSize(320, 50);
		embedded.setLocation(90, 100);
		
		JButton logic = new JButton("Logic Circuit");
		logic.addActionListener(this);
		logic.setFont(lagf);
		logic.setSize(320, 50);
		logic.setLocation(90, 160);
		
		JButton system = new JButton("System Programming");
		system.addActionListener(this);
		system.setFont(lagf);
		system.setSize(320, 50);
		system.setLocation(90, 220);
		
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
		
		add(embedded);
		add(logic);
		add(system);
		
		add(course);
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(20, 80, 430, 80);
		
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
		frame.setVisible(false);
		new LecturePageFrame(mc);
	}
}


	
