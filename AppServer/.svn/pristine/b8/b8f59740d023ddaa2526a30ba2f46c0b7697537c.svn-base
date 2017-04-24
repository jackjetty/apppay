package com.rising.general.common;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service("gBaseService")
public class BaseService {
	Log log = LogFactory.getLog(BaseService.class);

	public String gBaseAddress;

	

	public String getgBaseAddress() {
		return gBaseAddress;
	}

	@Value("#{propertiesReader[gBaseAddress]}")
	public void setgBaseAddress(String gBaseAddress) {
		this.gBaseAddress = gBaseAddress;
	}

	
	

	protected String redirectToMoblilePayMentService(String requestUrl,
			String parameter, String ipAddress) {
		String responseGson = null;
		URL url;
		try {
			url = new URL(gBaseAddress + requestUrl+"?ipAddress="+URLEncoder.encode(ipAddress, "UTF-8"));
			// 打开连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置提交方式
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 配置本次连接的Content-Type
			conn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
			// 维持长连接
			conn.setRequestProperty("Connection", "Keep-Alive");
			// 设置浏览器编码
			conn.setRequestProperty("Charset", "UTF-8");
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			// 将请求参数数据向服务器端发送
			out.write(parameter);
			out.flush();
			out.close();
			if (conn.getResponseCode() == 200) {
				// 获得服务器端输出流
				InputStream is = conn.getInputStream();
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					bos.write(buffer, 0, len);
				}
				is.close();
				responseGson = new String(bos.toByteArray());
			} else {
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("respCode", -202);
				result.put("respInfo", "移动支付服务出现未处理异常！");
				responseGson = new Gson().toJson(result);
				log.error("请求\"" + requestUrl + "\"时MobilePayMent内部服务出错！");
			}
		} catch (MalformedURLException e) {
			log.error("redirectToMoblilePayMentService()->" + e.toString());
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("respCode", -203);
			result.put("respInfo", "连接移动支付服务失败！");
			responseGson = new Gson().toJson(result);
		} catch (IOException e) {
			log.error("redirectToMoblilePayMentService()->" + e.toString());
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("respCode", -203);
			result.put("respInfo", "连接移动支付服务失败！");
			responseGson = new Gson().toJson(result);
		}
		return responseGson;
	}

	protected BufferedImage redirectToMoblilePayMentService2(
			String getImageURL2, String parameter,
			HashMap<String, Object> result) {
		URL url;
		try {
			url = new URL(gBaseAddress + getImageURL2);
			// 打开连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置提交方式
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 配置本次连接的Content-Type
			conn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
			// 维持长连接
			conn.setRequestProperty("Connection", "Keep-Alive");
			// 设置浏览器编码
			conn.setRequestProperty("Charset", "UTF-8");
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			// 将请求参数数据向服务器端发送
			out.write(parameter);
			out.flush();
			out.close();
			if (conn.getResponseCode() == 200) {
				// 获得服务器端输出流
				InputStream is = conn.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				BufferedImage image = ImageIO.read(bis);
				return image;
			} else {
				result.put("respCode", -202);
				result.put("respInfo", "移动支付服务出现未处理异常！");
				log.error("请求\"" + getImageURL2 + "\"时MobilePayMent内部服务出错！");
				return null;
			}
		} catch (MalformedURLException e) {
			log.error("redirectToMoblilePayMentService()->" + e.toString());
			result.put("respCode", -203);
			result.put("respInfo", "连接移动支付服务失败！");
			return null;
		} catch (IOException e) {
			log.error("redirectToMoblilePayMentService()->" + e.toString());
			result.put("respCode", -203);
			result.put("respInfo", "连接移动支付服务失败！");
			return null;
		}
	}

	protected byte[] redirectToMoblilePayMentService3(String getImageURL2,
			String parameter, HashMap<String, Object> result) {
		URL url;
		try {
			url = new URL(gBaseAddress + getImageURL2);
			// 打开连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置提交方式
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 配置本次连接的Content-Type
			conn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
			// 维持长连接
			conn.setRequestProperty("Connection", "Keep-Alive");
			// 设置浏览器编码
			conn.setRequestProperty("Charset", "UTF-8");
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			// 将请求参数数据向服务器端发送
			out.write(parameter);
			out.flush();
			out.close();
			if (conn.getResponseCode() == 200) {
				// 获得服务器端输出流
				InputStream is = conn.getInputStream();
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					bos.write(buffer, 0, len);
				}
				is.close();
				return bos.toByteArray();
			} else {
				log.error("请求\"" + getImageURL2 + "\"时MobilePayMent内部服务出错！");
				return null;
			}
		} catch (MalformedURLException e) {
			log.error("redirectToMoblilePayMentService()->" + e.toString());
			return null;
		} catch (IOException e) {
			log.error("redirectToMoblilePayMentService()->" + e.toString());
			return null;
		}
	}
}
