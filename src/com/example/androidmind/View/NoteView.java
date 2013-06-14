package com.example.androidmind.View;

import org.json.simple.JSONObject;

import com.example.adroidmind.data.Notes;
import com.example.androidmind.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Address;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

public class NoteView extends LinearLayout implements OnClickListener,
		OnLongClickListener {
	TextView noteText;
	PopupWindow notePopupWindow;
	Context context;
	Notes note;
	InputMethodManager mImm;

	public NoteView(Context context, Notes note) {
		super(context);
		// TODO Auto-generated constructor stub
		noteText = new TextView(context);
		noteText.setTextSize(20);
		noteText.setTextColor(Color.BLACK);
		noteText.setBackgroundColor(Color.rgb(247, 242, 128));
		if (note.getNote() == null)
			noteText.setText("필기를 입력하세요");
		else
			noteText.setText(note.getNote());
		addView(noteText);
		noteText.setOnClickListener(this);
		noteText.setOnLongClickListener(this);
		
		this.context = context;
		this.notePopupWindow = notePopupWindow;
		this.note = note;
		
	}

	void setPopupWindow(PopupWindow notePopupWindow) {
		this.notePopupWindow = notePopupWindow;
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub

		this.notePopupWindow.dismiss();
		// this.setEnabled(false);
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			this.notePopupWindow.dismiss();
			break;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		AlertDialog.Builder aDialog = new AlertDialog.Builder(context);
		aDialog.setTitle("필기하기");
		LayoutInflater vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout loginLayout = (LinearLayout) vi.inflate(R.layout.notice,
				null);
		final EditText contents = (EditText) loginLayout.findViewById(R.id.edit);

		contents.setText(note.getNote());
		aDialog.setView(loginLayout);
		// aDialog.show();
		aDialog.setNeutralButton("입력", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				setNoteText(contents.getText().toString());
			
			}
		}).setNegativeButton("취소", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

			}
		}).show();

	}

	protected void setNoteText(String str) {
		// TODO Auto-generated method stub

		if (str.length() != 0) {
			note.setNote(str);
			noteText.setText(str);
		} else {
			note.setNote(null);
			noteText.setText("필기를입력하세요");
		}
	}

}
