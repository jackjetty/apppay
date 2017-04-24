package com.rising.appserver;

import org.apache.commons.codec.digest.DigestUtils;

import com.rising.appserver.common.DESUtil;

public class UnTest{
	public static void main(String[] args){
	    String 	pword="4ljPBKxsvmOu3XcbotYJNOn9Z0Zo1K2RNG0oqPYYR0mdBW1SD/tpVQgm5WSBpPI4HwyJRe4cQg0Y5sJU5s/9MtPsTYP5CEzioyvvF/i0622NHHiFbWf3wDmOF9lC+KnJBvUPfQrU0I0MaxLX00IxWLkHYBgU42Zbx4aLOUP0VauOe3u2wV0ix7gtvBq1xafRAKDxeMZiMRANsmIFOH6sB4u5YNWTgpw+SmIBcmaAcM2LV1CtBSoM7RIJlews8DwPO+oGBqcLum4R5MekV81iARK7wbUV+bQh6hq055+YqNzk3YqhkQO01wIsgBJFhUJYVvNhFzCaMPamldYTp+LBRKGUYM+G+QeyzRNClDR+mRJcXT4gBPxOFc/L8Q/JhjUfSJZtRAtAZRmmMYLX5OzB+mvDwlCgrzKe/elVdFoQoOfqqzl7baFrzvVbbPFO5gDXECh39AO9YDIw7FSapTOqDPHz8r7hu1betD9nQXLv4l0r6lyEF5cdv37t8Fj2eBhsO48N3l3v+zUO04jfQWIKli2xvl/7FYpb0Fbc/LrqlyB8+nfaebl0jA==";
		 
		System.out.println(DESUtil.decryptMode("BESTPAYQCOIN", pword));
		/*
		 * <?xml version="1.0" encoding="utf-8" standalone="no"?>
<ROOT FuncName="rechargeQQ">
  <paymentNumber></paymentNumber>
  <phoneNumber></phoneNumber>
  <qqNumber></qqNumber>
  <productId></productId>
  <paymoney>0.10</paymoney>
  <srcsysid></srcsysid>
  <inputtime></inputtime>
  <clientip></clientip>
  <sign>e4d94752e0724d64b1a487feac127a1a</sign>
</ROOT>

		
		
		
		+paymentNumber值
        ++phoneNumber值
        ++qqNumber值
++productId值
++paymoney值
++srcsysid值
++inputtime值
++clientip值
+KEY_MD5 */
		
		String f = DigestUtils.md5Hex( "paymentNumber1231"
		+"phoneNumber123123"
				+"qqNumber12123"
		+"productId16019"
				+"paymoney0.10"
		+"srcsysidbestpay"
				+"inputtime20150821103713"
		+"clientip127.0.0.1"
				+"w168CZBPtR7scVM1Miv4gD5");  
		System.out.println(f);
	}
}