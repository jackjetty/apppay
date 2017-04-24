package com.rising.general.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.appserver.common.HttpRequestDeviceUtils;
import com.rising.general.common.BaseAction;
import com.rising.general.service.UserService;

@Controller
@RequestMapping("/general/user")
public class UserAction extends BaseAction {

	@Autowired
	UserService gUserService;

	@RequestMapping("/register")
	public void register(HttpServletRequest request,
			HttpServletResponse response) {
		String resultGson = "";
		String ipAddress = HttpRequestDeviceUtils.getIpAddr(request);
		try {
			String XMLString = getRequestXMLStringFromRequest(request);
			resultGson = gUserService.register(XMLString,ipAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}
		writerGson(resultGson, response);
	}

}
