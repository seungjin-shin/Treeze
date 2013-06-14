package com.example.androidmind.dialog;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.example.adroidmind.data.Survey;
import com.example.androidmind.R;

import android.app.Dialog;
import android.content.Context;


import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.SimpleAdapter.ViewBinder;

public class QuizDialog extends Dialog implements View.OnClickListener {
	RadioGroup radioGroup;
	ArrayList<RadioButton> radioBtnList = new ArrayList<RadioButton>();
	ImageButton okBtn;
	TextView quizTv;
	RadioButton rd;
	RadioButton rd1;
	OutputStream out;

	public QuizDialog(Context context, Survey survey, OutputStream out) {
		// TODO Auto-generated constructor stub
		super(context);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.out = out;
		setContentView(R.layout.quizdialog);

		quizTv = (TextView) findViewById(R.id.quiz);
		radioGroup = (RadioGroup) findViewById(R.id.quizRadioGroup);
		okBtn = (ImageButton) findViewById(R.id.okBtn);
		rd = new RadioButton(context);
		rd1 = new RadioButton(context);

		RadioButton rd2 = new RadioButton(context);
		rd.setTextColor(Color.rgb(0, 0, 0));
		rd1.setTextColor(Color.rgb(0, 0, 0));

		quizTv.setText(survey.getContents());

		rd.setText("1.예");
		rd1.setText("2.아니오");
		// rd2.setText("3.엄청 비싸다");
		radioGroup.addView(rd);
		radioGroup.addView(rd1);
		// radioGroup.addView(rd2);
		okBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (rd.isChecked()) {
			try {
				out.write("0".getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				out.write("1".getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dismiss();
	}

}
