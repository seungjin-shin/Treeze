package freemind.Frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import freemind.controller.AddGrid;
import freemind.controller.FreemindManager;
import freemind.controller.ImgBtn;

public class TextFrame extends JFrame{
	String text;
	FreemindManager fManager = FreemindManager.getInstance();
	AddGrid addGrid = new AddGrid();
	
	public TextFrame(String text, int x, int y) {
		this.text = text;
		JLabel msgLb = new JLabel(text, JLabel.CENTER);
		Font f = new Font("text", Font.PLAIN, 25);
		msgLb.setFont(f);
		this.setUndecorated(true);
		
		CloseBtn closeBtn = new CloseBtn(fManager.closeDefault, fManager.closePress, fManager.closeOver);
		closeBtn.setBackground(new Color(0, 0, 0, 0));
		closeBtn.setBorderPainted(false);
		closeBtn.setContentAreaFilled(false);
		
		this.getContentPane().setBackground(fManager.treezeColor);
		this.setLayout(addGrid.getGbl());
		
		addGrid.addGrid(addGrid.getGbl(), addGrid.getGbc(), msgLb, 0, 0, 1, 1, 1, 1, this);
		addGrid.getInsets().set(0, 70, 30, 70);
		addGrid.addGrid(addGrid.getGbl(), addGrid.getGbc(), closeBtn, 0, 1, 1, 1, 1, 1, this);
		
		Point parentPrameLoc = fManager.getFreemindMainFrame().getLocation();
		int parentWidth = fManager.getFreemindMainFrame().getWidth();
		int parentHeight = fManager.getFreemindMainFrame().getHeight();
		this.setSize((int)(parentWidth / 3), (int)(parentHeight / 4));
		this.setLocation(x - this.getWidth(), y);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void closeDialogue(){
		setVisible(false);
	}
	
	class CloseBtn extends ImgBtn{

		public CloseBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			closeDialogue();
		}
		
	}
}
