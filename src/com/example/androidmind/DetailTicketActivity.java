package com.example.androidmind;

import com.example.adroidmind.data.MindNode;
import com.example.adroidmind.data.Ticket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DetailTicketActivity extends Activity implements OnClickListener {
	Button replyTicket;
	TextView ticketTitleTv;
	TextView ticketContentsTv;
	Ticket ticket = (Ticket) MindNode.getNow();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailticket);
		ticketTitleTv = (TextView) findViewById(R.id.ticketTitleTV);
		ticketContentsTv = (TextView)findViewById(R.id.ticketcontentsTV);
		replyTicket = (Button)findViewById(R.id.replyBtn);
		replyTicket.setOnClickListener(this);
		ticketTitleTv.setText(ticket.getNodeStr());
		ticketContentsTv.setText(ticket.getContents());
		
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Intent i = new Intent();
		i.putExtra("result", "update");
		this.setResult(Activity.RESULT_OK,i);
		finish();
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, TicketWriteActivity.class);
		
		startActivityForResult(intent, 01);
	}
}
