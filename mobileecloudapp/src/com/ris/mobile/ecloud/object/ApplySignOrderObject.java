package com.ris.mobile.ecloud.object;
import java.io.Serializable;
public class ApplySignOrderObject  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3120371253041624672L;
	 
	// {"message":"成功","result":1,"data":{" ":1," ":"","":"丁丁","":"校外测试只报名","":"33060219861107303x","studNo":null,"":"E160510184104306371","mobile":"18072749082"}}

	private String userName;
	private String dealNumber;
	private String mobile;
	private String applyTitle;
	private String studNo;
	private String idNum;
	private String status;
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
	public String getStudNo() {
		return studNo;
	}
	public void setStudNo(String studNo) {
		this.studNo = studNo;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	} 
	 
	 
	
	  
}