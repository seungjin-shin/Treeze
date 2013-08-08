package Frame;

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
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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

public class LoginPageFrame extends JFrame {
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	LoginPagePanel loginPagePanel = new LoginPagePanel();
	loginPanel loginPanel = new loginPanel();
	Insets insets = new Insets(20, 20, 20, 20);
	ImageIcon loginLogoImg;
	LoginLogo loginlogo;
	SignUpPanel signUpPanel = new SignUpPanel();

	final int FRAME_WIDTH = 1200;
	final int FRAME_HEIGHT = 700;

	public LoginPageFrame() {
		// TODO Auto-generated constructor stub
		this.getContentPane().setBackground(new Color(141, 198, 63));
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		loginLogoImg = new ImageIcon("/Users/Kunyoung/Desktop/loginlogo.png");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		loginlogo = new LoginLogo();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(30, 30, 30, 30); // 패널 margin 설정 30
		gbl.setConstraints(loginPagePanel, gbc);
		this.add(loginPagePanel);
		loginPagePanel.setLayout(gbl);

		addGrid(gbl, gbc, new JLabel(), 0, 0, 1, 1, 2, 1, loginPagePanel);
		addGrid(gbl, gbc, loginPanel, 1, 0, 1, 1, 4, 1, loginPagePanel);
		addGrid(gbl, gbc, signUpPanel, 2, 0, 1, 1, 2, 1, loginPagePanel);
		loginPanel.setLayout(gbl);

		addGrid(gbl, gbc, new JLabel(), 0, 0, 1, 1, 1, 2, loginPanel);
		insets.left = 100; // 로고 이미지 margin 줌
		insets.right = 100;
		addGrid(gbl, gbc, loginlogo, 0, 1, 1, 1, 1, 5, loginPanel);
		insets.left = 20; // margin 재설
		insets.right = 20;
		addGrid(gbl, gbc, new JLabel(), 0, 2, 1, 1, 1, 3, loginPanel);
		insets.bottom = 5;
		insets.top = 5;

		addGrid(gbl, gbc, new HintTextField("e-mail address"), 0, 3, 1, 1, 1,
				1, loginPanel);
		addGrid(gbl, gbc, new HintTextField("password"), 0, 4, 1, 1, 1, 1,
				loginPanel);
		addGrid(gbl, gbc, new JButton("Sign in"), 0, 5, 1, 1, 1, 1, loginPanel);
		addGrid(gbl, gbc, new JLabel(), 0, 6, 1, 1, 1, 2, loginPanel);

		loginPagePanel.setVisible(true);
		this.setVisible(true);
	}

	private void addGrid(GridBagLayout gbl, GridBagConstraints gbc,
			Component c, int gridx, int gridy, int gridwidth, int gridheight,
			int weightx, int weighty, Container container) {
		gbc.gridx = gridx; // 몇 행
		gbc.gridy = gridy; // 몇 열
		gbc.gridwidth = gridwidth; // 행 몇개를 차지 할지
		gbc.gridheight = gridheight; // 열 몇개를 차지 할지
		gbc.weightx = weightx; // 행크기 비율
		gbc.weighty = weighty; // 열 크기 비율

		gbc.insets = insets;

		gbl.setConstraints(c, gbc);

		container.add(c);
	}

	class LoginPagePanel extends JPanel {

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

		public LoginPagePanel() {
			// TODO Auto-generated constructor stub
			this.setBackground(new Color(0, 0, 0, 0));
			this.setSize(10, 10);

		}

		protected void paintComponent(Graphics g) { // 테두리를 둥글게 하기위해
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

	class loginPanel extends JPanel {

		public loginPanel() {
			// TODO Auto-generated constructor stub
			this.setBackground(new Color(0, 0, 0, 0));

		}

	}

	class HintTextField extends JTextField implements FocusListener {

		private final String hint;

		public HintTextField(final String hint) {
			super(hint);
			Font f = new Font(hint, Font.ITALIC, 25);
			setFont(f);
			this.setBackground(new Color(234, 234, 234));
			setForeground(Color.GRAY);
			this.hint = hint;
			super.addFocusListener(this);
			this.setBorder(new LineBorder(Color.BLACK, 1, true));
			this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(),
					BorderFactory.createEmptyBorder(2, 20, 2, 5)));

		}

		@Override
		public void focusGained(FocusEvent e) { // 포커스를 얻었으면 써진 글씨가 없으면 힌트 글씨를 지
			if (this.getText().isEmpty()) {
				super.setText("");
			}

		}

		@Override
		public void focusLost(FocusEvent e) { // 포커스를 잃으면 써진 글씨가 없으면 힌트를 적음
			if (this.getText().isEmpty()) {
				Font f = new Font(hint, Font.ITALIC, 25);
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

	class LoginLogo extends JPanel {
		public LoginLogo() {
			// TODO Auto-generated constructor stub
			this.setBackground(new Color(0, 0, 0, 0));
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawImage(loginLogoImg.getImage(), 0, 0, this.getWidth(),
					this.getHeight(), null);
		}
	}

	class SignUpPanel extends JPanel {
		public SignUpPanel() {
			// TODO Auto-generated constructor stub
			this.setLayout(new BorderLayout());
			this.setBackground(new Color(0, 0, 0, 0));
			this.add(new JButton("Sign UP"), BorderLayout.SOUTH);
		}
	}
}
