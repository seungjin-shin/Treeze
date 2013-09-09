package freemind.controller;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.itextpdf.text.log.SysoLogger;

import freemind.Frame.TextDialogue;
import freemind.json.ArrayTicket;
import freemind.json.Ticket;
import freemind.modes.NodeAdapter;
import freemind.modes.common.CommonNodeKeyListener;
import freemind.modes.common.CommonNodeKeyListener.EditHandler;

public class AddAllTicketThread extends Thread{
	
	FreemindManager fManager;
	public AddAllTicketThread() {
		// TODO Auto-generated constructor stub
		fManager = FreemindManager.getInstance();
	}
	@Override
	public void run() {
		
		StringBuffer sbResult = new StringBuffer();
		InputStream is;
		URL url = null;

			HttpURLConnection connection;
			sbResult.delete(0, sbResult.capacity());
			try {
					url = new URL(
							"http://" + fManager.getSERVERIP() + ":8080/treeze/getAllTickets?classId=" + fManager.getClassId());
					
				connection = (HttpURLConnection) url.openConnection();

				if (connection != null) {
					connection.setConnectTimeout(5000); // Set Timeout
					connection.setUseCaches(false);
					
					
					if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
						BufferedReader br = new BufferedReader(
								new InputStreamReader(
										connection.getInputStream(), "UTF-8"));

						String strLine = null;

						while ((strLine = br.readLine()) != null) {
							sbResult.append(strLine + "\n");
						}

						br.close();
					}

					connection.disconnect();
					System.out.println(sbResult.toString());
					
				}
			}
			catch(IOException ee){
				ee.printStackTrace();
			}
			Gson gson = new Gson();
			
			ArrayTicket jonResultlecturelist = (ArrayTicket) gson.fromJson(sbResult.toString(), ArrayTicket.class);
			if(jonResultlecturelist == null){
				return;
			}
			ArrayList<Ticket> ticketList = jonResultlecturelist.getTickets();
		try {
			for (int i = 0; i < ticketList.size(); i++) {
				fManager.setTicket(ticketList.get(i));
				// try {
				// Thread.sleep(100);
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }

				fManager.getC().recurAddTicketNode(
						(NodeAdapter) fManager.getMc().getRootNode());
			}
		}
		
			catch (Exception e) {
				System.out.println("Add all tickets Error.");
				new TextDialogue(fManager.getFreemindMainFrame(), "Loading tickets error.", "Re loading...", true);
				fManager.getProfileFrame().freemindOpen(fManager.getMmFile(), fManager.getcInfo());
				return;
			}
			
			System.out.println("Add All tickets");
			new TextDialogue(fManager.getFreemindMainFrame(), "Loaded tickets, Total : " + ticketList.size(), true);
	}

}
