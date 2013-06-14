package com.example.androidmind.adapter;

import java.util.ArrayList;

import com.example.adroidmind.data.Class;
import com.example.adroidmind.data.Lecture;
import com.example.androidmind.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassAdapter extends ArrayAdapter<Class>{

	private LayoutInflater mInflater;

	public ClassAdapter(Context context, int layoutResource,
			ArrayList<Class> object) {

		// 상위 클래스의 초기화 과정
		// context, 0, 자료구조
		super(context, layoutResource, object);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	// 보여지는 스타일을 자신이 만든 xml로 보이기 위한 구문
	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View view = null;

		// 현재 리스트의 하나의 항목에 보일 컨트롤 얻기

		if (v == null) {

			// XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
			view = mInflater.inflate(R.layout.classitem, null);
		} else {

			view = v;
		}

		// 자료를 받는다.
		final Class classObject = this.getItem(position);

		if (classObject != null) {
			TextView classTV = (TextView) view.findViewById(R.id.classitemTV);
			classTV.setText(classObject.getClassName());
//			// 화면 출력
//			ImageView onlineImgView = (ImageView) view.findViewById(R.id.lectureonline);
//			TextView subjectTV = (TextView) view.findViewById(R.id.subjectTV);
//			TextView professorTV = (TextView) view
//					.findViewById(R.id.professorTV);
//
//			subjectTV.setText(lecture.getLectureName());
//
//			professorTV.setText(lecture.getProfessorName());
//			if(lecture.getStateOfLecture()){
//				onlineImgView.setBackgroundResource(R.drawable.online);
//			}
//			else{
//				onlineImgView.setBackgroundResource(R.drawable.offline);
//			}

		}

		return view;

	}



}
