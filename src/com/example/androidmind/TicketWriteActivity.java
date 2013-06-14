package com.example.androidmind;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;

import com.example.adroidmind.data.CurrentPositionOfNav;
import com.example.adroidmind.data.MindNode;
import com.example.adroidmind.data.Ticket;
import com.example.adroidmind.data.TicketInfo;
import com.example.android.network.ProfessorSocket;
import com.example.androidmind.Gson.FreemindGson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TicketWriteActivity extends Activity implements OnClickListener {

	MindNode parent;
	Button writeBtn;
	EditText ticketTitle;
	EditText ticketContents;
	// com.example.adroidmind.data.Class classinfo;
	OutputStream outputstream;
	Gson gson = new Gson();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ticketwrite);
		writeBtn = (Button) findViewById(R.id.writeBtn);
		ticketTitle = (EditText) findViewById(R.id.ticketTitleET);
		if (MindNode.getNow() instanceof Ticket) {
			ticketTitle.setFocusable(false);
			ticketTitle.setClickable(false);
			ticketTitle.setText("[Re]" + MindNode.getNow().getNodeStr());
		}
		// Bundle bundle = getIntent().getExtras();
		// classinfo = bundle.getParcelable("class");
		ticketContents = (EditText) findViewById(R.id.ticketcontentsET);
		writeBtn.setOnClickListener(this);
		try {
			if(ProfessorSocket.getSocket().getOutputStream()!=null)
			outputstream = ProfessorSocket.getSocket().getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		TicketInfo ticketInfo;
		Ticket ticket = new Ticket(MindNode.getNow(), ticketTitle.getText()
				.toString(), ticketContents.getText().toString(), "김미림");
		if (MindNode.getNow().equals(MindNode.getRoot())) {
			ticketInfo = new TicketInfo(ticket.getNodeStr(),
					ticket.getContents(), ticket.getPosition(), "root", ticket.getuserName());
		} else {
			ticketInfo = new TicketInfo(ticket.getNodeStr(),
					ticket.getContents(), ticket.getPosition(), ticket
							.getParentNode().getPosition(), ticket.getuserName());
		}
		FreemindGson myGson = new FreemindGson();
		String jsonResult = myGson.toJson(ticketInfo);
		jsonResult = "2" + jsonResult;
		try {
			
			outputstream.write(jsonResult.getBytes("UTF-8"));
			Log.d("check", "티켓보냄");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("check", "티켓 보내지 못함");
			e.printStackTrace();
		}
		Intent i = new Intent();
		i.putExtra("result", "update");
		this.setResult(Activity.RESULT_OK, i);
		finish();
	}

}
