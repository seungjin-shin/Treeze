package com.treeze.draw;

import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class ComponentObject  {
	int x;
	int y;
	int width;
	int height;
	int backgroundWidth;
	int backgroundHeight;
	
	public abstract JComponent makeComponent();
	
	

}
