package com.yayiabc.http.mvc.pojo.model;

import java.util.Date;
import java.util.List;

public class SaleDataVo{
	private Date created;
	
	private double dayCommission; // 日销售额

	private double dayOrderNum; // 日订单量

	private double allCommission; // 累计收入

	private double saleAllMoney; // 销售总额

	private double haocaiAllMoney; // 耗材销售总额

	private double gongjuAllMoney; // 工具销售总额

	private double orderNum; // 订单总数

	private List<MyOrderVo> myOrderVoList;

	public double getDayCommission() {
		return dayCommission;
	}

	public void setDayCommission(double dayCommission) {
		this.dayCommission = dayCommission;
	}

	public double getDayOrderNum() {
		return dayOrderNum;
	}

	public void setDayOrderNum(double dayOrderNum) {
		this.dayOrderNum = dayOrderNum;
	}

	public double getAllCommission() {
		return allCommission;
	}

	public void setAllCommission(double allCommission) {
		this.allCommission = allCommission;
	}

	public double getSaleAllMoney() {
		return saleAllMoney;
	}

	public void setSaleAllMoney(double saleAllMoney) {
		this.saleAllMoney = saleAllMoney;
	}

	public double getHaocaiAllMoney() {
		return haocaiAllMoney;
	}

	public void setHaocaiAllMoney(double haocaiAllMoney) {
		this.haocaiAllMoney = haocaiAllMoney;
	}

	public double getGongjuAllMoney() {
		return gongjuAllMoney;
	}

	public void setGongjuAllMoney(double gongjuAllMoney) {
		this.gongjuAllMoney = gongjuAllMoney;
	}

	public double getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(double orderNum) {
		this.orderNum = orderNum;
	}

	public List<MyOrderVo> getMyOrderVoList() {
		return myOrderVoList;
	}

	public void setMyOrderVoList(List<MyOrderVo> myOrderVoList) {
		this.myOrderVoList = myOrderVoList;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public SaleDataVo() {
		super();
	}

	public SaleDataVo(Date created,double dayCommission, double dayOrderNum,
			double allCommission, double saleAllMoney, double haocaiAllMoney,
			double gongjuAllMoney, double orderNum,
			List<MyOrderVo> myOrderVoList) {
		super();
		this.created=created;
		this.dayCommission = dayCommission;
		this.dayOrderNum = dayOrderNum;
		this.allCommission = allCommission;
		this.saleAllMoney = saleAllMoney;
		this.haocaiAllMoney = haocaiAllMoney;
		this.gongjuAllMoney = gongjuAllMoney;
		this.orderNum = orderNum;
		this.myOrderVoList = myOrderVoList;
	}

	@Override
	public String toString() {
		return "SaleDataVo [dayCommission=" + dayCommission + ", dayOrderNum="
				+ dayOrderNum + ", allCommission=" + allCommission
				+ ", saleAllMoney=" + saleAllMoney + ", haocaiAllMoney="
				+ haocaiAllMoney + ", gongjuAllMoney=" + gongjuAllMoney
				+ ", orderNum=" + orderNum + ", myOrderVoList=" + myOrderVoList
				+ "]";
	}

}
