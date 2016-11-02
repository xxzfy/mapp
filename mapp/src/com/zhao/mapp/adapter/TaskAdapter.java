package com.zhao.mapp.adapter;

import java.util.List;

import com.zhao.mapp.FlowingActivity;
import com.zhao.mapp.R;
import com.zhao.mapp.TaskListActivity;
import com.zhao.mapp.model.TaskModel;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class TaskAdapter extends BaseAdapter {
	private List<TaskModel> list;
	private Activity mActivity;
	private boolean[] mShowflag=new boolean[]{true,true,true,true,true,true,true,true,true,true};
	public TaskAdapter(Activity activity) {
		mActivity=activity;
	}
	@Override
	public int getCount() {
		if(list!=null) {
			return list.size();
		}
		return 0;
	}

	@Override
	public TaskModel getItem(int position) {
		if(list!=null&&list.size()>position){
			return list.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		if(list!=null&&list.size()>position&&list.get(position).getAnnalid()!=null&&!"".equals(list.get(position).getAnnalid())){
			return Long.valueOf(list.get(position).getAnnalid());
		}
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TaskModel model=list.get(position);
		convertView=mActivity.getLayoutInflater().inflate(R.layout.item_task_list, null);
		CheckBox cb=(CheckBox) convertView.findViewById(R.id.tv_task_list_item0);
		TextView[] tv=new TextView[11];
		tv[1]=(TextView) convertView.findViewById(R.id.tv_task_list_item1);
		tv[1].setText(model.getSe_accept_code());
		tv[2]=(TextView) convertView.findViewById(R.id.tv_task_list_item2);
		tv[2].setText(model.getUnit_name());
		tv[3]=(TextView) convertView.findViewById(R.id.tv_task_list_item3);
		tv[3].setText(model.getSe_type());
		tv[4]=(TextView) convertView.findViewById(R.id.tv_task_list_item4);
		tv[4].setText(model.getApply_type());
		tv[5]=(TextView) convertView.findViewById(R.id.tv_task_list_item5);
		tv[5].setText(model.getSe_code());
		tv[6]=(TextView) convertView.findViewById(R.id.tv_task_list_item6);
		tv[6].setText(model.getUse_unit());
		tv[7]=(TextView) convertView.findViewById(R.id.tv_task_list_item7);
		tv[7].setText(model.getReport_name());
		tv[8]=(TextView) convertView.findViewById(R.id.tv_task_list_item8);
		tv[8].setText(model.getTest_book_num());
		tv[9]=(TextView) convertView.findViewById(R.id.tv_task_list_item9);
		tv[9].setText(model.getTest_leader());
		tv[10]=(TextView) convertView.findViewById(R.id.tv_task_list_item10);
		tv[10].setText(model.getSe_flag());
		
		if(mShowflag[0]){
			cb.setVisibility(View.VISIBLE);
		}else{
			cb.setVisibility(View.GONE);
		}
		int u=position%2;
		
		for(int i=1;i<tv.length;i++){
			if(mShowflag[i]){
				tv[i].setVisibility(View.VISIBLE);
				if(u==1){
					tv[i].setBackgroundResource(R.drawable.bg_item_white);
				}else{
					tv[i].setBackgroundResource(R.drawable.bg_item_blue);
				}
			}else{
				tv[i].setVisibility(View.GONE);
			}
		}
		
		convertView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(mActivity, FlowingActivity.class);
				intent.putExtra("annalid", "123123");
				mActivity.startActivity(intent);
				mActivity.finish();
				
			}
		});
		
		return convertView;
	}
	public void setList(List<TaskModel> list) {
		this.list = list;
	}
	public void setmShowflag(boolean[] mShowflag) {
		this.mShowflag = mShowflag;
	}
	
}
