package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class Charge {
	private String chargeId;
	private String token;
	private int qbNum;
	private Integer state;//1表示未支付 2表示已支付
	private String qbType;
	private String money;
	private Date created;
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getQbType() {
		return qbType;
	}
	public void setQbType(String qbType) {
		this.qbType = qbType;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public int getQbNum() {
		return qbNum;
	}
	public void setQbNum(int qbNum) {
		this.qbNum = qbNum;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
}
