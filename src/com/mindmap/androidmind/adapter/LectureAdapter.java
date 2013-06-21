package com.mindmap.androidmind.adapter;

import java.util.ArrayList;


import com.example.androidmind.R;
import com.mindmap.adroidmind.data.Lecture;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.TextView;

public class LectureAdapter extends ArrayAdapter<Lecture> {
	private LayoutInflater mInflater;

	public LectureAdapter(Context context, int layoutResource,
			ArrayList<Lecture> object) {

		super(context, layoutResource, object);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View view = null;


		if (v == null) {
			view = mInflater.inflate(R.layout.coursesitem, null);
		} else {

			view = v;
		}

		final Lecture lecture = this.getItem(position);

		if (lecture != null) {
			ImageView onlineImgView = (ImageView) view.findViewById(R.id.lectureonline);
			TextView subjectTV = (TextView) view.findViewById(R.id.subjectTV);
			TextView professorTV = (TextView) view
					.findViewById(R.id.professorTV);

			subjectTV.setText(lecture.getLectureName());

			professorTV.setText(lecture.getProfessorName());
			if(lecture.getStateOfLecture()){
				onlineImgView.setBackgroundResource(R.drawable.online);
			}
			else{
				onlineImgView.setBackgroundResource(R.drawable.offline);
			}

		}

		return view;

	}

}
