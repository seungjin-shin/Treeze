package freemind.modes;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
import freemind.modes.mindmapmode.MindMapController;

public class TOCClickVersion extends JFrame {
	private Container ct;
	private int depth = 1;
	private String idx = "1.";
	private int cnt = 1;
	
	private final int HEIGHT = 20;
	private final int WIDTH = 20;
	private JPanel nPanel;
	private ImgPanel iPanel;
	
	private String filePath;
	
	JLabel nLabel = null;
	JTextField jText = null;
	
	int tmpInt;
	String tmpStr = "1.";
	int oldDepth;
	boolean nextAv = false;
	boolean prevAv = false;
	JButton enterBtn;
	JButton nextBtn;
	JButton prevBtn;
	JScrollPane sPanel;
	ArrayList<SlideData> sList = new ArrayList<SlideData>();
	SlideData sData;
	MindMapController mc;
	TOCMouseEvent mouseEvent = new TOCMouseEvent();
	TOCMouseMotionEvent mouseMotionEvent = new TOCMouseMotionEvent();
	int mouseLoc = 0;
	int i;
	ArrayList<IndexRange> iList = new ArrayList<IndexRange>();
	IndexRange iData;
	int iCnt = 0;
	public TOCClickVersion(ArrayList<SlideData> sList, MindMapController mc) {
		this.mc = mc;
		
		this.sList = (ArrayList<SlideData>) sList.clone();
		for(i = 0; i < sList.size(); i++){
			sData = sList.get(i);
			if(sData.getImgCnt() == 1){
				iData = new IndexRange(iCnt, iCnt);
				iCnt++;
			}
			else{
				iData = new IndexRange(iCnt, iCnt + sData.getImgCnt() - 1);
				iCnt = iCnt + sData.getImgCnt();
			}
			iList.add(iData);
		}
		
		filePath = sList.get(0).getImgPath();
		ct = getContentPane();
		setSize(300, 600);
		setLayout(null);

//		JLabel title = new JLabel("마인드맵에 과목명을 넣고 포커스를 노드에 잡은 후 이미지를 클릭하면 ");
//		title.setSize(100, 40);
//		title.setLocation(80, 20);
//		add(title);
		
		
		Border b1 = BorderFactory.createLineBorder( Color.black );

		iPanel = new ImgPanel();
		
		iPanel.setBorder(b1);
		iPanel.addMouseListener(mouseEvent);
		iPanel.addMouseMotionListener(mouseMotionEvent);
		
		//iPanel.setLayout(new FlowLayout());
//		iPanel.setSize(200, 600);
//		iPanel.setLocation(500, 50);

		sPanel = new JScrollPane(iPanel);
//		sPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);  
		sPanel.setBounds(50, 50, 200, 400);
		add(sPanel);

		//add(iPanel);

		setTitle("textarea");
		setVisible(true);
		setLocation(300, 200);
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
				if(sData.getImgCnt() == 1){
					if(sData.getNodeName().equals(""))
						img[cnt] = new ImageIcon(filePath + "undefined" + undefinedCnt++ + ".jpg").getImage();
					else
						img[cnt] = new ImageIcon(filePath + sData.getNodeName() + ".jpg").getImage();
					g.drawImage(img[cnt], 10, IMGHEIGHT * cnt + 10, 100, 100, null);
					g.drawString(cnt + 1 + "", 10, IMGHEIGHT * cnt + 8);
					cnt++;
				}
				else{
					int j = sData.getImgCnt();
					for(int k = 0; k < j; k++){
						if(sData.getNodeName().equals(""))
							img[cnt] = new ImageIcon(filePath + "undefined" + k + ".jpg").getImage();
						else
							img[cnt] = new ImageIcon(filePath + sData.getNodeName() + k + ".jpg").getImage();
						g.drawImage(img[cnt], 10, IMGHEIGHT * cnt + 10, 100, 100, null);
						g.drawString(cnt + 1 + "", 10, IMGHEIGHT * cnt + 8);
						cnt++;
					}
				}
				
			}
			if(mouseLoc < cnt)
				g.drawRect(10, mouseLoc * 130, 100, 120);
			setPreferredSize(new Dimension(180, 100 * cnt + cnt * 30));
			sPanel.updateUI();
		}
	}
	
	class TOCMouseEvent extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e){
			int x = e.getX();
			int y = e.getY();
			
			if(x > 10 && x < 100){
				int rIdx = 0;
				for(int i = 0; i < iList.size(); i++){
					if(mouseLoc >= iList.get(i).getStart() && mouseLoc <= iList.get(i).getEnd()){
						rIdx = i;
						break;
					}
				}
				if(!sList.get(rIdx).getNodeName().equals(""))
					mc.setChildName(sList.get(rIdx).getNodeName());
				else
					mc.setChildName("undefined");
				mc.addNew(mc.getSelected(), MindMapController.NEW_CHILD, null);
				//mc.addnew
				sList.remove(rIdx);
				iList.clear();
				iCnt = 0;
				
				if(sList.size() == 0)
				     mc.removeTOC();
				
				for(i = 0; i < sList.size(); i++){
					sData = sList.get(i);
					if(sData.getImgCnt() == 1){
						iData = new IndexRange(iCnt, iCnt);
						iCnt++;
					}
					else{
						iData = new IndexRange(iCnt, iCnt + sData.getImgCnt() - 1);
						iCnt = iCnt + sData.getImgCnt();
					}
					iList.add(iData);
				}
			}
		}
	}
	
	class TOCMouseMotionEvent extends MouseMotionAdapter{
		@Override
		public void mouseMoved(MouseEvent e){
			int x = e.getX();
			int y = e.getY();

			if(x > 10 && x < 100)
				mouseLoc = y / 130;
			else
				mouseLoc = 0;
		}
	}
}

class IndexRange{
	int start;
	int end;
	public IndexRange(int s, int e){
		start = s;
		end = e;
	}
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
}
