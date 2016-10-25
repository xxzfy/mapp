package com.zhao.mapp;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhao.mapp.ioc.view.ViewInjectUtils;
import com.zhao.mapp.ioc.view.annotation.ContentView;
import com.zhao.mapp.ioc.view.annotation.ViewInject;
import com.zhao.mapp.tools.ActivityManagerList;
@ContentView(value=R.layout.activity_main)
public class MainActivity extends BaseActivity{
	//用户信息
	@ViewInject(value=R.id.iv_user_info_photo)
	private ImageView iv_user_info_photo;
	@ViewInject(value=R.id.tv_user_info_name)
	private TextView tv_user_info_name;
	@ViewInject(value=R.id.tv_user_info_msg)
	private TextView tv_user_info_msg;
	//主面板
	@ViewInject(value=R.id.lv_main_plan)
	private GridLayout lv_main_plan;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewInjectUtils.inject(this);
		initEvent();
	}
	@Override
	protected void initEvent() {
		setHead("测试平台");
	}
	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View view) {
		if(view.getId()==R.id.btn_common_head_back){
			Intent intent=new Intent(MainActivity.this, LoginActivity.class);
			startActivity(intent);
			MainActivity.this.finish();
			return;
		}
		if(view instanceof ImageView){
			String activity_name=view.getContentDescription()+"";
			if(activity_name!=null&&!"null".equals(activity_name)&&!"".equals(activity_name)){
				try {
					Class<? extends Activity> activity=(Class<? extends Activity>) Class.forName(activity_name);
					Intent intent=new Intent(MainActivity.this, activity);
					startActivity(intent);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	class GridLayoutAdapter extends BaseAdapter{
		private List<ItemEntery> list=null;
		@Override
		public int getCount() {
			if(list==null)
			return 0;
			else return list.size();
		}

		@Override
		public ItemEntery getItem(int position) {
			if(list==null||list.size()<position+1)
			return null;
			else return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			if(list==null||list.size()<position+1)
			return 0;
			else return list.get(position).getId();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ItemEntery item=list.get(position);
			convertView=getLayoutInflater().inflate(R.layout.main_grid_item, null);
			ImageView iv=(ImageView) convertView.findViewById(R.id.iv_main_grid_item_img);
			TextView tv=(TextView) convertView.findViewById(R.id.tv_main_grid_item_text);
			Drawable dr=Drawable.createFromPath(item.getImgpath());
			iv.setBackground(dr);
			tv.setText(item.getTitle());
			iv.setContentDescription(item.getActivity_name());
			iv.setOnClickListener(MainActivity.this);
			return convertView;
		}
		/**
		 * 设置值
		 * @param list
		 */
		public void setList(List<ItemEntery> list) {
			this.list = list;
		}
		
	}
	/**
	 * 
	 * @author zhyp
	 *
	 */
	class ItemEntery{
		private Long id;
		private String orderid;
		private String imgpath;
		private String title;
		private String activity_name;
		public ItemEntery() {
			// TODO Auto-generated constructor stub
		}
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getOrderid() {
			return orderid;
		}

		public void setOrderid(String orderid) {
			this.orderid = orderid;
		}

		public String getImgpath() {
			return imgpath;
		}
		public void setImgpath(String imgpath) {
			this.imgpath = imgpath;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getActivity_name() {
			return activity_name;
		}
		public void setActivity_name(String activity_name) {
			this.activity_name = activity_name;
		}
		
	}
	/**
	 * 主界面返回键按下事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("您确定要退出平台吗？").setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					ActivityManagerList.finishProgram();
					
				}
			}).create().show();
		}
		return true;
	}
	
	
}
