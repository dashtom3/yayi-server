package com.yayiabc.http.mvc.pojo.jpa;

import java.sql.Date;

public class UserWitSetUp {
	  private int witSetupId; 
     private String userId;
     private String witType;
     private String accountHolder;
     private String cardNumber;
     private String oBank;
     private Date createTime;
     
     private UserWith userWith;
     
	public UserWith getUserWith() {
		return userWith;
	}
	public void setUserWith(UserWith userWith) {
		this.userWith = userWith;
	}
	public int getWitSetupId() {
		return witSetupId;
	}
	public void setWitSetupId(int witSetupId) {
		this.witSetupId = witSetupId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getWitType() {
		return witType;
	}
	public void setWitType(String witType) {
		this.witType = witType;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getoBank() {
		return oBank;
	}
	public void setoBank(String oBank) {
		this.oBank = oBank;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "UserWitSetUp [userId=" + userId + ", witType=" + witType + ", accountHolder=" + accountHolder
				+ ", cardNumber=" + cardNumber + ", oBank=" + oBank + ", createTime=" + createTime + "]";
	}
}
