package freemind.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.json.simple.JSONObject;

import freemind.Frame.SurveyFrame;
import freemind.json.CurrentPositionOfNav;
import freemind.json.FreemindGson;
import freemind.json.TreezeData;
import freemind.modes.NodeAdapter;

public class SlideShow {
	NodeAdapter focus;
	ImgFrame imgFrame = new ImgFrame(this);
	FreemindManager fManager;
	
	OutputStream os;
	
	final String NAVINUM = "0";
	
	TreezeData treezeData = new TreezeData();
	String jsonString;
	FreemindGson myGson = new FreemindGson();
	
	public OutputStream getOs() {
		return os;
	}

	public SlideShow(FreemindManager f) {
		fManager = f;
	}
	String str = new String();
	Controller c;
	
	public Controller getC() {
		return c;
	}

	public void setC(Controller c) {
		this.c = c;
	}

	public JFrame getImgFrame() {
		return imgFrame;
	}
	
	public void setImgFrame(ImgFrame imgFrame) {
		this.imgFrame = imgFrame;
	}
	
	public void setfocus(NodeAdapter focus) {
//		if(focus.getPrev() == null){
//			this.focus = focus;
//			show();
//		}
//		else{
//			if (this.focus != null && this.focus.imgCnt > 1
//				&& this.focus.imgCnt > pagenum + 1) {
//			pagenum++;
//		} else {
//			pagenum = 0;
//			while (focus.imgCnt == 0)
//				focus = focus.next;
//			this.focus = focus;
//		}
//		str = focus.imgPath + focus.nodeName + ".jpg";
//		System.out.println(focus.imgPath + focus.nodeName + ".jpg");
//		show();
//		}
		this.focus = focus;
	}

	NodeAdapter getfocus() {
		return focus;
	}

	void SlideshowStart() {

	}

	public void setfocusnext() {
		// TODO Auto-generated method stub
		
		NodeAdapter next;
		NodeAdapter cur = focus.getNext();
		while (true) {
			
			next = cur;

			if (next != null){ 
				
				if(next.getImgPath() != null){
					this.setfocus(next);
					show();
					break;
				}
				else{
					cur.getNodeType().forSurveyAct(); // 설문 보내는 곳 
					cur = cur.getNext();
					continue;
				}
			}
			else {
				System.out.println("마지막슬라이드입니다.");
				imgFrame.setVisible(false);
				break;
			}
		}
	}
	
	public void setfocusprev() {
		// TODO Auto-generated method stub
		
		NodeAdapter prev;
		NodeAdapter cur = focus.getPrev();
		while (true) {
			
			prev = cur;

			if (prev != null){ // modify for do not have img
				
				if(prev.getImgPath() != null){
					this.setfocus(prev);
					show();
					break;
				}
				else{
					cur.getNodeType().forSurveyAct(); // 설문 보내는곳 
					cur = cur.getPrev();
					continue;
				}
			}
			else {
				System.out.println("첫 슬라이드입니다.");
				imgFrame.setVisible(false);
				break;
			}
		}
	}

	public void show() {
		// TODO Auto-generated method stub
		if(focus.getImgPath() != null){
			imgFrame.show();
		}

	}

	class ImgFrame extends JFrame {
		SlideShow slideShow;
		Toolkit tk = Toolkit.getDefaultToolkit();
		double xsize = tk.getScreenSize().getWidth();
		double ysize = tk.getScreenSize().getHeight();
		
		public ImgFrame(SlideShow slideShow) {
			// TODO Auto-generated constructor stub
			
			this.setSize((int) xsize, (int) ysize);
			this.slideShow = slideShow;
			this.setUndecorated(true);
			this.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						nextShow();
						// 학생 보내야대
						//ArrayList<Integer> idxList = focus.getIdxList();
						
//						CurrentPositionOfNav sendPs = new CurrentPositionOfNav();
//						
//						String jsonString;
//						FreemindGson myGson = new FreemindGson();
//
//						//sendPs.setPosition(idxList);
//
//						jsonString = myGson.toJson(sendPs);
//						System.out.println(jsonString);
//						
//						for(int i = 0; i < c.getNaviOs().size(); i++){
//							os = c.getNaviOs().get(i);
//							try {
//								if(os != null)
//									os.write((NAVINUM + jsonString).getBytes()); // 다 보내
//							} catch (IOException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//						}
						
						sendPosition();
						System.out.println("Sldie Space");
						
						
						
					} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						showpause();
					} else if(e.getKeyCode() == KeyEvent.VK_PAGE_DOWN){
						nextShow();
						sendPosition();
//						ArrayList<Integer> idxList = focus.getIdxList();
//						
//						CurrentPositionOfNav sendPs = new CurrentPositionOfNav();
//						
//						String jsonString;
//						FreemindGson myGson = new FreemindGson();
//
//						sendPs.setPosition(idxList);
//
//						jsonString = myGson.toJson(sendPs);
//						System.out.println(jsonString);
//						
//						for(int i = 0; i < c.getNaviOs().size(); i++){
//							os = c.getNaviOs().get(i);
//							try {
//								if(os != null)
//									os.write((NAVINUM + jsonString).getBytes()); // 다 보내
//							} catch (IOException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//						}
					}
					else if(e.getKeyCode() == KeyEvent.VK_PAGE_UP){
						prevShow();
						sendPosition();
//						ArrayList<Integer> idxList = focus.getIdxList();
//						
//						CurrentPositionOfNav sendPs = new CurrentPositionOfNav();
//						
//						String jsonString;
//						FreemindGson myGson = new FreemindGson();
//
//						sendPs.setPosition(idxList);
//
//						jsonString = myGson.toJson(sendPs);
//						System.out.println(jsonString);
//						
//						for(int i = 0; i < c.getNaviOs().size(); i++){
//							os = c.getNaviOs().get(i);
//							try {
//								if(os != null)
//									os.write((NAVINUM + jsonString).getBytes()); // 다 보내
//							} catch (IOException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//						}
					}
					else if(e.getKeyCode() == KeyEvent.VK_F4){
						new SurveyFrame();
					}
					else
						return;
				}
			});
		}
		
		private void nextShow() {
			// TODO Auto-generated method stub
			this.slideShow.setfocusnext();
		}
		private void prevShow(){
			this.slideShow.setfocusprev();
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			BufferedImage i = null;
			try {
					i = ImageIO.read(new File(fManager.getDownPath() + System.getProperty("file.separator") + fManager.getClassId(), slideShow.getfocus().getImgPath() + ".jpg")); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			Image img = i.getScaledInstance((int) xsize, (int) ysize,
					Image.SCALE_DEFAULT); // 사이즈 변경
			g.drawImage(img, 0, 0, this); // 이미지 그리기. 좌표 수정

		}

	}
	
	public void sendPosition(){
		ArrayList<Integer> idxReverseList = new ArrayList<Integer>();
		int idx;
		ArrayList<Integer> idxList = new ArrayList<Integer>();
		NodeAdapter selNode = c.getSlideShow().getfocus();
		NodeAdapter selNodeParent;
		
		if (selNode.isRoot()){
			treezeData.setDataType(TreezeData.NAVI);
			treezeData.getArgList().clear();
			treezeData.getArgList().add("start");
			
			//return; // search the other loc

			jsonString = myGson.toJson(treezeData);
					
			try {
				if (os != null) {
					System.out.println("sendPosition");
					os.write(jsonString.getBytes("UTF-8"));
					os.flush();
				}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("start");
			return;
		}
		
		while (!selNode.isRoot()) {
			selNodeParent = (NodeAdapter) selNode.getParentNode();
			idx = selNodeParent.getChildPosition(selNode);
			idxReverseList.add(idx);
			selNode = selNodeParent;
		}
		
		for (int i = idxReverseList.size(); i > 0; i--) {
			idxList.add(idxReverseList.get(i - 1));
		}
		
		CurrentPositionOfNav sendPs = new CurrentPositionOfNav();

		sendPs.setPosition(idxList);

		jsonString = myGson.toJson(sendPs);
		
		treezeData.setDataType(TreezeData.NAVI);
		treezeData.getArgList().clear();
		treezeData.getArgList().add(jsonString);
		
		jsonString = myGson.toJson(treezeData);
		System.out.println(jsonString);
		
		try {
			if (os != null) {
				System.out.println("sendPosition");
				os.write(jsonString.getBytes("UTF-8"));
				os.flush();
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void setOs(OutputStream os){
		this.os = os;
	}
	
	public void showpause() {
		// TODO Auto-generated method stub
		imgFrame.setVisible(false);
	}
}