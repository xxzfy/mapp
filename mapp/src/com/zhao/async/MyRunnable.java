package com.zhao.async;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * 
 * @author zhyp
 *
 */
public class MyRunnable implements Runnable {
	private static String TAG="MyRunnable";
	private String mUrladdr;
	private String mAliveflag="close";
	private HashMap<String, String> mParams;
	private Handler mHandler;
	private int mWhat;
	/**
	 * 构造方法
	 * @param urladdr 网址
	 * @param aliveflag 是否保持连接，默认不保持
	 * @param params
	 * @param handler
	 * @param what
	 */
	public MyRunnable(String urladdr,String aliveflag,HashMap<String, String> params,Handler handler,int what) {
		this.mUrladdr=urladdr;
		if(aliveflag!=null){
			this.mAliveflag=aliveflag;
		}
		this.mParams=params;
		this.mHandler=handler;
		this.mWhat=what;
	}
	@Override
	public void run() {
		Looper.prepare();
		//打开遮罩层
		mHandler.sendEmptyMessage(10);
		HttpURLConnection conn=null;
		try {
			URL url=new URL(mUrladdr);
			conn=(HttpURLConnection) url.openConnection();
			//禁止保持连接
			conn.setRequestProperty("connection",mAliveflag);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setUseCaches(false);
			//设定传送的内容类型是可序列化的java对象
			//conn.setRequestProperty("Content-type", "application/x-java-serialized-object");
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			conn.setRequestMethod("POST");
			OutputStream os = conn.getOutputStream();
			StringBuffer parambuff=new StringBuffer();
			if (mParams!=null&&mParams.size()>0) {
				for (String key : mParams.keySet()) {
					parambuff.append("&").append(key).append("=").append(mParams.get(key));
				} 
			}
			byte[] sendData = parambuff.substring(1).getBytes("UTF-8");
			os.write(sendData);// 将post数据发出去
			os.flush();
			conn.connect();
			int len=0;
			byte[] buff=new byte[1024];
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			InputStream is=conn.getInputStream();
			if (is!=null) {
				while ((len = is.read(buff)) > -1) {
					bos.write(buff, 0, len);
				}
				String text = bos.toString();
				Log.i(TAG, text);
				Message msg = mHandler.obtainMessage(mWhat);
				msg.obj = text;
				mHandler.sendMessage(msg);
			}
		} catch (Exception e) {
			Log.i(TAG, "网络连接失败，请确认是否已脸上Intent网");
			e.printStackTrace();
		}finally{
			//关闭连接
			conn.disconnect();
		}
		Looper.loop();
	}
}