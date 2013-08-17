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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.jibx.runtime.impl.InputStreamWrapper;

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
		
 		if (e.getKeyCode() == KeyEvent.VK_F5) {
			
			//c.setFocus((NodeAdapter)c.getMc().getRootNode());
 			
			c.getSlideShow().setfocus((NodeAdapter)c.getMc().getRootNode().getChildAt(0));
			c.getSlideShow().show();
			
			c.getSlideShow().sendPosition();
			
//			treezeData.setDataType(TreezeData.NAVI);
//			treezeData.getArgList().clear();
//			treezeData.getArgList().add("start");
//			
//			//return; // search the other loc
//
//			jsonString = myGson.toJson(treezeData);
//					
//			try {
//				if(os != null){
//					os.write(jsonString.getBytes("UTF-8"));
//					os.flush();
//				}
//			} catch (UnsupportedEncodingException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			
//			System.out.println("start");
	
			
		} else if (e.getKeyCode() == KeyEvent.VK_F6) {
			if(c.getSlideShow().getfocus() == null)
				return;
			
			c.getSlideShow().show();
			
//			ArrayList<Integer> idxList = c.getSlideShow().getfocus().getIdxList();
//			
//			CurrentPositionOfNav sendPs = new CurrentPositionOfNav();
//			
//			String jsonString;
//			FreemindGson myGson = new FreemindGson();
//
//			sendPs.setPosition(idxList);
//
//			jsonString = myGson.toJson(sendPs);
//			System.out.println(jsonString);
//			
//			for(int i = 0; i < c.getNaviOs().size(); i++){
//				os = c.getNaviOs().get(i);
//				try {
//					if(os != null)
//						os.write((NAVINUM + jsonString).getBytes()); // 다 보내
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//			
//			System.out.println(jsonString);
			
			c.getSlideShow().sendPosition();			
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_F4){
			new SurveyFrame(fManager.getOs()); // c 넘겨서 소켓 다 보내야대
			//new SurveyResultFrame(31, 9);
		}
		else if(e.getKeyCode() == KeyEvent.VK_F3){
			//set slide show
			NodeAdapter root = (NodeAdapter)c.getMc().getRootNode();
        	NodeAdapter next;// = (NodeAdapter)mc.getRootNode();
        	
        	//set FreemindManager isSlideshow 
        	fManager.setSlideShowInfo(true);
        	
        	//set root
        	root.setPrev(null);
        	if(root.hasChildren()){
        		next = (NodeAdapter)root.getChildAt(0);
        		root.setNext(next);
//        		prev = cur;
//        		cur = (NodeAdapter)cur.getChildAt(0);
        		
        		for(int i = 0; i < root.getChildCount(); i++){ // root direct childs set
            		c.recurSetSlideShowInfo((NodeAdapter)root.getChildAt(i));
            	}
        		System.out.println("NodeKeyListener : set slideShowInfo");
        	}
        	else{
        		System.out.println("only root");
        		return;
        	}
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
        	
//        	fManager.setQuestion(true); // 질문 받았을 때 newChildAction에서 처리하려고
//        	fManager.setTicketContent("content test");
//        	fManager.setTicketTitle("Title test");
//        	fManager.setTicketWriter("write t");
//        	
//        	c.getMc().addNew(targetNode, MindMapController.NEW_CHILD, null);
//        	fManager.setQuestion(false);
//        	fManager.setTicketContent("");
//        	fManager.setTicketTitle("");
//        	fManager.setTicketWriter("");
        	
        	//mc.edit.stopEditing();
        	//targetNode.setFolded(true);
        	
        	c.getMc()._setFolded(targetNode, true);
        	
//        	MindIcon icon = MindIcon.factory("help");
//			if(!targetNode.isHaveQuestion()){
//				targetNode.addIcon(icon, -1); // ? 아이콘 한번만
//				targetNode.setHaveQuestion(true);
//			}
        	
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
			
			c.chkNodeType.checkNodeType((NodeAdapter)c.getMc().getRootNode());
			System.out.println("KeyListener : check node type");
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_F16){
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
			
			
//			new SurveyResultFrame(0,1,"dd");
//			try {
//				
//				fManager.getMc().load(new File("/Users/dewlit/Desktop/test/Linux.mm"));
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_F15){
			c.removeAllIcon((NodeAdapter) c.getMc().getRootNode());
			c.setSequenceIcon();
			System.out.println("KeyL : F15");
		}
		else if(e.getKeyCode() == KeyEvent.VK_F14){
			
			
			
//			UploadToServer uts = new UploadToServer();
//			try {
//				uts.dd();
//			} catch (ClientProtocolException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			System.out.println(fManager.getClassId());
			System.out.println("KeyL : F14");
		}
 		
		if (mListener != null)
			mListener.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		if (mListener != null)
			mListener.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		if (mListener != null)
			mListener.keyTyped(e);
	}
	
}
	

