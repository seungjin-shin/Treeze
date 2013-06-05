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

    private NodeMouseMotionObserver mListener;

    public NodeMouseMotionListener(Controller controller) {
        c = controller;
    }

    public void register(NodeMouseMotionObserver listener) {
        this.mListener = listener;

    }

    public void deregister() {
        mListener = null;
    }

    public void mouseClicked(MouseEvent e) {
        if (mListener != null)
            mListener.mouseClicked(e);
        System.out.println("Click"); //막아야돼
        //System.out.println(c.getModeController().getSelected().getNodeLevel());
        MindMapNode questionNode = c.getModeController().getSelected();
        MindMapNode questionNodeParent;//
        ArrayList<Integer> idxReverseList = new ArrayList<Integer>();
        int idx;
        String idxStr = "root";
        if(questionNode.isQuestion()){
        	questionNode.removeIcon(0);
        	questionNode.setQuestion(false);
        }
        c.getModeController().nodeChanged(questionNode); // 아이콘 지우기
        
        if(!questionNode.isRoot())
        	idxStr = "";
        
        while(!questionNode.isRoot()){
        	questionNodeParent = questionNode.getParentNode(); // 클릭하고 노드 idx 보내서 질문 리스트 받아와
        	idx = questionNodeParent.getChildPosition(questionNode);
        	idxReverseList.add(idx);
        	questionNode = questionNodeParent;
        }
        for(int i = idxReverseList.size(); i > 0; i--){
        	if(i == 1)
        		idxStr = idxStr + idxReverseList.get(i - 1);
        	else
        		idxStr = idxStr + idxReverseList.get(i - 1) + "/";
        	System.out.print(idxReverseList.get(i - 1)); 
        }
        new QuestionFrame(c.getClassId() + "", idxStr, c);
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
        if (mListener != null)
            mListener.mouseReleased(e);
    }

    public void updateSelectionMethod() {
        if (mListener != null)
            mListener.updateSelectionMethod();
    }

}
