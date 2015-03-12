package com.qf.teach.day18.thread.handler.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qf.teach.day18.thread.handler.R;
import com.qf.teach.day18.thread.handler.entity.Tuan;

public class TuanAdapter extends BaseAdapter {
	private Context context;
	private List<Tuan> tuanList;
	private ViewHodler viewHodler;

	public TuanAdapter(Context context, List<Tuan> tuanList) {
		this.context = context;
		this.tuanList = tuanList;
	}

	@Override
	public int getCount() {
		return tuanList.size();
	}

	@Override
	public Object getItem(int position) {
		return tuanList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.list_tuan, parent, false);
			
			viewHodler = new ViewHodler();
			viewHodler.txTitle = (TextView) convertView.findViewById(R.id.tx_title);
			
			convertView.setTag(viewHodler);
		} else {
			viewHodler = (ViewHodler) convertView.getTag();
		}
		
		Tuan tuan = tuanList.get(position);
		viewHodler.txTitle.setText(tuan.getBrand_name());
		
		return convertView;
	}
	
	class ViewHodler {
		public TextView txTitle;
	}

}
