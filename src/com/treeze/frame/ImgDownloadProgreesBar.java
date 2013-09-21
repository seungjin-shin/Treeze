package com.treeze.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import JDIalog.AddGrid;

import com.google.gson.Gson;
import com.treeze.data.ArrayUploadedFile;
import com.treeze.data.ArrayVersion;
import com.treeze.data.TreezeStaticData;
import com.treeze.data.UploadedFile;
import com.treeze.data.Version;

public class ImgDownloadProgreesBar extends JFrame{


	Container content;
	Toolkit tk;
	Dimension screenSize;
	ImageIcon icon;
	Image signPress = TreezeStaticData.TREEZE_UPDATE_BG;
	AddGrid addGrid = new AddGrid();
	String SERVERIP;
	String version;
	String curDir = System.getProperty("user.dir");
	JProgressBar bar;
	int stateCnt = 38;
	public ImgDownloadProgreesBar(){
		super("Treeze Updater");
		System.out.println(version);
		content = getContentPane();
		tk = Toolkit.getDefaultToolkit();
		screenSize = tk.getScreenSize();
		makeGUI();
	}
	public void makeGUI()
	{
		content = getContentPane();
		setSize(434, 181);
		setLocation(screenSize.width/2 - 150, screenSize.height/2 - 100);
		setUndecorated(true);
		setLayout(addGrid.gbl);

		bar = new JProgressBar();
		bar.setValue(0);
		bar.setSize(100, 100);
		bar.setStringPainted(true);
		content.setBackground(new Color(0,0,0,0));
		addGrid.getInsets().set(120, 40, 10, 40);
		bar.setBackground(new Color(0,0,0,0));
		addGrid.addGrid(addGrid.gbl, addGrid.gbc, bar, 0, 0, 1, 1, 1, 1, this);
		System.out.println(curDir);
		System.out.println(version);
		setVisible(true);
		
	}
	

	
	public void readCnfFile(){
		String tmp;
		File file = new File(curDir, "treeze.cnf");

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

			while ((tmp = in.readLine()) != null) {
				if(tmp.equals("[serverIp]")){
					SERVERIP = in.readLine();
					break;
				}	
			}
			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (SERVERIP == null){
			System.out.println("treeze.cnf Err");
			return;
		}
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		if(icon == null)
			icon = makeResizedImageIcon(this.getWidth(), this.getHeight(),signPress);
		g.drawImage(icon.getImage(), 0, 0, 434, 181, null);
	}
	
	public ImageIcon makeResizedImageIcon(int width, int height, Image imgs) {

		BufferedImage imageBuff = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		Image img = imgs;
		Graphics g = imageBuff.createGraphics();
		Image scaleImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		g.drawImage(scaleImg, 0, 0, new Color(0, 0, 0), null);
		return new ImageIcon(scaleImg);

	}


	
//	public void downloadAll(){
//		//File treeze.cnf ¿–æÓº≠ ip∫∏∞Ì 
//		
//		URL url = null;
//		final int NETWORK_UPLOADED_FILEINFO = 0;
//		final int NETWORK_UPLOADED_IMG = 1;
//		final int NETWORK_GETVERSION = 2;
//		int flag = NETWORK_GETVERSION;
//		
//		
//		InputStream is;
//		StringBuffer sbResult = new StringBuffer();
//		Gson gson = new Gson();
//		ArrayList<UploadedFile> uploadedFileList = new ArrayList<UploadedFile>();
//		HttpURLConnection connection;
//		
//		try {
//			while(true){
//				sbResult.delete(0, sbResult.capacity());
//				
//				if(flag == NETWORK_GETVERSION){
//					url = new URL("http://" + SERVERIP + ":8080/treeze/getLastVersion?userType=" + Version.STUDENT);
//				}
//				
//			else if (flag == NETWORK_UPLOADED_FILEINFO) {
//				url = new URL(
//						"http://" + SERVERIP + ":8080/treeze/file?version=" + version + "&userType=" + Version.STUDENT);
//			} else if (flag == NETWORK_UPLOADED_IMG) {
//				for (int i = 0; i < uploadedFileList.size(); i++) {
//					
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					bar.setValue(bar.getValue() + stateCnt);
//					bar.paintImmediately(0, 0, 434, 181);
//					// File file = new
//					// File("http://61.43.139.10:8080/treeze/img/"+uploadedFileList.get(i).getId());
//					url = new URL("http://" + SERVERIP + ":8080/treeze/img/"
//							+ uploadedFileList.get(i).getId());
//					connection = (HttpURLConnection) url.openConnection();
//					
//					File downDir = new File(curDir);
//
//					File file = new File(downDir, uploadedFileList
//							.get(i).getFileName());
//					
//					FileOutputStream fileOutput = new FileOutputStream(file);
//					InputStream inputStream = connection.getInputStream();
//
//					byte[] buffer = new byte[1024];
//
//					int bufferLength = 0;
//					while ((bufferLength = inputStream.read(buffer)) > 0)
//					{
//						fileOutput.write(buffer, 0, bufferLength);
//					}
//					fileOutput.close();
//				}
//				//networkHandler.sendMessage(msg);
//				bar.setValue(100);
//				return;
//			}
//			connection = (HttpURLConnection) url.openConnection();
//
//			if (connection != null) {
//				connection.setConnectTimeout(5000); // Set Timeout
//				connection.setUseCaches(false);
//
//				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//					BufferedReader br = new BufferedReader(
//							new InputStreamReader(
//									connection.getInputStream(), "UTF-8"));
//
//					String strLine = null;
//
//					while ((strLine = br.readLine()) != null) {
//						sbResult.append(strLine + "\n");
//					}
//
//					br.close();
//				}
//
//				connection.disconnect();
//				
//				if(flag == NETWORK_GETVERSION){
//					System.out.println(sbResult.toString());
//					
//					ArrayVersion arrayVersion = gson.fromJson(sbResult.toString(), ArrayVersion.class);
//					Version v = arrayVersion.getVersion();
//					version = v.getVersion();
//					flag = NETWORK_UPLOADED_FILEINFO;
//				}
//				else if (flag == NETWORK_UPLOADED_FILEINFO) {
//					
////					java.lang.reflect.Type type = new TypeToken<ArrayUploadedFile>() {
////					}.getType();
//					System.out.println(sbResult.toString());
//					gson = new Gson();
//					ArrayUploadedFile jonResultFileUploadList = gson
//							.fromJson(sbResult.toString(), ArrayUploadedFile.class);
//					uploadedFileList = jonResultFileUploadList
//							.getUploadedFiles();
//					flag = NETWORK_UPLOADED_IMG;
//				}
//			}
//			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
 public void changeProgressBar(int value){
	 System.out.println("[changeProogressBar Value] = " + value);
	 bar.setValue(value);
	bar.paintImmediately(0, 0, 434, 181);

 }
public void downloadEnd() {
	// TODO Auto-generated method stub
	this.setVisible(false);
}

}
