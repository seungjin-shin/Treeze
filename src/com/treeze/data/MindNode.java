package com.treeze.data;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.w3c.dom.Node;

import com.treeze.Abstract.ImgBtn;
import com.treeze.draw.PPTParentPanel;
import com.treeze.frame.MindMapMain;
import com.treeze.frame.TicketFrame;

//http://113.198.84.74:8080/treeze/
public class MindNode {
	static ClassInfo classinfo;
	static MindMapMain mindMapMain;
	static MindNode nowPPTNode;
	public static MindNode getNowPPTNode() {
		return nowPPTNode;
	}

	public static void setNowPPTNode(MindNode nowPPTNode) {
		MindNode.nowPPTNode = nowPPTNode;
	}

	public static ClassInfo getClassinfo() {
		return classinfo;
	}

	public static void setClassinfo(ClassInfo classinfo) {
		MindNode.classinfo = classinfo;
	}

	static MindNode root;
	static MindNode now;
	MindNode next;
	boolean existTicket = false;
	MindNode prev;
	boolean passed;
	final int NODE_WIDTH = 80;
	final int NODE_HEIGHT = 40;
	Image img;
	Image scaledImage;
	String imgPath;

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	BufferedImage imageBuff;
	JButton pptBtn = new JButton();
	JButton ticketBtn = new JButton();
	HideBtnThread h;
	String nodeID;

	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}

	public JButton getPptBtn() {
		return pptBtn;
	}

	public void setPptBtn(JButton pptBtn) {
		this.pptBtn = pptBtn;
	}

	public JButton getTicketBtn() {
		return ticketBtn;
	}

	public void setTicketBtn(JButton ticketBtn) {
		this.ticketBtn = ticketBtn;
	}

	static ImageIcon rootnodeImgIcon;
	static ImageIcon nodeImgIcon;
	static ImageIcon nodePassedImgIcon;
	static ImageIcon rootnodePassedImgIcon;
	Graphics g;
	// ArrayList<Notes> notes = new ArrayList<Notes>();

	static final int DISTANT = 50;
	MindNode parentNode;
	ArrayList<MindNode> childeNodes = new ArrayList<MindNode>();
	ArrayList<MindNode> LchildeNodes = new ArrayList<MindNode>();
	ArrayList<MindNode> RchildeNodes = new ArrayList<MindNode>();

	public NodeBtn nodeBtn = new NodeBtn(TreezeStaticData.NODE_BTN_IMG,
			TreezeStaticData.NODE_PRESS_IMG, TreezeStaticData.NODE_ENTER_IMG);

	public NodeBtn getNodeBtn() {
		return nodeBtn;
	}

	public void setNodeBtn(NodeBtn nodeBtn) {
		this.nodeBtn = nodeBtn;

	}

	int ChildCount = 0;
	int ChildLCount = 0;
	int ChildRCount = 0;

	int index;
	int scaleX;
	
	int scaleY;
	int absoluteIndex;
	int locateX;
	int locateY;
	int endX;
	int endY;
	int middleY;
	String nodeStr;
	int direction; // 0 = right 1 = left

	public MindNode() {

	}
	public int getScaleX() {
		return scaleX;
	}

	public void setScaleX(int scaleX) {
		this.scaleX = scaleX;
	}

	public MindNode(String nodeID, String str, int x, int y,
			MindMapMain mindMapMain) { // ���������������
		this.nodeStr = str;
		this.mindMapMain = mindMapMain;
		this.locateX = x;
		this.locateY = y;
		this.nodeID = nodeID;
		this.scaleX = str.length() * 18;
		this.scaleY = NODE_HEIGHT;
		setendX();
		//
		 endX = locateX + scaleX;
		middleY = locateY + NODE_HEIGHT / 2;
		endY = locateY + NODE_HEIGHT;
		root = this;
		now = this;
		ImgIconSet();

//		pptBtn.addMouseListener(new MouseListener() {
//
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				h.getContainer().remove(getnode().getPptBtn());
//				h.getContainer().remove(getnode().getTicketBtn());
//				h.getContainer().repaint();
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				if (h.getNode() == getnode()) {
//					h.stop();
//				}
//			}
//
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				// new PPTFrame(getnode());
//			}
//		});
//		ticketBtn.addMouseListener(new MouseListener() {
//
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				h.getContainer().remove(getnode().getPptBtn());
//				h.getContainer().remove(getnode().getTicketBtn());
//				h.getContainer().repaint();
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				if (h.getNode() == getnode()) {
//					h.stop();
//				}
//			}
//
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				System.out.println(classinfo.getClassName());
//				// new TicketFrame(getnode(),classinfo);
//				if (TreezeStaticData.TICKETFRAME != null) {
//					TreezeStaticData.TICKETFRAME.setVisible(false);
//					TreezeStaticData.TICKETFRAME.disable();
//				}
//				TreezeStaticData.TICKETFRAME = new TicketFrame(getnode(),
//						classinfo);
//
//			}
//		});
	}

	public MindNode(MindNode parentNode, String nodeID, String str,
			String directionstr, String imgPath) { // ��������������		// ��������������		// TODO Auto-generated constructor stub
		nodeStr = str;
		now.setNext(this);
		this.setPrev(now);
		MindNode.now = this;
		this.nodeID = nodeID;
		// this.scaleX = str.length() * 20;
		this.scaleY = NODE_HEIGHT;
		this.imgPath = imgPath;
		if (directionstr == null) {
			this.direction = parentNode.direction;
		} else if (directionstr.equals("left")) {
			this.direction = 1;
		} else {
			this.direction = 0;
		}
		this.scaleX = str.length() * 18;
		if(scaleX<100){
			this.scaleX = 100;
		}
	//	nodeBtn.addMouseListener(new NodeMouseListener());
		this.setParentNode(parentNode);
		setLocate(parentNode);
		parentNode.childeNodes.add(this);
		parentNode.ChildeList(direction).add(this);
		index = this.parentNodeChildCount();
		this.absoluteIndex = parentNode.ChildCount;
		parentNode.ChildCount++;
		this.paretNodeChildCntAdd();
		this.endX = this.locateX + scaleX;
		middleY = locateY +NODE_HEIGHT / 2;
		endY = locateY + scaleY;
		ImgIconSet();

//		pptBtn.addMouseListener(new MouseListener() {
//
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				h.getContainer().remove(getnode().getPptBtn());
//				h.getContainer().remove(getnode().getTicketBtn());
//				h.getContainer().repaint();
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				if (h.getNode() == getnode()) {
//					h.stop();
//				}
//			}
//
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				// new PPTFrame(getnode());
//			}
//		});
//		ticketBtn.addMouseListener(new MouseListener() {
//
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				h.getContainer().remove(getnode().getPptBtn());
//				h.getContainer().remove(getnode().getTicketBtn());
//				h.getContainer().repaint();
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				if (h.getNode() == getnode()) {
//					h.stop();
//				}
//			}
//
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				System.out.println(classinfo.getClassName());
//				// new TicketFrame(getnode(),classinfo);
//				if (TreezeStaticData.TICKETFRAME != null) {
//					TreezeStaticData.TICKETFRAME.setVisible(false);
//					TreezeStaticData.TICKETFRAME.disable();
//				}
//				TreezeStaticData.TICKETFRAME = new TicketFrame(getnode(),
//						classinfo);
//
//			}
//		});
	}

	// public static void setNav(CurrentPositionOfNav naviInfo) {
	// // TODO Auto-generated method stub
	// MindNode temp = root;
	// for (int i = 0; i < naviInfo.getPosition().size(); i++) {
	// temp = temp.childeNodes.get(naviInfo.getPosition().get(i) - 1);
	// }
	// temp.setPassed(true);
	// temp.setNow(temp);
	// }
	void ImgIconSet() {
		imageBuff = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

		g = imageBuff.createGraphics();
		img = TreezeStaticData.NODE_BG;
		scaledImage = img.getScaledInstance(NODE_WIDTH, NODE_HEIGHT * 2 / 3,
				Image.SCALE_SMOOTH);

		g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
		nodeImgIcon = new ImageIcon(scaledImage);
		img = TreezeStaticData.ROOT_NODE_BG;
		scaledImage = img.getScaledInstance(NODE_WIDTH, NODE_HEIGHT,
				Image.SCALE_SMOOTH);

		g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
		rootnodeImgIcon = new ImageIcon(scaledImage);

		img = TreezeStaticData.PASSED_ROOT_NODE_BG;
		scaledImage = img.getScaledInstance(NODE_WIDTH, NODE_HEIGHT,
				Image.SCALE_SMOOTH);
		g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
		rootnodePassedImgIcon = new ImageIcon(scaledImage);

		img = TreezeStaticData.PASSED_NODE_BG;
		scaledImage = img.getScaledInstance(NODE_WIDTH, NODE_HEIGHT * 2 / 3,
				Image.SCALE_SMOOTH);
		g.drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
		nodePassedImgIcon = new ImageIcon(scaledImage);

	}

	public String getPosition() {
		// TODO Auto-generated method stub

		String position = Integer.toString(absoluteIndex);
		MindNode temp = parentNode;
		while (!(this.equals(root)) && !temp.equals(root)) {
			position = Integer.toString(temp.absoluteIndex) + "/" + position;
			temp = temp.parentNode;
		}
		return position;
	}

	public void setendX() {
		// TODO Auto-generated method stub
		endX = locateX + NODE_WIDTH;
	}

	private ArrayList<MindNode> ChildeList(int direction) {
		// TODO Auto-generated method stub
		if (direction == 0)
			return this.RchildeNodes;
		else
			return this.LchildeNodes;
	}

	private void paretNodeChildCntAdd() {
		if (this.direction == 0)
			this.getParentNode().ChildRCount++;
		else
			this.getParentNode().ChildLCount++;
	}

	private int parentNodeChildCount() {
		// TODO Auto-generated method stub
		if (this.direction == 0)
			return this.getParentNode().ChildRCount;
		else
			return this.getParentNode().ChildLCount;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public int getMiddleY() {
		return middleY;
	}

	public void setMiddleY(int middleY) {
		this.middleY = middleY;
	}

	private int getChildCnt() {
		if (this.direction == 0)
			return this.ChildRCount;
		else
			return this.ChildLCount;
	}

	private MindNode getChildeAt(MindNode parentNode, int index, int direction) {
		if (this.direction == 0)
			return parentNode.RchildeNodes.get(index);
		else
			return parentNode.LchildeNodes.get(index);

	}

	private void setLocateY_UP() {
		this.locateY = this.locateY - DISTANT / 2;
		middleY = locateY + scaleY / 2;
		endY = locateY + scaleY;
	}

	private void setLocateY_DOWN() {
		this.locateY = this.locateY + DISTANT / 2;
		middleY = locateY + scaleY / 2;
		endY = locateY + scaleY;
	}

	private void setLocate(MindNode parentNode) {
		// TODO Auto-generated method stub
		if (this.direction == 0)
			this.locateX = parentNode.endX + DISTANT; // right
		else
			// left
			this.locateX = parentNode.locateX - DISTANT - scaleX;

		if (this.parentNodeChildCount() == 0) {
			this.locateY = parentNode.locateY;
		} else {

			this.allParentsetChageLocate(parentNode);

			for (int i = 0; i < this.parentNodeChildCount(); i++) {
				getChildeAt(parentNode, i, direction).setLocateY_UP();
				getChildeAt(parentNode, i, direction).allChildsetLocateUP();
			}
			MindNode temp = getChildeAt(parentNode,
					this.parentNodeChildCount() - 1, direction)
					.getLastChildNode();
			this.locateY = temp.locateY + DISTANT;
		}

	}

	private MindNode getLastChildNode() {
		MindNode temp = this;
		while (temp.getChildCnt() != 0) {
			temp = temp.getChildeAt(temp, temp.getChildCnt() - 1, direction);
		}
		return temp;
	}

	public void getAllTicket(ArrayList<MindNode> nodes, MindNode node) {
		for (int i = 0; i < node.getChildeNodes().size(); i++) {
			if (node.getChildeNodes().get(i) instanceof Ticket) {
				nodes.add(node.getChildeNodes().get(i));

				if (node.getChildeNodes().get(i).getChildeNodes().size() != 0) {
					getAllTicket(nodes, node.getChildeNodes().get(i));
				}
			}

		}

	}

	private void allParentsetChageLocate(MindNode parentNode) {
		if (parentNode != getRoot()) {
			for (int i = 0; parentNode.parentNodeChildCount() > i; i++) {
				if (i < parentNode.index) {
					getChildeAt(parentNode.getParentNode(), i, direction)
							.setLocateY_UP();
					getChildeAt(parentNode.getParentNode(), i, direction)
							.allChildsetLocateUP();
				} else if (i > parentNode.index) {
					getChildeAt(parentNode.getParentNode(), i, direction)
							.setLocateY_DOWN();
					getChildeAt(parentNode.getParentNode(), i, direction)
							.allChildsetLocateDOWN();
				} else {

				}
			}
			allParentsetChageLocate(parentNode.getParentNode());
		}
	}

	private void allChildsetLocateUP() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getChildCnt(); i++) {
			getChildeAt(this, i, direction).setLocateY_UP();
			if (getChildeAt(this, i, direction).getChildCnt() != 0) {
				getChildeAt(this, i, direction).allChildsetLocateUP();
			}
		}
	}

	private void allChildsetLocateDOWN() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getChildCnt(); i++) {
			getChildeAt(this, i, direction).setLocateY_DOWN();
			if (getChildeAt(this, i, direction).getChildCnt() != 0) {
				getChildeAt(this, i, direction).allChildsetLocateDOWN();
			}
		}
	}

	public MindNode clickNode(int x, int y) {

		if (this.locateX < x && locateY < y && endX > x && endY > y) {

			return this;
		}
		return null;

	}

	public static MindNode getRoot() {
		return root;
	}

	public static void setRoot(MindNode mindNode) {
		MindNode.root = mindNode;
	}

	public MindNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(MindNode parentNode) {
		this.parentNode = parentNode;
	}

	public int getLocateX() {
		return locateX;
	}

	public void setLocateX(int locateX) {
		this.locateX = locateX;
	}

	public int getLocateY() {
		return locateY;
	}

	public void setLocateY(int locateY) {
		this.locateY = locateY;
	}

	public String getNodeStr() {
		return nodeStr;
	}

	public void setNodeStr(String nodeStr) {
		this.nodeStr = nodeStr;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public MindNode getnode() {
		return this;
	}

	public int getScaleY() {
		return scaleY;
	}

	public static MindNode getNow() {

		return now;
	}

	public static void setNow(MindNode now) {
		if (MindNode.now == null) {
			return;
		} else {
			if (MindNode.now == getRoot()) {
				// MindNode.now.getNodeBtn().setIcon(rootnodeImgIcon);
			} else {
				// MindNode.now.getNodeBtn().setIcon(nodeImgIcon);
			}
		}
		MindNode.now = now;
		if (now == getRoot()) {
			now.getNodeBtn().ChangeNodeImg(TreezeStaticData.PASSED_NODE_BG,
					TreezeStaticData.PASSED_NODE_BG,
					TreezeStaticData.PASSED_NODE_BG);
		} else {

			now.getNodeBtn().ChangeNodeImg(TreezeStaticData.PASSED_NODE_BG,
					TreezeStaticData.PASSED_NODE_BG,
					TreezeStaticData.PASSED_NODE_BG);
		}
		now.getNodeBtn().repaint();
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {

		this.passed = passed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public static MindNode getNodeuseNodeID(MindNode node, String nodeID) {
		MindNode temp = null;
		if(node.getNodeID().equals(nodeID)){
			return node;
		}
		for (int i = 0; i < node.getChildeNodes().size(); i++) {
			// if(node.getChildeNodes().get(i) instanceof Ticket) continue;
			if (node.getChildeNodes().get(i).getNodeID().equals(nodeID)) {
				return node.getChildeNodes().get(i);
			} else if (node.getChildeNodes().get(i).getChildeNodes().size() != 0) {
				temp = getNodeuseNodeID(node.getChildeNodes().get(i), nodeID);
				if (temp != null) {
					return temp;
				}
			}
		}
		return temp;

	}

	public boolean isExistTicket() {
		return existTicket;
	}

	public void setExistTicket(boolean existTicket) {
		this.existTicket = existTicket;
	}

	public MindNode getNext() {
		return next;
	}

	public void setNext(MindNode next) {
		this.next = next;
	}

	public MindNode getPrev() {
		return prev;
	}

	public void setPrev(MindNode prev) {
		this.prev = prev;
	}

	public int getChildCount() {
		return ChildCount;
	}

	public void setChildCount(int childCount) {
		ChildCount = childCount;
	}

	public ArrayList<MindNode> getChildeNodes() {
		return childeNodes;
	}

	public void setChildeNodes(ArrayList<MindNode> childeNodes) {
		this.childeNodes = childeNodes;
	}

	public static void setNav(MindNode node) {
		// TODO Auto-generated method stub
		MindNode now = node;


		now.setPassed(true);
		MindNode.getNow()
				.getNodeBtn()
				.ChangeNodeImg(TreezeStaticData.NODE_BTN_IMG,
						TreezeStaticData.NODE_PRESS_IMG,
						TreezeStaticData.NODE_ENTER_IMG);

		MindNode.setNow(now);
	}

//	class NodeMouseListener implements MouseListener {
//
//		public void NodeMouseListener(MouseEvent e) {
//
//		}
//
//		public void mouseExited(MouseEvent e) {
//
//			// h = new HideBtnThread(getnode().getNodeBtn().getParent(),
//			// getnode());
//			// h.start();
//		}
//
//		public void mousePressed(MouseEvent e) {
//
//		}
//
//		public void mouseReleased(MouseEvent e) {
//		}
//
//		public void mouseClicked(MouseEvent e) {
//			// new PPTFrame();
//			// new TicketFrame(getnode(),classinfo);
//			mindMapMain.getMainFrameManager().changeTopPanel(getnode());
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent arg0) {
//			// TODO Auto-generated method stub
//			// nodeBtn.setLayout(null);
//			//
//			// System.out.println("占쎈�占�占쏙옙占쏙옙占� + getNodeID());
//			// getnode().getNodeBtn().getParent().add(getnode().pptBtn);
//			// getnode().getNodeBtn().getParent().add(getnode().ticketBtn);
//			// getnode().getNodeBtn().getParent()
//			// .setComponentZOrder(getnode().pptBtn, 1);
//			// getnode().getNodeBtn().getParent()
//			// .setComponentZOrder(getnode().ticketBtn, 2);
//			// getnode().getNodeBtn().getParent().repaint();
//			// if (h != null && h.getNode() == getnode()) {
//			// h.stop();
//			// }
//
//		}
//	}

	public class NodeBtn extends ImgBtn {

		public NodeBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			// TODO Auto-generated method stub
			if(!MindNode.getNowPPTNode().equals(getnode()))
				mindMapMain.getMainFrameManager().changeTopPanel(getnode());
			
		}

	}

	class HideBtnThread extends Thread {
		Container container;
		MindNode node;

		public MindNode getNode() {
			return node;
		}

		public void setNode(MindNode node) {
			this.node = node;
		}

		public Container getContainer() {
			return container;
		}

		public void setContainer(Container container) {
			this.container = container;
		}

		public HideBtnThread(Container container, MindNode node) {
			// TODO Auto-generated constructor stub
			this.container = container;
			this.node = node;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			int a = 0;
			try {
				sleep(1000);
				container.remove(node.getPptBtn());
				container.remove(node.getTicketBtn());
				container.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public int DepthofTicket() {
		int depth = 0;
		MindNode temp = this;
		while (temp instanceof Ticket) {
			temp = temp.getParentNode();
			depth++;
		}

		return depth;
	}
}
