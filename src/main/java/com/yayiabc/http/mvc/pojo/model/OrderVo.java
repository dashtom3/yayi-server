package com.yayiabc.http.mvc.pojo.model;

import java.util.List;

public class OrderVo {
	private String orderId; // 订单编号
	private String orderState; // 订单状态
	private String orderCreated; // 下单时间
	private String userName;
	private String userPhone;
	private double itemSumMoney; // 商品总价
	private double orderMoneyHaocai; // 耗材类
	private double orderMoneyGongju; // 工具类
	private double refundMoneyHaocai; // 已退金额，耗材
	private double refundMoneyGongju; // 已退金额，工具
	private double actualMoneyHaocai; // 实际销售额耗材
	private double actualMoneyGongju; // 实际销售额工具
	private double actualMoney; // 实际销售额
	private SaleIncomeVo saleIncomeVo;
	private List<OrderInfoVo> orderInfoVoList;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
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

	public double getItemSumMoney() {
		return itemSumMoney;
	}

	public void setItemSumMoney(double itemSumMoney) {
		this.itemSumMoney = itemSumMoney;
	}

	public double getOrderMoneyHaocai() {
		return orderMoneyHaocai;
	}

	public void setOrderMoneyHaocai(double orderMoneyHaocai) {
		this.orderMoneyHaocai = orderMoneyHaocai;
	}

	public double getOrderMoneyGongju() {
		return orderMoneyGongju;
	}

	public void setOrderMoneyGongju(double orderMoneyGongju) {
		this.orderMoneyGongju = orderMoneyGongju;
	}

	public double getRefundMoneyHaocai() {
		return refundMoneyHaocai;
	}

	public void setRefundMoneyHaocai(double refundMoneyHaocai) {
		this.refundMoneyHaocai = refundMoneyHaocai;
	}

	public double getRefundMoneyGongju() {
		return refundMoneyGongju;
	}

	public void setRefundMoneyGongju(double refundMoneyGongju) {
		this.refundMoneyGongju = refundMoneyGongju;
	}

	public double getActualMoneyHaocai() {
		return actualMoneyHaocai;
	}

	public void setActualMoneyHaocai(double actualMoneyHaocai) {
		this.actualMoneyHaocai = actualMoneyHaocai;
	}

	public double getActualMoneyGongju() {
		return actualMoneyGongju;
	}

	public void setActualMoneyGongju(double actualMoneyGongju) {
		this.actualMoneyGongju = actualMoneyGongju;
	}

	public double getActualMoney() {
		return actualMoney;
	}

	public void setActualMoney(double actualMoney) {
		this.actualMoney = actualMoney;
	}

	public SaleIncomeVo getSaleIncomeVo() {
		return saleIncomeVo;
	}

	public void setSaleIncomeVo(SaleIncomeVo saleIncomeVo) {
		this.saleIncomeVo = saleIncomeVo;
	}

	public List<OrderInfoVo> getOrderInfoVoList() {
		return orderInfoVoList;
	}

	public void setOrderInfoVoList(List<OrderInfoVo> orderInfoVoList) {
		this.orderInfoVoList = orderInfoVoList;
	}

	public OrderVo() {
		super();
	}

	public OrderVo(String orderId, String orderState, String orderCreated,
			String userName, String userPhone, double itemSumMoney,
			double orderMoneyHaocai, double orderMoneyGongju,
			double refundMoneyHaocai, double refundMoneyGongju,
			double actualMoneyHaocai, double actualMoneyGongju,
			double actualMoney, SaleIncomeVo saleIncomeVo,
			List<OrderInfoVo> orderInfoVoList) {
		super();
		this.orderId = orderId;
		this.orderState = orderState;
		this.orderCreated = orderCreated;
		this.userName = userName;
		this.userPhone = userPhone;
		this.itemSumMoney = itemSumMoney;
		this.orderMoneyHaocai = orderMoneyHaocai;
		this.orderMoneyGongju = orderMoneyGongju;
		this.refundMoneyHaocai = refundMoneyHaocai;
		this.refundMoneyGongju = refundMoneyGongju;
		this.actualMoneyHaocai = actualMoneyHaocai;
		this.actualMoneyGongju = actualMoneyGongju;
		this.actualMoney = actualMoney;
		this.saleIncomeVo = saleIncomeVo;
		this.orderInfoVoList = orderInfoVoList;
	}

	@Override
	public String toString() {
		return "OrderVo [orderId=" + orderId + ", orderState=" + orderState
				+ ", orderCreated=" + orderCreated + ", userName=" + userName
				+ ", userPhone=" + userPhone + ", itemSumMoney=" + itemSumMoney
				+ ", orderMoneyHaocai=" + orderMoneyHaocai
				+ ", orderMoneyGongju=" + orderMoneyGongju
				+ ", refundMoneyHaocai=" + refundMoneyHaocai
				+ ", refundMoneyGongju=" + refundMoneyGongju
				+ ", actualMoneyHaocai=" + actualMoneyHaocai
				+ ", actualMoneyGongju=" + actualMoneyGongju + ", actualMoney="
				+ actualMoney + ", saleIncomeVo=" + saleIncomeVo
				+ ", orderInfoVoList=" + orderInfoVoList + "]";
	}

}
