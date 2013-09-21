package com.treeze.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;

public class Path {
	
	ArrayList<LinePoint> points;
	Color color;
	BasicStroke bs;
	
	protected Path() {
		// TODO Auto-generated constructor stub
		points = new ArrayList<LinePoint>();
		color = Color.BLACK;
		bs = new BasicStroke(1);
	}

	protected void setColor(Color color) {
		this.color = color;
	}

	protected void setStroke(BasicStroke bs) {
		this.bs = bs;
	}

	protected ArrayList<LinePoint> getPoints() {
		return points;
	}

	protected void setPoints(ArrayList<LinePoint> points) {
		this.points = points;
	}

	protected BasicStroke getBs() {
		return bs;
	}

	protected void setBs(BasicStroke bs) {
		this.bs = bs;
	}

	protected Color getColor() {
		return color;
	}
	
	 

}

class LinePoint {
	int x;
	int y;
	double rateX;
	double rateY;
	static LinePoint beforeLinePoint;
	protected LinePoint(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
}