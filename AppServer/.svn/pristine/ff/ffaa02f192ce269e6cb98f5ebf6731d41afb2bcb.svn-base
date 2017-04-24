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
import com.rising.appserver.service.PublicInfoService;

@Controller
@RequestMapping("/publicInfo")
public class PublicInfo extends BaseAction {

	Log log = LogFactory.getLog(PublicInfo.class);

	@Autowired
	PublicInfoService publicInfoService;

	 

 

	@RequestMapping("/get")
	public void get(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson;
		try {
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = publicInfoService.getPublicInfo(parameter,request);
			 
		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("getFaq()->App发送xml报文格式出错!" + e.toString());
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("getFaq()->读取请求xml报文出错！" + e.toString());
		}
		writerGson(resultGson, request, response);
	}

	@RequestMapping("/getMessage")
	public void getMessage(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson; 
		try {
			paraMap = getParameterFromRequest(request);
		 
			String parameter = mapToString(paraMap); 
			resultGson = publicInfoService.getMessage(parameter,request);
			 
		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("getFaq()->App发送xml报文格式出错!" + e.toString());
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("getFaq()->读取请求xml报文出错！" + e.toString());
		}
		writerGson(resultGson, request, response);
	}
}

//	@RequestMapping("/setPublicInfoReaded")
//	public void setPublicInfoReaded(HttpServletRequest request,
//			HttpServletResponse response) {
//		HashMap<String, String> paraMap = null;
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		String resultGson;
//		try {
//			paraMap = getParameterFromRequest(request);
//			if (CheckParameter(parameterMust2, paraMap)) {
//				String parameter = mapToString(paraMap);
//				resultGson = publicInfoService.setPublicInfoReaded(parameter);
//			} else {
//				result.put("respCode", -100);
//				result.put("respInfo", "App发送数据错误!");
//				resultGson = new Gson().toJson(result);
//			}
//		} catch (DocumentException e) {
//			result.put("respCode", -101);
//			result.put("respInfo", "App发送xml报文格式出错!");
//			resultGson = new Gson().toJson(result);
//			log.error("getFaq()->App发送xml报文格式出错!" + e.toString());
//		} catch (IOException e) {
//			result.put("respCode", -200);
//			result.put("respInfo", "读取请求xml报文出错！");
//			resultGson = new Gson().toJson(result);
//			log.error("getFaq()->读取请求xml报文出错！" + e.toString());
//		}
//		// writerGson(encodeGsonString(resultGson), response);
//		writerGson(resultGson, response);
//	}
//
//	@RequestMapping("/setAnswerFeedBackReaded")
//	public void setAnswerFeedBackReaded(HttpServletRequest request,
//			HttpServletResponse response) {
//		HashMap<String, String> paraMap = null;
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		String resultGson;
//		try {
//			paraMap = getParameterFromRequest(request);
//			if (CheckParameter(parameterMust2, paraMap)) {
//				String parameter = mapToString(paraMap);
//				resultGson = publicInfoService
//						.setAnswerFeedBackReaded(parameter);
//			} else {
//				result.put("respCode", -100);
//				result.put("respInfo", "App发送数据错误!");
//				resultGson = new Gson().toJson(result);
//			}
//		} catch (DocumentException e) {
//			result.put("respCode", -101);
//			result.put("respInfo", "App发送xml报文格式出错!");
//			resultGson = new Gson().toJson(result);
//			log.error("getFaq()->App发送xml报文格式出错!" + e.toString());
//		} catch (IOException e) {
//			result.put("respCode", -200);
//			result.put("respInfo", "读取请求xml报文出错！");
//			resultGson = new Gson().toJson(result);
//			log.error("getFaq()->读取请求xml报文出错！" + e.toString());
//		}
//		// writerGson(encodeGsonString(resultGson), response);
//		writerGson(resultGson, response);
//	}
//
//	@RequestMapping("/getMoreMessage")
//	public void getMoreMessage(HttpServletRequest request,
//			HttpServletResponse response) {
//		HashMap<String, String> paraMap = null;
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		String resultGson;
//		try {
//			paraMap = getParameterFromRequest(request);
//			if (paraMap.get("MessageType").equals("PublicInfo")) {
//				if (CheckParameter(parameterMust4, paraMap)) {
//					String parameter = mapToString(paraMap);
//					resultGson = publicInfoService.getMoreMessage(parameter);
//				} else {
//					result.put("respCode", -100);
//					result.put("respInfo", "App发送数据错误!");
//					resultGson = new Gson().toJson(result);
//				}
//			} else {
//				if (CheckParameter(parameterMust3, paraMap)) {
//					String parameter = mapToString(paraMap);
//					resultGson = publicInfoService.getMoreMessage(parameter);
//				} else {
//					result.put("respCode", -100);
//					result.put("respInfo", "App发送数据错误!");
//					resultGson = new Gson().toJson(result);
//				}
//			}
//		} catch (DocumentException e) {
//			result.put("respCode", -101);
//			result.put("respInfo", "App发送xml报文格式出错!");
//			resultGson = new Gson().toJson(result);
//			log.error("getFaq()->App发送xml报文格式出错!" + e.toString());
//		} catch (IOException e) {
//			result.put("respCode", -200);
//			result.put("respInfo", "读取请求xml报文出错！");
//			resultGson = new Gson().toJson(result);
//			log.error("getFaq()->读取请求xml报文出错！" + e.toString());
//		}
//		// writerGson(encodeGsonString(resultGson), response);
//		writerGson(resultGson, response);
//	}
//}
