package com.zhao.mapp.tools;

import java.io.File;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.zhao.mapp.greendao.DaoMaster;
import com.zhao.mapp.greendao.DaoMaster.OpenHelper;
import com.zhao.mapp.greendao.DaoSession;
import com.zhao.mapp.pic.ImageUtils;

public class MApplication extends Application {
	private static final String TAG = "MApplication";
	public static MHandler mHandler;
	private static DaoMaster daoMaster;
	private static DaoSession daoSession;
	private static ImageUtils imageUtils;
	public static SQLiteDatabase db;
	private static boolean confirm_res=true;
	//图片缓存路径
	public static String imageCachePath=Environment.getDownloadCacheDirectory().getAbsolutePath()+File.separator+"mapp_cache"+File.separator;
	// 数据库名，表名是自动被创建的
	public static final String DB_NAME = "dbname.db";

	private static class MHolder {
		private static final MApplication INSTANCE = new MApplication();
	}

	public static MApplication getInstance() {
		return MHolder.INSTANCE;
	}

	public MApplication() {
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// 启用全局异常捕获
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext(), this);
		this.mHandler = new MHandler(getApplicationContext());
		//初始化数据库操作类
		getDaoSession(getInstance());
		imageUtils=ImageUtils.getInstance();
		
	}

	/**
	 * 重写退出
	 */
	@Override
	public void onTerminate() {
		super.onTerminate();
		System.exit(0);
	}

	public static DaoMaster getDaoMaster(Context context) {
		if (daoMaster == null) {
			OpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
			db= helper.getWritableDatabase();
			daoMaster = new DaoMaster(db);
		}
		return daoMaster;
	}

	public static DaoSession getDaoSession(Context context) {
		if (daoSession == null) {
			if (daoMaster == null) {
				daoMaster = getDaoMaster(context);
			}
			daoSession = daoMaster.newSession();
		}
		return daoSession;
	}

	public static SQLiteDatabase getSQLDatebase(Context context) {
		if (daoSession == null) {
			if (daoMaster == null) {
				daoMaster = getDaoMaster(context);
			}
			db = daoMaster.getDatabase();
		}
		return db;
	}
	/**
	 * 显示提示
	 * @param msg
	 */
	public static void showToastShort(String msg){
		Toast.makeText(getInstance(), msg, Toast.LENGTH_SHORT).show();
	}
	/**
	 * 显示提示
	 * @param msg
	 */
	public static void showToastLong(String msg){
		Toast.makeText(getInstance(), msg, Toast.LENGTH_LONG).show();
	}
	/**
	 * 提示对话框
	 * @param msg
	 */
	public static void showTipsDialog(String msg){
		new AlertDialog.Builder(getInstance()).setTitle("提示").setMessage(msg).setNegativeButton("关闭", null).show();
	}
	
}
