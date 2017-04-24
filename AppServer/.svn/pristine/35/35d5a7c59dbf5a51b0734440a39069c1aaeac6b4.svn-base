package com.rising.appserver.action;

import java.io.IOException;
//import java.net.URLDecoder;
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
import com.rising.appserver.service.TakeOrderService;
@Controller
@RequestMapping("/charge")
public class TakeOrder extends BaseAction {

	Log log = LogFactory.getLog(TakeOrder.class);
	
	@Autowired TakeOrderService takeOrderService;
	
	
	 
	
	@RequestMapping("/takeOrder")
	public void takeOrder(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, String> paraMap = null;
		HashMap<String,Object> result = new HashMap<String,Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = takeOrderService.takeOrder(parameter,request);
			 
		} catch (DocumentException e) {
			result.put("respCode",-101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("takeOrder()->App发送xml报文格式出错!"+e.toString());
		} catch (IOException e) {
			result.put("respCode",-200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("takeOrder()->读取请求xml报文出错！"+e.toString());
		}
		writerGson(resultGson, request,response);
	}
	
	@RequestMapping("/takeQQPerMonthOrder")
	public void takeQQPerMonthOrder(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, String> paraMap = null;
		HashMap<String,Object> result = new HashMap<String,Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = takeOrderService.takeQQPerMonthOrder(parameter,request);
			 
		} catch (DocumentException e) {
			result.put("respCode",-101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("takeOrder()->App发送xml报文格式出错!"+e.toString());
		} catch (IOException e) {
			result.put("respCode",-200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("takeOrder()->读取请求xml报文出错！"+e.toString());
		}
		writerGson(resultGson,request, response);
	}
	
	@RequestMapping("/takeQQVIPOrder")
	public void takeQQVIPOrder(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, String> paraMap = null;
		HashMap<String,Object> result = new HashMap<String,Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = takeOrderService.takeQQVIPOrder(parameter,request);
			 
		} catch (DocumentException e) {
			result.put("respCode",-101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("takeOrder()->App发送xml报文格式出错!"+e.toString());
		} catch (IOException e) {
			result.put("respCode",-200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("takeOrder()->读取请求xml报文出错！"+e.toString());
		}
		writerGson(resultGson, request,response);
	}
	
	@RequestMapping("/cancelQQPerMonthOrder")
	public void cancelQQPerMonthOrder(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, String> paraMap = null;
		HashMap<String,Object> result = new HashMap<String,Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = takeOrderService.cancelQQPerMonthOrder(parameter,request);
			 
		} catch (DocumentException e) {
			result.put("respCode",-101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("takeOrder()->App发送xml报文格式出错!"+e.toString());
		} catch (IOException e) {
			result.put("respCode",-200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("takeOrder()->读取请求xml报文出错！"+e.toString());
		}
		writerGson(resultGson, request, response);
	}
	
	@RequestMapping("/checkQQPerMonth")
	public void checkQQPerMonth(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, String> paraMap = null;
		HashMap<String,Object> result = new HashMap<String,Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = takeOrderService.checkQQPerMonthOrder(parameter,request);
			 
		} catch (DocumentException e) {
			result.put("respCode",-101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("takeOrder()->App发送xml报文格式出错!"+e.toString());
		} catch (IOException e) {
			result.put("respCode",-200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("takeOrder()->读取请求xml报文出错！"+e.toString());
		}
		writerGson(resultGson, request,response);
	}
	
	@RequestMapping("/checkQQPerMonthWithSecurityCode")
	public void checkQQPerMonthWithSecurityCode(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, String> paraMap = null;
		HashMap<String,Object> result = new HashMap<String,Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);
		 
			String parameter = mapToString(paraMap);
			resultGson = takeOrderService.checkQQPerMonthOrderWithSecurityCode(parameter,request);
			 
		} catch (DocumentException e) {
			result.put("respCode",-101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("takeOrder()->App发送xml报文格式出错!"+e.toString());
		} catch (IOException e) {
			result.put("respCode",-200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("takeOrder()->读取请求xml报文出错！"+e.toString());
		}
		writerGson(resultGson, request,response);
	}
	
}
