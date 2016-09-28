package com.zhao.mapp;

import java.io.IOException;

import com.zhao.mapp.http.OkHttpClientManager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public abstract class BaseActivity extends Activity implements View.OnClickListener{
	protected String TAG="BaseActivity";
	protected LruCache<String, Bitmap> mMemoryCache;
	protected ProgressBar pb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int maxMemory=(int) (Runtime.getRuntime().maxMemory()/1024);
		int cacheSize=maxMemory/8;
		mMemoryCache=new LruCache<String, Bitmap>(cacheSize){

			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getByteCount()/1024;
			}
			
		};
		pb=new ProgressBar(this);
	}
	/**
	 * 绑定事件
	 */
	protected abstract void initEvent();
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
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}
	/**
	 * 显示提示信息
	 * @param msg
	 */
	protected void showToastMsgLong(String msg){
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
}
