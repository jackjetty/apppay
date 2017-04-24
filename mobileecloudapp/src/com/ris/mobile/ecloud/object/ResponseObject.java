package com.ris.mobile.ecloud.object; 

import java.util.*;
import java.io.Serializable;
public class ResponseObject  implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4649196565269940767L;
	private boolean success;
	private String info;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}