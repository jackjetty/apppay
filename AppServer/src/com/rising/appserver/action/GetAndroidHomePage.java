package com.rising.appserver.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.appserver.common.BaseAction;
import com.rising.appserver.service.GetAndroidHomePageService;

@Controller
@RequestMapping("/android")
public class GetAndroidHomePage extends BaseAction {

	@Autowired
	GetAndroidHomePageService getAndroidHomePageService;

	@RequestMapping("/homePage")
	public void getAndroidHomePage(HttpServletRequest request,
			HttpServletResponse response) {		
		String resultGson = getAndroidHomePageService.getAndroidHomePage("",request);
		String newVersion = request.getParameter("version");
		if(newVersion!=null &&!"".equals(newVersion)){
			writerGson(resultGson,request,response);
		}else{
			writerGson(resultGson,response);
		}
	}
}
