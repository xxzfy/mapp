package com.zhao.mapp.tools;

import com.zhao.mapp.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyDialog extends Dialog implements View.OnClickListener {
	private Context context;
	private AnimationDrawable mAinmation;
	private MyDialogListener listener;

	public interface MyDialogListener {
		public void onClick(View v);
	}

	public MyDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	public MyDialog(Context context, int theme, MyDialogListener listener) {
		super(context, theme);
		this.context = context;
		this.listener = listener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv_dialog_cancel = (TextView) findViewById(R.id.tv_dialog_cancel);
		TextView tv_dialog_ok = (TextView) findViewById(R.id.tv_dialog_ok);
		if (tv_dialog_cancel != null && tv_dialog_ok != null) {
			tv_dialog_cancel.setOnClickListener(this);
			tv_dialog_ok.setOnClickListener(this);
		}
		ImageView iv = (ImageView) findViewById(R.id.iv_dialog_img);
		if (iv != null) {
			iv.setBackgroundResource(R.anim.my_ship);
			mAinmation = (AnimationDrawable) iv.getBackground();
			iv.post(new Runnable() {
				@Override
				public void run() {
					mAinmation.start();
				}
			});
		}
	}

	@Override
	protected void onStart() {
		TextView tv_dialog_cancel = (TextView) findViewById(R.id.tv_dialog_cancel);
		TextView tv_dialog_ok = (TextView) findViewById(R.id.tv_dialog_ok);
		ImageView iv_close=(ImageView) findViewById(R.id.iv_dialog_close);
		if (tv_dialog_cancel != null) {
			tv_dialog_cancel.setOnClickListener(this);
		}
		if (tv_dialog_ok != null) {
			tv_dialog_ok.setOnClickListener(this);
		}
		if(iv_close!=null){
			iv_close.setOnClickListener(this);
		}
		ImageView iv = (ImageView) findViewById(R.id.iv_dialog_img);
		if (iv != null) {
			iv.setBackgroundResource(R.anim.my_ship);
			mAinmation = (AnimationDrawable) iv.getBackground();
			iv.post(new Runnable() {
				@Override
				public void run() {
					mAinmation.start();
				}
			});
		}
		super.onStart();
	}

	@Override
	public void onClick(View v) {
		listener.onClick(v);
	}

}
