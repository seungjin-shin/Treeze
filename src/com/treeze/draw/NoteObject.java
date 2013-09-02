package com.treeze.draw;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class NoteObject {
	
	int x;
	int y;
	int width;
	int height;
	
	protected int backgroundWidth;
	protected int backgroundHeight;
	
	protected double rateX;
	protected double rateY;	
	protected double rateWidth;
	protected double rateHeight;
	
	protected transient ClickPanel clickPanel;

	//움직이거나 크기변경시 마지막에 이걸 꼭불러줘야함
	protected void setRate(int x, int y, int width, int height, int backgroundWidth, int backgroundHeight) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.backgroundWidth = backgroundWidth;
		this.backgroundHeight = backgroundHeight;
		rateX = (double)x/(double)backgroundWidth;
		rateWidth = (double)width/(double)backgroundWidth;
		rateY = (double)y/(double)backgroundHeight;
		rateHeight = (double)height/(double)backgroundHeight;
		
	}
	
	protected void setFeatureByRate(int backgroundWidth, int backgroundHeight) {
		this.backgroundWidth = backgroundWidth;
		this.backgroundHeight = backgroundHeight;
		
		x = (int)rateX * backgroundWidth;
		y = (int)rateY * backgroundWidth;
		width = (int)rateWidth * backgroundWidth;
		height = (int)rateHeight * backgroundWidth;
		
		
		
	}
	

	
	
	protected void removeSelectedItem(NoteManager nm) {
		
		if (clickPanel != null) {			
			clickPanel.removeClicked();
		}
	}
	
	public void setUnClicked(NoteManager nm) {

		if (clickPanel != null && clickPanel.isVisible()) {

			JPanel panel = (JPanel)clickPanel.getParent();
			panel.remove(clickPanel);
			clickPanel = null;
		}
	}
	
	protected ClickPanel makeClickPanel(ClickPanel cp) {
		
		if(clickPanel == null) {
			clickPanel = cp;
		}
		clickPanel.setVisible(true);
		return clickPanel;
	}
	
	protected int getRelativeX(NoteManager nm) {		
		
		return (int)(rateX * nm.getJpanel().getWidth());
		
	}
	
	protected int getRelativeY(NoteManager nm) {
		
		return (int)(rateY * nm.getJpanel().getHeight());
		
	}
	
	protected int getRelativeWidth(NoteManager nm) {		
		
		return (int)(rateWidth * nm.getJpanel().getWidth());
		
	}
	
	protected int getRelativeHeight(NoteManager nm) {
		
		return (int)(rateY * nm.getJpanel().getHeight());
		
	}
	
	protected ClickPanel getClickPanel() {
		return clickPanel;
	}

	protected void setClickPanel(ClickPanel clickPanel) {
		this.clickPanel = clickPanel;
	}
	



}


