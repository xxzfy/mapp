package com.zhao.mapp;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.zhao.mapp.drawpassword.component.LocusPassWordView;
import com.zhao.mapp.drawpassword.util.Md5Utils;
import com.zhao.mapp.drawpassword.util.SharedPreferencesHelper;
import com.zhao.mapp.ioc.view.ViewInjectUtils;
import com.zhao.mapp.ioc.view.annotation.ContentView;
import com.zhao.mapp.ioc.view.annotation.ViewInject;

@ContentView(value = R.layout.activity_fast_login)
public class FastLoginActivity extends BaseActivity{
	private static final String TAG = "FastLoginActivity";
	@ViewInject(value = R.id.tv_fast_login_title)
	private TextView tv_fast_login_title;
	@ViewInject(value = R.id.et_fast_login_name)
	private EditText et_fast_login_name;
	@ViewInject(value = R.id.et_fast_login_password)
	private EditText et_fast_login_password;
	@ViewInject(value = R.id.btn_fast_login_setfast)
	private Button btn_fast_login_setfast;
	@ViewInject(value = R.id.lpv_fast_loginpassword)
	private LocusPassWordView lpv_fast_loginpassword;
	
	private String mPassword = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewInjectUtils.inject(this);
		initEvent();
		setResult(RESULT_CANCELED);
	}

	/**
	 * 绑定事件
	 */
	@Override
	public void initEvent() {
		lpv_fast_loginpassword.setOnCompleteListener(new MyLocusCompleteListener());
		btn_fast_login_setfast.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_fast_login_setfast:
			if(lpv_fast_loginpassword.isShown()){
				lpv_fast_loginpassword.setVisibility(View.GONE);
			}else{
				lpv_fast_loginpassword.setVisibility(View.VISIBLE);
			}
			
			break;

		default:
			break;
		}
		
	}
	class MyLocusCompleteListener implements LocusPassWordView.OnCompleteListener{

		@Override
		public void onComplete(String password) {

			SharedPreferencesHelper sph = SharedPreferencesHelper.getInstance(getApplicationContext());
			String pwd = sph.getString("fastpassword", "");
			Md5Utils md5 = new Md5Utils();
			boolean passed = false;
			if (pwd.length() == 0) {
				sph.putString("fastpassword", md5.toMd5(password, ""));
				Toast.makeText(getApplicationContext(), "密码已设置", Toast.LENGTH_LONG).show();
				return;
			} else {
				String encodedPwd = md5.toMd5(password, "");
				if (encodedPwd.equals(pwd)) {
					passed = true;
				} else {
					lpv_fast_loginpassword.markError();
				}
			}

			if (passed) {
				Log.d(TAG, "密码正确!");
				Toast.makeText(getApplicationContext(), "密码验证通过", Toast.LENGTH_LONG).show();
				MApplication.isfastok=true;
				setResult(RESULT_OK);
				FastLoginActivity.this.finish();
			}else{
				Log.d(TAG, "密码错误!");
				Toast.makeText(getApplicationContext(), "密码验证失败", Toast.LENGTH_SHORT).show();
			}

		}
		
	}
	@Override
	protected Handler setMHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
