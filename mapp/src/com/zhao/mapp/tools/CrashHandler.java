package com.zhao.mapp.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

/**
  * 全局异常捕获处理
  * 2016-8-16
  * @author zhyp
  *
  */
public class CrashHandler implements UncaughtExceptionHandler {
	public static final String TAG = "CrashHandler";
	//Context
	private Context mContext;
	 //app
	private Application mApp;
	//app默认的异常处理类
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	//用于存储异常信息
	private Map<String,String> infos=new HashMap<String, String>();
	
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	
	private static class mHolder{
		private static final CrashHandler INSTANCE=new CrashHandler();
	} 
	public CrashHandler() {}
	/**
	 * 单例方式获取对象
	 * @return
	 */
	public static CrashHandler getInstance() {
		return mHolder.INSTANCE;
	}
	/**
	 * 初始化
	 * @param context
	 * @param app
	 */
	public void init(Context context, Application app) {
		this.mContext=context;
		this.mApp=app;
		//获得默认异常处理类
		this.mDefaultHandler=Thread.getDefaultUncaughtExceptionHandler();
		//设置当前类为默认异常处理类
		Thread.setDefaultUncaughtExceptionHandler(getInstance());
	}
	/**
	 * 但错误发生时会转入该处理类
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		 if (!handleException(ex) && mDefaultHandler != null) {  
	            // 如果用户没有处理则让系统默认的异常处理器来处理  
	            mDefaultHandler.uncaughtException(thread, ex);  
	        } else {  
	            ActivityManagerList.finishProgram(); 
	            mApp.onTerminate();  
	        }  
	}
	/**
	 * 错误处理
	 * @param ex
	 * @return
	 */
	private boolean handleException(Throwable ex) {
		if(ex==null)return false;
		// 收集设备参数信息  
        collectDeviceInfo(mContext);  
        // 保存日志文件  
        saveCrashInfo2File(ex);  
        return true; 
		
	}
	/**
	 * 收集设备信息
	 * @param mContext2
	 */
	private void collectDeviceInfo(Context seinfo) {
		 try {  
	            PackageManager pm = seinfo.getPackageManager();  
	            PackageInfo pi = pm.getPackageInfo(seinfo.getPackageName(),  
	                    PackageManager.GET_ACTIVITIES);  
	  
	            if (pi != null) {  
	                String versionName = pi.versionName == null ? "null"  
	                        : pi.versionName;  
	                String versionCode = pi.versionCode + "";  
	                infos.put("versionName", versionName);  
	                infos.put("versionCode", versionCode);  
	            }  
	        } catch (NameNotFoundException e) {  
	            Log.e(TAG, "an error occured when collect package info", e);  
	        }  
	        Field[] fields = Build.class.getDeclaredFields();  
	        for (Field field : fields) {  
	            try {  
	                field.setAccessible(true);  
	                infos.put(field.getName(), field.get(null).toString());  
	            } catch (Exception e) {  
	                Log.e(TAG, "an error occured when collect crash info", e);  
	            }  
	        } 
		
	}
	/**
	 * 保存日志
	 * @param ex
	 */
	private String saveCrashInfo2File(Throwable ex) {
		StringBuffer sb = new StringBuffer();  
        for (Map.Entry<String, String> entry : infos.entrySet()) {  
            String key = entry.getKey();  
            String value = entry.getValue();  
            sb.append(key + "=" + value + "\n");  
        }  
  
        Writer writer = new StringWriter();  
        PrintWriter printWriter = new PrintWriter(writer);  
        ex.printStackTrace(printWriter);  
        Throwable cause = ex.getCause();  
        while (cause != null) {  
            cause.printStackTrace(printWriter);  
            cause = cause.getCause();  
        }  
        printWriter.close();  
  
        String result = writer.toString();  
        sb.append(result);  
        try {  
            long timestamp = System.currentTimeMillis();  
            String time = sdf.format(new Date());  
            String fileName = "error-" + time + "-" + timestamp + ".log";  
            if (Environment.getExternalStorageState().equals(  
                    Environment.MEDIA_MOUNTED)) {  
                String path = Environment.getExternalStorageDirectory()  
                        .getAbsolutePath() + "/crashs";  
                Toast.makeText(mContext, path, Toast.LENGTH_LONG).show();  
                File dir = new File(path);  
                if (!dir.exists()) {  
                    dir.mkdirs();  
                }  
                FileOutputStream fos = new FileOutputStream(path + fileName);  
                fos.write(sb.toString().getBytes());  
                fos.flush();  
                fos.close();  
            }  
            return fileName;  
        } catch (Exception e) {  
            Log.e(TAG, "an error occured while writing file...", e);  
        }  
  
        return null;
		
	}
	
}
