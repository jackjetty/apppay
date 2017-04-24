package com.rising.appserver.action; 
import java.io.IOException; 
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap; 
import java.util.Iterator;
import java.util.Map; 
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;  

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;  
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rising.appserver.bean.RespError;
import com.rising.appserver.common.BaseAction;
import com.rising.appserver.common.CommonUtil; 
import com.rising.appserver.service.QQTaoService;
import com.alipay.config.*;
import com.alipay.util.*;
@Controller
@RequestMapping("/qqTao")
public class QQTao extends BaseAction{
	
	private Log log = LogFactory.getLog(QQTao.class);
	@Autowired 
	private QQTaoService qqTaoService;
	 
	
	@RequestMapping(value="/qqGame", produces = "text/html; charset=utf-8")
	public @ResponseBody String qqGame(HttpServletRequest request, HttpServletResponse response){  
		String qqNumber = CommonUtil.trim((request.getParameter("qqNumber")));
		String qqPassword = CommonUtil.trim((request.getParameter("qqPassword"))); 
		
		return qqTaoService.getQQBalance(qqNumber,qqPassword);
	}
}