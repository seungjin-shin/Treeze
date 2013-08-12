package com.treeze.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;


/*
 * 
 */
public class ImageObject extends DrawableObject {

	String imagePath;
	
	public ImageObject(int x, int y, int width, int height, String imagePath) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imagePath = imagePath;
	}	

	@Override
	public void draw(Graphics g, JPanel jpanel) {
		// TODO Auto-generated method stub

		Graphics2D g2D;
		g2D = (Graphics2D) g;
		java.awt.Image img = jpanel.getToolkit().getImage(imagePath);
		g2D.drawImage(img, x, y, width,height, jpanel);

	}

	protected void setImage(int x, int y, int width, int height,
			String imagePath) {

		this.x = x;
		this.y = y;
		this.width = y;
		this.height = height;
		this.imagePath = imagePath;
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
