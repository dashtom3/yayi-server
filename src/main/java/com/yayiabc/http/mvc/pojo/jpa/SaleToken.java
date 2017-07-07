package com.yayiabc.http.mvc.pojo.jpa;

public class SaleToken {
	private String saleId;
	private String saleToken;
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
	public SaleToken(String saleId, String saleToken) {
		super();
		this.saleId = saleId;
		this.saleToken = saleToken;
	}
	public SaleToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SaleToken [saleId=" + saleId + ", saleToken=" + saleToken + "]";
	}
	
}
