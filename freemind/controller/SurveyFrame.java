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
import javax.swing.JTextField;


class SurveyFrame extends JFrame{
	ActionListener btnListener = new BtnListener(this);
	
	public SurveyFrame() {
		setSize(450, 100);
		setLayout(null);
		setTitle("Input your survey contents");
		setVisible(true);
		setLocation(350, 200);
		
		getContentPane().setBackground(new Color(141, 198, 63));
		
		JTextField lecturetf = new JTextField();
		JLabel inputLb = new JLabel("survey :");
		inputLb.setSize(50, 30);
		inputLb.setLocation(10, 10);
		
		lecturetf.setSize(210, 30);
		lecturetf.setLocation(60, 10);
		JButton input = new JButton("Send survey");
		input.addActionListener(btnListener);
		input.setSize(130, 30);
		input.setLocation(280, 10);
		
		add(lecturetf);
		add(inputLb);
		add(input);
	}
}

class BtnListener implements ActionListener{
	JFrame frame;
	public BtnListener(JFrame f) {
		frame = f;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		new SurveyResultFrame();
	}
	
}


class SurveyResultFrame extends JFrame{
	ActionListener btnListener = new BtnListener(this);
	
	public SurveyResultFrame() {
		setSize(600, 400);
		setLayout(null);
		setTitle("Survey result");
		setVisible(true);
		setLocation(350, 200);
		
		getContentPane().setBackground(Color.white);
		Font f = new Font("Serif", Font.BOLD, 30);
		
		//JTextField lecturetf = new JTextField();
		JLabel resultLb = new JLabel("RESULT");
		resultLb.setFont(f);
		resultLb.setSize(200, 50);
		resultLb.setLocation(10, 10);
		add(resultLb);

		JLabel StringLb = new JLabel("Are you understand?");
		StringLb.setFont(f);
		StringLb.setSize(400, 50);
		StringLb.setLocation(10, 60);
		add(StringLb);
		
		JButton okBtn = new JButton("OK");
		
		okBtn.addActionListener(btnListener);
		okBtn.setSize(130, 30);
		okBtn.setLocation(300, 300);
		add(okBtn);
		
		
		JPanel resultPn2 = new ResultPanel2();
		resultPn2.setSize(460, 100);
		resultPn2.setLocation(20, 160);
		add(resultPn2);
		
		JPanel resultPn = new ResultPanel();
		resultPn.setSize(480, 160);
		resultPn.setLocation(10, 120);
		add(resultPn);
	}
	public void paint(Graphics g){
		
		super.paint(g);
		g.setColor(new Color(141, 198, 63));
		g.drawLine(20, 85, 500, 85);
		g.drawLine(20, 86, 500, 86);
		g.drawLine(20, 87, 500, 87);
		//g.fillRect(20, 150, 480, 200);
	}
}
class ResultPanel extends JPanel{
	public ResultPanel() {
		setSize(480, 160);
		setLayout(null);
		setVisible(true);
		setBackground(new Color(141, 198, 63));
		JLabel allCnt = new JLabel("Registered count : 40");
		allCnt.setSize(130, 40);
		allCnt.setLocation(10, 0);
		add(allCnt);
		
		JLabel joinCnt = new JLabel("Join count for quiz : 38");
		joinCnt.setSize(160, 40);
		joinCnt.setLocation(150, 0);
		add(joinCnt);
		
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.white);
		
		//g.drawRoundRect(10, 40, 460, 100, 30, 30);
		
	}
}

class ResultPanel2 extends JPanel{
	public ResultPanel2() {
		setSize(460, 100);
		setLayout(null);
		setVisible(true);
		setBackground(Color.white);
		
		JLabel yesLb = new JLabel("Yes");
		yesLb.setSize(50, 30);
		yesLb.setLocation(20, 20);
		add(yesLb);
		
		JLabel noLb = new JLabel("No");
		noLb.setSize(50, 30);
		noLb.setLocation(20, 50);
		add(noLb);
		
		JLabel yesPers = new JLabel("75%");
		yesPers.setSize(50, 30);
		yesPers.setLocation(360, 25);
		add(yesPers);
		
		JLabel noPers = new JLabel("25%");
		noPers.setSize(50, 30);
		noPers.setLocation(360, 50);
		add(noPers);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.blue);
		g.drawLine(50, 34, 150, 34);
		g.drawLine(50, 35, 150, 35);
		g.drawLine(50, 36, 150, 36);
		
		g.setColor(Color.red);
		g.drawLine(50, 64, 100, 64);
		g.drawLine(50, 65, 100, 65);
		g.drawLine(50, 66, 100, 66);
//		
//		g.drawRoundRect(10, 40, 460, 100, 30, 30);
		
	}
}

