package com.rising.appserver.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.appserver.common.HttpRequestDeviceUtils;

@Controller
@RequestMapping("/user")
public class UserView {
	
	@RequestMapping("/view")
	public void view(HttpServletRequest request,HttpServletResponse response){
		if(HttpRequestDeviceUtils.isMobileDevice(request)){
			try {
				response.sendRedirect("/AppServer/index_for_android.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				response.sendRedirect("/AppServer/index_for_pc.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}




