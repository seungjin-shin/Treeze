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

import freemind.modes.mindmapmode.MindMapController;


public class LecturePageFrame extends JFrame {
	private Container ct;

	JPanel classPanel = new ClassPanel(this);
	private Image profileImg;
	private URL profileImgURL = getClass().getClassLoader().getResource("minsuk.jpg");
	private Image logo;
	URL logoURL = getClass().getClassLoader().getResource("treezeLogo.png");
	ActionListener btnListener;
	MindMapController mc;
	public LecturePageFrame(MindMapController mc) {
		this.mc = mc;
		profileImg = new ImageIcon(profileImgURL).getImage();
		logo = new ImageIcon(logoURL).getImage();
		btnListener = new BtnListener(mc);
		
		setSize(800, 800);
		setLayout(null);//#afd679
		
		getContentPane().setBackground(new Color(175, 230, 121, 255));

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
		
		JButton newLecture = new JButton("New Class");
		newLecture.setSize(150, 40);
		newLecture.setLocation(300, 85);
		newLecture.setFont(midF);
		newLecture.addActionListener(btnListener);
		
		add(newLecture);
		
		classPanel.setSize(450, 500);
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

class ClassPanel extends JPanel implements ActionListener{
	JLabel course, professor, state;
	Image onBookMark, offBookMark;
	Image onState, offState;
	//URL onBookMarkURL = getClass().getClassLoader().getResource("onBookMark.png");
	JFrame frame;
	public ClassPanel(JFrame frame) {
		this.frame = frame;
		setSize(450, 500);
		setLayout(null);
		
		Font lagf = new Font("Serif", Font.BOLD, 30);
		course = new JLabel("Class Title");
		course.setSize(200, 50);
		course.setFont(lagf);
		course.setLocation(150, 20);
		
		JButton embedded = new JButton("First Class");
		embedded.addActionListener(this);
		embedded.setFont(lagf);
		embedded.setSize(280, 50);
		embedded.setLocation(90, 100);
		
		JButton logic = new JButton("Second Class");
		logic.addActionListener(this);
		logic.setFont(lagf);
		logic.setSize(280, 50);
		logic.setLocation(90, 160);
		
		JButton system = new JButton("System Programming");
		system.addActionListener(this);
		system.setFont(lagf);
		system.setSize(320, 50);
		system.setLocation(90, 220);
		
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
	}
}

	
