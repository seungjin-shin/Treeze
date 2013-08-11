package com.treeze.frame;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.treeze.data.MindNode;
import com.treeze.data.Ticket;

public class TicketFrame extends JFrame {
	MindNode node;
	ListPanel listPanel;
	TicketPanel ticketPanel = new TicketPanel();
	TicketTitleLabel ticketTitleLabel;
	TicketHead ticketHead;
	Insets insets = new Insets(20, 20, 20, 20);
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	JScrollPane jsp;
	//JList list = new JList();
	Ticket ticket;

	TicketListItem item1;
	
	JPanel grid = new JPanel();

	public TicketFrame(MindNode node) {

		// TODO Auto-generated constructor stub
		this.setSize(1000, 600);

		this.node = node;
		this.getContentPane().setBackground(new Color(141, 198, 63));
		ticketTitleLabel = new TicketTitleLabel();
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);
		insets.set(0, 20, 0, 20);
		addGrid(gbl, gbc, ticketTitleLabel, 0, 0, 1, 1, 1, 1, this);
		insets.set(5, 20, 20, 20);
		// insets.set(, 20, 20, 20);
		ticket = new Ticket(node, "1", "111", "1111");
		item1 = new TicketListItem(ticket);
		addGrid(gbl, gbc, ticketPanel, 0, 1, 1, 1, 1, 20, this);

		ticketPanel.setLayout(gbl);
		ticketHead = new TicketHead();
		listPanel = new ListPanel();

		ticketPanel.setBackground(new Color(0, 0, 0, 0));

		JLabel noPanel = new JLabel("N o.", JLabel.CENTER);

		JLabel subjectPanel = new JLabel("�???�?", JLabel.CENTER);
		JLabel whritePane = new JLabel("??????", JLabel.CENTER);
		JLabel dumy = new JLabel(); // 踰��?紐⑥������ ��린��� ���??�댄���?	
		JButton btn = new JButton("?? �?");
		dumy.setBackground(new Color(0, 0, 0, 0)); 
		dumy.setBackground(Color.RED);
		listPanel.setBackground(Color.red);
		// jsp = new JScrollPane(listPanel);

		ticketHead.setBackground(new Color(0, 0, 0, 0));
		// jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
		// listPanel.setBackground(new Color(0,0,0,0));
		insets.set(0, 20, 0, 20);
		addGrid(gbl, gbc, ticketHead, 0, 0, 2, 1, 1, 2, ticketPanel);
		addGrid(gbl, gbc, listPanel, 0, 1, 2, 1, 1, 40, ticketPanel);
		addGrid(gbl, gbc, dumy, 0, 2, 1, 1, 20, 1, ticketPanel);
		insets.set(10, 20, 0, 20);
		addGrid(gbl, gbc, btn, 1, 2, 1, 1, 1, 1, ticketPanel);
		ticketHead.setLayout(gbl);

		grid.setLayout(new GridLayout(100, 1));
		grid.add(item1);
		// grid.add(itme2);
		grid.setBackground(Color.WHITE);
		addGrid(gbl, gbc, noPanel, 0, 0, 1, 1, 1, 1, ticketHead);
		addGrid(gbl, gbc, subjectPanel, 1, 0, 1, 1, 13, 1, ticketHead);
		addGrid(gbl, gbc, whritePane, 2, 0, 1, 1, 2, 1, ticketHead);

		listPanel.getViewport().add(grid, null);

		this.setVisible(true);
	}

	class TicketList extends JFrame {
		public TicketList() {
			// TODO Auto-generated constructor stub
		}
	}

	class TicketTitleLabel extends JLabel {

		public TicketTitleLabel() {
			// TODO Auto-generated constructor stub
			this.setText(node.getNodeStr());
			this.setFont(new Font("Serif", Font.BOLD, 30));

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(4));
			g2.drawLine(0, this.getHeight() - 4, this.getWidth(),
					this.getHeight() - 4);

		}

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

	class TicketHead extends JPanel {
		JLabel noPanel = new JLabel("N o.");
		JLabel subjectPanel = new JLabel("��  紐�");
		JLabel whritePane = new JLabel("�����?");

		private void TicketHead() {
			// TODO Auto-generated method stub

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(0, this.getHeight() - 1, this.getWidth(),
					this.getHeight() - 1);
		}

	}

	class TicketPanel extends JPanel {

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
		protected int strokeSize = 2;
		protected Dimension arcs = new Dimension(30, 30);

		public TicketPanel() {
			// TODO Auto-generated constructor stub
			this.setBackground(new Color(0, 0, 0, 0));
			this.setSize(10, 10);

		}

		protected void paintComponent(Graphics g) {
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
			graphics.setColor(Color.WHITE);
			graphics.fillRoundRect(0, 0, width - shadowGap, height - shadowGap,
					arcs.width, arcs.height);
			graphics.setColor(getForeground());
			graphics.setStroke(new BasicStroke(strokeSize));
			graphics.drawRoundRect(0, 0, width - shadowGap, height - shadowGap,
					arcs.width, arcs.height);

			// Sets strokes to default, is better.
			graphics.setStroke(new BasicStroke());

		}
	}

	class TicketListItem extends JPanel {
		JLabel noPanel;
		JLabel subjectPanel;
		JLabel whriterPane;
		Ticket ticket;

		public TicketListItem(final Ticket ticket) {
			// TODO Auto-generated constructor stub
			this.ticket = ticket;
			noPanel = new JLabel("1", JLabel.CENTER);
			subjectPanel = new JLabel(ticket.getNodeStr(), JLabel.CENTER);
			whriterPane = new JLabel(ticket.getuserName(), JLabel.CENTER);
			this.setBackground(new Color(0, 0, 0, 0));
			// this.add(noPanel);
			this.setLayout(gbl);
			insets.bottom = 5;
			insets.top = 5;
			addGrid(gbl, gbc, noPanel, 0, 0, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, subjectPanel, 1, 0, 1, 1, 13, 1, this);
			addGrid(gbl, gbc, whriterPane, 2, 0, 1, 1, 2, 1, this);

			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("flflf");
					setBackground(new Color(255, 255, 255, 255));
				}

				public void mousePreswsed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					setBackground(new Color(10, 10, 100, 100));

					System.out.println(ticket.getContents());
				}
			});

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawLine(0, this.getHeight() - 1, this.getWidth(),
					this.getHeight() - 1);
		}
	}

	class ListPanel extends JScrollPane {

		public ListPanel() {
			// TODO Auto-generated constructor stub

			// this.add(noPanel);
			// this.setPreferredSize(new Dimension(0, 2000));
			this.setBackground(Color.WHITE);
			this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			// g2.drawLine(0, -5,this.getWidth(), -5);
			g2.drawLine(0, this.getHeight() - 1, this.getWidth(),
					this.getHeight() - 1);
		}
	}

}
