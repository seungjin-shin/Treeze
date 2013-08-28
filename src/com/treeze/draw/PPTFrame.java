package com.treeze.draw;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.InputStream;

import javax.swing.JFrame;




public class PPTFrame extends JFrame {
	
	PPTPanel pptPanel;
	NoteManager nm;

	
	
	public PPTFrame() {
		// TODO Auto-generated constructor stub
		
		pptPanel = new PPTPanel(Util.PPT_ADDR + "ppt.png");
		setBounds(500, 100, 600, 500);
		setVisible(true);
		pptPanel.setVisible(true);
		this.add(pptPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		nm = pptPanel.getNoteManager();
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub				
//				nm.restoreNote();				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
//				nm.saveStoredNote();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}



	public void actionPerformed(ActionEvent e) {
		repaint(); // update () -> paint() 순서로 호출
	}



}
