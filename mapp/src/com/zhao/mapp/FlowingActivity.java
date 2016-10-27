package com.zhao.mapp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zhao.mapp.adapter.DetailAdapter;
import com.zhao.mapp.adapter.HistoryAdapter;
import com.zhao.mapp.ioc.view.ViewInjectUtils;
import com.zhao.mapp.ioc.view.annotation.ContentView;
import com.zhao.mapp.ioc.view.annotation.ViewInject;
import com.zhao.mapp.model.DetailModel;
import com.zhao.mapp.model.HistoryModel;
import com.zhao.mapp.tools.EndFlowTextView;
import com.zhao.mapp.tools.FlowingTextView;
import com.zhao.mapp.tools.SignView;
import com.zhao.mapp.tools.StartFlowTextView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
@ContentView(value=R.layout.activity_flowing)
public class FlowingActivity extends BaseActivity {
	@ViewInject(value=R.id.ll_flowing_transfer_position)
	private LinearLayout ll_flowing_transfer_position;
	@ViewInject(value=R.id.ll_flowing_first_tab)
	private LinearLayout ll_flowing_first_tab;
	@ViewInject(value=R.id.fl_flowing_context)
	private FrameLayout fl_flowing_context;
	
	//签名域
	private SignView sv_item_flow_context_proc_sign;
	//清除
	private Button btn_item_flow_context_proc_sign_clear;
	//另存签名图片
	private Button btn_item_flow_context_proc_sign_saveother;
	//退回
	private Button item_flow_context_prpcessing_back;
	//保存
	private Button item_flow_context_prpcessing_save;
	//发送
	private Button item_flow_context_prpcessing_send;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init(this);
	}
	/**
	 * 加载布局
	 */
	private void init(Activity context) {
		ViewInjectUtils.inject(context);
		
		
		
		String[] huanjie=new String[]{"启动","受理","审查","审核","审批","办结"};
		int curr_index=2;
		LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
		lp.weight=1;
		for(int i=0;i<huanjie.length;i++){
			if(i==0){//第一个元素
				StartFlowTextView sdtv=new StartFlowTextView(FlowingActivity.this);
				sdtv.setBackgroundColor(Color.YELLOW);
				sdtv.setGravity(Gravity.CENTER);
				if(curr_index==i){
					sdtv.setBgColor(Color.GREEN);
				}else{
					sdtv.setBgColor(Color.BLUE);
				}
				sdtv.setText(huanjie[i]);
				ll_flowing_transfer_position.addView(sdtv,lp);
			}else if(i==huanjie.length-1){//最后一个元素
				EndFlowTextView eftv=new EndFlowTextView(FlowingActivity.this);
				eftv.setBackgroundColor(Color.YELLOW);
				eftv.setGravity(Gravity.CENTER);
				if(curr_index==i){
					eftv.setBgColor(Color.GREEN);
				}else{
					eftv.setBgColor(Color.BLUE);
				}
				eftv.setText(huanjie[i]);
				ll_flowing_transfer_position.addView(eftv,lp);
			}else{
				FlowingTextView ftv=new FlowingTextView(FlowingActivity.this);
				ftv.setBackgroundColor(Color.YELLOW);
				ftv.setGravity(Gravity.CENTER);
				if(curr_index==i){
					ftv.setBgColor(Color.GREEN);
				}else{
					ftv.setBgColor(Color.BLUE);
				}
				ftv.setText(huanjie[i]);
				ll_flowing_transfer_position.addView(ftv,lp);
			}
		}
		//选项卡
		String[] xuanxiang=new String[]{"处理签","详细信息","历史意见"};
		LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		params.setMargins(5, 0, 5, 0);
		for(int i=0;i<xuanxiang.length;i++){
			TextView tv=new TextView(FlowingActivity.this);
			tv.setMinWidth(100);
			tv.setGravity(Gravity.CENTER);
			tv.setText(xuanxiang[i]);
			tv.setBackgroundColor(Color.CYAN);
			tv.setOnClickListener(FlowingActivity.this);
			ll_flowing_first_tab.addView(tv, params);
		}
		TextView tv=new TextView(FlowingActivity.this);
		tv.setGravity(Gravity.CENTER);
		tv.setText(" 2");
		LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		ll_flowing_first_tab.addView(tv, params1);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_item_flow_context_proc_sign_clear:
			if(sv_item_flow_context_proc_sign!=null){
				sv_item_flow_context_proc_sign.clear();
			}
			break;
		case R.id.btn_item_flow_context_proc_sign_saveother:
			Intent pathintent=new Intent(FlowingActivity.this,FileBrowerActivity.class);
			startActivityForResult(pathintent, 1);
			break;
		case R.id.item_flow_context_prpcessing_back:
			showMyChoiseDialog(R.layout.dialog_my_choise, "温馨提示", "确定要退回吗？",1 );
			break;
		case R.id.item_flow_context_prpcessing_save:
			showMyChoiseDialog(R.layout.dialog_my_ing, "温馨提示", "正在存档请稍后...",3 );
			break;
		case R.id.item_flow_context_prpcessing_send:
			showMyChoiseDialog(R.layout.dialog_my_choise, "温馨提示", "确定要发送吗？",2 );
			break;
		default:
			break;
		}
		
		
		
		TextView tv=(TextView) v;
		String text=tv.getText().toString();
		if("处理签".equals(text)){
			View clq= getLayoutInflater().inflate(R.layout.item_flow_context_processing, null);
			fl_flowing_context.removeAllViews();
			fl_flowing_context.addView(clq);
			sv_item_flow_context_proc_sign=(SignView)clq.findViewById(R.id.sv_item_flow_context_proc_sign);
			btn_item_flow_context_proc_sign_clear=(Button)clq.findViewById(R.id.btn_item_flow_context_proc_sign_clear);
			btn_item_flow_context_proc_sign_saveother=(Button) clq.findViewById(R.id.btn_item_flow_context_proc_sign_saveother);
			item_flow_context_prpcessing_back=(Button) clq.findViewById(R.id.item_flow_context_prpcessing_back);
			item_flow_context_prpcessing_save=(Button) clq.findViewById(R.id.item_flow_context_prpcessing_save);
			item_flow_context_prpcessing_send=(Button) clq.findViewById(R.id.item_flow_context_prpcessing_send);
			btn_item_flow_context_proc_sign_clear.setOnClickListener(FlowingActivity.this);
			btn_item_flow_context_proc_sign_saveother.setOnClickListener(FlowingActivity.this);
			item_flow_context_prpcessing_back.setOnClickListener(FlowingActivity.this);
			item_flow_context_prpcessing_save.setOnClickListener(FlowingActivity.this);
			item_flow_context_prpcessing_send.setOnClickListener(FlowingActivity.this);
			
		}else if("详细信息".equals(text)){
			sv_item_flow_context_proc_sign=null;
			btn_item_flow_context_proc_sign_clear=null;
			
			ListView detail= (ListView) getLayoutInflater().inflate(R.layout.item_flow_context_detail_information, null);
			setAdapter(detail);
			fl_flowing_context.removeAllViews();
			fl_flowing_context.addView(detail);
		}else if("历史意见".equals(text)){
			sv_item_flow_context_proc_sign=null;
			btn_item_flow_context_proc_sign_clear=null;
			
			View history= getLayoutInflater().inflate(R.layout.item_flow_context_history, null);
			ListView history_list=(ListView) history.findViewById(R.id.lv_item_flow_context_history_list);
			HistoryAdapter adapter= new HistoryAdapter(FlowingActivity.this);
			List<HistoryModel> histlist = setlist();
			adapter.setList(histlist);
			history_list.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			
			fl_flowing_context.removeAllViews();
			fl_flowing_context.addView(history);
			
		}
		
	}
	private void setAdapter(ListView detail) {
		DetailAdapter da=new DetailAdapter(FlowingActivity.this);
		List<DetailModel> list=new ArrayList<DetailModel>();
		DetailModel model=new DetailModel();
		model.setId(1l);
		model.setKey("单位名称");
		model.setValue("山东金质信息技术有限公司");
		list.add(model);
		
		DetailModel model1=new DetailModel();
		model1.setId(1l);
		model1.setKey("单位地址");
		model1.setValue("济南市历下区历山路146-6号");
		list.add(model1);
		
		DetailModel model2=new DetailModel();
		model2.setId(1l);
		model2.setKey("联系人");
		model2.setValue("赵玉平");
		list.add(model2);
		
		DetailModel model3=new DetailModel();
		model3.setId(1l);
		model3.setKey("联系电话");
		model3.setValue("15865259452");
		list.add(model3);
		
		DetailModel model4=new DetailModel();
		model4.setId(1l);
		model4.setKey("主要业务");
		model4.setValue("电子政务、网站建设、条码扫描设备等");
		list.add(model4);
		
		da.setList(list);
		detail.setAdapter(da);
		da.notifyDataSetChanged();
	}
	/**
	 * @return
	 */
	public List<HistoryModel> setlist() {
		List<HistoryModel> histlist=new ArrayList<HistoryModel>();
		HistoryModel historymodel=new HistoryModel();
		historymodel.setId(1l);
		historymodel.setIndex("1");
		historymodel.setPerson("张三");
		historymodel.setOpinion("请领导批阅");
		historymodel.setResult("同意");
		historymodel.setSign("zhangsan");
		historymodel.setSigndate("2016-10-21");
		histlist.add(historymodel);
		
		HistoryModel historymodel1=new HistoryModel();
		historymodel1.setId(2l);
		historymodel1.setIndex("2");
		historymodel1.setPerson("李四");
		historymodel1.setOpinion("同意");
		historymodel1.setResult("同意");
		historymodel1.setSign("lisi");
		historymodel1.setSigndate("2016-10-22");
		histlist.add(historymodel1);
		
		HistoryModel historymodel2=new HistoryModel();
		historymodel2.setId(3l);
		historymodel2.setIndex("3");
		historymodel2.setPerson("王五");
		historymodel2.setOpinion("同意");
		historymodel2.setResult("同意");
		historymodel2.setSign("wangwu");
		historymodel2.setSigndate("2016-10-23");
		histlist.add(historymodel2);
		
		return histlist;
	}
	@Override
	protected void initEvent() {
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode==Activity.RESULT_OK){
			if(requestCode==1){
				
				String file_path=data.getStringExtra("path");
				File dir=new File(file_path);
		        if(dir.isDirectory()){
		        	Bitmap pic=sv_item_flow_context_proc_sign.getCacheBitmap();
		        	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        	pic.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		        	byte[] buff= baos.toByteArray();
		        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		        	String filename=sdf.format(new Date());
		        	File file=new File(file_path+"/"+filename+".jpg");
		        	try {
						FileOutputStream fos=new FileOutputStream(file);
						fos.write(buff);
						fos.flush();
						fos.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						try {
							if(baos!=null)
								baos.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
		        }
				
			}
		}
	}
	@Override
	protected Handler setMHandler() {
		return new Handler(getMainLooper()){
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1://对话框点击了确定的操作
					showToastMsgShort("退回成功！");
					break;
				case 2:
					showToastMsgShort("退回成功！");
				case 3:
					showToastMsgShort("保存完成!");
					
				default:
					break;
				}
			}
		};
	}
	
}
