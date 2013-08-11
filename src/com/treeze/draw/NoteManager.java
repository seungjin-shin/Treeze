package com.treeze.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;


/*
 * NoteManager객체 필기에 해당되는 부분은 NoteManager에서 관장한다.
 */
public class NoteManager {

	ArrayList<DrawableObject> drawobjList;
	ArrayList<PostItPanel> unDrawObjList;
	Path path;
	JPanel jpanel;
	FigureObject figureObj;

	public NoteManager(JPanel jpanel) {
		// TODO Auto-generated constructor stub
		this.drawobjList = new ArrayList<DrawableObject>();
		this.unDrawObjList = new ArrayList<PostItPanel>();
		this.jpanel = jpanel;

	}
	
	protected void initPath() {
		
		path = new Path();
		
	}
	
	protected void makePath(Point point, Color color, BasicStroke bs) {
		
		
		path.bs = bs;
		path.color = color;
		path.point.add(point);
		repaint();
		
	}
	
	protected void makePathComplete() {
		drawobjList.add(new LineObject(this.path));
	}
	
	protected void makeFigure(int x, int y, int width, int height, int type) {
		this.figureObj = new FigureObject(x, y, width, height, type);
		repaint();
		
	}
	
	protected void makeFigureComplete() {
		drawobjList.add(this.figureObj);
	}
	

	protected void draw(Graphics g) {
		System.out.println("in notemanager");
		for (int i = 0; i < drawobjList.size(); i++) {
			drawobjList.get(i).draw(g, jpanel);
			System.out.println("list obj : " + drawobjList.get(i));
		}
	}

	protected void drawLine(Path path) {

		drawobjList.add(new LineObject(path));
		repaint();
	}

	protected void drawLineByRealTime(Graphics g) {
		
		if (path != null) {

			for (int i = 0; i < path.point.size() - 1; i++) {
				g.drawLine(path.point.get(i).x, path.point.get(i).y,
						path.point.get(i + 1).x, path.point.get(i + 1).y);
			}

		}

	}
	


	
	protected void drawFigureByRealTime(Graphics g) {
//		Graphics2D g2 = (Graphics2D)g;
		if(figureObj != null) {
			figureObj.draw(g, jpanel);
		}
		
	}
	


	protected void drawFigure(int x, int y, int width, int height, int type) {
		drawobjList.add(new FigureObject(x, y, width, height, type));
		repaint();
	}

	protected void drawImage(int x, int y, int width, int height,
			String imagePath) {
		drawobjList.add(new ImageObject(x, y, width, height, imagePath));
		repaint();
	}
	
	

	protected void repaint() {
//		System.out.println("jpanel : " + jpanel);
		jpanel.repaint();
	}

	protected void addPath(Path path) {
		drawobjList.add(new LineObject(path));
	}

	protected void addMemo(int x, int y, int width, int height) {

		PostItPanel postItPanel = new PostItPanel(width, height, ((PPTPanel)jpanel));
		postItPanel.setBackground(Color.blue);
		postItPanel.setLocation(x, y);
		postItPanel.setVisible(true);

		PPTPanel pptPanel = (PPTPanel) jpanel;

		pptPanel.add(postItPanel);
		pptPanel.validate();

		unDrawObjList.add(postItPanel);
		repaint();

	}
	
	protected void addTextField(int x, int y, int width, int height) {
				
		MemoPanel memo = new MemoPanel();		
		memo.setBounds(x, y, width, height);
		jpanel.add(memo);	
		jpanel.setVisible(false);
		jpanel.setVisible(true);
		repaint();
		
		
		
	}
	
	protected void removeDrawableObj() {
		
		drawobjList.remove(drawobjList.size()-1);
		
	}

}
