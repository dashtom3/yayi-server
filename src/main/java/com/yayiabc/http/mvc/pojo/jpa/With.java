package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class With {
	private Integer cashId;
	private String trueName;
	private  String phone;
	private  int cashMoney;
	private  String type;
	private  String accountUser;
	private  String bank;
	private  String anumber;
	private  String appTime;
	private  String cashState;
	private Date cashSuTime;
    private SaleInfo saleInfo;
    private String saleId;
    
	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public SaleInfo getSaleInfo() {
		return saleInfo;
	}
	public void setSaleInfo(SaleInfo saleInfo) {
		this.saleInfo = saleInfo;
	}
	public Date getCashSuTime() {
		return cashSuTime;
	}
	public void setCashSuTime(Date cashSuTime) {
		this.cashSuTime = cashSuTime;
	}
	public Integer getCashId() {
		return cashId;
	}
	public void setCashId(Integer cashId) {
		this.cashId = cashId;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public void setCashMoney(int cashMoney) {
		this.cashMoney = cashMoney;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getCashMoney() {
		return cashMoney;
	}
	public void setCashMoney(Integer cashMoney) {
		this.cashMoney = cashMoney;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccountUser() {
		return accountUser;
	}
	public void setAccountUser(String accountUser) {
		this.accountUser = accountUser;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAnumber() {
		return anumber;
	}
	public void setAnumber(String anumber) {
		this.anumber = anumber;
	}
	public String getAppTime() {
		return appTime;
	}
	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}
	public String getCashState() {
		return cashState;
	}
	public void setCashState(String cashState) {
		this.cashState = cashState;
	}
	@Override
	public String toString() {
		return "With [cashId=" + cashId + ", trueName=" + trueName + ", phone=" + phone + ", cashMoney=" + cashMoney
				+ ", type=" + type + ", accountUser=" + accountUser + ", bank=" + bank + ", anumber=" + anumber
				+ ", appTime=" + appTime + ", cashState=" + cashState + ", cashSuTime=" + cashSuTime + ", saleInfo="
				+ saleInfo + ", saleId=" + saleId + "]";
	}
	

}
