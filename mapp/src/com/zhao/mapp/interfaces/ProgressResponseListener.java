package com.zhao.mapp.interfaces;
/**
 * 定义下载进度接口
 * @author zhyp
 *
 */
public interface ProgressResponseListener {
	void onResponseProgress(long bytesRead, long contentLength, boolean done);
}
