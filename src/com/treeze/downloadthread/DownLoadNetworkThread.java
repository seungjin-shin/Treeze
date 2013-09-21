package com.treeze.downloadthread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.io.json.AbstractJsonWriter.Type;
import com.treeze.data.ArrayUploadedFile;
import com.treeze.data.TreezeStaticData;
import com.treeze.data.UploadedFile;
import com.treeze.frame.ImgDownloadProgreesBar;
import com.treeze.frame.ProfileFrame;

public class DownLoadNetworkThread extends Thread {

		Long classId;
		InputStream is;
		URL url = null;
		StringBuffer sbResult = new StringBuffer();
		final int NETWORK_UPLOADED_FILEINFO = 0;
		final int NETWORK_UPLOADED_IMG = 1;
		int flag = NETWORK_UPLOADED_FILEINFO;
		Gson gson = new Gson();
		ArrayList<UploadedFile> uploadedFileList = new ArrayList<UploadedFile>();
		static ProfileFrame profileFrame;
		//Message msg = new Message();
		public DownLoadNetworkThread(long classId,ProfileFrame profileFrame) {
			// TODO Auto-generated constructor stub
			this.classId = classId;
		    this.profileFrame  = profileFrame;
		}
		public DownLoadNetworkThread(Long classId, int flag, ArrayList<UploadedFile> uploadedFileList) {
			// TODO Auto-generated constructor stub
			this.classId = classId;
			this.flag = flag;
			this.uploadedFileList = uploadedFileList;
		}
		@Override
		public void run() {

			HttpURLConnection connection;
			String ip = TreezeStaticData.IP;
			sbResult.delete(0, sbResult.capacity());
			try {
				if (flag == NETWORK_UPLOADED_FILEINFO) {
					url = new URL(
							"http://"+ ip+":8080/treeze/img/?classId="
									+ classId);
				} else if (flag == NETWORK_UPLOADED_IMG) {
					ImgDownloadProgreesBar imgDownloadProgreesBar = new ImgDownloadProgreesBar();
					int imgProgressBarCompleteValue;
					for (int i = 0; i < uploadedFileList.size(); i++) {
						imgProgressBarCompleteValue = uploadedFileList.size();
						// File file = new
						// File("http://61.43.139.10:8080/treeze/img/"+uploadedFileList.get(i).getId());
						url = new URL("http://"+ip+":8080/treeze/img/"
								+ uploadedFileList.get(i).getId());
						connection = (HttpURLConnection) url.openConnection();
						File imgPath =new File(TreezeStaticData.PPT_IMG_PATH);
						
						// SDCardRoot.mkdir();
						imgPath.mkdirs();
						// + "/Trezee/" + classinfo.getClassId()
						File file = new File(imgPath, uploadedFileList
								.get(i).getFileName());
						FileOutputStream fileOutput = new FileOutputStream(file);
						InputStream inputStream = connection.getInputStream();

						byte[] buffer = new byte[1024];

						int bufferLength = 0;
						while ((bufferLength = inputStream.read(buffer)) > 0)

						{
							
							fileOutput.write(buffer, 0, bufferLength);

						}
						imgDownloadProgreesBar.changeProgressBar((int)((double)i/(double)imgProgressBarCompleteValue*100));
						fileOutput.close();
					}
					imgDownloadProgreesBar.downloadEnd();
					this.profileFrame.startMindMapFrame();
					//networkHandler.sendMessage(msg);
					return;
				} 
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
					if (flag == NETWORK_UPLOADED_FILEINFO) {
						
						java.lang.reflect.Type type = new TypeToken<ArrayUploadedFile>() {
						}.getType();
						ArrayUploadedFile jonResultFileUploadList = (ArrayUploadedFile) gson
								.fromJson(sbResult.toString(), type);
						uploadedFileList = jonResultFileUploadList
								.getUploadedFiles();
						flag = NETWORK_UPLOADED_IMG;
						DownLoadNetworkThread downLoadNetworkThread = new DownLoadNetworkThread(classId,flag,uploadedFileList);
						downLoadNetworkThread.start();
					}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
			
				e.printStackTrace();

			}

		}

	}
