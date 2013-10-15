package freemind.Frame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import freemind.controller.FreemindManager;
import freemind.controller.ImgBtn;
import freemind.json.Ticket;
import freemind.modes.NodeAdapter;
import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapController;

public class TicketAnswerFrame extends JFrame{
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	Insets insets = new Insets(20, 20, 20, 20);
	JPanel fullPanel =new JPanel();
	LogoPanel logoPanel = new LogoPanel();
	Image logoimg;
	FreemindManager fManager;
	NodeAdapter selNode;
	TicketFrame tFrame;
	WriteTextArea answerTa;
	public TicketAnswerFrame(NodeAdapter node, TicketFrame t) {
		tFrame = t;
		selNode = node;
		
		fManager = FreemindManager.getInstance();
		setIconImage(fManager.topIcon);
		setTitle("Ticket Answer");
		answerTa = new WriteTextArea();
		// TODO Auto-generated constructor stub
		this.setBackground(fManager.treezeColor);
		this.setSize(800,1000);
		this.setLocation(400, 100);
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);
		logoimg =  fManager.treezeLogo;
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
			this.setBackground(new Color(0, 0, 0, 0));
			//this.setSize(10, 10);
			this.setLayout(gbl);
			insets.set(20, 20, 0, 0);
			
			addGrid(gbl, gbc, logoPanel, 0, 0, 1, 1,1, 2, this);
			insets.set(10, 20, 5, 20);
			addGrid(gbl, gbc, new WriteField(selNode.getTicketContent()), 0, 1, 1, 1,1, 8, this);
			insets.set(5, 20, 5, 20);
			addGrid(gbl, gbc, new WriteField(), 0, 2, 1, 1,1, 12, this);
			addGrid(gbl, gbc, new ButtonField(), 0, 3, 1, 1,1, 2, this);
			

		}

		protected void paintComponent(Graphics g) { // ??��??? ???? ???????
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
//			graphics.setColor(Color.WHITE); //�׵θ�  
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
		public WriteField(String question) {
			// TODO Auto-generated constructor stub
			this.setBackground(fManager.treezeColor);
			this.setLayout(gbl);
			addGrid(gbl, gbc, new UnderLineJLabel("Question"), 1, 1, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, new JLabel(), 1, 2, 1, 1, 1, 10, this);
			addGrid(gbl, gbc, new WriteTextArea(question), 2, 1, 1, 2, 8, 1, this);
		}
		public WriteField() {
			// TODO Auto-generated constructor stub
			this.setBackground(fManager.treezeColor);
			this.setLayout(gbl);
			addGrid(gbl, gbc, new UnderLineJLabel(" Answer "), 1, 1, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, new JLabel(), 1, 2, 1, 1, 1, 10, this);
			addGrid(gbl, gbc, answerTa, 2, 1, 1, 2, 8, 1, this);
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
		Color textAreaBgColor;
		JTextArea textArea;
		
		public JTextArea getTextArea() {
			return textArea;
		}

		public WriteTextArea() {
		// TODO Auto-generated constructor stub
		this.setBackground(new Color(0, 0, 0, 0));
		textAreaBgColor = new Color(255, 255, 255, 255);
		
		//this.setSize(10, 10);
		//this.setLayout(new BorderLayout());
		textArea = new JTextArea();
		textArea.setBackground(textAreaBgColor);
		//textArea.setBackground(new Color(0,0,0,0));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		this.getViewport().add(textArea);
		}

		public WriteTextArea(String question) {
			// TODO Auto-generated constructor stub
			this.setBackground(new Color(0, 0, 0, 0));
			textAreaBgColor = new Color(223, 223, 223, 255);
			//this.setSize(10, 10);
			//this.setLayout(new BorderLayout());
			JTextArea textArea = new JTextArea();
			textArea.setBackground(textAreaBgColor);
			textArea.setText(question);
			textArea.setFocusable(false);
			
			JScrollPane jsp = new JScrollPane(textArea);
			jsp.setBorder(null);
			this.setPreferredSize(new Dimension(2000,
					2000));
			//textArea.setBackground(new Color(0,0,0,0));
			this.setBorder(new EmptyBorder(10, 10, 10, 10));
			
			this.getViewport().add(jsp);
		}

		protected void paintComponent(Graphics g) { // ??��??? ???? ???????
			
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
			graphics.setColor(textAreaBgColor);
			graphics.fillRoundRect(0, 0, width - shadowGap, height - shadowGap,
					arcs.width, arcs.height);
			graphics.setColor(Color.BLACK); //�׵θ�  
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
			addGrid(gbl, gbc, new WriteBtn(fManager.writeDefault,fManager.writePress,fManager.writeOver), 2, 1, 1, 1, 1, 1, j);
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
			if(answerTa.getTextArea().getText().trim().equals("")){
				JDialog dlg = new JDialog(tFrame, "Error", true);
				JLabel errLb = new JLabel("Input your reply!");
				dlg.setLayout(new FlowLayout());
				dlg.add(errLb);
				dlg.setBounds(150,200,200,100);
				dlg.setVisible(true);
				return;
			}
			
			String parentID = ((NodeAdapter)selNode).getNodeID();
			NodeAdapter tmp;
			
			String reStr = "";
			
			while(true){
				if(!selNode.getNodeTypeStr().equals("Ticket")){
					break;
				}
				reStr += "[Re]";
				selNode = (NodeAdapter) selNode.getParent();
			}
			
			Ticket t = new Ticket();
			t.setParentNodeId(parentID);
			t.setContents(reStr + answerTa.getTextArea().getText());
			t.setUserName("����");
			setCurFrame();
			fManager.getUploadToServer().ticketPost(t);
			
//			fManager.getMc().addNew(selNode, MindMapController.NEW_CHILD, null);
//			fManager.getMc().edit.stopEditing();
//			
//			NodeAdapter tmp = (NodeAdapter)fManager.getMc().getSelected();
//			
//			if(answerTa.getTextArea().getText().length() > 14)
//				tmp.setText("[Re]" + answerTa.getTextArea().getText().substring(0, 12) + "...");
//			else
//				tmp.setText("[Re]" + answerTa.getTextArea().getText());
//			
//			tmp.setNodeTypeStr("Ticket");
////			tmp.setNodeID(fManager.getTicket().getId() + "");
//			tmp.setTicketContent("[Re]" + answerTa.getTextArea().getText());
//			tmp.setTicketWriter("����");
//			
//			fManager.getMc().nodeChanged(tmp);
			
//			tFrame.updateTickets();
			setVisibleTicketAnswerFrame(false);
		}
	}
	
	public void setCurFrame(){
		fManager.getUploadToServer().setCurFrame(this);
	}
	
	public void setVisibleTicketAnswerFrame(boolean visible){
		setVisible(false);
	}
}