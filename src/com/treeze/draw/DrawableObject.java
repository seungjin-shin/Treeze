package com.treeze.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

/*
 * graphic을 사용한 객체
 */
public abstract class DrawableObject extends NoteObject {

	DrawableObject(int x, int y, int width, int height, int backgroundWidth,
			int backgroundHeight) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.backgroundWidth = backgroundWidth;
		this.backgroundHeight = backgroundHeight;
		setRate(x, y, width, height, backgroundWidth, backgroundHeight);
	}

	DrawableObject(int backgroundWidth, int backgroundHeight) {
		// TODO Auto-generated constructor stub

		this.backgroundWidth = backgroundWidth;
		this.backgroundHeight = backgroundHeight;

	}

	// graphic을 사용하여 그림을 그리는 방법
	public abstract void draw(Graphics g, JPanel jpanel);

	public abstract void setClick(int x, int y, NoteManager nm);

	public abstract boolean isClick(int x, int y, NoteManager nm);


	protected void setRelativeLocation(NoteManager nm) {

		backgroundWidth = nm.getJpanel().getWidth();
		backgroundHeight = nm.getJpanel().getHeight();

		x = (int) (backgroundWidth * rateX);
		y = (int) (backgroundHeight * rateY);
		width = (int) (backgroundWidth * rateWidth);
		height = (int) (backgroundHeight * rateHeight);

		if (getClickPanel() != null) {
			getClickPanel().setBounds(x, y, width, height);
		}

	}
	

	public void move(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub
		int diffrenceX = x - this.x;
		int diffrenceY = y - this.y;

		this.x = this.x + diffrenceX;
		this.y = this.y + diffrenceY;
		setRate(this.x, this.y, this.width, this.height, this.backgroundWidth, this.backgroundHeight);

	}

	void setRightBottomSize(int x, int y) {

		this.width = x - this.x;
		this.height = y - this.y;
		setRate(this.x, this.y, this.width, this.height, this.backgroundWidth, this.backgroundHeight);

	}

	void setRightFloorSize(int x, int y) {

		this.width = x - this.x;
		this.height = this.height + this.y - y;
		this.y = y;
		setRate(this.x, this.y, this.width, this.height, this.backgroundWidth, this.backgroundHeight);

	}

	void setLeftBottomSize(int x, int y) {

		this.width = this.width + this.x - x;
		this.height = y - this.y;
		this.x = x;
		setRate(this.x, this.y, this.width, this.height, this.backgroundWidth, this.backgroundHeight);

	}

	void setLeftFloorSize(int x, int y) {

		this.width = this.width + this.x - x;
		this.height = this.height + this.y - y;
		this.y = y;
		this.x = x;
		setRate(this.x, this.y, this.width, this.height, this.backgroundWidth, this.backgroundHeight);

	}

}

// 그림과는 무관함

abstract class ClickPanel extends JPanel {

	protected int sizeDiffrence = 10;

	protected DrawableObject drawableObj;
	protected ComponentJPanel compJpanel;

	protected NoteManager nm;
	protected ClickPanel cp;
	protected ComponentSizeController csc;
	protected MouseInputAdapter defaultMIA;

	StateManager sm;

	int margin = 3;

	protected ClickPanel(int x, int y, int width, int height,
			ComponentJPanel compJpanel, NoteManager nm) {
		// TODO Auto-generated constructor stub
		// System.out.println(x + " " + y + " " + width + " " + height
		// + "asdfsdafasdf");
		this.setBounds(x, y, width, height);
		this.setBackground(new Color(0, 0, 0, 0));
		this.compJpanel = compJpanel;
		this.nm = nm;
		this.cp = this;
		csc = new ComponentSizeController(this);
		sm = StateManager.getStateManager();

		nm.getJpanel().add(this);
		this.setVisible(true);

		initDefMIA();

	}

	protected ClickPanel(int x, int y, int width, int height,
			DrawableObject drawableObj, NoteManager nm) {
		// TODO Auto-generated constructor stub

		this.setBounds(x, y, width, height);
		this.setBackground(new Color(0, 0, 0, 0));
		this.drawableObj = drawableObj;
		this.nm = nm;
		this.cp = this;
		csc = new ComponentSizeController(this);
		sm = StateManager.getStateManager();

		nm.getJpanel().add(this);
		this.setVisible(true);

		initDefMIA();

	}

	private void initDefMIA() {
		defaultMIA = new MouseInputAdapter() {

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
					// System.out.println("drag");
					csc.setMoveFlag(true);
				}
				csc.setSizeControlPoint(new Point(e.getX(), e.getY()));
			}

			public void mouseMoved(MouseEvent e) {

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
					setCursor(sm.getCurStateCursor());
				}

			}
		};
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Image bg = new ImageIcon(getClass().getResource(
				Util.IMG_ADDR + "click_image.png")).getImage();
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);

	}

	protected abstract void relocate(int x, int y);

	protected abstract void removeClicked();

	protected abstract void setRightBottomSize(MouseEvent e);

	protected abstract void setRightFloorSize(MouseEvent e);

	protected abstract void setLeftBottomSize(MouseEvent e);

	protected abstract void setLeftFloorSize(MouseEvent e);

}

class ClickGraphicPanel extends ClickPanel {

	public ClickGraphicPanel(int x, int y, int width, int height,
			DrawableObject drawableObj, NoteManager nm) {
		super(x, y, width, height, drawableObj, nm);
		// TODO Auto-generated constructor stub

	}

	@Override
	protected void relocate(int x, int y) {
		// TODO Auto-generated method stub
		// System.out.println("relocate");

		this.setLocation(x, y);
		drawableObj.move(x, y, nm);
		nm.repaint();

	}

	@Override
	protected void removeClicked() {
		// TODO Auto-generated method stub

		nm.getDrawobjList().remove(drawableObj);
		PPTPanel ptPanel = (PPTPanel) getParent();
		ptPanel.remove(this);
		nm.repaint();

	}
	
	@Override
	protected void setRightBottomSize(MouseEvent e) {
		Point p = csc.getTransformedPoint(e);
		csc.setRightBottomSize(e);
		drawableObj.setRightBottomSize(p.getLocation().x, p.getLocation().y);
		nm.repaint();

	}

	@Override
	protected void setRightFloorSize(MouseEvent e) {

		Point p = csc.getTransformedPoint(e);
		csc.setRightFloorSize(p);
		drawableObj.setRightFloorSize(p.getLocation().x, p.getLocation().y);
		nm.repaint();

	}

	@Override
	protected void setLeftBottomSize(MouseEvent e) {

		Point p = csc.getTransformedPoint(e);
		csc.setLeftBottomSize(p);		
		drawableObj.setLeftBottomSize(p.getLocation().x, p.getLocation().y);
		nm.repaint();

	}

	@Override
	protected void setLeftFloorSize(MouseEvent e) {

		Point p = csc.getTransformedPoint(e);
		csc.setLeftFloorSize(p);	
		drawableObj.setLeftFloorSize(p.getLocation().x, p.getLocation().y);
		nm.repaint();

	}

}

class ClickFigurePanel extends ClickGraphicPanel {

	FigureObject figureObj;

	public ClickFigurePanel(int x, int y, int width, int height,
			DrawableObject drawableObj, NoteManager nm) {
		super(x, y, width, height, drawableObj, nm);
		// TODO Auto-generated constructor stub
		margin = 20;
		this.addMouseListener(defaultMIA);
		this.addMouseMotionListener(defaultMIA);

		figureObj = (FigureObject) drawableObj;
	}



}
