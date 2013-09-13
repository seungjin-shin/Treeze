package freemind.controller;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class BlockScreenSaverThread extends Thread{
	
	@Override
	public void run() {
		try {
			Robot robot = new Robot();
			while (true) {
				Thread.sleep(60 * 1000); // one minute
				robot.keyPress(KeyEvent.VK_SHIFT);
				Thread.sleep(50);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
