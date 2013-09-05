package com.treeze.draw;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.treeze.data.MindNode;




public class PPTParentPanel extends JPanel {	

	private PPTParentPanel(MindNode node) {
		// TODO Auto-generated constructor stub
//		this.setLayout(new BorderLayout());
//		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//		
//		this.add(new PPTPanel(node));
////		
////		this.
//		

		
	}



	public void actionPerformed(ActionEvent e) {
		repaint(); // update () -> paint() 순서로 호출
	}



}
