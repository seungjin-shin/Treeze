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
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import freemind.json.CurrentPositionOfNav;
import freemind.json.FreemindGson;
import freemind.modes.MindIcon;
import freemind.modes.MindMapNode;
import freemind.modes.UploadToServer;

/**
 * The KeyListener which belongs to the node and cares for Events like C-D
 * (Delete Node). It forwards the requests to NodeController.
 */
public class NodeKeyListener implements KeyListener {

	private Controller c;

	private KeyListener mListener;

	public NodeKeyListener(Controller controller) {
		c = controller;
	}

	public void register(KeyListener listener) {
		this.mListener = listener;

	}

	public void deregister() {
		mListener = null;
	}

	public void keyPressed(KeyEvent e) {
		final String NAVINUM = "0";
		OutputStream os;

		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			if(c.getSlideList().size() == 0)
				return;
			
			c.getSlideShow().setfocus(c.getSlideList().get(0));
			c.getSlideShow().show();
			
			for(int i = 0; i < c.getNaviOs().size(); i++){
				os = c.getNaviOs().get(i);
				try {
					if(os != null)
						os.write((NAVINUM + "start").getBytes());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			System.out.println("start");
			
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(c.getSlideList().size() == 0)
				return;
			
			c.getSlideShow().show();
			
			ArrayList<Integer> idxList = c.getSlideShow().getfocus().getIdxList();
			
			CurrentPositionOfNav sendPs = new CurrentPositionOfNav();
			
			String jsonString;
			FreemindGson myGson = new FreemindGson();

			sendPs.setPosition(idxList);

			jsonString = myGson.toJson(sendPs);
			System.out.println(jsonString);
			
			for(int i = 0; i < c.getNaviOs().size(); i++){
				os = c.getNaviOs().get(i);
				try {
					if(os != null)
						os.write((NAVINUM + jsonString).getBytes()); // 다 보내
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			System.out.println(jsonString);
		}
		else if(e.getKeyCode() == KeyEvent.VK_F4){
			new SurveyFrame(c.getNaviOs()); // c 넘겨서 소켓 다 보내야대
			//new SurveyResultFrame(31, 9);
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
