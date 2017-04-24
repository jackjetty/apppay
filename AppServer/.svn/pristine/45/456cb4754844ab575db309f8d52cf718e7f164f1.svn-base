package com.rising.appserver.common;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map; 
public class HttpUtil {
 
	
	public static String submitData(String actionUrl){
		return submitData(actionUrl,new HashMap<String, String>(),"utf-8");
	}
	
	public static String submitData(String actionUrl, Map<String, String> params){
		return submitData(actionUrl,params,"utf-8");
	}
	
	public static String submitData(String actionUrl, Map<String, String> params,String encode){
		return submitData(actionUrl,params,encode,3000,30*1000);
	}

	public static String submitData(String actionUrl, Map<String, String> params,
			String encode, int connTimeout, int readTimeout) {
		String lowerActionUrl = actionUrl.toLowerCase();
		if (!lowerActionUrl.startsWith("http://")) {
			lowerActionUrl = "http://" + lowerActionUrl;
		} 
		StringBuffer postData = getRequestData(params, encode);
		

		try {
			byte[] data = null;
			if (postData != null && !"".equalsIgnoreCase(postData.toString())) {
				data = getRequestData(params, encode).toString().getBytes(encode); 
			}
			URL url = new URL(actionUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setConnectTimeout(connTimeout); 
			httpURLConnection.setReadTimeout(readTimeout);
			httpURLConnection.setDoInput(true);  

			if (data != null) {
				httpURLConnection.setDoOutput(true); 
				httpURLConnection.setRequestMethod("POST");  
				httpURLConnection.setUseCaches(false);  
 				httpURLConnection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
			} else {
				httpURLConnection.setRequestMethod("GET");
			}
			if (data != null) {
				 
				httpURLConnection.setRequestProperty("Content-Length",
						String.valueOf(data.length));
 				OutputStream outputStream = httpURLConnection.getOutputStream();
				outputStream.write(data);
			}
			int response = httpURLConnection.getResponseCode();  
			if (response == HttpURLConnection.HTTP_OK) {
				InputStream inptStream = httpURLConnection.getInputStream();
				return dealResponseResult(inptStream, encode);  
			}
		} catch (IOException e) {
			throw new RuntimeException( e);
		}
		return "";
	}

	public static StringBuffer getRequestData(Map<String, String> params,
			String encode) {
		if (params == null || params.size() == 0) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();  
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if(entry.getValue() == null){
				continue;
			}
			try {
				stringBuffer.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue(), encode))
						.append("&");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		stringBuffer.deleteCharAt(stringBuffer.length() - 1); 
		return stringBuffer;
	}

	public static String dealResponseResult(InputStream inputStream,
			String encode) {
		String resultData = null; 
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		try {
			while ((len = inputStream.read(data)) != -1) {
				byteArrayOutputStream.write(data, 0, len);
			}
		} catch (IOException e) {
			throw new RuntimeException( e);
		}
		try {
			resultData = new String(byteArrayOutputStream.toByteArray(), encode);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return resultData;
	}

}