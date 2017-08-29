package com.yayiabc.http.mvc.pojo.model;

public class OrderManagement {
	private	String orderId;
	private	String phone;  //电话
	private	String qbDed;
	private	String actualPay;
	private	String totalFee;
	private	String reName;   //名称
	private	String created;
	private	String state;
	private	String shippingName;
	private	String shippingCode;
	private	String refundInfo;
	private String qbDes;
	
	public String getQbDes() {
		return qbDes;
	}
	public void setQbDes(String qbDes) {
		this.qbDes = qbDes;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getActualPay() {
		return actualPay;
	}
	public void setActualPay(String actualPay) {
		this.actualPay = actualPay;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQbDed() {
		return qbDed;
	}
	public void setQbDed(String qbDed) {
		this.qbDed = qbDed;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getReName() {
		return reName;
	}
	public void setReName(String reName) {
		this.reName = reName;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getShippingCode() {
		return shippingCode;
	}
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}
	public String getRefundInfo() {
		return refundInfo;
	}
	public void setRefundInfo(String refundInfo) {
		this.refundInfo = refundInfo;
	}
	@Override
	public String toString() {
		return "OrderManagement [orderId=" + orderId + ", phone=" + phone
				+ ", qbDed=" + qbDed + ", actualPay=" + actualPay
				+ ", totalFee=" + totalFee + ", reName=" + reName
				+ ", created=" + created + ", state=" + state
				+ ", shippingName=" + shippingName + ", shippingCode="
				+ shippingCode + ", refundInfo=" + refundInfo + "]";
	}
}
