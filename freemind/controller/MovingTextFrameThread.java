package freemind.controller;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import freemind.Frame.TextFrame;

public class MovingTextFrameThread extends Thread{
	TextFrame textFrame;
	
	public void run() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		double xsize = tk.getScreenSize().getWidth();
		double ysize = tk.getScreenSize().getHeight();
		
		textFrame = new TextFrame("Receive a Question", (int)xsize, (int)ysize);
		int frameHeight = textFrame.getHeight();
		int changeHeight = 1;
		boolean overHalf = false;
		while(true){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textFrame.setLocation((int)xsize - textFrame.getWidth(),(int) ysize - changeHeight);
			if(frameHeight < changeHeight)
				overHalf = true;
			
			if(overHalf){
				changeHeight -= 1;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				changeHeight += 1;
			
			if(changeHeight < 0){
				textFrame.setVisible(false);
				break;
			}
		}
		FreemindManager.getInstance().slideShow.getImgFrame().requestFocus();
	}
}
