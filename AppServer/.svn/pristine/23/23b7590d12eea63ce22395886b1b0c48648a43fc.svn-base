package com.rising.appserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.Cookie; 

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient; 
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.rising.appserver.common.CommonUtil;
import com.rising.appserver.factory.HttpFactory;
public class UrlEncode{
	public static void main(String [] args){
		//构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		//创建GET方法的实例
		GetMethod getMethod = new GetMethod("http://pay.qq.com/ipay/index.shtml?n=3&c=ltmclub,cjclub&aid=pay.qqvip&ch=qdqb,kj");
		//使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
		new DefaultHttpMethodRetryHandler());
		//定义一个输入流
		InputStream ins = null;
		//定义文件流
		BufferedReader br =null;
		try {
		  //执行getMethod
		  int statusCode = httpClient.executeMethod(getMethod);
		  if (statusCode != HttpStatus.SC_OK) {
		    System.err.println("方法失败: "
		       + getMethod.getStatusLine());
		   }
		   
		  //使用getResponseBodyAsStream读取页面内容，这个方法对于目标地址中有大量数据需要传输是最佳的。
		  ins = getMethod.getResponseBodyAsStream();
		  String charset = getMethod.getResponseCharSet();
		  //System.out.println(charset);
		  if(charset.toUpperCase().equals("ISO-8859-1")){
		        charset = "gbk";
		  }
		  charset="utf-8";
		  //按服务器编码字符集构建文件流，这里的CHARSET要根据实际情况设置
		  br = new BufferedReader(new InputStreamReader(ins,getMethod.getResponseCharSet()));
		  StringBuffer sbf = new StringBuffer();
		  String line = null;
		    while ((line = br.readLine()) != null)
		    {
		      sbf.append(line);
		      sbf.append("\n");
		    }
		  String result = new String(sbf.toString().getBytes(getMethod.getResponseCharSet()),charset);
		  //输出内容
		  System.out.println(result);
		  //服务器编码
		   System.out.println("服务器编码是："+getMethod.getResponseCharSet());
		} catch (HttpException e) {
		  //发生致命的异常，可能是协议不对或者返回的内容有问题
		  System.out.println("请检查您所提供的HTTP地址！");
		    e.printStackTrace();
		} catch (IOException e) {
		  //发生网络异常
		   e.printStackTrace();
		} finally {
		  //关闭流，释放连接    
		} 
	}
}