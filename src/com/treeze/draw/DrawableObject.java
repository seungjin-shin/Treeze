package com.treeze.draw;

import java.awt.Graphics;
import javax.swing.JPanel;


/*
 * graphic을 사용한 객체
 */
public abstract class DrawableObject {	
	
	int x;
	int y;
	int width;
	int height;
	
	//graphic을 사용하여 그림을 그리는 방법
	public abstract void draw(Graphics g, JPanel jpanel);
	
	public abstract boolean isRemoveItem(int x, int y);

}






