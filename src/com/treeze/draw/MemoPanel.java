package com.treeze.draw;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.MouseInputAdapter;


/*
 * MemoPanel 해당 패널은 메모장으로서 글을 닮는 패널이다.
 */
public class MemoPanel extends JScrollPane {

	boolean changeSize = false;
	boolean moveFlag = false;
	Point point = new Point();
	JTextArea textArea;	
	int margin;

	public MemoPanel() {
		// TODO Auto-generated constructor stub
		margin = 10;
		this.setBackground(Color.GRAY);
		this.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));
		textArea = new JTextArea();
		textArea.setBackground(Color.YELLOW);

		// add(j);
		getViewport().add(textArea, null);
		textArea.setVisible(true);
		MouseInputAdapter mia = new MouseInputAdapter() {

			public void mouseDragged(MouseEvent e) {
				if (changeSize) {
					setSize(getWidth() + e.getX() - point.x, getHeight() + e.getY() - point.y);
					
					textArea.setSize(getWidth() + e.getX() - point.x - margin, getHeight() + e.getY() - point.y - 10);
					point.x = e.getX();
					point.y = e.getY();
				}

			}

			public void mousePressed(MouseEvent e) {
				if (e.getX() <= getWidth() && e.getX() > getWidth() - 10
						&& e.getY() <= getHeight()
						&& e.getY() > getHeight() - 10) {
					System.out.println("사이즈변화 클릭" + e.getX() + "  " + e.getY());
					changeSize = true;
					point.x = e.getX();
					point.y = e.getY();

				}

			}

			public void mouseReleased(MouseEvent e) {

				changeSize = false;
			}

			public void mouseClicked(MouseEvent e) {
			}
		};
		addMouseMotionListener(mia);
		addMouseListener(mia);

	}

}