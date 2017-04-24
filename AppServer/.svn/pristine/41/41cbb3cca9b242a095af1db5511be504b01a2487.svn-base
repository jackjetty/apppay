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
import com.rising.appserver.service.ChargeService;

@Controller
@RequestMapping("/charge")
public class Charge extends BaseAction {

	private Log log = LogFactory.getLog(Charge.class);

	@Autowired
	private ChargeService chargeService;

	@RequestMapping("/pay")
	public void pay(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request); 
			String parameter = mapToString(paraMap);
			resultGson = chargeService.takeCharge(parameter, request);

		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("pay()->App发送xml报文格式出错!" + e.toString());
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("pay()->读取请求xml报文出错！" + e.toString());
		}
		writerGson(resultGson, request, response);
	}

	@RequestMapping("/payQQPerMonth")
	public void payQQPerMonth(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);

			String parameter = mapToString(paraMap);
			resultGson = chargeService.takeQQPerMonthCharge(parameter, request);

		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("payQQPerMonth()->App发送xml报文格式出错!" + e.toString());
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("payQQPerMonth()->读取请求xml报文出错！" + e.toString());
		}
		writerGson(resultGson, request, response);
	}

	@RequestMapping("/payQQVIP")
	public void payQQVIP(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);

			String parameter = mapToString(paraMap);
			resultGson = chargeService.takeQQVIPCharge(parameter, request);

		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("payQQVIP()->App发送xml报文格式出错!" + e.toString());
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("payQQVIP()->读取请求xml报文出错！" + e.toString());
		}
		writerGson(resultGson, request, response);
	}

}
