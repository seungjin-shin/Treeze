package freemind.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressFrame extends JFrame{
	FreemindManager fManager;
	AddGrid addGrid = new AddGrid();
	ImageIcon logo;
	JProgressBar bar;
	public JProgressBar getBar() {
		return bar;
	}
	public ProgressFrame() {
		fManager = FreemindManager.getInstance();
		Point profileFramePoint = fManager.getProfileFrame().getLocation();
		int profileFrameWidth = fManager.getProfileFrame().getWidth();
		int profileFrameHeight = fManager.getProfileFrame().getHeight();
		
		setTitle("Pdf 2 imaging...");
		setSize(profileFrameWidth / 2, profileFrameHeight / 2);
		setLocation(profileFramePoint.x + (profileFrameWidth / 2), profileFramePoint.y + (profileFrameHeight / 2));
//		setUndecorated(true);
		
		setLayout(addGrid.gbl);
		
		bar = new JProgressBar();
		bar.setValue(0);
//		bar.setSize(100, 100);
		bar.setStringPainted(true);// 진행상태바에 현재상황 표시
		
		addGrid.insets.set(50, 20, 20, 10);
		addGrid.addGrid(addGrid.gbl, addGrid.gbc, bar, 0, 0, 1, 1, 1, 1, this);
		
		setAlwaysOnTop(true);
		System.out.println("[prrogress]");
		setVisible(false);
	}
//	@Override
//	public void paint(Graphics g) {
//		if(logo == null)
//			logo = fManager.makeResizedImageIcon(this.getWidth(), this.getHeight(), fManager.treezeLogo);
//		g.drawImage(logo.getImage(), 0, 0, this);
//	}
}
