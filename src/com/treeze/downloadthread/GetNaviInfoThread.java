package com.treeze.downloadthread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.treeze.data.ArrayNaviInfo;
import com.treeze.data.ClassInfo;
import com.treeze.data.JsonTicket;
import com.treeze.data.MindNode;
import com.treeze.data.Ticket;
import com.treeze.data.TreezeStaticData;

public class GetNaviInfoThread extends Thread{
	URL url = null;
	String ip = TreezeStaticData.IP;
	// Message msg = new Message();
	StringBuffer sbResult = new StringBuffer();
	ClassInfo classInfo = ClassInfo.getInstance();
	public  GetNaviInfoThread(){
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();


		HttpURLConnection connection;
		sbResult.delete(0, sbResult.capacity());
		try {

			url = new URL("http://" + ip
					+ ":8080/treeze/getNaviInfoes?classId="
					+ classInfo.getClassId());

			connection = (HttpURLConnection) url.openConnection();

			if (connection != null) {
				connection.setConnectTimeout(5000); // Set Timeout
				connection.setUseCaches(false);

				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(connection.getInputStream(),
									"UTF-8"));

					String strLine = null;

					while ((strLine = br.readLine()) != null) {
						sbResult.append(strLine + "\n");
					}

					br.close();
				}
				connection.disconnect();
				System.out.println("asdasdas"+sbResult.toString());
				Gson gson = new Gson();
				ArrayNaviInfo naviInfoes = gson.fromJson(sbResult.toString(), ArrayNaviInfo.class);
				
				for(int i=0;i<naviInfoes.getNaviInfoes().size();i++){
					
					MindNode.setNav(MindNode.getNodeuseNodeID(MindNode.getRoot(),naviInfoes.getNaviInfoes().get(i).getNodeID()));
				}

				// networkHandler.sendMessage(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block

			//
			e.printStackTrace();
			DownLoadAllTicket networkThread = new DownLoadAllTicket(classInfo);
			networkThread.start();

		}

	
	}
}
