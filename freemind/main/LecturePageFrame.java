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
		btnListener = new BtnListener(mc);
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
		
		
		
		classPanel.setSize(600, 500);
		classPanel.setLocation(290, 130);
		//connectPanel.setBackground(Color.white);
		
		add(classPanel);
		
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
		new InputClassFrame(null);
	}
}

class InputClassFrame extends JFrame{
	ActionListener btnListener;
	MindMapController mc;
	public InputClassFrame(MindMapController mc) {
		this.mc = mc;
		
		btnListener = new BtnListener(this, mc);
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
		JTextField lecturetf = new JTextField();
		lecturetf.setSize(150, 25);
		lecturetf.setLocation(60, 10);
		JButton input = new JButton("Create class");
		input.addActionListener(btnListener);
		input.setSize(110, 25);
		input.setLocation(240, 10);
		add(inputLb);
		add(lecturetf);
		add(input);
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
	public ClassPanel(JFrame frame, MindMapController mc) {
		this.frame = frame;
		this.mc = mc;
		
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
		embedded.setLocation(40, 130);
		
		JLabel logic = new JLabel("Second Class");
		logic.setFont(lagf);
		logic.setSize(240, 50);
		logic.setLocation(40, 190);
		
		lagf = new Font("Serif", Font.BOLD, 20);
		
		JLabel tmpLb = new JLabel("2013.5.23");
		tmpLb.setFont(lagf);
		tmpLb.setSize(240, 50);
		tmpLb.setLocation(295, 130);
		add(tmpLb);
		
		tmpLb = new JLabel("2012.4.22");
		tmpLb.setFont(lagf);
		tmpLb.setSize(240, 50);
		tmpLb.setLocation(295, 190);
		add(tmpLb);
		
		slideShowURL = getClass().getClassLoader().getResource("slideShow.png");
		mindmapURL = getClass().getClassLoader().getResource("mindmap.png");
		
		JButton slideBtn = new JButton(new ImageIcon(slideShowURL));
		JButton mindmapBtn = new JButton(new ImageIcon(mindmapURL));
		
		slideBtn.setSize(100, 24);
		slideBtn.setLocation(472, 130);
		slideBtn.setFocusable(false);
		add(slideBtn);
		
		mindmapBtn.setSize(100, 24);
		mindmapBtn.setLocation(472, 155);
		mindmapBtn.setFocusable(false);
		mindmapBtn.addActionListener(this);
		add(mindmapBtn);
		
		slideBtn = new JButton(new ImageIcon(slideShowURL));
		mindmapBtn = new JButton(new ImageIcon(mindmapURL));
		
		slideBtn.setSize(100, 24);
		slideBtn.setLocation(472, 190);
		slideBtn.setFocusable(false);
		add(slideBtn);
		
		mindmapBtn.setSize(100, 24);
		mindmapBtn.setLocation(472, 215);
		mindmapBtn.setFocusable(false);
		mindmapBtn.addActionListener(this);
		add(mindmapBtn);
		
		ClassTopBarPanel classToppn = new ClassTopBarPanel(null);
		classToppn.setSize(580, 50);
		classToppn.setLocation(10, 10);
		add(classToppn);
		
//		JLabel prof = new JLabel("¿Ã πŒºÆ");
//		prof.setFont(lagf);
//		prof.setSize(200, 50);
//		prof.setLocation(360, 100);
//		add(prof);
//		
//		JLabel prof2 = new JLabel("¿Ã πŒºÆ");
//		prof2.setFont(lagf);
//		prof2.setSize(200, 50);
//		prof2.setLocation(360, 160);
//		add(prof2);
//		
//		JLabel prof3 = new JLabel("¿Ã πŒºÆ");
//		prof3.setFont(lagf);
//		prof3.setSize(200, 50);
//		prof3.setLocation(360, 220);
		//add(prof3);
		
		add(embedded);
		add(logic);
		//add(system);
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(20, 120, 580, 120);
		
		g.drawLine(220, 140, 220, 460);
		g.drawLine(450, 140, 450, 460);
		
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
		mc.open(mc);
		frame.setVisible(false);
	}
}

	
