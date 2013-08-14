package freemind.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import freemind.controller.FreemindManager;
import freemind.json.ArrayClass;
import freemind.json.ArrayLecture;
import freemind.json.ClassInfo;
import freemind.json.Lecture;
import freemind.json.Mindmap;
import freemind.json.TreezeData;
import freemind.json.User;
import freemind.main.LecturePageFrame.ClassPanel;
import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapController;


public class ProfileFrame extends JFrame {
	final String SERVERIP = "113.198.84.74";
//	final String SERVERIP = "223.194.158.55";
	final int PORT = 2141;
	String myEmail = "minsuk@hansung.ac.kr";
	final String DOWNPATH = "/Users/dewlit/Desktop/TreezeIMG";
	
	JButton createLtBtn;
	ActionListener addLtListener = new AddLtListener();
	FreemindManager fManager;
	
	BtnPanel btnPanel;
	JPanel profilePanel;
	JPanel lectureListPanel;
	LogoPanel logoPanel;
	Image imgIcon;
//	ArrayList<Data.Class> classList = new ArrayList<Data.Class>();	
	NameLabel nameLabel = new NameLabel("이 민석");
	PersonalInfo personalInfo = new PersonalInfo("Hansung Univ", "Professor", "Computer engineering");
//	ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
	ListPanel listPanel;
	PicturePanel picturePanel = new PicturePanel(Toolkit.getDefaultToolkit().getImage("images/minsuk.jpg"));
	private static final Insets insets = new Insets(10, 10, 10, 10);
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	LectureHead lectureHead;
//	Lecture lecture;
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
	MindMapController mc;
	NetworkThread networkThread;
	
	public ProfileFrame(MindMapController mc) {
		this.mc = mc;
		fManager = FreemindManager.getInstance();
		fManager.setFilePath(DOWNPATH + "/");
		// TODO Auto-generated constructor stub
		this.setSize(1000, 600);
		this.setLocation(300, 200);
		
		this.getContentPane().setBackground(new Color(141, 198, 63));
		//this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		listPanel = new ListPanel();
//		lecture = new Lecture();
//		lecture.setLectureName("ÀÓº£µðµå½Ã½ºÅÛ");
//		lecture.setProfessorName("ÀÌ¹Î¼®");
		gbc.fill = GridBagConstraints.BOTH;

		// C:\Users\ÄÄ°ø\Desktop
		setLayout(gbl);
		
		logoPanel = new LogoPanel(Toolkit.getDefaultToolkit().getImage("images/treezeLogo.png"));

		lectureListPanel = new JPanel();
		
		profilePanel = new JPanel();
		btnPanel = new BtnPanel(lectureListPanel);
		
		profilePanel.setLayout(gbl);
		logoPanel.setBackground(new Color(0, 0, 0, 0));
		
		btnPanel.setBackground(new Color(0, 0, 0, 0));
		profilePanel.setBackground(new Color(0,0,0,0));
		
		lectureListPanel.setBackground(Color.WHITE);
		
		
		btnPanel.setLayout(new GridLayout(1, 4, 30, 5));


		lectureListPanel.setBorder(new LineBorder(Color.BLACK, 2, false));

		addGrid(gbl, gbc, logoPanel,        0, 0, 1, 1, 1, 3, this);
		addGrid(gbl, gbc, btnPanel,         1, 1, 1, 1, 5, 1, this);
		addGrid(gbl, gbc, profilePanel,     0, 2, 1, 1, 1, 15, this);
		addGrid(gbl, gbc, lectureListPanel, 1, 2, 1, 1, 5, 15, this);
		
		
		
		addGrid(gbl, gbc, picturePanel, 0, 0, 1, 1, 1, 3, profilePanel);
		addGrid(gbl, gbc, nameLabel, 	0, 1, 1, 1, 1, 0, profilePanel);
		addGrid(gbl, gbc, personalInfo, 0, 2, 1, 1, 1, 0, profilePanel);
	
		lectureHead = new LectureHead();
		lectureHead.setBackground(new Color(0,0,0,0));
		insets.left = 10;
		insets.top = 0;
		insets.bottom = 0;
		JLabel noPanel = new JLabel("N o",JLabel.CENTER);
		JLabel subjectPanel = new JLabel("Subject",JLabel.CENTER);
		JLabel whritePane = new JLabel("State",JLabel.CENTER);
		lectureHead.setLayout(gbl);
		addGrid(gbl, gbc, noPanel,      0, 0, 1, 1, 1, 1, lectureHead);
		addGrid(gbl, gbc, subjectPanel, 1, 0, 1, 1, 1, 1, lectureHead);
		addGrid(gbl, gbc, whritePane,   2, 0, 1, 1, 1, 1, lectureHead);
		lectureListPanel.setLayout(gbl);
		
		// lecture List ÆÐ³Î 
		addGrid(gbl, gbc, lectureHead, 1, 1, 1, 1, 1, 1, lectureListPanel);
		addGrid(gbl, gbc, listPanel, 1, 2, 1, 1, 1, 20, lectureListPanel);
		
		grid.setBackground(new Color(0,0,0,0));
		grid.setLayout(new GridLayout(100, 1));
		//grid.add(item1);
		
		JPanel dumy =new JPanel();
		dumy.setBackground(new Color(0, 0, 0, 0));
		addGrid(gbl, gbc,dumy, 0, 3, 1, 1, 1, 3, profilePanel);
		listPanel.getViewport().add(grid, null);
		networkThread = new NetworkThread();
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
		ImageIcon imgIcon;
		public LogoPanel(Image img) {
			// TODO Auto-generated constructor stub
			imgIcon = new ImageIcon(img);
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawImage(imgIcon.getImage(), 10, 10, this.getWidth()-10,
					this.getHeight()-10, null);
		}
	}

	class PicturePanel extends JPanel {
		ImageIcon imgIcon;
		public PicturePanel(Image image) {
			// TODO Auto-generated constructor stub
			imgIcon = new ImageIcon(image);
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
	
	class AddLtListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new InputLectureFrame();
		}
		
	}
	
	class BtnPanel extends JPanel {
		JButton lectureBtn = new JButton();
		JButton downBtn = new JButton();
		JButton logoutBtn = new JButton();
		ImageIcon imgIcon, resizeIcon;
		Image img, scaledImage;
		BufferedImage imageBuff;
		JPanel parentPanel;

		public BtnPanel(JPanel f) {
			createLtBtn = new JButton("add Lecture");
			parentPanel = f;
			createLtBtn.addActionListener(addLtListener);
			
			// TODO Auto-generated constructor stub
			// setBtnImg();
			add(createLtBtn);
			add(lectureBtn);
			add(downBtn);
			add(logoutBtn);
			// setBtnImg(logoutBtn.getWidth(),logoutBtn.geth)

		}

		void setBtnImg(int whdth, int height) {
			// downBtn.setSize(this.getWidth()/10, this.getHeight()/2);
//			scaledImage = img.getScaledInstance(whdth, height,
//					Image.SCALE_SMOOTH);
			imageBuff = new BufferedImage(whdth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = imageBuff.createGraphics();
			g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
			g.dispose();
			resizeIcon = new ImageIcon(scaledImage);
			createLtBtn.setIcon(resizeIcon);
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
				UploadToServer UTS = new UploadToServer();
				UTS.lecturePost(lectureTitle, "minsuk@hansung.ac.kr", "false");
				
				this.setVisible(false);
				
				networkThread = new NetworkThread();
				networkThread.start();
				
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
		JLabel lectureNm;
		JLabel professorNm;
		JLabel stateOfLecture;

		public LectureListItem(final Lecture lecture) {
			// TODO Auto-generated constructor stub
			lectureNm = new JLabel(lecture.getLectureName(), JLabel.CENTER);
			professorNm = new JLabel(lecture.getProfessorEmail(), JLabel.CENTER);
			if(lecture.getStateOfLecture()){
				stateOfLecture = new JLabel("Online", JLabel.CENTER);
			}
			else {
				stateOfLecture = new JLabel("Offline", JLabel.CENTER);
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
					lectureId  = lecture.getLectureId();
					networkFlag = NETWORK_FLAG_GET_CLASSLIST;
				lectureHead.setVisible(false);
				networkThread = new NetworkThread();
				networkThread.start();
				
				createLtBtn.setText("add Class");
				createLtBtn.removeActionListener(addLtListener);
				createLtBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						new InputClassFrame();
					}
				});
				
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
			setLocation(350, 200);
			
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
				UploadToServer UTS = new UploadToServer();
				UTS.classPost(lectureId + "", "minsuk@hansung.ac.kr", classTitle);

				this.setVisible(false);
				
				networkThread = new NetworkThread();
				networkThread.start();
			}
		}
	}
	
	class ClassListItem extends JPanel {
		JLabel classNm;
		JButton regBtn = new JButton("Reg Mindmap");
		JButton goBtn = new JButton("Start Lecture");
		public ClassListItem(final ClassInfo classInfo) {
			// TODO Auto-generated constructor stub
			this.setLayout(gbl);
			setBackground(Color.white);
			classNm = new JLabel(classInfo.getClassName(), JLabel.CENTER);
			
			regBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					mc.open();
				}
			});
			
			goBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					classId = classInfo.getClassId();
					
					DownLoadNetworkThread downLoadNetworkThread = new DownLoadNetworkThread(classId);
					downLoadNetworkThread.start();
					
					setMainFramevisible(false);
					
					MakeXMLFileThread makeXMLThread = new MakeXMLFileThread(classInfo);
					makeXMLThread.start();
				}
			});
			
			insets.top = 0;
			insets.bottom = 0;
			
			addGrid(gbl, gbc, classNm, 0, 0, 1, 2, 8, 3, this);
			addGrid(gbl, gbc, regBtn,  1, 0, 1, 1, 1, 3, this);
			addGrid(gbl, gbc, goBtn,   1, 1, 1, 1, 1, 3, this);

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
					
//					lectureId  = lecture.getLectureId();
//					networkFlag = NETWORK_FLAG_GET_MINDMAP;
//					NetworkThread networkThread = new NetworkThread();
//					networkThread.start();
//					lectureHead.setVisible(false);
//					grid.removeAll();
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
							"http://" + SERVERIP + ":8080/treeze/getMyLectures?professorEmail=" + myEmail);
					
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
					"http://" + SERVERIP + ":8080/treeze/getMindMap?classId="+classId);
				
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
				
				File file = new File(DOWNPATH, classId + ".mm");
				
				FileOutputStream fileOutput = new FileOutputStream(file);
				InputStream inputStream = connection.getInputStream();

				byte[] buffer = new byte[1024];

				int bufferLength = 0;

				fileOutput.write(jsonResultMindmaps.getMindmap().getMindmapXML().getBytes());

				fileOutput.close();
				inputStream.close();
				
				
				mc.load(new File(DOWNPATH + "/" + classId + ".mm"));
				
				Gson gson = new Gson();
				User user = new User();
//				ClassInfo classInfo = new ClassInfo();
				TreezeData treezeData = new TreezeData();
				
				user.setUserType(User.PROFESSOR);
				user.setUserEmail("minsuk@hansung.ac.kr");

				// classInfo 데이터 세팅
				

				// treezeData 데이터 세팅
				treezeData.setDataType(TreezeData.CONNECTIONINFO);
				treezeData.getArgList().add(gson.toJson(user));
				treezeData.getArgList().add(gson.toJson(cInfo));
				
				connectSocket(treezeData);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
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
				System.out.println("server 응답 : Error");
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
		
			
			
//			System.out.println("server 응답 : " + in.readLine());
			
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
		ArrayList<Lecture> lectureList = jonResultlecturelist.getLectures();
		grid.removeAll();
		for(int i=0;i<lectureList.size();i++){
			grid.add(new LectureListItem(lectureList.get(i)));
		}
		
		lectureListPanel.update(lectureListPanel.getGraphics());
		listPanel.updateUI();
	}
	void updateGetallClassList(){
		java.lang.reflect.Type type = new TypeToken<ArrayClass>(){}.getType();
		ArrayClass jonResultlecturelist = (ArrayClass) gson.fromJson(sbResult.toString(), (java.lang.reflect.Type) type);
		ArrayList<ClassInfo> classList = jonResultlecturelist.getClasses();
		grid.removeAll();
		for(int i=0;i<classList.size();i++){
			grid.add(new ClassListItem(classList.get(i)));
		}
		
		lectureListPanel.update(lectureListPanel.getGraphics());
		listPanel.updateUI();
	}
	public void setMainFramevisible(boolean bool){
		setVisible(bool);
	}
	
}
// http://manic.tistory.com/99