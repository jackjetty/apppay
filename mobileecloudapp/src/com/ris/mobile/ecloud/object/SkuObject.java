package com.ris.mobile.ecloud.object;
import java.io.Serializable;
public class SkuObject  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 447271574923337179L;
	private String skuName;
	private String price;
	private String limitDesc;
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLimitDesc() {
		return limitDesc;
	}
	public void setLimitDesc(String limitDesc) {
		this.limitDesc = limitDesc;
	}
	
	
}