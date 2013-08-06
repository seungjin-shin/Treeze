/*FreeMind - A Program for creating and viewing Mindmaps
 *Copyright (C) 2000-2001  Joerg Mueller <joergmueller@bigfoot.com>
 *See COPYING for Details
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/*$Id: NodeMouseMotionListener.java,v 1.15.14.3 2006/01/12 23:10:12 christianfoltin Exp $*/

package freemind.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import freemind.modes.MindMapNode;
import freemind.modes.NodeAdapter;

/**
 * The MouseMotionListener which belongs to every NodeView
 */
public class NodeMouseMotionListener implements MouseMotionListener,
        MouseListener {

    public static interface NodeMouseMotionObserver extends
            MouseMotionListener, MouseListener {

        void updateSelectionMethod();

    }

    private final Controller c;
    private FreemindManager fManager;

    private NodeMouseMotionObserver mListener;

    public NodeMouseMotionListener(Controller controller) {
        c = controller;
        fManager = FreemindManager.getInstance();
    }

    public void register(NodeMouseMotionObserver listener) {
        this.mListener = listener;

    }

    public void deregister() {
        mListener = null;
    }
    
    
    public void mouseClicked(MouseEvent e) {
    	if(e.getClickCount() == 2){
    		System.out.println("NodeMouseMotion double click");
    		return;
    	}
        if (mListener != null)
            mListener.mouseClicked(e);
//        System.out.println(c.getMc().getSelected().getText());
				
      //before QuestionFrame
//        String nodeText = c.getModeController().getSelected().getText();
//        MindMapNode questionNode = c.getModeController().getSelected();
//        MindMapNode questionNodeParent;//
//        ArrayList<Integer> idxReverseList = new ArrayList<Integer>();
//        int idx;
//        String idxStr = "root";
//        if(questionNode.isQuestion()){
//        	questionNode.removeIcon(0);
//        	questionNode.setQuestion(false);
//        }
//        c.getModeController().nodeChanged(questionNode); // 아이콘 지우기
//        
//        if(!questionNode.isRoot())
//        	idxStr = "";
//        
//        while(!questionNode.isRoot()){
//        	questionNodeParent = questionNode.getParentNode(); 
//        	idx = questionNodeParent.getChildPosition(questionNode);
//        	idxReverseList.add(idx);
//        	questionNode = questionNodeParent;
//        }
//        for(int i = idxReverseList.size(); i > 0; i--){
//        	if(i == 1)
//        		idxStr = idxStr + idxReverseList.get(i - 1);
//        	else
//        		idxStr = idxStr + idxReverseList.get(i - 1) + "/";
//        	System.out.print(idxReverseList.get(i - 1)); 
//        }
//        new QuestionFrame(c.getClassId() + "", idxStr, c, nodeText);
        //before QuestionFrame
    }

    public void mouseDragged(MouseEvent e) {
        if (mListener != null)
            mListener.mouseDragged(e);
    }

    public void mouseEntered(MouseEvent e) {
        if (mListener != null)
            mListener.mouseEntered(e);
    }

    public void mouseExited(MouseEvent e) {
        if (mListener != null)
            mListener.mouseExited(e);
    }
    public void mouseMoved(MouseEvent e) {
        if (mListener != null)
            mListener.mouseMoved(e);
    }
    public void mousePressed(MouseEvent e) {
        if (mListener != null)
            mListener.mousePressed(e);
    }
    public void mouseReleased(MouseEvent e) {
//    	if(!c.getMc().getSelected().hasChildren()) // last node remove edit event
//    		return;
    	
//        if (mListener != null)
//            mListener.mouseReleased(e); // 여기서 클릭
        
        NodeAdapter tmp = (NodeAdapter)c.getMc().getSelected();
        
        if(tmp.getNodeType() != null)
        	tmp.getNodeType().act();
        
//        MindMapNode selNode = c.getMc().getSelected();
//		
//		if (selNode.isQuestion()) { // at Q node
//			if (selNode.isHaveQuestion()) {
//				selNode.removeIcon(0);
//				selNode.setHaveQuestion(false);
//				c.getModeController().nodeChanged(selNode); // 아이콘 지우기
//			}
//			
//			//show Q Frame
//			if(!selNode.isFolded())
//				new TicketFrame((NodeAdapter)selNode, c);
//		}
//		else if(selNode.getTicketTitle() == null){ // at slide Node
//			MindMapNode selNodeParent;
//			//set focus
//			if (fManager.isSlideShowInfo()) {
//				c.getSlideShow().setfocus((NodeAdapter) selNode);
//				c.getSlideShow().show();
//			}
//			
//			//get node idx
//			ArrayList<Integer> idxReverseList = new ArrayList<Integer>();
//			int idx;
//			String idxStr = "root";
//			
//			if (!selNode.isRoot())
//				idxStr = "";
//			
//			while (!selNode.isRoot()) {
//				selNodeParent = selNode.getParentNode();
//				idx = selNodeParent.getChildPosition(selNode);
//				idxReverseList.add(idx);
//				selNode = selNodeParent;
//			}
//			
//			for (int i = idxReverseList.size(); i > 0; i--) {
//				if (i == 1)
//					idxStr = idxStr + idxReverseList.get(i - 1);
//				else
//					idxStr = idxStr + idxReverseList.get(i - 1) + "/";
//			}
//			System.out.println(idxStr);
//		}

        
    }
    public void updateSelectionMethod() {
        if (mListener != null)
            mListener.updateSelectionMethod();
    }

}
