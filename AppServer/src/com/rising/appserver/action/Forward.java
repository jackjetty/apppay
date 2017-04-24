package com.rising.appserver.action; 
import java.util.HashMap; 
import java.util.Enumeration; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rising.appserver.common.HttpUtil;
import com.rising.appserver.common.BaseAction;
@Controller
@RequestMapping("/forward")
public class Forward extends BaseAction {
	Log log = LogFactory.getLog(Forward.class);
	@RequestMapping("/flow")
	public void flow(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,String> map = new HashMap<String,String>();
        Enumeration<String> en = request.getParameterNames();
        String param;
        String value;
        String forwardUrl="";  
        while(en.hasMoreElements()){
                 param = en.nextElement().toString();
                 value = request.getParameter(param);
                 if(param.equalsIgnoreCase("forwardIp")){
                	 forwardUrl=value;
                	 continue;
                 }
                 map.put(param, value); 
        } 
        if(forwardUrl.equalsIgnoreCase("")){
        	log.error("flow()->forwardIp不存在"); 
        	return;
        } 
        forwardUrl="http://"+forwardUrl; 
        response.setCharacterEncoding("UTF-8");
        try{
        	response.getWriter().write(HttpUtil.submitData(forwardUrl,map));
    		response.getWriter().close();
        }catch (Exception e) {
			log.error("flow()->输出返回数据时出错！");
		} 
	}
}