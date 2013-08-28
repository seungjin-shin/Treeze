package com.treeze.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Path {
	
	ArrayList<Point> points;
	Color color;
	BasicStroke bs;
	
	public Path() {
		// TODO Auto-generated constructor stub
		points = new ArrayList<Point>();
		color = Color.BLACK;
		bs = new BasicStroke(1);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setStroke(BasicStroke bs) {
		this.bs = bs;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	public BasicStroke getBs() {
		return bs;
	}

	public void setBs(BasicStroke bs) {
		this.bs = bs;
	}

	public Color getColor() {
		return color;
	}
	
	 

}