package freemind.controller;

import java.awt.event.KeyEvent;

import freemind.Frame.TextDialogue;
import freemind.json.Ticket;
import freemind.modes.MindIcon;
import freemind.modes.NodeAdapter;
import freemind.modes.common.CommonNodeKeyListener;
import freemind.modes.common.CommonNodeKeyListener.EditHandler;
import freemind.modes.mindmapmode.MindMapController;
import freemind.view.mindmapview.EditNodeTextField;

public class AddTicketThread extends Thread{
	Ticket ticket;
	
	public AddTicketThread(Ticket t) {
		ticket = t;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		FreemindManager.getInstance().setTicket(ticket);
		FreemindManager.getInstance().getC().recurAddTicketNode((NodeAdapter) FreemindManager.getInstance().getMc().getRootNode());
		
		NodeAdapter updateNode = FreemindManager.getInstance().getReceiveQNode();
		if(updateNode.getIcons().isEmpty()){ // icon only one
			MindIcon icon = MindIcon.factory("help");
			updateNode.addIcon(icon, -1);
			FreemindManager.getInstance().getMc().nodeChanged(updateNode);
		}
//		final FreemindManager fManager = FreemindManager.getInstance();
//		
//		fManager.getC().getNodeKeyListener().register(new CommonNodeKeyListener(fManager.getMc(), new EditHandler(){
//
//			public void edit(KeyEvent e, boolean addNew, boolean editLong) {
//				// no edit.
//			}}));
//		System.out.println(fManager.getC().getNodeKeyListener().toString());
//		fManager.getFreemindMainFrame().requestFocus();
		
//		EditNodeTextField tmp2 = (EditNodeTextField)FreemindManager.getInstance().getMc().edit.getmCurrentEditDialog();
//		if(tmp2 != null){
//			tmp2.getTextfield().setVisible(false);
//			
//		}
		
//		NodeAdapter parentNode = (NodeAdapter) FreemindManager.getInstance().getReceiveQNode().getParentNode();
//		FreemindManager.getInstance().getMc()._setFolded(parentNode, true);
//		fManager.getMc().nodeChanged(parentNode);
//		fManager.getMc()._setFolded(parentNode, false);
//		fManager.getMc().nodeChanged(parentNode);
		
		new TextDialogue(FreemindManager.getInstance().getSlideShow().getImgFrame(), "Receive a Question", true);
	}
}
