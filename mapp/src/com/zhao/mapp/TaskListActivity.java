package com.zhao.mapp;

import java.util.ArrayList;

import com.zhao.mapp.adapter.TaskAdapter;
import com.zhao.mapp.ioc.view.ViewInjectUtils;
import com.zhao.mapp.ioc.view.annotation.ContentView;
import com.zhao.mapp.ioc.view.annotation.ViewInject;
import com.zhao.mapp.model.TaskModel;
import com.zhao.mapp.tools.FlowRadioGroup;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
	
	private boolean fl_flag=false;
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewInjectUtils.inject(this);
		//绑定事件
		initEvent();
		ta=new TaskAdapter(this);
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
		ta.setmShowflag(showflag);
		ta.setList(new ArrayList<TaskModel>());
		
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
		//todo
	}
	
	/**
	 * 设置是否显示列
	 * @param b
	 */
	private void setCheck(boolean b) {
		if(b){//选中是
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
	private void onLoadShow() {
		//设置显示隐藏布局
		fl_flag=true;
		LinearLayout ll_show= (LinearLayout) TaskListActivity.this.getLayoutInflater().inflate(R.layout.item_task_list_show_option, null);
		TextView tv_item_task_list_query_option_sub=(TextView) ll_show.findViewById(R.id.tv_item_task_list_query_option_sub);
		tv_item_task_list_query_option_sub.setOnClickListener(this); 
		//受理编号
		et_item_task_query_option_accept_code=(EditText) ll_show.findViewById(R.id.et_item_task_query_option_accept_code);
		//申报单位
		et_item_task_query_option_request_unit=(EditText) ll_show.findViewById(R.id.et_item_task_query_option_request_unit);
		//设备注册代码
		et_item_task_query_option_se_code=(EditText) ll_show.findViewById(R.id.et_item_task_query_option_se_code);
		//使用单位
		et_item_task_query_option_use_unit=(EditText) ll_show.findViewById(R.id.et_item_task_query_option_use_unit);
		//报告编号
		et_item_task_query_option_test_book_num=(EditText) ll_show.findViewById(R.id.et_item_task_query_option_test_book_num);
		//检验组长
		et_item_task_query_option_leader=(EditText) ll_show.findViewById(R.id.et_item_task_query_option_leader);
		//当前环节
		rg_item_task_query_option_current_step=(FlowRadioGroup) ll_show.findViewById(R.id.rg_item_task_query_option_current_step);
		//设备类型
		rg_item_task_query_option_se_type=(FlowRadioGroup) ll_show.findViewById(R.id.rg_item_task_query_option_se_type);
		//检验类型
		rg_item_task_query_option_examin_type=(FlowRadioGroup) ll_show.findViewById(R.id.rg_item_task_query_option_examin_type);
		
	}
	/**
	 * 查询
	 */
	private void onLoadQuery() {
		//设置显示隐藏布局
		fl_flag=true;
		LinearLayout ll_query= (LinearLayout) TaskListActivity.this.getLayoutInflater().inflate(R.layout.item_task_list_query_option, null);
		rg_item_task_list_show_option_title1=(RadioGroup) ll_query.findViewById(R.id.rg_item_task_list_show_option_title1);
		rg_item_task_list_show_option_title2=(RadioGroup) ll_query.findViewById(R.id.rg_item_task_list_show_option_title2);
		rg_item_task_list_show_option_title3=(RadioGroup) ll_query.findViewById(R.id.rg_item_task_list_show_option_title3);
		rg_item_task_list_show_option_title4=(RadioGroup) ll_query.findViewById(R.id.rg_item_task_list_show_option_title4);
		rg_item_task_list_show_option_title5=(RadioGroup) ll_query.findViewById(R.id.rg_item_task_list_show_option_title5);
		rg_item_task_list_show_option_title6=(RadioGroup) ll_query.findViewById(R.id.rg_item_task_list_show_option_title6);
		rg_item_task_list_show_option_title7=(RadioGroup) ll_query.findViewById(R.id.rg_item_task_list_show_option_title7);
		rg_item_task_list_show_option_title8=(RadioGroup) ll_query.findViewById(R.id.rg_item_task_list_show_option_title8);
		rg_item_task_list_show_option_title9=(RadioGroup) ll_query.findViewById(R.id.rg_item_task_list_show_option_title9);
		rg_item_task_list_show_option_title10=(RadioGroup) ll_query.findViewById(R.id.rg_item_task_list_show_option_title10);
		
		RadioButton item_task_list_show_option_checkall=(RadioButton) ll_query.findViewById(R.id.item_task_list_show_option_checkall);
		item_task_list_show_option_checkall.setOnClickListener(this);
		RadioButton item_task_list_show_option_checkallno=(RadioButton) ll_query.findViewById(R.id.item_task_list_show_option_checkallno);
		item_task_list_show_option_checkallno.setOnClickListener(this);
		TextView tv_item_task_list_show_option_ok=(TextView) ll_query.findViewById(R.id.tv_item_task_list_show_option_ok);
		tv_item_task_list_show_option_ok.setOnClickListener(this);
		
		
	}

	@Override
	protected void initEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Handler setMHandler() {
		// TODO Auto-generated method stub
		return null;
	}
}
