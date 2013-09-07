package com.treeze.frame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Handler;

import javax.jws.Oneway;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import JDIalog.TextDialogue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.io.json.AbstractJsonWriter.Type;
import com.treeze.Abstract.ImgBtn;
import com.treeze.data.ArrayClass;
import com.treeze.data.ArrayLecture;
import com.treeze.data.ArrayMindMap;
import com.treeze.data.ClassInfo;
import com.treeze.data.Lecture;
import com.treeze.data.Mindmap;
import com.treeze.data.TreezeStaticData;
import com.treeze.data.User;
import com.treeze.downloadthread.*;
import com.treeze.frame.TicketWriteFrame.WriteBtn;
import com.treeze.uploadthread.CreateLecture;
import com.treeze.uploadthread.DeleteCourse;

public class ProfileFrame extends JFrame {

	BtnPanel btnPanel;
	JPanel profilePanel;
	JPanel lectureListPanel;
	LogoPanel logoPanel;
	ImageIcon imgIcon;
	User user;
	ArrayList<ClassInfo> classList = new ArrayList<ClassInfo>();
	NameLabel nameLabel ;
	PersonalInfo personalInfo ;
	ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
	ListPanel listPanel;
	PicturePanel picturePanel = new PicturePanel(
			"/Users/Kunyoung/Desktop/seungjin.png");
	private static final Insets insets = new Insets(10, 10, 10, 10);
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	LectureHead lectureHead;

	MyLectureListBtn myLectureListBtn = new MyLectureListBtn(
			TreezeStaticData.MYCOURSE_IMG, TreezeStaticData.MYCOURSE_PRESS_IMG,
			TreezeStaticData.MYCOURSE_ENTER_IMG);
	AllLectureListBtn allLectureListBtn = new AllLectureListBtn(
			TreezeStaticData.ALLCOURSE_IMG, TreezeStaticData.ALLCOURSE_PRESS_IMG,
			TreezeStaticData.ALLCOURSE_ENTER_IMG);
	AddLectureListBtn addLectureBtn = new AddLectureListBtn(
			TreezeStaticData.ADD_COURSE_IMG,
			TreezeStaticData.ADD_COURSE_PRESS_IMG,
			TreezeStaticData.ADD_COURSE_ENTER_IMG);
	AllLectureListBtn logoutBtn = new AllLectureListBtn(
			TreezeStaticData.PROFILE_IMG, TreezeStaticData.PROFILE_PRESS_IMG,
			TreezeStaticData.PROFILE_ENTER_IMG);
	DeleteCourseBtn deleteCourseBtn = new DeleteCourseBtn(TreezeStaticData.DELETE_COURSE_IMG, TreezeStaticData.DELETE_COURSE_PRESS_IMG, TreezeStaticData.DELETE_COURSE_ENTER_IMG);

	static JPanel grid = new JPanel();
	JPanel fullPanel = new JPanel();
	final int NETWORK_FLAG_GET_LECTURELIST = 0;
	final int NETWORK_FLAG_GET_CLASSLIST = 1;
	final int NETWORK_FLAG_GET_MY_LECTURE_LIST = 2;
	final int NETWORK_FLAG_GET_MINDMAP = 3;
	int networkFlag;
	Handler networkHandler;
	String professorEmail;
	long lectureId;
	long classId;
	StringBuffer sbResult = new StringBuffer();
	Gson gson = new Gson();
	ClassInfo classInfo;
	ArrayList<JCheckBox> chkBoxList = new ArrayList<JCheckBox>();

	public ProfileFrame() {
		// TODO Auto-generated constructor stub

		this.setLocation(300, 200);

		this.getContentPane().setBackground(new Color(141, 198, 63));

		this.setResizable(false);
		// imgIcon = new ImageIcon("/Users/Kunyoung/Desktop/treezelogo.png");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		listPanel = new ListPanel();

		gbc.fill = GridBagConstraints.BOTH;
		user = User.getInstance();
		// C:\Users\占식곤옙\Desktop
		nameLabel = new NameLabel(user.getUserName());
		personalInfo = new PersonalInfo(user.getUserType(), user.getUserEmail(),"");
		setLayout(new BorderLayout());
		this.add(fullPanel);
		logoPanel = new LogoPanel();
		fullPanel.setLayout(gbl);
		fullPanel.setBackground(TreezeStaticData.TREEZE_BG_COLOR);
		btnPanel = new BtnPanel();
		btnPanel.setBtn(myLectureListBtn, allLectureListBtn, addLectureBtn,
				logoutBtn);
		profilePanel = new JPanel();

		lectureListPanel = new JPanel();
		profilePanel.setLayout(gbl);
		logoPanel.setBackground(new Color(0, 0, 0, 0));

		btnPanel.setBackground(new Color(0, 0, 0, 0));
		profilePanel.setBackground(new Color(0, 0, 0, 0));

		lectureListPanel.setBackground(Color.WHITE);

		lectureListPanel.setBorder(new LineBorder(Color.BLACK, 2, false));

		addGrid(gbl, gbc, logoPanel, 0, 0, 1, 1, 1, 3, fullPanel);
		insets.set(10, 20, 10, 20);
		addGrid(gbl, gbc, btnPanel, 1, 1, 1, 1, 5, 1, fullPanel);

		addGrid(gbl, gbc, profilePanel, 0, 2, 1, 1, 1, 15, fullPanel);
		addGrid(gbl, gbc, lectureListPanel, 1, 2, 1, 1, 5, 15, fullPanel);

		addGrid(gbl, gbc, picturePanel, 0, 0, 1, 1, 1, 3, profilePanel);
		addGrid(gbl, gbc, nameLabel, 0, 1, 1, 1, 1, 0, profilePanel);
		addGrid(gbl, gbc, personalInfo, 0, 2, 1, 1, 1, 0, profilePanel);

		lectureHead = new LectureHead();
		lectureHead.setBackground(new Color(0, 0, 0, 0));
		insets.left = 10;
		insets.top = 0;
		insets.bottom = 0;
		JLabel noPanel = new JLabel("과 목 명", JLabel.CENTER);
		JLabel subjectPanel = new JLabel("교 수 명", JLabel.CENTER);
		JLabel whritePane = new JLabel("상  태", JLabel.CENTER);
		lectureHead.setLayout(gbl);
		addGrid(gbl, gbc, noPanel, 0, 0, 1, 1, 15, 1, lectureHead);
		addGrid(gbl, gbc, subjectPanel, 1, 0, 1, 1, 1, 1, lectureHead);
		addGrid(gbl, gbc, whritePane, 2, 0, 1, 1, 1, 1, lectureHead);
		lectureListPanel.setLayout(gbl);

		// lecture List 占싻놂옙
		addGrid(gbl, gbc, lectureHead, 1, 1, 1, 1, 1, 1, lectureListPanel);
		addGrid(gbl, gbc, listPanel, 1, 2, 1, 1, 1, 20, lectureListPanel);

		grid.setBackground(new Color(0, 0, 0, 0));
		grid.setLayout(new GridLayout(100, 1, 0, 5));

		JPanel dumy = new JPanel();
		dumy.setBackground(new Color(0, 0, 0, 0));
		addGrid(gbl, gbc, dumy, 0, 3, 1, 1, 1, 3, profilePanel);
		listPanel.getViewport().add(grid, null);
		NetworkThread networkThread = new NetworkThread();
		networkFlag =NETWORK_FLAG_GET_MY_LECTURE_LIST;
		networkThread.start();
		this.setSize(1100, 600);
		this.setVisible(true);
		grid.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				// TODO Auto-generated method stub
				// fullPanel.setVisible(false);
				// fullPanel.setVisible(true);
				// setVisible(true);

				// wheelRotation
				listPanel.getVerticalScrollBar().setValue(
						listPanel.getVerticalScrollBar().getValue()
								+ arg0.getWheelRotation());
				// arg0.getWheelRotation();
			}
		});

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
			gbc.insets.left = 0;

		}

		container.add(c);
	}

	class LogoPanel extends JPanel {
		ImageIcon icon;

		public LogoPanel() {
			// TODO Auto-generated constructor stub

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			if (icon == null) {
				icon = TreezeStaticData.makeResizedImageIcon(getWidth(),
						getHeight(), TreezeStaticData.LOGO_IMG);
			}
			g.drawImage(icon.getImage(), 0, 0, null);
		}
	}

	class PicturePanel extends JPanel {
		ImageIcon icon;

		public PicturePanel(String imgPath) {
			// TODO Auto-generated constructor stub
			// imgIcon = new ImageIcon(imgPath);
			this.setBorder(new EmptyBorder(20, 20, 20, 20));
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			if (icon == null) {
				icon = TreezeStaticData.makeResizedImageIcon(getWidth(),
						getHeight(), TreezeStaticData.PROFILE_DEFAULT_IMG);
			}
			g.drawImage(icon.getImage(), 0, 0, null);

		}

	}

	class NameLabel extends JLabel {
		public NameLabel(String name) {
			// TODO Auto-generated constructor stub
			this.setText(name);
			this.setFont(new Font("Serif", Font.BOLD, 15));
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setVerticalAlignment(SwingConstants.CENTER);
		}

		void setText(int size) {
			this.setFont(new Font("Serif", Font.BOLD, size));
		}

	}

	class PersonalInfo extends JPanel {
		JLabel sc;
		JLabel job;
		JLabel address;

		public PersonalInfo(String school, String job, String address) {
			// TODO Auto-generated constructor stub
			sc = new JLabel(school);
			this.setLayout(new GridLayout(3, 1, 3, 3));
			this.setSize(this.getWidth(), this.getHeight());
			this.job = new JLabel(job);
			this.address = new JLabel(address);
			this.setBackground(new Color(0, 0, 0, 0));
			this.setBorder(new EmptyBorder(10, 0, 10, 0));

			// this.add(sc);
			// this.add(this.job);
			// this.add(this.address);
			this.labelset(this.sc);
			this.labelset(this.job);
			this.labelset(this.address);

		}

		void labelset(JLabel label) {
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
			g2.drawLine(0, this.getHeight() - 1, this.getWidth(),
					this.getHeight() - 1);
		}

	}

	class BtnPanel extends JPanel {

		public BtnPanel() {
			// TODO Auto-generated constructor stu
			this.setBackground(TreezeStaticData.TREEZE_BG_COLOR);
			this.setLayout(new GridLayout(1, 4, 30, 5));
			add(myLectureListBtn);
			add(addLectureBtn);
			add(allLectureListBtn);
			add(logoutBtn);
			// setBtnImg(logoutBtn.getWidth(),logoutBtn.geth
		}

		public void setBtn(JComponent j1, JComponent j2, JComponent j3,
				JComponent j4) {
			for (int i = 0; i < this.getComponentCount(); i++) {
				this.remove(this.getComponent(i));
			}
			add(j1);
			add(j2);
			add(j3);
			add(j4);
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
		JLabel lectureNm;
		JLabel professorNm;
		ImgPanel stateOfLecture;
		Lecture lecture;
		JScrollPane jsp;
		JCheckBox deleteChkBox = new JCheckBox();
		public LectureListItem(final Lecture lecture) {
			// TODO Auto-generated constructor stub
			this.lecture = lecture;
			this.setBorder(new EmptyBorder(5, 0, 5, 0));
			lectureNm = new JLabel(lecture.getLectureName(), JLabel.CENTER);
			professorNm = new JLabel(lecture.getProfessorName(), JLabel.CENTER);
			chkBoxList.add(deleteChkBox);
			// lectureNm.setFont(new Font("Serif", Font.ITALIC, 18));
			// professorNm.setFont(new Font("Serif", Font.ITALIC, 18));
			if (lecture.getStateOfLecture()) {
				// JPanel j = new JPanel()
				stateOfLecture = new ImgPanel(TreezeStaticData.ONLINE_IMG);

			} else {
				stateOfLecture = new ImgPanel(TreezeStaticData.OFFLIN_IMG);

			}
			this.setBackground(new Color(0, 0, 0, 0));
			// this.add(noPanel);
			this.setLayout(gbl);
			insets.bottom = 5;
			insets.top = 5;
			jsp = new JScrollPane(lectureNm);
			jsp.setBorder(null);
			addGrid(gbl, gbc, deleteChkBox, 0, 0, 1, 1, 0, 1, this);
			addGrid(gbl, gbc, jsp, 1, 0, 1, 1, 7, 1, this);
			addGrid(gbl, gbc, professorNm, 2, 0, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, stateOfLecture, 3, 0, 1, 1, 1, 1, this);
			lectureNm.setPreferredSize(new Dimension(lectureNm.getWidth(),
					lectureNm.getHeight()));
			jsp.getViewport().setBackground(Color.WHITE);
			stateOfLecture.repaint();
			this.addMouseListener(new ProfileMouseListener(this, jsp));
			jsp.addMouseListener(new ProfileMouseListener(this, jsp));

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawLine(0, this.getHeight() - 1, this.getWidth(),
					this.getHeight() - 1);
		}
	}

	class LectureListItemCheckBox extends JPanel {
		JLabel lectureNm;
		JLabel professorNm;

		Lecture lecture;
		JScrollPane jsp;
		JCheckBox addChkBox = new JCheckBox();

		public LectureListItemCheckBox(final Lecture lecture) {
			// TODO Auto-generated constructor stub
			this.lecture = lecture;
			this.setBorder(new EmptyBorder(5, 0, 5, 0));
			lectureNm = new JLabel(lecture.getLectureName(), JLabel.CENTER);
			professorNm = new JLabel(lecture.getProfessorName(), JLabel.CENTER);
			addChkBox.setBackground(Color.WHITE);
			chkBoxList.add(addChkBox);
			// lectureNm.setFont(new Font("Serif", Font.ITALIC, 18));
			// professorNm.setFont(new Font("Serif", Font.ITALIC, 18));

			this.setBackground(new Color(0, 0, 0, 0));
			// this.add(noPanel);
			this.setLayout(gbl);
			insets.bottom = 5;
			insets.top = 5;
			jsp = new JScrollPane(lectureNm);
			jsp.setBorder(null);
			addGrid(gbl, gbc, jsp, 0, 0, 1, 1, 7, 1, this);
			addGrid(gbl, gbc, professorNm, 1, 0, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, addChkBox, 2, 0, 1, 1, 1, 1, this);
			lectureNm.setPreferredSize(new Dimension(lectureNm.getWidth(),
					lectureNm.getHeight()));
			jsp.getViewport().setBackground(Color.WHITE);

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawLine(0, this.getHeight() - 1, this.getWidth(),
					this.getHeight() - 1);
		}
	}

	class ClassListItem extends JPanel {
		JLabel classNm;
		ClassInfo classInstance;

		public ClassListItem(final ClassInfo classInstance) {
			// TODO Auto-generated constructor stub
			this.classInstance = classInstance;
			this.setBorder(new EmptyBorder(5, 0, 5, 0));
			this.setLayout(new BorderLayout());
			this.setBackground(Color.WHITE);
			classNm = new JLabel(classInstance.getClassName(), JLabel.CENTER);
			classNm.setFont(new Font("Serif", Font.BOLD, 20));
			// this.setFont(new Font("Serif", Font.BOLD, 15));
			this.add(classNm);

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
					classId = classInstance.getClassId();
					classInfo = classInstance;
					ClassInfo.getInstance().setClassId(classInfo.getClassId());
					ClassInfo.getInstance().setLectureId(classInfo.getLectureId());
					classInfo.getInstance().setClassName(classInfo.getClassName());
					DownLoadNetworkThread downLoadNetworkThread = new DownLoadNetworkThread(
							classId);
					downLoadNetworkThread.start();
					networkFlag = NETWORK_FLAG_GET_MINDMAP;
					NetworkThread networkThread = new NetworkThread();
					networkThread.start();
					lectureHead.setVisible(false);
					fullPanel.setVisible(false);
					fullPanel.setVisible(true);
					setVisible(true);
					fullPanel.repaint();
					btnPanel.setVisible(false);
					btnPanel.setVisible(true);
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

	class ListPanel extends JScrollPane {

		public ListPanel() {
			// TODO Auto-generated constructor stub

			// this.add(noPanel);
			// this.setPreferredSize(new Dimension(0, 2000));
			this.setBackground(Color.WHITE);
			this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

			getVerticalScrollBar().getModel().addChangeListener(
					new ChangeListener() {

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

	class ImgPanel extends JPanel {
		Image img;
		ImageIcon icon;

		public ImgPanel(Image img) {
			// TODO Auto-generated constructor stub
			this.img = img;
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			if (icon == null) {
				icon = TreezeStaticData.makeResizedImageIcon(getWidth(),
						getHeight(), img);
			}
			g.drawImage(icon.getImage(), 0, 0, null);

		}
	}

	class NetworkThread extends Thread {

		// HttpResponse response;
		// InputStream is;
		URL url = null;
		String ip = TreezeStaticData.IP;

		// Message msg = new Message();

		@Override
		public void run() {
			// http://113.198.84.80:8080/treeze/getMyCourses?studentEmail=yukult400@gmail.com
			HttpURLConnection connection;
			sbResult.delete(0, sbResult.capacity());
			try {
				if (networkFlag == NETWORK_FLAG_GET_MINDMAP) {
					url = new URL("http://" + ip
							+ ":8080/treeze/getMindMap?classId=" + classId);
				} else if (networkFlag == NETWORK_FLAG_GET_LECTURELIST) {
					url = new URL("http://" + ip
							+ ":8080/treeze/getAllLectures");

				} else if (networkFlag == NETWORK_FLAG_GET_MY_LECTURE_LIST) {
					url = new URL(
							"http://"
									+ ip
									+ ":8080/treeze/getMyCourses?studentEmail="+user.getUserEmail());
				} else {
					url = new URL("http://" + ip
							+ ":8080/treeze/getClasses?lectureId=" + lectureId);

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
					if (networkFlag == NETWORK_FLAG_GET_MINDMAP) {

						// new MindMapMain(sbResult.toString());
						startMindMapFrame();
						// System.out.println(sbResult.toString());
					} else if (networkFlag == NETWORK_FLAG_GET_LECTURELIST)
						updateGetallLectureList();
					else if (networkFlag == NETWORK_FLAG_GET_MY_LECTURE_LIST) {
						updateGetMyLectureList();
					} else {
						updateGetallClassList();
					}

					// networkHandler.sendMessage(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block

				//
				e.printStackTrace();
				NetworkThread networkThread = new NetworkThread();
				networkThread.start();

			}

		}

	}

	void updateGetMyLectureList() {
		// TODO Auto-generated method stub
		btnPanel.setBtn(myLectureListBtn, allLectureListBtn, deleteCourseBtn,
				logoutBtn);
		java.lang.reflect.Type type = new TypeToken<ArrayLecture>() {
		}.getType();
		ArrayLecture jonResultlecturelist = (ArrayLecture) gson.fromJson(
				sbResult.toString(), (java.lang.reflect.Type) type);

		lectureList = jonResultlecturelist.getLectures();
		grid.removeAll();
		insets.set(5, 0, 5, 0);
		chkBoxList.clear();
		for (int i = 0; i < lectureList.size(); i++) {
			LectureListItem lectureListItem = new LectureListItem(
					lectureList.get(i));
			grid.add(lectureListItem);

		}
		
		lectureHead.setVisible(true);
		fullPanel.setVisible(false);
		fullPanel.setVisible(true);
		setVisible(true);
		fullPanel.repaint();
		btnPanel.setVisible(false);
		btnPanel.setVisible(true);
	}

	void updateGetallLectureList() {

		btnPanel.setBtn(myLectureListBtn, allLectureListBtn, addLectureBtn,
				logoutBtn);
		java.lang.reflect.Type type = new TypeToken<ArrayLecture>() {
		}.getType();
		ArrayLecture jonResultlecturelist = (ArrayLecture) gson.fromJson(
				sbResult.toString(), (java.lang.reflect.Type) type);

		lectureList = jonResultlecturelist.getLectures();
		grid.removeAll();
		insets.set(5, 0, 5, 0);
		chkBoxList.clear();
		for (int i = 0; i < lectureList.size(); i++) {
			LectureListItemCheckBox lectureListItemCheckBox = new LectureListItemCheckBox(
					lectureList.get(i));
			grid.add(lectureListItemCheckBox);

		}
		lectureHead.setVisible(false);
		fullPanel.setVisible(false);
		fullPanel.setVisible(true);
		setVisible(true);
		fullPanel.repaint();
		btnPanel.setVisible(false);
		btnPanel.setVisible(true);

	}

	void updateGetallClassList() {
		java.lang.reflect.Type type = new TypeToken<ArrayClass>() {
		}.getType();
		ArrayClass jonResultlecturelist = (ArrayClass) gson.fromJson(
				sbResult.toString(), (java.lang.reflect.Type) type);
		classList = jonResultlecturelist.getClasses();
		grid.removeAll();
		for (int i = 0; i < classList.size(); i++) {
			grid.add(new ClassListItem(classList.get(i)));
		}

		setVisible(true);
		btnPanel.setVisible(false);
		btnPanel.setVisible(true);

	}

	void startMindMapFrame() {
		java.lang.reflect.Type type = new TypeToken<Mindmap>() {
		}.getType();
		System.out.println(sbResult.toString());
		Mindmap jsonResultMindmaps = (Mindmap) gson.fromJson(
				sbResult.toString(), (java.lang.reflect.Type) type);
		MindMapMain mindmapMain = new MindMapMain(jsonResultMindmaps
				.getMindmap().getMindmapXML(), classInfo);
		MainFrameManager mainFrameManager = new MainFrameManager(mindmapMain,
				classInfo);
	}

	class ProfileMouseListener implements MouseListener {
		LectureListItem lectureListItem;
		JScrollPane jsp;

		public ProfileMouseListener(LectureListItem lectureListItem,
				JScrollPane jsp) {
			// TODO Auto-generated constructor stub
			this.lectureListItem = lectureListItem;
			this.jsp = jsp;
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			jsp.getViewport().setBackground(new Color(255, 255, 255, 255));
			lectureListItem.setBackground(new Color(255, 255, 255, 255));
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
			jsp.getViewport().setBackground(new Color(10, 10, 100, 100));
			lectureListItem.setBackground(new Color(10, 10, 100, 100));
			lectureId = lectureListItem.lecture.getLectureId();
			networkFlag = NETWORK_FLAG_GET_CLASSLIST;
			lectureHead.setVisible(false);
			NetworkThread networkThread = new NetworkThread();
			networkThread.start();

		}

	}

	class MyLectureListBtn extends ImgBtn {

		public MyLectureListBtn(final Image defaultImg, final Image pressImg,
				final Image enterImg) {
			// TODO Auto-generated constructor stub
			super(defaultImg, pressImg, enterImg);

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			fullPanel.setVisible(false);
			fullPanel.setVisible(true);
			setVisible(true);
			fullPanel.repaint();
			btnPanel.setVisible(false);
			btnPanel.setVisible(true);
		}

		@Override
		protected void Action() {

			networkFlag = NETWORK_FLAG_GET_MY_LECTURE_LIST;
			NetworkThread networkThread = new NetworkThread();
			networkThread.start();
			repaint();

		}
	}

	class AllLectureListBtn extends ImgBtn {

		public AllLectureListBtn(final Image defaultImg, final Image pressImg,
				final Image enterImg) {
			// TODO Auto-generated constructor stub
			super(defaultImg, pressImg, enterImg);

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			fullPanel.setVisible(false);
			fullPanel.setVisible(true);
			setVisible(true);
			fullPanel.repaint();
			btnPanel.setVisible(false);
			btnPanel.setVisible(true);
		}

		@Override
		protected void Action() {

			networkFlag = NETWORK_FLAG_GET_LECTURELIST;
			NetworkThread networkThread = new NetworkThread();
			networkThread.start();
			repaint();
		}
	}
	class DeleteCourseBtn extends ImgBtn{

		public DeleteCourseBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			// TODO Auto-generated method stub

			int chkCnt = 0;
		
			for (int i = 0; i < chkBoxList.size(); i++) {
				JCheckBox tmp = chkBoxList.get(i);
				if (tmp.isSelected()) {
					chkCnt++;
					Lecture lecture = lectureList.get(i);
					System.out.println(lecture.getLectureId());
					DeleteCourse deleteCourseBtn = new DeleteCourse(
							lecture.getLectureId() + "",
							lecture.getLectureName(), user.getUserEmail());
					deleteCourseBtn.start();
					
				}
			}
			repaint();
			if(chkCnt>0){
			TextDialogue textDialogue = new TextDialogue(ProfileFrame.this, "수강신청 취소 되었습니다..", true);
			}
			
		
			
		}
		
	}
	public class AddLectureListBtn extends ImgBtn {
		TextDialogue completeLectureAdd ;
		TextDialogue textDialogue;
		public AddLectureListBtn(final Image defaultImg, final Image pressImg,
				final Image enterImg) {
			// TODO Auto-generated constructor stub
			super(defaultImg, pressImg, enterImg);
			

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			fullPanel.setVisible(false);
			fullPanel.setVisible(true);
			setVisible(true);
			fullPanel.repaint();
			btnPanel.setVisible(false);
			btnPanel.setVisible(true);
		}

		@Override
		public void Action() {
			int chkCnt = 0;
			
			for (int i = 0; i < chkBoxList.size(); i++) {
				JCheckBox tmp = chkBoxList.get(i);
				if (tmp.isSelected()) {
					chkCnt++;
					Lecture lecture = lectureList.get(i);
					CreateLecture createLecture = new CreateLecture(
							lecture.getLectureId() + "",
							lecture.getLectureName(), user.getUserEmail(),AddLectureListBtn.this);
					createLecture.start();
					
				}
			}
			repaint();
			if(chkCnt>0){
				completeLectureAdd=  new TextDialogue(ProfileFrame.this, "수강신청이 완료 되었습니다.", true);
		     }
			
		}
		public void resultAddLecture(String result) {
			if(textDialogue==null)
			textDialogue = new TextDialogue(ProfileFrame.this, result, true);
			textDialogue.setVisible(true);
			if(completeLectureAdd!=null)
				completeLectureAdd.setVisible(false);
			
		}
		
	}

}
// http://manic.tistory.com/99