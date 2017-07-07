package com.yayiabc.http.mvc.pojo.model;

import java.util.List;



public class SaleDataStatistics {
	private Integer overYearHasCommission; // 历年已结算收入

	private Integer allCommission; // 总收入

	private Integer stayCommission; // 待结算

	private Integer hasCommission; // 已结算

	private Integer sumOrderMoney; // 订单总额

	private Integer orderNum; // 订单量

	private Integer dayCommission; // 日收入

	private Integer dayOrderNum; // 日订单量

	private String getUpdated; // 结算日期

	private List<SaleIncomeVo> saleIncomeVoList;

	private List<MyOrderVo> myOrderVoList;

	public Integer getOverYearHasCommission() {
		return overYearHasCommission;
	}

	public void setOverYearHasCommission(Integer overYearHasCommission) {
		this.overYearHasCommission = overYearHasCommission;
	}

	public Integer getAllCommission() {
		return allCommission;
	}

	public void setAllCommission(Integer allCommission) {
		this.allCommission = allCommission;
	}

	public Integer getStayCommission() {
		return stayCommission;
	}

	public void setStayCommission(Integer stayCommission) {
		this.stayCommission = stayCommission;
	}

	public Integer getHasCommission() {
		return hasCommission;
	}

	public void setHasCommission(Integer hasCommission) {
		this.hasCommission = hasCommission;
	}

	public Integer getSumOrderMoney() {
		return sumOrderMoney;
	}

	public void setSumOrderMoney(Integer sumOrderMoney) {
		this.sumOrderMoney = sumOrderMoney;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getDayCommission() {
		return dayCommission;
	}

	public void setDayCommission(Integer dayCommission) {
		this.dayCommission = dayCommission;
	}

	public Integer getDayOrderNum() {
		return dayOrderNum;
	}

	public void setDayOrderNum(Integer dayOrderNum) {
		this.dayOrderNum = dayOrderNum;
	}

	public String getGetUpdated() {
		return getUpdated;
	}

	public void setGetUpdated(String getUpdated) {
		this.getUpdated = getUpdated;
	}

	public List<SaleIncomeVo> getSaleIncomeVoList() {
		return saleIncomeVoList;
	}

	public void setSaleIncomeVoList(List<SaleIncomeVo> saleIncomeVoList) {
		this.saleIncomeVoList = saleIncomeVoList;
	}

	public List<MyOrderVo> getMyOrderVoList() {
		return myOrderVoList;
	}

	public void setMyOrderVoList(List<MyOrderVo> myOrderVoList) {
		this.myOrderVoList = myOrderVoList;
	}

	public SaleDataStatistics() {
		super();
	}

	public SaleDataStatistics(Integer overYearHasCommission,
			Integer allCommission, Integer stayCommission,
			Integer hasCommission, Integer sumOrderMoney, Integer orderNum,
			Integer dayCommission, Integer dayOrderNum, String getUpdated,
			List<SaleIncomeVo> saleIncomeVoList, List<MyOrderVo> myOrderVoList) {
		super();
		this.overYearHasCommission = overYearHasCommission;
		this.allCommission = allCommission;
		this.stayCommission = stayCommission;
		this.hasCommission = hasCommission;
		this.sumOrderMoney = sumOrderMoney;
		this.orderNum = orderNum;
		this.dayCommission = dayCommission;
		this.dayOrderNum = dayOrderNum;
		this.getUpdated = getUpdated;
		this.saleIncomeVoList = saleIncomeVoList;
		this.myOrderVoList = myOrderVoList;
	}

	@Override
	public String toString() {
		return "SaleDataStatistics [overYearHasCommission="
				+ overYearHasCommission + ", allCommission=" + allCommission
				+ ", stayCommission=" + stayCommission + ", hasCommission="
				+ hasCommission + ", sumOrderMoney=" + sumOrderMoney
				+ ", orderNum=" + orderNum + ", dayCommission=" + dayCommission
				+ ", dayOrderNum=" + dayOrderNum + ", getUpdated=" + getUpdated
				+ ", saleIncomeVoList=" + saleIncomeVoList + ", myOrderVoList="
				+ myOrderVoList + "]";
	}

}
