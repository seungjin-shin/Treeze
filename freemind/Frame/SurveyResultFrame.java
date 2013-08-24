package freemind.Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SurveyResultFrame extends JFrame implements ActionListener{
	int yesCnt;
	int noCnt;
	int yesPer;
	int noPer;
	int total;
	String surveyTitle;
	public SurveyResultFrame(int total, int y, int n, String s) {
		this.total = total;
		surveyTitle = s;
		yesCnt = y;
		noCnt = n;
		yesPer = (int)(((double)y/(double)(y + n)) * 100);
		noPer = (int)(((double)n/(double)(y + n)) * 100);
		
		setSize(600, 400);
		setLayout(null);
		setTitle("Survey result");
		setLocation(350, 200);
		
		getContentPane().setBackground(Color.white);
		Font f = new Font("Serif", Font.BOLD, 30);
		
		JLabel resultLb = new JLabel("RESULT");
		resultLb.setFont(f);
		resultLb.setSize(200, 50);
		resultLb.setLocation(10, 10);
		add(resultLb);

		JLabel StringLb = new JLabel(surveyTitle);
		StringLb.setFont(f);
		StringLb.setSize(400, 50);
		StringLb.setLocation(10, 60);
		add(StringLb);
		
		JButton okBtn = new JButton("OK");
		
		okBtn.addActionListener(this);
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
		
		setVisible(true);
	}
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(new Color(141, 198, 63));
		g.drawLine(10, 85, 490, 85);
		g.drawLine(10, 86, 490, 86);
		g.drawLine(10, 87, 490, 87);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	class ResultPanel extends JPanel{
		public ResultPanel() {
			setSize(480, 160);
			setLayout(null);
			setVisible(true);
			setBackground(new Color(141, 198, 63));
			JLabel allCnt = new JLabel("Registered count : " + total);
			allCnt.setSize(130, 40);
			allCnt.setLocation(10, 0);
			add(allCnt);
			
			JLabel joinCnt = new JLabel("Join count for survey : " + (yesCnt + noCnt));
			joinCnt.setSize(160, 40);
			joinCnt.setLocation(150, 0);
			add(joinCnt);
		}
		public void paint(Graphics g){
			super.paint(g);
			g.setColor(Color.white);
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
			
			JLabel yesPers = new JLabel(yesPer + "%");
			yesPers.setSize(50, 30);
			yesPers.setLocation(360, 25);
			add(yesPers);
			
			JLabel noPers = new JLabel(noPer + "%");
			noPers.setSize(50, 30);
			noPers.setLocation(360, 50);
			add(noPers);
		}
		
		public void paint(Graphics g){
			super.paint(g);
			g.setColor(Color.blue);
			g.drawLine(50, 34, 50 + yesPer * 3, 34);
			g.drawLine(50, 35, 50 + yesPer * 3, 35);
			g.drawLine(50, 36, 50 + yesPer * 3, 36);
			
			g.setColor(Color.red);
			g.drawLine(50, 64, 50 + noPer * 3, 64);
			g.drawLine(50, 65, 50 + noPer * 3, 65);
			g.drawLine(50, 66, 50 + noPer * 3, 66);
			
		}
	}
}