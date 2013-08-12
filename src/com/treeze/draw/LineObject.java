package com.treeze.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class LineObject extends DrawableObject {
	// protected void setPaths(Array)

	NoteManager nm;
	Path path;
	
	public LineObject(Path path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}

	@Override
	public void draw(Graphics g, JPanel jpanel) {
		// TODO Auto-generated method stub

		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < path.point.size() - 1; i++) {

			g2.setColor(path.color);
			g2.setStroke(path.bs);
			g2.drawLine(path.point.get(i).x, path.point.get(i).y,path.point.get(i + 1).x, path.point.get(i + 1).y);
		}
	}

	@Override
	public boolean isRemoveItem(int x, int y) {
		// TODO Auto-generated method stub
		System.out.println("path : " + path.point.size());
		if(path.point.size() == 0) {
			return false;
		}
		Point firstPoint = path.point.get(0);
		Point lastPoint = path.point.get(path.point.size() - 1);
		
		Point largerPoint = new Point();
		Point smallerPoint = new Point();
		
		if(firstPoint.x > lastPoint.x) {
			largerPoint.x = firstPoint.x;
			smallerPoint.x = lastPoint.x;
		}else {
			largerPoint.x = lastPoint.x;
			smallerPoint.x = firstPoint.x;
		}
		
		if(firstPoint.y > lastPoint.y) {
			largerPoint.y = firstPoint.y;
			smallerPoint.y = lastPoint.y;
		}else {
			largerPoint.y = lastPoint.y;
			smallerPoint.y = firstPoint.y;
		}
		
		if((largerPoint.x > x && x > smallerPoint.x) && (largerPoint.y > y && y > smallerPoint.y)) {
			return true;
		}else {
			return false;
		}
	}

}

class Path {
	ArrayList<Point> point = new ArrayList<Point>();
	Color color;
	BasicStroke bs;

	public void setColor(Color color) {
		this.color = color;
	}

	public void setStroke(BasicStroke bs) {
		this.bs = bs;
	}

}
