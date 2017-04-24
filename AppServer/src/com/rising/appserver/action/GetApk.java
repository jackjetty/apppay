package com.rising.appserver.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rising.appserver.common.HttpRequestDeviceUtils;
import com.rising.appserver.service.GetApkService;

@Controller
@RequestMapping("/apk")
public class GetApk {

	@Autowired
	GetApkService getApkService;

	@RequestMapping("/hbpay.apk")
	public void getApk(HttpServletRequest request, HttpServletResponse response) {
		try {
			String from = request.getParameter("From");
			String ip = HttpRequestDeviceUtils.getIpAddr(request);
			String from2 = from;
			from = "From=" + from+"&Ip="+ip;
			String filePath = request.getSession().getServletContext()
					.getRealPath("/apk/hbpay-" + from2 + ".apk");
			File file = new File(filePath);
			byte[] data = null;
			if (file.exists()) {
				FileInputStream in = new FileInputStream(file);
				ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
				byte[] cache = new byte[1024];
				int nRead = 0;
				while ((nRead = in.read(cache)) != -1) {
					out.write(cache, 0, nRead);
					out.flush();
				}
				out.close();
				in.close();
				data = out.toByteArray();
			}
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("application/vnd.android.package-archive");
			response.addHeader("Content-Disposition",
					"attachment;filename=hbpay.apk");
			ServletOutputStream sos;
			sos = response.getOutputStream();
			sos.write(data);
			sos.flush();
			sos.close();
			getApkService.writeDownload(from,request);
		} catch (IOException e) {
		}
		
	}

}
