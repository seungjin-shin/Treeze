package com.treeze.draw;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public abstract class ComponentJPanel extends JPanel {
	
	NoteObject no = new NoteObject();
	
	protected void setClickPanel(ClickPanel cp) {
		no.setClickPanel(cp);
	}
	
	protected ClickPanel getClickPanel() {
		return no.getClickPanel();
	}
	

	
	public abstract void addToPanel(JPanel jpanel , NoteManager nm);
	
	public void removeSelectedItem(NoteManager nm) {
		no.removeSelectedItem(nm);
	}
	
	public void setUnClicked(NoteManager nm) {
		no.setUnClicked(nm);
	}


}


abstract class ClickComponentPanel extends ClickPanel {
	
	private ComponentSizeController contentSC;
	private ClickComponentPanel ccp;
	

	protected ClickComponentPanel(int x, int y, int width, int height,
			final ComponentJPanel compJpanel, NoteManager nm) {
		super(x, y, width, height, compJpanel, nm);
		// TODO Auto-generated constructor stub
		ccp = this;
		contentSC = new ComponentSizeController(compJpanel);
		margin = 15;
		
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();
				if(keyCode == StateManager.KEY_CODE_DEL) {
					removeClicked();
				}
				
			}
		});
		
		
		MouseInputAdapter mia  = new MouseInputAdapter() {

			public void mouseReleased(MouseEvent e) {
				csc.initChangeSizeFlag();
			}

			public void mouseDragged(MouseEvent e) {
				Point sizeControlPoint = csc.getSizeControlPoint();
				if (csc.isChangeSizeFlag()) {
					if (csc.isRightBottomChangeSizeFlag()) {
						setRightBottomSize(e);
					} else if (csc.isRightFloorChangeSizeFlag()) {
						setRightFloorSize(e);
					} else if (csc.isLeftBottomChangeSizeFlag()) {
						setLeftBottomSize(e);
					} else if (csc.isLeftFloorChangeSizeFlag()) {
						setLeftFloorSize(e);
					}

				} else if (csc.isMoveFlag()) {
					cp.relocate(cp.getLocation().x + e.getX()
							- sizeControlPoint.x, cp.getLocation().y + e.getY()
							- sizeControlPoint.y);
				}

			}

			public void mousePressed(MouseEvent e) {

				if (csc.isChangeSize(e, margin)) {

					if (csc.isRightBottomChangeSize(e, margin)) {
						csc.setRightBottomChangeSizeFlag(true);
					} else if (csc.isLeftBottomChangeSize(e, margin)) {
						csc.setLeftBottomChangeSizeFlag(true);
					} else if (csc.isLeftFloorChangeSize(e, margin)) {
						csc.setLeftFloorChangeSizeFlag(true);
					} else if (csc.isRightFloorChangeSize(e, margin)) {
						csc.setRightFloorChangeSizeFlag(true);
					}

				} else if (csc.isDrag(e, margin)) {
					System.out.println("drag");
					csc.setMoveFlag(true);
				}
				csc.setSizeControlPoint(new Point(e.getX(), e.getY()));
			}

			public void mouseMoved(MouseEvent e) {
				ccp.grabFocus();
				ccp.requestFocus();

				if (csc.isChangeSize(e, margin)) {

					if (csc.isRightBottomChangeSize(e, margin)
							|| csc.isLeftFloorChangeSize(e, margin)) {
						setCursor(StateManager.changeSizeRightCursor);
					} else if (csc.isLeftBottomChangeSize(e, margin)
							|| csc.isRightFloorChangeSize(e, margin)) {
						setCursor(StateManager.changeSizeLeftCursor);
					}

				} else if (csc.isDrag(e, margin)) {
					setCursor(StateManager.moveCursor);
				} else {
					
					contentFocused();
				}

			}
		};
		
		this.addMouseListener(mia);
		this.addMouseMotionListener(mia);
	}
	
	protected abstract void contentFocused();
	


	@Override
	protected void relocate(int x, int y) {
		// TODO Auto-generated method stub
		this.setLocation(x, y);
		compJpanel.setLocation(x, y);
		nm.repaint();
		
	}

	@Override
	protected void removeClicked() {
		// TODO Auto-generated method stub
		System.out.println("ฤฺบน");
		PPTPanel ptPanel = (PPTPanel) getParent();
		ptPanel.remove(this);
		ptPanel.remove(compJpanel);
		
		nm.getComponentList().remove(this);
		nm.repaint();
		
	}

	@Override
	protected void setRightBottomSize(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = csc.getTransformedPoint(e);
		csc.setRightBottomSize(p);
		contentSC.setRightBottomSize(p);
		nm.repaint();
		
	}

	@Override
	protected void setRightFloorSize(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = csc.getTransformedPoint(e);
		csc.setRightFloorSize(p);
		contentSC.setRightFloorSize(p);
		nm.repaint();
		
		
	}

	@Override
	protected void setLeftBottomSize(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = csc.getTransformedPoint(e);
		csc.setLeftBottomSize(p);
		contentSC.setLeftBottomSize(p);
		nm.repaint();
		
	}

	@Override
	protected void setLeftFloorSize(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = csc.getTransformedPoint(e);
		csc.setLeftFloorSize(p);
		contentSC.setLeftFloorSize(p);
		nm.repaint();
		
	}
	
}