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

	Path path = new Path();
	
	public LineObject(Path path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}

	protected void setPaths(Path path) {
		this.path = path;
		// this.paths = paths;
	}

	@Override
	public void draw(Graphics g, JPanel jpanel) {
		// TODO Auto-generated method stub

		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < path.point.size() - 1; i++) {

			g2.setColor(path.color);
			g2.setStroke(path.bs);

			g2.drawLine(path.point.get(i).x, path.point.get(i).y,
					path.point.get(i + 1).x, path.point.get(i + 1).y);
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
