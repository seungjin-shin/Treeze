package freemind.Frame;

import java.awt.BasicStroke;
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.google.gson.Gson;

import freemind.controller.FreemindManager;
import freemind.controller.ImgBtn;
import freemind.json.FreemindGson;
import freemind.json.Survey;
import freemind.json.SurveyTrueFalseType;
import freemind.json.SurveyTypeFactory;
import freemind.json.TreezeData;

public class SurveyFrame extends JFrame{
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	Insets insets = new Insets(20, 20, 20, 20);
	FreemindManager fManager = FreemindManager.getInstance();
	JPanel fullPanel =new JPanel();
	LogoPanel logoPanel = new LogoPanel();
	Image logoimg;
	
	
	Survey survey = new Survey();
//	HintTextField answer1 = new HintTextField(survey.getAnswer1());
//	HintTextField answer2 = new HintTextField(survey.getAnswer2());
//	HintTextField answer3 = new HintTextField(survey.getAnswer3());
//	HintTextField answer4 = new HintTextField(survey.getAnswer4());
//	HintTextField answer5 = new HintTextField(survey.getAnswer5());
	WriteTextArea surveyTa = new WriteTextArea();
	
	ButtonGroup btnGroup = new ButtonGroup();
	JRadioButton type1 = new JRadioButton("True & False", true);
//	JRadioButton type2 = new JRadioButton("One of Three");
//	JRadioButton type3 = new JRadioButton("One of Four");
//	JRadioButton type4 = new JRadioButton("One of Five");
	
	public SurveyFrame() {
		// TODO Auto-generated constructor stub
		this.setBackground(fManager.treezeColor);
		this.setSize(800,700);
		this.setLocation(400, 100);
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);
		setIconImage(fManager.topIcon);
		logoimg =  fManager.treezeLogo;
		setTitle("Survey");
		//fullPanel.setBackground(Color.BLUE);
		addGrid(gbl, gbc, new RoundPanel(), 0, 1, 1, 1, 1, 1, this);
		//fullPanel.setLayout(gbl);
		//addGrid(gbl, gbc, new RoundPanel(), 0, 0, 1, 1, 1, 1, fullPanel);
		//this.setResizable(false);
		setAlwaysOnTop(true);
		setVisible(true);
		logoPanel.setVisible(true);
	}
	
	public void falseVisibleMainFrame(){
		setVisible(false);
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
			addGrid(gbl, gbc, new WriteField("dd"), 0, 1, 1, 1,1, 8, this);
			insets.set(5, 20, 5, 20);
			addGrid(gbl, gbc, new WriteField(), 0, 2, 1, 1,1, 12, this);
			addGrid(gbl, gbc, new ButtonField(), 0, 3, 1, 1,1, 3, this);
			

		}

		protected void paintComponent(Graphics g) { // ??θ??? ???? ???????
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
			g.drawImage(fManager.treezeLogo, 0, 0, this.getWidth()/5,
					this.getHeight(), null);
		}
	}
	class WriteField extends JPanel{
		public WriteField(String question) {
			// TODO Auto-generated constructor stub
			this.setBackground(fManager.treezeColor);
			this.setLayout(gbl);
			addGrid(gbl, gbc, new UnderLineJLabel("Survey Contents"), 1, 1, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, new JLabel(),                    1, 2, 1, 1, 1, 10, this);
			addGrid(gbl, gbc, surveyTa,     2, 1, 1, 2, 8, 1, this);
		}
		public WriteField() {
			// TODO Auto-generated constructor stub
			this.setBackground(fManager.treezeColor);
			this.setLayout(gbl);
			addGrid(gbl, gbc, new UnderLineJLabel("          Type           "), 1, 1, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, new JLabel(),                    1, 2, 1, 3, 1, 10, this);
			
//			addGrid(gbl, gbc, answer1,                         2, 1, 1, 1, 8, 1, this);
//			addGrid(gbl, gbc, answer2,                         2, 2, 1, 1, 8, 1, this);
//			addGrid(gbl, gbc, answer3,                         2, 3, 1, 1, 8, 1, this);
//			addGrid(gbl, gbc, answer4,                         2, 4, 1, 1, 8, 1, this);
//			addGrid(gbl, gbc, answer5,                         2, 5, 1, 1, 8, 1, this);
			btnGroup.add(type1);
//			btnGroup.add(type2);
//			btnGroup.add(type3);
//			btnGroup.add(type4);
			type1.setBackground(fManager.treezeColor);
//			type2.setBackground(fManager.treezeColor);
//			type3.setBackground(fManager.treezeColor);
//			type4.setBackground(fManager.treezeColor);
			
			addGrid(gbl, gbc, type1,                         2, 1, 1, 1, 8, 1, this);
//			addGrid(gbl, gbc, type2,                         2, 2, 1, 1, 8, 1, this);
//			addGrid(gbl, gbc, type3,                         2, 3, 1, 1, 8, 1, this);
//			addGrid(gbl, gbc, type4,                         2, 4, 1, 1, 8, 1, this);
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
			
			//textArea.setBackground(new Color(0,0,0,0));
			this.setBorder(new EmptyBorder(10, 10, 10, 10));
			
			this.getViewport().add(textArea);
		}

		protected void paintComponent(Graphics g) { // ??θ??? ???? ???????
			
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
			graphics.setColor(Color.BLACK); // 
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
//			this.setBackground(Color.black);
			this.setLayout(gbl);
			jlabelDumy.setForeground(new Color(0, 0, 0, 0));
			addGrid(gbl, gbc, jlabelDumy, 1, 1, 1, 1, 1, 1, this);
			addGrid(gbl, gbc, new JLabel(), 1, 2, 1, 1, 1, 10, this);
			addGrid(gbl, gbc, j, 2, 1, 1, 2, 8, 1, this);
			j.setLayout(gbl);
			j.setBackground(fManager.treezeColor);
			insets.set(0,0, 0,0);
			addGrid(gbl, gbc, new JLabel(), 1, 1, 1, 2, 2, 1, j);
			addGrid(gbl, gbc, new SendBtn(fManager.sendDefault,fManager.sendPress,fManager.sendOver), 2, 1, 1, 1, 1, 1, j);
			addGrid(gbl, gbc, new JLabel(), 2, 2, 1, 1, 1, 1, j);
			addGrid(gbl, gbc, new JLabel(), 3, 1, 1, 2, 2, 1, j);
			insets.set(10, 10, 10, 10);
		}
	}
	
	class HintTextField extends JTextField implements FocusListener {

		private final String hint;

		public HintTextField(final String hint) {
			super(hint);
			Font f = new Font(hint, Font.ITALIC, 20);
			setFont(f);
			this.setBackground(new Color(234, 234, 234));
			setForeground(Color.GRAY);
			this.hint = hint;
			super.addFocusListener(this);
			
			setColumns(24);
			
//			this.setBorder(new LineBorder(new Color(234, 234, 234), 1, true));
			this.setBorder(new LineBorder(fManager.treezeColor));
////			this.setBorder(new LineBorder(Color.black, 1, true));
			this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(),
					BorderFactory.createEmptyBorder(2, 5, 2, 5)));
		}

		@Override
		public void focusGained(FocusEvent e) { // Æ÷Ä¿½º¸¦ ¾ò¾ú
			if (this.getText().isEmpty()) {
				super.setText("");
			}
			this.setBackground(Color.white);

		}

		@Override
		public void focusLost(FocusEvent e) { // Æ÷Ä¿½º¸¦ 
			if (this.getText().isEmpty()) {
				Font f = new Font(hint, Font.ITALIC, 10);
				setFont(f);

				setForeground(Color.GRAY);
				super.setText(hint);
			} else {
				setForeground(Color.BLACK);
			}
			this.setBackground(new Color(234, 234, 234));
		}

		@Override
		public String getText() {
			String typed = super.getText();
			return typed.equals(hint) ? "" : typed;
		}
		
	}
	
	class SendBtn extends ImgBtn{
		
		public SendBtn(final Image defaultImg, final Image pressImg, final Image enterImg) {
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
			falseVisibleMainFrame();
			
			Survey survey = new Survey();
			SurveyTrueFalseType surveyType = (SurveyTrueFalseType)SurveyTypeFactory
					.createSurveyType(SurveyTypeFactory.TRUEFALSETYPE);
			
			survey.setContents(surveyTa.getTextArea().getText());
			survey.setSurveyType(surveyType);
			
			Gson gson = new Gson();
			TreezeData treezeData = new TreezeData();
			treezeData.setDataType(TreezeData.SURVEY);
			treezeData.getArgList().add(gson.toJson(survey));
			
			try {
				FreemindManager.getInstance().getOs().write(gson.toJson(treezeData).getBytes("UTF-8"));
				FreemindManager.getInstance().getOs().flush();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
