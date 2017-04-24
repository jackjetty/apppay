package com.rising.appserver.pojo;

import java.sql.Timestamp;

/**
 * TbRole2menu entity. @author MyEclipse Persistence Tools
 */

public class TbQQCookieState implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4435066547147950859L;
	private TbQQCookieStateId id;
	private String ckDomain;
	private String ckValue;
	private String ckPath;
	private Timestamp ckExpires;
	
	private Boolean ckSecure;
	private Timestamp lastDate;
	private String remark;
	public TbQQCookieStateId getId() {
		return id;
	}
	public void setId(TbQQCookieStateId id) {
		this.id = id;
	}
	public String getCkDomain() {
		return ckDomain;
	}
	public void setCkDomain(String ckDomain) {
		this.ckDomain = ckDomain;
	}
	public String getCkValue() {
		return ckValue;
	}
	public void setCkValue(String ckValue) {
		this.ckValue = ckValue;
	}
	public String getCkPath() {
		return ckPath;
	}
	public void setCkPath(String ckPath) {
		this.ckPath = ckPath;
	}
	public Timestamp getCkExpires() {
		return ckExpires;
	}
	public void setCkExpires(Timestamp ckExpires) {
		this.ckExpires = ckExpires;
	}
	public Boolean getCkSecure() {
		return ckSecure;
	}
	public void setCkSecure(Boolean ckSecure) {
		this.ckSecure = ckSecure;
	}
	public Timestamp getLastDate() {
		return lastDate;
	}
	public void setLastDate(Timestamp lastDate) {
		this.lastDate = lastDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}