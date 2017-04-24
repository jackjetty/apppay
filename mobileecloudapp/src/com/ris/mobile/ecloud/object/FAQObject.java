package com.ris.mobile.ecloud.object;
 
import java.io.Serializable;
import java.util.List;
public class FAQObject  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5846760875195643051L;
	
	private String title;
	private String content;
	
	 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	} 
}

	 