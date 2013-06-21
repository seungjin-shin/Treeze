package com.mindmap.androidmind.adapter;

import java.util.ArrayList;

import com.example.androidmind.R;
import com.mindmap.adroidmind.data.Class;
import com.mindmap.adroidmind.data.Lecture;

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

		super(context, layoutResource, object);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View view = null;

		if (v == null) {

			view = mInflater.inflate(R.layout.classitem, null);
		} else {

			view = v;
		}

		final Class classObject = this.getItem(position);

		if (classObject != null) {
			TextView classTV = (TextView) view.findViewById(R.id.classitemTV);
			classTV.setText(classObject.getClassName());
		}

		return view;

	}



}
