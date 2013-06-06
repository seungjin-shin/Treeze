package freemind.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import freemind.json.ArrayClass;
import freemind.json.ArrayTicket;
import freemind.json.Ticket;
import freemind.json.TicketInfo;
import freemind.main.BtnListener;
import freemind.modes.UploadToServer;



class QuestionFrame extends JFrame{
	Controller c;
	BtnListener btnListener = new BtnListener(this);
	JScrollPane sPanel;
	QuestionPanel questPn;
	String questionTitle;
	public QuestionFrame(String classId, String idxStr, Controller c, String s) {
		questionTitle = s;
		this.c = c;
		setSize(550, 400);
		setLayout(null);
		setTitle("Question Frame");
		setVisible(true);
		setLocation(350, 200);
		
		getContentPane().setBackground(new Color(141, 198, 63));
		Font f = new Font("Serif", Font.BOLD, 30);
		
		JLabel titleLb = new JLabel(questionTitle);
		titleLb.setFont(f);
		titleLb.setSize(250, 50);
		titleLb.setLocation(20, 10);
		add(titleLb);

		questPn = new QuestionPanel(classId, idxStr);
		
		sPanel = new JScrollPane(questPn);
		sPanel.setBounds(20, 80, 460, 260);
		
		add(sPanel);
		
	}
	public void paint(Graphics g){
		
		super.paint(g);
		g.setColor(new Color(100, 100, 121, 255));
		g.drawLine(20, 85, 500, 85);
		g.drawLine(20, 86, 500, 86);
	}
	class QuestionPanel extends JPanel implements ActionListener{
		final int TOPPADDING = 60;
		final int TOPNUMPADDING = 50;
		final int QUESTIONHGAP = 30;
		final int LINETOPPADDING = 85;
		int questionCnt = 0;
		int questionReverse = 3;
		String idxStr;
		String classId;
		ArrayList<Ticket> ticketList;
		int childTotalCnt = 0;
		public QuestionPanel(String classId, String idxStr) {
			this.classId = classId;
			this.idxStr = idxStr;
			setSize(480, 360);
			setLayout(null);
			setVisible(true);
			setBackground(Color.white);
			
			init();
		}
		public void init(){
			
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
			
			String sHtml = "";
			BufferedReader in = null;
			String buf = "";
			
			try
			{
				URL url = new URL("http://61.43.139.10:8080/treeze/getTickets/?classId=" + classId + "&position=" + idxStr);
				URLConnection urlconn = url.openConnection();
				in = new BufferedReader(new InputStreamReader(urlconn.getInputStream(),"UTF-8"));
				
				while((buf = in.readLine()) != null)
				{
					sHtml += buf;
				}
			}
			catch(Exception e)
			{
				System.out.println("연결 에러");
			}
			finally
			{
				if(sHtml.equals("")) sHtml = "Data가 존재하지 않습니다";
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println(sHtml);
			ticketList = new ArrayList<Ticket>();
			Ticket tmpTicket;
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<ArrayTicket>() {
			}.getType();
			ArrayTicket jonResultClasslist = (ArrayTicket) gson
					.fromJson(sHtml, type);
			ticketList = jonResultClasslist.getTickets();
			JLabel tmpLb;
			JButton tmpBtn;
			childTotalCnt = 0;
			ArrayList<Ticket> tmpTicketList = new ArrayList<Ticket>();
			
			for(questionCnt = 0; questionCnt < ticketList.size(); questionCnt++){
				tmpTicket = ticketList.get(questionCnt);
				tmpTicketList.add(tmpTicket);
				
				for(int i = 0; i < tmpTicketList.size(); i++){
					if (tmpTicket.getTicketPosition().substring(0,tmpTicket.getTicketPosition().length() - 2).equals(tmpTicketList.get(i).getTicketPosition())) {
						// 자식으로 들어감
						tmpTicketList.get(i).setHaveChild(true);
						tmpTicketList.get(i).getChildList().add(tmpTicket);
						ticketList.remove(tmpTicket);
						childTotalCnt++;
						questionCnt--;
						break;
					}
					if (tmpTicketList.get(i).isHaveChild()) {
						for (int j = 0; j < tmpTicketList.get(i).getChildList()
								.size(); j++) {
							// 자식에 자식으로 들어감
							Ticket tmp = tmpTicketList.get(i).getChildList().get(j);
							if (tmpTicket.getTicketPosition().substring(0,tmpTicket.getTicketPosition().length() - 2).equals(tmp.getTicketPosition())) {
								tmp.setHaveChild(true);
								tmp.getChildList().add(tmpTicket);
								ticketList.remove(tmpTicket);
								tmpTicketList.remove(tmp);
								childTotalCnt++;
								questionCnt--;
								break;
							}
						}
					}
				}
			}
			
			int childCnt = 0;
			for(questionCnt = 0; questionCnt < ticketList.size(); questionCnt++){
				tmpTicket = ticketList.get(questionCnt);
				tmpLb = new JLabel((ticketList.size() + childTotalCnt - questionCnt - childCnt) + "");
				tmpLb.setSize(50, 40);
				tmpLb.setLocation(20, TOPNUMPADDING + (questionCnt + childCnt) * QUESTIONHGAP);
				add(tmpLb);
				
				tmpBtn = new JButton(tmpTicket.getTicketTitle());
				tmpBtn.setSize(260, 20);
				tmpBtn.setLocation(60, TOPPADDING + (questionCnt + childCnt) * QUESTIONHGAP);
				tmpBtn.addActionListener(this);
				tmpBtn.setFocusable(false);
				add(tmpBtn);
				
				tmpLb = new JLabel(tmpTicket.getUserName());
				tmpLb.setSize(60, 40);
				tmpLb.setLocation(380, TOPNUMPADDING + (questionCnt + childCnt) * QUESTIONHGAP);
				add(tmpLb);
				
				
				if(tmpTicket.isHaveChild()){
					for (int j = 0; j < tmpTicket.getChildList().size(); j++) {
						childCnt++;
						tmpTicket = tmpTicket.getChildList().get(j);
						tmpLb = new JLabel((ticketList.size() + childTotalCnt
								- questionCnt - childCnt)
								+ "");
						tmpLb.setSize(50, 40);
						tmpLb.setLocation(20, TOPNUMPADDING
								+ (questionCnt + childCnt) * QUESTIONHGAP);
						add(tmpLb);

						tmpBtn = new JButton(tmpTicket.getTicketTitle());
						tmpBtn.setSize(260, 20);
						tmpBtn.setLocation(60, TOPPADDING
								+ (questionCnt + childCnt) * QUESTIONHGAP);
						tmpBtn.addActionListener(this);
						tmpBtn.setFocusable(false);
						add(tmpBtn);

						tmpLb = new JLabel(tmpTicket.getUserName());
						tmpLb.setSize(60, 40);
						tmpLb.setLocation(380, TOPNUMPADDING
								+ (questionCnt + childCnt) * QUESTIONHGAP);
						add(tmpLb);
					}
				}
			}
			setPreferredSize(new Dimension(420, 20 + TOPPADDING + (questionCnt + childTotalCnt) * QUESTIONHGAP));
		}
		
		public void paint(Graphics g){
			super.paint(g);
			g.setColor(Color.black);
			
			g.drawLine(20, 50, 430, 50);
			g.drawLine(20, 51, 430, 51);
			
			for(int i = 0; i < (questionCnt + childTotalCnt); i++)
				g.drawLine(20, LINETOPPADDING + i * QUESTIONHGAP, 430, LINETOPPADDING + i * QUESTIONHGAP);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String ticketTitle = e.getActionCommand();
			for(int i = 0; i < ticketList.size(); i++){
				if(ticketTitle.equals(ticketList.get(i).getTicketTitle())){
					new InputReplyFrame(ticketList.get(i));
					break;
				}
				else if(ticketList.get(i).isHaveChild()){
					for(int j = 0; j < ticketList.get(i).getChildList().size(); j++){
					
						if (ticketTitle.equals(ticketList.get(i).getChildList().get(j).getTicketTitle())) {
							new InputReplyFrame(ticketList.get(i).getChildList().get(j));
							break;
						}
					}
				}
			}
		}
		
		class InputReplyFrame extends JFrame implements ActionListener{
			JTextField classtf;
			JTextArea replyArea;
			JTextArea contentsArea;
			Ticket ticket;
			public InputReplyFrame(Ticket t) {
				ticket = t;
				setSize(400, 540);
				setLayout(null);
				setTitle("Input your reply");
				setVisible(true);
				setLocation(350, 200);
				
				getContentPane().setBackground(new Color(141, 198, 63));
				
				JLabel contentsLb = new JLabel("contents :");
				contentsLb.setSize(60, 30);
				contentsLb.setLocation(10, 10);
				add(contentsLb);
				
				contentsArea = new JTextArea();
				contentsArea.setText(ticket.getContents());
				contentsArea.setEditable(false);
				contentsArea.setLineWrap(true);
				JScrollPane sPane = new JScrollPane(contentsArea);
				sPane.setBounds(80, 10, 200, 170);
				add(sPane);
				
				
				JLabel inputLb = new JLabel("Reply :");
				inputLb.setSize(50, 30);
				inputLb.setLocation(10, 210);
				replyArea = new JTextArea();
				replyArea.setLineWrap(true);
				sPane = new JScrollPane(replyArea);
				sPane.setBounds(80, 210, 200, 250);
				add(sPane);
				
				JButton input = new JButton("Reply");
				input.addActionListener(this);
				input.setSize(80, 25);
				input.setLocation(290, 434);
				add(inputLb);
				add(input);
			}
			
			public void paint(Graphics g){
				super.paint(g);
				
				g.drawRect(86, 39, 202, 172);
				g.drawRect(87, 40, 200, 170);
				
				g.drawRect(86, 239, 202, 252);
				g.drawRect(87, 240, 200, 250);
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String classTitle = replyArea.getText();
				classTitle = classTitle.trim();
				JDialog dlg;
				final String QUESTION = "2";
				
				if(classTitle.equals("")){
					dlg = new JDialog(this, "Error", true);
					JLabel errLb = new JLabel("Input your reply!");
					dlg.setLayout(new FlowLayout());
					dlg.add(errLb);
					dlg.setBounds(150,200,200,100);
					dlg.setVisible(true);
					return;
				}
				else{
					UploadToServer UTS = new UploadToServer();
					UTS.ticketPost("[Re]" + ticket.getTicketTitle(),
							ticket.getClassId() + "", ticket.getPosition(),
							classTitle, "교수", ticket.getTicketPosition()
									+ "/0");
					
					TicketInfo ticketInfo = new TicketInfo();
					ticketInfo.setContents(classTitle);
					ticketInfo.setPosition(ticket.getTicketPosition());
					ticketInfo.setTicketPosition(ticket.getTicketPosition() + "/0");
					ticketInfo.setTicketTitle("[Re]" + ticket.getTicketTitle());
					ticketInfo.setUserName("교수");
					Gson gson = new Gson();
					String quesStr = gson.toJson(ticketInfo);
					OutputStream tmpOs;
					for(int i = 0; i < c.getNaviOs().size(); i++){
						tmpOs = c.getNaviOs().get(i);
						try {
							if(tmpOs != null)
								tmpOs.write((QUESTION + quesStr).getBytes("UTF-8"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					questPn.removeAll(); // No가 겹쳐서 removeAll 하고 다시 그리기
					questPn.init();
					sPanel.updateUI();
					this.setVisible(false);
				}
			}
		}
		
	}
}

