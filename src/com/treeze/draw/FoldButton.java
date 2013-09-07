package com.treeze.draw;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FoldButton extends JButton {
	
	ComponentSizeController psc;
	int originalX;
	int originalY;
	
	public static final int FOLD_BUTTON_SIZE_WIDTH = 40;
	public static final int FOLD_BUTTON_SIZE_HEIGHT = 20;
	
	FoldButton foldButton;
	PostItPanel postItPanel;
	
	public FoldButton(final int width,final int height, final PostItPanel postItPanel) {
		// TODO Auto-generated constructor stub

		this.postItPanel = postItPanel;
		this.psc = postItPanel.getPsc();		
		foldButton = this;		

		//essential\
		//asfasdfadsfasdfsdf
		
		this.setBackground(new Color(0,0,0,0));
		this.setIcon(Util.makeResizedImageIcon(width, height, Util.IMG_ADDR + "fold.png", this));
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setBorderPainted(false);
		this.setFocusable(false);
		this.setFocusPainted(false);	
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int curFoldMode = psc.getCurFoldMode();
				
				if(curFoldMode == ComponentSizeController.FOLD_MODE_UNFOLD) {
					setIcon(Util.makeResizedImageIcon(width, height, Util.IMG_ADDR + "expand.png", foldButton));
					
					psc.setOriginalWidth(psc.getWidth());
					psc.setOriginalHeight(psc.getHeight());
					
					psc.setSize(FOLD_BUTTON_SIZE_WIDTH, FOLD_BUTTON_SIZE_HEIGHT);
					psc.setCurFoldMode(ComponentSizeController.FOLD_MODE_FOLD);
					
					postItPanel.getWasteBasketButton().setVisible(false);
					postItPanel.getWasteBasketButton().setVisible(true);
					foldButton.setVisible(true);
				}else if(curFoldMode == ComponentSizeController.FOLD_MODE_FOLD) {
					setIcon(Util.makeResizedImageIcon(width, height, Util.IMG_ADDR + "fold.png", foldButton));
					psc.setCurFoldMode(ComponentSizeController.FOLD_MODE_UNFOLD);
					psc.setSize(psc.getOriginalWidth(),
							psc.getOriginalHeight());
					psc.invalidate();
				}
				
				
				
			}
			
		});
		psc.invalidate();
	}
	
	public void reLocation(int x, int y) {
		this.setLocation(x, y);
	}
	


}

class WasteBasketButton extends JButton {
	
	private JPanel postItPanel;
	
	public WasteBasketButton(int width, int height, final JPanel postItPanel) {
		// TODO Auto-generated constructor stub
		this.postItPanel = postItPanel;
		this.setBackground(new Color(0,0,0,0));
		this.setIcon(Util.makeResizedImageIcon(width, height, Util.IMG_ADDR + "wastebasket.png", this));
		this.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));	
		this.setVisible(true);
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				PPTPanel pptPanel = (PPTPanel)postItPanel.getParent();
				pptPanel.remove(postItPanel);
				NoteManager nm= pptPanel.getNoteManager(); 
				nm.repaint();
				
			}
		});
		
		
	}

}
