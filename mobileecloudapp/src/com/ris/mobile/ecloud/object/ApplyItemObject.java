package com.ris.mobile.ecloud.object;
import java.io.Serializable;

import android.graphics.drawable.Drawable;
public class ApplyItemObject  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3980794758518345212L;
	private String applyId;
	private String applyUrl;
	private String applyTitle;
	private String applyPeriod;
	private String applySurplus;
	
	 
	public String getApplyUrl() {
		return applyUrl;
	}
	public void setApplyUrl(String applyUrl) {
		this.applyUrl = applyUrl;
	}
	public String getApplyTitle() {
		return applyTitle;
	}
	public void setApplyTitle(String applyTitle) {
		this.applyTitle = applyTitle;
	}
	public String getApplyPeriod() {
		return applyPeriod;
	}
	public void setApplyPeriod(String applyPeriod) {
		this.applyPeriod = applyPeriod;
	}
	public String getApplySurplus() {
		return applySurplus;
	}
	public void setApplySurplus(String applySurplus) {
		this.applySurplus = applySurplus;
	}
	
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	 
	
	
	
}