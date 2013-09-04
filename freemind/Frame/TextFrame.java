package freemind.Frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import freemind.controller.AddGrid;
import freemind.controller.FreemindManager;
import freemind.controller.ImgBtn;

public class TextFrame extends JFrame implements MouseListener{
	FreemindManager fManager = FreemindManager.getInstance();
	AddGrid addGrid = new AddGrid();
	
	public TextFrame(String text, int x, int y) {
		JLabel msgLb = new JLabel(text, JLabel.CENTER);
		Font f = new Font(text, Font.PLAIN, 25);
		msgLb.setFont(f);
		this.setUndecorated(true);
		
		this.getContentPane().setBackground(fManager.treezeColor);
		this.setLayout(addGrid.getGbl());
		
		addGrid.addGrid(addGrid.getGbl(), addGrid.getGbc(), msgLb, 0, 0, 1, 1, 1, 1, this);
		
		int parentWidth = fManager.getFreemindMainFrame().getWidth();
		int parentHeight = fManager.getFreemindMainFrame().getHeight();
		this.setSize((int)(parentWidth / 3), (int)(parentHeight / 4));
		this.setLocation(x - this.getWidth(), y);
		this.setResizable(false);
		addMouseListener(this);
		setAlwaysOnTop(true);
		this.setVisible(true);
	}
	
	public void closeDialogue(){
		setVisible(false);
		fManager.getSlideShow().getImgFrame().requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		closeDialogue();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		closeDialogue();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
