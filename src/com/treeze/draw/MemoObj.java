package com.treeze.draw;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class MemoObj extends ComponentObject {
	
	String textContent;
	
	public MemoObj(int x, int y, int width, int height, String textContent) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.textContent = textContent;
	}


	@Override
	public ComponentJPanel makeComponent() {
		// TODO Auto-generated method stub
		MemoPanel memo = new MemoPanel(x,y,width,height);
		memo.setText(textContent);
		return memo;
	}




}
