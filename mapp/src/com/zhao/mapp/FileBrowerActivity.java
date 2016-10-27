package com.zhao.mapp;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import com.zhao.mapp.adapter.MyFileBrowserAdapter;
import com.zhao.mapp.ioc.view.ViewInjectUtils;
import com.zhao.mapp.ioc.view.annotation.ContentView;
import com.zhao.mapp.ioc.view.annotation.ViewInject;
import com.zhao.mapp.model.FileTypeModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

@ContentView(value = R.layout.activity_file_brower)
public class FileBrowerActivity extends BaseActivity {
	@ViewInject(value = R.id.ll_file_brower_path)
	private LinearLayout ll_file_brower_path;
	@ViewInject(value = R.id.lv_file_brower_dirslist)
	private ListView lv_file_brower_dirslist;
	@ViewInject(value = R.id.ll_file_brower_option)
	private LinearLayout ll_file_brower_option;

	@ViewInject(value = R.id.tv_file_brower_path)
	private TextView tv_file_brower_path;

	@ViewInject(value = R.id.btn_file_brower_option_back)
	private ImageButton btn_file_brower_option_back;
	@ViewInject(value = R.id.btn_file_brower_option_cancel)
	private Button btn_file_brower_option_cancel;
	@ViewInject(value = R.id.btn_file_brower_option_open)
	private Button btn_file_brower_option_open;
	@ViewInject(value = R.id.btn_file_brower_option_ok)
	private Button btn_file_brower_option_ok;
	
	private String path="";
	private String showpath="";
	private List<FileTypeModel> filenamelist=new ArrayList<FileTypeModel>();
	private MyFileBrowserAdapter mfba;
	private String curfilename="";
	private int curtype=0;//0文件1文件夹
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewInjectUtils.inject(this);
		initEvent();
		init();
	}

	private void init() {
		 mfba=new MyFileBrowserAdapter(this);
		path=Environment.getExternalStorageDirectory().getAbsolutePath();
		showpath="root";
		//刷新列表
		refdiclist();
	}

	/**
	 * @param rootdir
	 */
	public void refdiclist() {
		//变量初始化
		curfilename="";
		curtype=0;
		mfba.setSelectItem(-1);
		
		File rootdir=new File(path);
		File[] dirs= rootdir.listFiles(new MyDirFilter());
		File[] files= rootdir.listFiles(new MyFileFilter());
		filenamelist.clear();
		//循环文件夹
		for(int i=0;i<dirs.length;i++){
			FileTypeModel ftm=new FileTypeModel(1,dirs[i].getName(),dirs[i].getAbsolutePath());
			filenamelist.add(ftm);
		}
		//循环文件
		for(int i=0;i<files.length;i++){
			FileTypeModel ftm=new FileTypeModel(0,files[i].getName(),files[i].getAbsolutePath());
			filenamelist.add(ftm);
		}
		mfba.setList(filenamelist);
		lv_file_brower_dirslist.setAdapter(mfba);
		mfba.notifyDataSetChanged();
	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_file_brower_option_back:
			if(Environment.getExternalStorageDirectory().getAbsolutePath().equals(path)){
				showToastMsgLong("已是顶层！");
				return;
			}
			path=path.substring(0,path.lastIndexOf("/"));
			showpath=showpath.substring(0,showpath.lastIndexOf("/"));
			tv_file_brower_path.setText(showpath);
			//刷新列表
			refdiclist();
			
			break;
		case R.id.btn_file_brower_option_cancel:
			curfilename="";
			curtype=0;
			setResult(Activity.RESULT_CANCELED);
			FileBrowerActivity.this.finish();
			break;
		case R.id.btn_file_brower_option_open:
			if(curtype==0){
				showToastMsgShort("选择的不是文件加不能打开！");
				return ;
			}
			path=path+"/"+curfilename;
			showpath=showpath+"/"+curfilename;
			tv_file_brower_path.setText(showpath);
			//刷新列表
			refdiclist();
			break;
		case R.id.btn_file_brower_option_ok:
			if(curtype==1&&curfilename!=null&&!"".equals(curfilename)){
				path=path+"/"+curfilename;
			}
			Intent intent = new Intent();
			intent.putExtra("path", path);
			setResult(Activity.RESULT_OK, intent);
			FileBrowerActivity.this.finish();
			break;

		default:
			break;
		}

	}

	@Override
	protected void initEvent() {
		btn_file_brower_option_back.setOnClickListener(this);
		btn_file_brower_option_cancel.setOnClickListener(this);
		btn_file_brower_option_open.setOnClickListener(this);
		btn_file_brower_option_ok.setOnClickListener(this);
		lv_file_brower_dirslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				FileTypeModel ftm= filenamelist.get(position);
				curfilename=ftm.getFilename();
				curtype=ftm.getType();
				mfba.setSelectItem(position);
				mfba.notifyDataSetInvalidated();
			}
		});

	}
	class MyDirNameFilter implements FilenameFilter{

		@Override
		public boolean accept(File dir, String filename) {
			if(dir.isDirectory()){
				return true;
			}
			return false;
		}
		
	}
	class MyFileNameFilter implements FilenameFilter{

		@Override
		public boolean accept(File dir, String filename) {
			if(dir.isFile()){
				return true;
			}
			return false;
		}
		
	}
	class MyDirFilter implements FileFilter{
		@Override
		public boolean accept(File pathname) {
			if(pathname.isDirectory()) {
				return true;
			}
			return false;
		}
	}
	class MyFileFilter implements FileFilter{
		@Override
		public boolean accept(File pathname) {
			if(pathname.isFile()){
				return true;
			}
			return false;
		}
	}
	@Override
	protected Handler setMHandler() {
		// TODO Auto-generated method stub
		return null;
	}
}
