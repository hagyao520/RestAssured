package com.sales.webapi.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnectionUtil {	
	public static HttpURLConnection getConnection(String url){
		HttpURLConnection connection = null;
		try {
			// 打开和URL之间的连接
			URL Url = new URL(url);
			connection = (HttpURLConnection) Url.openConnection();
			 // 设置通用的请求属性
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(50000);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");
//			connection.setRequestProperty("Content-Type", "application/xml");
			connection.setRequestProperty("Charset", "utf-8");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			System.out.println(connection);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return connection;
	}
}
