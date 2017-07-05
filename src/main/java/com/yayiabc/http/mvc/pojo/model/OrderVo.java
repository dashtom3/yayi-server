package com.yayiabc.http.mvc.pojo.model;

public class OrderVo {
	private String orderId; // 订单编号
	private String orderState; // 订单状态
	private String signUpdated; // 签收时间
	private String itemName; // 商品名称
	private String itemSKU; // 商品SKU代码
	private Integer price; // 价格
	private Integer num; // 数量
	private Integer totalFee; // 小计
	private Integer refundMoney; // 退款金额
	private Integer commission; // 提成收入
	private String orderCreated; // 下单时间

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

	public String getSignUpdated() {
		return signUpdated;
	}

	public void setSignUpdated(String signUpdated) {
		this.signUpdated = signUpdated;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemSKU() {
		return itemSKU;
	}

	public void setItemSKU(String itemSKU) {
		this.itemSKU = itemSKU;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
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

	public OrderVo() {
		super();
	}

	public OrderVo(String orderId, String orderState, String signUpdated,
			String itemName, String itemSKU, Integer price, Integer num,
			Integer totalFee, Integer refundMoney, Integer commission,
			String orderCreated) {
		super();
		this.orderId = orderId;
		this.orderState = orderState;
		this.signUpdated = signUpdated;
		this.itemName = itemName;
		this.itemSKU = itemSKU;
		this.price = price;
		this.num = num;
		this.totalFee = totalFee;
		this.refundMoney = refundMoney;
		this.commission = commission;
		this.orderCreated = orderCreated;
	}

	@Override
	public String toString() {
		return "OrderVo [orderId=" + orderId + ", orderState=" + orderState
				+ ", signUpdated=" + signUpdated + ", itemName=" + itemName
				+ ", itemSKU=" + itemSKU + ", price=" + price + ", num=" + num
				+ ", totalFee=" + totalFee + ", refundMoney=" + refundMoney
				+ ", commission=" + commission + ", orderCreated="
				+ orderCreated + "]";
	}

}
