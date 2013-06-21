package com.mindmap.androidmind.adapter;

import java.util.ArrayList;


import com.example.androidmind.R;
import com.mindmap.adroidmind.data.MindNode;
import com.mindmap.adroidmind.data.Ticket;



import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

public class TicketAdapter extends ArrayAdapter<Ticket> {
	private LayoutInflater mInflater;

	public TicketAdapter(Context context, int layoutResource,
			ArrayList<Ticket> object) {
		super(context, layoutResource, object);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View view = null;

		if (v == null) {
			view = mInflater.inflate(R.layout.ticketitem, null);
		} else {

			view = v;
		}

		final Ticket ticket = this.getItem(position);

		if (ticket != null) {
			TextView ticketNo = (TextView) view.findViewById(R.id.ticketNo);
			TextView ticketTitle = (TextView) view
					.findViewById(R.id.ticketTitle);
			TextView ticketWriter = (TextView)view.findViewById(R.id.ticketWriter);
			if(ticket.getTicketNumber()>0){
           ticketNo.setText(""+ticket.getTicketNumber());
           ticketTitle.setText(ticket.getNodeStr());
           
			}
			else{
				String depthSpace = new String("");
				MindNode getdepthTemp = ticket.getParentNode();
				while(getdepthTemp instanceof Ticket){
					depthSpace = depthSpace+"    ";
					getdepthTemp = getdepthTemp.getParentNode();
				}
				ticketTitle.setText(depthSpace+ticket.getNodeStr());
			}
			ticketWriter.setText(ticket.getuserName());
           ticketWriter.setText(ticket.getuserName());

		}

		return view;

	}

}
