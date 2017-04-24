package com.rising.appserver.action; 
 
import java.io.IOException; 
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 

import com.google.gson.Gson;
import com.rising.appserver.common.BaseAction;
import com.rising.appserver.common.CommonUtil;
import com.rising.appserver.service.QQPayService; 
@Controller
@RequestMapping("/qqPay")
public class QQPay extends BaseAction{
	private Log log = LogFactory.getLog(QQPay.class);
	@Autowired 
	private QQPayService qqPayService;
	@RequestMapping("/getQQBalance")
	public void getQQBalance(HttpServletRequest request,HttpServletResponse response){
		String qqNumber=request.getParameter("qqNumber"); 
		String qqPassword=request.getParameter("qqPassword"); 
		HashMap<String,Object> result = new HashMap<String,Object>(); 
		result=qqPayService.getQQBalance(qqNumber,qqPassword);
		result.put("qqNumber", qqNumber);  
		String resultGson;
		resultGson = new Gson().toJson(result);
		CommonUtil.appendLog("qqservice",CommonUtil.doDateForm(new Date(), "yyyy-MM-dd HH:mm:ss")+"  getQQBalance  "+ resultGson);
		writerGson(resultGson,request, response);
	}
	
	@RequestMapping("/payQQService")
	public void payQQService(HttpServletRequest request,HttpServletResponse response){ 
		String qqNumber=request.getParameter("qqNumber"); 
		String qqPassword=request.getParameter("qqPassword"); 
		String targetQQNum=request.getParameter("targetQQNum"); 
		String serviceCode=request.getParameter("serviceCode"); 
		String openAid=request.getParameter("openAid"); 
		String openMonth=request.getParameter("openMonth"); 
		
		
		HashMap<String,Object> result = new HashMap<String,Object>(); 
		result=qqPayService.payQQService(qqNumber,qqPassword,targetQQNum,serviceCode,openAid,openMonth);
		result.put("qqNumber", qqNumber);  
		String resultGson;
		resultGson = new Gson().toJson(result);
		
		CommonUtil.appendLog("qqservice",CommonUtil.doDateForm(new Date(), "yyyy-MM-dd HH:mm:ss")+"  payQQService  "+ resultGson);
		writerGson(resultGson,request, response);
 
	}
	
	
	
	@RequestMapping("/payQQGame")
	public void payQQGame(HttpServletRequest request,HttpServletResponse response){ 
		String qqNumber=request.getParameter("qqNumber"); 
		String qqPassword=request.getParameter("qqPassword"); 
		String targetQQNum=request.getParameter("targetQQNum"); 
		String serviceCode=request.getParameter("serviceCode"); 
		String openAid=request.getParameter("openAid"); 
		String gameZone=request.getParameter("gameZone"); 
		String openMonth=request.getParameter("openMonth");  
		HashMap<String,Object> result = new HashMap<String,Object>(); 
		result=qqPayService.payQQGame(qqNumber,qqPassword,targetQQNum,serviceCode,openAid,gameZone,openMonth);
		result.put("qqNumber", qqNumber);  
		String resultGson;
		resultGson = new Gson().toJson(result); 
		CommonUtil.appendLog("qqservice",CommonUtil.doDateForm(new Date(), "yyyy-MM-dd HH:mm:ss")+"  payQQGame  "+ resultGson);
		writerGson(resultGson,request, response);
 
	} 
		 
}