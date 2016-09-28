package com.zhao.mapp;

import com.zhao.mapp.ioc.view.ViewInjectUtils;
import com.zhao.mapp.ioc.view.annotation.ContentView;
import com.zhao.mapp.ioc.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
@ContentView(value=R.layout.activity_login)
public class LoginActivity extends BaseActivity {
	@ViewInject(value=R.id.tv_login_title)
	private TextView tv_login_title;
	@ViewInject(value=R.id.tv_login_tofastlogin)
	private TextView tv_login_tofastlogin;
	@ViewInject(value=R.id.et_login_name)
	private EditText et_login_name;
	@ViewInject(value=R.id.et_login_pass)
	private EditText et_login_pass;
	@ViewInject(value=R.id.btn_login_sub)
	private Button btn_login_sub;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewInjectUtils.inject(this);
		initEvent();
		
	}
	
	@Override
	protected void initEvent() {
		btn_login_sub.setOnClickListener(this);
		tv_login_tofastlogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_login_sub){
			String loginname=et_login_name.getText().toString();
			String password=et_login_pass.getText().toString();
			//去除登录名的空格开头结尾
			if(loginname.trim().length()==0){
				showToastMsgShort("登录名不能为空!");
				return;
			}
			//密码允许使用空格
			if(password.length()==0){
				showToastMsgShort("密码不能为空!");
				return;
			}
			//demo，如正式使用则需要检验登录名和密码的验证
			if("admin".equals(loginname.trim())&&"123".equals(password)){
				toMain();
			}else{
				showToastMsgShort("登录名或密码错误！");
			}
		}else if(v.getId()==R.id.tv_login_tofastlogin){
			//跳转到快速登录界面
			Intent intent=new Intent(LoginActivity.this,FastLoginActivity.class);
			startActivityForResult(intent, 0);
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 0:
			switch (resultCode) {
			case RESULT_OK:
				//跳转到主界面
				toMain();
				break;
			default:
				break;
			}
			break;

		default:
			break;
		}
		
	}

	/**
	 * 跳转到主界面
	 */
	public void toMain() {
		Intent nextintent=new Intent(LoginActivity.this, MainActivity.class);
		startActivity(nextintent);
		LoginActivity.this.finish();
	}


	
	
}
