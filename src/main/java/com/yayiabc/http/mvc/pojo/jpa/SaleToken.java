package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class SaleToken {
	private String saleId;
	private String saleToken;
	private Date loginTime;

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getSaleToken() {
		return saleToken;
	}

	public void setSaleToken(String saleToken) {
		this.saleToken = saleToken;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public SaleToken(String saleId, String saleToken, Date loginTime) {
		this.saleId = saleId;
		this.saleToken = saleToken;
		this.loginTime = loginTime;
	}

	public SaleToken() {
	}
}
