package com.yayiabc.http.mvc.pojo.jpa;

public class WXEntry {
	private String appId;
	private String timestamp;
	private String nonceStr;
	private String signature;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	@Override
	public String toString() {
		return "WXEntry [appId=" + appId + ", timestamp=" + timestamp + ", nonceStr=" + nonceStr + ", signature="
				+ signature + "]";
	}
	

	
}
