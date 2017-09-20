package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
public class UserWith {
	/**
	 * 
	 */
	private  String userId;
	private  int withId;
	private double aType;
	private double bType;
	private double cType;
	private double giveType;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss",style="GMT+8")
	private Date created; //发起申请时间
	private Date determineTime;  //批准提现时间
	private int sign;  //提现标志位  1表示 待审核  2  表示审核成功
  //  private int witSetupId;  //提现设置表的主键#{witType},#{accountHolder},#{cardNumber},#{oBank}
	
     private String witType;
     private String accountHolder;
     private  String cardNumber;
     private String oBank;
     

	public Date getDetermineTime() {
		return determineTime;
	}

	public void setDetermineTime(Date determineTime) {
		this.determineTime = determineTime;
	}

	public int getWithId() {
		return withId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setWithId(int withId) {
		this.withId = withId;
	}

	public double getaType() {
		return aType;
	}
	public void setaType(double aType) {
		this.aType = aType;
	}
	public double getbType() {
		return bType;
	}
	public void setbType(double bType) {
		this.bType = bType;
	}
	public double getcType() {
		return cType;
	}
	public void setcType(double cType) {
		this.cType = cType;
	}
	public double getGiveType() {
		return giveType;
	}
	public void setGiveType(double giveType) {
		this.giveType = giveType;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
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

	public UserWith() {
		this.aType = 0;
		this.bType = 0;
		this.cType = 0;
		this.giveType = 0;
		this.sign = 1;
	}
}
