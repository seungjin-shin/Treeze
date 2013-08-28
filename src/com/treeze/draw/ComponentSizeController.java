package com.treeze.draw;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

public class ComponentSizeController {

	int originalWidth;
	int originalHeight;

	StateManager sm;
	Point sizeControlPoint;

	JComponent component;

	private int curFoldMode;

	public static final int FOLD_MODE_UNFOLD = 0;
	public static final int FOLD_MODE_FOLD = 1;

	int margin;

	// 어디부분을 바꿔야 하는가? 는
	boolean leftBottomChangeSizeFlag;
	boolean rightBottomChangeSizeFlag;
	boolean leftFloorChangeSizeFlag;
	boolean rightFloorChangeSizeFlag;	
	boolean firstChangeSizeFlag;
	
	boolean moveFlag;
	
	
	Point initialComponentSize; 
	

	public Point getSizeControlPoint() {
		return sizeControlPoint;
	}

	public void setSizeControlPoint(Point sizeControlPoint) {
		this.sizeControlPoint = sizeControlPoint;
	}

	public void initChangeSizeFlag() {
		this.firstChangeSizeFlag = true;
		this.leftBottomChangeSizeFlag = false;
		this.rightBottomChangeSizeFlag = false;
		this.leftFloorChangeSizeFlag = false;
		this.rightFloorChangeSizeFlag = false;

	}

	public ComponentSizeController(JComponent component) {
		// TODO Auto-generated constructor stub

		originalHeight = 0;
		originalWidth = 0;
		sm = StateManager.getStateManager();
		sizeControlPoint = new Point();
		this.component = component;
		initialComponentSize = new Point();
		
	}

	public boolean isDrag(MouseEvent e, int margin) {
		
		if (e.getY() < margin
				|| e.getX() < margin
				|| (component.getHeight() - margin < e.getY() && e.getY() < component
						.getHeight())
				|| (component.getWidth() - margin < e.getX() && e.getX() < component
						.getWidth())) {

			return true;
		} else {
			return false;
		}
	}

	public boolean isChangeSize(MouseEvent e, int margin) {
		setMargin(margin);
		if ((e.getX() <= component.getWidth()
				&& e.getX() > component.getWidth() - margin
				&& e.getY() <= component.getHeight() && e.getY() > component
				.getHeight() - margin)
				|| (e.getX() <= margin && e.getX() > 0 && e.getY() <= margin && e
						.getY() > 0)
				|| (e.getX() <= margin && e.getX() > 0
						&& e.getY() <= component.getHeight() && e.getY() > component
						.getHeight() - margin)
				|| ((e.getX() <= component.getWidth()
						&& e.getX() > component.getWidth() - margin
						&& e.getY() <= margin && e.getY() > 0))) {
			return true;

		} else {
			return false;
		}
	}

	// setsize

	public boolean isRightFloorChangeSize(MouseEvent e, int margin) {

		if ((e.getX() <= component.getWidth()
				&& e.getX() > component.getWidth() - margin
				&& e.getY() <= margin && e.getY() >= 0)) {
			return true;

		} else {
			return false;
		}
	}

	public boolean isRightBottomChangeSize(MouseEvent e, int margin) {

		// 첫번째께 오른쪽
		if ((e.getX() <= component.getWidth()
				&& e.getX() > component.getWidth() - margin
				&& e.getY() <= component.getHeight() && e.getY() > component
				.getHeight() - margin)) {
			return true;

		} else {
			return false;
		}
	}

	// left 왼쪽
	public boolean isLeftBottomChangeSize(MouseEvent e, int margin) {

		if ((e.getX() <= margin && e.getX() > 0
				&& e.getY() <= component.getHeight() && e.getY() > component
				.getHeight() - margin)) {
			return true;

		} else {
			return false;
		}
	}

	public boolean isLeftFloorChangeSize(MouseEvent e, int margin) {

		if ((e.getX() <= margin && e.getX() > 0 && e.getY() <= margin && e
				.getY() >= 0)) {
			return true;

		} else {
			return false;
		}
	}

	public void setLeftBottomSize(MouseEvent e) {
		System.out.println(firstChangeSizeFlag);
		 if(firstChangeSizeFlag) {
			 firstChangeSizeFlag = false;
			 initialComponentSize.x = component.getX();
			 initialComponentSize.y = component.getY();
			 System.out.println("adsfasfasdfadsfadsfasdfasdfsadf");
			 
		 }

		int height = component.getHeight() + e.getY() - sizeControlPoint.y;
		int width = component.getWidth() - e.getX() - sizeControlPoint.x;
		
		if(keepSize(width, height, 40, 30)) {
			return;
		}
		
		component.setSize(width, height);
		component.setLocation(component.getLocation().x + e.getX() + sizeControlPoint.x, initialComponentSize.y);
		
		sizeControlPoint.x = e.getX();
		sizeControlPoint.y = e.getY();

		invalidate();
	}
	
	public void setLeftBottomSize(Point p) {
		
		int x = p.getLocation().x;
		int y = p.getLocation().y;
		
		int width = component.getWidth() + component.getX()- x;
		int height = y - component.getY();
		
		if(keepSize(width, height, 40, 30)) {
			return;
		}
		
		component.setSize(width, height);
		component.setLocation(x, component.getY());
		
		

		invalidate();
	}

	public void setRightBottomSize(MouseEvent e) {

		int width = component.getWidth() + e.getX() - sizeControlPoint.x;
		int height = component.getHeight() + e.getY() - sizeControlPoint.y;
		
		if(keepSize(width, height, 40, 30)) {
			return;
		}

		component.setSize(width, height);

		sizeControlPoint.x = e.getX();
		sizeControlPoint.y = e.getY();

		invalidate();
	}
	
	public void setRightBottomSize(Point p) {
		
		int x = p.getLocation().x;
		int y = p.getLocation().y;
		
		int width = x - component.getX();
		int height = y - component.getY();
		
		if(keepSize(width, height, 40, 30)) {
			return;
		}
		
		component.setSize(width, height);
		
		invalidate();
		
	}
	
	public void setLeftFloorSize(Point p) {
		
		int x = p.getLocation().x;
		int y = p.getLocation().y;
		
		int width = component.getWidth() + component.getX() - x;
		int height = component.getHeight() + component.getY() - y;
		
		if(keepSize(width, height, 40, 30)) {
			return;
		}
		
		component.setSize(width, height);
		component.setLocation(x, y);
		invalidate();

	}
	
	public Point getTransformedPoint(MouseEvent e) {
		
		int transformedX = component.getX() + e.getX();
		int transformedY = component.getY() + e.getY();
		
		return new Point(transformedX, transformedY);
	}
	
	public void setRightFloorSize(Point p) {
		
		int transformedX = p.getLocation().x;
		int transformedY = p.getLocation().y;

		int width = transformedX - component.getX();
		int height = component.getHeight() + component.getY() - transformedY;
		
		if(keepSize(width, height, 40, 30)) {
			return;
		}
		
		component.setSize(width, height);
		component.setLocation(component.getX(), transformedY);	

		invalidate();
	}

	protected boolean keepSize(int width, int height, int minimumWidth,
			int minimumHeight) {
		if (width < minimumWidth || height < minimumHeight) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setBound(int x, int y, int width, int height) {
		component.setBounds(x, y, width, height);
	}

	public int getOriginalWidth() {
		return originalWidth;
	}

	public void setOriginalWidth(int originalWidth) {
		this.originalWidth = originalWidth;
	}

	public int getOriginalHeight() {
		return originalHeight;
	}

	public void setOriginalHeight(int originalHeight) {
		this.originalHeight = originalHeight;
	}

	public void setChangeSizeFlag(boolean flag) {
		sm.setChangeSizeFlag(flag);
	}



	public boolean isChangeSizeFlag() {
//		return sm.isChangeSizeFlag();
		if(leftBottomChangeSizeFlag || leftFloorChangeSizeFlag || rightBottomChangeSizeFlag || rightFloorChangeSizeFlag)
			return true;
		else 
			return false;
	}
	
	public void setMoveFlag(boolean moveFlag) {
		sm.setMoveFlag(moveFlag);
		this.moveFlag = moveFlag;
	}

	public boolean isMoveFlag() {
		return moveFlag;
	}

	public int getWidth() {
		return component.getWidth();
	}

	public int getHeight() {
		return component.getHeight();
	}

	protected void invalidate() {
		component.setVisible(false);
		component.setVisible(true);
	}

	public void setSize(int width, int height) {
		component.setSize(width, height);
	}

	public int getCurFoldMode() {
		return curFoldMode;
	}

	public void setCurFoldMode(int foldMode) {
		this.curFoldMode = foldMode;
	}

	public void setLocation(int x, int y) {
		component.setLocation(x, y);
	}

	protected int getMargin() {
		return margin;
	}

	protected void setMargin(int margin) {
		this.margin = margin;
	}

	protected Point getLocation() {
		return component.getLocation();
	}

	public boolean isLeftBottomChangeSizeFlag() {
		return leftBottomChangeSizeFlag;
	}

	public void setLeftBottomChangeSizeFlag(boolean leftBottomChangeSizeFlag) {
		initChangeSizeFlag();
		this.leftBottomChangeSizeFlag = leftBottomChangeSizeFlag;
	}

	public boolean isRightBottomChangeSizeFlag() {
		return rightBottomChangeSizeFlag;
	}

	public void setRightBottomChangeSizeFlag(boolean rightBottomChangeSizeFlag) {
		initChangeSizeFlag();
		this.rightBottomChangeSizeFlag = rightBottomChangeSizeFlag;
	}

	public boolean isLeftFloorChangeSizeFlag() {
		return leftFloorChangeSizeFlag;
	}

	public void setLeftFloorChangeSizeFlag(boolean leftFloorChangeSizeFlag) {
		initChangeSizeFlag();
		this.leftFloorChangeSizeFlag = leftFloorChangeSizeFlag;
	}

	public boolean isRightFloorChangeSizeFlag() {
		return rightFloorChangeSizeFlag;
	}

	public void setRightFloorChangeSizeFlag(boolean rightFloorChangeSizeFlag) {
		initChangeSizeFlag();
		this.rightFloorChangeSizeFlag = rightFloorChangeSizeFlag;
	}


}
