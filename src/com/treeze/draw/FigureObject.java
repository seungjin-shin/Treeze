package com.treeze.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FigureObject extends DrawableObject {

	// ArrayList<Figure> figures = new ArrayList<Figure>();
	int type;

//	public static final int FIGURE_TYPE_CIRCLE = 0;
//	public static final int FIGURE_TYPE_RECT = 1;
	
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
		// g2.drawRect(x1, y1, width, height);
		if (PPTPanel.FIGURE_TYPE_CIRCLE == type) {
			g2.drawOval(x, y, width, height);
		} else if (PPTPanel.FIGURE_TYPE_REC == type) {
			g2.drawRect(x, y, width, height);
		}

	}

}
