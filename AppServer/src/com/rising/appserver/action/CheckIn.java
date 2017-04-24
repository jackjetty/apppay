package com.rising.appserver.action;

import java.io.IOException;
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
import com.rising.appserver.service.CheckService;


@Controller
@RequestMapping(("/check"))
public class CheckIn extends BaseAction{
	
	Log log = LogFactory.getLog(CheckIn.class);
	
	@Autowired CheckService checkService;
	
	
	 
	
	@RequestMapping("/in")
	public void in(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, String> paraMap = null;
		HashMap<String,Object> result = new HashMap<String,Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = checkService.checkIn(parameter,request);
				//resultGson = URLDecoder.decode(resultGson,"UTF-8");
			 
		} catch (DocumentException e) {
			result.put("respCode",-101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("in()->App发送xml报文格式出错!"+e.toString());
		} catch (IOException e) {
			result.put("respCode",-200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("in()->读取请求xml报文出错！"+e.toString());
		}
		writerGson(resultGson,request, response);
	}
	
	@RequestMapping("/getLeftMoney")
	public void getLeftMoney(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, String> paraMap = null;
		HashMap<String,Object> result = new HashMap<String,Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = checkService.getLeftMoney(parameter,request);
				//resultGson = URLDecoder.decode(resultGson,"UTF-8");
			 
		} catch (DocumentException e) {
			result.put("respCode",-101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("in()->App发送xml报文格式出错!"+e.toString());
		} catch (IOException e) {
			result.put("respCode",-200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("in()->读取请求xml报文出错！"+e.toString());
		}
		writerGson(resultGson,request, response);
	}
	
	@RequestMapping("/recommend")
	public void recommend(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, String> paraMap = null;
		HashMap<String,Object> result = new HashMap<String,Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = checkService.checkRecommend(parameter,request);
				//resultGson = URLDecoder.decode(resultGson,"UTF-8");
			 
		} catch (DocumentException e) {
			result.put("respCode",-101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("in()->App发送xml报文格式出错!"+e.toString());
		} catch (IOException e) {
			result.put("respCode",-200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("in()->读取请求xml报文出错！"+e.toString());
		}
		writerGson(resultGson,request, response);
	}
	
	@RequestMapping("/submitRecommend")
	public void submitRecommend(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, String> paraMap = null;
		HashMap<String,Object> result = new HashMap<String,Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = checkService.recommend(parameter,request);
				//resultGson = URLDecoder.decode(resultGson,"UTF-8");
			 
		} catch (DocumentException e) {
			result.put("respCode",-101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("in()->App发送xml报文格式出错!"+e.toString());
		} catch (IOException e) {
			result.put("respCode",-200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("in()->读取请求xml报文出错！"+e.toString());
		}
		writerGson(resultGson,request, response);
	}
	
	

}
