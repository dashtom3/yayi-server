package com.yayiabc.http.mvc.pojo.model;

public class MyOrderVo {
	private String saleId;

	private String orderCreated;

	private String userName;

	private String userPhone;

	private String itemName;

	private String totalFee;

	private String refundMoney;

	private String getMoney;

	private String getState;

	private String getUpdated;

	private SaleDataStatistics saleDataStatistics;

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(String orderCreated) {
		this.orderCreated = orderCreated;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(String refundMoney) {
		this.refundMoney = refundMoney;
	}

	public String getGetMoney() {
		return getMoney;
	}

	public void setGetMoney(String getMoney) {
		this.getMoney = getMoney;
	}

	public String getGetState() {
		return getState;
	}

	public void setGetState(String getState) {
		this.getState = getState;
	}

	public String getGetUpdated() {
		return getUpdated;
	}

	public void setGetUpdated(String getUpdated) {
		this.getUpdated = getUpdated;
	}

	public SaleDataStatistics getSaleDataStatistics() {
		return saleDataStatistics;
	}

	public void setSaleDataStatistics(SaleDataStatistics saleDataStatistics) {
		this.saleDataStatistics = saleDataStatistics;
	}

	public MyOrderVo() {
		super();
	}

	public MyOrderVo(String saleId, String orderCreated, String userName,
			String userPhone, String itemName, String totalFee,
			String refundMoney, String getMoney, String getState,
			String getUpdated, SaleDataStatistics saleDataStatistics) {
		super();
		this.saleId = saleId;
		this.orderCreated = orderCreated;
		this.userName = userName;
		this.userPhone = userPhone;
		this.itemName = itemName;
		this.totalFee = totalFee;
		this.refundMoney = refundMoney;
		this.getMoney = getMoney;
		this.getState = getState;
		this.getUpdated = getUpdated;
		this.saleDataStatistics = saleDataStatistics;
	}

	@Override
	public String toString() {
		return "MyOrderVo [saleId=" + saleId + ", orderCreated=" + orderCreated
				+ ", userName=" + userName + ", userPhone=" + userPhone
				+ ", itemName=" + itemName + ", totalFee=" + totalFee
				+ ", refundMoney=" + refundMoney + ", getMoney=" + getMoney
				+ ", getState=" + getState + ", getUpdated=" + getUpdated
				+ ", saleDataStatistics=" + saleDataStatistics + "]";
	}

}
