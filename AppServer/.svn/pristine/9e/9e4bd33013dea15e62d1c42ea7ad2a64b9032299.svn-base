package com.rising.appserver.action;

//import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

//import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
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
import com.rising.appserver.service.GetImageService;

@Controller
@RequestMapping("/image")
public class GetImage extends BaseAction {
	Log log = LogFactory.getLog(GetImage.class);

 

	@Autowired
	GetImageService getImageService;

	@RequestMapping("/getSalesInfoImage")
	public void getImage(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson;
		byte [] buffimage;
		try {
			paraMap = getParameterFromRequest(request);
			 
			String parameter = mapToString(paraMap);
			buffimage = getImageService.getImage(parameter,request);
			ServletOutputStream sos;
			try {
				sos = response.getOutputStream();
				sos.write(buffimage);
				sos.flush();
				sos.close();
			} catch (IOException e) {
				log.error("getImage()->输出图片时出错！ " + e);
			}
			 
		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("getSalesInfo()->App发送xml报文格式出错!" + e.toString());
			writerGson(resultGson,request, response);
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("getSalesInfo()->读取请求xml报文出错！" + e.toString());
			writerGson(resultGson,request, response);
		}
	}
	
	@RequestMapping("/getIOSSalesInfoImage")
	public void getIOSSalesInfoImage(HttpServletRequest request,
			HttpServletResponse response){
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson;
		byte [] buffimage;
		try {
			paraMap = getParameterFromRequest2(request);
			 
			String parameter = mapToString(paraMap);
			buffimage = getImageService.getImage(parameter,request);
			ServletOutputStream sos;
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			String imgName = paraMap.get("ImgName");
			if(imgName.toLowerCase().contains("jpg")){
				response.setContentType("image/jpeg");
			}else if(imgName.toLowerCase().contains("png")){
				response.setContentType("image/png");
			}
			
			response.addHeader("Content-Disposition",
					"attachment;filename="+imgName);
			sos = response.getOutputStream();
			sos.write(buffimage);
			sos.flush();
			sos.close();
			 
		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("getSalesInfo()->App发送xml报文格式出错!" + e.toString());
			writerGson(resultGson,request, response);
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("getSalesInfo()->读取请求xml报文出错！" + e.toString());
			writerGson(resultGson,request, response);
		}
	}
	@RequestMapping("/getIOSSalesInfoImage2")
	public void getIOSSalesInfoImage2(HttpServletRequest request,
			HttpServletResponse response){
		HashMap<String, String> paraMap = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		String resultGson;
		byte [] buffimage;
		try {
			paraMap = getParameterFromRequest2(request);
			 
			String parameter = mapToString(paraMap);
			buffimage = getImageService.getImage(parameter,request);
			ServletOutputStream sos;
			sos = response.getOutputStream();
			sos.write(buffimage);
			sos.flush();
			sos.close();
			 
		} catch (DocumentException e) {
			result.put("respCode", -101);
			result.put("respInfo", "App发送xml报文格式出错!");
			resultGson = new Gson().toJson(result);
			log.error("getSalesInfo()->App发送xml报文格式出错!" + e.toString());
			writerGson(resultGson,request, response);
		} catch (IOException e) {
			result.put("respCode", -200);
			result.put("respInfo", "读取请求xml报文出错！");
			resultGson = new Gson().toJson(result);
			log.error("getSalesInfo()->读取请求xml报文出错！" + e.toString());
			writerGson(resultGson,request, response);
		}
	}
}
