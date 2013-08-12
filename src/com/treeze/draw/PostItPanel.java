package com.treeze.draw;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import com.treeze.util.Var;


/*
 * PostItPanel 부분 포스트잇(필기를 담고 접었다 폈다 할 수 있음)
 */
public class PostItPanel extends JPanel {

	int x1;
	int y1;

	boolean changeSize = false;
	boolean moveFlag = false;
	Point point = new Point();

	NoteManager nm;

	PPTPanel parentPanel;

	private int curFoldMode;

	public static final int FOLD_MODE_UNFOLD = 0;
	public static final int FOLD_MODE_FOLD = 1;

	int originalX;
	int originalY;

	int originalWidth;
	int originalHeight;

	public PostItPanel(int width, int height, final PPTPanel parentPanel) {
		// TODO Auto-generated constructor stub

		// this.set
		this.setLayout(null);
		this.setSize(width, height);
		nm = getNoteManager();
		this.parentPanel = parentPanel;
		curFoldMode = FOLD_MODE_UNFOLD;

		originalX = getX();
		originalY = getY();

		originalWidth = width;
		originalHeight = height;

		MouseInputAdapter mia = new MouseInputAdapter() {

			public void mouseDragged(MouseEvent e) {

				if (changeSize) {
					setSize(getWidth() + e.getX() - point.x, getHeight() + e.getY() - point.y);
					point.x = e.getX();
					point.y = e.getY();

				} else if (moveFlag) {

					setLocation(getLocation().x + e.getX() - point.x, getLocation().y + e.getY() - point.y);
					
				} else {
					
					if (curFoldMode == FOLD_MODE_FOLD) {
						return;
					}
					
					if (parentPanel.getCurNoteMode() == PPTPanel.NOTE_MODE_PEN) {
						// shift를 누르고 있으므로 직선을 그린다.
						if (parentPanel.getLineMode() == PPTPanel.LINE_MODE_STRAIGHT) {

							nm.makePath(new Point(e.getX(), y1),
									parentPanel.color, parentPanel.bs);

						} else {

							nm.makePath(new Point(e.getX(), e.getY()),
									parentPanel.color, parentPanel.bs);

						}

					} else if (parentPanel.getCurNoteMode() == PPTPanel.NOTE_MODE_FIGURE) {
						nm.makeFigure(x1, y1, e.getX() - x1, e.getY() - y1, parentPanel.getCurFigureMode());
					}
				}

			}

			public void mousePressed(MouseEvent e) {

				x1 = e.getX();
				y1 = e.getY();

				if (e.getClickCount() == 1) {

					if (isChangeSize(e)) {
						changeSize = true;
						point.x = e.getX();
						point.y = e.getY();

					} else if (isDrag(e)) {
						// System.out.println("무브변");
						moveFlag = true;
						point.x = e.getX();
						point.y = e.getY();
						// 그외에 부분을 선택했으 경우
					} else {
						
						if (curFoldMode == FOLD_MODE_FOLD) {
							return;
						}
						
						if (parentPanel.getCurNoteMode() == PPTPanel.NOTE_MODE_PEN) {

							nm.initPath();

						} else if (parentPanel.getCurNoteMode() == PPTPanel.NOTE_MODE_FIGURE) {

						}
					}
				}

			}

			public void mouseReleased(MouseEvent e) {

				changeSize = false;
				moveFlag = false;
				
				if (curFoldMode == FOLD_MODE_FOLD) {
					return;
				}
				//공통된 행동
				if(parentPanel.getCurNoteMode() == PPTPanel.NOTE_MODE_PEN) {
					nm.makePathComplete();
				}else if(parentPanel.getCurNoteMode() == PPTPanel.NOTE_MODE_FIGURE) {
					if(parentPanel.getCurFigureMode() == PPTPanel.FIGURE_TYPE_CIRCLE || parentPanel.getCurFigureMode() == PPTPanel.FIGURE_TYPE_REC) {
						
						nm.makeFigureComplete();
						
					}
				}
				
			}

			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mouseMoved(arg0);

			}

			public void mouseClicked(MouseEvent e) {
				// System.out.println(e.getX() + " " + e.getY());
				int clickCount = e.getClickCount();
				// System.out.println("click count" + clickCount);

				if (clickCount == 1) {

					if (parentPanel.getCurNoteMode() == parentPanel.NOTE_MODE_FIGURE) {

						if(parentPanel.getCurFigureMode() == PPTPanel.FIGURE_TYPE_STAR) {
							nm.drawImage(x1, y1, 20, 20, Var.IMG_ADDR +"star.png");
						}
					}

				} else if (clickCount == 2) {
					if (getCurFoldMode() == FOLD_MODE_FOLD) {

						setCurFoldMode(FOLD_MODE_UNFOLD);
						setSize(originalWidth, originalHeight);

					} else if (getCurFoldMode() == FOLD_MODE_UNFOLD) {

						if (parentPanel.getCurNoteMode() == PPTPanel.NOTE_MODE_FIGURE) {
							nm.removeLastDrawableObj();
						}
						// 그다음 두번째에는 textarea를 넣어버리면됨.
						nm.addTextField(x1, y1, 50, 50);
					}
				}

			}

		};

		setFoldBtn(getX(), getY(), 20, 20);
		addMouseListener(mia);
		addMouseMotionListener(mia);

	}

	protected void setCurFoldMode(int mode) {
		curFoldMode = mode;
	}

	protected int getCurFoldMode() {
		return curFoldMode;
	}

	private void setFoldBtn(int x, int y, int width, int height) {

		FoldButton foldButton = new FoldButton(x + this.getWidth() - width, y,
				width, height, this);
		this.add(foldButton);

	}

	public NoteManager getNoteManager() {

		if (nm == null) {
			nm = new NoteManager(this);
		}
		return nm;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image bg = new ImageIcon(Var.IMG_ADDR + "memo.png").getImage();
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);

		nm.draw(g);
		nm.drawLineByRealTime(g);
		nm.drawFigureByRealTime(g);
	}

	private boolean isChangeSize(MouseEvent e) {
		if (e.getX() <= getWidth() && e.getX() > getWidth() - 10
				&& e.getY() <= getHeight() && e.getY() > getHeight() - 10) {
			return true;

		} else {
			return false;
		}

	}

	private boolean isDrag(MouseEvent e) {
		if (e.getY() < 10) {			
			return true;
		} else {
			return false;
		}
	}



}