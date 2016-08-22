package com.zhao.mapp.tools;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zhao.mapp.greendao.DaoMaster;
import com.zhao.mapp.greendao.DaoMaster.OpenHelper;
import com.zhao.mapp.greendao.DaoSession;

public class MApplication extends Application {
	private static final String TAG = "MApplication";
	public static MHandler mHandler;
	private static DaoMaster daoMaster;
	private static DaoSession daoSession;
	public static SQLiteDatabase db;
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
			daoMaster = new DaoMaster(helper.getWritableDatabase());
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
}
