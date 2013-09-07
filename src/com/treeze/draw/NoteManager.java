package com.treeze.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.google.gson.Gson;
import com.treeze.data.ClassInfo;
import com.treeze.data.JsonTicket;
import com.treeze.data.MindNode;
import com.treeze.data.Ticket;
import com.treeze.data.TreezeStaticData;
import com.treeze.data.User;
import com.treeze.downloadthread.DownLoadAllTicket;
import com.treeze.downloadthread.DownLoadNote;

/*
 * NoteManager객체 필기에 해당되는 부분은 NoteManager에서 관장한다.
 */
public class NoteManager {

	private ArrayList<DrawableObject> drawobjList;
	private ArrayList<ComponentJPanel> componentList;

	protected Path path;
	private JPanel jpanel;

	protected FigureObject figureObj;

	protected Gson gson;
	protected FileIOManager fim;

	protected static final int IMG_TYPE_STAR = 0;

	public static final int IMG_SIZE_NO_DECIDED = -1;

	public static Image STAR_IMG;

	private NoteManager nm;
	
	public synchronized void addComponentJPanel(ComponentJPanel componentJPanel) {
		if(componentList == null) {
			componentList = new ArrayList<ComponentJPanel>();
		}
		componentList.add(componentJPanel);
	}
	
	public synchronized void addDrawableObject(DrawableObject drawableObject) {
		if(drawobjList == null) {
			drawobjList = new ArrayList<DrawableObject>();
		}
		drawobjList.add(drawableObject);
	}

	protected NoteManager(JPanel jpanel) {
		// TODO Auto-generated constructor stub
		this.drawobjList = new ArrayList<DrawableObject>();
		this.componentList = new ArrayList<ComponentJPanel>();
		this.jpanel = jpanel;

		gson = new Gson();
		fim = new FileIOManager();

		nm = this;

		jpanel.addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				nm.setRelativeLocation();
				nm.repaint();

			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	protected void initPath() {

		path = new Path();

	}

	protected void makePath(LinePoint point, Color color, BasicStroke bs) {

		path.setBs(bs);
		path.setColor(color);
		// path.getPoints().add(point);
		path.points.add(point);
		repaint();

	}

	protected void makePathComplete() {
//		drawobjList.add(new LineObject(this.path, jpanel.getWidth(), jpanel
//				.getHeight()));
		addDrawableObject(new LineObject(this.path, jpanel.getWidth(), jpanel
				.getHeight()));
		this.path = null;
		// System.out.println("drawobj size : " + drawobjList.size());
	}

	protected void makeFigure(int x, int y, int width, int height, int type) {
		this.figureObj = new FigureObject(x, y, width, height,
				jpanel.getWidth(), jpanel.getHeight(), type);
		repaint();

	}

	protected void makeFigureComplete() {

		addDrawableObject(this.figureObj);
		this.figureObj = null;
		// System.out.println("drawobj size : " + drawobjList.size());
	}

	public synchronized void setFeatureByRate() {
		for (int i = 0; i < drawobjList.size(); i++) {
			drawobjList.get(i).setFeatureByRate(jpanel.getWidth(),
					jpanel.getHeight());
		}
		for (int i = 0; i < componentList.size(); i++) {
			componentList.get(i).setFeatureByRate(jpanel.getWidth(),
					jpanel.getHeight());
		}
	}

	public void restore() {
		drawAll(jpanel.getGraphics());
		addAllToPanel();
	}

	protected void drawAll(Graphics g) {

		for (int i = 0; i < drawobjList.size(); i++) {

			drawobjList.get(i).draw(g, jpanel);

		}

	}

	protected void addAllToPanel() {
		for (int i = 0; i < componentList.size(); i++) {

			ComponentJPanel component = componentList.get(i);
			component.addToPanel(jpanel, this);
		}
	}

	protected void drawLineByRealTime(Graphics g) {

		if (path != null) {

			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(path.getColor());
			g2.setStroke(path.getBs());

			for (int i = 0; i < path.getPoints().size() - 1; i++) {
				g.drawLine(path.getPoints().get(i).x,
						path.getPoints().get(i).y,
						path.getPoints().get(i + 1).x,
						path.getPoints().get(i + 1).y);
			}

		}

	}

	protected void drawFigureByRealTime(Graphics g) {

		// System.out.println(figureObj);

		if (figureObj != null) {
			figureObj.draw(g, jpanel);
		}

	}


	protected void drawImage(int x, int y, double width, double height, int type) {
		// if(IMG_SIZE_WIDTH != IMG_SIZE_NO_DECIDED) {
		// width = IMG_SIZE_WIDTH;
		// height = IMG_SIZE_HEIGHT;
		// }
		
		addDrawableObject(new ImageObject(x, y, (int) width, (int) height, jpanel
				.getWidth(), jpanel.getHeight(), type));
		repaint();
	}

	public synchronized void repaint() {

		jpanel.repaint();

	}

	protected void addPostIt(int x, int y, int width, int height) {

		PostItPanel postItPanel = new PostItPanel(x, y, width, height,
				jpanel.getWidth(), jpanel.getHeight());

		jpanel.add(postItPanel);
		jpanel.validate();

//		componentList.add(postItPanel);
		addComponentJPanel(postItPanel);
		repaint();

	}

	protected void addMemo(int x, int y, int width, int height) {

		MemoPanel memo = new MemoPanel(x, y, width, height, jpanel.getWidth(),
				jpanel.getHeight());
		memo.addToPanelByClick(jpanel, this);
//		componentList.add(memo);
		addComponentJPanel(memo);
		repaint();

	}

	protected void removeLastDrawableObj() {

		drawobjList.remove(drawobjList.size() - 1);

	}

	protected void loadNote(String nodeId) {
		
		StoredNoteObject sno = null;
		 
//		try {
//			sno = loadStoredNote(nodeId);
//			componentList = sno.getCOList();
//			drawobjList = sno.getDOList();
//			setFeatureByRate();
//			// 패널과 그림을 다시 만들고
//			restore();
//			repaint();
//			System.out.println("저장된 필기를 로컬에서 가져옵니다.");
//
//
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//			System.out.println("저장된 필기가없습니다. 네트워크에서 가져옵니다.");
//			User user= User.getInstance();	
//			ClassInfo ci = ClassInfo.getInstance();
//			new DownLoadNote("1", user.getUserEmail(), nodeId, this).start();
//		
//		}
		
		User user= User.getInstance();	
		ClassInfo ci = ClassInfo.getInstance();
		new DownLoadNote("1", user.getUserEmail(), nodeId, this).start();
		

		
		

	}
	
	

	protected StoredNoteObject loadStoredNote(String nodeId) throws IOException {

		String noteData = null;

		noteData = fim.read(Util.NOTE_ADDR + nodeId + ".txt");

		return gson.fromJson(noteData, StoredNoteObject.class);

	}
	protected String getStoredNote(String nodeId) {
		
		StoredNoteObject sno = new StoredNoteObject(drawobjList, componentList);
		String noteContent = gson.toJson(sno);
		return noteContent;
		
	}

	protected void saveStoredNote(String nodeId) {
		System.out.println("saveStoredNote : " + componentList.size());

		StoredNoteObject sno = new StoredNoteObject(drawobjList, componentList);
		String noteContent = gson.toJson(sno);

		try {
			fim.write(Util.NOTE_ADDR + nodeId + ".txt", noteContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void setClick(int x, int y) {
		setUnClicked();
		for (int i = 0; i < drawobjList.size(); i++) {
			// System.out.println("asdfadsfasdfsdasdafasdfasdfsadf");
			drawobjList.get(i).setClick(x, y, this);

		}
		repaint();

	}

	protected boolean isClickableItem(int x, int y) {
		for (int i = 0; i < drawobjList.size(); i++) {
			if (drawobjList.get(i).isClick(x, y, this)) {
				return true;
			}
		}

		for (int i = 0; i < drawobjList.size(); i++) {
			if (drawobjList.get(i).isClick(x, y, this)) {
				return true;
			}
		}

		return false;

	}

	protected void removeSelectedItem() {
		for (int i = 0; i < drawobjList.size(); i++) {
			drawobjList.get(i).removeSelectedItem(this);
		}

		for (int i = 0; i < componentList.size(); i++) {
			// System.out.println("코맥이");
			componentList.get(i).removeSelectedItem(this);
		}
		repaint();
	}

	protected void setUnClicked() {
		// System.out.println("unclick size : " +drawobjList.size());
		for (int i = 0; i < drawobjList.size(); i++) {
			drawobjList.get(i).setUnClicked(this);
		}

		for (int i = 0; i < componentList.size(); i++) {
			// System.out.println("코맥이");
			componentList.get(i).setUnClicked(this);
		}
		repaint();
	}

	protected void setRelativeLocation() {

		for (int i = 0; i < drawobjList.size(); i++) {
			drawobjList.get(i).setRelativeLocation(this);
		}

		for (int i = 0; i < componentList.size(); i++) {

			componentList.get(i).setRelativeLocation(this);
		}

	}

	protected void setMoveFlag(int x, int y) {
		initMoveFlag();
		for (int i = 0; i < drawobjList.size(); i++) {
			if (drawobjList.get(i).isClick(x, y, this) && drawobjList.get(i).clickPanel == null) {
				drawobjList.get(i).setMoveFlag(true);
				// System.out.println("asfasdfasfasdfasfasdf");
				return;
			}
		}
	}

	protected boolean isMoveFlag() {

		for (int i = 0; i < drawobjList.size(); i++) {
			if (drawobjList.get(i).isMoveFlag()) {
				return true;
			}
		}
		return false;
	}

	protected void initMoveFlag() {
		for (int i = 0; i < drawobjList.size(); i++) {
			drawobjList.get(i).setMoveFlag(false);
			;

		}
	}

	protected void move(int pressX, int pressY, int dragX, int dragY) {
		for (int i = 0; i < drawobjList.size(); i++) {
			if (drawobjList.get(i).isMoveFlag()) {
				drawobjList.get(i).move(pressX, pressY, dragX, dragY, nm);
			}
		}
		repaint();
	}

	protected ArrayList<DrawableObject> getDrawobjList() {
		return drawobjList;
	}

	protected void setDrawobjList(ArrayList<DrawableObject> drawobjList) {
		this.drawobjList = drawobjList;
	}

	protected ArrayList<ComponentJPanel> getComponentList() {
		return componentList;
	}

	protected void setComponentList(ArrayList<ComponentJPanel> componentList) {
		this.componentList = componentList;
	}

	protected JPanel getJpanel() {
		return jpanel;
	}

	protected void setJpanel(JPanel jpanel) {
		this.jpanel = jpanel;
	}

}

