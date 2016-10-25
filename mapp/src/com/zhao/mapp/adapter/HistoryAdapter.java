package com.zhao.mapp.adapter;

import java.util.List;

import com.zhao.mapp.R;
import com.zhao.mapp.model.HistoryModel;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HistoryAdapter extends BaseAdapter {
	private Activity mActivity;
	List<HistoryModel> list=null;
	public HistoryAdapter(Activity context) {
		this.mActivity=context;
	}
	@Override
	public int getCount() {
		if(list!=null)return list.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(list!=null&&list.size()>position)return list.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		if(list!=null&&list.size()>position)return list.get(position).getId();
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HistoryModel model=list.get(position);
		LinearLayout ll= (LinearLayout) mActivity.getLayoutInflater().inflate(R.layout.item_flow_context_history_listitem, null);
		TextView tv_item_flow_context_history_index=(TextView) ll.findViewById(R.id.tv_item_flow_context_history_index);
		TextView tv_item_flow_context_history_person=(TextView) ll.findViewById(R.id.tv_item_flow_context_history_person);
		TextView tv_item_flow_context_history_opinion=(TextView) ll.findViewById(R.id.tv_item_flow_context_history_opinion);
		TextView tv_item_flow_context_history_date=(TextView) ll.findViewById(R.id.tv_item_flow_context_history_date);
		tv_item_flow_context_history_index.setText(model.getIndex());
		tv_item_flow_context_history_person.setText(model.getPerson());
		tv_item_flow_context_history_opinion.setText(model.getOpinion());
		tv_item_flow_context_history_date.setText(model.getSigndate());
		
		//设置颜色
		if(position%2==0){
			tv_item_flow_context_history_index.setBackgroundResource(R.color.TRANSPARENT_WHITE);
			tv_item_flow_context_history_person.setBackgroundResource(R.color.TRANSPARENT_WHITE);
			tv_item_flow_context_history_opinion.setBackgroundResource(R.color.TRANSPARENT_WHITE);
			tv_item_flow_context_history_date.setBackgroundResource(R.color.TRANSPARENT_WHITE);
		}else{
			tv_item_flow_context_history_index.setBackgroundResource(R.color.WHITE);
			tv_item_flow_context_history_person.setBackgroundResource(R.color.WHITE);
			tv_item_flow_context_history_opinion.setBackgroundResource(R.color.WHITE);
			tv_item_flow_context_history_date.setBackgroundResource(R.color.WHITE);
		}
		
		return ll;
	}

	public void setList(List<HistoryModel> list) {
		this.list = list;
	}

}
