package freemind.main;

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
import com.sun.xml.internal.rngom.digested.DOptionalPattern;

import freemind.controller.FreemindManager;
import freemind.json.ArrayUploadedFile;
import freemind.json.UploadedFile;

public class DownLoadNetworkThread extends Thread {
		final String DOWNPATH = FreemindManager.getInstance().getDonwPath();
		final String SERVERIP = FreemindManager.getInstance().getServerIP();
		
		Long classId;
		InputStream is;
		URL url = null;
		StringBuffer sbResult = new StringBuffer();
		final int NETWORK_UPLOADED_FILEINFO = 0;
		final int NETWORK_UPLOADED_IMG = 1;
		int flag = NETWORK_UPLOADED_FILEINFO;
		Gson gson = new Gson();
		ArrayList<UploadedFile> uploadedFileList = new ArrayList<UploadedFile>();
		//Message msg = new Message();
		public DownLoadNetworkThread(long classId) {
			// TODO Auto-generated constructor stub
			this.classId = classId;
		}
		public DownLoadNetworkThread(Long classId2, int flag, ArrayList<UploadedFile> uploadedFileList) {
			// TODO Auto-generated constructor stub
			this.classId = classId;
			this.flag = flag;
			this.uploadedFileList = uploadedFileList;
		}
		
		@Override
		public void run() {

			HttpURLConnection connection;
			sbResult.delete(0, sbResult.capacity());
			try {
				if (flag == NETWORK_UPLOADED_FILEINFO) {
					url = new URL(
							"http://" + SERVERIP + ":8080/treeze/img/?classId="
									+ classId);
				} else if (flag == NETWORK_UPLOADED_IMG) {
					for (int i = 0; i < uploadedFileList.size(); i++) {

						// File file = new
						// File("http://61.43.139.10:8080/treeze/img/"+uploadedFileList.get(i).getId());
						url = new URL("http://" + SERVERIP + ":8080/treeze/img/"
								+ uploadedFileList.get(i).getId());
						connection = (HttpURLConnection) url.openConnection();
						File SDCardRoot = new File(DOWNPATH);

						// SDCardRoot.mkdir();
						if(!SDCardRoot.exists())
							SDCardRoot.mkdir();
						
						// + "/Trezee/" + classinfo.getClassId()
						File file = new File(SDCardRoot, uploadedFileList
								.get(i).getFileName());
						
						FileOutputStream fileOutput = new FileOutputStream(file);
						InputStream inputStream = connection.getInputStream();

						byte[] buffer = new byte[1024];

						int bufferLength = 0;
						while ((bufferLength = inputStream.read(buffer)) > 0)

						{

							fileOutput.write(buffer, 0, bufferLength);

						}

						fileOutput.close();
					}
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
