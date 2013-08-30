package com.treeze.draw;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class PostItObj extends ComponentObject {

	StoredNoteObject sno;
	
	public PostItObj(int x, int y, int width, int height, int backgroundWidth, int backgroundHeight, ArrayList<DrawableObject> drawobjList, ArrayList<ComponentJPanel> componentList) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.backgroundWidth = backgroundWidth;
		this.backgroundHeight = backgroundHeight;
		this.sno = new StoredNoteObject(drawobjList, componentList);
	}
	


	@Override
	public ComponentJPanel makeComponent() {
		// TODO Auto-generated method stub
		PostItPanel postItPanel = new PostItPanel(x,y,width, height,backgroundWidth, backgroundHeight);
		postItPanel.setBackground(Color.blue);
		postItPanel.setLocation(x, y);
		postItPanel.setVisible(true);	
		
		NoteManager postItNM = postItPanel.getNoteManager();
		postItNM.setComponentList(sno.getCOList());
		System.out.println("postItNM component size : "+sno.getCOList().size());
		postItNM.setDrawobjList(sno.getDOList());
		System.out.println("postItNM draw size : "+sno.getDOList().size());
		return postItPanel;
		
	}



	
	

}
