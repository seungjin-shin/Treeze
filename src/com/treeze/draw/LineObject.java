package com.treeze.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class LineObject extends DrawableObject {

	Path path;

	public LineObject(Path path, int backgroundWidth, int backgroundHeight) {
		// TODO Auto-generated constructor stub		
		super(backgroundWidth, backgroundHeight);
		this.path = path;
		setLineFeature();
		setRate(x, y, width, height, this.backgroundWidth, this.backgroundHeight);
	}

	@Override
	public void draw(Graphics g, JPanel jpanel) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < path.points.size() - 1; i++) {
			if(g2 != null) {
				g2.setColor(path.color);
				g2.setStroke(path.bs);
				g2.drawLine(path.points.get(i).x, path.points.get(i).y,
						path.points.get(i + 1).x, path.points.get(i + 1).y);				
			}
		}
	}

	@Override
	public boolean isClick(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub
		// System.out.println("position : " + x + " " + y);
		if (path.points.size() == 0) {
			return false;
		}			
		
		for(int i = 0; i < path.points.size(); i++) {
			int curPathX = path.points.get(i).x;
			int curPathY = path.points.get(i).y;
			
			double distanceResult = Math.sqrt(Math.pow(curPathX - x, 2) + Math.pow(curPathY - y, 2));
			
			if( 0 <= distanceResult && 10 >= distanceResult) {
				return true;
			}
			
			
		}

		return false;
	}

	private void setLineFeature() {

		if (path.points.size() == 0) {
			return;
		}

		Point largestPoint = new Point();
		Point smallestPoint = new Point();

		boolean firstFlag = true;

		for (int i = 0; i < path.points.size(); i++) {

			int curPathX = path.points.get(i).x;
			int curPathY = path.points.get(i).y;

			if (firstFlag == true) {
				smallestPoint.x = curPathX;
				smallestPoint.y = curPathY;
				largestPoint.x = curPathX;
				largestPoint.y = curPathY;
				firstFlag = false;
				continue;
			}

			if (smallestPoint.x > curPathX)
				smallestPoint.x = curPathX;
			if (smallestPoint.y > curPathY)
				smallestPoint.y = curPathY;
			if (largestPoint.x < curPathX)
				largestPoint.x = curPathX;
			if (largestPoint.y < curPathY)
				largestPoint.y = curPathY;

		}

		this.width = largestPoint.x - smallestPoint.x;
		this.height = largestPoint.y - smallestPoint.y;
		this.x = smallestPoint.x;
		this.y = smallestPoint.y;
	}
	//이움직임은 panel에서 관장하므로 실제적으로 여기서 불러서 사용하지 않는다.
	@Override
	public void move(int fromX, int fromY, int toX, int toY, NoteManager nm) {
		// TODO Auto-generated method stub
		int diffrenceX = toX - fromX;
		int diffrenceY = toY - fromY;
		for (int i = 0; i < path.points.size(); i++) {
			int curPathX = path.points.get(i).x;
			int curPathY = path.points.get(i).y;

			path.points.get(i).x = curPathX + diffrenceX;
			path.points.get(i).y = curPathY + diffrenceY;
		}

		this.x = this.x + diffrenceX;
		this.y = this.y + diffrenceY;
		setRate(this.x, this.y, width, height, backgroundWidth, backgroundHeight);

	}

	@Override
	public void setClick(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub
		if (isClick(x, y, nm)) {

			makeClickPanel(new ClickLinePanel(this.x, this.y,
					width, height, this, nm));

		}

	}
	//실제적으로 이녀석이 계속 불려서 움직이는데 이녀석은 자기 자신뿐만 아니라 클릭패널의 이동도 관장한다.
	@Override
	protected void setRelativeLocation(NoteManager nm) {
		// TODO Auto-generated method stub
		super.setRelativeLocation(nm);

		if (path.points.size() == 0) {
			return;
		}
		
		for (int i = 0; i < path.points.size(); i++) {

			path.points.get(i).x = (int) (backgroundWidth * path.points.get(i).rateX);
			path.points.get(i).y = (int) (backgroundHeight * path.points.get(i).rateY);
			
		}


	}
	
	@Override
	protected void setRate(int x, int y, int width, int height, int backgroundWidth, int backgroundHeight) {
		// TODO Auto-generated method stub
		super.setRate(x, y, width, height, backgroundWidth, backgroundHeight);
		
		if (path.points.size() == 0) {
			return;
		}
		for (int i = 0; i < path.points.size(); i++) {

			int curPathX = path.points.get(i).x;
			int curPathY = path.points.get(i).y;

			path.points.get(i).rateX = (double)curPathX/(double)backgroundWidth;
			path.points.get(i).rateY = (double)curPathY/(double)backgroundHeight;
//			System.out.println("rateX : " + path.points.get(i).rateX);

		}
		
	}
	
	@Override
	protected void setFeatureByRate(int backgroundWidth, int backgroundHeight) {
		// TODO Auto-generated method stub
		super.setFeatureByRate(backgroundWidth, backgroundHeight);
		
		if (path.points.size() == 0) {
			return;
		}
		for (int i = 0; i < path.points.size(); i++) {

			double rateX = path.points.get(i).rateX;
			double rateY = path.points.get(i).rateY;

			path.points.get(i).x = (int)rateX * backgroundWidth;
			path.points.get(i).y = (int)rateY * backgroundHeight;

		}
		
	}

}

class ClickLinePanel extends ClickGraphicPanel {

	public ClickLinePanel(int x, int y, int width, int height,
			DrawableObject drawableObj, NoteManager nm) {
		super(x, y, width, height, drawableObj, nm);
		margin = 10;
		// TODO Auto-generated constructor stub

		MouseInputAdapter mia = new MouseInputAdapter() {

			public void mouseDragged(MouseEvent e) {
				Point sizeControlPoint = csc.getSizeControlPoint();
				if (csc.isMoveFlag()) {

					cp.relocate(cp.getLocation().x + e.getX()
							- sizeControlPoint.x, cp.getLocation().y + e.getY()
							- sizeControlPoint.y);

				}

			}

			public void mousePressed(MouseEvent e) {

				if (csc.isDrag(e, margin)) {
					csc.setMoveFlag(true);
				}
				csc.setSizeControlPoint(new Point(e.getX(), e.getY()));
			}

			public void mouseMoved(MouseEvent e) {

				if (csc.isDrag(e, margin)) {
					setCursor(StateManager.moveCursor);
				} else {
					setCursor(sm.getCurStateCursor());
				}

			}
		};

		this.addMouseListener(mia);
		this.addMouseMotionListener(mia);

	}


}
