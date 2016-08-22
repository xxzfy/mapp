package com.zhao.mapp;

import java.io.IOException;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhao.mapp.http.OkHttpClientManager;
import com.zhao.mapp.tools.MApplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
	private TextView tv_msg;
	private ImageView iv_test;
	private Button btn_showpic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_msg=(TextView) this.findViewById(R.id.tv_msg);
		iv_test=(ImageView) this.findViewById(R.id.iv_test);
		btn_showpic=(Button) this.findViewById(R.id.btn_showpic);
;		btn_showpic.setOnClickListener(this);
	}
	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View arg0) {
		tv_msg.setText("加载图片!!");
		String url="http://192.168.0.103:9081/tsserver/showysjllist";
		OkHttpClientManager.asyncGet(url, new Callback() {
			@Override
			public void onResponse(Response response) throws IOException {
				String msg =response.body().string();
				MApplication.mHandler.showMsg(msg, 0);
			}
			
			@Override
			public void onFailure(Request arg0, IOException arg1) {
				MApplication.mHandler.showMsg("网络连接失败！", 0);
				
			}
		});
		
		
	}
}
