package com.zhao.mapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
	private TextView tv_msg;
	private ImageView iv_test;
	private Button btn_showpic;
	private String[] imageUrls;
	private int idex=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		tv_msg=(TextView) this.findViewById(R.id.tv_msg);
//		iv_test=(ImageView) this.findViewById(R.id.iv_test);
//		btn_showpic=(Button) this.findViewById(R.id.btn_showpic);
//		btn_showpic.setOnClickListener(this);
//		imageUrls=new String[]{
//			"http://img.mp.itc.cn/upload/20160829/1ff02342476a43f4a174edd9eb06fafe.jpg",	
//			"http://img.mp.itc.cn/upload/20160829/a6d06b5438f14a6183d9ed6fe4ec7a50_th.jpg",
//			"http://imgnews.gmw.cn/attachement/jpg/site2/20160830/7164863972643588751.jpg",
//			"http://imgnews.gmw.cn/attachement/jpg/site2/20160830/4422434320192586386.jpg"
//		};
	}
	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
//		case R.id.btn_showpic:
//			if(idex<imageUrls.length){
//				displayNetImage(iv_test, imageUrls[idex]);
//			}else{
//				idex=0;
//				displayNetImage(iv_test, imageUrls[idex]);
//			}
//			idex++;
//			break;

		default:
			break;
		}
	}
}
