package com.treeze.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.google.gson.Gson;

/*
 * NoteManager객체 필기에 해당되는 부분은 NoteManager에서 관장한다.
 */
public class NoteManager {

	protected ArrayList<DrawableObject> drawobjList;
	protected ArrayList<ComponentJPanel> componentList;
	
	Path path;
	private JPanel jpanel;


	FigureObject figureObj;

	Gson gson;
	FileIOManager fim;
	
	public static final int IMG_TYPE_STAR = 0;
	

	public NoteManager(JPanel jpanel) {
		// TODO Auto-generated constructor stub
		this.drawobjList = new ArrayList<DrawableObject>();
		this.componentList = new ArrayList<ComponentJPanel>();
		this.jpanel = jpanel;

		gson = new Gson();
		fim = new FileIOManager();

	}

	protected void initPath() {

		path = new Path();

	}

	protected void makePath(Point point, Color color, BasicStroke bs) {

		path.setBs(bs);
		path.setColor(color);
		path.getPoints().add(point);
		repaint();

	}

	protected void makePathComplete() {
		drawobjList.add(new LineObject(this.path));
		this.path = null;
//		System.out.println("drawobj size : " + drawobjList.size());
	}

	protected void makeFigure(int x, int y, int width, int height, int type) {
		this.figureObj = new FigureObject(x, y, width, height, type);
		repaint();

	}

	protected void makeFigureComplete() {
		drawobjList.add(this.figureObj);
		this.figureObj = null;
//		System.out.println("drawobj size : " + drawobjList.size());
	}
	
	public void restore() {
		drawAll(jpanel.getGraphics());
		addAllToPanel();
	}

	protected void drawAll(Graphics g) {
		
		for (int i = 0; i < drawobjList.size(); i++) {
			System.out.println("draw");

			drawobjList.get(i).draw(g, jpanel);

		}

	}
	
	protected void addAllToPanel() {
		for (int i = 0; i < componentList.size(); i++) {

			ComponentJPanel component = componentList.get(i);
			component.addToPanel(jpanel, this);
		}
	}



	protected void drawLine(Path path) {

		drawobjList.add(new LineObject(path));
		repaint();
	}

	protected void drawLineByRealTime(Graphics g) {

		if (path != null) {
			System.out.println("sex");
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

		if (figureObj != null) {
			figureObj.draw(g, jpanel);
		}

	}

	protected void drawFigure(int x, int y, int width, int height, int type) {
		drawobjList.add(new FigureObject(x, y, width, height, type));
		repaint();
	}

//	protected void drawImage(int x, int y, int width, int height,
//			String imagePath) {
//		drawobjList.add(new ImageObject(x, y, width, height, imagePath));
//		repaint();
//	}
//	
	protected void drawImage(int x, int y, int width, int height,
			int  type) {
		drawobjList.add(new ImageObject(x, y, width, height, type));
		repaint();
	}

	public void repaint() {

		jpanel.repaint();

	}

	protected void addPath(Path path) {
		drawobjList.add(new LineObject(path));
	}

	protected void addPostIt(int x, int y, int width, int height) {
		
		PostItPanel postItPanel = new PostItPanel(x,y,width,height);
		

		jpanel.add(postItPanel);
		jpanel.validate();
		
		componentList.add(postItPanel);
		repaint();
		



	}


	protected void addMemo(int x, int y, int width, int height) {

		MemoPanel memo = makeMemo(x, y, width, height);
		memo.addToPanel(jpanel, this);
		componentList.add(memo);		
		repaint();

	}
	
	protected MemoPanel makeMemo(int x, int y, int width, int height) {
		return new MemoPanel(x, y, width, height);
	}

	protected void removeLastDrawableObj() {

		drawobjList.remove(drawobjList.size() - 1);

	}

	protected void eraseObjByPosition(int x, int y) {
		
	}

	protected void changeJson() {

		Gson gson = new Gson();
		String json = gson.toJson(jpanel);


	}
	
	protected void restoreNote() {
		
		StoredNoteObject sno = loadStoredNote();
		componentList = sno.getCOList();
		drawobjList = sno.getDOList();
		restore();
		repaint();
	}
	
	

	protected StoredNoteObject loadStoredNote() {
		
		
		String noteData = null;
		try {
			noteData = fim.read(Util.NOTE_ADDR + "content.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(noteData);
		return gson.fromJson(noteData, StoredNoteObject.class);
		
		

	}

	protected void saveStoredNote() {


		StoredNoteObject sno = new StoredNoteObject(drawobjList, componentList);
		String noteContent = gson.toJson(sno);

		try {
			fim.write(Util.NOTE_ADDR + "content.txt", noteContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	protected void setClick(int x, int y) {
		setUnClicked();
		for(int i = 0; i< drawobjList.size(); i++) {
			System.out.println("asdfadsfasdfsdasdafasdfasdfsadf");
			drawobjList.get(i).setClick(x, y, this);
			
				
		}
		repaint();
		
	}
	
	protected boolean isClickableItem(int x, int y) {
		for(int i = 0; i < drawobjList.size(); i++) {
			if(drawobjList.get(i).isClick(x, y, this)) {
				return true;
			}
		}
		
		for(int i = 0; i < drawobjList.size(); i++) {
			if(drawobjList.get(i).isClick(x, y, this)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	protected void removeSelectedItem() {
		for(int i = 0; i < drawobjList.size(); i++) {
			drawobjList.get(i).removeSelectedItem(this);
		}
		
		for(int i = 0; i < componentList.size(); i++) {
			System.out.println("코맥이");
			componentList.get(i).removeSelectedItem(this);
		}
		repaint();
	}
	
	protected void setUnClicked() {
		System.out.println("unclick size : " +drawobjList.size());
		for(int i = 0; i < drawobjList.size(); i++) {
			drawobjList.get(i).setUnClicked(this);
		}
		
		for(int i = 0; i < componentList.size(); i++) {
			System.out.println("코맥이");
			componentList.get(i).setUnClicked(this);
		}
		repaint();
	}

	public ArrayList<DrawableObject> getDrawobjList() {
		return drawobjList;
	}

	public void setDrawobjList(ArrayList<DrawableObject> drawobjList) {
		this.drawobjList = drawobjList;
	}
	
	public ArrayList<ComponentJPanel> getComponentList() {
		return componentList;
	}

	public void setComponentList(ArrayList<ComponentJPanel> componentList) {
		this.componentList = componentList;
	}
	
	public JPanel getJpanel() {
		return jpanel;
	}

	public void setJpanel(JPanel jpanel) {
		this.jpanel = jpanel;
	}




}



