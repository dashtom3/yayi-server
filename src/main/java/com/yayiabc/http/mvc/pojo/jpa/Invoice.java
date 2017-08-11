package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class Invoice {
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
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
		this.cTime = cTime;
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
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
	public String getTaxpayerNum() {
		return taxpayerNum;
	}
	public void setTaxpayerNum(String taxpayerNum) {
		this.taxpayerNum = taxpayerNum;
	}
	public String getRegisteredPhone() {
		return registeredPhone;
	}
	public void setRegisteredPhone(String registeredPhone) {
		this.registeredPhone = registeredPhone;
	}

}
