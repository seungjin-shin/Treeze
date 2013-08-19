package freemind.main;
//
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.net.URL;
//import java.util.ArrayList;
//
//import javax.help.plaf.basic.BasicFavoritesNavigatorUI.AddAction;
//import javax.swing.BorderFactory;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JScrollPane;
//import javax.swing.JTextField;
//import javax.swing.border.Border;
//
//import freemind.controller.SlideData;
//import freemind.modes.mindmapmode.MindMapController;
//
//
//public class LoginFrame extends JFrame {
//	private Container ct;
//	
//	JLabel nLabel = null;
//	JTextField jText = null;
//	
//	private JTextField IDTf;
//	private JTextField passwdTf;
//
//	JButton enterBtn;
//	JButton nextBtn;
//	JButton prevBtn;
//	JScrollPane sPanel;
//	
//	UploadListner btnListner;
//	
//	MindMapController mc;
//	
//	private Image logo;
//	public LoginFrame(MindMapController mc) {
//		this.mc = mc;
//		setSize(300, 250);
//		setLayout(null);
//		getContentPane().setBackground(new Color(141, 198, 63));
//		logo = Toolkit.getDefaultToolkit().getImage("images/treezeLogo.png");
//		
//		btnListner = new UploadListner(this);
//
//		JLabel ID = new JLabel("E-mail : ");
//		JLabel passwd = new JLabel("Password : ");
//		ID.setSize(100, 40);
//		ID.setLocation(60, 90);
//		add(ID);
//		
//		passwd.setSize(100, 40);
//		passwd.setLocation(60, 120);
//		add(passwd);
//		
//		JButton upBtn = new JButton("Login");
//		upBtn.setSize(90, 30);
//		upBtn.setLocation(140, 160);
//		//upBtn.setLocation(20, 20);
//		upBtn.addActionListener(btnListner);
//		add(upBtn);
//		
//		IDTf = new JTextField();
//		IDTf.setSize(100, 20);
//		IDTf.setLocation(130, 100);
//		passwdTf = new JPasswordField();
//		passwdTf.setSize(100, 20);
//		passwdTf.setLocation(130, 130);
//
//		add(IDTf);
//		add(passwdTf);
//		setTitle("Login");
//		setVisible(true);
//		setLocation(400, 400);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//	}
//	public void paint(Graphics g){
//		super.paint(g);
//		g.drawImage(logo, 20, 50, 200, 50, null);
//		
//		g.setColor(Color.white);
//		g.drawLine(20, 120, 240, 120);
//		repaint();
//	}
//	class UploadListner implements ActionListener{
//		JFrame frame;
//		public UploadListner(JFrame jf) {
//			frame = jf;
//			// TODO Auto-generated constructor stub
//		}
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			frame.setVisible(false);
//			new LoggedInFrame(mc);
//		}
//		
//	}
//	
//}
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
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.apache.http.client.ClientProtocolException;

import freemind.controller.FreemindManager;
import freemind.modes.mindmapmode.MindMapController;



public class LoginFrame extends JFrame{
	Color treezeColor = new Color(141, 198, 63);
	Color noColor = new Color(0, 0, 0, 0);
	Insets insets = new Insets(10, 10, 10, 10);
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	MainPanel mPanel = new MainPanel();
//	LogoPanel lPanel = new LogoPanel(Toolkit.getDefaultToolkit().getImage("images/treezeLogo.png"));
	LogoPanel lPanel = new LogoPanel(FreemindManager.getInstance().treezeLogo);
//	LoginPanel loPanel = new LoginPanel(Toolkit.getDefaultToolkit().getImage("images/LoginInputBar.png"),
//			Toolkit.getDefaultToolkit().getImage("images/login.png"));
	LoginPanel loPanel = new LoginPanel(FreemindManager.getInstance().loginInputBar,FreemindManager.getInstance().login); 
	SignPanel sPanel = new SignPanel();
	RightPanel rPanel = new RightPanel();
	MindMapController mc;
	public LoginFrame(MindMapController mc) {
		this.mc = mc;
		this.getContentPane().setBackground(treezeColor);
		this.setSize(647, 394);
		this.setLayout(gbl);
		this.setLocation(400, 100);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gbc.fill = GridBagConstraints.BOTH;
		setInsets(40, 0, 120, 120);
		addGrid(gbl, gbc, lPanel, 0, 0, 1, 1, 1, 30, mPanel);
		setInsets(0, 0, 120, 120);
		addGrid(gbl, gbc, loPanel, 0, 1, 1, 1, 1, 4, mPanel);
		setInsets(0, 20, 120, 120);
		addGrid(gbl, gbc, sPanel, 0, 2, 1, 1, 1, 4, mPanel);
		setInsets(0, 5, 120, 120);
		addGrid(gbl, gbc, rPanel, 0, 3, 1, 1, 1, 1, mPanel);
		
		setInsets(10, 10, 10, 10);
		addGrid(gbl, gbc, mPanel, 0, 0, 1, 1, 1, 1, this);
		
		setServerIP();
		
		setVisible(true);
	}
	
	public void setServerIP(){
		String tmp;
		String ip = null;
		int port = -1;
		File file = new File(System.getProperty("user.dir"), "treeze.cnf");

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

			while ((tmp = in.readLine()) != null) {
				if(tmp.equals("[serverIp]"))
					ip = in.readLine();
				if(tmp.equals("[serverPort]"))
					port = Integer.parseInt(in.readLine());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ip == null || port == -1){
			System.out.println("treeze.cnf Err");
			return;
		}
		FreemindManager.getInstance().setSERVERIP(ip);
		FreemindManager.getInstance().setPORT(port);
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
			forgotPw.setForeground(Color.white);
			setInsets(0, 0, 40, 0);
			addGrid(gbl, gbc, signUp, 0, 0, 1, 1, 1, 1, this);
			setInsets(0, 0, 0, 40);
			addGrid(gbl, gbc, forgotPw, 1, 0, 1, 1, 1, 1, this);
			setLayout(gbl);
			setBackground(noColor);
		}
	}
	
	class LoginPanel extends JPanel{
		JTextField emailTf;
		JTextField pwTf;
		JButton loginBtn;
		ImageIcon icon;
		ImageIcon inputIcon;
		public LoginPanel(Image textImg, Image loginImg) {
			inputIcon = new ImageIcon(textImg);
			emailTf = new HintTextField("e-mail address");
			pwTf = new HintTextField("password");
			pwTf.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						pushLoginBtn();
					}
				}
			});
			
			
			icon = new ImageIcon(loginImg);
			loginBtn = new JButton(icon);
			loginBtn.setBorder(null);
			loginBtn.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent arg0) {
					
				}
				
				@Override
				public void focusGained(FocusEvent arg0) {
					pwTf.requestFocus();
					
				}
			});
			
			loginBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					pushLoginBtn();
				}
			});
//			this.setBorder(new LineBorder(Color.black, 1, true));
//			loginBtn.setBorder(BorderFactory.createCompoundBorder(loginBtn.getBorder(),
//					BorderFactory.createEmptyBorder(0, 0, 0, 0)));
			setInsets(15, 0, 0, 15);
			addGrid(gbl, gbc, emailTf,  0, 0, 1, 1, 7, 5, this);
			setInsets(5, 15, 0, 15);
			addGrid(gbl, gbc, pwTf,     0, 1, 1, 1, 7, 5, this);
			setInsets(0, 0, 0, 0);
			addGrid(gbl, gbc, loginBtn, 1, 0, 1, 2, 3, 1, this);
			setLayout(gbl);
			setBackground(noColor);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(inputIcon.getImage(), 0, 16, 243, 26, null);
			g.drawImage(inputIcon.getImage(), 0, 50, 243, 26, null);
			
		}
	}
	
	public void pushLoginBtn(){
		setVisible(false);
		JFrame pFrame = new ProfileFrame(mc);
		FreemindManager.getInstance().setProfileFrame(pFrame);
	}
	
	class LogoPanel extends JPanel{
		ImageIcon icon;
		public LogoPanel(Image img) {
			icon = new ImageIcon(img);
			setBackground(noColor);
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(icon.getImage(), 0, 0, 384, 107, null);
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
			
			setColumns(24);
			
			this.setBorder(new LineBorder(new Color(234, 234, 234), 1, true));
//			this.setBorder(new LineBorder(Color.black, 1, true));
			this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(),
					BorderFactory.createEmptyBorder(2, 0, 2, 5)));

		}

		@Override
		public void focusGained(FocusEvent e) { // Æ÷Ä¿½º¸¦ ¾ò¾úÀ¸¸é ½áÁø ±Û¾¾°¡ ¾øÀ¸¸é ÈùÆ® ±Û¾¾¸¦ Áö
			if (this.getText().isEmpty()) {
				super.setText("");
			}

		}

		@Override
		public void focusLost(FocusEvent e) { // Æ÷Ä¿½º¸¦ ÀÒÀ¸¸é ½áÁø ±Û¾¾°¡ ¾øÀ¸¸é ÈùÆ®¸¦ ÀûÀ½
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
	}
}
