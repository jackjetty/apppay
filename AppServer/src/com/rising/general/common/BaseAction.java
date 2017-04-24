package com.rising.general.common;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

@Controller("gBaseAction")
public class BaseAction {

	Log log = LogFactory.getLog(BaseAction.class);

	public String getRequestXMLStringFromRequest(HttpServletRequest request)
			throws IOException {
		BufferedReader br = null;
		StringBuilder rs = new StringBuilder();
		String line;
		try {
			br = request.getReader();
			while ((line = br.readLine()) != null) {
				rs.append(line);
			}
			br.close();
		} catch (IOException e) {
			log.error("getRequestXMLStringFromRequest()->获取xml请求报文出错！"
					+ e.toString());
			throw e;
		}

		return rs.toString();
	}

	public void writerGson(String GsonString, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(GsonString);
			response.getWriter().close();
		} catch (Exception e) {
			log.error("writerGson()->输出返回数据时出错！");
		}
	}

	
}
