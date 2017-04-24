package com.rising.general.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.appserver.common.HttpRequestDeviceUtils;
import com.rising.general.common.BaseAction;
import com.rising.general.service.ProductService;

@Controller
@RequestMapping("/general/product")
public class ProductAction extends BaseAction {

	@Autowired
	ProductService gProductService;

	@RequestMapping("/getInfo")
	public void getProductInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String ipAddress = HttpRequestDeviceUtils.getIpAddr(request);
		String resultGson = "";
		try {
			String XMLString = getRequestXMLStringFromRequest(request);
			resultGson = gProductService.getInfo(XMLString,ipAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}
		writerGson(resultGson, response);
	}
}
