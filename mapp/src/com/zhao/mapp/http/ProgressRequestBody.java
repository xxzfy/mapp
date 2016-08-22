package com.zhao.mapp.http;

import java.io.IOException;

import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.zhao.mapp.interfaces.ProgressRequestListener;

public class ProgressRequestBody extends RequestBody {
	//实际的待包装请求体
    private final RequestBody requestBody;
    //进度回调接口
    private final ProgressRequestListener progressListener;
    //包装完成的BufferedSink
    private BufferedSink bufferedSink;
    
	public ProgressRequestBody(RequestBody requestBody, ProgressRequestListener progressListener) {
		super();
		this.requestBody = requestBody;
		this.progressListener = progressListener;
	}

	@Override
	public MediaType contentType() {
		return requestBody.contentType();
	}
	@Override
	public long contentLength() throws IOException {
		return requestBody.contentLength();
	}
	@Override
	public void writeTo(BufferedSink sink) throws IOException {
		if(bufferedSink==null){
			bufferedSink=Okio.buffer(sink(sink));
		}

	}
	/**
	 * 写入回调进度接口
	 * @param sink
	 * @return
	 */
	private Sink sink(Sink sink){
		return new ForwardingSink(sink) {
			//当前写入字节数
            long bytesWritten = 0L;
            //总字节长度，避免多次调用contentLength()方法
            long contentLength = 0L;
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
            	super.write(source, byteCount);
            	if(contentLength==0){
            		contentLength=contentLength();
            	}
            	//增加当前写入的字节数
                bytesWritten += byteCount;
                //回调
                progressListener.onRequestProgress(bytesWritten, contentLength, bytesWritten == contentLength);
            }
			
		};
	}
}
