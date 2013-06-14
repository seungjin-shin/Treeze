package com.example.androidmind;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.w3c.dom.Node;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;

import com.example.adroidmind.data.ArrayClass;
import com.example.adroidmind.data.ArrayLecture;
import com.example.adroidmind.data.ArrayTicketInfo;
import com.example.adroidmind.data.ArrayUploadedFile;
import com.example.adroidmind.data.MindNode;
import com.example.adroidmind.data.Ticket;
import com.example.adroidmind.data.TicketInfo;
import com.example.adroidmind.data.UploadedFile;
import com.example.android.network.ProfessorSocket;
import com.example.androidmind.View.MindView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class MindMapActivity extends Activity {
	MindView mindview;
	com.example.adroidmind.data.Class classinfo;
	StringBuffer sbResult = new StringBuffer();
	private ArrayList<MindNode> nodes = new ArrayList<MindNode>();
	ArrayList<UploadedFile> uploadedFileList;
	private int flag;
	final int NETWORK_UPLOADED_FILE = 0;
	final int NETWORK_UPLOAD_MINDINFO = 1;
	final int NETWORK_DOWNLOAD_ALL_TICKET = 2;
	private Handler networkHandler;
	String xml = new String();
	Gson gson = new Gson();
	ProgressDialog dialog;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		Bundle bundle = getIntent().getExtras();
		classinfo = bundle.getParcelable("class");
		File file = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/Treeze/" + classinfo.getClassId() + "/");
		// if(file.exists()){
		//
		// }
		dialog = ProgressDialog.show(this, "",
				"마인드맵 정보를 가져오는 중 입니다. 잠시만 기다려주세요.", true);
		NetworkThread thread = new NetworkThread();
		thread.start();

		flag = NETWORK_UPLOADED_FILE;

		dialog.show();
		networkHandler = new Handler() {
			public void handleMessage(Message msg) {
				if (flag == NETWORK_UPLOADED_FILE) {
					// dialog.show();
					Type type = new TypeToken<ArrayUploadedFile>() {
					}.getType();
					ArrayUploadedFile jonResultFileUploadList = (ArrayUploadedFile) gson
							.fromJson(sbResult.toString(), type);
					uploadedFileList = jonResultFileUploadList
							.getUploadedFiles();
					File SDCardRoot = new File(Environment
							.getExternalStorageDirectory().getAbsolutePath()
							+ "/Treeze/" + classinfo.getClassId() + "/");
					if (SDCardRoot.exists()) {
						CreateMindMap();
					} else {
						flag = NETWORK_UPLOAD_MINDINFO;
						NetworkThread thread = new NetworkThread();
						thread.start();
					}
				} else if (flag == NETWORK_UPLOAD_MINDINFO) {
					CreateMindMap();

				} else {
					Type type = new TypeToken<ArrayTicketInfo>() {
					}.getType();
					ArrayTicketInfo jonResultTicketList = (ArrayTicketInfo) gson
							.fromJson(sbResult.toString(), type);
					for (int i = 0; i < jonResultTicketList.getTicket().size(); i++)
						new Ticket(MindNode.getNode(jonResultTicketList
								.getTicket().get(i).getTicketPosition()),
								jonResultTicketList.getTicket().get(i)
										.getTicketTitle(), jonResultTicketList
										.getTicket().get(i).getContents(),
								jonResultTicketList.getTicket().get(i)
										.getUserName());

					mindview.invalidate();

				}
			}
		};

		// String xml2 = new String();
		//
		// BufferedReader in;
		// try {
		// in = new BufferedReader(new InputStreamReader(new
		// FileInputStream("/mnt/sdcard/rjs" + ".mm"), "UTF-8"));
		// String s = "";
		//
		// while ((s = in.readLine()) != null) {
		// xml2 += s;
		// //System.out.println(s);
		// }
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// String xml2 = "<map version=\"0.9.0\">"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"리눅스 강의\">"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" POSITION=\"left\" TEXT=\" 리눅스 소개\">"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"리눅스의 역사\"/>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Linux도 운영 체제\"/>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Linux 전에는 Unix…\"/>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Linux의 특징\"/>"
		// + "</node>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" POSITION=\"left\" TEXT=\" 공개 소스 소프트웨어 (OSS or FOSS)\"/>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" POSITION=\"left\" TEXT=\" Linux 사용에 필요핚 기본 개념과 용어\">"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Linux 배포판 들\"/>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"그 중 가장 인기 많은… ubuntu\"/>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Linux의 구조\"/>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"인터페이스 부분\"/>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Linux 기본 개념들…\"/>"
		// + "</node>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" POSITION=\"right\" TEXT=\" 시스템 호출 정리\">"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"프로세스 관련 명령\"/>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Top 명령\"/>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"Windows 작업관리자\"/>"
		// +
		// "<node CREATED=\"1365038113483\" ID=\"ID_1002961678\" MODIFIED=\"1365038132371\" TEXT=\"시스템 호출\"/>"
		// + "</node>" + "</node>" + "</map>";
		// MindMap map = (MindMap) xstream.fromXML(xml);
		//
		// map.nodes.get(0);
		// float screenWidth = getResources().getDisplayMetrics().widthPixels;
		// float screenHeight = getResources().getDisplayMetrics().heightPixels;
		// MindNode root = new MindNode(map.nodes.get(0).getTEXT(),
		// (int) (screenWidth / 2), (int) (screenHeight / 2));
		// nodes.add(root);
		// for (int i = 0; i < map.nodes.get(0).nodes.size(); i++) {
		// nodetoMindNode(root, map.nodes.get(0).nodes.get(i));
		// }
		// // while(true){
		// // temp =
		// // }
		// // xstream.processAnnotations(MindMap.class);
		// // XStream xstream = new XStream();
		//
		// // http://113.198.84.74:8080/treeze/
		//
		// mindview = new MindView(this, nodes, classinfo);
		// // requestWindowFeature(Window.FEATURE_NO_TITLE);
		// mindview.setBackgroundColor(Color.WHITE);
		//
		// setContentView(mindview);
		// LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
		// .getSystemService(LAYOUT_INFLATER_SERVICE);

		// LinearLayout linear = new LinearLayout(this);
		// LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		// ViewGroup.LayoutParams.FILL_PARENT,
		// ViewGroup.LayoutParams.FILL_PARENT);
		// linear.setLayoutParams(params);
		// linear.setOrientation(LinearLayout.VERTICAL);
		// Button btn = new Button(this);
		// EditText etx = new EditText(this);
		// linear.addView(mindview, 50, 30);
		// linear.addView(btn);
		// linear.addView(etx);
		// setContentView(linear);
		//
	}

	public void CreateMindMap() {

		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(
					Environment.getExternalStorageDirectory().getAbsolutePath()
							+ "/Treeze/" + classinfo.getClassId() + "/"
							+ uploadedFileList.get(0).getFileName()), "UTF-8"));
			String s = "";

			while ((s = in.readLine()) != null) {
				xml += s;
			}
			startMindView();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dialog.cancel();
			finish();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			dialog.cancel();
			finish();
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			dialog.cancel();
			finish();
			e.printStackTrace();
		}
		flag = NETWORK_DOWNLOAD_ALL_TICKET;
		NetworkThread thread = new NetworkThread();
		thread.start();

	}

	public void startMindView() {
		XStream xstream = new XStream();
		xstream.processAnnotations(MindMap.class);
		MindMap map = (MindMap) xstream.fromXML(xml);
		map.nodes.get(0);
		float screenWidth = getResources().getDisplayMetrics().widthPixels;
		float screenHeight = getResources().getDisplayMetrics().heightPixels;
		MindNode root = new MindNode(map.nodes.get(0).getTEXT(),
				(int) (screenWidth / 2), (int) (screenHeight / 2));
		nodes.add(root);
		for (int i = 0; i < map.nodes.get(0).nodes.size(); i++) {
			nodetoMindNode(root, map.nodes.get(0).nodes.get(i));
		}
		// while(true){
		// temp =
		// }
		// xstream.processAnnotations(MindMap.class);
		// XStream xstream = new XStream();

		// http://113.198.84.74:8080/treeze/

		mindview = new MindView(this, nodes, classinfo);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		mindview.setBackgroundColor(Color.WHITE);
		dialog.cancel();

		setContentView(mindview);

		LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
				.getSystemService(LAYOUT_INFLATER_SERVICE);
	}

	public void nodetoMindNode(MindNode parent, node child) {
		MindNode temp = new MindNode(parent, child.getTEXT(),
				child.getPOSITION());
		nodes.add(temp);
		if (child.nodes != null)
			for (int i = 0; i < child.nodes.size(); i++) {
				nodetoMindNode(temp, child.nodes.get(i));
			}
	}

	class NetworkThread extends Thread {

		HttpResponse response;
		InputStream is;
		URL url = null;
		Message msg = new Message();

		@Override
		public void run() {

			HttpURLConnection connection;
			sbResult.delete(0, sbResult.capacity());
			try {
				if (flag == NETWORK_UPLOADED_FILE) {
					url = new URL(
							"http://61.43.139.10:8080/treeze/img/?classId="
									+ classinfo.getClassId());
				} else if (flag == NETWORK_UPLOAD_MINDINFO) {
					for (int i = 0; i < uploadedFileList.size(); i++) {

						// File file = new
						// File("http://61.43.139.10:8080/treeze/img/"+uploadedFileList.get(i).getId());
						url = new URL("http://61.43.139.10:8080/treeze/img/"
								+ uploadedFileList.get(i).getId());
						connection = (HttpURLConnection) url.openConnection();
						File SDCardRoot = new File(Environment
								.getExternalStorageDirectory()
								.getAbsolutePath()
								+ "/Treeze/" + classinfo.getClassId() + "/");

						// SDCardRoot.mkdir();
						SDCardRoot.mkdirs();
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
					networkHandler.sendMessage(msg);
					return;
				} else {
					url = new URL(
							"http://61.43.139.10:8080/treeze//getAllTickets/?classId="
									+ classinfo.getClassId());
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

					networkHandler.sendMessage(msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				dialog.cancel();
				finish();
				e.printStackTrace();

			}

		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if (ProfessorSocket.getSocket() != null)
			ProfessorSocket.endSocket();

	}

}

@XStreamAlias("map")
class MindMap {
	@XStreamAsAttribute
	String version;
	@XStreamImplicit(itemFieldName = "node")
	ArrayList<node> nodes;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ArrayList<node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<node> nodes) {
		this.nodes = nodes;
	}

}

@XStreamAlias("node")
class node implements Cloneable {

	@XStreamImplicit(itemFieldName = "node")
	ArrayList<node> nodes;

	@XStreamAsAttribute
	String CREATED;

	@XStreamAsAttribute
	String ID;
	@XStreamAsAttribute
	String MODIFIED;
	@XStreamAsAttribute
	String POSITION;
	@XStreamAsAttribute
	String TEXT;

	public String getCREATED() {
		return CREATED;
	}

	public void setCREATED(String cREATED) {
		CREATED = cREATED;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMODIFIED() {
		return MODIFIED;
	}

	public void setMODIFIED(String mODIFIED) {
		MODIFIED = mODIFIED;
	}

	public String getPOSITION() {
		return POSITION;
	}

	public void setPOSITION(String pOSITION) {
		POSITION = pOSITION;
	}

	public String getTEXT() {
		return TEXT;
	}

	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}

}
