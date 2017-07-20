package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class SaleMyWalletDetail {
	private String itemClassify;//商品类型
	private Double totalMoney;//销售额
	private Double refundMoney;//已退款金额
	private Double realMoney;//实际销售额
	private Double orderMoney;//收入
	private Double rewardMoney;//奖励金额
	private Double money;//进账总额
	private Date changeTime;//收入或者支出时间
	private String describe;//收入或者支出描述
	public String getItemClassify() {
		return itemClassify;
	}
	public void setItemClassify(String itemClassify) {
		this.itemClassify = itemClassify;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Double getRefundMoney() {
		return refundMoney;
	}
	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
		this.realMoney=this.totalMoney-this.refundMoney;
	}
	public Double getRealMoney() {
		return realMoney;
	}
	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}
	public Double getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}
	public Double getRewardMoney() {
		return rewardMoney;
	}
	public void setRewardMoney(Double rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public SaleMyWalletDetail(String itemClassify, Double totalMoney,
			Double refundMoney, Double realMoney, Double orderMoney,
			Double rewardMoney, Double money, Date changeTime, String describe) {
		super();
		this.itemClassify = itemClassify;
		this.totalMoney = totalMoney;
		this.refundMoney = refundMoney;
		this.realMoney = realMoney;
		this.orderMoney = orderMoney;
		this.rewardMoney = rewardMoney;
		this.money = money;
		this.changeTime = changeTime;
		this.describe = describe;
	}
	public SaleMyWalletDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SaleMyWalletDetail [itemClassify=" + itemClassify
				+ ", totalMoney=" + totalMoney + ", refundMoney=" + refundMoney
				+ ", realMoney=" + realMoney + ", orderMoney=" + orderMoney
				+ ", rewardMoney=" + rewardMoney + ", money=" + money
				+ ", changeTime=" + changeTime + ", describe=" + describe + "]";
	}
	
	
}
