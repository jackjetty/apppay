package com.ris.mobile.ecloud.object;
import java.io.Serializable;
import java.util.List;
public class ApplySPObject  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3774132062505412690L;
	
	 
	
	private String applyId;
	private String applyTitle;
	private String applyAmountName;
	private String applyAmountValue;
	private String userId;
	private String userName; 
	private String idNum;
	private String mobile;
	private String skuLabel;
	private List<SkuObject> skuList; 
	
	private String sex;
	private String labelOther1;
	private String hintOther1;
	private String labelOther2;
	private String hintOther2;
	private String remark;
	
	
	public String getSkuLabel() {
		return skuLabel;
	}
	public void setSkuLabel(String skuLabel) {
		this.skuLabel = skuLabel;
	}
	public List<SkuObject> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<SkuObject> skuList) {
		this.skuList = skuList;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getApplyAmountName() {
		return applyAmountName;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getApplyTitle() {
		return applyTitle;
	}
	public void setApplyTitle(String applyTitle) {
		this.applyTitle = applyTitle;
	}
	public void setApplyAmountName(String applyAmountName) {
		this.applyAmountName = applyAmountName;
	}
	public String getApplyAmountValue() {
		return applyAmountValue;
	}
	public void setApplyAmountValue(String applyAmountValue) {
		this.applyAmountValue = applyAmountValue;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	 
	 
	 
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLabelOther1() {
		return labelOther1;
	}
	public void setLabelOther1(String labelOther1) {
		this.labelOther1 = labelOther1;
	}
	public String getHintOther1() {
		return hintOther1;
	}
	public void setHintOther1(String hintOther1) {
		this.hintOther1 = hintOther1;
	}
	public String getLabelOther2() {
		return labelOther2;
	}
	public void setLabelOther2(String labelOther2) {
		this.labelOther2 = labelOther2;
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
	 
	
	
}