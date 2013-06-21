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


public class UploadMM extends JFrame {
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
	
	public UploadMM(ArrayList<SlideData> sList, MindMapController mc) {
		
		setSize(300, 150);
		setLayout(null);
		
		this.sList = (ArrayList<SlideData>) sList.clone();
		this.mc = mc;
		btnListner = new UploadListner();

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
		upBtn.addActionListener(btnListner);
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
	}
	class UploadListner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			UploadToServer UTS = new UploadToServer();
			mc.removeUploadMM();
		}
		
	}
	
}
