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

	public ImageObject(int x, int y, int width, int height,
			int backgroundWidth, int backgroundHeight, String imagePath) {
		// TODO Auto-generated constructor stub
		super(x, y, width, height, backgroundWidth, backgroundHeight);
		this.imagePath = imagePath;

	}

	public ImageObject(int x, int y, int width, int height,
			int backgroundWidth, int backgroundHeight, int type) {
		// TODO Auto-generated constructor stub
		super(x, y, width, height, backgroundWidth, backgroundHeight);
		this.type = type;

	}

	@Override
	public void draw(Graphics g, JPanel jpanel) {
		// TODO Auto-generated method stub

		try {

			Graphics2D g2D;
			g2D = (Graphics2D) g;

			if (NoteManager.STAR_IMG == null) {
				NoteManager.STAR_IMG = jpanel.getToolkit().getImage(
						getClass().getResource(Util.IMG_ADDR + "star.png"));
			}

			if (type == NoteManager.IMG_TYPE_STAR) {
				g2D.drawImage(NoteManager.STAR_IMG, x, y, width, height, jpanel);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public boolean isClick(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub
		Point largestPoint = new Point(this.x + width / 2, this.y + height / 2);
		Point smallestPoint = new Point(this.x - width / 2, this.y - height / 2);

		if ((largestPoint.x >= x && x >= smallestPoint.x)
				&& (largestPoint.y >= y && y >= smallestPoint.y)) {

			return true;
		} else {
			return false;
		}

	}

	@Override
	public void setClick(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub
		if (isClick(x, y, nm)) {
			makeClickPanel(new ClickImagePanel(this.x, this.y, width, height,
					this, nm));

		}

	}

	@Override
	protected void setRelativeLocation(NoteManager nm) {
		// TODO Auto-generated method stub
		super.setRelativeLocation(nm);
//		NoteManager.IMG_SIZE_WIDTH = width;
//		NoteManager.IMG_SIZE_HEIGHT = height;

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
