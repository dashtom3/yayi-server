package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class With {
	private Integer cashId;
	private String realName;
	private  String phone;
	private  String cashMoney;
	private  String type;
	private  String accountUser;
	private  String bank;
	private  String anumber;
	private  String appTime;
	private  String cashState;
	private Date cashSuTime;
	
	
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCashMoney() {
		return cashMoney;
	}
	public void setCashMoney(String cashMoney) {
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
		return "With [cashMoney=" + cashMoney + ", cashSuTime=" + cashSuTime
				+ "]";
	}

}
