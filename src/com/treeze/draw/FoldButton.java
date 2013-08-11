package com.treeze.draw;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.treeze.util.Var;

public class FoldButton extends JButton {
	
	PostItPanel memoPanel;
	int originalX;
	int originalY;
	
	int originalWidth;
	int originalHeight;
	
	public FoldButton(int x, int y, int width, int height, final PostItPanel memoPanel) {
		// TODO Auto-generated constructor stub
		this.setBounds(x, y, width, height);
		this.setVisible(true);
		
		this.memoPanel = memoPanel;		
		this.originalX = x;
		this.originalY = y;
		this.originalWidth = width;
		this.originalHeight = height;
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				memoPanel.setSize(20, 20);
				memoPanel.setCurFoldMode(PostItPanel.FOLD_MODE_FOLD);
				
				
			}
		});
		

	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Image bg = new ImageIcon(Var.IMG_ADDR + "fold.png").getImage();
		g.drawImage(bg, getX(), getY(), getWidth(), getHeight(), this);

	}
	


}
