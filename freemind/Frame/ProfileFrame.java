package freemind.Frame;

import java.awt.BasicStroke;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Handler;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import freemind.controller.AddAllTicketThread;
import freemind.controller.FreemindManager;
import freemind.controller.ImgBtn;
import freemind.controller.ProgressThread;
import freemind.controller.SetResizeImgThread;
import freemind.json.ArrayClass;
import freemind.json.ArrayLecture;
import freemind.json.ClassInfo;
import freemind.json.Lecture;
import freemind.json.Mindmap;
import freemind.json.TreezeData;
import freemind.json.User;
import freemind.main.DownLoadNetworkThread;
import freemind.modes.NodeAdapter;
import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapController;


public class ProfileFrame extends JFrame {
	final String SERVERIP = FreemindManager.getInstance().getSERVERIP();
	final int PORT = FreemindManager.getInstance().getPORT();
	String userEmail;
	String userName;
	final String DOWNPATH = FreemindManager.getInstance().getDownPath();
	
	JButton createLtBtn;
	JButton logoutBtn;
	FreemindManager fManager;
	
	BtnPanel btnPanel;
	JPanel profilePanel;
	JPanel lectureListPanel;
	LogoPanel logoPanel;
	Image imgIcon;
//	ArrayList<Data.Class> classList = new ArrayList<Data.Class>();	
	NameLabel nameLabel;
	PersonalInfo personalInfo = new PersonalInfo("Hansung Univ", "Professor", "Computer engineering");
//	ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
	ListPanel listPanel;
	PicturePanel picturePanel = new PicturePanel();
	private static final Insets insets = new Insets(10, 10, 10, 10);
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	LectureHead lectureHead;
//	Lecture lecture;
	JPanel grid = new JPanel();
	ArrayList<Lecture> lectureList;
	ArrayList<ClassInfo> classList;

	
	final int NETWORK_FLAG_GET_LECTURELIST = 0;
	final int NETWORK_FLAG_GET_CLASSLIST = 1;
	final int NETWORK_FLAG_GET_MINDMAP = 2;
	int networkFlag;
	Handler networkHandler;
	String professorEmail;
	long lectureId;
	long classId;
	StringBuffer sbResult = new StringBuffer();
	Gson gson = new Gson();
	MindMapController mc;
	NetworkThread networkThread;
	ArrayList<JCheckBox> chkBoxList = new ArrayList<JCheckBox>();
	
	public ProfileFrame(MindMapController mc) {
		this.mc = mc;
		fManager = FreemindManager.getInstance();
		setIconImage(fManager.topIcon);
		userEmail = fManager.getUser().getUserEmail();
		userName = fManager.getUser().getUserName();
		nameLabel = new NameLabel(userName);
//		fManager.setFilePath(DOWNPATH + "/");
		// TODO Auto-generated constructor stub
		this.setSize(1000, 600);
		this.setLocation(400, 100);
		this.setTitle("Lecture page");
		this.getContentPane().setBackground(new Color(141, 198, 63));
		//this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		listPanel = new ListPanel();
//		lecture = new Lecture();
		gbc.fill = GridBagConstraints.BOTH;

		// C:\Users\��째첩\Desktop
		setLayout(gbl);
		
		logoPanel = new LogoPanel();

		lectureListPanel = new JPanel();
		
		profilePanel = new JPanel();
		btnPanel = new BtnPanel(this);
		
		profilePanel.setLayout(gbl);
		logoPanel.setBackground(new Color(0, 0, 0, 0));
		
		btnPanel.setBackground(fManager.treezeColor);
		profilePanel.setBackground(new Color(0,0,0,0));
		
		lectureListPanel.setBackground(Color.WHITE);
		
		
		lectureListPanel.setBorder(new LineBorder(Color.BLACK, 2, false));

		addGrid(gbl, gbc, logoPanel,        0, 0, 1, 1, 1, 3, this);
		addGrid(gbl, gbc, btnPanel,         1, 1, 1, 1, 6, 2, this);
		addGrid(gbl, gbc, profilePanel,     0, 2, 1, 1, 1, 15, this);
		addGrid(gbl, gbc, lectureListPanel, 1, 2, 1, 1, 6, 15, this);
		
		
		
		addGrid(gbl, gbc, picturePanel, 0, 0, 1, 1, 1, 3, profilePanel);
		addGrid(gbl, gbc, nameLabel, 	0, 1, 1, 1, 1, 0, profilePanel);
		addGrid(gbl, gbc, personalInfo, 0, 2, 1, 1, 1, 0, profilePanel);
	
		lectureHead = new LectureHead();
		lectureHead.setBackground(new Color(0,0,0,0));
		insets.left = 10;
		insets.top = 0;
		insets.bottom = 0;
		lectureHead.setLayout(gbl);
		
		setLectureHead();
		
		lectureListPanel.setLayout(gbl);
		
		// lecture List ��쨀� 
		addGrid(gbl, gbc, lectureHead, 1, 1, 1, 1, 1, 1, lectureListPanel);
		addGrid(gbl, gbc, listPanel, 1, 2, 1, 1, 1, 20, lectureListPanel);
		
		grid.setBackground(new Color(0,0,0,0));
		grid.setLayout(new GridLayout(100, 1));
		//grid.add(item1);
		
		JPanel dumy =new JPanel();
		dumy.setBackground(new Color(0, 0, 0, 0));
		addGrid(gbl, gbc,dumy, 0, 3, 1, 1, 1, 3, profilePanel);
		listPanel.getViewport().add(grid, null);
		startNetwortThread();
		setResizable(false);
		this.setVisible(true);
	}
	
	public void showTextDialogue(String msg, String msg2){
		new TextDialogue(this, msg, msg2, true);
	}
	
	public void setLectureHead(){
		lectureHead.removeAll();
		
		JLabel noPanel = new JLabel("N o", JLabel.CENTER);
		JLabel subjectPanel = new JLabel("Subject", JLabel.CENTER);
		JLabel whritePane = new JLabel("Delete",JLabel.CENTER);
		addGrid(gbl, gbc, noPanel,      0, 0, 1, 1, 1, 1, lectureHead);
		addGrid(gbl, gbc, new JLabel(), 1, 0, 1, 1, 1, 1, lectureHead);
		addGrid(gbl, gbc, new JLabel(), 2, 0, 1, 1, 1, 1, lectureHead);
		addGrid(gbl, gbc, subjectPanel, 3, 0, 1, 1, 13, 1, lectureHead);
		addGrid(gbl, gbc, new JLabel(), 4, 0, 1, 1, 1, 1, lectureHead);
		addGrid(gbl, gbc, whritePane,   5, 0, 1, 1, 3, 1, lectureHead);
		
		lectureHead.updateUI();
		lectureListPanel.repaint();
//		mainFrameRepaint();
	}
	
	public void setClassHead(){
		lectureHead.removeAll();
		
		JLabel noPanel = new JLabel("Title", JLabel.CENTER);
		JLabel subjectPanel = new JLabel("Mode", JLabel.CENTER);
		JLabel whritePane = new JLabel("Delete",JLabel.CENTER);
		addGrid(gbl, gbc, noPanel,      0, 0, 1, 1, 24, 1, lectureHead);
		addGrid(gbl, gbc, new JLabel(), 1, 0, 1, 1, 1, 1, lectureHead);
		addGrid(gbl, gbc, subjectPanel, 2, 0, 1, 1, 3, 1, lectureHead);
		addGrid(gbl, gbc, whritePane,   3, 0, 1, 1, 5, 1, lectureHead);
		
//		lectureHead.repaint();
//		lectureHead.invalidate();
		lectureHead.updateUI();
		
		lectureListPanel.repaint();
//		repaint();
//		mainFrameRepaint();
	}

	private void addGrid(GridBagLayout gbl, GridBagConstraints gbc,
			Component c, int gridx, int gridy, int gridwidth, int gridheight,
			int weightx, int weighty, Container container) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;

		gbc.insets = insets;

		gbl.setConstraints(c, gbc);

		if (c == picturePanel) {
			gbc.insets.left = -100;
			
		}
			
		container.add(c);
	}

	class LogoPanel extends JPanel {
		ImageIcon imgIcon;
		
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			if(imgIcon == null)
				imgIcon = fManager.makeResizedImageIcon(this.getWidth(), this.getHeight(), fManager.treezeLogo);
			g.drawImage(imgIcon.getImage(), 0, 0, null);
		}
	}

	class PicturePanel extends JPanel {
		ImageIcon imgIcon;
		public PicturePanel() {
			// TODO Auto-generated constructor stub
			this.setBorder(new EmptyBorder(20, 20, 20, 20));
			this.setBackground(FreemindManager.getInstance().treezeColor);
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			if(imgIcon == null)
				imgIcon = fManager.makeResizedImageIcon(this.getWidth(), this.getHeight(), fManager.defaultProfile);
			g.drawImage(imgIcon.getImage(), 0, 0, null);
		
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
		
		JFrame parentFrame;
		AddLectureBtn addLtBtn;
		DeleteLectureBtn delLtBtn;
		ProfileBtn profileBtn;
		LectureListBtn ltListBtn;
		AddClassBtn addCsBtn;
		DeleteClassBtn delCsBtn;
		JButton nullBtn;
		
		public BtnPanel(JFrame f) {
			parentFrame = f;
			
//			loginBtn.setBackground(new Color(0, 0, 0, 0));
//			loginBtn.setBorderPainted(false);
//			loginBtn.setContentAreaFilled(false);
//			loginBtn.setFocusable(false);
			
			this.setLayout(gbl);
			
			this.setBackground(fManager.treezeColor);
			
			addLtBtn = new AddLectureBtn(fManager.addLectureDefault, fManager.addLecturePress, fManager.addLectureOver);
			addLtBtn.setBackground(fManager.treezeColor);
			addLtBtn.setBorderPainted(false);
			addLtBtn.setContentAreaFilled(false);
			addLtBtn.setFocusable(false);
			delLtBtn = new DeleteLectureBtn(fManager.deleteLectureDefault, fManager.deleteLecturePress, fManager.deleteLectureOver);
			delLtBtn.setBackground(fManager.treezeColor);
			delLtBtn.setBorderPainted(false);
			delLtBtn.setContentAreaFilled(false);
			delLtBtn.setFocusable(false);
			ltListBtn = new LectureListBtn(fManager.lectureListDefault, fManager.lectureListPress, fManager.lectureListOver);
			ltListBtn.setBackground(fManager.treezeColor);
			ltListBtn.setBorderPainted(false);
			ltListBtn.setContentAreaFilled(false);
			ltListBtn.setFocusable(false);
			profileBtn = new ProfileBtn(fManager.profileDefault, fManager.profilePress, fManager.profileOver);
			profileBtn.setBackground(fManager.treezeColor);
			profileBtn.setBorderPainted(false);
			profileBtn.setContentAreaFilled(false);
			profileBtn.setFocusable(false);
			addCsBtn = new AddClassBtn(fManager.addClassDefault, fManager.addClassPress, fManager.addClassOver);
			addCsBtn.setBackground(fManager.treezeColor);
			addCsBtn.setBorderPainted(false);
			addCsBtn.setContentAreaFilled(false);
			addCsBtn.setFocusable(false);
			delCsBtn = new DeleteClassBtn(fManager.deleteClassDefault, fManager.deleteClassPress, fManager.deleteClassOver);
			delCsBtn.setBackground(fManager.treezeColor);
			delCsBtn.setBorderPainted(false);
			delCsBtn.setContentAreaFilled(false);
			delCsBtn.setFocusable(false);
			
			nullBtn = new JButton();
			nullBtn.setBackground(fManager.treezeColor);
			nullBtn.setBorderPainted(false);
			nullBtn.setContentAreaFilled(false);
			nullBtn.setFocusable(false);
			
			setLecturePage();
		}

		void setLecturePage(){
			removeAll();
			
//			add(addLtBtn);
//			add(delLtBtn);
//			add(nullBtn);
//			add(profileBtn);
			addGrid(gbl, gbc, addLtBtn,   0, 0, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, delLtBtn,   1, 0, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, nullBtn,    2, 0, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, profileBtn, 3, 0, 1, 1, 1, 1, this);
			
			parentFrame.repaint();
		}
		
		void setClassPage(){
			removeAll();
//			add(ltListBtn);
//			add(addCsBtn);
//			add(delCsBtn);
//			add(profileBtn);
			addGrid(gbl, gbc, ltListBtn,   0, 0, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, addCsBtn,    1, 0, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, delCsBtn,    2, 0, 1, 1, 1, 1, this);
			insets.right = 5;
			addGrid(gbl, gbc, profileBtn,  3, 0, 1, 1, 1, 1, this);
			parentFrame.repaint();
			
		}

	}
	
	
	class AddLectureBtn extends ImgBtn{

		public AddLectureBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			setCurFrame();
			new InputLectureFrame();
		}
	}

	class DeleteLectureBtn extends ImgBtn{

		public DeleteLectureBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			setCurFrame();
			int deleteCnt = 0;
			for(int i = 0; i < chkBoxList.size(); i++){
				JCheckBox tmp = chkBoxList.get(i);
				if(tmp.isSelected()){
					deleteCnt++;
					Lecture lecture = lectureList.get(i);
					fManager.getUploadToServer().deleteLecturePost(lecture);
				}
			}
			
			new TextDialogue(fManager.getProfileFrame(), "Delete lecture, Total : " + deleteCnt, true);
			startNetwortThread();
		}
	}
	
	public void setCurFrame(){
		fManager.getUploadToServer().setCurFrame(this);
	}
	
	class LectureListBtn extends ImgBtn{

		public LectureListBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			setCurFrame();
			networkFlag = NETWORK_FLAG_GET_LECTURELIST;
			startNetwortThread();
			btnPanel.setLecturePage();
			setLectureHead();
//			lectureHead.setVisible(true);			
		}
	}
	
	class AddClassBtn extends ImgBtn{

		public AddClassBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			setCurFrame();
			new InputClassFrame();
		}
	}
	
	class DeleteClassBtn extends ImgBtn{

		public DeleteClassBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			setCurFrame();
			int deleteCnt = 0;
			for(int i = 0; i < chkBoxList.size(); i++){
				JCheckBox tmp = chkBoxList.get(i);
				if(tmp.isSelected()){
					deleteCnt++;
					ClassInfo classInfo = classList.get(i);
//					System.out.println(tmp.getActionCommand());
					fManager.getUploadToServer().deleteClassPost(classInfo);
				}
			}
			
			new TextDialogue(fManager.getProfileFrame(), "Delete class, Total : " + deleteCnt, true);
			startNetwortThread();
		}
	}
	
	class ProfileBtn extends ImgBtn{

		public ProfileBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			// TODO Auto-generated method stub
			
		}
	}
	
	class InputLectureFrame extends JFrame implements ActionListener{
		JTextField lecturetf;
		JPanel panel;
		JScrollPane sPanel;
		public InputLectureFrame() {
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
				fManager.getUploadToServer().lecturePost(lectureTitle);
				
				this.setVisible(false);

				startNetwortThread();
				
			}
		}
	}
	
	class LectureHead extends JPanel {
		

		private void LectureHead() {
			// TODO Auto-generated method stub
	
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(0, this.getHeight() - 1, this.getWidth(),
					this.getHeight() - 1);
		}
	}
	
	class LectureListItem extends JPanel {
		JLabel lectureNo;
		JLabel lectureTitle;
		JCheckBox delChkBox;
		JScrollPane jsp;
		
		public LectureListItem(int no, final Lecture lecture) {
			// TODO Auto-generated constructor stub
			lectureNo = new JLabel(no + "", JLabel.CENTER);
			lectureNo.setBackground(Color.black);
			lectureTitle = new JLabel(lecture.getLectureName(), JLabel.CENTER);
			lectureTitle.setBackground(Color.blue);
			
			delChkBox = new JCheckBox();
			delChkBox.setBackground(Color.white);
			chkBoxList.add(delChkBox);
			
			jsp = new JScrollPane(lectureTitle);
			jsp.setBorder(null);
			
			this.setBackground(Color.white);
			// this.add(noPanel);
			this.setLayout(gbl);
//			this.setLayout(new GridLayout(1, 2));
			insets.bottom = 5;
			insets.top = 5;
			addGrid(gbl, gbc, lectureNo, 0, 0, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, jsp,       1, 0, 1, 1, 15, 1, this);
			addGrid(gbl, gbc, delChkBox, 2, 0, 1, 1, 1, 1, this);

			lectureTitle.setPreferredSize(new Dimension(lectureTitle
					.getWidth(), lectureTitle.getHeight()));

			jsp.getViewport().setBackground(Color.WHITE);
			jsp.addMouseWheelListener(new MouseWheelListener() {
				
				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					listPanel.getVerticalScrollBar().setValue(
							listPanel.getVerticalScrollBar().getValue()
									+ e.getWheelRotation());
				}
			});
			jsp.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					setBackground(new Color(255, 255, 255, 255));
				}

				public void mousePreswsed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					setBackground(new Color(10, 10, 100, 100));
					lectureId  = lecture.getLectureId();
					fManager.setLecture(lecture);
					networkFlag = NETWORK_FLAG_GET_CLASSLIST;
//				lectureHead.setVisible(false);
					startNetwortThread();
					
				btnPanel.setClassPage();
				setClassHead();
				
				//grid.removeAll();
				//invalidate();
					//System.out.println(ticket.getContents());
				}
			});
			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					setBackground(new Color(255, 255, 255, 255));
				}

				public void mousePreswsed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					setBackground(new Color(10, 10, 100, 100));
					lectureId  = lecture.getLectureId();
					fManager.setLecture(lecture);
					networkFlag = NETWORK_FLAG_GET_CLASSLIST;
//				lectureHead.setVisible(false);

					startNetwortThread();
				btnPanel.setClassPage();
				
				//grid.removeAll();
				//invalidate();
					//System.out.println(ticket.getContents());
				}
			});

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawLine(0, this.getHeight() - 1, this.getWidth(),
					this.getHeight() - 1);
		}
	}
	
	class InputClassFrame extends JFrame implements ActionListener{
		JTextField classtf;
		
		public InputClassFrame() {
			
			setSize(380, 100);
			setLayout(null);
			setTitle("Input your class title");
			setVisible(true);
			setLocation(400, 100);
			
			getContentPane().setBackground(new Color(141, 198, 63));
			JLabel inputLb = new JLabel("Title :");
			inputLb.setSize(50, 30);
			inputLb.setLocation(10, 10);

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
				fManager.getUploadToServer().classPost(lectureId + "", classTitle);

				this.setVisible(false);
				
				startNetwortThread();
			}
		}
	}
	
	class RegistBtn extends ImgBtn{
		int classId;
		
		public void setClassId(int classId) {
			this.classId = classId;
		}

		public RegistBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			if(!fManager.isReadyFreemind()){
				showTextDialogue("Loading Freemind.", "Please waiting.");
				return;
			}
			fManager.setClassId(classId);
			mc.open();
		}
		
		
	}
	
	class StartLectureBtn extends ImgBtn{
		ClassInfo classInfo;
		public void setClassInfo(ClassInfo classInfo) {
			this.classInfo = classInfo;
		}

		public StartLectureBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			if(!fManager.isReadyFreemind()){
				showTextDialogue("Loading Freemind.", "Please waiting.");
				return;
			}
			
			fManager.setClassId((int) classInfo.getClassId());
			
			fManager.getUploadToServer().setStateOfLecture(fManager.getLecture(), true);
			
			DownLoadNetworkThread downLoadNetworkThread = new DownLoadNetworkThread(classInfo.getClassId());
			downLoadNetworkThread.start();
			
			setMainFramevisible(false);
			FreemindManager.getInstance().getFreemindMainFrame().setVisible(true);
			
			MakeXMLFileThread makeXMLThread = new MakeXMLFileThread(classInfo);
			makeXMLThread.start();
		}
		
	}
	
	class ClassListItem extends JPanel {
		JLabel classNm;
		RegistBtn regBtn;
		StartLectureBtn startBtn;
		JCheckBox delChkBox;
		JPanel btnPanel = new JPanel();
		JButton tmp, tmp2;
		public ClassListItem(final ClassInfo classInfo) {
			// TODO Auto-generated constructor stub
			this.setLayout(gbl);
			setBackground(Color.white);
			classNm = new JLabel(classInfo.getClassName(), JLabel.CENTER);
			
			delChkBox = new JCheckBox();
			delChkBox.setBackground(Color.white);
			chkBoxList.add(delChkBox);
			
			btnPanel.setBackground(fManager.noColor);
			btnPanel.setLayout(new GridLayout(2, 1, 0, 5));
			
			boolean chkClassEmpty = fManager.getUploadToServer().checkClassIsEmpty(classInfo.getClassId());

			if(chkClassEmpty){
				regBtn = new RegistBtn(fManager.regFalse, fManager.regFalse, fManager.regFalse);
				regBtn.setEnabled(false);
				startBtn = new StartLectureBtn(fManager.startDefault, fManager.startPress, fManager.startOver);
				startBtn.setClassInfo(classInfo);
			}
			else{
				startBtn = new StartLectureBtn(fManager.startFalse, fManager.startFalse, fManager.startFalse);
				startBtn.setEnabled(false);
				regBtn = new RegistBtn(fManager.regDefault, fManager.regPress, fManager.regOver);
				regBtn.setClassId(classInfo.getClassId());
			}
			
			regBtn.setBorderPainted(false);
			regBtn.setContentAreaFilled(false);
			regBtn.setFocusable(false);
			startBtn.setBorderPainted(false);
			startBtn.setContentAreaFilled(false);
			startBtn.setFocusable(false);
			
			btnPanel.add(regBtn);
			btnPanel.add(startBtn);
			
			insets.top = 30;
			insets.bottom = 30;
			addGrid(gbl, gbc, classNm,      0, 1, 1, 1, 8, 1, this);
			insets.top = 10;
			insets.bottom = 10;
			addGrid(gbl, gbc, btnPanel,     1, 0, 1, 3, 2, 1, this);
			insets.top = 10;
			insets.bottom = 10;
			addGrid(gbl, gbc, delChkBox,    2, 0, 1, 3, 1, 1, this);
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawLine(0, this.getHeight() - 1, this.getWidth(),
					this.getHeight() - 1);
		}
	}
	
	class ListPanel extends JScrollPane {

		public ListPanel() {
			// TODO Auto-generated constructor stub

			// this.add(noPanel);
			// this.setPreferredSize(new Dimension(0, 2000));
			this.setBackground(Color.WHITE);
			this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			getVerticalScrollBar().getModel().addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					repaint();	
				}
			});
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			// g2.drawLine(0, -5,this.getWidth(), -5);
			g2.drawLine(0, this.getHeight() - 1, this.getWidth(),
					this.getHeight() - 1);
		}
	}
	class NetworkThread extends Thread {

		//HttpResponse response;
		InputStream is;
		URL url = null;
		//Message msg = new Message();

		@Override
		public void run() {
			
			HttpURLConnection connection;
			sbResult.delete(0, sbResult.capacity());
			try {
				if (networkFlag == NETWORK_FLAG_GET_LECTURELIST) {
					url = new URL(
							"http://" + SERVERIP + ":8080/treeze/getMyLectures?professorEmail=" + userEmail);
					
				} else {
					url = new URL(
							"http://" + SERVERIP + ":8080/treeze//getClasses?lectureId="
									+ lectureId);
					
				}
				connection = (HttpURLConnection) url.openConnection();

				if (connection != null) {
					connection.setConnectTimeout(5000); // Set Timeout
					connection.setUseCaches(false);
					
					if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
						BufferedReader br = new BufferedReader(
								new InputStreamReader(
										connection.getInputStream(), "UTF-8"));

						String strLine = null;

						while ((strLine = br.readLine()) != null) {
							sbResult.append(strLine + "\n");
						}

						br.close();
					}

					connection.disconnect();
					System.out.println(sbResult.toString());
					chkBoxList.clear();
					
					if(networkFlag == NETWORK_FLAG_GET_MINDMAP){
//						new MindMap(sbResult.toString());
						//System.out.println(sbResult.toString());
					}
					else if(networkFlag ==NETWORK_FLAG_GET_LECTURELIST)
						updateGetallLectureList();
					else{
						updateGetallClassList();
					}
					
					//networkHandler.sendMessage(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block

				//
				e.printStackTrace();

			}

		}

	}
	
	class MakeXMLFileThread extends Thread{
		InputStream is;
		URL url = null;
		//Message msg = new Message();
		ClassInfo cInfo;
		public MakeXMLFileThread(ClassInfo cInfo) {
			// TODO Auto-generated constructor stub
			this.cInfo = cInfo;
		}
		
		@Override
		public void run() {

			HttpURLConnection connection;
			sbResult.delete(0, sbResult.capacity());
			try {
				url = new URL(
					"http://" + SERVERIP + ":8080/treeze/getMindMap?classId=" + cInfo.getClassId());
				
				connection = (HttpURLConnection) url.openConnection();
				
				if (connection != null) {
					connection.setConnectTimeout(5000); // Set Timeout
					connection.setUseCaches(false);
					
					if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
						BufferedReader br = new BufferedReader(
								new InputStreamReader(
										connection.getInputStream(), "UTF-8"));
						
						String strLine = null;
						
						while ((strLine = br.readLine()) != null) {
							sbResult.append(strLine + "\n");
						}
						
						br.close();
					}
					
					connection.disconnect();
					System.out.println(sbResult.toString());
				}			
				
				java.lang.reflect.Type type = new TypeToken<Mindmap>() {
				}.getType();
				System.out.println(sbResult.toString());
				Mindmap jsonResultMindmaps = (Mindmap) gson.fromJson(
						sbResult.toString(), (java.lang.reflect.Type) type);
				
				File dirPath = new File(DOWNPATH);
				if(!dirPath.exists())
					dirPath.mkdir();
				
				dirPath = new File(DOWNPATH + System.getProperty("file.separator") + cInfo.getClassId());
				if(!dirPath.exists())
					dirPath.mkdir();
				
				File file = new File(DOWNPATH + System.getProperty("file.separator") + cInfo.getClassId(), cInfo.getClassId() + ".mm");
				
				OutputStreamWriter fileOutput;
				fileOutput = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
				
				InputStream inputStream = connection.getInputStream();

				byte[] buffer = new byte[1024];

				int bufferLength = 0;

				fileOutput.write(jsonResultMindmaps.getMindmap().getMindmapXML());

				fileOutput.close();
				inputStream.close();
				fManager.setMmFile(file);
				fManager.setcInfo(cInfo);
				
				freemindOpen(file, cInfo);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void freemindOpen(File file, ClassInfo cInfo){
		try {
			mc.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fManager.getC().checkNodeType();
		Thread addAllTicketThread = new AddAllTicketThread();
		addAllTicketThread.start();
		
		fManager.setMode(FreemindManager.LECMODE);
		fManager.setEnableMenuBar();
		
		fManager.getC().setSlideShowInfo();
		
		Thread setResizeImgThread = new SetResizeImgThread();
		setResizeImgThread.start();
		
		Gson gson = new Gson();
		User user = new User();
//		ClassInfo classInfo = new ClassInfo();
		TreezeData treezeData = new TreezeData();
		
		user.setUserType(User.PROFESSOR);
		user.setUserEmail(fManager.getUser().getUserEmail());

		// classInfo d
		

		// treezeData d
		treezeData.setDataType(TreezeData.CONNECTIONINFO);
		treezeData.getArgList().add(gson.toJson(user));
		treezeData.getArgList().add(gson.toJson(cInfo));
		
		connectSocket(treezeData);
	}
	
	void connectSocket(TreezeData t){
		Socket socket;
		try {
			socket = new Socket(SERVERIP, PORT);
//			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
//			PrintWriter pw = new PrintWriter(osw);
//			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			OutputStream os = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			
			System.out.println(gson.toJson(t));
			
			try {
				os.write(gson.toJson(t).getBytes("UTF-8"));
				os.flush();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			int cnt = -1;
			byte[] b = new byte[1024];

			cnt = in.read(b);

			if (cnt == -1) {
				System.out.println("server end : Error");
				// c.getNaviOs().remove(os); // remove Client at Err
			} else {
				String rcvStr = null;
				try {
					rcvStr = new String(b, 0, cnt, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("FindMindSocket - rcvStr : " + rcvStr);
			}
		
			
			fManager.setOs(os);
			fManager.setIn(in);
			fManager.getC().startFreemindSocket();
			fManager.getSlideShow().setOs(os);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void updateGetallLectureList(){
		java.lang.reflect.Type type = new TypeToken<ArrayLecture>(){}.getType();
		ArrayLecture jonResultlecturelist = (ArrayLecture) gson.fromJson(sbResult.toString(), (java.lang.reflect.Type) type);
		if(jonResultlecturelist == null){
			startNetwortThread();
			return;
		}
		lectureList = jonResultlecturelist.getLectures();
		
		grid.removeAll();
		for(int i=0;i<lectureList.size();i++){
			grid.add(new LectureListItem(i + 1, lectureList.get(i)));
		}
		listPanel.updateUI();
		lectureListPanel.repaint();
		
//		mainFrameRepaint();
	}
	void updateGetallClassList(){
		java.lang.reflect.Type type = new TypeToken<ArrayClass>(){}.getType();
		ArrayClass jonResultlecturelist = (ArrayClass) gson.fromJson(sbResult.toString(), (java.lang.reflect.Type) type);
		if(jonResultlecturelist == null){
			startNetwortThread();
			return;
		}
		classList = jonResultlecturelist.getClasses();
		grid.removeAll();
		for(int i=0;i<classList.size();i++){
			grid.add(new ClassListItem(classList.get(i)));
		}
		listPanel.updateUI();
		lectureListPanel.repaint();
		
		if(classList.size() == 0){
			setMainFramevisible(false);
			setMainFramevisible(true);
			mainFrameRepaint();
		}
//		mainFrameRepaint();
	}
	
	public void startNetwortThread(){ // not receive
		networkThread = new NetworkThread();
		networkThread.start();
	}
	
	public void setMainFramevisible(boolean bool){
		setVisible(bool);
	}
	
	public void mainFrameRepaint(){
		repaint();
	}
	
	
	
}
// http://manic.tistory.com/99