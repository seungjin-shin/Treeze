package com.treeze.draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.MouseInputAdapter;

/*
 * MemoPanel 해당 패널은 메모장으로서 글을 닮는 패널이다.
 */
public class MemoPanel extends ComponentJPanel {

	Point point = new Point();
	JTextArea textArea;
	int margin;

	ComponentSizeController csc;
	int foldSize;
	MemoPanel memoPanel;	
	
	private PPTPanel ptPanel;
	private NoteManager nm;	

	public MemoPanel(final int x, final int y, final int width, final int height) {
		// TODO Auto-generated constructor stub
		margin = 3;
		foldSize = 30;
		memoPanel = this;

		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, width, height);
		this.add(textArea);		

		csc = new ComponentSizeController(this);
		
		csc.setBound(x, y, width, height);
		
		textArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {}
			
			@Override
			public void keyReleased(KeyEvent arg0) {}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				Dimension textAreaDimention = textArea.getPreferredSize();
				textArea.setSize(textArea.getPreferredSize());
				csc.setSize(textAreaDimention.width + 10 , textAreaDimention.height+ 10);
				getClickPanel().setSize(textAreaDimention.width + 10, textAreaDimention.height +10);
				
				
			}
		});
		
		textArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// 
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				getClickPanel().setVisible(false);
				
			}
		});
		
		MouseInputAdapter mia = new MouseInputAdapter() {

			public void mouseDragged(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {
				
				if (csc.isChangeSize(e, margin) || csc.isDrag(e, margin)) {

					getClickPanel().setVisible(true);
					
				}

			}

			public void mouseMoved(MouseEvent e) {
				if (csc.isChangeSize(e, margin) || csc.isDrag(e, margin)) {

					setCursor(StateManager.moveCursor);

				}

			}
		};
		
		addMouseMotionListener(mia);
		addMouseListener(mia);

	}
	
	public void setText(String text) {
		textArea.setText(text);
		Dimension textAreaDimention = textArea.getPreferredSize();
		csc.setSize(textAreaDimention.width, textAreaDimention.height);
		textArea.setSize(textArea.getPreferredSize());
	}
	
	public String getText() {
		return textArea.getText();
	}




//	private void unfold() {
//		psc.setCurFoldMode(ComponentSizeController.FOLD_MODE_UNFOLD);
//		psc.setSize(psc.getOriginalWidth(), psc.getOriginalHeight());
//		textArea.setSize(psc.getWidth() - margin, psc.getWidth() - margin);
//		textArea.setVisible(true);
//	}
//
//	private void fold() {
//		psc.setOriginalWidth(psc.getWidth());
//		psc.setOriginalHeight(psc.getHeight());
//		psc.setSize(foldSize, foldSize);
//		textArea.setSize(psc.getWidth() - margin, psc.getWidth() - margin);
//		textArea.setVisible(false);
//		psc.setCurFoldMode(ComponentSizeController.FOLD_MODE_FOLD);
//		
//
//	}

	@Override
	public void addToPanel(JPanel jpanel, NoteManager nm) {
		// TODO Auto-generated method stub		
		ptPanel = (PPTPanel)jpanel;
		this.nm = nm;
		setClickPanel(new ClickMemoPanel(this.getX(), this.getY(), this.getWidth(), this.getHeight(), memoPanel, nm));
		getClickPanel().setVisible(false);
		jpanel.add(this);
		jpanel.setVisible(false);
		jpanel.setVisible(true);
		
	}

}

class ClickMemoPanel extends ClickComponentPanel {

	protected ClickMemoPanel(int x, int y, int width, int height,
			ComponentJPanel compJpanel, NoteManager nm) {
		super(x, y, width, height, compJpanel, nm);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void contentFocused() {
		// TODO Auto-generated method stub
		MemoPanel mp = (MemoPanel)compJpanel;
		mp.textArea.grabFocus();
		mp.textArea.requestFocus();
		setCursor(mp.textArea.getCursor());
		
	}
	
}