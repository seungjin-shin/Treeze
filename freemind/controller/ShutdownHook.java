package freemind.controller;

import freemind.Frame.TextDialogue;

public class ShutdownHook extends Thread{

	@Override
	public void run() {
		System.out.println("shutdown exe");
		FreemindManager fManager = FreemindManager.getInstance();
		fManager.init();
		if(fManager.getLecture() != null)
			fManager.getUploadToServer().setStateOfLecture(fManager.getLecture(), false);
		if(!fManager.getLogStrArr().isEmpty()){
			fManager.getUploadToServer().logFileUpload();
		}
	}
}
