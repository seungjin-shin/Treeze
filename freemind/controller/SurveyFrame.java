package freemind.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


class SurveyFrame extends JFrame{
	ActionListener btnListener = new BtnListener(this);
	
	public SurveyFrame() {
		setSize(450, 100);
		setLayout(null);
		setTitle("Input your survey contents");
		setVisible(true);
		setLocation(350, 200);
		
		getContentPane().setBackground(new Color(175, 230, 121, 255));
		
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
	}
	
}

class SurveyResultFrame extends JFrame{
	public SurveyResultFrame() {
		// TODO Auto-generated constructor stub
	setSize(450, 100);
	setLayout(null);
	setTitle("Input your survey contents");
	setVisible(true);
	setLocation(350, 200);
	getContentPane().setBackground(new Color(175, 230, 121, 255));
	
	
	}
}
