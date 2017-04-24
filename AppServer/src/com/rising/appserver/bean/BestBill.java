package com.rising.appserver.bean;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("ROOT")
public class BestBill {
	
	
	private String balanceFileId;
	private String statisticFromDate;
	private String statisticEndDate;
	private String countSum;
	private String successCountSum;
	private String failCountSum;
	private String moneySum;
	private String successMoneySum;
	private String failMoneySum; 

    // 隐式集合，加上这个注解可以去掉book集合最外面的<list></list>这样的标记
    @XStreamImplicit
    private List<TradeDetail> tradeDetailList;

	public List<TradeDetail> getTradeDetailList() {
		return tradeDetailList;
	}

	public void setTradeDetailList(List<TradeDetail> tradeDetailList) {
		this.tradeDetailList = tradeDetailList;
	}

	public String getBalanceFileId() {
		return balanceFileId;
	}

	public void setBalanceFileId(String balanceFileId) {
		this.balanceFileId = balanceFileId;
	}

	public String getStatisticFromDate() {
		return statisticFromDate;
	}

	public void setStatisticFromDate(String statisticFromDate) {
		this.statisticFromDate = statisticFromDate;
	}

	public String getStatisticEndDate() {
		return statisticEndDate;
	}

	public void setStatisticEndDate(String statisticEndDate) {
		this.statisticEndDate = statisticEndDate;
	}

	public String getCountSum() {
		return countSum;
	}

	public void setCountSum(String countSum) {
		this.countSum = countSum;
	}

	public String getSuccessCountSum() {
		return successCountSum;
	}

	public void setSuccessCountSum(String successCountSum) {
		this.successCountSum = successCountSum;
	}

	public String getFailCountSum() {
		return failCountSum;
	}

	public void setFailCountSum(String failCountSum) {
		this.failCountSum = failCountSum;
	}

	public String getMoneySum() {
		return moneySum;
	}

	public void setMoneySum(String moneySum) {
		this.moneySum = moneySum;
	}

	public String getSuccessMoneySum() {
		return successMoneySum;
	}

	public void setSuccessMoneySum(String successMoneySum) {
		this.successMoneySum = successMoneySum;
	}

	public String getFailMoneySum() {
		return failMoneySum;
	}

	public void setFailMoneySum(String failMoneySum) {
		this.failMoneySum = failMoneySum;
	}
    

   
}