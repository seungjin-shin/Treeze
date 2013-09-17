package com.treeze.frame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.treeze.Abstract.ImgBtn;
import com.treeze.data.ClassInfo;
import com.treeze.data.MindNode;
import com.treeze.data.TreezeStaticData;
import com.treeze.data.User;
import com.treeze.frame.TicketWriteFrame.ButtonField;
import com.treeze.frame.TicketWriteFrame.LogoPanel;
import com.treeze.frame.TicketWriteFrame.RoundPanel;
import com.treeze.frame.TicketWriteFrame.UnderLineJLabel;
import com.treeze.frame.TicketWriteFrame.WriteBtn;
import com.treeze.frame.TicketWriteFrame.WriteField;
import com.treeze.frame.TicketWriteFrame.WriteTextArea;
import com.treeze.uploadthread.TicketUpLoadThread;

public class BugReportFrame extends JFrame{

	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	Insets insets = new Insets(0, 0, 0, 0);
	JPanel fullPanel = new JPanel();
	LogoPanel logoPanel = new LogoPanel();


	JTextArea textArea = new JTextArea();
	ClassInfo classInfo;
	User user;
	

	public BugReportFrame( ) {
		// TODO Auto-generated constructor stub

		this.setSize(800, 500);
		
		gbc.fill = GridBagConstraints.BOTH;



		this.user = User.getInstance();
		this.setLayout(new BorderLayout());
		this.add(fullPanel);

		fullPanel.setBackground(TreezeStaticData.TREEZE_BG_COLOR);
		fullPanel.setLayout(gbl);
		addGrid(gbl, gbc, new RoundPanel(), 0, 1, 1, 1, 1, 1, fullPanel);
		// fullPanel.setLayout(gbl);
		// addGrid(gbl, gbc, new RoundPanel(), 0, 0, 1, 1, 1, 1, fullPanel);
		// this.setResizable(false);

		setVisible(true);
		logoPanel.setVisible(true);
	}

	private void addGrid(GridBagLayout gbl, GridBagConstraints gbc,
			Component c, int gridx, int gridy, int gridwidth, int gridheight,
			int weightx, int weighty, Container container) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;

		gbc.insets = insets;

		gbl.setConstraints(c, gbc);

		container.add(c);
	}

	class RoundPanel extends JPanel {

		protected Color shadowColor = Color.black;
		/** Sets if it drops shadow */
		protected boolean shady = true;
		/** Sets if it has an High Quality view */
		protected boolean highQuality = true;
		protected int shadowGap = 1;
		/** The offset of shadow. */
		protected int shadowOffset = 1;
		/** The transparency value of shadow. ( 0 - 255) */
		protected int shadowAlpha = 150;
		protected int strokeSize = 10;
		protected Dimension arcs = new Dimension(30, 30);

		public RoundPanel() {
			// TODO Auto-generated constructor stub
			this.setBackground(TreezeStaticData.TREEZE_BG_COLOR);
			// this.setSize(10, 10);
			this.setLayout(gbl);
			insets.set(20, 20, 0, 0);
			JPanel dumy = new JPanel();
			dumy.setBackground(new Color(0,0,0,0));
			
			addGrid(gbl, gbc, logoPanel,    0, 0, 1, 1, 2, 3, this);
			addGrid(gbl, gbc, dumy, 1, 0, 1, 1, 5, 3, this);
			insets.set(10, 20, 5, 20);
			addGrid(gbl, gbc, new WriteField(), 0, 1, 2, 1, 1, 10, this);
			insets.set(5, 20, 5, 20);
			addGrid(gbl, gbc, new ButtonField(), 0, 2, 2, 1, 1, 1, this);

		}

		protected void paintComponent(Graphics g) {
			int width = getWidth();
			int height = getHeight();
			int shadowGap = this.shadowGap;
			Color shadowColorA = new Color(shadowColor.getRed(),
					shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
			Graphics2D graphics = (Graphics2D) g;

			// Sets antialiasing if HQ.
			if (highQuality) {
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
			}

			// Draws shadow borders if any.
			if (shady) {
				graphics.setColor(shadowColorA);
				graphics.fillRoundRect(shadowOffset,// X position
						shadowOffset,// Y position
						width - strokeSize - shadowOffset, // width
						height - strokeSize - shadowOffset, // height
						arcs.width, arcs.height);// arc Dimension
			} else {
				shadowGap = 1;
			}
			graphics.setColor(Color.WHITE);
			graphics.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);
			graphics.setColor(TreezeStaticData.TREEZE_BG_COLOR);
			graphics.setStroke(new BasicStroke(strokeSize));
			graphics.fillRoundRect(5, 5, width - 10, height - 10, arcs.width,
					arcs.height);
	
			graphics.setStroke(new BasicStroke());

		}
	}

	class LogoPanel extends JPanel {
		ImageIcon icon;
		public LogoPanel() {
			// TODO Auto-generated constructor stub

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			// super.paint(g);
			if(icon==null){
				icon = TreezeStaticData.makeResizedImageIcon(getWidth(), getHeight(), TreezeStaticData.LOGO_IMG);
			}
			g.drawImage(icon.getImage(), 0, 0,
					 null);
		}
	}

	class WriteField extends JPanel {
		public WriteField() {
			// TODO Auto-generated constructor stub
			this.setBackground(TreezeStaticData.TREEZE_BG_COLOR);
			this.setLayout(gbl);
			addGrid(gbl, gbc, new UnderLineJLabel("Bug Report"), 1, 1, 1, 1, 1,
					1, this);
			addGrid(gbl, gbc, new JLabel(), 1, 2, 1, 1, 1, 10, this);
			addGrid(gbl, gbc, new WriteTextArea(), 2, 1, 1, 2, 8, 1, this);
		}
	}

	class WriteTextArea extends JScrollPane {

		protected Color shadowColor = Color.black;
		/** Sets if it drops shadow */
		protected boolean shady = true;
		/** Sets if it has an High Quality view */
		protected boolean highQuality = true;
		protected int shadowGap = 1;
		/** The offset of shadow. */
		protected int shadowOffset = 1;
		/** The transparency value of shadow. ( 0 - 255) */
		protected int shadowAlpha = 150;
		protected int strokeSize = 1;
		protected Dimension arcs = new Dimension(30, 30);

		public WriteTextArea() {
			// TODO Auto-generated constructor stub
			this.setBackground(new Color(0, 0, 0, 0));
			// this.setSize(10, 10);
			// this.setLayout(new BorderLayout());

			// textArea.setBackground(new Color(0,0,0,0));
			this.setBorder(new EmptyBorder(10, 10, 10, 10));
			this.getViewport().add(textArea);
			
		}

		protected void paintComponent(Graphics g) { // 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占썸묾占쎈�占쏙옙占쏙옙占썸�占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占�
			int width = getWidth();
			int height = getHeight();
			int shadowGap = this.shadowGap;
			Color shadowColorA = new Color(shadowColor.getRed(),
					shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
			Graphics2D graphics = (Graphics2D) g;

			// Sets antialiasing if HQ.
			if (highQuality) {
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
			}

			// Draws shadow borders if any.
			if (shady) {
				graphics.setColor(shadowColorA);
				graphics.fillRoundRect(shadowOffset,// X position
						shadowOffset,// Y position
						width - strokeSize - shadowOffset, // width
						height - strokeSize - shadowOffset, // height
						arcs.width, arcs.height);// arc Dimension
			} else {
				shadowGap = 1;
			}

			// Draws the rounded opaque panel with borders.
			graphics.setColor(Color.WHITE);
			graphics.fillRoundRect(0, 0, width - shadowGap, height - shadowGap,
					arcs.width, arcs.height);
			graphics.setColor(Color.BLACK); // �������뱄옙��옙		graphics.setStroke(new BasicStroke(strokeSize));
			graphics.drawRoundRect(0, 0, width - shadowGap, height - shadowGap,
					arcs.width, arcs.height);

			// Sets strokes to default, is better.
			graphics.setStroke(new BasicStroke());
			super.paintComponent(g);
		}
	}

	class UnderLineJLabel extends JLabel {
		public UnderLineJLabel(String str) {
			// TODO Auto-generated constructor stub
			this.setText(str);
			this.setHorizontalAlignment(JLabel.CENTER);
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.WHITE);
			g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
		}
	}

	class ButtonField extends JPanel {
		JPanel j = new JPanel();
		JLabel jlabelDumy = new JLabel("Bug Report");

		public ButtonField() {
			// TODO Auto-generated constructor stub
			this.setBackground(TreezeStaticData.TREEZE_BG_COLOR);
			this.setLayout(gbl);
			jlabelDumy.setForeground(new Color(0, 0, 0, 0));
			addGrid(gbl, gbc, jlabelDumy, 1, 1, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, new JLabel(), 1, 2, 1, 1, 1, 10, this);
			addGrid(gbl, gbc, j, 2, 1, 1, 2, 8, 1, this);
			j.setLayout(gbl);
			j.setBackground(TreezeStaticData.TREEZE_BG_COLOR);
			insets.set(0, 0, 0, 0);
			addGrid(gbl, gbc, new JLabel(), 1, 1, 1, 2, 2, 1, j);
			addGrid(gbl, gbc, new WriteBtn(TreezeStaticData.WRITE_BTN,
					TreezeStaticData.WRITE_PRESS_BTN,
					TreezeStaticData.WRITE_ENTER_BTN), 2, 1, 1, 1, 1, 1, j);
			addGrid(gbl, gbc, new JLabel(), 2, 2, 1, 1, 1, 1, j);
			addGrid(gbl, gbc, new JLabel(), 3, 1, 1, 2, 2, 1, j);
			insets.set(10, 10, 10, 10);
		}
	}

	class WriteBtn extends ImgBtn {

		public WriteBtn(final Image defaultImg, final Image pressImg,
				final Image enterImg) {
			// TODO Auto-generated constructor stub
			super(defaultImg, pressImg, enterImg);
		
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);

		}

		@Override
		protected void Action() {
			close();
		}
	}

	void close() {
		setVisible(false);
	

	}

}
