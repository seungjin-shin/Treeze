package freemind.controller;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import freemind.json.FreemindGson;
import freemind.json.Survey;
import freemind.json.TreezeData;
import freemind.modes.UploadToServer;


class SurveyFrame extends JFrame implements ActionListener{
	final String SURVEYPNUM = "1";
	ArrayList<OutputStream> naviOs = new ArrayList<OutputStream>();
	JTextField surveyTf;
	String surveyStr = null;
	OutputStream os;
	public SurveyFrame(OutputStream o) {
		os = o;
		
		setSize(450, 100);
		setLayout(null);
		setTitle("Input your survey contents");
		setVisible(true);
		setLocation(350, 200);
		
		getContentPane().setBackground(new Color(141, 198, 63));
		
		surveyTf= new JTextField();
		JLabel inputLb = new JLabel("survey :");
		inputLb.setSize(50, 30);
		inputLb.setLocation(10, 10);
		
		surveyTf.setSize(210, 30);
		surveyTf.setLocation(60, 10);
		JButton input = new JButton("Send survey");
		input.addActionListener(this);
		input.setSize(130, 30);
		input.setLocation(280, 10);
		
		add(surveyTf);
		add(inputLb);
		add(input);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		
		surveyStr = surveyTf.getText();
		
		JDialog dlg;
		
		if(surveyStr.equals("")){
			dlg = new JDialog(this, "Error", true);
			JLabel errLb = new JLabel("Input survey contents!");
			dlg.setLayout(new FlowLayout());
			dlg.add(errLb);
			dlg.setBounds(150,200,200,100);
			dlg.setVisible(true);
			return;
		}
		else{
			String jsonStr;
			FreemindGson myGson = new FreemindGson();
			
			LectureInfo lectureInfo;
    		lectureInfo = FreemindLectureManager.getInstance();
    		
    		lectureInfo.setSurverTitle(surveyStr);
        	
			Survey survey = new Survey();
			survey.setContents(surveyStr);
			jsonStr = myGson.toJson(survey);
//			OutputStream os;
//			for(int i = 0; i < naviOs.size(); i++){
//				os = naviOs.get(i);
//				try {
//					if(os != null){
//						os.write((SURVEYPNUM + jsonStr).getBytes("UTF-8")); // 다 보내
//						System.out.println(i + "번째 보냄");
//					}
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
			TreezeData t = new TreezeData();
			t.setDataType(TreezeData.SURVEY);
			t.getArgList().add(jsonStr);
			
			jsonStr = myGson.toJson(t);
			
			try {
				System.out.println("SurveyFrame : " + jsonStr);
				os.write(jsonStr.getBytes("UTF-8"));
				os.flush();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("SurveyFrame : send survey");
			this.setVisible(false);
		}
	}		
}

class SurveyResultFrame extends JFrame implements ActionListener{
	int yesCnt;
	int noCnt;
	int yesPer;
	int noPer;
	String surveyTitle;
	public SurveyResultFrame(int y, int n, String s) {
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
			JLabel allCnt = new JLabel("Registered count : " + (yesCnt + noCnt));
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


