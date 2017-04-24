package com.rising.appserver.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.appserver.common.BaseAction;
import com.rising.appserver.service.GetIOSHomePageService;


@Controller
@RequestMapping("/iOS")
public class GetIOSHomePage extends BaseAction {
	@Autowired
	GetIOSHomePageService getIOSHomePageService;

	@RequestMapping("/homePage")
	public void getAndroidHomePage(HttpServletRequest request,
			HttpServletResponse response) {		
		String resultGson = getIOSHomePageService.getIOSHomePage("",request);
		writerGson(resultGson,request, response);
	}
}
