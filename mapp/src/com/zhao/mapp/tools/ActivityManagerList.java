package com.zhao.mapp.tools;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;

/**
 * @author tanhy 进程管理页面
 *
 */
public class ActivityManagerList {
	private static List<Activity> activityList = new ArrayList<Activity>();
	
	public static void remove(Activity activity) {
		activityList.remove(activity);
	}

	public static void add(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * 释放进程,并关闭程序
	 */
	public static void finishProgram() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
}
