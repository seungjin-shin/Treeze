package com.treeze.frame;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
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
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import JDIalog.TextDialogue;

import com.treeze.data.TreezeStaticData;
import com.treeze.uploadthread.SignUpThread;


public class SignFrame extends JFrame {
	Color treezeColor = new Color(141, 198, 63);
	Color noColor = new Color(0, 0, 0, 0);
	Insets insets = new Insets(10, 10, 10, 10);
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	MainPanel mPanel = new MainPanel();
	// LogoPanel lPanel = new
	// LogoPanel(Toolkit.getDefaultToolkit().getImage("images/treezeLogo.png"));
	LogoPanel lPanel = new LogoPanel(TreezeStaticData.LOGO_IMG);
	// LoginPanel loPanel = new
	// LoginPanel(Toolkit.getDefaultToolkit().getImage("images/LoginInputBar.png"),
	// Toolkit.getDefaultToolkit().getImage("images/login.png"));
	SignPanel signPanel;
	RightPanel rPanel = new RightPanel();
	
	SignBtn signBtn = null;
	JPanel signLbPanel = new JPanel();
	JPanel signBtnPanel = new JPanel();
	JPanel typePanel = new JPanel();
	ButtonGroup typeBtnGp = new ButtonGroup();
	JRadioButton stuTypeBtn = new JRadioButton("Student", true);
	JRadioButton profTypeBtn = new JRadioButton("Professor");
	
	JTextField emailTf = new HintTextField("E-mail Address");
	
	HintTextField studIDTf= new HintTextField("Student Identification Number");
	HintTextField userTf= new HintTextField("User Name");
	final HintTextFieldPassword pwTf = new HintTextFieldPassword("Password");
	HintTextFieldPassword confPwTf= new HintTextFieldPassword("Confirm Password");
	boolean chk = true;
	JLabel studErrLb = new JLabel();
	JLabel userErrLb = new JLabel();
	JLabel emailErrLb = new JLabel("  Email duplicated.");
	JLabel pwErrLb = new JLabel();
	JLabel confPwErrLb = new JLabel();
	
	
	public SignFrame() {
		
		this.getContentPane().setBackground(treezeColor);
		this.setSize(400, 600);
		this.setLayout(gbl);
		this.setLocation(400, 100);
		signPanel = new SignPanel();
		emailErrLb.setForeground(treezeColor);
		// setResizable(false);
		gbc.fill = GridBagConstraints.BOTH;
		setInsets(30, 20, 30, 60);
		addGrid(gbl, gbc, lPanel, 0, 0, 1, 1, 1, 30, mPanel);
		
		JLabel signUpLabel = new JLabel("Sign Up");
		signUpLabel.setFont(new Font("Sign UP", Font.PLAIN, 30));
		signUpLabel.setForeground(Color.white);
		signLbPanel.setLayout(new FlowLayout());
		signLbPanel.add(new JLabel());
		signLbPanel.add(signUpLabel);
		signLbPanel.add(new JLabel());
		signLbPanel.setBackground(noColor);
		
		typePanel.setLayout(new FlowLayout());
		typePanel.setBackground(noColor);
		stuTypeBtn.setBackground(TreezeStaticData.TREEZE_BG_COLOR);
		stuTypeBtn.setActionCommand(com.treeze.data.User.STUDENT);
		profTypeBtn.setActionCommand(com.treeze.data.User.PROFESSOR);
		profTypeBtn.setBackground(TreezeStaticData.TREEZE_BG_COLOR);
		typeBtnGp.add(profTypeBtn);
		typeBtnGp.add(stuTypeBtn);
		
//		typePanel.add(profTypeBtn);
//		typePanel.add(stuTypeBtn);
		
		setInsets(10, 10, 10, 10);
		addGrid(gbl, gbc, signLbPanel, 0, 1, 1, 1, 1, 1, mPanel);

		setLoginPanel();

		setInsets(0, 0, 30, 30);
		addGrid(gbl, gbc, signPanel, 0, 2, 1, 1, 1, 40, mPanel);
//		setInsets(0, 20, 120, 120);
//		addGrid(gbl, gbc, sPanel, 0, 2, 1, 1, 1, 4, mPanel);
		
		signBtnPanel.setLayout(gbl);
		signBtnPanel.setBackground(noColor);
		signBtn = new SignBtn(TreezeStaticData.SIGN_IMG, TreezeStaticData.SIGN_PRESS_IMG,
				TreezeStaticData.SIGN_ENTER_IMG);
		signBtn.setBackground(new Color(0, 0, 0, 0));
		signBtn.setBorderPainted(false);
		signBtn.setContentAreaFilled(false);
		signBtn.setFocusable(false);
		// loginBtn.setBorder(null);
		
		signBtn.setBorder(BorderFactory.createCompoundBorder(
				signBtn.getBorder(),
				BorderFactory.createEmptyBorder(1, 5, 1, 5)));
		setInsets(0, 0, 0, 0);
		JLabel dumy = new JLabel();
		JLabel dumy2 = new JLabel();
		JLabel dumy3 = new JLabel();
		JLabel dumy4 = new JLabel();
		dumy.setBackground(noColor);
		dumy2.setBackground(noColor);
		dumy3.setBackground(noColor);
		dumy4.setBackground(noColor);
		
		addGrid(gbl, gbc, dumy,         0, 0, 1, 1, 1, 1, signBtnPanel);
		addGrid(gbl, gbc, dumy2,         1, 0, 1, 1, 1, 1, signBtnPanel);
		setInsets(0, 0, 25, 25);
		addGrid(gbl, gbc, signBtn,      2, 0, 1, 1, 1, 1, signBtnPanel);
		setInsets(0, 0, 0, 0);
		addGrid(gbl, gbc, dumy3,         3, 0, 1, 1, 1, 1, signBtnPanel);
		addGrid(gbl, gbc, dumy4,        4, 0, 1, 1, 1, 1, signBtnPanel);
		
//		signBtnPanel.add(new JLabel());
//		signBtnPanel.add(new JButton("dd"));
//		signBtnPanel.add(new JLabel());
		
		setInsets(20, 0, 0, 0);
		addGrid(gbl, gbc, signBtnPanel, 0, 3, 1, 1, 1, 35, mPanel);
		
		setInsets(40, 0, 60, 60);
		addGrid(gbl, gbc, rPanel, 0, 4, 1, 1, 1, 5, mPanel);

		setInsets(10, 10, 10, 10);
		addGrid(gbl, gbc, mPanel, 0, 0, 1, 1, 1, 10, this);

	

		setVisible(true);
	}

	public void mPanelRepaint(){
		repaint();
	}
	
	public void setLoginPanel() {
		
		confPwTf.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					if(signBtn.isEnabled())
						pushLoginBtn();
				}
			}
		});
		
		emailTf.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {

				 chk = checkDuplEmail(emailTf.getText());
				if(chk){
					signBtn.setEnabled(false);
					emailErrLb.setForeground(Color.red);
				}
				else{
					signBtn.setEnabled(true);
					emailErrLb.setForeground(treezeColor);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// setInsets(t, b, l, r)
		setInsets(10, 0, 10, 10);
		addGrid(gbl, gbc, typePanel,   0, 1, 1, 1, 7, 1, signPanel);
		setInsets(7, 0, 10, 10);
		addGrid(gbl, gbc, studIDTf,    0, 2, 1, 1, 7, 1, signPanel);
		addGrid(gbl, gbc, studErrLb,   0, 3, 1, 1, 7, 1, signPanel);
		addGrid(gbl, gbc, userTf,      0, 4, 1, 1, 7, 1, signPanel);
		addGrid(gbl, gbc, userErrLb,   0, 5, 1, 1, 7, 1, signPanel);
		addGrid(gbl, gbc, emailTf,     0, 6, 1, 1, 7, 1, signPanel);
		setInsets(0, 0, 10, 10);
		addGrid(gbl, gbc, emailErrLb,  0, 7, 1, 1, 7, 1, signPanel);
		setInsets(7, 0, 10, 10);
		addGrid(gbl, gbc, pwTf,        0, 8, 1, 1, 7, 1, signPanel);
		addGrid(gbl, gbc, pwErrLb,     0, 9, 1, 1, 7, 1, signPanel);
		addGrid(gbl, gbc, confPwTf,    0,10, 1, 1, 7, 1, signPanel);
		addGrid(gbl, gbc, confPwErrLb, 0,11, 1, 1, 7, 1, signPanel);
//		
//		setInsets(20, 0, 50, 50);
//		addGrid(gbl, gbc, signBtn,     0,11, 1, 1, 7, 4, signPanel);


//		setInsets(20, 0, 50, 50);
//		addGrid(gbl, gbc, signBtn,     0,11, 1, 1, 7, 4, signPanel);
		
		
	}
	public boolean checkDuplEmail(String email){

		  HttpClient httpClient = new DefaultHttpClient();
	       HttpGet get = new HttpGet("http://" + TreezeStaticData.IP + ":8080/treeze/existsEmail?userEmail=" + email);
	       String str = "";
	       MultipartEntity multipart = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE, null,
					Charset.forName("UTF-8"));  // xml, classId, LectureName 				
		try {
			HttpResponse response = httpClient.execute(get);

			HttpEntity resEntity = response.getEntity();

			InputStream inputStream = resEntity.getContent();
	    	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));


	    	  String tmp;

				while((tmp = in.readLine()) != null )
					str += tmp;

				System.out.println("checkEmail : " + str);

				EntityUtils.consume(resEntity);

				if(str.equals("true")){
					emailErrLb.setForeground(Color.RED);
					return true;
				}
				else{
					emailErrLb.setForeground(treezeColor);
					return false;
				}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			new TextDialogue(SignFrame.this, "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	  }
	

	public void setInsets(int t, int b, int l, int r) {
		insets.top = t;
		insets.bottom = b;
		insets.left = l;
		insets.right = r;
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

	class RightPanel extends JPanel {
		JLabel rightLb = new JLabel("Treeze @ 2013");
		Font sFont = new Font("Serif", Font.PLAIN, 10);

		public RightPanel() {
			rightLb.setForeground(Color.white);
			rightLb.setFont(sFont);
			setLayout(new FlowLayout());
			add(new JLabel());
			add(rightLb);
			add(new JLabel());
			setBackground(noColor);
		}
	}

	class SignBtn extends com.treeze.Abstract.ImgBtn {

		public SignBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			boolean chk = checkDuplEmail(emailTf.getText());
			if(chk==false)
			pushLoginBtn();
		}

	}

	class SignPanel extends JPanel {
		public SignPanel() {
			this.setLayout(gbl);
			setBackground(noColor);
		}
	}

	public void pushLoginBtn() {
		
		if(pwTf.getText().equals(confPwTf.getText())){
			System.out.println("媛��");
			SignUpThread signUpThread = new SignUpThread(Integer.parseInt(studIDTf.getText()),pwTf.getText(),emailTf.getText(),userTf.getText(),typeBtnGp.getSelection().getActionCommand());
			signUpThread.start();
			TextDialogue textDialogue = new TextDialogue(this, "Register has been completed!!", true);
			this.setVisible(false);
		}
		else{
			TextDialogue stringDialog  = new TextDialogue(this,"Password incorrect",true);
			
		
		}
		
		//fManager.uts.signPost(signUser);
		
		//setVisible(false);
		// JFrame pFrame = new ProfileFrame(mc);
		// FreemindManager.getInstance().setProfileFrame(pFrame);
	}

	class LogoPanel extends JPanel {
		ImageIcon icon;
		Image img;
		public LogoPanel(Image img) {
			this.img = img;
			setBackground(noColor);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(icon == null){
				icon  = TreezeStaticData.makeResizedImageIcon(getWidth()*4/5, getHeight(), img);
			}
			g.drawImage(icon.getImage(), 0, 0, null);
		}
	}
	

	class MainPanel extends JPanel {

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
		protected int strokeSize = 7;
		protected Dimension arcs = new Dimension(50, 50);

		public MainPanel() {
			// TODO Auto-generated constructor stub
			this.setBackground(noColor);
			this.setLayout(gbl);
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

		

			graphics.setColor(Color.white);
			graphics.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);
			graphics.setColor(treezeColor);
			graphics.setStroke(new BasicStroke(strokeSize));
			graphics.fillRoundRect(5, 5, width - 10, height - 10, arcs.width,
					arcs.height);

			// Sets strokes to default, is better.
			graphics.setStroke(new BasicStroke());

		}
	}

	class HintTextField extends JTextField implements FocusListener {

		private final String hint;
		ImageIcon icon;
		public HintTextField(final String hint) {
			super(hint);
			Font f = new Font(hint, Font.ITALIC, 10);
			setFont(f);
			this.setBackground(new Color(234, 234, 234));
			setForeground(Color.GRAY);
			this.hint = hint;
			super.addFocusListener(this);
			
			setColumns(18);

			setBackground(new Color(0, 0, 0, 0));
		
			this.setBorder(new EmptyBorder(5, 10, 5, 10));

		}

		@Override
		public void focusGained(FocusEvent e) { 
												
			if (this.getText().isEmpty()) {
				super.setText("");
			}
//			tfIma = fManager.activateBar;
			repaint();
			mPanelRepaint();
		}

		@Override
		public void focusLost(FocusEvent e) { 
			if (this.getText().isEmpty()) {
				Font f = new Font(hint, Font.ITALIC, 10);
				setFont(f);

				setForeground(Color.GRAY);
				super.setText(hint);
			} else {
				setForeground(Color.BLACK);
			}
//			tfIma = fManager.loginInputBar;
			repaint();
			mPanelRepaint();
		}

		@Override
		public String getText() {
			String typed = super.getText();
			return typed.equals(hint) ? "" : typed;
		}

		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			if(icon ==null){
				icon = TreezeStaticData.makeResizedImageIcon(getWidth(), getHeight(), TreezeStaticData.LOGIN_INPUT_BAR);
			}
			g.drawImage(icon.getImage(), 0, 0, null);
			super.paint(g);
		}
	}
	class HintTextFieldPassword extends JPasswordField implements FocusListener {

	
		private String realPw = "";
		String str;
		JLabel hintLabel;
		ImageIcon icon;
		public HintTextFieldPassword(String hint) {
	
			Font f = new Font(hint, Font.ITALIC, 10);
			setFont(f);
			this.setBackground(new Color(234, 234, 234));
			setForeground(Color.GRAY);
			
			this.hintLabel = new JLabel(hint);
			super.addFocusListener(this);
			
			
			hintLabel.setForeground(new Color(166,166,166));
			
			this.setLayout(new BorderLayout());
			this.add(hintLabel);
			setBackground(new Color(0, 0, 0, 0));
			this.setBorder(new EmptyBorder(5, 10, 5, 10));
			
		
		}

		@Override
		public void focusGained(FocusEvent e) {
		
			hintLabel.setVisible(false);
			
		}

		@Override
		public void focusLost(FocusEvent e) { 
			if (this.getText().isEmpty()) {
				
				hintLabel.setVisible(true);
			} else {
				hintLabel.setVisible(false);
			}
		}

		
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			if(icon ==null){
				icon = TreezeStaticData.makeResizedImageIcon(getWidth(), getHeight(), TreezeStaticData.LOGIN_INPUT_BAR);
			}
			g.drawImage(icon.getImage(), 0, 0, null);
			super.paint(g);
		}
	}

}