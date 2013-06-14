package com.example.androidmind;

import java.util.ArrayList;

import com.example.adroidmind.data.MindNode;
import com.example.adroidmind.data.Ticket;
import com.example.androidmind.adapter.LectureAdapter;
import com.example.androidmind.adapter.TicketAdapter;
import com.thoughtworks.xstream.tools.benchmark.model.A100Parents.Parent000;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TicketActivity extends Activity implements OnClickListener, OnItemClickListener{
	private static final String Ticket = null;
	static TextView nodeNmTV;
	MindNode node =MindNode.getNow();
	ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
	ListView listview;
	TicketAdapter ticketAdapter;
	String ipAddress;
	Button writeBtn;
	Button resetBtn;
	com.example.adroidmind.data.Class classinfo;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent i = new Intent();
		Bundle bundle = getIntent().getExtras();
		classinfo = bundle.getParcelable("class");
		setContentView(R.layout.ticket);
		nodeNmTV = (TextView) findViewById(R.id.nodeNmTV);
		nodeNmTV.setTextAppearance(this,R.style.TextAppearanceLarge);
		writeBtn = (Button) findViewById(R.id.writeBtn);
		ticketAdapter = new TicketAdapter(this,R.layout.courseslist,ticketList);
		resetBtn = (Button) findViewById(R.id.listResetBtn);
		
	    nodeNmTV.setText(node.getNodeStr());
	    updateList();
	   // ticketList.add(new Ticket(node, "교수님 이슬라이드 이해가 잘안되요.", 1, "신승진"));
	   // ticketList.add(new Ticket(node, "이부분 오타 인 것 같습니다.", 2, "김미림"));
	    listview = (ListView) findViewById(R.id.ticketLV);
	    listview.setAdapter(ticketAdapter);
	    listview.setOnItemClickListener(this);
	    writeBtn.setOnClickListener(this);
	    resetBtn.setOnClickListener(this);
	   
	    
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		updateList();
		ticketAdapter.notifyDataSetChanged();
	}
	void updateList(){
		ticketList.clear();
		while((node instanceof Ticket)){
			node = node.getParentNode();
		}
		node.setNow(node);
		nodeNmTV.setText(node.getNodeStr());
		int number = 1;
	    for(int i=0;i<node.getChildCount();i++){
	    	
	    	if (node.getChildeNodes().get(i) instanceof Ticket) {
	    		((Ticket)node.getChildeNodes().get(i)).setTicketNumber(number);
				ticketList.add((Ticket) node.getChildeNodes().get(i));
				if(node.getChildeNodes().get(i).getChildCount()>0){
					addReplyTicketList(((Ticket)node.getChildeNodes().get(i)));
				}
				number++;
			}
	    }
	    
	}
	
	private void addReplyTicketList(Ticket ticket) {
		// TODO Auto-generated method stub
		  for(int i=0;i<ticket.getChildCount();i++){
			  ticketList.add((Ticket) ticket.getChildeNodes().get(i));
			  if(ticket.getChildeNodes().get(i).getChildCount()>0){
					addReplyTicketList((Ticket) ticket.getChildeNodes().get(i));
			  }
		  }
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view.equals(writeBtn)){
		Intent intent = new Intent(this, TicketWriteActivity.class);
		intent.putExtra("class", classinfo);
		startActivityForResult(intent, 01);
		}
		else if(resetBtn.equals(view)){
			updateList();
			ticketAdapter.notifyDataSetChanged();
		}
		
		//ticketAdapter.notifyDataSetChanged();
		
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		
		node.setNow(ticketList.get(position));
		Intent intent = new Intent(this, DetailTicketActivity.class);
		intent.putExtra("class", classinfo);
		startActivityForResult(intent, 01);
	}
	
}
