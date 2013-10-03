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
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.apache.batik.dom.util.HashTable;


import freemind.Frame.SurveyFrame;
import freemind.json.CurrentPositionOfNav;
import freemind.json.FreemindGson;
import freemind.json.NaviInfo;
import freemind.json.TreezeData;
import freemind.modes.MindIcon;
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
	
	HashMap<String, Image> resizeImgHashMap = new HashMap<String, Image>();

	public HashMap<String, Image> getResizeImgHashMap() {
		return resizeImgHashMap;
	}

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
		setAndRemoveCurruntIcon(focus);
		this.focus = focus;
	}
	
	public void setAndRemoveCurruntIcon(NodeAdapter cur){
		NodeAdapter prev = this.focus;
		int pos;
		
		if(prev != null){
			if(prev.getIcons() != null){
				prev.getIcons().remove(MindIcon.factory("button_ok"));
			}
			FreemindManager.getInstance().getMc().nodeChanged(prev);
		}
		
		if(cur.getIcons() != null){
			pos = cur.getIcons().size();
		}
		else{
			pos = -1;
		}
		cur.addIcon(MindIcon.factory("button_ok"), pos);
		FreemindManager.getInstance().getMc().nodeChanged(cur);
		
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
//					cur.getNodeType().forQuizAct(); // 설문 보내는 곳 
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
//					cur.getNodeType().forQuizAct(); // 설문 보내는곳 
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
			imgFrame.setCurrentImage();
			imgFrame.show();
			imgFrame.repaint();
		}

	}

	class ImgFrame extends JFrame {
		SlideShow slideShow;
		Toolkit tk = Toolkit.getDefaultToolkit();
		double xsize = tk.getScreenSize().getWidth();
		double ysize = tk.getScreenSize().getHeight();
		Image curImage;
		
		public ImgFrame(SlideShow slideShow) {
			// TODO Auto-generated constructor stub
			
			this.setSize((int) xsize, (int) ysize);
			this.slideShow = slideShow;
			this.setUndecorated(true);
			this.setAlwaysOnTop(true);
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
					if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_PAGE_DOWN || e.getKeyCode() == KeyEvent.VK_RIGHT) {
						nextShow();
						sendPosition();
						System.out.println("Sldie Space");
						
					} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						showpause();
					}
					else if(e.getKeyCode() == KeyEvent.VK_PAGE_UP || e.getKeyCode() == KeyEvent.VK_LEFT){
						prevShow();
						sendPosition();
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
//			ImageIcon imgIcon;
//			
//			Image img = new ImageIcon(fManager.getDownPath() + System.getProperty("file.separator") + fManager.getClassId(), slideShow.getfocus().getImgPath() + ".jpg").getImage();
//			
//			imgIcon = fManager.makeResizedImageIcon((int)xsize, (int)ysize, img);
//			BufferedImage i = null;
//			try {
//					i = ImageIO.read(new File(fManager.getDownPath() + System.getProperty("file.separator") + fManager.getClassId(), slideShow.getfocus().getImgPath() + ".jpg")); 
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//			
////			i.get
//			
//			Image img = i.getScaledInstance((int)xsize, (int)ysize,
//					Image.SCALE_DEFAULT); // 사이즈 변경
//			
////			ImageIcon tmpIcon = fManager.makeResizedImageIcon((int)xsize, (int)ysize, img);
//			
//			g.drawImage(img, 0, 0, this);
//			g.drawImage(tmpIcon.getImage(), 0, 0, this); // 이미지 그리기. 좌표 수정
			g.drawImage(curImage, 0, 0, this);
		}
		
		public void setCurrentImage(){
			curImage = resizeImgHashMap.get(focus.getImgPath());
		}
	}
	
	
	public void sendPosition(){
		int idx;
		NodeAdapter selNode = c.getSlideShow().getfocus();
		String nodeID;
		if (selNode.isRoot()){
			treezeData.setDataType(TreezeData.NAVI);
			treezeData.getArgList().clear();
			treezeData.getArgList().add("start");
			
			//return; // search the other loc

			jsonString = myGson.toJson(treezeData);
					
			try {
				if (os != null) {
					FreemindManager.getInstance().getLogger().info("send navi : " + jsonString);
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
		else{
			nodeID = selNode.getNodeID();
		}
		
		NaviInfo naviInfo = new NaviInfo();

		naviInfo.setNodeID(nodeID);
		
		fManager.getUploadToServer().sendNaviInfo(naviInfo);

		jsonString = myGson.toJson(naviInfo);
		
		treezeData.setDataType(TreezeData.NAVI);
		treezeData.getArgList().clear();
		treezeData.getArgList().add(jsonString);
		
		jsonString = myGson.toJson(treezeData);
		System.out.println(jsonString);
		
		try {
			if (os != null) {
				FreemindManager.getInstance().getLogger().info("send navi : " + jsonString);
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