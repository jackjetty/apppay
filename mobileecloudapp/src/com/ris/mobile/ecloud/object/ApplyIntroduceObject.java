package com.ris.mobile.ecloud.object;
import java.io.Serializable;
 
public class ApplyIntroduceObject  implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8598257851164270599L;
	private String applyId;
	private String introduce;
	private String applyType;
	private boolean applyAble;
	private String prompt;
	
	 
  
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public boolean isApplyAble() {
		return applyAble;
	}
	public void setApplyAble(boolean applyAble) {
		this.applyAble = applyAble;
	}
	
}