package com.zhao.mapp.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zhao.mapp.MApplication;
import com.zhao.mapp.interfaces.ProgressRequestListener;
import com.zhao.mapp.interfaces.ProgressResponseListener;

public class OkHttpClientManager {
	private static String TAG = "OkHttpClientManager";
	private static OkHttpClient client;

	private static class MHolder {
		private static OkHttpClientManager INSTANCE = new OkHttpClientManager();
	}

	public OkHttpClientManager() {
		OkHttpClientManager.client = new OkHttpClient();
		// 设置缓存
		int cachesize = 10 * 1024 * 1024;
		Environment.getDownloadCacheDirectory();
		File cacheDirectory = null;
		try {
			cacheDirectory = File.createTempFile("okhttp_", "_cache");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Cache cache = new Cache(cacheDirectory, cachesize);
		client.setCache(cache);
		// 设置超时时间
		client.setConnectTimeout(100, TimeUnit.SECONDS);
		client.setReadTimeout(300, TimeUnit.SECONDS);
		client.setWriteTimeout(100, TimeUnit.SECONDS);
	}

	public static OkHttpClientManager getInstance() {
		return MHolder.INSTANCE;
	}

	/**
	 * 异步get查询
	 * 
	 * @param url
	 */
	private void _asyncGet(String url, Callback callback) {
		Request request = new Request.Builder().url(url).build();
		client.newCall(request).enqueue(callback);
	}

	/**
	 * 同步get查询
	 * 
	 * @param url
	 * @throws IOException
	 */
	private Response _syncGet(String url) throws IOException {
		Request request = new Request.Builder().url(url).addHeader("", "").build();

		Response response = client.newCall(request).execute();
		return response;
	}

	/**
	 * 同步post上传参数、文件
	 * 
	 * @param url
	 * @param params
	 * @param files
	 * @return
	 * @throws IOException
	 */
	private void _asyncPost(String url, Map<String, String> params, Map<String, File> files, Callback callback) throws IOException {
		MultipartBuilder mb = new MultipartBuilder();
		mb.type(MultipartBuilder.FORM);
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				mb.addFormDataPart(key, params.get(key));
			}
		}
		if (files != null && files.size() > 0) {
			for (String key : files.keySet()) {
				File file = files.get(key);
				RequestBody rb = RequestBody.create(MediaType.parse("application/octet-stream"), file);
				mb.addFormDataPart(key, file.getName(), rb);
			}
		}
		RequestBody body = mb.build();
		Request request = new Request.Builder().header("Authorization", "Client-ID ...").url(url).post(body).build();

		client.newCall(request).enqueue(callback);
	}

	/**
	 * 同步post上传参数、文件
	 * 
	 * @param url
	 * @param params
	 * @param files
	 * @return
	 * @throws IOException
	 */
	private Response _syncPost(String url, Map<String, String> params, Map<String, File> files) throws IOException {
		MultipartBuilder mb = new MultipartBuilder();
		mb.type(MultipartBuilder.FORM);
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				mb.addFormDataPart(key, params.get(key));
			}
		}
		if (files != null && files.size() > 0) {
			for (String key : files.keySet()) {
				File file = files.get(key);
				RequestBody rb = RequestBody.create(MediaType.parse(""), file);
				mb.addFormDataPart(key, file.getName(), rb);
			}
		}
		RequestBody body = mb.build();
		Request request = new Request.Builder().header("Authorization", "Client-ID ...").url(url).post(body).build();

		Response response = client.newCall(request).execute();
		return response;
	}

	/**
	 * 提交字符串参数
	 * 
	 * @param url
	 * @param params
	 * @throws IOException
	 */
	private void _asyncPostParams(String url, Map<String, String> params, Callback callback) {
		FormEncodingBuilder feb = new FormEncodingBuilder();
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				feb.addEncoded(key, params.get(key));
			}
		}
		RequestBody body = feb.build();
		Request request = new Request.Builder().header("Authorization", "Client-ID ...").url(url).post(body).build();
		client.newCall(request).enqueue(callback);
	}

	/**
	 * 提交字符串参数
	 * 
	 * @param url
	 * @param params
	 * @throws IOException
	 */
	private Response _syncPostParams(String url, Map<String, String> params) throws IOException {
		FormEncodingBuilder feb = new FormEncodingBuilder();
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				feb.addEncoded(key, params.get(key));
			}
		}
		RequestBody body = feb.build();
		Request request = new Request.Builder().header("Authorization", "Client-ID ...").url(url).post(body).build();
		Response respone = client.newCall(request).execute();
		return respone;
	}

	/**
	 * 下载 2016-8-17
	 * 
	 * @param url
	 * @param dir
	 * @param downloadProgeress
	 *            不为null是显示进度条
	 */
	private void _downloadShowProgeress(String url, String dir, final ProgressBar downloadProgeress) {

		// 这个是ui线程回调，可直接操作UI
		final UIProgressResponseListener uiProgressResponseListener = new UIProgressResponseListener() {
			@Override
			public void onUIResponseProgress(long bytesRead, long contentLength, boolean done) {
				Log.e("TAG", "bytesRead:" + bytesRead);
				Log.e("TAG", "contentLength:" + contentLength);
				Log.e("TAG", "done:" + done);
				if (contentLength != -1) {
					// 长度未知的情况下回返回-1
					Log.e("TAG", (100 * bytesRead) / contentLength + "% done");
				}
				Log.e("TAG", "================================");
				// ui层回调
				downloadProgeress.setProgress((int) ((100 * bytesRead) / contentLength));
				// Toast.makeText(getApplicationContext(), bytesRead + " " +
				// contentLength + " " + done, Toast.LENGTH_LONG).show();
			}
		};

		// 构造请求
		final Request request1 = new Request.Builder().url(url).build();

		// 包装Response使其支持进度回调
		ProgressHelper.addProgressResponseListener(client, uiProgressResponseListener).newCall(request1).enqueue(new Callback() {
			@Override
			public void onFailure(Request request, IOException e) {
				Log.e("TAG", "error ", e);
			}

			@Override
			public void onResponse(Response response) throws IOException {
				Log.e("TAG", response.body().string());
			}
		});
	}

	/**
	 * 下载 2016-8-17
	 * 
	 * @param url
	 * @param dir
	 * @param downloadProgeress
	 *            不为null是显示进度条
	 */
	private void _downloadNotProgeress(String url, String dir) {

		// 这个是非ui线程回调，不可直接操作UI
		final ProgressResponseListener progressResponseListener = new ProgressResponseListener() {
			@Override
			public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
				Log.e("TAG", "bytesRead:" + bytesRead);
				Log.e("TAG", "contentLength:" + contentLength);
				Log.e("TAG", "done:" + done);
				if (contentLength != -1) {
					// 长度未知的情况下回返回-1
					Log.e("TAG", (100 * bytesRead) / contentLength + "% done");
				}
				Log.e("TAG", "================================");
			}
		};

		// 构造请求
		final Request request1 = new Request.Builder().url(url).build();

		// 包装Response使其支持进度回调
		ProgressHelper.addProgressResponseListener(client, progressResponseListener).newCall(request1).enqueue(new Callback() {
			@Override
			public void onFailure(Request request, IOException e) {
				Log.e("TAG", "error ", e);
			}

			@Override
			public void onResponse(Response response) throws IOException {
				Log.e("TAG", response.body().string());
			}
		});
	}

	/**
	 * 上传 2016-8-17
	 * 
	 * @param url
	 * @param path
	 * @param uploadProgress
	 *            不为null显示进度条
	 */
	private void _uploadShowProgress(String url, String name, File file, final ProgressBar uploadProgress) {
		// 这个是ui线程回调，可直接操作UI
		final UIProgressRequestListener uiProgressRequestListener = new UIProgressRequestListener() {
			@Override
			public void onUIRequestProgress(long bytesWrite, long contentLength, boolean done) {
				Log.e("TAG", "bytesWrite:" + bytesWrite);
				Log.e("TAG", "contentLength" + contentLength);
				Log.e("TAG", (100 * bytesWrite) / contentLength + " % done ");
				Log.e("TAG", "done:" + done);
				Log.e("TAG", "================================");
				// ui层回调
				uploadProgress.setProgress((int) ((100 * bytesWrite) / contentLength));
				// Toast.makeText(getApplicationContext(), bytesWrite + " " +
				// contentLength + " " + done, Toast.LENGTH_LONG).show();
			}
		};

		// 构造上传请求，类似web表单
		RequestBody requestBody = new MultipartBuilder().type(MultipartBuilder.FORM).addFormDataPart("name", name).addFormDataPart("photo", file.getName(), RequestBody.create(null, file)).addPart(Headers.of("Content-Disposition", "form-data; name=\"another\";filename=\"another.dex\""), RequestBody.create(MediaType.parse("application/octet-stream"), file)).build();

		// 进行包装，使其支持进度回调
		final Request request = new Request.Builder().url(url).post(ProgressHelper.addProgressRequestListener(requestBody, uiProgressRequestListener)).build();

		// 开始请求
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Request request, IOException e) {
				Log.e("TAG", "error ", e);
			}

			@Override
			public void onResponse(Response response) throws IOException {
				Log.e("TAG", response.body().string());
			}
		});

	}

	/**
	 * 上传 2016-8-17
	 * 
	 * @param url
	 * @param path
	 * @param uploadProgress
	 *            不为null显示进度条
	 */
	private void _uploadNotProgress(String url, String name, File file) {

		// 这个是非ui线程回调，不可直接操作UI
		final ProgressRequestListener progressListener = new ProgressRequestListener() {
			@Override
			public void onRequestProgress(long bytesWrite, long contentLength, boolean done) {
				Log.e("TAG", "bytesWrite:" + bytesWrite);
				Log.e("TAG", "contentLength" + contentLength);
				Log.e("TAG", (100 * bytesWrite) / contentLength + " % done ");
				Log.e("TAG", "done:" + done);
				Log.e("TAG", "================================");
			}
		};

		// 构造上传请求，类似web表单
		RequestBody requestBody = new MultipartBuilder().type(MultipartBuilder.FORM).addFormDataPart("name", name).addFormDataPart("photo", file.getName(), RequestBody.create(null, file)).addPart(Headers.of("Content-Disposition", "form-data; name=\"another\";filename=\"another.dex\""), RequestBody.create(MediaType.parse("application/octet-stream"), file)).build();

		// 进行包装，使其支持进度回调
		final Request request = new Request.Builder().url(url).post(ProgressHelper.addProgressRequestListener(requestBody, progressListener)).build();
		// 开始请求
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Request request, IOException e) {
				Log.e("TAG", "error ", e);
			}

			@Override
			public void onResponse(Response response) throws IOException {
				Log.e("TAG", response.body().string());
			}
		});

	}

	// ---------------------------------------------公开方法-----------------------------------------------
	/**
	 * 异步查询
	 * 
	 * @param url
	 * @param callback
	 */
	public static void asyncGet(String url, Callback callback) {
		getInstance()._asyncGet(url, callback);
	}

	/**
	 * 同步get查询
	 * 
	 * @param url
	 * @throws IOException
	 */
	public static Response syncGet(String url) throws IOException {
		return getInstance()._syncGet(url);
	}

	public static void asyncPost(String url, Map<String, String> params, Map<String, File> files, Callback callback) throws IOException {
		getInstance()._asyncPost(url, params, files, callback);
	}

	/**
	 * 同步提交
	 * 
	 * @param url
	 * @param params
	 * @param files
	 * @return
	 * @throws IOException
	 */
	public static Response syncPost(String url, Map<String, String> params, Map<String, File> files) throws IOException {
		return getInstance()._syncPost(url, params, files);
	}

	/**
	 * 异步提交普通参数
	 * 
	 * @param url
	 * @param params
	 * @param callback
	 */
	public static void asyncPostParams(String url, Map<String, String> params, Callback callback) {
		getInstance()._asyncPostParams(url, params, callback);
	}

	/**
	 * 同步提交普通参数
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static Response syncPostParams(String url, Map<String, String> params) throws IOException {
		return getInstance()._syncPostParams(url, params);
	}

	// ===========================================公共方法=============================================================
	private String getFileName(String path) {
		int separatorIndex = path.lastIndexOf("/");
		return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
	}
	
	/**
	 * 不显示进度条的上传
	 * @param url
	 * @param name
	 * @param file
	 */
	public static void uploadFile(String url, String name, File file) {
		getInstance()._uploadNotProgress(url, name, file);
	}
	
	/**
	 * 显示进度条的上传
	 * @param url
	 * @param name
	 * @param file
	 */
	public static void uploadFile(String url, String name, File file, ProgressBar uploadProgress) {
		getInstance()._uploadShowProgress(url, name, file, uploadProgress);
	}
	
	/**
	 * 下载文件
	 * @param path
	 * @return
	 */
	public static void asyncDownloadFile(String url, String dir) {
		getInstance()._downloadNotProgeress(url, dir);
	}
	
	/**
	 * 下载文件
	 * @param path
	 * @return
	 */
	public static void asyncDownloadFile(String url, String dir, ProgressBar downloadProgress) {
		getInstance()._downloadShowProgeress(url, dir, downloadProgress);
	}
	/**
	 * 获取网络图片
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static Bitmap syncDownLoadImage(String url) throws IOException{
		InputStream is= getInstance()._syncGet(url).body().byteStream();
		Bitmap btmap=BitmapFactory.decodeStream(is);
		return btmap;
	}
	/**
	 * 加载网络图片
	 * @param url
	 * @param iv
	 */
	public static void displayImage(String url,final ImageView iv){
//		String imagename=url.substring(url.lastIndexOf("/"));
//		File file=new File(MApplication.imageCachePath+imagename);

		getInstance()._asyncGet(url, new Callback() {
			@Override
			public void onResponse(Response resp) throws IOException {
				 InputStream is= resp.body().byteStream();
				 if(is !=null){
					 BitmapDrawable bd=new BitmapDrawable(is);
					 iv.setImageDrawable(bd);
				 }else{
					 MApplication.showToastShort("未找到图片");
				 }
			}
			
			@Override
			public void onFailure(Request arg0, IOException arg1) {
				MApplication.showToastShort("图片加载失败！");
			}
		});
	
	}
}
