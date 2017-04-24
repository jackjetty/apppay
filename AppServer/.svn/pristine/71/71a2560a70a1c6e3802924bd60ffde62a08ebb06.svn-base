package com.rising.general.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.appserver.common.HttpRequestDeviceUtils;
import com.rising.general.common.BaseAction;
import com.rising.general.service.OrderService;

@Controller
@RequestMapping("/general/order")
public class OrderAction extends BaseAction {

	@Autowired
	OrderService gOrderService;

	@RequestMapping("/apply")
	public void apply(HttpServletRequest request, HttpServletResponse response) {
		String resultGson = "";
		String ipAddress = HttpRequestDeviceUtils.getIpAddr(request);
		try {
			String XMLString = getRequestXMLStringFromRequest(request);
			resultGson = gOrderService.apply(XMLString,ipAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}
		writerGson(resultGson, response);
	}
}
