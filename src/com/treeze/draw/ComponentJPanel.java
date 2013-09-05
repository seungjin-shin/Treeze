package com.treeze.draw;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public abstract class ComponentJPanel extends JPanel {
	
	private NoteObject no = new NoteObject();	
	
	protected int backgroundWidth;
	protected int backgroundHeight;
	
	protected double rateX;
	protected double rateY;	
	protected double rateWidth;
	protected double rateHeight;
	
	ComponentSizeController csc;
	
	protected ComponentJPanel() {
		// TODO Auto-generated constructor stub
		no = new NoteObject();
		csc = new ComponentSizeController(this);
	}
	
	protected abstract void addToPanel(JPanel jpanel , NoteManager nm);


	protected void removeSelectedItem(NoteManager nm) {
		if (no.getClickPanel() != null && no.getClickPanel().isVisible()) {
			no.getClickPanel().removeClicked();
		}
	}
	
	
	protected void setUnClicked(NoteManager nm) {

		if (getClickPanel() != null) {
			
			getClickPanel().setVisible(false);			
			
		}
	}
	protected ClickPanel makeClickPanel(ClickPanel cp) {
		return no.makeClickPanel(cp);
	}
	
	protected  void setRelativeLocation(NoteManager nm) {
		backgroundWidth = nm.getJpanel().getWidth();
		backgroundHeight = nm.getJpanel().getHeight();
		
		int x  = (int) (backgroundWidth*rateX);
		int y = (int) (backgroundHeight*rateY);
		int width  = (int) (backgroundWidth*rateWidth);
		int height = (int) (backgroundHeight*rateHeight);
		
		
		if(getClickPanel() != null) {
			getClickPanel().setBounds(x, y, width, height);
		}
		
		this.setBounds(x, y, width, height);
		
	}
	
	
	//생성자 이후에 혹은 컴포넌트의 크기를 정한 후에 불러줘야함 
	protected void setRate(int backgroundWidth, int backgroundHeight) {
		
		rateX = (double)getX()/(double)backgroundWidth;
		rateWidth = (double)getWidth()/(double)backgroundWidth;
		rateY = (double)getY()/(double)backgroundHeight;
		rateHeight = (double)getHeight()/(double)backgroundHeight;
		
		this.backgroundWidth = backgroundWidth;
		this.backgroundHeight = backgroundHeight;
		
		
	}
	
	protected void setFeatureByRate(int backgroundWidth, int backgroundHeight) {
		this.backgroundWidth = backgroundWidth;
		this.backgroundHeight = backgroundHeight;
		
		int x = (int)rateX * backgroundWidth;
		int y = (int)rateY * backgroundWidth;
		int width = (int)rateWidth * backgroundWidth;
		int height = (int)rateHeight * backgroundWidth;
		
		this.setBounds(x, y, width, height);
		
	}
	
	protected void setLeftFloorSize(Point p) {
		csc.setLeftFloorSize(p);
		setRate(backgroundWidth, backgroundHeight);
	}
	
	protected void setRightFloorSize(Point p) {
		csc.setRightFloorSize(p);
		setRate(backgroundWidth, backgroundHeight);
	}
	
	protected void setLeftBottomSize(Point p) {
		csc.setLeftBottomSize(p);
		setRate(backgroundWidth, backgroundHeight);
	}
	
	protected void setRightBottomSize(Point p) {
		csc.setRightBottomSize(p);
		setRate(backgroundWidth, backgroundHeight);
	}
	
	@Override
	public void setLocation(int x, int y) {
		// TODO Auto-generated method stub
		super.setLocation(x, y);
		setRate(backgroundWidth, backgroundHeight);
		
	}
	
	
	protected void setClickPanel(ClickPanel cp) {
		no.setClickPanel(cp);
	}
	
	protected ClickPanel getClickPanel() {
		return no.getClickPanel();
	}

	
	public int getBackgroundWidth() {
		return backgroundWidth;
	}

	public void setBackgroundWidth(int backgroundWidth) {
		this.backgroundWidth = backgroundWidth;
	}

	public int getBackgroundHeight() {
		return backgroundHeight;
	}

	public void setBackgroundHeight(int backgroundHeight) {
		this.backgroundHeight = backgroundHeight;
	}

	public double getRateX() {
		return rateX;
	}

	public void setRateX(double rateX) {
		this.rateX = rateX;
	}

	public double getRateY() {
		return rateY;
	}

	public void setRateY(double rateY) {
		this.rateY = rateY;
	}

	public double getRateWidth() {
		return rateWidth;
	}

	public void setRateWidth(double rateWidth) {
		this.rateWidth = rateWidth;
	}

	public double getRateHeight() {
		return rateHeight;
	}

	public void setRateHeight(double rateHeight) {
		this.rateHeight = rateHeight;
	}
	
//	protected void setRate(ComponentJPanel cjp, )


}


abstract class ClickComponentPanel extends ClickPanel {
	
//	private ComponentSizeController contentSC;
	private ClickComponentPanel ccp;
	

	protected ClickComponentPanel(int x, int y, int width, int height,
			final ComponentJPanel compJpanel, NoteManager nm) {
		super(x, y, width, height, compJpanel, nm);
		// TODO Auto-generated constructor stub
		ccp = this;
//		contentSC = new ComponentSizeController(compJpanel);
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
		
		PPTPanel ptPanel = (PPTPanel) getParent();
		ptPanel.remove(this);
		ptPanel.remove(compJpanel);
		
		nm.getComponentList().remove(compJpanel);
		nm.repaint();
		
	}

	@Override
	protected void setRightBottomSize(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = csc.getTransformedPoint(e);
		csc.setRightBottomSize(p);
		compJpanel.setRightBottomSize(p);
		nm.repaint();
		
	}

	@Override
	protected void setRightFloorSize(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = csc.getTransformedPoint(e);
		csc.setRightFloorSize(p);
		compJpanel.setRightFloorSize(p);
		nm.repaint();
		
		
	}

	@Override
	protected void setLeftBottomSize(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = csc.getTransformedPoint(e);
		csc.setLeftBottomSize(p);
		compJpanel.setLeftBottomSize(p);
		nm.repaint();
		
	}

	@Override
	protected void setLeftFloorSize(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = csc.getTransformedPoint(e);
		csc.setLeftFloorSize(p);
		compJpanel.setLeftFloorSize(p);
		nm.repaint();
		
	}
	
	
	
}