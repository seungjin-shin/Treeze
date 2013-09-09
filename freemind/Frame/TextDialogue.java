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

public class TextDialogue extends JDialog{
	FreemindManager fManager = FreemindManager.getInstance();
	AddGrid addGrid = new AddGrid();
	
	public TextDialogue(JFrame parentFrame, String text, boolean modal) {
		super(parentFrame, "", modal);
		JLabel msgLb = new JLabel(text, JLabel.CENTER);
		Font f = new Font("text", Font.PLAIN, 25);
		msgLb.setFont(f);
		
		CloseBtn closeBtn = new CloseBtn(fManager.closeDefault, fManager.closePress, fManager.closeOver);
		closeBtn.setBackground(new Color(0, 0, 0, 0));
		closeBtn.setBorderPainted(false);
		closeBtn.setContentAreaFilled(false);
		
		this.getContentPane().setBackground(fManager.treezeColor);
		this.setLayout(addGrid.getGbl());
		
		addGrid.addGrid(addGrid.getGbl(), addGrid.getGbc(), msgLb,    0, 0, 1, 1, 1, 1, this);
		addGrid.getInsets().set(0, 100, 30, 100);
		addGrid.addGrid(addGrid.getGbl(), addGrid.getGbc(), closeBtn, 0, 1, 1, 1, 1, 1, this);
		
		Point parentPrameLoc = parentFrame.getLocation();
		int parentWidth = parentFrame.getWidth();
		int parentHeight = parentFrame.getHeight();
		this.setSize(text.length() * 20, 200);
		this.setLocationRelativeTo(parentFrame);
//		this.setResizable(false);
		this.setVisible(true);
	}
	
	public TextDialogue(JFrame parentFrame, String text, String text2, boolean modal) {
		super(parentFrame, "", modal);
		JLabel msgLb = new JLabel(text, JLabel.CENTER);
		JLabel msgLb2 = new JLabel(text2, JLabel.CENTER);
		Font f = new Font(text, Font.PLAIN, 25);
		msgLb.setFont(f);
		msgLb2.setFont(f);
		
		CloseBtn closeBtn = new CloseBtn(fManager.closeDefault, fManager.closePress, fManager.closeOver);
		closeBtn.setBackground(new Color(0, 0, 0, 0));
		closeBtn.setBorderPainted(false);
		closeBtn.setContentAreaFilled(false);
		
		this.getContentPane().setBackground(fManager.treezeColor);
		this.setLayout(addGrid.getGbl());
		
		addGrid.addGrid(addGrid.getGbl(), addGrid.getGbc(), msgLb,    0, 0, 1, 1, 1, 1, this);
		addGrid.addGrid(addGrid.getGbl(), addGrid.getGbc(), msgLb2,    0, 1, 1, 1, 1, 1, this);
		addGrid.getInsets().set(0, 70, 10, 70);
		addGrid.addGrid(addGrid.getGbl(), addGrid.getGbc(), closeBtn, 0, 2, 1, 1, 1, 1, this);
		
		Point parentPrameLoc = parentFrame.getLocation();
		int parentWidth = parentFrame.getWidth();
		int parentHeight = parentFrame.getHeight();
		
		this.setSize(text.length() * 20, 300);
		this.setLocationRelativeTo(parentFrame);
//		this.setResizable(false);
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
