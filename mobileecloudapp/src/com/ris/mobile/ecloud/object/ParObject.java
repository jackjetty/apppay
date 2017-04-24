package com.ris.mobile.ecloud.object;
import java.io.Serializable;
public class ParObject  implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7549769054989543604L;
	private String parCode;
	private String parName;
	private String parValue;
	
	public String getParValue() {
		return parValue;
	}
	public void setParValue(String parValue) {
		this.parValue = parValue;
	}
	public String getParCode() {
		return parCode;
	}
	public void setParCode(String parCode) {
		this.parCode = parCode;
	}
	public String getParName() {
		return parName;
	}
	public void setParName(String parName) {
		this.parName = parName;
	}
	
}