package com.rising.appserver.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import javax.servlet.http.HttpServletRequest;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.rmi.*;
import java.util.Map; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import javax.xml.namespace.QName; 
import javax.xml.rpc.ServiceException;  

import org.apache.axis.client.Call; 
import org.apache.axis.utils.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils; 
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.params.HttpClientParams;

import com.rising.appserver.common.BaseService;
import com.rising.appserver.common.CommonUtil; 
import com.rising.appserver.common.XmlUtil;
import com.rising.appserver.dao.QQCookieStateDao;
import com.rising.appserver.factory.HttpFactory;
import com.rising.appserver.pojo.TbQQCookieState;
import com.rising.appserver.pojo.TbQQCookieStateId;
@Service("qqPayService")
public class QQPayService{ 
	
	
	public final static String USER_AGENT_H = "User-Agent";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
	public final static String UTF_8 = "UTF-8";
	
	private HttpClient client;
	private String qqNumber;
	private String qqPassword;
	private Pattern pattern;
	private Matcher matcher;
	private String login_sig; 
	private String pt_verifysession_v1;
	private String verifycode;
	private String token;
	private String cookie_uin;
	private String cookie_skey;
	
	private int qbBalance;
	 
	
	private HashMap<String,Object>  resultMap; 
	
	@Autowired 
	private QQCookieStateDao qqCookieStateDao; 
	
	public HashMap<String,Object> getQQBalance(String qqNumber,String qqPassword){
		this.qqNumber=CommonUtil.trim(qqNumber);
		this.qqPassword=CommonUtil.trim(qqPassword); 
		this.qbBalance=0; 
	    resultMap=new HashMap<String,Object> ();
	    resultMap.put("resCode", 0);
	    resultMap.put("resInfo", "操作成功！");
		 
		if(this.qqNumber.equals("")
				||this.qqPassword.equals("")){ 
			resultMap.put("resCode", 50);
			resultMap.put("resInfo", "参数不完整！");
			return resultMap; 
		}
		 HttpFactory http = HttpFactory.getInstance();
		 client=http.getClient();
		 //client = new HttpClient();  
		HttpClientParams params = client.getParams();
		params.setContentCharset("utf-8");
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		client.getParams().setParameter("http.protocol.single-cookie-header",
				true);  
		if(!qqLoad()){
			
			return resultMap;
		}  
		return resultMap;
	}
	
	public HashMap<String,Object>  payQQService(String qqNumber,
			String qqPassword,String targetQQNum,String serviceCode,String openAid,String openMonth){
		
		this.qqNumber=CommonUtil.trim(qqNumber);
		this.qqPassword=CommonUtil.trim(qqPassword); 
		this.qbBalance=0; 
		targetQQNum=CommonUtil.trim(targetQQNum); 
		serviceCode=CommonUtil.trim(serviceCode);
		openAid=CommonUtil.trim(openAid);
		openMonth=CommonUtil.trim(openMonth); 
		 
	    resultMap=new HashMap<String,Object> ();
	    resultMap.put("resCode", 0);
	    resultMap.put("resInfo", "操作成功！");
		 
		if(this.qqNumber.equals("")
				||this.qqPassword.equals("")
				||targetQQNum.equals("")
				||serviceCode.equals("")
				||openAid.equals("")
				||openMonth.equals("")){ 
			resultMap.put("resCode", 50);
			resultMap.put("resInfo", "参数不完整！");
			return resultMap; 
		}
		HttpFactory http = HttpFactory.getInstance();
		client = http.getClient(); 
		//client = new HttpClient();  
		HttpClientParams params = client.getParams();
		params.setContentCharset("utf-8");
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		client.getParams().setParameter("http.protocol.single-cookie-header",
				true); 
		 
		
		if(!qqLoad()){
			return resultMap;
		} 
		/*
		if(this.qbBalance<CommonUtil.castInt(openMonth)*10){
			resultMap.put("resCode", 51);
			resultMap.put("resInfo", "余额不足");
			return resultMap; 
			
		} */
		if(!goPayHome() ){
			return resultMap;
		} 
		if(!get_pay_info(  serviceCode)){
			return resultMap;
		}
		if(CommonUtil.castInt(CommonUtil.trim(resultMap.get("resCode")))!=0)
			 return resultMap;
		
		if(!uniform_pay(  serviceCode,  openAid,  targetQQNum,  openMonth)){
			return resultMap;
		}
		 
		return resultMap;
		
	}
	
	
	public HashMap<String,Object>  payQQGame(String qqNumber,
			String qqPassword,String targetQQNum, String serviceCode,String openAid,String gameZone,String openMonth){
		
		this.qqNumber=CommonUtil.trim(qqNumber);
		this.qqPassword=CommonUtil.trim(qqPassword); 
		this.qbBalance=0; 
		targetQQNum=CommonUtil.trim(targetQQNum); 
		serviceCode=CommonUtil.trim(serviceCode);
		openAid=CommonUtil.trim(openAid);
		openMonth=CommonUtil.trim(openMonth); 
		gameZone=CommonUtil.trim(gameZone); 
		 
	    resultMap=new HashMap<String,Object> ();
	    resultMap.put("resCode", 0);
	    resultMap.put("resInfo", "操作成功！");
		 
		if(this.qqNumber.equals("")
				||this.qqPassword.equals("")
				||targetQQNum.equals("")
				||serviceCode.equals("")
				||gameZone.equals("")
				||openAid.equals("")
				||openMonth.equals("")){ 
			resultMap.put("resCode", 50);
			resultMap.put("resInfo", "参数不完整！");
			return resultMap; 
		}
		HttpFactory http = HttpFactory.getInstance();
		client = http.getClient(); 
		//client = new HttpClient();  
		 
		HttpClientParams params = client.getParams();
		params.setContentCharset("utf-8");
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		client.getParams().setParameter("http.protocol.single-cookie-header",
				true); 
		 
		
		if(!qqLoad()){
			((SimpleHttpConnectionManager)client.getHttpConnectionManager()).closeIdleConnections(60000);  
			return resultMap;
		}  
		/*
		if(this.qbBalance<CommonUtil.castInt(openMonth)*10){
			resultMap.put("resCode", 51);
			resultMap.put("resInfo", "余额不足");
			return resultMap;  
		} */
		
		
		
		if(!goPayHome() ){
			return resultMap;
		} 
		if(!get_pay_info(  serviceCode)){
			return resultMap;
		} 
		if(CommonUtil.castInt(CommonUtil.trim(resultMap.get("resCode")))!=0)
			 return resultMap;
		
		if(!uniform_pay(  serviceCode,  openAid, gameZone, targetQQNum,  openMonth)){
			return resultMap;
		} 
		return resultMap;
		
	}
	
	public boolean clear(){
		String hql="  from  TbQQCookieState where id.qqNumber=?  "; 
		List <TbQQCookieState>  qqCookieStateList=qqCookieStateDao.findByHQL(hql, qqNumber); 
		for(TbQQCookieState tbQQCookieState:qqCookieStateList){ 
			qqCookieStateDao.delete(tbQQCookieState);
		}  
		return true;
	}
	
	private boolean qqLoad(){
		String hql="  from  TbQQCookieState where id.qqNumber=?  ";  
		
		client.getState().clearCookies(); 
		List <TbQQCookieState> qqCookieStateList=qqCookieStateDao.findByHQL(hql, qqNumber); 
		for(TbQQCookieState tbQQCookieState:qqCookieStateList){
		 
			 client.getState().addCookie(new Cookie(tbQQCookieState.getCkDomain(), 
					 tbQQCookieState.getId().getCkName(), 
					 tbQQCookieState.getCkValue(),
					 tbQQCookieState.getCkPath(),
					  tbQQCookieState.getCkExpires() 
					 ,tbQQCookieState.getCkSecure())); 
		} 
		
		
		if(balance_query_sortflow()){
			return true;
		}
		client.getState().clearCookies(); 
		clear();
		this.login_sig = ""; 
		if(!loadPage()){
			return false;
		}
		
		try {
	        Thread.sleep(20); 
		} catch (InterruptedException e) {
		        e.printStackTrace();
		}
		
		if(!check()){
			return false;
		}
		try {
	        Thread.sleep(20); 
		} catch (InterruptedException e) {
		        e.printStackTrace();
		}
		
		if(!login()){
			return false;
		} 
		try {
	        Thread.sleep(20); 
		} catch (InterruptedException e) {
		        e.printStackTrace();
		}
		 
		
		TbQQCookieState tbQQCookieState ;
		TbQQCookieStateId tbQQCookieStateId ; 
		Calendar cal = Calendar.getInstance(); 
	    Date curDate=cal.getTime();  
		for (Cookie cookie : client.getState().getCookies()) {
			tbQQCookieState=new TbQQCookieState();
			tbQQCookieStateId=new TbQQCookieStateId();
			tbQQCookieStateId.setQqNumber(qqNumber);
			tbQQCookieStateId.setCkName(cookie.getName());
			 
			tbQQCookieState.setId(tbQQCookieStateId);
			tbQQCookieState.setCkDomain(cookie.getDomain());
			if(cookie.getExpiryDate()!=null)
			        tbQQCookieState.setCkExpires(new Timestamp(cookie.getExpiryDate().getTime()));
			tbQQCookieState.setCkPath(cookie.getPath());
			tbQQCookieState.setCkSecure(cookie.getSecure());
			tbQQCookieState.setCkValue(cookie.getValue());
			tbQQCookieState.setLastDate(new Timestamp(curDate.getTime()));
			tbQQCookieState.setRemark(""); 
			if(qqCookieStateDao.findById(tbQQCookieStateId)==null)
				qqCookieStateDao.save(tbQQCookieState); 
			else
				qqCookieStateDao.update(tbQQCookieState);  
			
		}    
		
		
		return true;
		
		
	}
	
	
	
	
	
	
	private boolean loadPage() {
		StringBuffer urlBuffer = new StringBuffer("");
		urlBuffer.append("http://ui.ptlogin2.qq.com/cgi-bin/login?appid=11000101&f_url=loginerroralert&link_target=top&ep=http%3A//pay.qq.com/cgi-bin/login/qqacctlogin.cgi&s_url=http%3A//pay.qq.com/&qlogin_jumpname=payjump&qlogin_param=url%3Dhttp%253A//pay.qq.com");
		try {
			GetMethod get = new GetMethod(urlBuffer.toString());
			get.setRequestHeader("Referer", "http://ui.ptlogin2.qq.com");
			get.setRequestHeader(USER_AGENT_H, USER_AGENT);
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				String result = get.getResponseBodyAsString(); 
				for (Cookie c : client.getState().getCookies()) {
					if (c.getName().equalsIgnoreCase("pt_login_sig")) {
						this.login_sig = CommonUtil.trim(c.getValue());
					} 
				} 
			}
			
		} catch (Exception e) { 
			resultMap.put("resCode", 60);
			resultMap.put("resInfo", "【loadPage】出错"+e.getMessage()); 
			return false; 
		}
		if(this.login_sig.equalsIgnoreCase("")){
			resultMap.put("resCode", 61);
			resultMap.put("resInfo", "【loadPage】出错login_sig没赋值！"); 
			return false; 
		}
		return true;

	}
	
	private  boolean check() {
		StringBuffer urlBuffer = new StringBuffer("");
		urlBuffer.append("http://check.ptlogin2.qq.com/check?");
		urlBuffer.append("regmaster=");
		urlBuffer.append("&pt_tea=1");
		urlBuffer.append("&pt_vcode=1");
		urlBuffer.append("&uin=").append(this.qqNumber);
		urlBuffer.append("&appid=11000101");
		urlBuffer.append("&js_ver=10131");
		urlBuffer.append("&js_type=1");
		urlBuffer.append("&login_sig=").append(this.login_sig);
		urlBuffer.append("&u1=http%3A%2F%2Fpay.qq.com%2F");
		urlBuffer.append("&r=").append(CommonUtil.getRandom());
		String[] resultArr=new String[]{"0"};
		 
		try {
			GetMethod get = new GetMethod(urlBuffer.toString());
			get.setRequestHeader("Referer", "http://check.ptlogin2.qq.com");
			get.setRequestHeader(USER_AGENT_H, USER_AGENT);
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				String result = get.getResponseBodyAsString(); 
				Pattern cellPtn = Pattern.compile("^ptui_checkVC\\((.*?)\\);$");
				Matcher matcher = cellPtn.matcher(result);
				if (matcher.find()) {
					result = CommonUtil.trim(matcher.group(1));
				} 
				result = result.replaceAll("'", "");
				resultArr = result.split(",");
				//ptui_checkVC('0','!EKX','\x00\x00\x00\x00\x07\x3c\xfd\x27','40506d0502da528a5f2c4debc9dba626cef081aa73bf478b25b6f4f6e56ff5108f09d508014649cecb80ef92043f150c1c68297cc653f882','0');
				
				verifycode = CommonUtil.trim(resultArr[1]);
				pt_verifysession_v1 = CommonUtil.trim(resultArr[3]); 
			}

		} catch (Exception e) {
			resultMap.put("resCode", 70);
			resultMap.put("resInfo", "【check】出错"+e.getMessage()); 
			return false; 

		}
		
		if(this.verifycode.equalsIgnoreCase("")){
			resultMap.put("resCode", 71);
			resultMap.put("resInfo", "【check】出错verifycode没赋值！"); 
			return false; 
		}
		if(!resultArr[0].equalsIgnoreCase("0")){
			resultMap.put("resCode", 72);
			resultMap.put("resInfo", "【check】出错需要输入验证码！"); 
			return false;
		}
		return true;
		
	}
	
	
	private boolean login() { 
		try {
			String url = this.getQdPayUrl(qqPassword, qqNumber, pt_verifysession_v1,
					verifycode, login_sig);
			GetMethod get = new GetMethod(url);
			get.setRequestHeader("Referer", "http://ptlogin2.qq.com/");
			get.setRequestHeader(USER_AGENT_H, USER_AGENT);
			int status;
			status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				String text = CommonUtil.trim(get.getResponseBodyAsString()); 
				//ptuiCB('0','0','http://pay.qq.com/','1','登录成功！', '小报记者');
				if(text.indexOf("登录成功")<0){
					resultMap.put("resCode", 81);
					resultMap.put("resInfo", "【login】出错"+ text); 
					return false; 
				}
			}
		} catch (Exception e) {
			resultMap.put("resCode", 80);
			resultMap.put("resInfo", "【login】出错"+e.getMessage()); 
			return false; 

		}
		
		return true;

	}
	 
	private boolean balance_query_sortflow() { 
		StringBuffer urlBuffer = new StringBuffer("");
		urlBuffer.append("http://pay.qq.com/cgi-bin/personal/balance_query_sortflow.cgi?");
		urlBuffer.append("items=qb,qd");
		urlBuffer.append("&from=pay"); 
		urlBuffer.append("&r=").append(CommonUtil.getRandom());
		try {
			String url = urlBuffer.toString();
			GetMethod get = new GetMethod(url);
			get.setRequestHeader("Referer", "http://pay.qq.com");
			get.setRequestHeader(USER_AGENT_H, USER_AGENT);
			int status;
			status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				String result = CommonUtil.trim(get.getResponseBodyAsString());  
				pattern=Pattern.compile("^.+(\"qb_balance\": \")(\\d+)\\D+.*$");
				matcher=pattern.matcher(result);  
				if(matcher.find()){  
					result=CommonUtil.trim(matcher.group(2)); 
					this.qbBalance=CommonUtil.castInt(result); 
					resultMap.put("qbBalance",CommonUtil.castInt(result));
					
				}  
				else{
					resultMap.put("resCode", 91);
					resultMap.put("resInfo", "【balance_query_sortflow】出错"+result); 
					return false; 
				}
				
			}
		} catch (Exception e) {
			resultMap.put("resCode", 90);
			resultMap.put("resInfo", "【balance_query_sortflow】出错"+e.getMessage()); 
			return false; 

		}
		
		return true;

	}
	
	
	
	private boolean goPayHome() {
		try {
			String url = "http://pay.qq.com/";
			GetMethod get = new GetMethod(url);
			get.setRequestHeader("Referer", "http://pay.qq.com/");
			get.setRequestHeader(USER_AGENT_H, USER_AGENT);
			int status;
			status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				String text = get.getResponseBodyAsString(); 
				for (Cookie c : client.getState().getCookies()) {
					if (c.getName().equalsIgnoreCase("uin")) {
						this.cookie_uin = CommonUtil.trim(c.getValue());
					}
					if (c.getName().equalsIgnoreCase("skey")) {
						this.cookie_skey = CommonUtil.trim(c.getValue());
					} 
				} 
			}
		} catch (Exception e) {
			resultMap.put("resCode", 100);
			resultMap.put("resInfo", "【goPayHome】出错"+e.getMessage()); 
			return false; 

		}
		this.cookie_uin=CommonUtil.trim(this.cookie_uin);
		this.cookie_skey=CommonUtil.trim(this.cookie_skey);
		if(this.cookie_uin.equals("")
				||this.cookie_skey.equals("")){
			resultMap.put("resCode", 101);
			resultMap.put("resInfo", "【goPayHome】出错cookie_uin cookie_skey 取值为空！"); 
			return false; 
		}
		
		return true;

	}
	
	
	private boolean get_pay_info(String serviceCode) {
		cookie_uin=CommonUtil.trim(cookie_uin);
		cookie_skey=CommonUtil.trim(cookie_skey); 
		this.token="";
		try {
			StringBuffer urlBuffer = new StringBuffer("");
			urlBuffer.append("http://api.unipay.qq.com/cgi-bin/get_pay_info.fcg?");
			urlBuffer.append("amt=1000");
			urlBuffer.append("&service_code=").append(serviceCode); 
			urlBuffer.append("&sck=").append(hex_md5(cookie_uin+cookie_skey).toLowerCase() );
			urlBuffer.append("&cmd=buy");
			urlBuffer.append("&payType=2"); 
			urlBuffer.append("&rr=").append(CommonUtil.getRandom());
			
			GetMethod get = new GetMethod(urlBuffer.toString());
			get.setRequestHeader("Referer", "http://api.unipay.qq.com");
			get.setRequestHeader(USER_AGENT_H, USER_AGENT);
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				String result = get.getResponseBodyAsString();
				pattern=Pattern.compile("^(.+)(\"token\" : \")(.+)(\",\"kj\")(.+)$");
		    	matcher = pattern.matcher(result ); 
				if(matcher.find()){  
					this.token=CommonUtil.trim(matcher.group(3)) ;   
				}   
			}

		} catch (Exception e) {
			resultMap.put("resCode", 110);
			resultMap.put("resInfo", "【get_pay_info】出错"+e.getMessage()); 
			return false; 

		}
		this.token=CommonUtil.trim(this.token);
		if(this.token.equals("") ){
			resultMap.put("resCode", 111);
			resultMap.put("resInfo", "【get_pay_info】出错token取值为空！"); 
			return false; 
		}
		
		return true;
		
	}
		  
 
     
	
	private boolean uniform_pay(String serviceCode,String openAid,String gameZone,String userNum,String openMonth) {
		try {
			StringBuffer urlBuffer = new StringBuffer("");
			urlBuffer.append("http://upayportal.qq.com/cgi-bin/uniform_pay.fcg?");
			urlBuffer.append("pay_num=").append(this.qqNumber);
			urlBuffer.append("&user_num=").append(userNum);
			urlBuffer.append("&service_code=").append(serviceCode); 
			urlBuffer.append("&sck=").append(this.hex_md5(cookie_skey).toLowerCase() );
			urlBuffer.append("&source=1");
			urlBuffer.append("&serve=1");
			urlBuffer.append("&req_source=1");
			urlBuffer.append("&open_mode=2");
			urlBuffer.append("&open_month=").append(openMonth);
			urlBuffer.append("&if_callback=1"); 
			urlBuffer.append("&callback_url=http%3A%2F%2Fpay.qq.com%2Fipay%2Fcallback.shtml");
			urlBuffer.append("&aid4open=pay.").append(openAid).append("%7Crch%3Dqdqb%7C").append(this.token);
			urlBuffer.append("&from=pay");
			urlBuffer.append("&rr=").append(CommonUtil.getRandom());
			urlBuffer.append("&is_club=0");
			urlBuffer.append("&gamezone=").append(gameZone);
			urlBuffer.append("&game_scene=dq_pay"); 
			urlBuffer.append("&pay_way=2");
			urlBuffer.append("&vendor_type=4");
			urlBuffer.append("&auto_pay=0");
			GetMethod get = new GetMethod(urlBuffer.toString());
			get.setRequestHeader("Referer", "http://upayportal.qq.com/");
			get.setRequestHeader(USER_AGENT_H, USER_AGENT);
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				String result = get.getResponseBodyAsString();
				result=URLDecoder.decode(result,"utf-8") ;
				result=URLDecoder.decode(result,"utf-8"); 
				pattern=Pattern.compile("^.+(\\?)(.+)(\";).*$");
				matcher=pattern.matcher(result);  
				if(matcher.find()){  
					result=CommonUtil.trim(matcher.group(2));   
				} 
				HashMap<String, String> rstMap = new HashMap<String, String>();
				Pattern responsePtn = Pattern.compile("(&)"); 
				String[] strs = responsePtn.split(result);  
				Pattern cellPtn = Pattern.compile("^(.+)=(.*?)$");  
				for (String cellRst:strs) { 
					matcher = cellPtn.matcher(cellRst );  
					if(matcher.find()){  
						rstMap.put(CommonUtil.trim(matcher.group(1)) , CommonUtil.trim(matcher.group(2))); 
					}     
				
				} 
				if(!CommonUtil.trim(rstMap.get("result")).equalsIgnoreCase("0")){ 
					resultMap.put("resCode", 121);
					resultMap.put("resInfo", "充值失败！"+rstMap.get("result_info")); 
				} 
				 

			}

		} catch (Exception e) {
			resultMap.put("resCode", 120);
			resultMap.put("resInfo", "【uniform_pay】出错"+e.getMessage()); 
			return false; 

		}
		return true;
	}
	
	
	
	private boolean uniform_pay(String serviceCode,String openAid,String userNum,String openMonth) {
		  
		try {
			StringBuffer urlBuffer = new StringBuffer("");
			urlBuffer.append("http://upayportal.qq.com/cgi-bin/uniform_pay.fcg?");
			urlBuffer.append("pay_num=").append(this.qqNumber);
			urlBuffer.append("&user_num=").append(userNum);
			urlBuffer.append("&service_code=").append(serviceCode); 
			urlBuffer.append("&sck=").append(this.hex_md5(cookie_skey).toLowerCase() );
			urlBuffer.append("&source=1");
			urlBuffer.append("&serve=1");
			urlBuffer.append("&req_source=1");
			urlBuffer.append("&open_mode=2");
			urlBuffer.append("&open_month=").append(openMonth);
			urlBuffer.append("&if_callback=1"); 
			urlBuffer.append("&callback_url=http%3A%2F%2Fpay.qq.com%2Fipay%2Fcallback.shtml");
			urlBuffer.append("&aid4open=pay.").append(openAid).append("%7Crch%3Dqdqb%7C").append(this.token);
			urlBuffer.append("&from=pay");
			urlBuffer.append("&rr=").append(CommonUtil.getRandom());
			urlBuffer.append("&pay_way=2");
			urlBuffer.append("&vendor_type=4");
			urlBuffer.append("&auto_pay=0");
			GetMethod get = new GetMethod(urlBuffer.toString());
			get.setRequestHeader("Referer", "http://upayportal.qq.com/");
			get.setRequestHeader(USER_AGENT_H, USER_AGENT);
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				String result = get.getResponseBodyAsString();
				result=URLDecoder.decode(result,"utf-8") ;
				result=URLDecoder.decode(result,"utf-8"); 
				pattern=Pattern.compile("^.+(\\?)(.+)(\";).*$");
				matcher=pattern.matcher(result);  
				if(matcher.find()){  
					result=CommonUtil.trim(matcher.group(2));   
				} 
				HashMap<String, String> rstMap = new HashMap<String, String>();
				Pattern responsePtn = Pattern.compile("(&)"); 
				String[] strs = responsePtn.split(result);  
				Pattern cellPtn = Pattern.compile("^(.+)=(.*?)$");  
				for (String cellRst:strs) { 
					matcher = cellPtn.matcher(cellRst );  
					if(matcher.find()){  
						rstMap.put(CommonUtil.trim(matcher.group(1)) , CommonUtil.trim(matcher.group(2))); 
					}     
				
				} 
				if(!CommonUtil.trim(rstMap.get("result")).equalsIgnoreCase("0")){ 
					resultMap.put("resCode", 121);
					resultMap.put("resInfo", "充值失败！"+rstMap.get("result_info")); 
				} 
				 

			}

		} catch (Exception e) {
			resultMap.put("resCode", 120);
			resultMap.put("resInfo", "【uniform_pay】出错"+e.getMessage()); 
			return false; 

		}
		return true;
		
	}
	
	
	
	
	
	
	
	
	private    String hex_md5(String str) throws Exception {
		String url = ""; 
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		Reader in = new InputStreamReader(this.getClass().getResourceAsStream(
				"/tencent.js"));
		engine.eval(in);
		if (engine instanceof Invocable) {
			Invocable invoke = (Invocable) engine;
			url = invoke.invokeFunction("hex_md5", str).toString();
		} 
		return url;

	}
	
	private  String getQdPayUrl(String password, String salt, String pt_verifysession_v1, String verifycode, String login_sig) 
					throws Exception{

		String url = ""; 
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		Reader in = new InputStreamReader(this.getClass().getResourceAsStream(
				"/tencent.js"));
		engine.eval(in);
		if (engine instanceof Invocable) {
			Invocable invoke = (Invocable) engine;
			url = invoke.invokeFunction("getQdPayUrl", password, salt,
					pt_verifysession_v1, verifycode, login_sig).toString();

		} 
		return url;

	}
	
	
	
	
	
	
	 
}