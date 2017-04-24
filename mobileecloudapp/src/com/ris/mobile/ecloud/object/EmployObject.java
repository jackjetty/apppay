package com.ris.mobile.ecloud.object; 
import java.io.Serializable;
public class EmployObject  implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4770786831576436422L;
	private String title;
	private String url;
	private String issueTime; 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}
	 
	
}