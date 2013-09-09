package freemind.controller;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProgressFrame extends JFrame{
	FreemindManager fManager;
	AddGrid addGrid = new AddGrid();
	ImageIcon logo;
	JProgressBar bar;
	public JProgressBar getBar() {
		return bar;
	}
	public ProgressFrame(String title) {
		fManager = FreemindManager.getInstance();

		int profileFrameWidth = fManager.getProfileFrame().getWidth();
		int profileFrameHeight = fManager.getProfileFrame().getHeight();
		
		setTitle(title);
		setSize(500, 200);
		setLocationRelativeTo(fManager.getProfileFrame());
		getContentPane().setBackground(fManager.treezeColor);
//		setUndecorated(true);
		
		setLayout(addGrid.gbl);
//		setLayout(null);
		
		ProgressBarPanel pPanel = new ProgressBarPanel();
		pPanel.setLayout(addGrid.gbl);
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(addGrid.gbl);
		
		bar = new JProgressBar();
		bar.setValue(0);
//		bar.setSize(300, 100);
		bar.setStringPainted(true);// 진행상태바에 현재상황 표시
//		bar.setIndeterminate(true);
//		bar.setLocation(50, 200);
		addGrid.addGrid(addGrid.gbl, addGrid.gbc, bar,    0, 0, 1, 1, 1, 1, jPanel);
//		addGrid.addGrid(addGrid.gbl, addGrid.gbc, new JLabel("PDF to Image..."),    0, 0, 1, 1, 1, 1, pPanel);
		
//		addGrid.addGrid(addGrid.gbl, addGrid.gbc, pPanel, 0, 0, 1, 1, 1, 2, this);
		addGrid.insets.set(30, 40, 30, 40);
		addGrid.addGrid(addGrid.gbl, addGrid.gbc, jPanel, 0, 1, 1, 1, 1, 1, this);
		
		setAlwaysOnTop(true);
		System.out.println(profileFrameWidth + "," + profileFrameHeight + "[prrogress]");
//		pack();
//		setResizable(false);
		setVisible(true);
	}
//	@Override
//	public void paint(Graphics g) {
//		super.paint(g);
//		if(logo == null){
//			logo = fManager.makeResizedImageIcon(this.getWidth(), this.getHeight(), fManager.treezeLogo);
//			System.out.println("paint null");
//		}
//		g.drawImage(logo.getImage(), 0, 0, this);
//		System.out.println("paint draw");
//	}

//	public void paint(Graphics g) {
//		// TODO Auto-generated method stub
//		super.paint(g);
//		if(logo == null){
//			logo = fManager.makeResizedImageIcon(500, 300, fManager.treezeLogo);
//			System.out.println("paint null");
//		}
//		else{
//			g.drawImage(logo.getImage(), 0, 0, this);
//			System.out.println("paint draw");
//		}
//	}
	
	class ProgressBarPanel extends JPanel{
		public ProgressBarPanel() {
			setBackground(fManager.treezeColor);
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(logo == null){
				logo = fManager.makeResizedImageIcon(500, 200, fManager.treezeLogo);
				System.out.println("paint null");
			}
			else{
				g.drawImage(logo.getImage(), 0, 0, this);
				System.out.println("paint draw");
			}
		}
	}
}
