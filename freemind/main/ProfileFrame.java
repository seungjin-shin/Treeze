package freemind.main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.jws.Oneway;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import freemind.modes.mindmapmode.MindMapController;

public class ProfileFrame extends JFrame {

	BtnPanel btnPanel;
	JPanel profilePanel;
	JPanel lectureListPanel;
	LogoPanel logoPanel;
	Image logoImg;
	JButton btn = new JButton("버튼");
	JButton btn1 = new JButton("버튼");
	JButton btn2 = new JButton("버튼");
	JButton btn3 = new JButton("버튼");
	JButton btn4 = new JButton("버튼");
	JButton btn5 = new JButton("버튼");
	JButton btn6 = new JButton("버튼");
	JButton btn7 = new JButton("버튼");
	JButton btn8 = new JButton("버튼");
	JButton btn9 = new JButton("버튼");
	JButton btn10 = new JButton("버튼");
	JButton downBtn = new JButton();
	NameLabel nameLabel = new NameLabel("이 민석");
	PersonalInfo personalInfo = new PersonalInfo("한성대학교 컴퓨터공학과", "대학생", "경기도 평택시");
	
	
	PicturePanel picturePanel = new PicturePanel(Toolkit.getDefaultToolkit().getImage("images/minsuk.jpg"));
	private static final Insets insets = new Insets(10, 10, 10, 10);
	MindMapController mc;
	
	public ProfileFrame(MindMapController mc) {
		// TODO Auto-generated constructor stub
		this.mc = mc;
		this.setSize(1000, 600);
		this.setLocation(300, 200);
		
		this.getContentPane().setBackground(new Color(141, 198, 63));
		//this.setResizable(false);
		logoImg = Toolkit.getDefaultToolkit().getImage("images/treezeLogo.png");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.BOTH;

		// C:\Users\컴공\Desktop
		setLayout(gbl);
		
		logoPanel = new LogoPanel();

		btnPanel = new BtnPanel();
		profilePanel = new JPanel();
		
		lectureListPanel = new JPanel();
		profilePanel.setLayout(gbl);
		logoPanel.setBackground(new Color(0, 0, 0, 0));
		
		btnPanel.setBackground(new Color(0, 0, 0, 0));
		profilePanel.setBackground(new Color(0,0,0,0));
		
		lectureListPanel.setBackground(Color.WHITE);
		
		// logoPanel.setLayout(new BorderLayout());
		
		//logoPanel.setBorder(new EmptyBorder(10,10,10,10));
		// logoPanel.add(j);
		
		btnPanel.setLayout(new GridLayout(1, 4, 30, 5));

		//btnPanel.setBorder(new EmptyBorder(0, 10000, 0, 10));

		lectureListPanel.setBorder(new LineBorder(Color.BLACK, 2, false));

		addGrid(gbl, gbc, logoPanel,        0, 0, 1, 1, 1,  3, this);
//		insets.bottom = 50;
//		insets.top = 50;
//		insets.right = 50;
//		insets.left = 50;
		addGrid(gbl, gbc, btnPanel,         1, 1, 1, 1, 5,  1, this);
		addGrid(gbl, gbc, profilePanel,     0, 2, 1, 1, 1, 15, this);
		addGrid(gbl, gbc, lectureListPanel, 1, 2, 1, 1, 5, 15, this); 
		
		addGrid(gbl, gbc, picturePanel,     0, 0, 1, 1, 1,  3, profilePanel);
		addGrid(gbl, gbc, nameLabel, 	    0, 1, 1, 1, 1,  0, profilePanel);
		addGrid(gbl, gbc, personalInfo,     0, 2, 1, 1, 1,  0, profilePanel);
		JPanel dumy =new JPanel();
		dumy.setBackground(new Color(0, 0, 0, 0));
		addGrid(gbl, gbc,dumy, 0, 3, 1, 1, 1, 3, profilePanel);

		this.setVisible(true);
	}

	private void addGrid(GridBagLayout gbl, GridBagConstraints gbc,
			Component c, int gridx, int gridy, int gridwidth, int gridheight,
			int weightx, int weighty, Container container) {
		gbc.gridx = gridx; // 전체 레이아웃 위치
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth; // 행 열 갯수 테이블에서 잡는거처럼
		gbc.gridheight = gridheight;
		gbc.weightx = weightx; // 비율
		gbc.weighty = weighty;

		gbc.insets = insets;

		gbl.setConstraints(c, gbc);

		if (c == picturePanel) {
			gbc.insets.left = -100;
			
		}
		else if(c==logoPanel){
			//gbc.insets.top` = -100;
		}
	
		container.add(c);
	}

	class LogoPanel extends JPanel {

		public LogoPanel() {
			// TODO Auto-generated constructor stub

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			

			g.drawImage(logoImg, 0, 0, this.getWidth(),
					this.getHeight(), null);
		}
	}

	class PicturePanel extends JPanel {
		Image img;
		public PicturePanel(Image img) {
			// TODO Auto-generated constructor stub
			this.img = img;
			this.setBorder(new EmptyBorder(20, 20, 20, 20));
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);

			g.drawImage(img, 0, 0, this.getWidth(),
					this.getHeight(), null);
		
		}
		
	}
	class NameLabel extends JLabel{
		public NameLabel(String name) {
			// TODO Auto-generated constructor stub
			this.setText(name);
			this.setFont(new Font("Serif", Font.BOLD, 15));
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setVerticalAlignment(SwingConstants.CENTER);
		}
		void setText(int size){
			this.setFont(new Font("Serif", Font.BOLD, size));
		}
	}
	class PersonalInfo extends JPanel{
		JLabel sc;
		JLabel job;
		JLabel address;
		public PersonalInfo(String school,String job,String address) {
			// TODO Auto-generated constructor stub
			sc = new JLabel(school);
			this.setLayout(new GridLayout(3, 1,3,3));
			this.setSize(this.getWidth(),this.getHeight());
			this.job = new JLabel(job);
			this.address = new JLabel(address);
			this.setBackground(new Color(0,0,0,0));
			this.setBorder(new EmptyBorder(10, 0, 10, 0));
		
			
//			this.add(sc);
//			this.add(this.job);
//			this.add(this.address);
			this.labelset(this.sc);
			this.labelset(this.job);
			this.labelset(this.address);
			
		}
		void labelset(JLabel label){
			label.setFont(new Font("Serif", Font.BOLD, 12));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setVerticalAlignment(SwingConstants.CENTER);
			this.add(label);
			
		}
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.WHITE);
			g2.drawLine(0, 0, this.getWidth(), 0);
			g2.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight());
		}
		
	}
	class BtnPanel extends JPanel {
		JButton profileBtn = new JButton();
		JButton lectureBtn = new JButton();

		JButton logoutBtn = new JButton();
		ImageIcon imgIcon, resizeIcon;
		Image img, scaledImage;
		BufferedImage imageBuff;

		public BtnPanel() {
			// TODO Auto-generated constructor stub
			imgIcon = new ImageIcon("C:\\Users\\Kunyoung\\Desktop\\menubar.png");
			img = imgIcon.getImage();
			// setBtnImg();
			add(profileBtn);
			add(lectureBtn);
			add(downBtn);
			add(logoutBtn);
			// setBtnImg(logoutBtn.getWidth(),logoutBtn.geth)

		}

		void setBtnImg(int whdth, int height) {
			// downBtn.setSize(this.getWidth()/10, this.getHeight()/2);
			scaledImage = img.getScaledInstance(whdth, height,
					Image.SCALE_SMOOTH);
			imageBuff = new BufferedImage(whdth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = imageBuff.createGraphics();
			g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
			g.dispose();
			resizeIcon = new ImageIcon(scaledImage);
			profileBtn.setIcon(resizeIcon);
			lectureBtn.setIcon(resizeIcon);
			downBtn.setIcon(resizeIcon);
			logoutBtn.setIcon(resizeIcon);

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);

		}
	}

}
