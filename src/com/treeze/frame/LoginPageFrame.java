package com.treeze.frame;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import JDIalog.TextDialogue;

import com.google.gson.Gson;
import com.treeze.Abstract.ImgBtn;
import com.treeze.data.ArrayUser;
import com.treeze.data.ArrayVersion;
import com.treeze.data.TreezeStaticData;
import com.treeze.data.User;
import com.treeze.data.Version;
import com.treeze.uploadthread.UpdateThread;




public class LoginPageFrame extends JFrame{
	Color treezeColor = new Color(141, 198, 63);
	Color noColor = new Color(0, 0, 0, 0);
	Insets insets = new Insets(10, 10, 10, 10);
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	MainPanel mPanel = new MainPanel();
//	LogoPanel lPanel = new LogoPanel(Toolkit.getDefaultToolkit().getImage("images/treezeLogo.png"));
	LogoPanel lPanel = new LogoPanel(TreezeStaticData.LOGO_IMG);
//	LoginPanel loPanel = new LoginPanel(Toolkit.getDefaultToolkit().getImage("images/LoginInputBar.png"),
//			Toolkit.getDefaultToolkit().getImage("images/login.png"));
	LoginPanel loPanel; 
	SignPanel sPanel = new SignPanel();
	RightPanel rPanel = new RightPanel();
	
	JTextField emailTf = new HintTextField("e-mail address");;
	final JTextField pwTf = new HintTextFieldPassword("password");;
	
	
	
	public LoginPageFrame() {
	
		this.getContentPane().setBackground(treezeColor);
		this.setSize(647, 394);
		this.setLayout(gbl);
		this.setLocation(400, 100);
		setIconImage(TreezeStaticData.TREEZE_ICON_IMG);
		loPanel = new LoginPanel();
		
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gbc.fill = GridBagConstraints.BOTH;
		setInsets(40, 45, 120, 120);
		addGrid(gbl, gbc, lPanel, 0, 0, 1, 1, 1, 30, mPanel);

		setLoginPanel();

		setInsets(0, 0, 120, 120);
		addGrid(gbl, gbc, loPanel, 0, 1, 1, 1, 1, 4, mPanel);
		setInsets(0, 20, 120, 120);
		addGrid(gbl, gbc, sPanel, 0, 2, 1, 1, 1, 4, mPanel);
		setInsets(0, 5, 120, 120);
		addGrid(gbl, gbc, rPanel, 0, 3, 1, 1, 1, 1, mPanel);
		
		setInsets(10, 10, 10, 10);
		addGrid(gbl, gbc, mPanel, 0, 0, 1, 1, 1, 1, this);
		if(!checkVersion().equals(TreezeStaticData.VERSION))
		{
			UpdateThread updateThread = new UpdateThread();
			updateThread.start();
			System.exit(0);
		}
		else{
		setVisible(true);
		}
	}
	
	public String checkVersion(){
		  HttpClient httpClient = new DefaultHttpClient();
	       HttpGet get = new HttpGet("http://" + TreezeStaticData.IP + ":8080/treeze/getLastVersion?userType=" + Version.STUDENT);
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

				System.out.println("checkVersion : " + str);
				Gson gson = new Gson();
				ArrayVersion arrayVersion = gson.fromJson(str, ArrayVersion.class);
				Version version = arrayVersion.getVersion();

			EntityUtils.consume(resEntity);
			return version.getVersion();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			new TextDialogue(LoginPageFrame.this, "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	  }
	
	public void setLoginPanel(){
		LoginBtn loginBtn;
			pwTf.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						pushLoginBtn();
					}
				}
			});
			
			
			loginBtn = new LoginBtn(TreezeStaticData.LOGIN_BTN, TreezeStaticData.LOGIN_PRESS_BTN, TreezeStaticData.LOGIN_PRESS_BTN);
			loginBtn.setBackground(treezeColor);
			loginBtn.setBorderPainted(false);
			loginBtn.setContentAreaFilled(false);
			loginBtn.setFocusable(false);
			//loginBtn.setBorder(null);
			loginBtn.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent arg0) {
					
				}
				
				@Override
				public void focusGained(FocusEvent arg0) {
					pwTf.requestFocus();
					
				}
			});
			
			
			loginBtn.setBorder(BorderFactory.createCompoundBorder(loginBtn.getBorder(),
					BorderFactory.createEmptyBorder(1, 5, 1, 5)));
			setInsets(30, 10, 0, 15);
//			setInsets(t, b, l, r)
			
			addGrid(gbl, gbc, emailTf,  0, 0, 1, 1, 7, 5, loPanel);
			setInsets(0, 30, 0, 15);
			addGrid(gbl, gbc, pwTf,     0, 1, 1, 1, 7, 5, loPanel);
			setInsets(30, 30, 10, 0);
			addGrid(gbl, gbc, loginBtn, 1, 0, 1, 2, 5, 5, loPanel);
	}
	
	
	
	public void setInsets(int t, int b, int l, int r){
		insets.top = t;
		insets.bottom = b;
		insets.left = l;
		insets.right = r;
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
	
	class RightPanel extends JPanel{
		JLabel rightLb = new JLabel("Treeze @ 2013");
		Font sFont = new Font("Serif", Font.PLAIN, 8);
		public RightPanel() {
			rightLb.setForeground(Color.white);
			rightLb.setFont(sFont);
			addGrid(gbl, gbc, rightLb, 0, 1, 1, 1, 1, 1, this);
			setLayout(gbl);
			setBackground(noColor);
		}
	}
	
	class SignPanel extends JPanel{
		JLabel signUp = new JLabel("Sign Up");
		
		JLabel forgotPw = new JLabel("Forgot your password?");
		public SignPanel() {
			signUp.setForeground(Color.white);
			signUp.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					LoginPageFrame.this.setCursor(Cursor.DEFAULT_CURSOR);
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					LoginPageFrame.this.setCursor(Cursor.HAND_CURSOR);
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					new SignFrame();
				}
			});
			forgotPw.setForeground(Color.white);
			setInsets(0, 0, 40, 0);
			addGrid(gbl, gbc, signUp, 0, 0, 1, 1, 1, 1, this);
			setInsets(0, 0, 0, 40);
			addGrid(gbl, gbc, forgotPw, 1, 0, 1, 1, 1, 1, this);
			setLayout(gbl);
			setBackground(noColor);
		}
	}
	
	class LoginBtn extends ImgBtn{

		public LoginBtn(Image defaultImg, Image pressImg, Image enterImg) {
			super(defaultImg, pressImg, enterImg);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void Action() {
			pushLoginBtn();
		}
		
	}
	
	class LoginPanel extends JPanel{
		public LoginPanel() {
			this.setLayout(gbl);
			setBackground(treezeColor);
		}
	}
	
	public void pushLoginBtn(){
//		boolean loginChk = false;
//		UploadToServer uploadToServer = new UploadToServer();
//		loginChk = uploadToServer.signIn(emailTf.getText(), pwTf.getText());
//		System.out.println("loginChk" + loginChk);
////		if (loginChk) {
//			setVisible(false);
//			JFrame pFrame = new ProfileFrame(mc);
//			FreemindManager.getInstance().setProfileFrame(pFrame);
//		}
	
		if(signIn(emailTf.getText(), pwTf.getText())){
		new ProfileFrame();
		setVisible(false);
		}
		
	}
	public boolean signIn(String email, String pw){
//  	  113.198.84.80:8080/treeze/createUser
//  	  113.198.84.80:8080/treeze/login?userEmail="minsuk@hansung.ac.kr"&password="1234"
//  	  "emailFalse"
//  	  "passwordFalse"
			HttpClient httpClient = new DefaultHttpClient();
	       HttpGet get = new HttpGet("http://" + TreezeStaticData.IP + ":8080/treeze/login?userEmail=" + email + "&password=" + pw); 

	       MultipartEntity multipart = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE, null,
					Charset.forName("UTF-8"));  // xml, classId, LectureName 			
	       StringBody typeBoby;
		try {

			HttpResponse response = httpClient.execute(get);

			HttpEntity resEntity = response.getEntity();

			InputStream inputStream = resEntity.getContent();
	    	  BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

	    	  String str = "";
	    	  String tmp;

				while((tmp = in.readLine()) != null )
					str += tmp;

			System.out.println("response : " + str);

			EntityUtils.consume(resEntity);

			if(str.equals("emailFalse")){
				new TextDialogue(this, "Email is not exist.", true);
				return false;
			}
			else if(str.equals("passwordFalse")){
				new TextDialogue(this, "Incorrect password.", true);
				return false;
			}
			else{
				Gson gson = new Gson();
				
				ArrayUser arrayUser = gson.fromJson(str, ArrayUser.class);
				User user = User.getInstance();
				
				user.setUserName(arrayUser.getUser().getUserName());
				user.setUserType(arrayUser.getUser().getUserType());
				user.setUserEmail(arrayUser.getUser().getUserEmail());
				user.setUserImgId(arrayUser.getUser().getUserImgId());
			
				System.out.println("[user] = "+User.getInstance().getUserName());
				System.out.println("[user] = "+arrayUser.getUser().getUserName());
				
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			new TextDialogue(this, "Server down, Program end", true);
			e.printStackTrace();
			System.exit(0);
		}
		return false;
	  }

	class LogoPanel extends JPanel{
		ImageIcon icon;
		public LogoPanel(Image img) {
			//icon = new ImageIcon(img);
			//this.set
			setBackground(noColor);
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			if(icon==null){
			icon = TreezeStaticData.makeResizedImageIcon(this.getWidth(),this.getHeight(),TreezeStaticData.LOGO_IMG);
			}
			g.drawImage(icon.getImage(), 0, 0,null);
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

			// Draws the rounded opaque panel with borders.
			// graphics.setColor(treezeColor); // panel color
			// graphics.fillRoundRect(0, 0, width - shadowGap,
			// height - shadowGap, arcs.width, arcs.height);
			// graphics.setColor(Color.white); // round color
			// graphics.setStroke(new BasicStroke(strokeSize));
			// graphics.drawRoundRect(0, 0, width - shadowGap,
			// height - shadowGap, arcs.width, arcs.height);

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
//			setFocusable(false);.
			
//			this.setBorder(new LineBorder(new Color(234, 234, 234), 1, true));
//			this.setBorder(new LineBorder(Color.black, 1, true));
//			this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(),
//					BorderFactory.createEmptyBorder(2, 0, 2, 5)));
			this.setBorder(new EmptyBorder(5, 10, 5, 10));

		}

		@Override
		public void focusGained(FocusEvent e) { // �첨�쩔쩍쨘쨍짝 쩐챵쩐첬�쨍쨍챕 쩍찼�첩 짹�쩐쩐째징 쩐첩�쨍쨍챕 �첫�짰 짹�쩐쩐쨍짝 �철
			if (this.getText().isEmpty()) {
				super.setText("");
			}

		}

		@Override
		public void focusLost(FocusEvent e) { // �첨�쩔쩍쨘쨍짝 ���쨍쨍챕 쩍찼�첩 짹�쩐쩐째징 쩐첩�쨍쨍챕 �첫�짰쨍짝 �청�쩍
			if (this.getText().isEmpty()) {
				Font f = new Font(hint, Font.ITALIC, 10);
				setFont(f);

				setForeground(Color.GRAY);
				super.setText(hint);
			} else {
				setForeground(Color.BLACK);
			}
		}

		@Override
		public String getText() {
			String typed = super.getText();
			return typed.equals(hint) ? "" : typed;
		}
		
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			g.drawImage(TreezeStaticData.LOGIN_INPUT_BAR, 0, 0, this.getWidth(),
					this.getHeight(), null);
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
			
			this.setBorder(new EmptyBorder(5, 10, 5, 10));
			setBackground(new Color(0, 0, 0, 0));
		
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
			this.repaint();
			
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
