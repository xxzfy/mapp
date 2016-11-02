package com.zhao.mapp;

import java.io.IOException;

import com.zhao.mapp.http.OkHttpClientManager;
import com.zhao.mapp.tools.ActivityManagerList;
import com.zhao.mapp.tools.MyDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseActivity extends Activity implements View.OnClickListener{
	protected String TAG="BaseActivity";
	protected LruCache<String, Bitmap> mMemoryCache;
	protected ProgressBar pb;
	protected MyDialog mDialog;
	protected Handler mHandler=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//添加到全局ActivityList变量中
		ActivityManagerList.add(this);
		int maxMemory=(int) (Runtime.getRuntime().maxMemory()/1024);
		int cacheSize=maxMemory/8;
		mMemoryCache=new LruCache<String, Bitmap>(cacheSize){

			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getByteCount()/1024;
			}
			
		};
		pb=new ProgressBar(this);
		mHandler=setMHandler();
	}
	/**
	 * 显示自定义选择对话框
	 * @param contextid R.layout.dialog_my_choise
	 * @param title
	 * @param msg
	 * @param what
	 */
	public void showMyChoiseDialog(int contextid,String title,String msg,int what) {
		final int mWhat=what;
		if(mDialog==null){
			mDialog=new MyDialog(this, R.style.MyDialog,new MyDialog.MyDialogListener() {
				
				@Override
				public void onClick(View v) {
					if(v.getId()==R.id.tv_dialog_ok){
						mDialog.dismiss();
						//如果存在handler则向handler发送消息
						if(mHandler!=null){
							mHandler.sendEmptyMessage(mWhat);
						}
					}
					
					mDialog.dismiss();
				}
			});
		}
		mDialog.setContentView(contextid);
		TextView tv_dialog_my_title=(TextView) mDialog.findViewById(R.id.tv_dialog_my_title);
		TextView tv_dialog_my_msg=(TextView) mDialog.findViewById(R.id.tv_dialog_my_msg);
		if(title!=null&&!"".equals(title)){
			tv_dialog_my_title.setText(title);
		}
		if(msg!=null&&!"".equals(msg)){
			tv_dialog_my_msg.setText(msg);
		}
		mDialog.show();
	}
	
	public void closeMyChoiseDialog(){
		if(mDialog==null&&mDialog.isShowing()){
			mDialog.dismiss();
		}
	}
	/**
	 * 绑定事件
	 */
	protected abstract void initEvent();
	/**
	 * 设置对话框处理类
	 * @return
	 */
	protected abstract Handler setMHandler();
	
	protected void displayNetImage(ImageView iv,String url){
		Bitmap bmap=mMemoryCache.get(url);
		if(bmap==null){
			bmap=new AsyncDownLoadImage().doInBackground(url);
		}else{
			if(iv!=null){
				iv.setImageBitmap(bmap);
			}
		}
	}
	protected void displayLocalImage(ImageView iv,String url){
		
	}
	/**
	 * 把图片出入缓存
	 * @param key
	 * @param bitmap
	 */
	public void addImageToMemCache(String key,Bitmap bitmap){
		if(mMemoryCache.get(key)!=null){
			return;
		}
		mMemoryCache.put(key, bitmap);
	}
	/**
	 * 从缓存中取出图片
	 * @param key
	 * @return
	 */
	public Bitmap getImageFromMemCache(String key){
		return mMemoryCache.get(key);
	}
	/**
	 * 异步下载图片
	 * @author zhyp
	 *
	 */
	class AsyncDownLoadImage extends AsyncTask<Object, Void, Bitmap>{
		private String imageUrl;
		private ImageView iv;
		@Override
		protected Bitmap doInBackground(Object... params) {
			imageUrl=(String) params[0];
			iv=(ImageView) params[1];
			Bitmap bitmap=downloadImage(imageUrl);
			if(bitmap!=null){
				addImageToMemCache(imageUrl, bitmap);
			}
			return bitmap;
		}
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);
			if(iv!=null&&bitmap!=null){
				iv.setImageBitmap(bitmap);
			}
		}
		/**
		 * 后台下载图片
		 * @param object
		 * @return
		 * @throws IOException 
		 */
		private Bitmap downloadImage(String url){
			Bitmap bm=null;
			try {
				bm = OkHttpClientManager.syncDownLoadImage(url);
			} catch (IOException e) {
				MApplication.showToastShort("图片加载失败");
				Log.e(TAG, e.getMessage(), e);
			}
			return bm;
		}
	}
	/**
	 * 显示提示信息
	 * @param msg
	 */
	protected void showToastMsgShort(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	/**
	 * 显示提示信息
	 * @param msg
	 */
	protected void showToastMsgLong(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	/**
	 * 按下事件
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent=new Intent(this, MainActivity.class);
			startActivity(intent);
			this.finish();
		}
		return true;
	}
	/**
	 * 设置页面头
	 */
	protected void setHead(String title) {
		TextView tv_title=(TextView) this.findViewById(R.id.tv_common_head_title);
		if(tv_title!=null){
			tv_title.setText(title);
		}
		Button btn_back=(Button) this.findViewById(R.id.btn_common_head_back);
		if(btn_back!=null){
			btn_back.setOnClickListener(this);
		}
	}
	
}
