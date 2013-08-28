package com.treeze.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

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
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(1));

		if (StateManager.FIGURE_TYPE_CIRCLE == type) {
			g2.drawOval(x, y, width, height);
		} else if (StateManager.FIGURE_TYPE_REC == type) {
			g2.drawRect(x, y, width, height);
		}

	}

	@Override
	public boolean isClick(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub	
		
		Point largestPoint = new Point(this.x + width/2, this.y + height/2);
		Point smallestPoint = new Point(this.x - width/2, this.y - height/2);
		
		if((largestPoint.x >= x && x >= smallestPoint.x) && (largestPoint.y >= y && y >= smallestPoint.y)) {
			this.clickPanelWidth = largestPoint.x - smallestPoint.x;
			this.clickPanelHeight = largestPoint.y - smallestPoint.y;
			this.clickPanelX = this.x;
			this.clickPanelY = this.y  ;
			return true;
		}else {
			return false;
		}
		
	}


	@Override
	public void move(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub
		int diffrenceX = x - this.x;
		int diffrenceY = y - this.y;
		
		this.x = this.x + diffrenceX;
		this.y = this.y + diffrenceY;
		
	}

	@Override
	public void setClick(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub
		if (isClick(x, y, nm)) {
			
			clickPanel = new ClickFigurePanel(clickPanelX, clickPanelY, clickPanelWidth, clickPanelHeight, this, nm);

		}
		
	}

	

	
	

}
