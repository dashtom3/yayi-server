package com.yayiabc.http.mvc.pojo.model;

import java.util.List;

public class SaleIncomeVo {
	private String saleId; // 销售员编号
	private String saleName; // 销售员姓名
	private String salePhone; // 销售员手机号
	private String userId; // 用户编号
	private String userName; // 用户姓名
	private String userPhone; // 用户手机号
	private String getState; // 收入状态
	private double getMoney;	//收入
	private String getUpdated; // 结算日期
	private String beYearMonth; // 收入所属月份
	private String settlementTime; // 结算时间
	private double allMoney; // 销售额
	private double allActual; // 实际销售额
	private List<OrderVo> orderVoList;
	private SaleDataStatistics saleDataStatistics;

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public String getSalePhone() {
		return salePhone;
	}

	public void setSalePhone(String salePhone) {
		this.salePhone = salePhone;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public double getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(double allMoney) {
		this.allMoney = allMoney;
	}

	public double getAllActual() {
		return allActual;
	}

	public void setAllActual(double allActual) {
		this.allActual = allActual;
	}

	public List<OrderVo> getOrderVoList() {
		return orderVoList;
	}

	public void setOrderVoList(List<OrderVo> orderVoList) {
		this.orderVoList = orderVoList;
	}

	public SaleDataStatistics getSaleDataStatistics() {
		return saleDataStatistics;
	}

	public void setSaleDataStatistics(SaleDataStatistics saleDataStatistics) {
		this.saleDataStatistics = saleDataStatistics;
	}

	public String getBeYearMonth() {
		return beYearMonth;
	}

	public void setBeYearMonth(String beYearMonth) {
		this.beYearMonth = beYearMonth;
	}

	public String getSettlementTime() {
		return settlementTime;
	}

	public void setSettlementTime(String settlementTime) {
		this.settlementTime = settlementTime;
	}

	public double getGetMoney() {
		return getMoney;
	}

	public void setGetMoney(double getMoney) {
		this.getMoney = getMoney;
	}

	public SaleIncomeVo() {
		super();
	}

	public SaleIncomeVo(String saleId, String saleName, String salePhone,
			String userId, String userName, String userPhone, String getState,
			String getUpdated, String beYearMonth, String settlementTime,
			double allMoney,double getMoney, double allActual, List<OrderVo> orderVoList,
			SaleDataStatistics saleDataStatistics) {
		super();
		this.saleId = saleId;
		this.saleName = saleName;
		this.salePhone = salePhone;
		this.userId = userId;
		this.userName = userName;
		this.getMoney=getMoney;
		this.userPhone = userPhone;
		this.getState = getState;
		this.getUpdated = getUpdated;
		this.beYearMonth = beYearMonth;
		this.settlementTime = settlementTime;
		this.allMoney = allMoney;
		this.allActual = allActual;
		this.orderVoList = orderVoList;
		this.saleDataStatistics = saleDataStatistics;
	}

	@Override
	public String toString() {
		return "SaleIncomeVo [saleId=" + saleId + ", saleName=" + saleName
				+ ", salePhone=" + salePhone + ", userId=" + userId
				+ ", userName=" + userName + ", userPhone=" + userPhone
				+ ", getState=" + getState + ", getUpdated=" + getUpdated
				+ ", beYearMonth=" + beYearMonth + ", settlementTime="
				+ settlementTime + ", allMoney=" + allMoney + ", allActual="
				+ allActual + ", orderVoList=" + orderVoList
				+ ", saleDataStatistics=" + saleDataStatistics + "]";
	}

}
