package com.ris.mobile.ecloud.object;
import java.io.Serializable;
import java.util.List;
public class ApplyPayObject  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3774332061505412681L;
	private String applyId;
	private String applyTitle;
	private String applyAmountName;
	private String applyAmountValue;
	
	private String skuLabel;
	private List<SkuObject> skuList; 
	  
	 
	private String labelOther1;
	private String hintOther1;
	private String labelOther2;
	private String hintOther2;
	private String sex; 
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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