package freemind.modes;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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


public class UploadMM extends JFrame {
	private Container ct;
	private int depth = 1;
	private String idx = "1.";
	private int cnt = 1;
	
	private final int HEIGHT = 20;
	private final int WIDTH = 20;
	private JPanel nPanel;
	
	private String filePath;
	
	JLabel nLabel = null;
	JTextField jText = null;
	
	private JLabel prevLb;
	private JTextField IDTf;
	private JTextField passwdTf;
	private String prevStr;
	int tmpInt;
	String tmpStr = "1.";
	int oldDepth;
	boolean nextAv = false;
	boolean prevAv = false;
	JButton enterBtn;
	JButton nextBtn;
	JButton prevBtn;
	JScrollPane sPanel;
	
	public UploadMM() {
		
		setSize(300, 150);
		setLayout(null);

		JLabel ID = new JLabel("ID : ");
		JLabel passwd = new JLabel("Password : ");
		ID.setSize(100, 40);
		ID.setLocation(60, 10);
		add(ID);
		
		passwd.setSize(100, 40);
		passwd.setLocation(60, 40);
		add(passwd);
		
		JButton upBtn = new JButton("Upload");
		upBtn.setSize(90, 30);
		upBtn.setLocation(140, 75);
		add(upBtn);
		
		IDTf = new JTextField();
		IDTf.setSize(100, 20);
		IDTf.setLocation(130, 20);
		passwdTf = new JPasswordField();
		passwdTf.setSize(100, 20);
		passwdTf.setLocation(130, 50);

		add(IDTf);
		add(passwdTf);
		setTitle("Login for upload");
		setVisible(true);
		setLocation(400, 400);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
