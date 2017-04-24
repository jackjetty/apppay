package com.rising.appserver.service;
 
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.config.AlipayConfig;
import com.rising.appserver.common.CommonUtil;
import com.rising.appserver.dao.QQCookieStateDao;
import com.rising.appserver.factory.HttpFactory; 
import com.rising.appserver.pojo.TbQQCookieState;
import com.rising.appserver.pojo.TbQQCookieStateId;

@Service("qqTaoService")
public class QQTaoService {
	
	public final static String USER_AGENT_H = "User-Agent";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
	public final static String UTF_8 = "UTF-8";  
	private HttpFactory http = HttpFactory.getInstance();  
 
	private Cookie[] cookies;
	private String cookiestr;
	
	
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
	
	public String getQQBalance(String qqNumber,String qqPassword){
		String hql="";
		
		
		HttpFactory http = HttpFactory.getInstance();
		client = http.getClient(); 
		HttpClientParams params = client.getParams();
		params.setContentCharset("utf-8");
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		client.getParams().setParameter("http.protocol.single-cookie-header",
				true); 
	 
		
		
		return "success";
	}
 
	
	
	
}