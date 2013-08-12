package com.treeze.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FigureObject extends DrawableObject {

	// ArrayList<Figure> figures = new ArrayList<Figure>();
	int type;


	public FigureObject(int x, int y, int width, int height, int type) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
			
	}
	
	protected void setFigure(int x, int y, int width, int height, int type) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
			
	}

	@Override
	public void draw(Graphics g, JPanel jpanel) {
		// TODO Auto-generated method stub

		Graphics2D g2;
		g2 = (Graphics2D) g;

		if (PPTPanel.FIGURE_TYPE_CIRCLE == type) {
			g2.drawOval(x, y, width, height);
		} else if (PPTPanel.FIGURE_TYPE_REC == type) {
			g2.drawRect(x, y, width, height);
		}

	}

	@Override
	public boolean isRemoveItem(int x, int y) {
		// TODO Auto-generated method stub	
		
		Point largerPoint = new Point(x + width/2, y + height/2);
		Point smallerPoint = new Point(x - width/2, y - height/2);
		
		if((largerPoint.x > x && x > smallerPoint.x) && (largerPoint.y > y && y > smallerPoint.y)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	

}
