package com.rising.appserver;

import com.rising.appserver.bean.BestBill;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlTest{
	public static void main(String[] args){
        StringBuffer xmlBuffer=new StringBuffer("");
        xmlBuffer.append("<?xml version='1.0' encoding='UTF-8'?>");
        xmlBuffer.append("<ROOT FuncName='bestBalance'  >");
        xmlBuffer.append("<balanceFileId>对账单文件编号(可以采取日期格式，保持唯一性即可)</balanceFileId>");
        xmlBuffer.append("<statisticFromDate>账单统计开始时间(格式:yyyy-MM-dd HH:mm:ss)</statisticFromDate>");
        xmlBuffer.append("<statisticEndDate>账单统计结束时间(格式:yyyy-MM-dd HH:mm:ss)</statisticEndDate>");
        xmlBuffer.append("<countSum>支付成功总笔数(数字)</countSum>");
        xmlBuffer.append("<successCountSum>所有充值成功的交易笔数(数字)</successCountSum>");
        xmlBuffer.append("<failCountSum>所有充值失败的交易笔数(数字)</failCountSum>");
        xmlBuffer.append("<moneySum>支付成功总金额(数字，保留后2位)</moneySum>");
        xmlBuffer.append("<successMoneySum>所有充值成功的支付交易金额(数字，保留后2位)</successMoneySum>");
        xmlBuffer.append("<failMoneySum>所有充值失败的支付交易金额(数字，保留后2位)</failMoneySum>");
        xmlBuffer.append("<tradeDetail>");
        xmlBuffer.append("<tradeNo>流水号</tradeNo>");
        xmlBuffer.append("<phoneNumber>手机号码</phoneNumber>");
        xmlBuffer.append("<qqNumber>QQ号码</qqNumber>");
        xmlBuffer.append("<productId>充值产品编号</productId>");
        xmlBuffer.append("<payMoney>支付金额</payMoney>");
        xmlBuffer.append("<tradeDate>支付时间(格式:yyyy-MM-dd HH:mm:ss)</tradeDate>");
        xmlBuffer.append("<chargeStatus>充值状态(成功：success 失败： fail)</chargeStatus> ");
        xmlBuffer.append("</tradeDetail>");
        xmlBuffer.append("");
        xmlBuffer.append("</ROOT>");
        xmlBuffer.append("");
        xmlBuffer.append("");
        xmlBuffer.append("");	    
		          
		          
		          
		          
		 
        XStream xstream = new XStream(new DomDriver());
        
        xstream.processAnnotations(BestBill.class);
        //xstream.omitField(BestBalance.class, "balanceFileId");
        @SuppressWarnings("unchecked")
        BestBill bestBalance = (BestBill) xstream.fromXML(xmlBuffer.toString());
        System.out.println(bestBalance.getBalanceFileId());
        System.out.println(bestBalance.getTradeDetailList().size());
		         
		 
		
		      
		      
		      
		      
		      
		            
		            
		
		 
		
	}
}