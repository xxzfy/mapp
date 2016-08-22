package com.zhao.mapp.interfaces;
/**
 * 定义上传进度接口
 * @author zhyp
 *
 */
public interface ProgressRequestListener {
	void onRequestProgress(long bytesWritten, long contentLength, boolean done);
}
