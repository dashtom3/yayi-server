package com.yayiabc.http.mvc.pojo.model;


import java.util.Date;

public class Order {
	    private String token;
	    private String  orderItem;
	 
	     //









         //订单
		private String invoiceHand;

		private Integer isRegister;



		private  Integer qbDed;
		
		private  Integer receiverId;
		
		private String buyerMessage;
       //发票
		private Integer InvoiceId;
		private String companyName;
		private String taxpayerNum;
		private String registeredAddress;
		private String registeredPhone;
		private String opneBank;
		private String bankNumber;
		private String stickNanme;
		private String stickPhone;
		private String stickaddress;
		private String invoiceHead;
		private String orderId;
		private String invoiceStyle;
		private String  InvoiceState;
		
		private Date cTime;
		private String userId;
	    
	    
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getOrderItem() {
		return orderItem;
	}
	public String getInvoiceHand() {
		return invoiceHand;
	}
	public void setInvoiceHand(String invoiceHand) {
		this.invoiceHand = invoiceHand;
	}
	public Integer getIsRegister() {
		return isRegister;
	}
	public void setIsRegister(Integer isRegister) {
		this.isRegister = isRegister;
	}
	public Integer getQbDed() {
		return qbDed;
	}
	public void setQbDed(Integer qbDed) {
		this.qbDed = qbDed;
	}
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	public String getBuyerMessage() {
		return buyerMessage;
	}
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}
	public Integer getInvoiceId() {
		return InvoiceId;
	}
	public void setInvoiceId(Integer invoiceId) {
		InvoiceId = invoiceId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTaxpayerNum() {
		return taxpayerNum;
	}
	public void setTaxpayerNum(String taxpayerNum) {
		this.taxpayerNum = taxpayerNum;
	}
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public String getRegisteredPhone() {
		return registeredPhone;
	}
	public void setRegisteredPhone(String registeredPhone) {
		this.registeredPhone = registeredPhone;
	}
	public String getOpneBank() {
		return opneBank;
	}
	public void setOpneBank(String opneBank) {
		this.opneBank = opneBank;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getStickNanme() {
		return stickNanme;
	}
	public void setStickNanme(String stickNanme) {
		this.stickNanme = stickNanme;
	}
	public String getStickPhone() {
		return stickPhone;
	}
	public void setStickPhone(String stickPhone) {
		this.stickPhone = stickPhone;
	}
	public String getStickaddress() {
		return stickaddress;
	}
	public void setStickaddress(String stickaddress) {
		this.stickaddress = stickaddress;
	}
	public String getInvoiceHead() {
		return invoiceHead;
	}
	public void setInvoiceHead(String invoiceHead) {
		this.invoiceHead = invoiceHead;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getInvoiceStyle() {
		return invoiceStyle;
	}
	public void setInvoiceStyle(String invoiceStyle) {
		this.invoiceStyle = invoiceStyle;
	}
	public String getInvoiceState() {
		return InvoiceState;
	}
	public void setInvoiceState(String invoiceState) {
		InvoiceState = invoiceState;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}
	@Override
	public String toString() {
		return "Order [token=" + token + ", orderItem=" + orderItem + ", invoiceHand=" + invoiceHand + ", isRegister="
				+ isRegister + ", qbDed=" + qbDed + ", receiverId=" + receiverId + ", buyerMessage=" + buyerMessage
				+ ", InvoiceId=" + InvoiceId + ", companyName=" + companyName + ", taxpayerNum=" + taxpayerNum
				+ ", registeredAddress=" + registeredAddress + ", registeredPhone=" + registeredPhone + ", opneBank="
				+ opneBank + ", bankNumber=" + bankNumber + ", stickNanme=" + stickNanme + ", stickPhone=" + stickPhone
				+ ", stickaddress=" + stickaddress + ", invoiceHead=" + invoiceHead + ", orderId=" + orderId
				+ ", invoiceStyle=" + invoiceStyle + ", InvoiceState=" + InvoiceState + ", cTime=" + cTime + ", userId="
				+ userId + "]";
	}
}
