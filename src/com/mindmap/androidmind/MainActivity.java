package com.mindmap.androidmind;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.example.androidmind.R;
import com.mindmap.adroidmind.data.MindNode;
import com.mindmap.androidmind.View.MindView;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {
	MindView mindview;
	Button loginBtn;
	Button joinBtn;
	EditText idET;
	EditText passwordET;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.loginpage);
		loginBtn = (Button) findViewById(R.id.loginBtn);
		joinBtn = (Button) findViewById(R.id.joinBtn);
		idET = (EditText) findViewById(R.id.idET);
		passwordET = (EditText) findViewById(R.id.passwordET);
		loginBtn.setOnClickListener(this);
		joinBtn.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if (view.equals(loginBtn)) {
			Intent i = new Intent(this, HomeActivity.class);
			startActivity(i);
		} else {

		}
	}

}
