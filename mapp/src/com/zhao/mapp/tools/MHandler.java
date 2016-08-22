package com.zhao.mapp.tools;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

public class MHandler extends Handler {
	private Context mContext;
	public MHandler(Context context) {
		super(context.getMainLooper());
		this.mContext=context;
	}
	/**
	 * 显示提示信息
	 * @param text 提示内容
	 * @param time 显示时间长短 0短1长
	 */
	public void showMsg(String text,int time){
		Message msg= this.obtainMessage(0);
		msg.arg1=time;
		msg.obj=text;
		this.sendMessage(msg);
	}
	/**
	 * msg.what=0输出文本提示 msg.obj存储待输出的文本，msg.arg1确定显示的时间长短：0短1长
	 */
	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
		case 0:
			String text=(String) msg.obj;
			Toast.makeText(mContext, text, msg.arg1).show();
			break;
		default:
			break;
		}
	}
	
}
