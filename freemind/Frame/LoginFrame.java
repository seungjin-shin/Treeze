package freemind.Frame;
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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.http.client.ClientProtocolException;

import freemind.controller.FreemindManager;
import freemind.controller.ImgBtn;
import freemind.controller.UpdateThread;
import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapController;



public class LoginFrame extends JFrame{
	Color treezeColor = new Color(141, 198, 63);
	Color noColor = new Color(0, 0, 0, 0);
	Insets insets = new Insets(10, 10, 10, 10);
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	FreemindManager fManager = FreemindManager.getInstance();
	MainPanel mPanel = new MainPanel();
//	LogoPanel lPanel = new LogoPanel(Toolkit.getDefaultToolkit().getImage("images/treezeLogo.png"));
	LogoPanel lPanel = new LogoPanel();
//	LoginPanel loPanel = new LoginPanel(Toolkit.getDefaultToolkit().getImage("images/LoginInputBar.png"),
//			Toolkit.getDefaultToolkit().getImage("images/login.png"));
	LoginPanel loPanel; 
	SignPanel sPanel;
	RightPanel rPanel = new RightPanel();
	MindMapController mc;
	JTextField emailTf = new HintTextField("e-mail address");;
	final JTextField pwTf = new HintTextFieldPassword("password");;
	
	
	
	public LoginFrame(MindMapController mc) {
		
		this.mc = mc;
		this.getContentPane().setBackground(treezeColor);
		this.setSize(600, 394);
		this.setLayout(gbl);
		this.setLocation(400, 100);
		this.setTitle("Treeze login");
		setIconImage(fManager.topIcon);
		loPanel = new LoginPanel();
		sPanel = new SignPanel();
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gbc.fill = GridBagConstraints.BOTH;
		setInsets(30, 35, 120, 120);
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
		
		setTreezeInfo();
		setResizable(false);
		setVisible(true);
	}
	
	public void setMainCursor(int cursor){
		setCursor(cursor);
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
			
			
			loginBtn = new LoginBtn(fManager.loginDefault, fManager.loginPress, fManager.loginOver);
			loginBtn.setBackground(new Color(0, 0, 0, 0));
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
			addGrid(gbl, gbc, emailTf,  0, 0, 1, 1, 5, 5, loPanel);
			setInsets(0, 30, 0, 15);
			addGrid(gbl, gbc, pwTf,     0, 1, 1, 1, 5, 5, loPanel);
//			setInsets(30, 30, 10, 0);
			setInsets(30, 30, 0, 0);
			addGrid(gbl, gbc, loginBtn, 1, 0, 1, 2, 13, 5, loPanel);
	}
	
	public void setTreezeInfo(){
		String tmp;
		String ip = null;
		int port = -1;
		String version = null;
		File file = new File(System.getProperty("user.dir"), "treeze.cnf");

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

			while ((tmp = in.readLine()) != null) {
				if(tmp.equals("[serverIp]"))
					ip = in.readLine();
				if(tmp.equals("[serverPort]"))
					port = Integer.parseInt(in.readLine());
				if(tmp.equals("[profVersion]"))
					version = in.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ip == null || port == -1){
			System.out.println("treeze.cnf Err");
			new TextDialogue(this, "Fail to read treeze.cnf file.", true);
			System.exit(0);
			return;
		}
		fManager.setSERVERIP(ip);
		fManager.setPORT(port);
		
		fManager.getUploadToServer().setSERVERIP(ip);
		fManager.getUploadToServer().setCurFrame(this);
		UploadToServer uploadToServer = fManager.getUploadToServer();
		String curVersion = uploadToServer.checkVersion();
		
		if(!version.equals(curVersion)){
			
		Thread updateThread = new UpdateThread();
		updateThread.start();
			System.exit(0);
		}
			
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
		Font sFont = new Font("Serif", Font.PLAIN, 10);
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
		Font f = new Font("Serif", Font.PLAIN, 14);
		
		public SignPanel() {
			signUp.setFont(f);
			forgotPw.setFont(f);
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
					setMainCursor(Cursor.DEFAULT_CURSOR);
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					setMainCursor(Cursor.HAND_CURSOR);
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
			setBackground(noColor);
		}
	}
	
	public void pushLoginBtn(){
		boolean loginChk = false;
		fManager.getUploadToServer().setCurFrame(this);
		loginChk = fManager.getUploadToServer().signIn(emailTf.getText(), pwTf.getText());
		if (loginChk) {
			setVisible(false);
			JFrame pFrame = new ProfileFrame(mc);
			FreemindManager.getInstance().setProfileFrame(pFrame);
		}
			
		System.out.println("loginChk" + loginChk);
	}
	
	class LogoPanel extends JPanel{
		ImageIcon icon;
		public LogoPanel() {
			setBackground(noColor);
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(icon == null)
				icon = fManager.makeResizedImageIcon(this.getWidth(), this.getHeight(), fManager.treezeLogo);
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
		Font f;
		public HintTextField(final String hint) {
			super(hint);
			f = new Font(hint, Font.PLAIN, 12);
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
		public void focusGained(FocusEvent e) { //
			if (this.getText().isEmpty()) {
				super.setText("");
			}

		}

		@Override
		public void focusLost(FocusEvent e) { // 
			if (this.getText().isEmpty()) {
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
			g.drawImage(fManager.loginInputBar, 0, 0, this.getWidth(),
					this.getHeight(), null);
			super.paint(g);
		}
	}
	
	class HintTextFieldPassword extends JPasswordField implements FocusListener {

		private String realPw = "";
		String str;
		JLabel hintLabel;
		Font f;
		public HintTextFieldPassword(String hint) {

			f = new Font(hint, Font.PLAIN, 12);
			setFont(f);
			this.setBackground(new Color(234, 234, 234));
			setForeground(Color.GRAY);

			this.hintLabel = new JLabel(hint);
			this.hintLabel.setFont(f);
			super.addFocusListener(this);


			hintLabel.setForeground(new Color(166,166,166));

			this.setLayout(new BorderLayout());
			this.add(hintLabel);
			setBackground(new Color(0, 0, 0, 0));
			this.setBorder(new EmptyBorder(5, 10, 5, 10));


		}

		@Override
		public void focusGained(FocusEvent e) { // 
			hintLabel.setVisible(false);

		}

		@Override
		public void focusLost(FocusEvent e) { // �첨�쩔쩍쨘쨍짝 
			if (this.getText().isEmpty()) {
				hintLabel.setVisible(true);
			} else {
				hintLabel.setVisible(false);
			}
		}


		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			g.drawImage(fManager.loginInputBar, 0, 0, this.getWidth(),
					this.getHeight(), null);
			super.paint(g);
		}
	}
	
}
