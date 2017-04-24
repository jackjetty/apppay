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
import com.rising.appserver.service.FlowService;

@Controller
@RequestMapping("/flow")
public class Flow extends BaseAction {

	Log log = LogFactory.getLog(Flow.class);
	@Autowired
	FlowService flowService; 
	
	
	@RequestMapping("/query")
	public void query(HttpServletRequest request, HttpServletResponse response) {
		 
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson;
		
		try { 
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = flowService.query(parameter,request);
			 
		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("query()->App发送xml报文格式出错!" + e.toString());
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("query()->读取请求xml报文出错！" + e.toString());
		}
		writerGson(resultGson, request,response);
	}
	
	
	@RequestMapping("/recommend")
	public void recommend(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson; 
		try { 
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = flowService.recommend(parameter,request); 
		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("recommend()->App发送xml报文格式出错!" + e.toString());
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("recommend()->读取请求xml报文出错！" + e.toString());
		}
		writerGson(resultGson,request, response);
	}
	
	@RequestMapping("/apply")
	public void apply(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson;
		
		try { 
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = flowService.apply(parameter,request);
			 
		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("apply()->App发送xml报文格式出错!" + e.toString());
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("apply()->读取请求xml报文出错！" + e.toString());
		}
		writerGson(resultGson,request, response);
	}
	
	@RequestMapping("/handle")
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson;
		
		try { 
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap); 
			resultGson = flowService.handle(parameter,request);
			 
		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("handle()->App发送xml报文格式出错!" + e.toString());
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("handle()->读取请求xml报文出错！" + e.toString());
		}
		writerGson(resultGson, request,response);
	}
	@RequestMapping("/orderQuery")
	public void orderQuery(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson;
		
		try { 
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			resultGson = flowService.orderQuery(parameter,request);
			 
		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("orderQuery()->App发送xml报文格式出错!" + e.toString());
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("orderQuery()->读取请求xml报文出错！" + e.toString());
		}
		writerGson(resultGson,request, response);
	}
}