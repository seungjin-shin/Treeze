package com.treeze.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JPanel;


/*
 * 
 */
public class ImageObject extends DrawableObject {

	String imagePath;
	Image img;
	int type;
	//Util.IMG_ADDR + "star.png"
	public static Image STAR_IMG; 
	
	 
	
	public ImageObject(int x, int y, int width, int height, String imagePath) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imagePath = imagePath;
	}
	
	public ImageObject(int x, int y, int width, int height, int type) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}	

	@Override
	public void draw(Graphics g, JPanel jpanel) {
		// TODO Auto-generated method stub

		try {
			
			Graphics2D g2D;
			g2D = (Graphics2D) g;
			
			if(STAR_IMG == null) {
				STAR_IMG = jpanel.getToolkit().getImage(getClass().getResource(Util.IMG_ADDR + "star.png"));
			}
			
			if(type == NoteManager.IMG_TYPE_STAR) {
				g2D.drawImage(STAR_IMG, x, y, width, height, jpanel);
			}
			
			
		}catch (Exception e) {
			System.out.println(e);
		}
		

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
	public boolean isClick(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub
		Point largestPoint = new Point(this.x + width/2, this.y + height/2);
		Point smallestPoint = new Point(this.x - width/2, this.y - height/2);
		
		if((largestPoint.x >= x && x >= smallestPoint.x) && (largestPoint.y >= y && y >= smallestPoint.y)) {
//			this.clickWidth = largestPoint.x - smallestPoint.x;
//			this.clickHeight = largestPoint.y - smallestPoint.y;
//			this.clickX = smallestPoint.x ;
//			this.clickY = smallestPoint.y ;
			this.clickPanelHeight = height;
			this.clickPanelWidth = width;
			this.clickPanelX = this.x;
			this.clickPanelY = this.y;
			System.out.println("trrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr?");
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

			clickPanel=new ClickImagePanel(clickPanelX, clickPanelY,
					clickPanelWidth, clickPanelHeight, this, nm);

		}
		
	}



}

class ClickImagePanel extends ClickLinePanel {

	public ClickImagePanel(int x, int y, int width, int height,
			DrawableObject drawableObj, NoteManager nm) {
		super(x, y, width, height, drawableObj, nm);
		// TODO Auto-generated constructor stub
		margin = 20;
	}
	
}
