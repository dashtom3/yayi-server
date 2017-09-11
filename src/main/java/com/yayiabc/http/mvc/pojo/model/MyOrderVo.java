package com.yayiabc.http.mvc.pojo.model;

public class MyOrderVo {
	private String saleId;

	private String orderCreated;

	private String userName;

	private String userPhone;

	private String userPic;

	private String orderId;
	
	private double allMoney; // 销售额

	private double haocaiMoney; // 耗材销售额

	private double gongjuMoney; // 工具销售额

	private double refundMoney; // 已退金额

	private double actualMoney; // 实际销售额

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

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public double getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(double allMoney) {
		this.allMoney = allMoney;
	}

	public double getHaocaiMoney() {
		return haocaiMoney;
	}

	public void setHaocaiMoney(double haocaiMoney) {
		this.haocaiMoney = haocaiMoney;
	}

	public double getGongjuMoney() {
		return gongjuMoney;
	}

	public void setGongjuMoney(double gongjuMoney) {
		this.gongjuMoney = gongjuMoney;
	}

	public double getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(double refundMoney) {
		this.refundMoney = refundMoney;
	}

	public double getActualMoney() {
		return actualMoney;
	}

	public void setActualMoney(double actualMoney) {
		this.actualMoney = actualMoney;
	}

	public SaleDataStatistics getSaleDataStatistics() {
		return saleDataStatistics;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setSaleDataStatistics(SaleDataStatistics saleDataStatistics) {
		this.saleDataStatistics = saleDataStatistics;
	}

	public MyOrderVo() {
		super();
	}

	public MyOrderVo(String saleId,String orderId, String orderCreated, String userName,
			String userPhone, double allMoney, double haocaiMoney,
			double gongjuMoney, double refundMoney, double actualMoney,String userPic,
			SaleDataStatistics saleDataStatistics) {
		super();
		this.orderId=orderId;
		this.saleId = saleId;
		this.orderCreated = orderCreated;
		this.userName = userName;
		this.userPhone = userPhone;
		this.allMoney = allMoney;
		this.haocaiMoney = haocaiMoney;
		this.gongjuMoney = gongjuMoney;
		this.refundMoney = refundMoney;
		this.actualMoney = actualMoney;
		this.userPic=userPic;
		this.saleDataStatistics = saleDataStatistics;
	}

	@Override
	public String toString() {
		return "MyOrderVo [saleId=" + saleId + ", orderCreated=" + orderCreated
				+ ", userName=" + userName + ", userPhone=" + userPhone
				+ ", allMoney=" + allMoney + ", haocaiMoney=" + haocaiMoney
				+ ", gongjuMoney=" + gongjuMoney + ", refundMoney="
				+ refundMoney + ", actualMoney=" + actualMoney
				+ ", saleDataStatistics=" + saleDataStatistics + "]";
	}

}
