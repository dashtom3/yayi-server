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
	@Override
	public String toString() {

		if("0".equals(invoiceStyle)&&"1".equals(InvoiceState)){
			//普通发票  公司发票
			return "发票信息    \r\n发票类型:"+showInvoiceStyle(invoiceStyle)+"\r\n发票性质:"+ showInvoiceState(InvoiceState)+"\r\n公司抬头:" + companyName + "\r\n纳税人识别号:" + taxpayerNum;
			
		}else if("0".equals(invoiceStyle)&&"0".equals(InvoiceState)){
			//普通发票  个人发票
			return "发票信息    \r\n发票类型:"+showInvoiceStyle(invoiceStyle)+"\r\n发票性质:"+ showInvoiceState(InvoiceState)+"\r\n发票抬头:" + companyName;
		}else{
			//增值税发票 公司发票
			return "发票信息    \r\n发票类型:"+showInvoiceStyle(invoiceStyle)+"\r\n发票性质:公司发票"+"\r\n单位名称:" + companyName + "\r\n纳税人识别号:" + taxpayerNum
					+ "\r\n注册地址:" + registeredAddress + "\r\n注册电话:" + registeredPhone + "\r\n开户银行:"
					+ opneBank + "\r\n银行账号:" + bankNumber + "\r\n收票人姓名:" + stickNanme + "\r\n收票人手机号:" + stickPhone
					+ "\r\n收票人地址:" + stickaddress
					;
		}
	}

	private String showInvoiceStyle(String invoiceStyle){
		if("0".equals(invoiceStyle)){
			return "普通发票";
		}else if("1".equals(invoiceStyle)){
			return "增值税发票";
		}
		return "非法发票";
	}
	private String showInvoiceState(String InvoiceState){
		if("1".equals(InvoiceState)){
			return "公司发票";
		}else if("0".equals(InvoiceState)){
			return "个人发票";
		}
		return "非法发票";
	}
}
