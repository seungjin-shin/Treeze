package freemind.Frame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.google.gson.Gson;

import freemind.controller.Controller;
import freemind.controller.FreemindManager;
import freemind.controller.ImgBtn;
import freemind.json.Ticket;
import freemind.json.TicketInfo;
import freemind.modes.MindMapNode;
import freemind.modes.NodeAdapter;
import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapController;


public class TicketFrame extends JFrame{
	ListPanel listPanel;
	TicketPanel ticketPanel = new TicketPanel();
	TicketTitleLabel ticketTitleLabel;
	TicketHead ticketHead;
	Insets insets = new Insets(20,20,20,20);
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	JScrollPane jsp;
	JList list = new JList();
	int ticketCnt = 0;
	String nodeTitle;
	FreemindManager fManager = FreemindManager.getInstance();
	
	JPanel grid = new JPanel();
	
	NodeAdapter qNode;
	Controller c;
	
	public TicketFrame(NodeAdapter node, Controller cr) {
		qNode = node;
		this.c = cr;
		nodeTitle = qNode.getParentNode().getText();
		// TODO Auto-generated constructor stub
		this.setSize(800, 600);
		
		this.getContentPane().setBackground(new Color(141, 198, 63));
		ticketTitleLabel = new TicketTitleLabel();
		gbc.fill = GridBagConstraints.BOTH; //
		this.setLayout(gbl);
		insets.set(0, 20, 0, 20);
		addGrid(gbl, gbc, ticketTitleLabel, 0, 0, 1, 1, 1, 1, this);
		insets.set(5, 20, 20, 20);
		//insets.set(, 20, 20, 20);
		addGrid(gbl, gbc, ticketPanel, 0, 1, 1, 1, 1, 20, this);
		
		ticketPanel.setLayout(gbl);
		ticketHead = new TicketHead();
		listPanel = new ListPanel();
		
		
		ticketPanel.setBackground(new Color(0,0,0,0));
		Font f = new Font("Serif", Font.PLAIN, 14);
		JLabel numLb  = new JLabel("N o.",JLabel.CENTER);
		JLabel sbLb = new JLabel("subject", JLabel.LEFT);
		JLabel wrLb = new JLabel("writer", JLabel.CENTER);
		
		numLb.setFont(f);
		sbLb.setFont(f);
		wrLb.setFont(f);
		
		JLabel dumy = new JLabel();
		WriteBtn writeBtn = new WriteBtn(fManager.writeDefault, fManager.writePress, fManager.writeOver);
		
		dumy.setBackground(new Color(0,0,0,0));
		dumy.setBackground(Color.RED);
		listPanel.setBackground(Color.red);	
		//jsp = new JScrollPane(listPanel);
		
		ticketHead.setBackground(new Color(0,0,0,0));
		//jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
		//listPanel.setBackground(new Color(0,0,0,0));
		insets.set(0, 20, 0, 20);
		addGrid(gbl, gbc, ticketHead, 0, 0, 2, 1,  1,  2, ticketPanel);
		addGrid(gbl, gbc, listPanel,  0, 1, 2, 1,  1, 40, ticketPanel);
		addGrid(gbl, gbc, dumy     ,  0, 2, 1, 1, 20,  1, ticketPanel);
		insets.set(10, 20, 10, 20);
		addGrid(gbl, gbc, writeBtn,        1, 2, 1, 1,   2, 4, ticketPanel);
		ticketHead.setLayout(gbl);
		
		grid.setLayout(new GridLayout(100,1));
		
		//grid.add
		
		grid.setBackground(Color.WHITE);
		insets.set(0, 0, 0, 0);
		addGrid(gbl, gbc, numLb,  0, 0, 1, 1, 1,  1, ticketHead);
		addGrid(gbl, gbc, sbLb,   1, 0, 1, 1, 13, 1, ticketHead);
		addGrid(gbl, gbc, wrLb,   2, 0, 1, 1, 2,  1, ticketHead);
		
		listPanel.getViewport().add(grid, null);
		
		addWindowListener(new WindowCloseListener());
	}
	
	class WindowCloseListener implements WindowListener{

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			setVisibleTicketFrame(false);
			if (qNode.hasChildren()) {
				c.getMc()._setFolded(qNode, true);
				c.getMc().nodeChanged(qNode);
			}			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void setVisibleTicketFrame(boolean visible){
		setVisible(false);
	}
	
	class WriteBtn extends ImgBtn{

		public WriteBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
		}

		@Override
		protected void Action() {
			new WriteTicketFrame(qNode);
//			c.getMc()._setFolded(selNode, true);
//			c.getMc().nodeChanged(selNode);
//			setVisibleTicketFrame(false);
		}
		
	}
	
	public void updateTickets(){
		ticketCnt = 0;
    	grid.removeAll();
    	for(int i = 0; i < qNode.getChildCount(); i++)
    		addTickets((NodeAdapter)qNode.getChildAt(i));
		listPanel.updateUI();
		mainFrameRepaint();
	}
	
	public void mainFrameRepaint(){
		setVisible(false);
		setVisible(true);
		repaint();
	}
	
	public void addTickets(NodeAdapter node){
		TicketListItem item;
		NodeAdapter cur = node;
		int i;
		
		ticketCnt++;

		item = new TicketListItem(ticketCnt + "", node);

		grid.add(item);
		
		for(i = 0; i < cur.getChildCount(); i++){
			addTickets((NodeAdapter)cur.getChildAt(i));
		}
		
	}
	
	class TicketList extends JFrame{
		public TicketList() {
		}
	}
	
	class TicketTitleLabel extends JLabel{
		
		public TicketTitleLabel() {
			// TODO Auto-generated constructor stub
			this.setText(nodeTitle);
			this.setFont(new Font("Serif", Font.BOLD, 30));
			
		}
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawLine(0, this.getHeight()-10, this.getWidth(), this.getHeight()-10);
		}
		
	}
	public void newTicketAnswerFrame(NodeAdapter node){
		new TicketAnswerFrame(node, this); 
	}
	
	
	
	private void addGrid(GridBagLayout gbl, GridBagConstraints gbc,Component c, int gridx, int gridy, int gridwidth, int gridheight,
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
	
	class TicketHead extends JPanel{
		
		private void TicketHead() {
			// TODO Auto-generated method stub
		}
	}
	
	
	class TicketPanel extends JPanel{
		
		protected Color shadowColor = Color.black;
	    /** Sets if it drops shadow */
	    protected boolean shady = true;
	    /** Sets if it has an High Quality view */
	    protected boolean highQuality = true;
		protected int shadowGap = 1;
	    /** The offset of shadow.  */
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
		        
		        //Sets antialiasing if HQ.
		        if (highQuality) {
		            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
		        }

		        //Draws shadow borders if any.
		        if (shady) {
		            graphics.setColor(shadowColorA);
		            graphics.fillRoundRect(
		                    shadowOffset,// X position
		                    shadowOffset,// Y position
		                    width - strokeSize - shadowOffset, // width
		                    height - strokeSize - shadowOffset, // height
		                    arcs.width, arcs.height);// arc Dimension
		        } else {
		            shadowGap = 1;
		        }

		        //Draws the rounded opaque panel with borders.
		        graphics.setColor(Color.WHITE);
		        graphics.fillRoundRect(0, 0, width - shadowGap, 
				height - shadowGap, arcs.width, arcs.height);
		        graphics.setColor(getForeground());
		        graphics.setStroke(new BasicStroke(strokeSize));
		        graphics.drawRoundRect(0, 0, width - shadowGap, 
				height - shadowGap, arcs.width, arcs.height);

		        //Sets strokes to default, is better.
		        graphics.setStroke(new BasicStroke());
		       
		    }
	}
	
	class TicketListItem extends JPanel{
		JLabel numLb;
		JLabel sbLb;
		JLabel wrLb;
		NodeAdapter selelctNode;
		JScrollPane jsp;
		
		public TicketListItem(String no, NodeAdapter node) {
			// TODO Auto-generated constructor stub
			
			selelctNode = node;
			numLb  = new JLabel(no, JLabel.CENTER);
			if (node.getTicketContent() != null) {
				if (node.getTicketContent().length() > 20)
					sbLb = new JLabel(node.getTicketContent().substring(0, 20)
							+ "...", JLabel.LEFT);
				else
					sbLb = new JLabel(node.getTicketContent(), JLabel.LEFT);
				wrLb = new JLabel(node.getTicketWriter(), JLabel.CENTER);
			}
			else{
				sbLb = new JLabel("TestSub", JLabel.CENTER);
				wrLb = new JLabel("TestWriter", JLabel.CENTER);
			}
			

			jsp = new JScrollPane(sbLb);
			jsp.setBorder(null);

			this.setBackground(Color.WHITE);
			// this.add(noPanel);
			this.setLayout(gbl);
			insets.bottom = 5;
			insets.top = 5;
			insets.right = 3;
			insets.left = 5;

			addGrid(gbl, gbc, numLb, 0, 0, 1, 1, 1, 1, this);

			addGrid(gbl, gbc, jsp, 1, 0, 1, 1, 13, 1, this);
			addGrid(gbl, gbc, wrLb, 2, 0, 1, 1, 1, 1, this);

			sbLb.setPreferredSize(new Dimension(sbLb
					.getWidth(), sbLb.getHeight()));

			jsp.getViewport().setBackground(Color.WHITE);
			jsp.addMouseWheelListener(new MouseWheelListener() {
				
				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					listPanel.getVerticalScrollBar().setValue(
							listPanel.getVerticalScrollBar().getValue()
									+ e.getWheelRotation());
				}
			});
			
			this.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					setBackground(new Color(255, 255, 255, 255));
					listPanel.repaint();
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
					//System.out.println(noPanel.getText());
//					new InputReplyFrame(selNode);
					newTicketAnswerFrame(selelctNode);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					setBackground(new Color(10, 10, 100, 100));
					
				}
			});
			
			jsp.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					setBackground(new Color(255, 255, 255, 255));
					listPanel.repaint();
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
					//System.out.println(noPanel.getText());
//					new InputReplyFrame(selNode);
					newTicketAnswerFrame(selelctNode);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					setBackground(new Color(10, 10, 100, 100));
					
				}
			});
			
		}
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawLine(0, this.getHeight()-1,this.getWidth(), this.getHeight()-1);
		}
	}
	
	class ListPanel extends JScrollPane{
		
		public ListPanel() {
			// TODO Auto-generated constructor stub
			this.setBackground(Color.WHITE);
			this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			getVerticalScrollBar().getModel().addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					repaint();	
				}
			});
		}
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(0, 0,this.getWidth(), 0);
			g2.drawLine(0, this.getHeight()-1,this.getWidth(), this.getHeight()-1);
		}
	}
	
}

