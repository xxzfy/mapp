package com.zhao.mapp.adapter;

import java.util.List;

import com.zhao.mapp.R;
import com.zhao.mapp.model.FileTypeModel;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyFileBrowserAdapter extends BaseAdapter {
	private List<FileTypeModel> list;
	private Activity mActivity;
	private int selectItem=-1;
	public MyFileBrowserAdapter(Activity activity) {
		mActivity=activity;
	}
	@Override
	public int getCount() {
		if(list!=null) return list.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(list!=null&&list.size()>position) return list.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FileTypeModel ftm=list.get(position);
		convertView=mActivity.getLayoutInflater().inflate(R.layout.item_file_brower_list, null);
		ImageView iv=(ImageView) convertView.findViewById(R.id.iv_item_file_browser_img);
		TextView filename= (TextView) convertView.findViewById(R.id.tv_item_file_browser_filename);
		TextView filepath= (TextView) convertView.findViewById(R.id.tv_item_file_browser_filepath);
		if(ftm.getType()==1){
			iv.setImageResource(R.drawable.ic_menu_archive);
		}else{
			iv.setImageResource(R.drawable.ic_menu_copy_holo_light);
		}
		filename.setText(ftm.getFilename());
		filepath.setText(ftm.getFilepath());
		
		if(position==selectItem){//选中的
			convertView.setBackgroundResource(R.drawable.bg_blue);
		}else{
			convertView.setBackgroundResource(R.drawable.bg_item_white_blue);
		}
		
		return convertView;
	}

	public void setList(List<FileTypeModel> list) {
		this.list = list;
	}
	public void setSelectItem(int selectItem) {
		this.selectItem = selectItem;
	}
	
}
