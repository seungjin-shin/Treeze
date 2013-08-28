package com.treeze.draw;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

/*
 * PostItPanel 부분 포스트잇(필기를 담고 접었다 폈다 할 수 있음)
 */
public class PostItPanel extends ComponentJPanel {

	// size control은 여기서 전부다.

	int x1;
	int y1;

	Point point = new Point();
	PostItPanel postItPanel;
	int margin = 5;

	// essential
	GridLayoutManager glm;
	JPanel pptPanel;
	private ComponentSizeController psc;
	StateManager sm;

	// it is componet of postitpanel
	private FoldButton foldButton;
	private WasteBasketButton wasteBasketButton;
	private PostItHeadPanel headPanel;
	private PostItBodyPanel bodyPanel;

	public PostItPanel(int x, int y, int width, int height) {
		// TODO Auto-generated constructor stub

		this.postItPanel = this;

		// size control
		psc = new ComponentSizeController(this);
		psc.setCurFoldMode(ComponentSizeController.FOLD_MODE_UNFOLD);
		psc.setOriginalWidth(width);
		psc.setOriginalHeight(height);

		// state control
		sm = StateManager.getStateManager();

		// make postitpanel design
		glm = new GridLayoutManager(this);
		glm.addGrid(new PostItHeadPanel(pptPanel, this), 1, 1, 1, 1, 1, 0, this);
		glm.addGrid(new PostItBodyPanel(this), 1, 2, 1, 1, 1, 1, this);
		psc.setBound(x, y, width, height);
		// this.setMinimumSize(new Dimension(40, 40));

	}

	public int getOriginalWidth() {
		return psc.getOriginalWidth();
	}

	public int getOriginalHeight() {
		return psc.getOriginalHeight();
	}

	public void setOriginalHeight(int originalHeight) {
		psc.setOriginalHeight(originalHeight);
	}

	public NoteManager getNoteManager() {
		return this.getBodyPanel().getNoteManager();
	}

	@Override
	public void addToPanel(JPanel jpanel, NoteManager nm) {
		// TODO Auto-generated method stub
		PPTPanel pptPanel = (PPTPanel) jpanel;

		pptPanel.add(this);
		pptPanel.validate();

		this.getNoteManager().restore();
		this.getNoteManager().repaint();

	}

	public FoldButton getFoldButton() {
		return foldButton;
	}

	public void setFoldButton(FoldButton foldButton) {
		this.foldButton = foldButton;
	}

	public WasteBasketButton getWasteBasketButton() {
		return wasteBasketButton;
	}

	public void setWasteBasketButton(WasteBasketButton wasteBasketButton) {
		this.wasteBasketButton = wasteBasketButton;
	}

	public PostItHeadPanel getHeadPanel() {
		return headPanel;
	}

	public void setHeadPanel(PostItHeadPanel headPanel) {
		this.headPanel = headPanel;
	}

	public PostItBodyPanel getBodyPanel() {
		return bodyPanel;
	}

	public void setBodyPanel(PostItBodyPanel bodyPanel) {
		this.bodyPanel = bodyPanel;
	}

	public ComponentSizeController getPsc() {
		return psc;
	}

	public void setPsc(ComponentSizeController psc) {
		this.psc = psc;
	}

}

class PostItHeadPanel extends JPanel {

	GridLayoutManager glm;
	JPanel postItPanel;
	int postItHeadPanelHeight = 30;

	public PostItHeadPanel(JPanel pptPanel, PostItPanel postItPanel) {
		// TODO Auto-generated constructor stub
		glm = new GridLayoutManager(this);
		this.postItPanel = postItPanel;

		FoldButton fb = new FoldButton(postItHeadPanelHeight,
				postItHeadPanelHeight, ((PostItPanel) postItPanel));
		WasteBasketButton wbb = new WasteBasketButton(postItHeadPanelHeight,
				postItHeadPanelHeight, postItPanel);

		postItPanel.setHeadPanel(this);
		postItPanel.setFoldButton(fb);
		postItPanel.setWasteBasketButton(wbb);
		
		//
		glm.addGrid(fb, 1, 1, 1, 1, 0, 1, this);
		glm.addGrid(new JPanel(), 2, 1, 1, 1, 1, 1, this);
		glm.addGrid(wbb, 3, 1, 1, 1, 0, 1, this);

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Image bg = new ImageIcon(getClass().getResource(
				Util.IMG_ADDR + "postithead.png")).getImage();
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}

}

class PostItBodyPanel extends JPanel {

	DrawablePanel dp;
	PostItPanel postItPanel;

	StateManager sm;
	Point mousePressedPoint;
	Point sizeControlPoint;
	PostItBodyPanel postItBodyPanel;

	ComponentSizeController postitSizeController;
	ComponentSizeController bodySizeController;

	public PostItBodyPanel(final PostItPanel postItPanel) {
		// TODO Auto-generated constructor stub
		dp = new DrawablePanel(this, Util.IMG_ADDR + "postitbody.png");
		this.postItPanel = postItPanel;
		this.postitSizeController = postItPanel.getPsc();
		this.bodySizeController = new ComponentSizeController(this);
		this.sm = postItPanel.sm;
		postItBodyPanel = this;
		sizeControlPoint = postitSizeController.sizeControlPoint;

		postItPanel.setBodyPanel(this);

		MouseInputAdapter mia = new MouseInputAdapter() {

			public void mousePressed(MouseEvent e) {
				mousePressedPoint = new Point(e.getX(), e.getY());

				sm.setCurMouseMode(StateManager.MOUSE_STATE_PRESSED);

				if (e.getClickCount() == 1) {

					if (bodySizeController.isLeftBottomChangeSize(e, 10)
							|| bodySizeController
									.isRightBottomChangeSize(e, 10)) {

						if (bodySizeController.isLeftBottomChangeSize(e, 10)) {

							postitSizeController
									.setLeftBottomChangeSizeFlag(true);

						} else {

							postitSizeController
									.setRightBottomChangeSizeFlag(true);

						}
						// 안해도 되는 행위 StateManager에 값을 넣어준다.
						postitSizeController.setChangeSizeFlag(true);
						sizeControlPoint.x = e.getX();
						sizeControlPoint.y = e.getY();

					} else if (bodySizeController.isDrag(e, 10)) {
						// 안해도 되는 행위 StateManager에 값을 넣어준다.
						postitSizeController.setMoveFlag(true);
						sizeControlPoint.x = e.getX();
						sizeControlPoint.y = e.getY();

					} else {

						if (postitSizeController.getCurFoldMode() == ComponentSizeController.FOLD_MODE_FOLD) {
							return;
						}

						if (sm.getCurNoteMode() == StateManager.NOTE_MODE_PEN) {

							getNoteManager().initPath();

						} else if (sm.getCurNoteMode() == StateManager.NOTE_MODE_FIGURE) {

						}
					}
				}

			}

			public void mouseReleased(MouseEvent e) {

				sm.setExMouseMode(sm.getCurMouseMode());
				sm.setCurMouseMode(StateManager.MOUSE_STATE_RELEASED);

				if (postitSizeController.getCurFoldMode() == ComponentSizeController.FOLD_MODE_FOLD) {
					return;
				}
				if (postitSizeController.isChangeSizeFlag() == true
						|| postitSizeController.isMoveFlag() == true) {
					postitSizeController.setChangeSizeFlag(false);
					postitSizeController.setMoveFlag(false);
					return;
				}
				// 공통된 행동
				if (postitSizeController.isChangeSizeFlag() == false
						&& postitSizeController.isMoveFlag() == false
						&& sm.getExMouseMode() == StateManager.MOUSE_STATE_DRAGGED) {
					if (sm.getCurNoteMode() == StateManager.NOTE_MODE_PEN) {
						getNoteManager().makePathComplete();
					} else if (sm.getCurNoteMode() == StateManager.NOTE_MODE_FIGURE) {
						if (sm.getCurFigureMode() == StateManager.FIGURE_TYPE_CIRCLE
								|| sm.getCurFigureMode() == StateManager.FIGURE_TYPE_REC) {

							getNoteManager().makeFigureComplete();

						}
					}
				}

			}

			public void mouseDragged(MouseEvent e) {
				sm.setExMouseMode(sm.getCurMouseMode());
				sm.setCurMouseMode(StateManager.MOUSE_STATE_DRAGGED);

				if (postitSizeController.isChangeSizeFlag()) {

					if (postitSizeController.isLeftBottomChangeSizeFlag()) {
						System.out.println("11111111");
						postitSizeController.setLeftBottomSize(e);
					} else if (postitSizeController
							.isRightBottomChangeSizeFlag()) {
						System.out.println("222222222");
						postitSizeController.setRightBottomSize(e);
					}

				} else if (postitSizeController.isMoveFlag()) {

					postitSizeController.setLocation(
							postItPanel.getLocation().x + e.getX()
									- sizeControlPoint.x,
							postItPanel.getLocation().y + e.getY()
									- sizeControlPoint.y);

				} else {

					if (postitSizeController.getCurFoldMode() == ComponentSizeController.FOLD_MODE_FOLD) {
						return;
					}

					if (sm.getCurNoteMode() == StateManager.NOTE_MODE_PEN) {
						// shift를 누르고 있으므로 직선을 그린다.
						if (sm.getCurLineMode() == StateManager.LINE_MODE_STRAIGHT) {

							getNoteManager().makePath(
									new Point(e.getX(), mousePressedPoint.y),
									sm.color, sm.bs);

						} else {

							getNoteManager().makePath(
									new Point(e.getX(), e.getY()), sm.color,
									sm.bs);

						}

					} else if (sm.getCurNoteMode() == StateManager.NOTE_MODE_FIGURE) {
						getNoteManager().makeFigure(mousePressedPoint.x,
								mousePressedPoint.y,
								e.getX() - mousePressedPoint.x,
								e.getY() - mousePressedPoint.y,
								sm.getCurFigureMode());
					}
				}

			}

			public void mouseMoved(MouseEvent e) {


				if (bodySizeController.isRightBottomChangeSize(e, 10) || bodySizeController.isLeftBottomChangeSize(e, 10)) {

					if (bodySizeController.isRightBottomChangeSize(e, 10)) {
						setCursor(StateManager.changeSizeRightCursor);
					} else if(bodySizeController.isLeftBottomChangeSize(e, 10)){
						setCursor(StateManager.changeSizeLeftCursor);
					}

				} else if (bodySizeController.isDrag(e, 10)) {

					setCursor(StateManager.moveCursor);
				} else {

					setCursor(sm.getCurStateCursor());
				}
			}

			public void mouseClicked(MouseEvent e) {			

				int clickCount = e.getClickCount();
				int clickButton = e.getButton();

				if (clickButton == StateManager.CLICK_BUTTON_LEFT) {

					if (clickCount == 1) {

						if (sm.getCurNoteMode() == StateManager.NOTE_MODE_FIGURE) {
							if (sm.getCurFigureMode() == StateManager.FIGURE_TYPE_STAR) {
								getNoteManager().drawImage(mousePressedPoint.x, mousePressedPoint.y, 25, 25, NoteManager.IMG_TYPE_STAR);
							}

						} else if (sm.getCurNoteMode() == StateManager.NOTE_MODE_ERASER) {

//							getNoteManager().erase(mousePressedPoint.x, mousePressedPoint.y);

						}

					} else if (clickCount == 2) {
						// 첫번째 클릭했던 내용을 지워버리면된다.
						if (sm.getCurNoteMode() == StateManager.NOTE_MODE_FIGURE) {
							if (sm.getCurFigureMode() == StateManager.FIGURE_TYPE_STAR) {
								getNoteManager().removeLastDrawableObj();
							}
						}
						// 그다음 두번째에는 textarea를 넣어버리면됨
						if (sm.getCurNoteMode() != StateManager.NOTE_MODE_ERASER)
							getNoteManager().addMemo(mousePressedPoint.x, mousePressedPoint.y, 100, 100);
					}

				} else if (clickButton == StateManager.CLICK_BUTTON_RIGHT) {

					getNoteManager().addPostIt(mousePressedPoint.x, mousePressedPoint.y, 100, 100);

				}


			}

		};		
		this.addMouseListener(mia);
		this.addMouseMotionListener(mia);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		dp.paintComponent(g);

	}

	public String getBackgroundFilepath() {
		return dp.getBackgroundFilepath();
	}

	public void setBackgroundFilepath(String backgroundFilepath) {
		dp.setBackgroundFilepath(backgroundFilepath);
	}

	public NoteManager getNoteManager() {
		return dp.getNoteManager();
	}

}
