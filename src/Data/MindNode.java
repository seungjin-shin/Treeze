package Data;

import java.awt.Color;
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

import Frame.TicketFrame;

//http://113.198.84.74:8080/treeze/
public class MindNode {
	static MindNode root;
	static MindNode now;
	MindNode next;
	boolean existTicket = false;
	MindNode prev;
	boolean passed;
	final int NODE_WIDTH = 100;
	final int NODE_HEIGHT = 50;
	Image img;
	Image scaledImage;
	BufferedImage imageBuff;
	static ImageIcon rootnodeImgIcon;
	static ImageIcon nodeImgIcon;
	static ImageIcon nodePassedImgIcon;
	static ImageIcon rootnodePassedImgIcon;
	Graphics g;
	// ArrayList<Notes> notes = new ArrayList<Notes>();

	static final int DISTANT = 80;
	MindNode parentNode;
	ArrayList<MindNode> childeNodes = new ArrayList<MindNode>();
	ArrayList<MindNode> LchildeNodes = new ArrayList<MindNode>();
	ArrayList<MindNode> RchildeNodes = new ArrayList<MindNode>();

	JButton nodeBtn = new JButton();

	public JButton getNodeBtn() {
		return nodeBtn;
	}

	public void setNodeBtn(JButton nodeBtn) {
		this.nodeBtn = nodeBtn;

	}

	int ChildCount = 0;
	int ChildLCount = 0;
	int ChildRCount = 0;

	int index;

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

	public MindNode(String str, int x, int y) { // ï¿½ï¿½Æ®ï¿½ï¿½ï¿½ï¿½
		this.nodeStr = str;

		this.locateX = x;
		this.locateY = y;

		// this.scaleX = str.length() * 20;
		this.scaleY = 50;
		setendX();
		nodeBtn.addMouseListener(new NodeMouseListener());
		// endX = locateX + scaleX;
		middleY = locateY + scaleY / 2;
		endY = locateY + scaleY;
		root = this;
		now = this;
		ImgIconSet();
		getNodeBtn().setIcon(rootnodeImgIcon);

	}

	public MindNode(MindNode parentNode, String str, String directionstr) { // ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
																			// ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		// TODO Auto-generated constructor stub
		nodeStr = str;
		now.setNext(this);
		this.setPrev(now);
		this.setNow(this);

		// this.scaleX = str.length() * 20;
		this.scaleY = 50;
		if (directionstr == null) {
			this.direction = parentNode.direction;
		} else if (directionstr.equals("left")) {
			this.direction = 1;
		} else {
			this.direction = 0;
		}
		nodeBtn.addMouseListener(new NodeMouseListener());
		this.setParentNode(parentNode);
		setLocate(parentNode);
		parentNode.childeNodes.add(this);
		parentNode.ChildeList(direction).add(this);
		index = this.parentNodeChildCount();
		this.absoluteIndex = parentNode.ChildCount;
		parentNode.ChildCount++;
		this.paretNodeChildCntAdd();
		this.endX = this.locateX + 100;
		middleY = locateY + scaleY / 2;
		endY = locateY + scaleY;
		ImgIconSet();
		getNodeBtn().setIcon(nodeImgIcon);
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
		endX = locateX + 100;
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
			this.locateX = parentNode.endX + DISTANT;
		else
			this.locateX = parentNode.locateX - DISTANT - 100;

		if (this.parentNodeChildCount() == 0) { // ºÎ¸ð ÀÚ½ÄÀÌ ¾ø¾úÀ» °æ¿ì ÀÏÁ÷¼± »ó¿¡ À§Ä¡ÇÏ°ÔÇÔ.
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
		} else {
			if (MindNode.now == getRoot()) {
				MindNode.now.getNodeBtn().setIcon(rootnodeImgIcon);
			} else {
				MindNode.now.getNodeBtn().setIcon(nodeImgIcon);
			}
		}
		MindNode.now = now;
		if (now == getRoot()) {
			now.getNodeBtn().setIcon(rootnodePassedImgIcon);
		} else {
			now.getNodeBtn().setIcon(nodePassedImgIcon);
		}
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

	public static MindNode getNode(String position) {
		String[] splitStr = position.split("/");
		MindNode temp = MindNode.getRoot();
		for (int i = 0; i < splitStr.length - 1; i++) {
			temp = temp.getChildeNodes().get(Integer.parseInt(splitStr[i]));
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

	public static void setNav(CurrentPositionOfNav naviInfo) {
		// TODO Auto-generated method stub
		MindNode temp = root;
		for (int i = 0; i < naviInfo.getPosition().size(); i++) {
			temp = temp.childeNodes.get(naviInfo.getPosition().get(i));
		}

		temp.setPassed(true);
		temp.getNodeBtn().setIcon(nodePassedImgIcon);
		temp.setNow(temp);
	}

	class NodeMouseListener implements MouseListener {

		public void NodeMouseListener(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
			System.out.println(getNodeStr());
			new TicketFrame(getnode());

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			// nodeBtn.setLayout(null);

			// fullNameAlert.setText(nodeStr);
			// fullNameAlert.setLocation(getLocateX(), getLocateY());
			// fullNameAlert.setSize(100,100);

			// nodeBtn.add(fullNameAlert);

		}
	}

}