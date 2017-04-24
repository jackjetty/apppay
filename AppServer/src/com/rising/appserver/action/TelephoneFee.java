package com.rising.appserver.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


//
//import com.google.gson.Gson;
import com.rising.appserver.common.BaseAction;
import com.rising.appserver.common.MD5;
import com.rising.appserver.service.TelephoneFeeService;


@Controller
@RequestMapping("/telephoneFee")
public class TelephoneFee extends BaseAction{
	
	Log log = LogFactory.getLog(TelephoneFee.class);
	
	@Autowired TelephoneFeeService telephoneFeeService;
	
	@RequestMapping("/record")
	public void record(HttpServletRequest request,HttpServletResponse response){
		String json2 = "";
		JSONObject jsonObject = null;
		try {
			json2 = getRequestXMLStringFromRequest(request);
			jsonObject = JSONObject.fromObject(json2);
		} catch (IOException e) {
			log.error(e);
		}
		String key = "ransi_mobile96e79218965eb72_20140103";
		 
		String sign_type =  jsonObject.getString("sign_type");
		String sign = jsonObject.getString("sign");
		String dt_order = jsonObject.getString("dt_order");
		String mb_cust = jsonObject.getString("mb_cust");
		String money_order= jsonObject.getString("money_order");
		String no_order = jsonObject.getString("no_order");
		String price_goods =   jsonObject.getString("price_goods");
		String sta_order= jsonObject.getString("sta_order");
		String parameter = "dt_order="+dt_order+"&mb_cust="+mb_cust+"&money_order="+money_order+"&no_order="+no_order+"&price_goods="+price_goods+"&sign_type="+sign_type+"&sta_order="+sta_order+"&key="+key;
		String resultGson = null;
		if(sign!=null && !"".equals(sign) && sign.equals(MD5.encryptByMD5With32Bit(parameter))){
			HashMap<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("no_order", no_order);
			paraMap.put("dt_order", dt_order);
			paraMap.put("mb_cust", mb_cust);
			paraMap.put("price_goods", price_goods);
			paraMap.put("money_order", money_order);
			paraMap.put("sta_order", sta_order);
			String parameter2 = mapToString(paraMap);
			resultGson = telephoneFeeService.addRecord(parameter2,request);
		}else{
			HashMap<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("ret_code", "1111");
			paraMap.put("ret_msg", "交易失败");
		}
		writerGson(resultGson, request,response);
	}
	
}
