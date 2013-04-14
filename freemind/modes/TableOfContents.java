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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import freemind.controller.SlideData;

public class TableOfContents extends JFrame {
	private Container ct;
	private int depth = 1;
	private String idx = "1.";
	private int cnt = 1;
	
	private final int HEIGHT = 20;
	private final int WIDTH = 20;
	private BtnListener btnListener;
	private JPanel nPanel;
	private ImgPanel iPanel;
	
	private String filePath;
	
	JLabel nLabel = null;
	JTextField jText = null;
	
	private JLabel prevLb;
	private JTextField prevTf;
	private JTextField pageTf;
	private String prevStr;
	int tmpInt;
	String tmpStr = "1.";
	ArrayList<TableInfo> tList = new ArrayList<TableInfo>();
	TableInfo tData;
	int oldDepth;
	boolean nextAv = false;
	boolean prevAv = false;
	JButton enterBtn;
	JButton nextBtn;
	JButton prevBtn;
	JScrollPane sPanel;
	ArrayList<SlideData> sList;
	
	public TableOfContents(ArrayList<SlideData> sList) {
		
		this.sList = sList;
		filePath = sList.get(0).getImgPath();
		ct = getContentPane();
		btnListener = new BtnListener();
		setSize(800, 800);
		setLayout(null);

		JLabel title = new JLabel("table of contents");
		//JLabel title = new JLabel(new ImageIcon("c:\\test\\수학의 정석\\지수.jpg"));
		title.setSize(100, 40);
		title.setLocation(150, 20);
		add(title);
		
		enterBtn = new JButton("enter");
		enterBtn.addActionListener(btnListener);
		enterBtn.setSize(80, 50);
		enterBtn.setLocation(250, 30);
		
		nextBtn = new JButton("->");
		nextBtn.addActionListener(btnListener);
		nextBtn.setSize(50, 50);
		nextBtn.setLocation(350, 30);
		nextBtn.setEnabled(false);

		prevBtn = new JButton("<-");
		prevBtn.addActionListener(btnListener);
		prevBtn.setSize(50, 50);
		prevBtn.setLocation(430, 30);
		prevBtn.setEnabled(false);
		
		add(enterBtn);
		add(nextBtn);
		add(prevBtn);
		
		Border b1 = BorderFactory.createLineBorder( Color.black );

		iPanel = new ImgPanel();
		
		iPanel.setBorder(b1);
		
		//iPanel.setLayout(new FlowLayout());
//		iPanel.setSize(200, 600);
//		iPanel.setLocation(500, 50);

		nPanel = new JPanel();
		nPanel.setBorder(b1);
		
		nPanel.setLayout(null);
		nPanel.setSize(400, 350);
		nPanel.setLocation(30, 100);
		
		nLabel = new JLabel("No");
		nLabel.setSize(40, 20);
		nLabel.setLocation(15, 0);
		nPanel.add(nLabel);
		
		nLabel = new JLabel("Contents");
		nLabel.setSize(80, 20);
		nLabel.setLocation(100, 0);
		nPanel.add(nLabel);
		
		nLabel = new JLabel("Page Num");
		nLabel.setSize(80, 20);
		nLabel.setLocation(300, 0);
		nPanel.add(nLabel);
		
		sPanel = new JScrollPane(iPanel);
//		sPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);  
		sPanel.setBounds(500, 50, 200, 400);
		add(sPanel);

		nLabel = new JLabel(idx);
		nLabel.setSize(40, 20);
		nLabel.setLocation(20, 20);
		jText = new JTextField();
		jText.setSize(100, 20);
		jText.setLocation(80, 20);
		pageTf = new JTextField();
		pageTf.setSize(50, 20);
		pageTf.setLocation(300, 20);
		
		tData = new TableInfo();
		tData.setDepth(depth);
		tData.setIdx(idx);
		
		tList.add(tData);
		
		prevLb = nLabel;
		prevTf = jText;
		nPanel.add(nLabel);
		nPanel.add(jText);
		nPanel.add(pageTf);
		add(nPanel);
		//add(iPanel);

		setTitle("textarea");
		setVisible(true);
		setLocation(600, 200);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class ImgPanel extends JPanel{
		final int IMGHEIGHT = 130;
		int i;
		Image img[];
		int cnt = 0;
		SlideData sData;
		
		public ImgPanel() {
			img = new Image[sList.get(0).getsCnt()];
		}

		public void paint(Graphics g){
			super.paintComponents(g);
			cnt = 0;
			int undefinedCnt = 0;
			
			for(i = 0; i < sList.size(); i++){
				sData = sList.get(i);
				
				if(sData.getImgNum() == 1){
					if(sData.getData().equals(""))
						img[cnt] = new ImageIcon(filePath + "undefined" + undefinedCnt++ + ".jpg").getImage();
					else
						img[cnt] = new ImageIcon(filePath + sData.getData() + ".jpg").getImage();
					g.drawImage(img[cnt], 10, IMGHEIGHT * cnt + 10, 100, 100, null);
					g.drawString(cnt + 1 + "", 10, IMGHEIGHT * cnt + 8);
					cnt++;
				}
				else{
					int j = sData.getImgNum();
					for(int k = 0; k < j; k++){
						if(sData.getData().equals(""))
							img[cnt] = new ImageIcon(filePath + "undefined" + k + ".jpg").getImage();
						else
							img[cnt] = new ImageIcon(filePath + sData.getData() + k + ".jpg").getImage();
						g.drawImage(img[cnt], 10, IMGHEIGHT * cnt + 10, 100, 100, null);
						g.drawString(cnt + 1 + "", 10, IMGHEIGHT * cnt + 8);
						cnt++;
					}
				}
			}
			setPreferredSize(new Dimension(180, 100 * cnt + cnt * 30));
			sPanel.updateUI();
		}
	}
	
	class TableInfo{
		int depth;
		String idx;
		boolean more;
		
		public boolean isMore() {
			return more;
		}
		public void setMore(boolean more) {
			this.more = more;
		}
		public int getDepth() {
			return depth;
		}
		public void setDepth(int depth) {
			this.depth = depth;
		}
		public String getIdx() {
			return idx;
		}
		public void setIdx(String idx) {
			this.idx = idx;
		}
	}
	
	class BtnListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String chk = e.getActionCommand();
			String tmp[];
			
			if(chk.equals("enter")){
				cnt++;
				boolean insertChk = false;
				
				nextBtn.setEnabled(true);
				
				if(tList.size() > depth - 1)
					tData = tList.get(depth - 1);
				
				if(tData == null){
					tData = new TableInfo();
					insertChk = true;
				}
				
				if(depth == 1){
					tmp = idx.split("\\.");
					tmpInt = Integer.parseInt(tmp[0]);
					tmpInt++;
					tmpStr = tmpInt + ".";
					
					tData.setIdx(tmpStr);
					
					for(int i = depth; i < tList.size(); i++){
						tData = tList.get(i);
						tData.setMore(false);
					}
					
					nLabel = new JLabel(tmpStr);
					nLabel.setSize(40, 20);
					nLabel.setLocation(WIDTH * depth, HEIGHT * cnt);

					jText = new JTextField();
					jText.setSize(100, 20);
					jText.setLocation(60 + WIDTH * depth, HEIGHT * cnt);
					
					pageTf = new JTextField();
					pageTf.setSize(50, 20);
					pageTf.setLocation(300, HEIGHT * cnt);
				}
				else{
					tmpInt = Integer.parseInt(idx.substring(idx.length() - 1, idx.length()));
					tmpInt++;
					tmpStr = idx.substring(0, idx.length() - 1);
					tmpStr += tmpInt;
					
					tData.setDepth(depth);
					tData.setIdx(idx);
					tData.setMore(true);
					if(insertChk)
						tList.add(tData);
					
					for(int i = depth; i < tList.size(); i++){
						tData = tList.get(i);
						tData.setMore(false);
					}
					
					nLabel = new JLabel(tmpStr);
					nLabel.setSize(40, 20);
					nLabel.setLocation(WIDTH * depth, HEIGHT * cnt);

					jText = new JTextField();
					jText.setSize(100, 20);
					jText.setLocation(60 + WIDTH * depth, HEIGHT * cnt);
					
					pageTf = new JTextField();
					pageTf.setSize(50, 20);
					pageTf.setLocation(300, HEIGHT * cnt);
				}
				prevStr = idx;
				idx = tmpStr;
				
				nPanel.add(nLabel);
				nPanel.add(jText);
				nPanel.add(pageTf);
				
				nPanel.updateUI();
				
				oldDepth = depth;
				
			} else if(chk.equals("->")){
				depth++;
				
				prevBtn.setEnabled(true);
				
				if(oldDepth == depth - 1)
					nextBtn.setEnabled(false);
				
				if(depth == 2){
					if (tList.size() > depth - 1)
						tData = tList.get(depth - 1);
					if (prevStr.length() == 2 || tData == null || !tData.isMore())
						tmpStr = prevStr + "1";
					else {
						String temp = tData.getIdx();
						tmpInt = Integer.parseInt(temp.substring(
								temp.length() - 1, temp.length()));
						tmpInt++;
						tmpStr = temp.substring(0, temp.length() - 1);
						tmpStr += tmpInt;
					}
					prevLb.setText(tmpStr);
					prevLb.setLocation(WIDTH * depth, HEIGHT * cnt);
					
					prevTf.setLocation(60 + WIDTH * depth, HEIGHT * cnt);
				}
				else{
					if (tList.size() > depth - 1)
						tData = tList.get(depth - 1);
					if(tData == null || !tData.isMore())
						tmpStr = prevStr + ".1";
					else{
						String temp = tData.getIdx();
						tmpInt = Integer.parseInt(temp.substring(
								temp.length() - 1, temp.length()));
						tmpInt++;
						tmp = temp.split("\\.");
						for(int i = 0; i < tmp.length - 1; i++)
							tmpStr += tmp[i] + ".";
						tmpStr += tmpInt;
					}
					
					prevLb.setText(tmpStr);
					prevLb.setLocation(WIDTH * depth, HEIGHT * cnt);

					prevTf.setLocation(60 + WIDTH * depth, HEIGHT * cnt);
				}
				idx = tmpStr;
			} else if(chk.equals("<-")){
				depth--;
				nextBtn.setEnabled(true);
				if(depth == 1){
					prevBtn.setEnabled(false);
					tmp = prevStr.split("\\.");
					tmpInt = Integer.parseInt(tmp[0]);
					tmpInt++;
					tmpStr = tmpInt + ".";
					
					prevLb.setText(tmpStr);					
					prevLb.setLocation(WIDTH * depth, HEIGHT * cnt);

					prevTf.setLocation(60 + WIDTH * depth, HEIGHT * cnt);
				}
				else{
					if(tList.size() > depth - 1)
						tData = tList.get(depth - 1);
					String temp = tData.getIdx();
					
					tmpInt = Integer.parseInt(temp.substring(temp.length() - 1, temp.length()));
					tmpInt++;
					
					tmpStr = temp.substring(0, temp.length() - 1);
					tmpStr += tmpInt;
					
					prevLb.setText(tmpStr);
					prevLb.setLocation(WIDTH * depth, HEIGHT * cnt);

					prevTf.setLocation(60 + WIDTH * depth, HEIGHT * cnt);	
				}
				idx = tmpStr;
			} else{
				;
			}
			prevLb = nLabel;
			prevTf = jText;
			
			tmpStr = "";
			tData = null;
			
			ct.repaint();
		}
	}
}
