package com.zhao.mapp.tools;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;

/**
 * @author tanhy 进程管理页面
 *
 */
public class ActivityManagerList {
	static class MHolder{
		private static ActivityManagerList INSTANCE=new ActivityManagerList();
	}
	private List<Activity> activityList = new ArrayList<Activity>();
	public ActivityManagerList() {
		// TODO Auto-generated constructor stub
	}
	
	public static ActivityManagerList getInstance(){
		return MHolder.INSTANCE;
	}
	public static void remove(Activity activity) {
		getInstance().activityList.remove(activity);
	}

	public static void add(Activity activity) {
		getInstance().activityList.add(activity);
	}

	/**
	 * 释放进程,并关闭程序
	 */
	public static void finishProgram() {
		List<Activity> acList=getInstance().activityList;
		for (Activity activity : acList) {
			activity.finish();
		}
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
}
