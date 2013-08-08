package Frame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.CubicCurve2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.attribute.standard.Finishings;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.ZoneView;

import org.omg.CORBA.portable.OutputStream;

import Data.ArrayLecture;
import Data.ClassInfo;
import Data.CurrentPositionOfNav;
import Data.MindNode;
import Data.TreezeData;
import Data.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class MindMapMain extends JFrame {
	static MindNode md;
	final int NODE_WIDTH = 100;
	final int NODE_HEIGHT = 50;
	final int SCROLLFRAME_WIDTH = 4000;
	final int SCROLLFRAME_HEIGHT = 2000;

	ImageIcon imgIcon;
	Image img;
	Image scaledImage;
	BufferedImage imageBuff;
	ImageIcon resizeIcon;

	private ArrayList<MindNode> nodes = new ArrayList<MindNode>();
	MindNode root;
	MindNode node;
	MindNode node1_1;
	MindNode node1_2;
	MindNode node1_3;
	MindNode node2;
	MindNode node3;
	MindNode node3_1;
	MindNode node3_2;
	MindNode node3_3;
	MindNode node3_4;
	MindNode node4;
	MindNode node4_1;
	MindNode node4_2;
	MindNode node4_3;
	JScrollPane jsp;
	JButton j;
	ScrollPanel nodeScrollPanel = new ScrollPanel();
	CubicCurve2D.Float graph = new CubicCurve2D.Float();
	java.awt.Dimension screenSize;
	ImageIcon rootnodeImgIcon;
	ImageIcon nodeImgIcon;
	ImageIcon nodePassedImgIcon;
	ImageIcon rootnodePassedImgIcon;
	Graphics g;
	ClassInfo classinfo;
	/**
	 * @param args
	 */
	public MindMapMain(String xml2,ClassInfo classinfo) {
		// TODO Auto-generated constructor stub
		screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setTitle("留����㏊");
		this.classinfo = classinfo;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// this.setUndecorated(true);
		// imgIcon = new ImageIcon("/Users/Kunyoung/Desktop/passrootbg.png");
		// img = imgIcon.getImage();
		// scaledImage = img.getScaledInstance(NODE_WIDTH, NODE_HEIGHT,
		// Image.SCALE_SMOOTH);
		// imageBuff = new BufferedImage(1, 1,
		// BufferedImage.TYPE_INT_RGB);
		// g = imageBuff.createGraphics();
		// g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
		ImgIconSet();
		// resizeIcon = new ImageIcon(scaledImage);
		this.getContentPane().setBackground(Color.white);

		// JButton j = new JButton(resizeIcon);

		// g.drawRect(30, 100, 300, 200);

		this.setLayout(new BorderLayout());

		String xml = new String();

		File file = new File("/Users/Kunyoung/Desktop/do.mm");

		byte b[] = new byte[1024];

		try {
			FileInputStream fin = new FileInputStream(file);

			int cnt;
			while ((cnt = fin.read(b)) != -1) {
				String str = new String(b, 0, cnt, "UTF8");
				xml = xml + str;

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XStream xstream = new XStream();
		xstream.processAnnotations(MindMaps.class);

		// String xml = xstream.toXML(joe);
		MindMaps map = (MindMaps) xstream.fromXML(xml2);

		root = new MindNode(map.nodes.get(0).getTEXT(),
				(int) screenSize.getWidth(), (int) screenSize.getHeight());
		nodes.add(root);
		for (int i = 0; i < map.nodes.get(0).nodes.size(); i++) {
			nodetoMindNode(root, map.nodes.get(0).nodes.get(i));
		}

		nodeScrollPanel = new ScrollPanel(); // �ㅽ�濡ㅼ� ���⑤� ��
		jsp = new JScrollPane(nodeScrollPanel); // �ㅽ�濡��⑤���遺�
		this.add(jsp);

		setSize(screenSize.width, screenSize.height);

		SocketThread socketThread = new SocketThread(classinfo);
		socketThread.start();
		setVisible(true);
		nodeScrollPanel.init(); // �ㅽ�濡������� (猷⑦�瑜�以����������);

	}

	void ImgIconSet() {
		imageBuff = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		g = imageBuff.createGraphics();
		img = Toolkit.getDefaultToolkit().getImage("images/nodebg.png");
		scaledImage = img.getScaledInstance(NODE_WIDTH, NODE_HEIGHT * 2 / 3,
				Image.SCALE_SMOOTH);

		g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
		nodeImgIcon = new ImageIcon(scaledImage);
		img = Toolkit.getDefaultToolkit().getImage("images/rootnodebg.png");
		scaledImage = img.getScaledInstance(NODE_WIDTH, NODE_HEIGHT,
				Image.SCALE_SMOOTH);

		g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
		rootnodeImgIcon = new ImageIcon(scaledImage);

		img = Toolkit.getDefaultToolkit().getImage(
				"images/passedrootnodebg.png");
		scaledImage = img.getScaledInstance(NODE_WIDTH, NODE_HEIGHT,
				Image.SCALE_SMOOTH);
		g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
		rootnodePassedImgIcon = new ImageIcon(scaledImage);

		img = Toolkit.getDefaultToolkit().getImage("images/passednodebg.png");
		scaledImage = img.getScaledInstance(NODE_WIDTH, NODE_HEIGHT,
				Image.SCALE_SMOOTH);
		g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
		nodePassedImgIcon = new ImageIcon(scaledImage);

	}

	void reassignmentNode() {
		// removeAll();

	}

	class ScrollPanel extends JPanel {
		private Point pp = new Point();

		public ScrollPanel() {
			// TODO Auto-generated constructor stub
			this.setLayout(null);
			this.setBackground(Color.WHITE);
			this.add(new JButton("dmdk"));
			for (MindNode node : nodes) {
				// if(node==MindNode.getRoot()||node.getParentNode()==MindNode.getRoot()){
				// node.getNodeBtn().setIcon(rootnodeImgIcon);
				// }
				// else{
				// node.getNodeBtn().setIcon(nodeImgIcon);
				// }
				node.getNodeBtn().setBounds(node.getLocateX(),
						node.getLocateY(), NODE_WIDTH, NODE_HEIGHT);

				node.getNodeBtn().setText(node.getNodeStr());
				node.getNodeBtn()
						.setVerticalTextPosition(SwingConstants.CENTER);
				node.getNodeBtn().setHorizontalTextPosition(
						SwingConstants.CENTER);
				node.getNodeBtn().setBackground(new Color(0, 0, 0, 0));
				node.getNodeBtn().setBorderPainted(false);
				node.getNodeBtn().setContentAreaFilled(false);

				this.add(node.getNodeBtn());

			}
			this.setPreferredSize(new Dimension(SCROLLFRAME_WIDTH,
					SCROLLFRAME_HEIGHT));

			final Rectangle rect = new Rectangle();
			final Point move = new Point();
			MindNode node = MindNode.getRoot();
			MouseInputAdapter mia = new MouseInputAdapter() {
				int m_XDifference, m_YDifference;
				boolean m_dragging;

				public void mouseDragged(MouseEvent e) {

					JViewport vport = jsp.getViewport();

					Point cp = e.getPoint();

					Point vp = vport.getViewPosition();
					vp.translate((pp.x - cp.x) / 2, (pp.y - cp.y) / 2);
					scrollRectToVisible(new Rectangle(vp, vport.getSize()));

				}

				public void mousePressed(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
					m_XDifference = e.getX();
					m_YDifference = e.getY();

					pp = e.getPoint();
					// jsp.getHorizontalScrollBar().setValue(4000);
				}

				public void mouseReleased(MouseEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

				}
			};
			addMouseMotionListener(mia);
			addMouseListener(mia);

		}

		public void init() {

			jsp.getHorizontalScrollBar().setValue(
					root.getLocateX() - screenSize.width / 2);
			jsp.getVerticalScrollBar().setValue(
					root.getLocateY() - screenSize.height / 2);
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub

			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			// g2.drawLine(100, 100, 1001, 1010);
			g2.setStroke(new BasicStroke(2));
			for (MindNode node : nodes) {
				if (node.getParentNode() != null) {
					if (node.getDirection() == 0) {
						graph.setCurve((node.getParentNode().getEndX()), node
								.getParentNode().getMiddleY(), (node
								.getParentNode().getEndX()), node
								.getParentNode().getMiddleY(), node
								.getLocateX() - 80, node.getMiddleY(), node
								.getLocateX(), node.getMiddleY());
					} else {

						graph.setCurve(node.getEndX(), 
								node.getMiddleY(), 
								
								node.getEndX()+80 , 
								node.getMiddleY(),
								node.getParentNode().getLocateX(),
								node.getParentNode().getMiddleY(), 
								
								node.getParentNode().getLocateX(),
								
								node.getParentNode().getMiddleY());
						// graph.setCurve(start.x, start.y,
						// start.x + xctrl, start.y,
						// end.x + childXctrl, end.y,
						// end.x, end.y);

						// g.drawLine(10, 10, 100, 100);
						// g2.setStroke(new BasicStroke(2));
						// g2.setColor(Color.RED);
						// g2.drawLine(node.getEndX(), node.getMiddleY(), node
						// .getParentNode().getLocateX(), node
						// .getParentNode().getMiddleY());
					}
					if(node.isPassed()){
					
						g2.setColor(new Color(37, 170, 226));
						g2.setStroke(new BasicStroke(5));
					}
					else{
						
						g2.setColor(new Color(0, 0, 0));
						g2.setStroke(new BasicStroke(1));
					}
					
					g2.draw(graph);

				}
			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//new MindMapMain("");
		 ProfileFrame p = new ProfileFrame();
		// LoginPageFrame l = new LoginPageFrame();
		// System.out.println("zz");
	}

	public void nodetoMindNode(MindNode parent, node child) {
		 if(child.getNODETYPESTR().equals("Question")) return;
		MindNode temp = new MindNode(parent, child.getTEXT(),
				child.getPOSITION());
		nodes.add(temp);
		if (child.nodes != null)
			for (int i = 0; i < child.nodes.size(); i++) {
				nodetoMindNode(temp, child.nodes.get(i));
			}

	}

	class SocketThread extends Thread {

		InputStream inputStream;

		Socket serverSocket;
		ClassInfo classInfo;
		public SocketThread(ClassInfo classInfo) {
			// TODO Auto-generated constructor stub
			this.classInfo = classInfo;
		}

		public void run() {

			String ip = "223.194.158.55";
			int port = 2141;
			Socket socket;
			String str;
			try {
				socket = new Socket(ip, port);
				Gson gson = new Gson();
				User user = new User();
				//ClassInfo classInfo = new ClassInfo();
				TreezeData treezeData = new TreezeData();

				// user µ•¿Ã≈Õ ºº∆√
				user.setUserType(User.STUDENT);
				user.setUserEmail("Kunyoungsin@hansung.ac.kr");

				// classInfo µ•¿Ã≈Õ ºº∆√
				classInfo.setClassName("임베디드");
				
				// treezeData µ•¿Ã≈Õ ºº∆√
				treezeData.setDataType("connectionInfo");
				treezeData.getArgList().add(gson.toJson(user));
				treezeData.getArgList().add(gson.toJson(classInfo));

				// IO Stream ºº∆√
				InputStream is;
				try {
					is = socket.getInputStream();
					java.io.OutputStream os = socket.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os);
					PrintWriter pw = new PrintWriter(osw);
					BufferedReader in = new BufferedReader(
							new InputStreamReader(is));

					// º≠πˆø° ¿¸º€
					pw.println(gson.toJson(treezeData));
					pw.flush();

					// º≠πˆ¿« ¿¿¥‰
					System.out.println("server send : " + in.readLine());

					// ¿œΩ√¡§¡ˆøÎ
					// Scanner scan = new Scanner(System.in);
					// scan.next();
					while (!(str = in.readLine()).equals("null")) {
						java.lang.reflect.Type type = new TypeToken<TreezeData>() {
						}.getType();
						TreezeData jsonResultTreezeData = (TreezeData) gson
								.fromJson(str, (java.lang.reflect.Type) type);
						System.out.println(jsonResultTreezeData.getArgList()
								.get(0));
						if (jsonResultTreezeData.getDataType().equals(
								TreezeData.NAVI)) {

							if (jsonResultTreezeData.getArgList().get(0)
									.equals("start")) {
								MindNode.setNow(MindNode.getRoot());
							} else {

								type = new TypeToken<CurrentPositionOfNav>() {
								}.getType();
								CurrentPositionOfNav naviInfo = (CurrentPositionOfNav) gson
										.fromJson(jsonResultTreezeData
												.getArgList().get(0), type);
								MindNode.setNav(naviInfo);
							}
							repaint();
						} else if (jsonResultTreezeData.getDataType().equals(
								TreezeData.SURVEY)) {
							System.out.println("Survey");
						} else {
							System.out.println("Survey");
						}
						// lectureList = jonResultlecturelist.getLectures();
					}
					System.out.println("socket end");
					pw.close();
					socket.close();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// naviInputStream = naviSocket.getInputStream();
		}
	}
}

@XStreamAlias("map")
class MindMaps {
	@XStreamAsAttribute
	String version;
	@XStreamImplicit(itemFieldName = "node")
	ArrayList<node> nodes;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ArrayList<node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<node> nodes) {
		this.nodes = nodes;
	}

}

@XStreamAlias("node")
class node implements Cloneable {

	@XStreamImplicit(itemFieldName = "node")
	ArrayList<node> nodes;

	@XStreamAsAttribute
	String CREATED;

	@XStreamAsAttribute
	String ID;
	@XStreamAsAttribute
	String MODIFIED;
	@XStreamAsAttribute
	String POSITION;
	@XStreamAsAttribute
	String TEXT;
	@XStreamAsAttribute
	String IMGPATH;
	@XStreamAsAttribute
	String NODETYPESTR;

	public String getNODETYPESTR() {
		return NODETYPESTR;
	}

	public void setNODETYPESTR(String nODETYPESTR) {
		NODETYPESTR = nODETYPESTR;
	}

	public String getIMGPATH() {
		return IMGPATH;
	}

	public void setIMGPATH(String iMGPATH) {
		IMGPATH = iMGPATH;
	}

	public String getCREATED() {
		return CREATED;
	}

	public void setCREATED(String cREATED) {
		CREATED = cREATED;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMODIFIED() {
		return MODIFIED;
	}

	public void setMODIFIED(String mODIFIED) {
		MODIFIED = mODIFIED;
	}

	public String getPOSITION() {
		return POSITION;
	}

	public void setPOSITION(String pOSITION) {
		POSITION = pOSITION;
	}

	public String getTEXT() {
		return TEXT;
	}

	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}
}