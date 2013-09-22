package JDIalog;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.treeze.data.TreezeStaticData;
import com.treeze.frame.MindMapMain;



public class TextDialogue extends JDialog{
	
	String text;
	
	AddGrid addGrid = new AddGrid();
	JFrame parent;
	public TextDialogue(JFrame parentFrame, String text, boolean modal) {
		super(parentFrame, "", modal);
		this.text = text;
		this.parent = parentFrame;
		JLabel msgLb = new JLabel(text, JLabel.CENTER);
		Font f = new Font("text", Font.PLAIN, 25);
		msgLb.setFont(f);
		this.setSize(text.length()*20, 200);
		CloseBtn closeBtn = new CloseBtn(TreezeStaticData.CLOSE_IMG,TreezeStaticData.CLOSE_PRESS_IMG, TreezeStaticData.CLOSE_ENTER_IMG);
		closeBtn.setBackground(new Color(0, 0, 0, 0));
		closeBtn.setBorderPainted(false);
		closeBtn.setContentAreaFilled(false);
		
		this.getContentPane().setBackground(TreezeStaticData.TREEZE_BG_COLOR);
		this.setLayout(addGrid.getGbl());
		
		addGrid.addGrid(addGrid.getGbl(), addGrid.getGbc(), msgLb, 0, 0, 3, 1, 1, 2, this);
		
		addGrid.getInsets().set(0, 20, 30, 20);
		addGrid.addGrid(addGrid.getGbl(), addGrid.getGbc(), new JLabel(), 0, 1, 1, 1, 1, 1, this);
		addGrid.addGrid(addGrid.getGbl(), addGrid.getGbc(), closeBtn,     1, 1, 1, 1, 1, 1, this);
		addGrid.addGrid(addGrid.getGbl(), addGrid.getGbc(), new JLabel(), 2, 1, 1, 1, 1, 1, this);
		
		this.setLocationRelativeTo(parentFrame);
		
		
		this.setResizable(false);
		closeBtn.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0.getKeyCode());
				if(arg0.getKeyCode()==10||arg0.getKeyCode()==27){
					closeDialogue();
				}
				
			}
		});
		setVisible(true);
		
	}
	
	public void closeDialogue(){
		setVisible(false);
		parent.repaint();
		
	}
	
	class CloseBtn extends com.treeze.Abstract.ImgBtn{

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
