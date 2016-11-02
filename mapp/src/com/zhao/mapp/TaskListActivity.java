package com.zhao.mapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhao.mapp.adapter.TaskAdapter;
import com.zhao.mapp.http.OkHttpClientManager;
import com.zhao.mapp.ioc.view.ViewInjectUtils;
import com.zhao.mapp.ioc.view.annotation.ContentView;
import com.zhao.mapp.ioc.view.annotation.ViewInject;
import com.zhao.mapp.model.TaskModel;
import com.zhao.mapp.tools.FlowRadioGroup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
@ContentView(value=R.layout.activity_task_list)
public class TaskListActivity extends BaseActivity {
	
	@ViewInject(value=R.id.fl_task_list_option)
	private FrameLayout fl_task_list_option;
	@ViewInject(value=R.id.lv_task_list_list)
	private ListView lv_task_list_list;
	@ViewInject(value=R.id.tv_task_list_tool_query)
	private TextView tv_task_list_tool_query;
	@ViewInject(value=R.id.tv_task_list_tool_show)
	private TextView tv_task_list_tool_show;
	@ViewInject(value=R.id.sv_task_list_option)
	private ScrollView sv_task_list_option;
	//标题列
	private LinkedList<View> tv_task_list_title;
	
	@ViewInject(value=R.id.tv_task_list_title0)
	private CheckBox tv_task_list_title0;
	@ViewInject(value=R.id.tv_task_list_title1)
	private TextView tv_task_list_title1;
	@ViewInject(value=R.id.tv_task_list_title2)
	private TextView tv_task_list_title2;
	@ViewInject(value=R.id.tv_task_list_title3)
	private TextView tv_task_list_title3;
	@ViewInject(value=R.id.tv_task_list_title4)
	private TextView tv_task_list_title4;
	@ViewInject(value=R.id.tv_task_list_title5)
	private TextView tv_task_list_title5;
	@ViewInject(value=R.id.tv_task_list_title6)
	private TextView tv_task_list_title6;
	@ViewInject(value=R.id.tv_task_list_title7)
	private TextView tv_task_list_title7;
	@ViewInject(value=R.id.tv_task_list_title8)
	private TextView tv_task_list_title8;
	@ViewInject(value=R.id.tv_task_list_title9)
	private TextView tv_task_list_title9;
	@ViewInject(value=R.id.tv_task_list_title10)
	private TextView tv_task_list_title10;
	
	private boolean fl_query_flag=false;
	private boolean fl_show_flag=false;
	
	//受理编号
	private EditText et_item_task_query_option_accept_code;
	//申报单位
	private EditText et_item_task_query_option_request_unit;
	//设备注册代码
	private EditText et_item_task_query_option_se_code;
	//使用单位
	private EditText et_item_task_query_option_use_unit;
	//报告编号
	private EditText et_item_task_query_option_test_book_num;
	//检验组长
	private EditText et_item_task_query_option_leader;
	//当前环节
	private FlowRadioGroup rg_item_task_query_option_current_step;
	//设备类型
	private FlowRadioGroup rg_item_task_query_option_se_type;
	//检验类型
	private FlowRadioGroup rg_item_task_query_option_examin_type;
	private RadioGroup rg_item_task_list_show_option_title0=null;
	private RadioGroup rg_item_task_list_show_option_title1=null;
	private RadioGroup rg_item_task_list_show_option_title2=null;
	private RadioGroup rg_item_task_list_show_option_title3=null;
	private RadioGroup rg_item_task_list_show_option_title4=null;
	private RadioGroup rg_item_task_list_show_option_title5=null;
	private RadioGroup rg_item_task_list_show_option_title6=null;
	private RadioGroup rg_item_task_list_show_option_title7=null;
	private RadioGroup rg_item_task_list_show_option_title8=null;
	private RadioGroup rg_item_task_list_show_option_title9=null;
	private RadioGroup rg_item_task_list_show_option_title10=null;
	
	private LinkedList<RadioGroup> optionlist=new LinkedList<RadioGroup>();
	/** 各列是否显示*/
	private boolean[] showflag=new boolean[]{true,true,true,true,true,true,true,true,true,true,true};
	//查询条件
	private String se_accept_code;//设备受理编号
	private String unit_name;//申报单位
	private String se_code;//设备注册代码
	private String use_unit;//使用单位
	private String test_book_num;//报告编号
	private String test_leader;//检验组长姓名
	private String current_step;//当前环节
	private String se_type;//设备类别
	private String apply_type;//检验类别
	
	private TaskAdapter ta;
	private Animation anim_in;
	private Animation anim_out;
	
	private List<TaskModel> tasklist=null;
	
	private Handler mhandler=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewInjectUtils.inject(this);
		init();
		//绑定事件
		initEvent();
		ta=new TaskAdapter(this);
		mhandler=new Handler(getMainLooper()){
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					toShow();
					break;

				default:
					break;
				}
			}
			
		};
		
	}

	private void init() {
		tv_task_list_title=new LinkedList<View>();
		tv_task_list_title.add(tv_task_list_title0);
		tv_task_list_title.add(tv_task_list_title1);
		tv_task_list_title.add(tv_task_list_title2);
		tv_task_list_title.add(tv_task_list_title3);
		tv_task_list_title.add(tv_task_list_title4);
		tv_task_list_title.add(tv_task_list_title5);
		tv_task_list_title.add(tv_task_list_title6);
		tv_task_list_title.add(tv_task_list_title7);
		tv_task_list_title.add(tv_task_list_title8);
		tv_task_list_title.add(tv_task_list_title9);
		tv_task_list_title.add(tv_task_list_title10);
		
		anim_in=AnimationUtils.loadAnimation(TaskListActivity.this, R.anim.show_anim);
		anim_out=AnimationUtils.loadAnimation(TaskListActivity.this, R.anim.close_anim);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_task_list_tool_query://显示查询条件
			onLoadQuery();
			break;
		case R.id.tv_task_list_tool_show://显示显示列设置页面
			onLoadShow();
			break;
		case R.id.tv_item_task_list_query_option_sub://查询提交
			toQuery();
			break;
		case R.id.item_task_list_show_option_checkall://全部选中显示
			setCheck(true);
			break;
		case R.id.item_task_list_show_option_checkallno://全部选中不显示
			setCheck(false);
			break;
		case R.id.tv_item_task_list_show_option_ok://确定设置
			toShow();
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * 显示
	 */
	private void toShow() {
		//先隐藏设置界面
		if(fl_show_flag||fl_query_flag){
			fl_show_flag=false;
			fl_query_flag=false;
			hidOption();
		}
		//如果已设置显示列则按设置显示，如果没设置按默认显示
		if (optionlist!=null||!optionlist.isEmpty()) {
			for (int i = 0; i < optionlist.size(); i++) {
				RadioGroup rg = optionlist.get(i);
				RadioButton rb= (RadioButton) rg.getChildAt(0);
				if(rb.isChecked()){
					showflag[i]=true;
				}else{
					showflag[i]=false;
				}
			} 
		}
		for(int i=0;i<tv_task_list_title.size();i++){
			if(showflag[i]){
				tv_task_list_title.get(i).setVisibility(View.VISIBLE);
			}else{
				tv_task_list_title.get(i).setVisibility(View.GONE);
			}
			
		}
		ta.setmShowflag(showflag);
		ta.setList(tasklist);
		lv_task_list_list.setAdapter(ta);
		ta.notifyDataSetChanged();
		
	}
	/**
	 * 查询
	 */
	private void toQuery() {
		se_accept_code     =et_item_task_query_option_accept_code  .getText().toString();
		unit_name    =et_item_task_query_option_request_unit .getText().toString();
		se_code         =et_item_task_query_option_se_code      .getText().toString();
		use_unit        =et_item_task_query_option_use_unit     .getText().toString();
		test_book_num   =et_item_task_query_option_test_book_num.getText().toString();
		test_leader          =et_item_task_query_option_leader       .getText().toString();
		current_step    =((RadioButton) rg_item_task_query_option_current_step.findViewById(rg_item_task_query_option_current_step.getCheckedRadioButtonId())).getText().toString();
		se_type         =((RadioButton) rg_item_task_query_option_se_type.findViewById(rg_item_task_query_option_se_type.getCheckedRadioButtonId())).getText().toString();
		apply_type     =((RadioButton) rg_item_task_query_option_examin_type.findViewById(rg_item_task_query_option_examin_type.getCheckedRadioButtonId())).getText().toString();
		//查询
		OkHttpClientManager.asyncGet("http://192.168.0.103", new Callback() {
			
			@Override
			public void onResponse(Response response) throws IOException {
				String msg=response.body().string();
				JSONObject json=JSONObject.parseObject(msg);
				JSONArray jsonarray= json.getJSONArray("list");
				tasklist= JSONArray.parseArray(jsonarray.toJSONString(), TaskModel.class);
				mhandler.sendEmptyMessage(1);
			}
			
			@Override
			public void onFailure(Request request, IOException e) {
				Log.e(TAG, "查询失败", e);
				
			}
		});
	}
	
	/**
	 * 设置是否显示列
	 * @param b
	 */
	private void setCheck(boolean b) {
		if(b){//选中是
			((RadioButton)rg_item_task_list_show_option_title0.getChildAt(0)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title1.getChildAt(0)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title2.getChildAt(0)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title3.getChildAt(0)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title4.getChildAt(0)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title5.getChildAt(0)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title6.getChildAt(0)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title7.getChildAt(0)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title8.getChildAt(0)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title9.getChildAt(0)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title10.getChildAt(0)).setChecked(true);
		}else{//选中否
			((RadioButton)rg_item_task_list_show_option_title0.getChildAt(1)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title1.getChildAt(1)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title2.getChildAt(1)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title3.getChildAt(1)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title4.getChildAt(1)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title5.getChildAt(1)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title6.getChildAt(1)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title7.getChildAt(1)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title8.getChildAt(1)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title9.getChildAt(1)).setChecked(true);
			((RadioButton)rg_item_task_list_show_option_title10.getChildAt(1)).setChecked(true);
		}
		
	}
	
	/**
	 * 设置显示列
	 */
	private void onLoadQuery() {
		fl_query_flag=false;
		if(fl_show_flag){
			fl_show_flag=false;
			//隐藏层
			hidOption();
			return;
		}
		//先清理员布局
		fl_task_list_option.removeAllViews();
		//设置显示隐藏布局
		fl_query_flag=true;
		LinearLayout ll_query= (LinearLayout) TaskListActivity.this.getLayoutInflater().inflate(R.layout.item_task_list_query_option, null);
		TextView tv_item_task_list_query_option_sub=(TextView) ll_query.findViewById(R.id.tv_item_task_list_query_option_sub);
		tv_item_task_list_query_option_sub.setOnClickListener(this); 
		//受理编号
		et_item_task_query_option_accept_code=(EditText) ll_query.findViewById(R.id.et_item_task_query_option_accept_code);
		//申报单位
		et_item_task_query_option_request_unit=(EditText) ll_query.findViewById(R.id.et_item_task_query_option_request_unit);
		//设备注册代码
		et_item_task_query_option_se_code=(EditText) ll_query.findViewById(R.id.et_item_task_query_option_se_code);
		//使用单位
		et_item_task_query_option_use_unit=(EditText) ll_query.findViewById(R.id.et_item_task_query_option_use_unit);
		//报告编号
		et_item_task_query_option_test_book_num=(EditText) ll_query.findViewById(R.id.et_item_task_query_option_test_book_num);
		//检验组长
		et_item_task_query_option_leader=(EditText) ll_query.findViewById(R.id.et_item_task_query_option_leader);
		//当前环节
		rg_item_task_query_option_current_step=(FlowRadioGroup) ll_query.findViewById(R.id.rg_item_task_query_option_current_step);
		//设备类型
		rg_item_task_query_option_se_type=(FlowRadioGroup) ll_query.findViewById(R.id.rg_item_task_query_option_se_type);
		//检验类型
		rg_item_task_query_option_examin_type=(FlowRadioGroup) ll_query.findViewById(R.id.rg_item_task_query_option_examin_type);
		fl_task_list_option.addView(ll_query);
		showOption();
		
	}

	/**
	 * 查询
	 */
	private void onLoadShow() {
		fl_query_flag=false;
		if(fl_show_flag){
			fl_show_flag=false;
			hidOption();
			return;
		}
		//先清理员布局
		fl_task_list_option.removeAllViews();
		//设置显示隐藏布局
		fl_show_flag=true;
		//清空
		optionlist.clear();
		LinearLayout ll_show= (LinearLayout) TaskListActivity.this.getLayoutInflater().inflate(R.layout.item_task_list_show_option, null);
		rg_item_task_list_show_option_title0=(RadioGroup) ll_show.findViewById(R.id.rg_item_task_list_show_option_title0);
		optionlist.add(rg_item_task_list_show_option_title0);
		rg_item_task_list_show_option_title1=(RadioGroup) ll_show.findViewById(R.id.rg_item_task_list_show_option_title1);
		optionlist.add(rg_item_task_list_show_option_title1);
		rg_item_task_list_show_option_title2=(RadioGroup) ll_show.findViewById(R.id.rg_item_task_list_show_option_title2);
		optionlist.add(rg_item_task_list_show_option_title2);
		rg_item_task_list_show_option_title3=(RadioGroup) ll_show.findViewById(R.id.rg_item_task_list_show_option_title3);
		optionlist.add(rg_item_task_list_show_option_title3);
		rg_item_task_list_show_option_title4=(RadioGroup) ll_show.findViewById(R.id.rg_item_task_list_show_option_title4);
		optionlist.add(rg_item_task_list_show_option_title4);
		rg_item_task_list_show_option_title5=(RadioGroup) ll_show.findViewById(R.id.rg_item_task_list_show_option_title5);
		optionlist.add(rg_item_task_list_show_option_title5);
		rg_item_task_list_show_option_title6=(RadioGroup) ll_show.findViewById(R.id.rg_item_task_list_show_option_title6);
		optionlist.add(rg_item_task_list_show_option_title6);
		rg_item_task_list_show_option_title7=(RadioGroup) ll_show.findViewById(R.id.rg_item_task_list_show_option_title7);
		optionlist.add(rg_item_task_list_show_option_title7);
		rg_item_task_list_show_option_title8=(RadioGroup) ll_show.findViewById(R.id.rg_item_task_list_show_option_title8);
		optionlist.add(rg_item_task_list_show_option_title8);
		rg_item_task_list_show_option_title9=(RadioGroup) ll_show.findViewById(R.id.rg_item_task_list_show_option_title9);
		optionlist.add(rg_item_task_list_show_option_title9);
		rg_item_task_list_show_option_title10=(RadioGroup) ll_show.findViewById(R.id.rg_item_task_list_show_option_title10);
		optionlist.add(rg_item_task_list_show_option_title10);
		
		RadioButton item_task_list_show_option_checkall=(RadioButton) ll_show.findViewById(R.id.item_task_list_show_option_checkall);
		item_task_list_show_option_checkall.setOnClickListener(this);
		RadioButton item_task_list_show_option_checkallno=(RadioButton) ll_show.findViewById(R.id.item_task_list_show_option_checkallno);
		item_task_list_show_option_checkallno.setOnClickListener(this);
		TextView tv_item_task_list_show_option_ok=(TextView) ll_show.findViewById(R.id.tv_item_task_list_show_option_ok);
		tv_item_task_list_show_option_ok.setOnClickListener(this);
		fl_task_list_option.addView(ll_show);
		
		for(int i=0;i<optionlist.size();i++){
			RadioGroup rg=optionlist.get(i);
			if(rg!=null){
				if(showflag[i]){
					((RadioButton)rg.getChildAt(0)).setChecked(true);
				}else{
					((RadioButton)rg.getChildAt(1)).setChecked(true);
				}
			}
		}
		showOption();
	}
	/**
	 * 隐藏层
	 */
	public void hidOption() {
		//加载动画
		sv_task_list_option.startAnimation(anim_out);
		fl_task_list_option.setVisibility(View.GONE);
	}
	
	/**
	 * 显示层
	 */
	public void showOption() {
		//加载动画
		sv_task_list_option.startAnimation(anim_in);
		//显示布局
		fl_task_list_option.setVisibility(View.VISIBLE);
	}

	@Override
	protected void initEvent() {
		tv_task_list_tool_query.setOnClickListener(this);
		tv_task_list_tool_show.setOnClickListener(this);
//		lv_task_list_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				Intent intent=new Intent(TaskListActivity.this, FlowingActivity.class);
//				intent.putExtra("annalid", "123123");
//				TaskListActivity.this.startActivity(intent);
//				TaskListActivity.this.finish();
//			}
//		});
	}
	

	@Override
	protected Handler setMHandler() {
		// TODO Auto-generated method stub
		return null;
	}
}
