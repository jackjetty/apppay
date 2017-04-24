package com.rising.appserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.Cookie;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;

import com.rising.appserver.common.CommonUtil;

public class JsMode{
	
	 
	public static void main(String[] args) throws Exception{
		 
		
		/*String result=\"_unipay_callback({\\"token\\" : \\"E3883B5D9930B7B4D2A7271C44929C0711407\\",\\"kj\\" : \\"0\\",\\"cft\\" : \\"0\\",\\"qdqb\\" : \\"1\\",\\"m\\" : \\"qdqb\\",\\"tenpay\\" : \\"0\\",\\"history\\" : \\"\\",\\"wcpay\\" : \\"0\\",\\"towcpay\\" : \\"0\\",\\"mobile_qq_status\\" : 2,\\"mobile_qq_os\\" : \\"\\",\\"mobile_qq_ver\\" : \\"\\",\\"ret\\" : 0})\";
		
		Pattern pattern;
		Matcher matcher; 
		pattern=Pattern.compile(\"^(.+)(\\"token\\" : \\")(.+)(\\",\\"kj\\")(.+)$\");
    	matcher = pattern.matcher(result ); 
		if(matcher.find()){  
			System.out.println(CommonUtil.trim(matcher.group(3)));   
		}  
		*/
		String result= "http://pay.qq.com/ipay/callback.shtml?c=16FA9176EFC73A002E620FCAED6FC77Fserial%3D%26service%5Fcode%3D%2DLOLDQ%26service%5Fname%3D%25E8%258B%25B1%25E9%259B%2584%25E8%2581%2594%25E7%259B%259F%25E7%2582%25B9%25E5%2588%25B8%26timetype%3D0%26open%5Fmonth%3D100%26pre%5Fopen%5Fmonth%3D100%26result%3D0%26autopay%3D0%26pay%5Fuin%3D121437479%26user%5Fnum%3D729172380%26pay%5Fmode%3D2%26serve%3D1%26lover%5Fnum%3D0%26phonecard%5Fserial%3D%26qlyz%5Fuin%3D0%26open%5Fdetail%3D%26pay%5Fway%3D2%26phonecard%5Fpay%5Fway%3D&k=66&t=1440579859";
		 
		 result=URLDecoder.decode(result,"utf-8") ;
		 result=URLDecoder.decode(result,"utf-8");
		 //System.out.println(System.currentTimeMillis());	
		 System.out.println(javax.mail.internet.MimeUtility.encodeText("冉思科技"));
		/* Pattern pattern;
			Matcher matcher; 
			pattern=Pattern.compile("^.+(\\?)(.+)(\";).*$");
			matcher=pattern.matcher(result);  
			if(matcher.find()){  
				result=CommonUtil.trim(matcher.group(2));   
			} 
	
		 HashMap<String, Object> map = new HashMap<String, Object>();
			Pattern responsePtn = Pattern.compile("(&)"); 
			String[] strs = responsePtn.split(result); 
			
			 
			Pattern cellPtn = Pattern.compile("^(.+)=(.*?)$");  
			for (String cellRst:strs) { 
				  matcher = cellPtn.matcher(cellRst ); 
				 
				if(matcher.find()){  
					map.put(CommonUtil.trim(matcher.group(1)) , CommonUtil.trim(matcher.group(2)));
				     System.out.println(CommonUtil.trim(matcher.group(1)));    
				     System.out.println(CommonUtil.trim(matcher.group(2))); 
				}     
			
			} 
		 
		String result="{ \"resultcode\": 0, \"resultinfo\": { \"qb_balance\": \"103.00\", \"qd_balance\": \"0.0\" } }";
		Pattern pattern;
		Matcher matcher; 
		pattern=Pattern.compile("^.+(\"qb_balance\": \")(\\d+)\\D+.*$");
		matcher=pattern.matcher(result);  
		if(matcher.find()){  
			result=CommonUtil.trim(matcher.group(2));   
		} 
		System.out.println(result);	*/
		     
	 
		//4E6AA27BE6E4B23D1A38A8CC3A9B49F8serial=&service_code=LTMCLUB&service_name=%E4%BC%9A%E5%91%98%E5%8C%85%E6%9C%88&timetype=0&open_month=1&pre_open_month=1&result=0&autopay=1&pay_uin=121437479&user_num=121437479&pay_mode=1&serve=1&lover_num=0&phonecard_serial=&qlyz_uin=0&open_detail=&pay_way=2&phonecard_pay_way=
		
		
	 //String tt=URLDecoder.decode("%7B%22respCode%22%3A0%2C%22qqRefundBatchHttpUrlList%22%3A%5B%22http%3A%2F%2F115.239.134.175%3A80%2FAppServer%2FqqReback%2FqqRefund%3Frefund_date%5Cu003d2015-08-22+16%3A35%3A32%5Cu0026batch_no%5Cu003d20150822163512%5Cu0026batch_num%5Cu003d1%5Cu0026detail_data%5Cu003d2015082200001000540063157229%5E9.00%5E%E5%85%85%E5%80%BC%E5%A4%B1%E8%B4%A5%22%2C%22http%3A%2F%2F115.239.134.175%3A80%2FAppServer%2FqqReback%2FqqRefund%3Frefund_date%5Cu003d2015-08-22+16%3A27%3A47%5Cu0026batch_no%5Cu003d20150822162741%5Cu0026batch_num%5Cu003d1%5Cu0026detail_data%5Cu003d2015081900001000540062956297%5E0.01%5E%E5%85%85%E5%80%BC%E5%A4%B1%E8%B4%A5%22%5D%2C%22respInfo%22%3A%22%22%7D","utf-8") ;
 //tt=URLDecoder.decode(tt,"utf-8") ;
	 //tt= tt.replace("\\u00", "%") ;
	// System.out.println(tt);
		String tt="#";
		tt=URLEncoder.encode(tt);
		System.out.println(tt);
	 //
	 //tt= tt.replace("\\u0026", "&") ;
	 
	
				 
				 /*String header=\"Set-Cookie=ptui_identifier=000D8CBFD13E2D4F0C5C40F3D8E461CFD6F198159959A6694A4365FB; PATH=/; DOMAIN=ui.ptlogin2.qq.com;\";
		header=CommonUtil.trim(header);
		
		 
		Pattern pattern;
		Matcher matcher; 
		pattern=Pattern.compile(\"^(Set-Cookie=).+$\");
		matcher=pattern.matcher(header); 
	    if(matcher.matches()){
	    	pattern=Pattern.compile(\"^(Set-Cookie=)(.+)(=)(.+)(; PATH=)(.+)(; DOMAIN=)(.+);$\");
	    	matcher = pattern.matcher(header ); 
			if(matcher.find()){  
				System.out.println(CommonUtil.trim(matcher.group(2)));  
				System.out.println(CommonUtil.trim(matcher.group(4)));  
				System.out.println(CommonUtil.trim(matcher.group(6))); 
				System.out.println(CommonUtil.trim(matcher.group(8))); 
			}  
	    }*/
		//Random rand=new Random();
		//System.out.println(rand.nextDouble());
		
		
		
	   }
	
	 
}