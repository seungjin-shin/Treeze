package com.treeze.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Util {
	
	public static final String IMG_ADDR = "images/";
	public static final String PPT_ADDR = "images/";
	public static final String NOTE_ADDR = "C:/notes/";
	
	public static  ImageIcon makeResizedImageIcon(int width, int height, String imgPath, JComponent component) {
		
		BufferedImage imageBuff = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Image img = new ImageIcon(component.getClass().getResource(imgPath)).getImage();
		Graphics g = imageBuff.createGraphics();
		Image scaleImg = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		g.drawImage(scaleImg, 0, 0, new Color(0, 0, 0), null);
		return new ImageIcon(scaleImg);
		
	}

	
	
}
