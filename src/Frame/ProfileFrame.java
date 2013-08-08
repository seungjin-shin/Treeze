package Frame;

import DownloadThread.*;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Data.ArrayClass;
import Data.ArrayLecture;
import Data.ArrayMindMap;
import Data.ClassInfo;
import Data.Lecture;
import Data.Mindmap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.io.json.AbstractJsonWriter.Type;

public class ProfileFrame extends JFrame {

	BtnPanel btnPanel;
	JPanel profilePanel;
	JPanel lectureListPanel;
	LogoPanel logoPanel;
	ImageIcon imgIcon;
	ArrayList<ClassInfo> classList = new ArrayList<ClassInfo>();
	NameLabel nameLabel = new NameLabel("신 승진");
	PersonalInfo personalInfo = new PersonalInfo("한성대학교 컴퓨터공학과", "대학생",
			"경기도 평택시");
	ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
	ListPanel listPanel;
	PicturePanel picturePanel = new PicturePanel(
			"/Users/Kunyoung/Desktop/seungjin.png");
	private static final Insets insets = new Insets(10, 10, 10, 10);
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	LectureHead lectureHead;
	Lecture lecture;
	JPanel grid = new JPanel();

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
	ClassInfo classInfo;
	public ProfileFrame() {
		// TODO Auto-generated constructor stub
		this.setSize(1000, 600);
		this.setLocation(300, 200);

		this.getContentPane().setBackground(new Color(141, 198, 63));
		// this.setResizable(false);
		imgIcon = new ImageIcon("/Users/Kunyoung/Desktop/treezelogo.png");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		listPanel = new ListPanel();
		lecture = new Lecture();
		lecture.setLectureName("임베디드시스템");
		lecture.setProfessorName("이민석");
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
		profilePanel.setBackground(new Color(0, 0, 0, 0));

		lectureListPanel.setBackground(Color.WHITE);

		btnPanel.setLayout(new GridLayout(1, 4, 30, 5));

		lectureListPanel.setBorder(new LineBorder(Color.BLACK, 2, false));

		addGrid(gbl, gbc, logoPanel, 0, 0, 1, 1, 1, 3, this);
		addGrid(gbl, gbc, btnPanel, 1, 1, 1, 1, 5, 1, this);
		addGrid(gbl, gbc, profilePanel, 0, 2, 1, 1, 1, 15, this);
		addGrid(gbl, gbc, lectureListPanel, 1, 2, 1, 1, 5, 15, this);

		addGrid(gbl, gbc, picturePanel, 0, 0, 1, 1, 1, 3, profilePanel);
		addGrid(gbl, gbc, nameLabel, 0, 1, 1, 1, 1, 0, profilePanel);
		addGrid(gbl, gbc, personalInfo, 0, 2, 1, 1, 1, 0, profilePanel);

		lectureHead = new LectureHead();
		lectureHead.setBackground(new Color(0, 0, 0, 0));
		insets.left = 10;
		insets.top = 0;
		insets.bottom = 0;
		JLabel noPanel = new JLabel("과 목 명", JLabel.CENTER);
		JLabel subjectPanel = new JLabel("교  수", JLabel.CENTER);
		JLabel whritePane = new JLabel("상  태", JLabel.CENTER);
		lectureHead.setLayout(gbl);
		addGrid(gbl, gbc, noPanel, 0, 0, 1, 1, 1, 1, lectureHead);
		addGrid(gbl, gbc, subjectPanel, 1, 0, 1, 1, 1, 1, lectureHead);
		addGrid(gbl, gbc, whritePane, 2, 0, 1, 1, 1, 1, lectureHead);
		lectureListPanel.setLayout(gbl);

		// lecture List 패널
		addGrid(gbl, gbc, lectureHead, 1, 1, 1, 1, 1, 1, lectureListPanel);
		addGrid(gbl, gbc, listPanel, 1, 2, 1, 1, 1, 20, lectureListPanel);

		grid.setBackground(new Color(0, 0, 0, 0));
		grid.setLayout(new GridLayout(100, 1));
		// grid.add(item1);

		JPanel dumy = new JPanel();
		dumy.setBackground(new Color(0, 0, 0, 0));
		addGrid(gbl, gbc, dumy, 0, 3, 1, 1, 1, 3, profilePanel);
		listPanel.getViewport().add(grid, null);
		NetworkThread networkThread = new NetworkThread();
		networkThread.start();
		this.setVisible(true);
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

		public LogoPanel() {
			// TODO Auto-generated constructor stub

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawImage(imgIcon.getImage(), 10, 10, this.getWidth() - 10,
					this.getHeight() - 10, null);
		}
	}

	class PicturePanel extends JPanel {
		ImageIcon imgIcon;

		public PicturePanel(String imgPath) {
			// TODO Auto-generated constructor stub
			imgIcon = new ImageIcon(imgPath);
			this.setBorder(new EmptyBorder(20, 20, 20, 20));
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);

			g.drawImage(imgIcon.getImage(), 0, 0, this.getWidth(),
					this.getHeight(), null);

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
			g2.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight());
		}

	}

	class BtnPanel extends JPanel {
		JButton profileBtn = new JButton();
		JButton lectureBtn = new JButton();
		JButton downBtn = new JButton();
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
		JLabel stateOfLecture;
		Lecture lecture;

		public LectureListItem(final Lecture lecture) {
			// TODO Auto-generated constructor stub
			this.lecture = lecture;
			lectureNm = new JLabel(lecture.getLectureName(), JLabel.CENTER);
			professorNm = new JLabel(lecture.getProfessorName(), JLabel.CENTER);
			if (lecture.getStateOfLecture()) {
				stateOfLecture = new JLabel("온라인", JLabel.CENTER);
			} else {
				stateOfLecture = new JLabel("오프라인", JLabel.CENTER);
			}
			this.setBackground(new Color(0, 0, 0, 0));
			// this.add(noPanel);
			this.setLayout(gbl);
			insets.bottom = 5;
			insets.top = 5;
			addGrid(gbl, gbc, lectureNm, 0, 0, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, professorNm, 1, 0, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, stateOfLecture, 2, 0, 1, 1, 1, 1, this);

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
					lectureId = lecture.getLectureId();
					networkFlag = NETWORK_FLAG_GET_CLASSLIST;
					lectureHead.setVisible(false);
					NetworkThread networkThread = new NetworkThread();
					networkThread.start();
					// grid.removeAll();
					// invalidate();
					// System.out.println(ticket.getContents());
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

	class ClassListItem extends JPanel {
		JLabel classNm;
		ClassInfo classInstance;

		public ClassListItem(final ClassInfo classInstance) {
			// TODO Auto-generated constructor stub
			this.classInstance = classInstance;
			this.setLayout(new BorderLayout());
			classNm = new JLabel(classInstance.getClassName(), JLabel.CENTER);
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
					DownLoadNetworkThread downLoadNetworkThread = new DownLoadNetworkThread(
							classId);
					downLoadNetworkThread.start();
					// lectureId = lecture.getLectureId();
					networkFlag = NETWORK_FLAG_GET_MINDMAP;
					 NetworkThread networkThread = new NetworkThread();
					 networkThread.start();
					// lectureHead.setVisible(false);
					// grid.removeAll();
					// invalidate();
					// System.out.println(ticket.getContents());
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

		// HttpResponse response;
		InputStream is;
		URL url = null;
		String ip ="223.194.158.55";
		// Message msg = new Message();

		@Override
		public void run() {

			HttpURLConnection connection;
			sbResult.delete(0, sbResult.capacity());
			try {
				if (networkFlag == NETWORK_FLAG_GET_MINDMAP) {
					url = new URL(
							"http://"+ip+":8080/treeze/getMindMap?classId="
									+ classId);
				} else if (networkFlag == NETWORK_FLAG_GET_LECTURELIST) {
					url = new URL(
							"http://"+ip+":8080/treeze/getAllLectures");

				} else {
					url = new URL(
							"http://"+ip+":8080/treeze//getClasses?lectureId="
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
					if (networkFlag == NETWORK_FLAG_GET_MINDMAP) {

						// new MindMapMain(sbResult.toString());
						startMindMapFrame();
						// System.out.println(sbResult.toString());
					} else if (networkFlag == NETWORK_FLAG_GET_LECTURELIST)
						updateGetallLectureList();
					else {
						updateGetallClassList();
					}

					// networkHandler.sendMessage(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block

				//
				e.printStackTrace();

			}

		}

	}

	void updateGetallLectureList() {
		java.lang.reflect.Type type = new TypeToken<ArrayLecture>() {
		}.getType();
		ArrayLecture jonResultlecturelist = (ArrayLecture) gson.fromJson(
				sbResult.toString(), (java.lang.reflect.Type) type);
		lectureList = jonResultlecturelist.getLectures();
		grid.removeAll();
		for (int i = 0; i < lectureList.size(); i++) {
			grid.add(new LectureListItem(lectureList.get(i)));
		}
		invalidate();
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

	}

	void startMindMapFrame() {
		java.lang.reflect.Type type = new TypeToken<Mindmap>() {
		}.getType();
		System.out.println(sbResult.toString());
		Mindmap jsonResultMindmaps = (Mindmap) gson.fromJson(
				sbResult.toString(), (java.lang.reflect.Type) type);
		new MindMapMain(jsonResultMindmaps.getMindmap().getMindmapXML(),classInfo);
		//System.out.println(jsonResultMindmap.getMindmapXML());
	}
}
// http://manic.tistory.com/99