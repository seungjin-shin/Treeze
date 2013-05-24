package freemind.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.help.plaf.basic.BasicFavoritesNavigatorUI.AddAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import freemind.controller.SlideData;
import freemind.modes.mindmapmode.MindMapController;


public class LoginFrame extends JFrame {
	private Container ct;
	
	private String filePath;
	
	JLabel nLabel = null;
	JTextField jText = null;
	
	private JTextField IDTf;
	private JTextField passwdTf;

	JButton enterBtn;
	JButton nextBtn;
	JButton prevBtn;
	JScrollPane sPanel;
	
	UploadListner btnListner;
	
	ArrayList<SlideData> sList = new ArrayList<SlideData>();
	MindMapController mc;
	
	private Image logo;
	URL logoURL = getClass().getClassLoader().getResource("treezeLogo.png");
	public LoginFrame(MindMapController mc) {
		this.mc = mc;
		setSize(300, 250);
		setLayout(null);
		getContentPane().setBackground(new Color(175, 230, 121, 255));
		logo = new ImageIcon(logoURL).getImage();
		
		btnListner = new UploadListner(this);

		JLabel ID = new JLabel("E-mail : ");
		JLabel passwd = new JLabel("Password : ");
		ID.setSize(100, 40);
		ID.setLocation(60, 90);
		add(ID);
		
		passwd.setSize(100, 40);
		passwd.setLocation(60, 120);
		add(passwd);
		
		JButton upBtn = new JButton("Login");
		upBtn.setSize(90, 30);
		upBtn.setLocation(140, 160);
		upBtn.addActionListener(btnListner);
		add(upBtn);
		
		IDTf = new JTextField();
		IDTf.setSize(100, 20);
		IDTf.setLocation(130, 100);
		passwdTf = new JPasswordField();
		passwdTf.setSize(100, 20);
		passwdTf.setLocation(130, 130);

		add(IDTf);
		add(passwdTf);
		setTitle("Login");
		setVisible(true);
		setLocation(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(logo, 20, 50, 200, 50, null);
		
		g.setColor(Color.white);
		g.drawLine(20, 120, 240, 120);
		
	}
	class UploadListner implements ActionListener{
		JFrame frame;
		public UploadListner(JFrame jf) {
			frame = jf;
			// TODO Auto-generated constructor stub
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//UploadToServer UTS = new UploadToServer();
			//UTS.doFileUpload(sList);
			//UTS.doFileUpload(mmFilePath + ".mm","http://localhost:8080/ImageUploadTest/file.jsp");
			//mc.removeUploadMM();
			frame.setVisible(false);
			new LoggedInFrame(mc);
		}
		
	}
	
}
