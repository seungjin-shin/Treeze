package com.example.androidmind.adapter;

import java.util.ArrayList;


import com.example.adroidmind.data.MindNode;
import com.example.adroidmind.data.Ticket;
import com.example.androidmind.R;



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
			view = mInflater.inflate(R.layout.ticketitem, null);
		} else {

			view = v;
		}

		// 자료를 받는다.
		final Ticket ticket = this.getItem(position);

		if (ticket != null) {
			// 화면 출력
			TextView ticketNo = (TextView) view.findViewById(R.id.ticketNo);
			TextView ticketTitle = (TextView) view
					.findViewById(R.id.ticketTitle);
			TextView ticketWriter = (TextView)view.findViewById(R.id.ticketWriter);
////			ImageView onlineBTN = (ImageView) view
////					.findViewById(R.id.onlineImg);
////			ImageView starBTN = (ImageView) view.findViewById(R.id.starImg);
//			// 텍스트뷰1에 getLabel()을 출력 즉 첫번째 인수값
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
//			if (courses.isOnline()) {
//				onlineBTN.setBackgroundResource(R.drawable.nodebg);
//				starBTN.setBackgroundResource(R.drawable.nodebg);
//			}
//
//			else {
//				onlineBTN.setBackgroundResource(R.drawable.nodebg);
//				starBTN.setBackgroundResource(R.drawable.nodebg);
//			}

			// 이미지뷰에 뿌려질 해당 이미지값을 연결 즉 세번째 인수값

		}

		return view;

	}

}
