package freemind.controller;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



class QuestionFrame extends JFrame{
	ActionListener btnListener = new BtnListener(this);
	
	public QuestionFrame() {
		setSize(600, 400);
		setLayout(null);
		setTitle("Survey result");
		setVisible(true);
		setLocation(350, 200);
		
		//getContentPane().setBackground(new Color(175, 230, 121, 255));
		getContentPane().setBackground(new Color(141, 198, 63));
		Font f = new Font("Serif", Font.BOLD, 30);
		
		//JTextField lecturetf = new JTextField();
		JLabel titleLb = new JLabel("Hardware security");
		titleLb.setFont(f);
		titleLb.setSize(250, 50);
		titleLb.setLocation(20, 10);
		add(titleLb);

		QuestionPanel questPn = new QuestionPanel();
		questPn.setSize(460, 260);
		questPn.setLocation(20, 80);
		add(questPn);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void paint(Graphics g){
		
		super.paint(g);
		g.setColor(new Color(100, 100, 121, 255));
		g.drawLine(20, 85, 500, 85);
		g.drawLine(20, 86, 500, 86);
		//g.drawLine(20, 87, 600, 87);
		//g.fillRect(20, 150, 480, 200);
	}
}
class QuestionPanel extends JPanel{
	public QuestionPanel() {
		setSize(480, 360);
		setLayout(null);
		setVisible(true);
		setBackground(Color.white);
		
		JLabel noLb = new JLabel("No.");
		noLb.setSize(50, 40);
		noLb.setLocation(20, 10);
		add(noLb);
		JLabel titleLb = new JLabel("Title");
		titleLb.setSize(60, 40);
		titleLb.setLocation(180, 10);
		add(titleLb);
		JLabel writerLb = new JLabel("Writer");
		writerLb.setSize(50, 40);
		writerLb.setLocation(380, 10);
		add(writerLb);
		
		JLabel no1 = new JLabel("2");
		no1.setSize(50, 40);
		no1.setLocation(20, 50);
		add(no1);
		
		JLabel no2 = new JLabel("1");
		no2.setSize(50, 40);
		no2.setLocation(20, 110);
		add(no2);
		
		JLabel reply = new JLabel("[Re] What is the meaning 'HSM'?");
		reply.setSize(200, 40);
		reply.setLocation(120, 80);
		add(reply);
		
		JLabel title1 = new JLabel("What is the meaning 'HSM'?");
		title1.setSize(200, 40);
		title1.setLocation(120, 50);
		add(title1);
		
		JLabel title2 = new JLabel("About SafeNet...");
		title2.setSize(200, 40);
		title2.setLocation(120, 110);
		add(title2);
		
		JLabel writer1 = new JLabel("0892070");
		writer1.setSize(60, 40);
		writer1.setLocation(380, 50);
		add(writer1);
		
		JLabel writer2 = new JLabel("prof");
		writer2.setSize(60, 40);
		writer2.setLocation(380, 80);
		add(writer2);
		
		JLabel writer3 = new JLabel("0892051");
		writer3.setSize(60, 40);
		writer3.setLocation(380, 110);
		add(writer3);
		
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.black);
		
		g.drawLine(20, 50, 430, 50);
		g.drawLine(20, 51, 430, 51);
		
		g.drawLine(20, 85, 430, 85);
		g.drawLine(20, 115, 430, 115);
		
		//g.drawRoundRect(10, 40, 460, 100, 30, 30);
		
	}
}

