package com.zhao.mapp;

import java.io.File;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

import com.zhao.mapp.greendao.DaoMaster;
import com.zhao.mapp.greendao.DaoMaster.OpenHelper;
import com.zhao.mapp.greendao.DaoSession;
import com.zhao.mapp.pic.ImageUtils;
import com.zhao.mapp.tools.CrashHandler;
import com.zhao.mapp.tools.DBHelper;

public class MApplication extends Application {
	private static DaoMaster daoMaster;
	private static DaoSession daoSession;
	private static DBHelper dbHelper;
	private static ImageUtils imageUtils;
	public static SQLiteDatabase db;
	private static boolean confirm_res=true;
	private static  MApplication INSTANCE;
	public static boolean isfastok=false;
	//图片缓存路径
	public static String imageCachePath=Environment.getDownloadCacheDirectory().getAbsolutePath()+File.separator+"mapp_cache"+File.separator;
	// 数据库名，表名是自动被创建的
	public static final String DB_NAME = "dbname.db";

	public static MApplication getInstance() {
		if(INSTANCE==null){
			synchronized (ACCESSIBILITY_SERVICE) {
				if (INSTANCE == null) {
					INSTANCE = new MApplication();
				}
			}
		}
		return INSTANCE;
	}

	public MApplication() {
		super();
		INSTANCE=this;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// 启用全局异常捕获
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext(), this);
		//初始化数据库操作类
		getDaoSession();
		getDbHelper();
		dbHelper=DBHelper.getInstance(getInstance());
		imageUtils=ImageUtils.getInstance();
		INSTANCE=this;
		
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

	public static DaoSession getDaoSession() {
		if (daoSession == null) {
			if (daoMaster == null) {
				daoMaster = getDaoMaster(getInstance());
			}
			daoSession = daoMaster.newSession();
		}
		return daoSession;
	}

	public static SQLiteDatabase getSQLDatebase() {
		if (daoSession == null) {
			if (daoMaster == null) {
				daoMaster = getDaoMaster(getInstance());
			}
			db = daoMaster.getDatabase();
		}
		return db;
	}
	public static DBHelper getDbHelper() {
		if (dbHelper == null) {
			dbHelper = DBHelper.getInstance(getInstance());
		}
		return dbHelper;
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
