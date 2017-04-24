package com.ris.mobile.ecloud.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.ris.mobile.ecloud.R;
import com.ris.mobile.ecloud.log.CommonLog;
import com.ris.mobile.ecloud.log.LogFactory;
import com.ris.mobile.ecloud.object.RequestObject;

import com.ris.mobile.ecloud.util.OkHttpClientManager.Param;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * 
 * @author Mathew
 * 
 */
public class NetUtil {
	private static final String NOT_LOGIN = "notlogin";
	private static final String TAG = "NetUtil";
	private static final CommonLog log = LogFactory.createLog(TAG);
	private static Header[] headers = new BasicHeader[11];
	 //Product Model: ,,
	//android.os.Build.MODEL SCH-I939D
	//android.os.Build.VERSION.SDK 16
	//android.os.Build.VERSION.RELEASE 4.1.1

	
	
	 
	static {
		headers[0] = new BasicHeader("Model", android.os.Build.MODEL);
		headers[1] = new BasicHeader("Imei", "");
		headers[2] = new BasicHeader("Os", "Android");
		headers[3] = new BasicHeader("Osversion", android.os.Build.VERSION.RELEASE);
		headers[4] = new BasicHeader("Appversion", "");
		headers[5] = new BasicHeader("Phonenumber", "");
		headers[6] = new BasicHeader("Simserialnumber", "");
		headers[7] = new BasicHeader("Imsi", "");
		headers[8] = new BasicHeader("Iso", "");
		headers[9] = new BasicHeader("Networkoperator", "");
		headers[10] = new BasicHeader("Cookie", "");

	}

	public static Object post(RequestObject requestObject) {
		
		
		
		
		TelephonyManager tm = (TelephonyManager)requestObject.context.getSystemService(Context.TELEPHONY_SERVICE);
		PackageInfo packageInfo=CommonUtil.getSoftVersion(requestObject.context);
		
		headers[1]=new BasicHeader("Imei", tm.getDeviceId());
		if(packageInfo!=null)
		   headers[4] = new BasicHeader("Appversion", packageInfo.packageName);
		headers[5] = new BasicHeader("Phonenumber", tm.getLine1Number());
		headers[6] = new BasicHeader("Simserialnumber", tm.getSimSerialNumber());
		headers[7] = new BasicHeader("Imsi", tm.getSubscriberId());
		headers[8] = new BasicHeader("Iso", tm.getNetworkCountryIso());
		headers[9] = new BasicHeader("Networkoperator",tm.getNetworkOperator());
		 
		
		String url = requestObject.context.getString(R.string.app_host).concat(
				requestObject.context.getString(requestObject.requestUrl));
		log.d("Post " + url);
		BasicNameValuePair pair;
		Object obj = null;
		SharedPreferenceUtil sharedPreferenceUtil = new SharedPreferenceUtil(
				requestObject.context);
		String timestamp = new Long(System.currentTimeMillis()).toString();
		String token = sharedPreferenceUtil.getToken();
		String userId = sharedPreferenceUtil.getUserId();
		try {
			ArrayList<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
			pair = new BasicNameValuePair("timestamp", timestamp);
			pairList.add(pair);
			pair = new BasicNameValuePair("attach","Android"+"@"+ CommonUtil.trim(android.os.Build.VERSION.RELEASE)+"@"+ CommonUtil.trim(android.os.Build.MODEL));
			pairList.add(pair);
			
			
			pair = new BasicNameValuePair("userId", userId);
			pairList.add(pair);
			pair = new BasicNameValuePair("sign", Md5.digest("token=" + token
					+ "&timestamp=" + timestamp + "&userId=" + userId));
			pairList.add(pair);

			if (requestObject.requestDataMap != null) {
				HashMap<String, String> map = requestObject.requestDataMap;

				for (Map.Entry<String, String> entry : map.entrySet()) {

					if (entry.getKey().equals("userId"))
						continue;
					pair = new BasicNameValuePair(entry.getKey(),
							entry.getValue());
					pairList.add(pair);
				}

			}
			Param[] params = new Param[pairList.size()];
			Param param;
			for (int i = 0; i < pairList.size(); i++) {
				param = new Param();
				param.key = pairList.get(i).getName();
				param.value = pairList.get(i).getValue();
				params[i] = param;

			}
			String result = OkHttpClientManager.postAsString(url,headers, params);
			
			log.e(result);
			 
			obj = requestObject.jsonParser.parseJSON(result);
            if(obj==null)
            	 return requestObject.jsonParser.getConnectErrorObject();
			return obj;

		} catch (ClientProtocolException e) {

			log.e(e.getLocalizedMessage());
		} catch (IOException e) {

			log.e(e.getLocalizedMessage());
		}
		return null;

	}

	/*
	 * 
	 * 
	 * public static Object post(RequestObject requestObject) {
	 * 
	 * 
	 * DefaultHttpClient client = new DefaultHttpClient(); String url =
	 * requestObject
	 * .context.getString(R.string.app_host).concat(requestObject.context
	 * .getString(requestObject.requestUrl)); log.d( "Post " + url); HttpPost
	 * post = new HttpPost(url); post.setHeaders(headers); Object obj = null;
	 * BasicNameValuePair pair; SharedPreferenceUtil sharedPreferenceUtil=new
	 * SharedPreferenceUtil(requestObject.context); String timestamp=new
	 * Date(System.currentTimeMillis()).toLocaleString(); String
	 * token=sharedPreferenceUtil.getToken(); String userId=
	 * sharedPreferenceUtil.getUserId();
	 * 
	 * try { ArrayList<BasicNameValuePair> pairList = new
	 * ArrayList<BasicNameValuePair>(); pair = new
	 * BasicNameValuePair("timestamp",timestamp); pairList.add(pair); //pair =
	 * new BasicNameValuePair("token",token ); //pairList.add(pair); pair = new
	 * BasicNameValuePair("userId",userId); pairList.add(pair); pair = new
	 * BasicNameValuePair
	 * ("sign",Md5.digest("token="+token+"&timestamp="+timestamp
	 * +"&userId="+userId)); pairList.add(pair);
	 * 
	 * 
	 * 
	 * //token=TOKEN值&timestamp=格林威治毫秒数&userId=用户ID if
	 * (requestObject.requestDataMap != null) { HashMap<String, String> map =
	 * requestObject.requestDataMap;
	 * 
	 * for (Map.Entry<String, String> entry : map.entrySet()) { pair = new
	 * BasicNameValuePair(entry.getKey(), entry.getValue()); pairList.add(pair);
	 * }
	 * 
	 * } HttpEntity entity = new UrlEncodedFormEntity(pairList, "UTF-8");
	 * post.setEntity(entity); HttpResponse response = client.execute(post);
	 * 
	 * 
	 * log.e(response.getStatusLine().getStatusCode()); if
	 * (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	 * //setCookie(response); String result =
	 * EntityUtils.toString(response.getEntity(), "UTF-8"); log.e(result); try {
	 * if (invilidateLogin(result)) { return Status.Login; } obj =
	 * requestObject.jsonParser.parseJSON(result); } catch (JSONException e) {
	 * log.e( e.getLocalizedMessage() ); } return obj; } } catch
	 * (ClientProtocolException e) {
	 * 
	 * log.e( e.getLocalizedMessage() ); } catch (IOException e) {
	 * 
	 * log.e( e.getLocalizedMessage() ); } return null; }
	 */

	/**
	 * 添加Cookie
	 * 
	 * @param response
	 */
	private static void setCookie(HttpResponse response) {
		if (response.getHeaders("Set-Cookie").length > 0) {
			log.d(response.getHeaders("Set-Cookie")[0].getValue());
			headers[10] = new BasicHeader("Cookie",
					response.getHeaders("Set-Cookie")[0].getValue());
		}
	}

	/**
	 * 验证是否需要登录
	 * 
	 * @param result
	 * @return
	 * @throws JSONException
	 */
	private static boolean invilidateLogin(String result) throws JSONException {
		/*
		 * JSONObject jsonObject = new JSONObject(result); String responseCode =
		 * jsonObject.getString("response"); if (NOT_LOGIN.equals(responseCode))
		 * { return true; } return false;
		 */
		return false;
	}

	/**
	 * 
	 * @param vo
	 * @return
	 */
	public static Object get(RequestObject requestObject) {
		DefaultHttpClient client = new DefaultHttpClient();
		String url = requestObject.context.getString(R.string.app_host).concat(
				requestObject.context.getString(requestObject.requestUrl));
		log.d("Get " + url);
		HttpGet get = new HttpGet(url);
		get.setHeaders(headers);
		Object obj = null;
		try {

			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				setCookie(response);
				String result = EntityUtils.toString(response.getEntity(),
						"UTF-8");
				log.d(result);
				try {
					if (invilidateLogin(result)) {
						return Status.Login;
					}
					obj = requestObject.jsonParser.parseJSON(result);
				} catch (JSONException e) {
					log.e(e.getLocalizedMessage());
				}
				return obj;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得网络连接是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean hasNetwork(Context context) {
		ConnectivityManager con = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo workinfo = con.getActiveNetworkInfo();
		if (workinfo == null || !workinfo.isAvailable()) {
			// Toast.makeText(context, R.string.net_error,
			// Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	public static enum Status {
		Login
	}

	public static String getLocalIpAddress(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		// 判断wifi是否开启
		if (wifiManager.isWifiEnabled()) {
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress();
			return int2ip(ipAddress);

		} else {
			try {
				for (Enumeration<NetworkInterface> en = NetworkInterface
						.getNetworkInterfaces(); en.hasMoreElements();) {
					NetworkInterface intf = en.nextElement();
					for (Enumeration<InetAddress> enumIpAddr = intf
							.getInetAddresses(); enumIpAddr.hasMoreElements();) {
						InetAddress inetAddress = enumIpAddr.nextElement();
						if (!inetAddress.isLoopbackAddress()) {
							return inetAddress.getHostAddress().toString();
						}
					}
				}
			} catch (SocketException ex) {
				log.e(ex.toString());
			}
		}

		return null;
	}

	public static String GetNetIp() {
		URL infoUrl = null;
		InputStream inStream = null;
		try {
			// http://iframe.ip138.com/ic.asp
			// infoUrl = new URL("http://city.ip138.com/city0.asp");
			infoUrl = new URL("http://city.ip138.com/ip2city.asp");
			//
			URLConnection connection = infoUrl.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			int responseCode = httpConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				inStream = httpConnection.getInputStream();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inStream, "utf-8"));
				StringBuilder strber = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null)
					strber.append(line + "\n");
				inStream.close();
				// 从反馈的结果中提取出IP地址
				int start = strber.indexOf("[");
				int end = strber.indexOf("]", start + 1);
				line = strber.substring(start + 1, end);
				return line;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String int2ip(int ipInt) {
		StringBuilder sb = new StringBuilder();
		sb.append(ipInt & 0xFF).append(".");
		sb.append((ipInt >> 8) & 0xFF).append(".");
		sb.append((ipInt >> 16) & 0xFF).append(".");
		sb.append((ipInt >> 24) & 0xFF);
		return sb.toString();
	}
}
