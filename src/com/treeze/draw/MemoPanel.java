package com.treeze.draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;

/*
 * MemoPanel 해당 패널은 메모장으로서 글을 닮는 패널이다.
 */
public class MemoPanel extends ComponentJPanel {

	Point point = new Point();
	JTextArea textArea;
	int margin;
	final static int TEXTAREA_MARGIN =0;
	// ComponentSizeController csc;
	int foldSize;
	MemoPanel memoPanel;

	private PPTPanel ptPanel;
	private NoteManager nm;

	StateManager sm;

	public void setAllFocusable(boolean arg0) {
		// TODO Auto-generated method stub
		super.setFocusable(arg0);
		textArea.setFocusable(arg0);
	}

	public MemoPanel(final int x, final int y, final int width,
			final int height, int backgroundWidth, int backgroundHeight) {
		// TODO Auto-generated constructor stub
		super();
		margin = 3;
		foldSize = 30;
		memoPanel = this;
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin,
				margin));

		textArea = new JTextArea();
		textArea.setBounds(0, 0, width, height);

		this.add(textArea);

		csc = new ComponentSizeController(this);
		csc.setBound(x, y, width, height);
		setRate(backgroundWidth, backgroundHeight);

		sm = StateManager.getStateManager();

		textArea.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				ptPanel.repaint();
				textArea.setBackground(new Color(0, 0, 0, 0));
				memoPanel.setBackground(new Color(0, 0, 0, 0));
				System.out.println("[focusLost]");
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				textArea.setBackground(Color.white);
				memoPanel.setBackground(Color.gray);
				System.out.println("[focusGain]");
			}
		});
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				textAreaChangeSize();
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				textAreaChangeSize();			
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
			
			}
		});
//		textArea.addKeyListener(new KeyListener() {   //한글입력시 한글 리스너가 이상하게 동작해서 DocumentListner를 사용//
//
//			@Override
//			public void keyTyped(KeyEvent arg0) {
//
//				// TODO Auto-generated method stub
//				System.out.println("type");
//
//				if (getClickPanel().isVisible()) {
//					getClickPanel().removeClicked();
//				}
//
//				Dimension textAreaDimention = textArea.getPreferredSize();
//				getClickPanel().setVisible(false);
//				if (textAreaDimention.width < 10) {
//					csc.setSize(60, 60);
//					return;
//				}
//
//				// Enter key
//
//					textArea.setSize(textArea.getPreferredSize());
//					csc.setSize(textAreaDimention.width
//							+ textArea.getFont().getSize() + TEXTAREA_MARGIN,
//							textAreaDimention.height
//									+ textArea.getFont().getSize()
//									
//									);
//					getClickPanel().setSize(
//							textAreaDimention.width
//									+ textArea.getFont().getSize()
//									+ TEXTAREA_MARGIN,
//							textAreaDimention.height
//									+ textArea.getFont().getSize()
//									);
////				} else {
////
////					textArea.setSize(textArea.getPreferredSize());
////					csc.setSize(textAreaDimention.width
////							+ textArea.getFont().getSize() + TEXTAREA_MARGIN,
////							textAreaDimention.height
////									+ textArea.getFont().getSize()
////									);
////					getClickPanel().setSize(
////							textAreaDimention.width
////									+ textArea.getFont().getSize()
////									+ TEXTAREA_MARGIN,
////							textAreaDimention.height
////									+ textArea.getFont().getSize()
////									);
////				}
//
//			}
//
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//				System.out.println("key Released");
//			}
//
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//				System.out.println("key Press");
//			}
//		});

		MouseInputAdapter mia = new MouseInputAdapter() {

			public void mouseDragged(MouseEvent e) {
				
				if (sm.getCurNoteMode() == StateManager.NOTE_MODE_GRAB) {

				if (csc.isChangeSize(e, margin) || csc.isDrag(e, margin)) {

					Point point = csc.getTransformedPoint(e);
					getClickPanel().relocate(point.x, point.y);

				}
				}

			}

			public void mousePressed(MouseEvent e) {
				// textArea.setBackground(Color.white);
				// memoPanel.setBackground(Color.gray);
				if (sm.getCurNoteMode() == StateManager.NOTE_MODE_GRAB) {
					if (csc.isChangeSize(e, margin) || csc.isDrag(e, margin)) {


						getClickPanel().setFocusable(true);
						memoPanel.setFocusable(false);
						getClickPanel().requestFocus();
						getClickPanel().grabFocus();
						getClickPanel().setVisible(true);

					}
				}

			}

			public void mouseMoved(MouseEvent e) {

				if (sm.getCurNoteMode() == StateManager.NOTE_MODE_GRAB) {
					if (csc.isChangeSize(e, margin) || csc.isDrag(e, margin)) {

						getClickPanel().setFocusable(true);
						setCursor(StateManager.moveCursor);

					}
				}

			}
		};

		addMouseMotionListener(mia);
		addMouseListener(mia);
		textArea.addMouseListener(mia);
		textArea.addMouseMotionListener(mia);
	}

	protected void textAreaChangeSize() {
		// TODO Auto-generated method stub


		// TODO Auto-generated method stub
	
		if(getClickPanel()==null)
		{
			return;
		}
		if (getClickPanel().isVisible()) {
			getClickPanel().removeClicked();
		}

		Dimension textAreaDimention = textArea.getPreferredSize();
		getClickPanel().setVisible(false);
		if (textAreaDimention.width < 10) {
			csc.setSize(60, 60);
			return;
		}

		
			textArea.setSize(textArea.getPreferredSize());
			csc.setSize(textAreaDimention.width
					+ textArea.getFont().getSize() + TEXTAREA_MARGIN,
					textAreaDimention.height
							+ textArea.getFont().getSize()
							
							);
			getClickPanel().setSize(
					textAreaDimention.width
							+ textArea.getFont().getSize()
							+ TEXTAREA_MARGIN,
					textAreaDimention.height
							+ textArea.getFont().getSize()
							);
		

	
		
	}

	public void setText(String text) {
		textArea.setText(text);
		Dimension textAreaDimention = textArea.getPreferredSize();
		csc.setSize(textAreaDimention.width, textAreaDimention.height);
		textArea.setSize(textArea.getPreferredSize());
	}

	private void setAllBackground(Color color) {
		this.setBackground(color);
		textArea.setBackground(color);
	}

	public String getText() {
		return textArea.getText();
	}



	@Override
	public void addToPanel(JPanel jpanel, NoteManager nm) {
		// TODO Auto-generated method stub
		// ptPanel = (PPTPanel) jpanel;
		// this.nm = nm;
		//
		// setClickPanel(new ClickMemoPanel(this.getX(), this.getY(),
		// this.getWidth(), this.getHeight(), memoPanel, nm));
		//
		//
		//
		// getClickPanel().setVisible(false);
		// this.setAllFocusable(true);
		// // this.setAllBackground(new Color(0,0,0,0));
		// this.setBackground(Color.gray);
		// this.setBackground(Color.white);
		//
		// jpanel.add(this);
		// jpanel.setVisible(false);
		// jpanel.setVisible(true);
		//
		// jpanel.repaint();
		
		ptPanel = (PPTPanel) jpanel;
		this.nm = nm;

		setClickPanel(new ClickMemoPanel(this.getX(), this.getY(),
				this.getWidth(), this.getHeight(), memoPanel, nm));
		this.setAllBackground(new Color(0, 0, 0, 0));
		this.setAllFocusable(true);

		jpanel.add(this);
		jpanel.setVisible(false);
		jpanel.setVisible(true);

		getClickPanel().setVisible(false);

	}

	public void addToPanelByClick(JPanel jpanel, NoteManager nm) {
		// TODO Auto-generated method stub
		ptPanel = (PPTPanel) jpanel;
		this.nm = nm;

		setClickPanel(new ClickMemoPanel(this.getX(), this.getY(),
				this.getWidth(), this.getHeight(), memoPanel, nm));

		jpanel.add(this);
		jpanel.setVisible(false);
		jpanel.setVisible(true);

		getClickPanel().setVisible(false);
		this.setAllFocusable(true);

	}

}

class ClickMemoPanel extends ClickComponentPanel {

	protected ClickMemoPanel(int x, int y, int width, int height,
			final ComponentJPanel compJpanel, NoteManager nm) {
		super(x, y, width, height, compJpanel, nm);
		// TODO Auto-generated constructor stub
		this.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				compJpanel.setFocusable(false);

			}
		});
	}

	@Override
	protected void contentFocused() {
		// TODO Auto-generated method stub
		System.out.println("content focused");

		MemoPanel mp = (MemoPanel) compJpanel;
		mp.textArea.setBackground(Color.white);
		mp.setBackground(Color.gray);
		if (!mp.textArea.hasFocus()) {

			mp.textArea.grabFocus();
			mp.textArea.requestFocus();
			this.setFocusable(false);
			mp.setFocusable(true);

		}

		setCursor(mp.textArea.getCursor());

	}

}