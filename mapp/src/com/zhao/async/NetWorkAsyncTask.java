package com.zhao.async;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhao.mapp.model.TaskModel;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Handler;

public class NetWorkAsyncTask extends AsyncTask<String,Integer,LinkedHashMap<String, Object>> {
	private Activity mActivity;
	 //NETWORK_GET表示发送GET请求
    public static final String NETWORK_GET = "NETWORK_GET";
    //NETWORK_POST_KEY_VALUE表示用POST发送键值对数据
    public static final String NETWORK_POST_KEY_VALUE = "NETWORK_POST_KEY_VALUE";
    //NETWORK_POST_XML表示用POST发送XML数据
    public static final String NETWORK_POST_XML = "NETWORK_POST_XML";
    //NETWORK_POST_JSON表示用POST发送JSON数据
    public static final String NETWORK_POST_JSON = "NETWORK_POST_JSON";
    private Handler mHandler;
    private List<TaskModel> tasklist;
	public NetWorkAsyncTask(Activity activity,Handler handler,List<TaskModel> list) {
		mActivity=activity;
		mHandler=handler;
		tasklist=list;
	}
	@Override
	protected LinkedHashMap<String, Object> doInBackground(String... params) {
		LinkedHashMap<String, Object> result=new LinkedHashMap<String, Object>();
		String action=(String) params[0];
		String urladdr=params[1];
		URL url=null;//请求的url地址
		HttpURLConnection connection=null;
		String requestHeader=null;
		byte[] requestBody=null;
		String responseHeader=null;
		byte[] responseBody=null;
		
		try {
			if(NETWORK_GET.equals(action)){
				url=new URL(urladdr);
				connection=(HttpURLConnection) url.openConnection();
				 //HttpURLConnection默认就是用GET发送请求，所以下面的setRequestMethod可以省略
				connection.setRequestMethod("GET");
				 //HttpURLConnection默认也支持从服务端读取结果流，所以下面的setDoInput也可以省略
				connection.setDoInput(true);
				//用setRequestProperty方法设置一个自定义的请求头:action，由于后端判断
                connection.setRequestProperty("action", NETWORK_GET);
                //禁用网络缓存
                connection.setUseCaches(false);
                //获取请求头
                requestHeader = getReqeustHeader(connection);
                //在对各种参数配置完成后，通过调用connect方法建立TCP连接，但是并未真正获取数据
                //connection.connect()方法不必显式调用，当调用conn.getInputStream()方法时内部也会自动调用connect方法
                connection.connect();
                int len=0;
				byte[] buff=new byte[1024];
				ByteArrayOutputStream bos=new ByteArrayOutputStream();
				if(connection.getResponseCode()==200){
					InputStream is=connection.getInputStream();
					while((len=is.read(buff))>-1){
						bos.write(buff,0,len);
					}
					String text=bos.toString();
					JSONObject json=JSONObject.parseObject(text);
					JSONArray jsonarray= json.getJSONArray("list");
					tasklist= JSONArray.parseArray(jsonarray.toJSONString(), TaskModel.class);
					mHandler.sendEmptyMessage(1);
				}
			}
			else if(NETWORK_POST_KEY_VALUE.equals(action)){
				url=new URL(urladdr);
				connection=(HttpURLConnection) url.openConnection();
				//通过setRequestMethod将conn设置成POST方法
				connection.setRequestMethod("POST");
				//调用conn.setDoOutput()方法以显式开启请求体
				connection.setDoOutput(true);
				//用setRequestProperty方法设置一个自定义的请求头:action，由于后端判断
                connection.setRequestProperty("action", NETWORK_POST_KEY_VALUE);
                //获取请求头
                requestHeader=getReqeustHeader(connection);
                //获取connection的输出流
                OutputStream os = connection.getOutputStream();
                //获取两个键值对name=孙群和age=27的字节数组，将该字节数组作为请求体
                requestBody = new String("name=孙群&age=27").getBytes("UTF-8");
               //将请求体写入到conn的输出流中
                os.write(requestBody);
               //记得调用输出流的flush方法
                os.flush();
               //关闭输出流
                os.close();
              //当调用getInputStream方法时才真正将请求体数据上传至服务器
                InputStream is = connection.getInputStream();
              //获得响应体的字节数组
                responseBody = getBytesByInputStream(is);
              //获得响应头
                responseHeader = getResponseHeader(connection); 
			}else if (NETWORK_POST_XML.equals(action)) {
                //用POST发送XML数据
                url = new URL(urladdr);
                connection = (HttpURLConnection) url.openConnection();
                //通过setRequestMethod将connection设置成POST方法
                connection.setRequestMethod("POST");
                //调用connection.setDoOutput()方法以显式开启请求体
                connection.setDoOutput(true);
                //用setRequestProperty方法设置一个自定义的请求头:action，由于后端判断
                connection.setRequestProperty("action", NETWORK_POST_XML);
                //获取请求头
                requestHeader = getReqeustHeader(connection);
                //获取connection的输出流
                OutputStream os = connection.getOutputStream();
                //读取assets目录下的person.xml文件，将其字节数组作为请求体
                requestBody = getBytesFromAssets("person.xml");
                //将请求体写入到connection的输出流中
                os.write(requestBody);
                //记得调用输出流的flush方法
                os.flush();
                //关闭输出流
                os.close();
                //当调用getInputStream方法时才真正将请求体数据上传至服务器
                InputStream is = connection.getInputStream();
                //获得响应体的字节数组
                responseBody = getBytesByInputStream(is);
                //获得响应头
                responseHeader = getResponseHeader(connection);
            } else if (NETWORK_POST_JSON.equals(action)) {
                //用POST发送JSON数据
                url = new URL(urladdr);
                connection = (HttpURLConnection) url.openConnection();
                //通过setRequestMethod将connection设置成POST方法
                connection.setRequestMethod("POST");
                //调用connection.setDoOutput()方法以显式开启请求体
                connection.setDoOutput(true);
                //用setRequestProperty方法设置一个自定义的请求头:action，由于后端判断
                connection.setRequestProperty("action", NETWORK_POST_JSON);
                //获取请求头
                requestHeader = getReqeustHeader(connection);
                //获取connection的输出流
                OutputStream os = connection.getOutputStream();
                //读取assets目录下的person.json文件，将其字节数组作为请求体
                requestBody = getBytesFromAssets("person.json");
                //将请求体写入到connection的输出流中
                os.write(requestBody);
                //记得调用输出流的flush方法
                os.flush();
                //关闭输出流
                os.close();
                //当调用getInputStream方法时才真正将请求体数据上传至服务器
                InputStream is = connection.getInputStream();
                //获得响应体的字节数组
                responseBody = getBytesByInputStream(is);
                //获得响应头
                responseHeader = getResponseHeader(connection);
            }
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//断开连接
			connection.disconnect();
		}
		 result.put("url", url.toString());
         result.put("action", action);
         result.put("requestHeader", requestHeader);
         result.put("requestBody", requestBody);
         result.put("responseHeader", responseHeader);
         result.put("responseBody", responseBody);
         return result;
		
	}
	@Override
	protected void onPostExecute(LinkedHashMap<String, Object> result) {
		super.onPostExecute(result);
		String url = (String)result.get("url");//请求的URL地址
        String action = (String) result.get("action");//http请求的操作类型
        String requestHeader = (String) result.get("requestHeader");//请求头
        byte[] requestBody = (byte[]) result.get("requestBody");//请求体
        String responseHeader = (String) result.get("responseHeader");//响应头
        byte[] responseBody = (byte[]) result.get("responseBody");//响应体

		
		
	}
	
	//读取请求头
    private String getReqeustHeader(HttpURLConnection conn) {
        //https://github.com/square/okhttp/blob/master/okhttp-urlconnection/src/main/java/okhttp3/internal/huc/HttpURLConnectionImpl.java
        Map<String,List<String>> requestHeaderMap = conn.getRequestProperties();
        Iterator<String> requestHeaderIterator = requestHeaderMap.keySet().iterator();
        StringBuilder sbRequestHeader = new StringBuilder();
        while (requestHeaderIterator.hasNext()) {
            String requestHeaderKey = requestHeaderIterator.next();
            String requestHeaderValue = conn.getRequestProperty(requestHeaderKey);
            sbRequestHeader.append(requestHeaderKey);
            sbRequestHeader.append(":");
            sbRequestHeader.append(requestHeaderValue);
            sbRequestHeader.append("\n");
        }
        return sbRequestHeader.toString();
    }

    //读取响应头
    private String getResponseHeader(HttpURLConnection conn) {
        Map<String,List<String>> responseHeaderMap = conn.getHeaderFields();
        int size = responseHeaderMap.size();
        StringBuilder sbResponseHeader = new StringBuilder();
        for(int i = 0; i < size; i++){
            String responseHeaderKey = conn.getHeaderFieldKey(i);
            String responseHeaderValue = conn.getHeaderField(i);
            sbResponseHeader.append(responseHeaderKey);
            sbResponseHeader.append(":");
            sbResponseHeader.append(responseHeaderValue);
            sbResponseHeader.append("\n");
        }
        return sbResponseHeader.toString();
    }

    //根据字节数组构建UTF-8字符串
    private String getStringByBytes(byte[] bytes) {
        String str = "";
        try {
            str = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    //从InputStream中读取数据，转换成byte数组，最后关闭InputStream
    private byte[] getBytesByInputStream(InputStream is) {
        byte[] bytes = null;
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        byte[] buffer = new byte[1024 * 8];
        int length = 0;
        try {
            while ((length = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, length);
            }
            bos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

    //根据文件名，从asserts目录中读取文件的字节数组
    private byte[] getBytesFromAssets(String fileName){
        byte[] bytes = null;
        AssetManager assetManager = mActivity.getAssets();
        InputStream is = null;
        try{
            is = assetManager.open(fileName);
            bytes = getBytesByInputStream(is);
        }catch (IOException e){
            e.printStackTrace();
        }
        return bytes;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
    	super.onProgressUpdate(values);
    }
	
}
