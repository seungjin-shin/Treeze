package freemind.Frame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import freemind.controller.*;
import freemind.json.Ticket;
import freemind.modes.NodeAdapter;
import freemind.modes.UploadToServer;

public class WriteTicketFrame extends JFrame{
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	Insets insets = new Insets(0, 0, 0, 0);
	JPanel fullPanel =new JPanel();
	LogoPanel logoPanel = new LogoPanel();
	Image logoimg;
	FreemindManager fManager;
	NodeAdapter qNode;
	String parentID;
	WriteTextArea questionTa;
	public WriteTicketFrame(NodeAdapter node) {
		qNode = node;
		parentID = ((NodeAdapter)qNode.getParentNode()).getNodeID();
		questionTa = new WriteTextArea();
		// TODO Auto-generated constructor stub
		this.setSize(800,500);
		this.setLocation(400, 100);
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(new BorderLayout());
		this.add(fullPanel);
		
		fManager = FreemindManager.getInstance();
		logoimg = fManager.treezeLogo;
		//fullPanel.setBackground(Color.BLUE);
		addGrid(gbl, gbc, new RoundPanel(), 0, 1, 1, 1, 1, 1, this);
		//fullPanel.setLayout(gbl);
		//addGrid(gbl, gbc, new RoundPanel(), 0, 0, 1, 1, 1, 1, fullPanel);
		//this.setResizable(false);
		setAlwaysOnTop(true);
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
	
	class RoundPanel extends JPanel{

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
			this.setBackground(fManager.treezeColor);
			//this.setSize(10, 10);
			this.setLayout(gbl);
			insets.set(20, 20, 0, 0);
			addGrid(gbl, gbc, logoPanel, 0, 0, 1, 1, 1, 3, this);
			
			insets.set(10, 20, 5, 20);
			addGrid(gbl, gbc, new WriteField(), 0, 1, 1, 1,1, 10, this);
			insets.set(5, 20, 5, 20);
			addGrid(gbl, gbc, new ButtonField(), 0, 2, 1, 1,1, 2, this);
			

		}

		protected void paintComponent(Graphics g) { // ÔøΩ◊µŒ∏ÔøΩÔøΩÔøΩ ÔøΩ’±€∞ÔøΩ ÔøΩœ±ÔøΩÔøΩÔøΩÔøΩÔøΩ
			super.paintComponent(g);
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
//			graphics.setColor(TreezeStaticData.TREEZE_BG_COLOR);
//			graphics.fillRoundRect(0, 0, width - shadowGap, height - shadowGap,
//					arcs.width, arcs.height);
//			graphics.setColor(Color.WHITE); // 
//			graphics.setStroke(new BasicStroke(strokeSize));
//			graphics.fillRoundRect(5, 5, width - 10, height - 10, arcs.width,
//					arcs.height);
			graphics.setColor(Color.WHITE); 
			graphics.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);
			graphics.setColor(fManager.treezeColor); 
			graphics.setStroke(new BasicStroke(strokeSize));
			graphics.fillRoundRect(5, 5, width - 10, height - 10, arcs.width,
					arcs.height);
			// Sets strokes to default, is better.
			graphics.setStroke(new BasicStroke());

		}
	}
	
	class LogoPanel extends JPanel{
		
		
		public LogoPanel() {
			// TODO Auto-generated constructor stub
		
		}
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			//super.paint(g);
			g.drawImage(fManager.treezeLogo, 0, 0, this.getWidth()/3,
					this.getHeight(), null);
		}
	}
	class WriteField extends JPanel{
		public WriteField() {
			// TODO Auto-generated constructor stub
			this.setBackground(fManager.treezeColor);
			this.setLayout(gbl);
			addGrid(gbl, gbc, new UnderLineJLabel("Question"), 1, 1, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, new JLabel(), 1, 2, 1, 1, 1, 10, this);
			addGrid(gbl, gbc, questionTa, 2, 1, 1, 2, 8, 1, this);
		}
	}
	class WriteTextArea extends JScrollPane{

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
		JTextArea textArea;
		
		public JTextArea getTextArea() {
			return textArea;
		}

		public WriteTextArea() {
		// TODO Auto-generated constructor stub
		this.setBackground(new Color(0, 0, 0, 0));
		//this.setSize(10, 10);
		//this.setLayout(new BorderLayout());
		textArea = new JTextArea();
		//textArea.setBackground(new Color(0,0,0,0));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.getViewport().add(textArea);
		}

		protected void paintComponent(Graphics g) { // ÔøΩ◊µŒ∏ÔøΩÔøΩÔøΩ ÔøΩ’±€∞ÔøΩ ÔøΩœ±ÔøΩÔøΩÔøΩÔøΩÔøΩ
			
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
			graphics.setColor(Color.BLACK); //åÎëêÎ¶ 
			graphics.setStroke(new BasicStroke(strokeSize));
			graphics.drawRoundRect(0, 0, width - shadowGap, height - shadowGap,
					arcs.width, arcs.height);

			// Sets strokes to default, is better.
			graphics.setStroke(new BasicStroke());
			super.paintComponent(g);
		}
	}
	class UnderLineJLabel extends JLabel{
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
			g.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
		}
	}
	class ButtonField extends JPanel{
		JPanel j = new JPanel();
		JLabel jlabelDumy  = new JLabel("Question");
		public ButtonField() {
			// TODO Auto-generated constructor stub
			this.setBackground(fManager.treezeColor);
			this.setLayout(gbl);
			jlabelDumy.setForeground(new Color(0, 0, 0, 0));
			addGrid(gbl, gbc, jlabelDumy, 1, 1, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, new JLabel(), 1, 2, 1, 1, 1, 10, this);
			addGrid(gbl, gbc, j, 2, 1, 1, 2, 8, 1, this);
			j.setLayout(gbl);
			j.setBackground(fManager.treezeColor);
			insets.set(0,0, 0,0);
			addGrid(gbl, gbc, new JLabel(), 1, 1, 1, 2, 2, 1, j);
			addGrid(gbl, gbc, new WriteBtn(fManager.writeDefault, fManager.writePress, fManager.writeOver), 2, 1, 1, 1, 1, 1, j);
			addGrid(gbl, gbc, new JLabel(), 2, 2, 1, 1, 1, 1, j);
			addGrid(gbl, gbc, new JLabel(), 3, 1, 1, 2, 2, 1, j);
			insets.set(10, 10, 10, 10);
		}
	}
	class WriteBtn extends ImgBtn{
		
		public WriteBtn(final Image defaultImg, final Image pressImg, final Image enterImg) {
			// TODO Auto-generated constructor stub
			super(defaultImg, pressImg, enterImg);
			
		}
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			
		}
		@Override
		protected void Action(){
			Ticket t = new Ticket();
			t.setParentNodeId(parentID);
			t.setContents(questionTa.getTextArea().getText());
			t.setUserName("±≥ºˆ");
			setCurFrame();
			setVisibleWriteFrame();
			fManager.getUploadToServer().ticketPost(t);
		}
	}
	public void setCurFrame(){
		fManager.getUploadToServer().setCurFrame(this);
	}
	public void setVisibleWriteFrame(){
		setVisible(false);
	}
}
