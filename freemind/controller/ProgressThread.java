//package freemind.controller;
//
//public class ProgressThread extends Thread{
//@Override
//public void run() {
//	ProgressFrame progFrame = new ProgressFrame();
//	int i = 0;
//	
//	while(progFrame.getBar().getValue() != 100){
//		System.out.println("whlie : " + i);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		progFrame.getBar().setValue(i);
//		System.out.println(progFrame.getBar().isShowing() +"" +  progFrame.getBar().isValid() + progFrame.getBar().isVisible());
//		progFrame.getBar().paintImmediately(50, 300, 300, 100);
////		progFrame.repaint();
//		i++;
//	}
//	progFrame.setVisible(false);
//}
//}


package freemind.controller;

public class ProgressThread extends Thread{
	FreemindManager fManager;
	String title;
	public ProgressThread(String title) {
		fManager = FreemindManager.getInstance();
		this.title = title;
	}
	
@Override
public void run() {
	ProgressFrame progFrame = new ProgressFrame(title);
	
	while(progFrame.getBar().getValue() != 100){
		progFrame.getBar().setValue(fManager.getLodingValue());
		progFrame.getBar().paintImmediately(0, 0, 500, 300);
		progFrame.repaint();
	}
	progFrame.setVisible(false);
}
}
