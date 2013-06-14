package com.example.androidmind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.adroidmind.data.ArrayClass;
import com.example.adroidmind.data.ArrayLecture;
import com.example.adroidmind.data.CurrentPositionOfNav;
import com.example.adroidmind.data.Lecture;
import com.example.androidmind.View.MindView;
import com.example.androidmind.View.ProfileView;
import com.example.androidmind.adapter.ClassAdapter;
import com.example.androidmind.adapter.LectureAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnItemClickListener,
		OnClickListener {
	String professorEmail;
	long lectureId;

	ArrayList<Lecture> lectureList;
	ArrayList<com.example.adroidmind.data.Class> classList;

	ListView listview;

	LectureAdapter lectureAdapter;
	ClassAdapter classAdapter;

	MindView mindview;
	StringBuffer sbResult = new StringBuffer();
	Handler networkHandler;
	Gson gson = new Gson();
	LinearLayout lecturehede;
	Button resetBtn;
	int networkFlag;
	final int NETWORK_FLAG_GET_LECTURELIST = 0;
	final int NETWORK_FLAG_GET_CLASSLIST = 1;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		LinearLayout root = new LinearLayout(this);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout coursesListView = (LinearLayout) inflater.inflate(
				R.layout.courseslist, null);
		root.setBackgroundColor(Color.rgb(141, 198, 63));
		root.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		LinearLayout horizontallayout = new LinearLayout(this);
		horizontallayout.setOrientation(LinearLayout.HORIZONTAL);
		horizontallayout.setBackgroundColor(Color.rgb(141, 198, 63));

		root.setBackgroundColor(Color.WHITE);
		horizontallayout.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		root.setOrientation(LinearLayout.VERTICAL);
		setContentView(root);
		LinearLayout.LayoutParams weight = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		weight.weight = 10;

		LinearLayout.LayoutParams rootChildLogoWeigh = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		rootChildLogoWeigh.weight = 10;
		root.addView(LayoutInflater.from(this).inflate(R.layout.logo, null),
				rootChildLogoWeigh);
		LinearLayout.LayoutParams rootChildHorizontalWeigh = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		rootChildHorizontalWeigh.weight = 3;
		root.addView(horizontallayout, rootChildHorizontalWeigh);
		horizontallayout.addView(
				LayoutInflater.from(this).inflate(R.layout.profile, null),
				weight);
		horizontallayout.addView(coursesListView, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		lecturehede = (LinearLayout) findViewById(R.id.lecturelisthead);

		// lecturehede.setVisibility(-1);
		resetBtn = (Button) findViewById(R.id.resetBtn);
		resetBtn.setOnClickListener(this);
		listview = (ListView) findViewById(R.id.coursesLV);
		listview.setDivider(null);
		// coursesList.add(new Courses("임베디드 시스템", "이민석", true));
		// coursesList.add(new Courses("데이터 베이스", "김 영웅", false));

		// listview.setAdapter(lectureAdapter);
		listview.setOnItemClickListener(this);
		LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
		margin.setMargins(0, 0, 5, 5);
		margin.weight = 3;
		coursesListView.setLayoutParams(margin);
		updateGetallLectureList();
		networkHandler = new Handler() {
			public void handleMessage(Message msg) {
				if (networkFlag == NETWORK_FLAG_GET_LECTURELIST) {
					Type type = new TypeToken<ArrayLecture>() {
					}.getType();
					ArrayLecture jonResultlecturelist = (ArrayLecture) gson
							.fromJson(sbResult.toString(), type);
					lectureList = jonResultlecturelist.getLectures();
					updateList();
				} else if (networkFlag == NETWORK_FLAG_GET_CLASSLIST) {

					Type type = new TypeToken<ArrayClass>() {
					}.getType();
					ArrayClass jonResultlecturelist = (ArrayClass) gson
							.fromJson(sbResult.toString(), type);
					classList = jonResultlecturelist.getClasses();
					updateList();

				}
			}
		};

	}

	public void updateGetallLectureList() {
		networkFlag = NETWORK_FLAG_GET_LECTURELIST;
		NetworkThread networkThread = new NetworkThread();
		networkThread.start();
	}

	public void updateGetClassList() {
		networkFlag = NETWORK_FLAG_GET_CLASSLIST;
		NetworkThread networkThread = new NetworkThread();
		networkThread.start();
	}

	public void updateList() {

		if (networkFlag == NETWORK_FLAG_GET_LECTURELIST) {
			lectureAdapter = new LectureAdapter(this, R.layout.courseslist,
					lectureList);
			listview.setAdapter(lectureAdapter);
			lecturehede.setVisibility(1);
		} else {
			lecturehede.setVisibility(-1);
			classAdapter = new ClassAdapter(this, R.layout.courseslist,
					classList);
			listview.setAdapter(classAdapter);
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		if (networkFlag == NETWORK_FLAG_GET_LECTURELIST) {
			professorEmail = lectureList.get(position).getProfessorEmail();
			lectureId = lectureList.get(position).getLectureId();
			updateGetClassList();
		} else {

			Intent i = new Intent(this, MindMapActivity.class);
			com.example.adroidmind.data.Class selectClass = classList
					.get(position);
			i.putExtra("class", selectClass);
			startActivity(i);
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
				if (networkFlag == NETWORK_FLAG_GET_LECTURELIST) {
					url = new URL(
							"http://61.43.139.10:8080/treeze/getAllLectures");
					
				} else {
					url = new URL(
							"http://61.43.139.10:8080/treeze/getClasses/?lectureId="
									+ lectureId);
					
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

				updateGetallLectureList();
				e.printStackTrace();

			}

		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		if (networkFlag == NETWORK_FLAG_GET_CLASSLIST) {
			updateGetallLectureList();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == resetBtn) {
			updateGetallLectureList();
		}

	}
}
