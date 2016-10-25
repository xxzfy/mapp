package com.zhao.mapp.adapter;

import java.util.List;

import com.zhao.mapp.R;
import com.zhao.mapp.model.DetailModel;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailAdapter extends BaseAdapter {
	private Activity mActivity;
	List<DetailModel> list=null;
	public DetailAdapter(Activity context) {
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
		DetailModel model=list.get(position);
		LinearLayout ll= (LinearLayout) mActivity.getLayoutInflater().inflate(R.layout.item_flow_context_detail_listitem, null);
		TextView tv_item_flow_context_detail_listitem_key=(TextView) ll.findViewById(R.id.tv_item_flow_context_detail_listitem_key);
		TextView tv_item_flow_context_detail_listitem_value=(TextView) ll.findViewById(R.id.tv_item_flow_context_detail_listitem_value);
		tv_item_flow_context_detail_listitem_key.setText(model.getKey());
		tv_item_flow_context_detail_listitem_value.setText(model.getValue());
		
		//设置颜色
		if(position%2==0){
			tv_item_flow_context_detail_listitem_key.setBackgroundResource(R.color.TRANSPARENT_WHITE);
			tv_item_flow_context_detail_listitem_value.setBackgroundResource(R.color.TRANSPARENT_WHITE);
		}else{
			tv_item_flow_context_detail_listitem_key.setBackgroundResource(R.color.WHITE);
			tv_item_flow_context_detail_listitem_value.setBackgroundResource(R.color.WHITE);
		}
		
		return ll;
	}

	public void setList(List<DetailModel> list) {
		this.list = list;
	}

}
