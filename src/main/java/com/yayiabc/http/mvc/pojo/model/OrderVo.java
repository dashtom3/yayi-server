package com.yayiabc.http.mvc.pojo.model;

public class OrderVo {
	private String orderId; // 订单编号
	private String state; // 订单状态
	private Integer refundMoney; // 退款金额
	private Integer commission; // 提成收入
	private String orderCreated; // 下单时间
	private String userName;
	private String userPhone;
	private double itemSumMoney;	//商品总价
	private double orderMoneyHaocai;	//耗材类
	private double orderMoneyGongju;	//工具类
	private double refundMoneyHaocai;	//已退金额，耗材
	private double refundMoneyGongju;	//已退金额，工具
	private double actualMoney;	//实际销售额
	private SaleIncomeVo saleIncomeVo;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(Integer refundMoney) {
		this.refundMoney = refundMoney;
	}

	public Integer getCommission() {
		return commission;
	}

	public void setCommission(Integer commission) {
		this.commission = commission;
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

	public OrderVo() {
		super();
	}

	public OrderVo(String orderId, String state, Integer refundMoney,
			Integer commission, String orderCreated, String userName,
			String userPhone, double itemSumMoney, double orderMoneyHaocai,
			double orderMoneyGongju, double refundMoneyHaocai,
			double refundMoneyGongju, double actualMoney,
			SaleIncomeVo saleIncomeVo) {
		super();
		this.orderId = orderId;
		this.state = state;
		this.refundMoney = refundMoney;
		this.commission = commission;
		this.orderCreated = orderCreated;
		this.userName = userName;
		this.userPhone = userPhone;
		this.itemSumMoney = itemSumMoney;
		this.orderMoneyHaocai = orderMoneyHaocai;
		this.orderMoneyGongju = orderMoneyGongju;
		this.refundMoneyHaocai = refundMoneyHaocai;
		this.refundMoneyGongju = refundMoneyGongju;
		this.actualMoney = actualMoney;
		this.saleIncomeVo = saleIncomeVo;
	}

	@Override
	public String toString() {
		return "OrderVo [orderId=" + orderId + ", state=" + state
				+ ", refundMoney=" + refundMoney + ", commission=" + commission
				+ ", orderCreated=" + orderCreated + ", userName=" + userName
				+ ", userPhone=" + userPhone + ", itemSumMoney=" + itemSumMoney
				+ ", orderMoneyHaocai=" + orderMoneyHaocai
				+ ", orderMoneyGongju=" + orderMoneyGongju
				+ ", refundMoneyHaocai=" + refundMoneyHaocai
				+ ", refundMoneyGongju=" + refundMoneyGongju + ", actualMoney="
				+ actualMoney + ", saleIncomeVo=" + saleIncomeVo + "]";
	}

}
