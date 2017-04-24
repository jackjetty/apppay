package com.ris.mobile.ecloud.parser;

import org.json.JSONException;
import org.json.JSONObject; 

import com.alibaba.fastjson.JSON;  
import com.ris.mobile.ecloud.object.ApplySPObject;
import com.ris.mobile.ecloud.object.ConnectErrorObject;  
import com.ris.mobile.ecloud.object.SkuObject;
import com.ris.mobile.ecloud.util.CommonUtil;
public class ApplySPParser extends BaseParser<ApplySPObject>  { 
		@Override
		public ApplySPObject parseJSON(String paramString)  { 
			checkResponse(paramString);  
			if (this.connectErrorObject.getErrCode()==0) {   
				try{
					ApplySPObject applySPObject=JSON.parseObject(resultData, ApplySPObject.class);
					
					JSONObject jsonObject = new JSONObject(resultData); 
					String skuList= jsonObject.getString("skuList");  
					if(!CommonUtil.trim(skuList).equals("")){
						applySPObject.setSkuList (JSON.parseArray(skuList, SkuObject.class));  
					}
					return applySPObject;
				}catch(Exception ex){ 
					connectErrorObject.setErrCode (101);
					connectErrorObject.setErrInfo ("返回值格式有误！！");
					return null;
				}  
			}
			return null;  
	   }  
}