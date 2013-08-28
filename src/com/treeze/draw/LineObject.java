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

	public LineObject(Path path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}

	@Override
	public void draw(Graphics g, JPanel jpanel) {
		// TODO Auto-generated method stub

		Graphics2D g2 = (Graphics2D) g;

		for (int i = 0; i < path.points.size() - 1; i++) {

			g2.setColor(path.color);
			g2.setStroke(path.bs);
			g2.drawLine(path.points.get(i).x, path.points.get(i).y,
					path.points.get(i + 1).x, path.points.get(i + 1).y);
		}
	}

	@Override
	public boolean isClick(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub
//		System.out.println("position : " + x + " " + y);
		if (path.points.size() == 0) {
			return false;
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

		if ((largestPoint.x >= x && x >= smallestPoint.x)
				&& (largestPoint.y >= y && y >= smallestPoint.y)) {

			this.clickPanelWidth = largestPoint.x - smallestPoint.x;
			this.clickPanelHeight = largestPoint.y - smallestPoint.y;
			this.clickPanelX = smallestPoint.x;
			this.clickPanelY = smallestPoint.y;

			this.width = largestPoint.x - smallestPoint.x;
			this.height = largestPoint.y - smallestPoint.y;
			this.x = smallestPoint.x;
			this.y = smallestPoint.y;

			return true;
		} else {
			return false;
		}
	}

	@Override
	public void move(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub
		int diffrenceX = x - this.x;
		int diffrenceY = y - this.y;

		System.out.println("x y : " + x + " " + this.x);
		System.out.println("diffrence : " + diffrenceX + " " + diffrenceY);

		for (int i = 0; i < path.points.size(); i++) {
			int curPathX = path.points.get(i).x;
			int curPathY = path.points.get(i).y;

			path.points.get(i).x = curPathX + diffrenceX;
			path.points.get(i).y = curPathY + diffrenceY;
		}

		this.x = this.x + diffrenceX;
		this.y = this.y + diffrenceY;

	}

	@Override
	public void setClick(int x, int y, NoteManager nm) {
		// TODO Auto-generated method stub
		if (isClick(x, y, nm)) {
			
			clickPanel=new ClickLinePanel(clickPanelX, clickPanelY,
					clickPanelWidth + 10, clickPanelHeight + 10, this, nm);

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
