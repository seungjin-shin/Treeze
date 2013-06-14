package com.example.androidmind.View;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileView extends LinearLayout{

	public ProfileView(Context context) {
		super(context);
		
		// TODO Auto-generated constructor stub
		TextView name = new TextView(context);
		Gravity g = new Gravity();
		this.setGravity(Gravity.CENTER);
	
		//name.set
		//this.setOrientation(orientation)
	
		this.setBackgroundColor(Color.GREEN);
		name.setText("이름 : 신건영");
		this.addView(name);
	}

}
