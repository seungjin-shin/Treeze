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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.json.simple.JSONObject;

public class SlideShow {
	SlideData focus;
	int pagenum = 0;
	ImgFrame imgFrame = new ImgFrame(this);
	String str = new String();
	Controller c;
	
	public SlideShow(Controller c){
		this.c = c;
	}
	
	
	void setfocus(SlideData focus) {
		if(focus.getPrev() ==null){
			this.focus = focus;
			show();
		}
		else{
		if (this.focus != null && this.focus.imgCnt > 1
				&& this.focus.imgCnt > pagenum + 1) {
			pagenum++;
		} else {
			pagenum = 0;
			while (focus.imgCnt == 0)
				focus = focus.next;
			this.focus = focus;
		}
		str = focus.imgPath + focus.nodeName + ".jpg";
		System.out.println(focus.imgPath + focus.nodeName + ".jpg");
		show();
		}
	}

	SlideData getfocus() {
		return focus;
	}

	void SlideshowStart() {

	}

	public void setfocusnext() {
		// TODO Auto-generated method stub
		if (focus.getNext() != null)
			this.setfocus(focus.getNext());
		else {
			System.out.println("마지막슬라이드입니다.");
			imgFrame.setVisible(false);
		}
	}

	public void show() {
		// TODO Auto-generated method stub

		imgFrame.show();

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
					OutputStream os;
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						nextShow();
						
						ArrayList<Integer> idxList = focus.getIdxList();
						
						CurrentPositionOfNav sendPs = new CurrentPositionOfNav();
						
						String jsonString;
						FreemindGson myGson = new FreemindGson();

						sendPs.setPosition(idxList);

						jsonString = myGson.toJson(sendPs);
						System.out.println(jsonString);
						
						for(int i = 0; i < c.getNaviOs().size(); i++){
							os = c.getNaviOs().get(i);
							try {
								os.write(jsonString.getBytes()); // 다 보내
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						 // 여기서도 소켓 보내야대
					} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						showpause();
					} else
						return;
				}

			});
		}

		private void nextShow() {
			// TODO Auto-generated method stub
			this.slideShow.setfocusnext();
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			BufferedImage i = null;
			try {
				if (slideShow.getfocus().imgCnt > 1) {

					i = ImageIO.read(new File(slideShow.getfocus().imgPath
							+ slideShow.getfocus().getNodeName() + (pagenum)
							+ ".jpg"));
				} else {
					i = ImageIO.read(new File(slideShow.getfocus().imgPath
							+ slideShow.getfocus().getNodeName() + ".jpg"));
				}
				// i = ImageIO.read(new File(str));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 변형하고자 하는 이미지
			Image img = i.getScaledInstance((int) xsize, (int) ysize,
					Image.SCALE_DEFAULT); // 사이즈 변경
			g.drawImage(img, 0, 0, this); // 이미지 그리기. 좌표 수정

		}

	}

	public void showpause() {
		// TODO Auto-generated method stub
		imgFrame.setVisible(false);
	}
}