package com.ucai.elts.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.google.gson.Gson;

public class HttpUtils {
	public enum Method{
		GET,POST
	}
	private static String UTF_8="utf-8";
	
	public static HttpClient mClient;
	public static HttpEntity getEntity(String url,ArrayList<BasicNameValuePair> params,Method method) throws ClientProtocolException, IOException{
		mClient=new DefaultHttpClient();
		HttpUriRequest request=null;
		StringBuilder sb=new StringBuilder(url);
		switch (method) {
		case GET:
			if(null!=params && !params.isEmpty()){
				sb.append("?");
				for(BasicNameValuePair param:params){
					sb.append(param.getName())
					  .append("=")
					  .append(URLEncoder.encode(param.getValue()))
					  .append("&");
				}
				sb.deleteCharAt(sb.length()-1);
			}
			request=new HttpGet(sb.toString());
			break;
		case POST:
			request=new HttpPost(url);
			if(null!=params && !params.isEmpty()){
				UrlEncodedFormEntity reqEntity=new UrlEncodedFormEntity(params, UTF_8);
				((HttpPost)request).setEntity(reqEntity);
			}
			break;
		}
		mClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		mClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 3000);
		HttpResponse response = mClient.execute(request);
		if(response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK){
			return null;
		}
		return response.getEntity();
	}
	
	public static InputStream getInputStream(String url,ArrayList<BasicNameValuePair> params,Method method) throws IllegalStateException, ClientProtocolException, IOException{
		return getEntity(url, params, method).getContent();
	}
	
	public static void closeClient(){
		if(mClient!=null){
			mClient.getConnectionManager().shutdown();
			mClient=null;
		}
	}
	
	/**
    测试网络连接
	*/
  public static boolean isNetworkConnected(Context context){
		if(context==null){
			return false;
		}
		ConnectivityManager manager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if(networkInfo!=null){
			return networkInfo.isAvailable();
		}else{
			Toast.makeText(context, "网络连接失败", Toast.LENGTH_LONG).show();
			return false;
		}
	}
	
	public static <T> T parseJson(String url,Class<?> cls) throws Exception{
		HttpEntity entity = HttpUtils.getEntity(url, null, HttpUtils.Method.GET);
		String strJson = EntityUtils.toString(entity, "utf-8");
		Gson gson=new Gson();
		T t = (T) gson.fromJson(strJson, cls);
		return t;
	}
}
