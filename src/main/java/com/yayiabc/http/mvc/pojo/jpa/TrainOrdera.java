package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class TrainOrdera {
	private Integer trainOrderId;
	private Integer trainId;
	private String userId;
	private String weChatNumber;
	private String phoneNumber;
	private Integer qbDed;
	private String actualPrice;
	private Integer payType;
	private String unitPrice;
	private Integer state;
	private Date signUpTime;
	public Integer getTrainOrderId() {
		return trainOrderId;
	}
	public void setTrainOrderId(Integer trainOrderId) {
		this.trainOrderId = trainOrderId;
	}
	public Integer getTrainId() {
		return trainId;
	}
	
	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getWeChatNumber() {
		return weChatNumber;
	}
	public void setWeChatNumber(String weChatNumber) {
		this.weChatNumber = weChatNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getQbDed() {
		return qbDed;
	}
	public void setQbDed(Integer qbDed) {
		this.qbDed = qbDed;
	}
	public String getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getSignUpTime() {
		return signUpTime;
	}
	public void setSignUpTime(Date signUpTime) {
		this.signUpTime = signUpTime;
	}

}
