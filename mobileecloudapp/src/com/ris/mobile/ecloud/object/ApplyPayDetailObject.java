package com.ris.mobile.ecloud.object;
import java.io.Serializable;
public class ApplyPayDetailObject  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3773232061505412681L;
	private String userName;
	private String dealNumber;
	private String payNo;
	private String userSex;
	private boolean showPay;
	private boolean showCancel;
	 
	private String applyTitle;
	private String enterTime;
	private String applyAmountName;
	private String hintOther1;
	private String hintOther2;
	private String remark;
	private String status;
	private String labelOther1;
	private String labelOther2;
	private String instruction;
	
	private String skuLabel;
	private String skuName;
	
	public boolean isShowPay() {
		return showPay;
	}
	public void setShowPay(boolean showPay) {
		this.showPay = showPay;
	}
	public boolean isShowCancel() {
		return showCancel;
	}
	public void setShowCancel(boolean showCancel) {
		this.showCancel = showCancel;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDealNumber() {
		return dealNumber;
	}
	public void setDealNumber(String dealNumber) {
		this.dealNumber = dealNumber;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getApplyTitle() {
		return applyTitle;
	}
	public void setApplyTitle(String applyTitle) {
		this.applyTitle = applyTitle;
	}
	public String getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(String enterTime) {
		this.enterTime = enterTime;
	}
	public String getApplyAmountName() {
		return applyAmountName;
	}
	public void setApplyAmountName(String applyAmountName) {
		this.applyAmountName = applyAmountName;
	}
	public String getHintOther1() {
		return hintOther1;
	}
	public void setHintOther1(String hintOther1) {
		this.hintOther1 = hintOther1;
	}
	public String getHintOther2() {
		return hintOther2;
	}
	public void setHintOther2(String hintOther2) {
		this.hintOther2 = hintOther2;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLabelOther1() {
		return labelOther1;
	}
	public void setLabelOther1(String labelOther1) {
		this.labelOther1 = labelOther1;
	}
	public String getLabelOther2() {
		return labelOther2;
	}
	public void setLabelOther2(String labelOther2) {
		this.labelOther2 = labelOther2;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getSkuLabel() {
		return skuLabel;
	}
	public void setSkuLabel(String skuLabel) {
		this.skuLabel = skuLabel;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	
	
	
 

}