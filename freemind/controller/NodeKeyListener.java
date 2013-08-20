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
/*$Id: NodeKeyListener.java,v 1.16.18.2 2006/01/12 23:10:12 christianfoltin Exp $*/

package freemind.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.jibx.runtime.impl.InputStreamWrapper;

import freemind.Frame.TicketAnswerFrame;
import freemind.json.CurrentPositionOfNav;
import freemind.json.FreemindGson;
import freemind.json.TreezeData;
import freemind.modes.MindMapNode;
import freemind.modes.NodeAdapter;
import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapMapModel;

/**
 * The KeyListener which belongs to the node and cares for Events like C-D
 * (Delete Node). It forwards the requests to NodeController.
 */
public class NodeKeyListener implements KeyListener {

	private Controller c;
	private KeyListener mListener;
	private FreemindManager fManager;
	private boolean pressedShiftKey = false; 
	
	public NodeKeyListener(Controller controller) {
		c = controller;
		fManager = FreemindManager.getInstance();
	}

	public void register(KeyListener listener) {
		this.mListener = listener;
	}

	public void deregister() {
		mListener = null;
	}

	public void keyPressed(KeyEvent e) {
		OutputStream os = fManager.getOs();
		
		TreezeData treezeData = new TreezeData();
		String jsonString;
		FreemindGson myGson = new FreemindGson();
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			pressedShiftKey = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_F5) {
			if(pressedShiftKey){
				if(c.getSlideShow().getfocus() == null)
					return;
				
				c.getSlideShow().show();
				
				c.getSlideShow().sendPosition();
				return;
			}
 			c.startSlideShow();
			
		} else if (e.getKeyCode() == KeyEvent.VK_F6) {
			new TicketAnswerFrame();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F4){
			new SurveyFrame(fManager.getOs()); // c 넘겨서 소켓 다 보내야대
			//new SurveyResultFrame(31, 9);
		}
		else if(e.getKeyCode() == KeyEvent.VK_F3){
		}
		else if(e.getKeyCode() == KeyEvent.VK_F8){
			//if receive q
			int position[] = {2,0};
        	int i;
        	FreemindManager fManager = FreemindManager.getInstance();
        	MindMapNode targetNode = null;
        	MindMapNode tmp = c.getMc().getRootNode();
        	for(i = 0; i < position.length; i++){
       			tmp = (MindMapNode)tmp.getChildAt(position[i]);
        	}
        	
        	if(tmp.hasChildren()){
        		for(i = 0; i < tmp.getChildCount(); i++){// 완료
        			MindMapNode forSearchQNode;
        			forSearchQNode = (MindMapNode)tmp.getChildAt(i);
        			if(forSearchQNode.getText().equals("Q"))
        				break;
        		}
        		targetNode = (MindMapNode) tmp.getChildAt(i); 
        		
        	}
        	else{
        		System.out.println("not have Question Node!");
        		return;
        	}
        	
        	c.getMc()._setFolded(targetNode, true);
        	
			c.getMc().nodeChanged(targetNode);
		}
		else if(e.getKeyCode() == KeyEvent.VK_F9){
			//set Q node
			
			if (!fManager.isAddQuestionNodeInfo()) {
				fManager.setAddQuestionNodeInfo(true);

				c.addQNode.addNodeForQuestion(c.getMc().getRootNode());

				// modify Q node
				c.addQNode.modifyForQuestion(c.getMc().getRootNode());
				c.getMc().edit.stopEditing();

				/*
				modify last node, why not change
				in modifyForQuestion() method?????
				*/
				NodeAdapter tmp = (NodeAdapter)c.getMc().getSelected();
				tmp.setText("Q");
				tmp.setNodeTypeStr("Question");
				c.getMap().nodeChanged(tmp);

				System.out.println("NodeKeyListener : set QuestionNodeInfo");

			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_F10){
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_F16){
			c.recurSetUploadXmlID((NodeAdapter) c.getMc().getRootNode());
			c.makeUploadXml();
				
			UploadToServer uts = new UploadToServer();
			try {
				uts.doFileUpload();
				uts.doXmlUpload();
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_F15){
			c.removeAllIcon((NodeAdapter) c.getMc().getRootNode());
			c.setSequenceIcon();
			System.out.println("KeyL : F15");
		}
		else if(e.getKeyCode() == KeyEvent.VK_F14){
			System.out.println("KeyL : F14");
		}
 		
		if (mListener != null)
			mListener.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		if (mListener != null)
			mListener.keyReleased(e);
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			pressedShiftKey = false;
	}

	public void keyTyped(KeyEvent e) {
		if (mListener != null)
			mListener.keyTyped(e);
	}
	
}
	

