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
	private String getUpdated; // 结算日期
	private Integer getMoney; // 销售收入金额
	private Integer signLateSeven; // 签收是否已过7天
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



	public Integer getGetMoney() {
		return getMoney;
	}

	public void setGetMoney(Integer getMoney) {
		this.getMoney = getMoney;
	}

	public Integer getSignLateSeven() {
		return signLateSeven;
	}

	public void setSignLateSeven(Integer signLateSeven) {
		this.signLateSeven = signLateSeven;
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

	public SaleIncomeVo() {
		super();
	}

	public SaleIncomeVo(String saleId, String saleName, String salePhone,
			String userId, String userName, String userPhone, String getState,
			String getUpdated, Integer getMoney, Integer signLateSeven,
			List<OrderVo> orderVoList, SaleDataStatistics saleDataStatistics) {
		super();
		this.saleId = saleId;
		this.saleName = saleName;
		this.salePhone = salePhone;
		this.userId = userId;
		this.userName = userName;
		this.userPhone = userPhone;
		this.getState = getState;
		this.getUpdated = getUpdated;
		this.getMoney = getMoney;
		this.signLateSeven = signLateSeven;
		this.orderVoList = orderVoList;
		this.saleDataStatistics = saleDataStatistics;
	}

	@Override
	public String toString() {
		return "SaleIncomeVo [saleId=" + saleId + ", saleName=" + saleName
				+ ", salePhone=" + salePhone + ", userId=" + userId
				+ ", userName=" + userName + ", userPhone=" + userPhone
				+ ", getState=" + getState + ", getUpdated=" + getUpdated
				+ ", getMoney=" + getMoney + ", signLateSeven=" + signLateSeven
				+ ", orderVoList=" + orderVoList + ", saleDataStatistics="
				+ saleDataStatistics + "]";
	}

}
