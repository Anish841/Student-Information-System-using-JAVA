package org.iiitb.sis.model;

public class Notification {

	private String userId;
	private String notiName;
	private String notiDetail;
	private int len;
	private String seen;
	private String notiType;
	private String url;
	
	public String getNotiType() {
		return notiType;
	}
	public void setNotiType(String notiType) {
		this.notiType = notiType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSeen() {
		return seen;
	}
	public void setSeen(String seen) {
		this.seen = seen;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public String getNotiDetail() {
		return notiDetail;
	}
	public void setNotiDetail(String notiDetail) {
		this.notiDetail = notiDetail;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNotiName() {
		return notiName;
	}
	public void setNotiName(String notiName) {
		this.notiName = notiName;
	}
	
}
