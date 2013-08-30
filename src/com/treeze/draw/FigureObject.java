package com.treeze.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class FigureObject extends DrawableObject {

	// ArrayList<Figure> figures = new ArrayList<Figure>();
	int type;
	
	


	public FigureObject(int x, int y, int width, int height,  int backgroundWidth, int backgroundHeight,int type) {
		super(x, y, width, height, backgroundWidth, backgroundHeight);
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
		
		if (StateManager.FIGURE_TYPE_CIRCLE == type) {
			//≤¿¡ˆ¡°
			Point centerPoint = new Point(this.x+ this.width/2, this.y + this.height/2);
			double result=Math.pow((x-centerPoint.x), 2)/Math.pow(width/2, 2) + Math.pow((y-centerPoint.y), 2)/Math.pow(height/2, 2);
			if(0.8<= result && result <= 1) {
				return true;
			}
			
			
		} else if (StateManager.FIGURE_TYPE_REC == type) {
			double diffrence = 5;
			if((this.x - diffrence <= x && x <= this.x) &&  (this.y <= y && y <= this.y + width)
			|| (this.x + width - diffrence <= x && x <= this.x + width) &&  (this.y <= y && y <= this.y + width)
			|| (this.y - diffrence <= y && y <= this.y) &&  (this.x <= x && x <= this.x + height)		
			|| (this.y + height - diffrence <= y && y <= this.y + height) &&  (this.x <= x && x <= this.x + height)) {
				return true;
					
				
			}
		}
		return false;
		
	}


	@Override
	public void setClick(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub
		if (isClick(x, y, nm)) {

			 makeClickPanel(new ClickFigurePanel(this.x, this.y, width, height, this, nm));

		}		
	}
	
	


	

	
	

}
