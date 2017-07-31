package com.yayiabc.http.mvc.pojo.log;

import java.util.Date;

public class SaleLog {
	private Integer saleLogId;
	private String saleId;
	private String phone;
	private String userName;
	private String operate;
	private String arguments;
	private Date created;
	public Integer getSaleLogId() {
		return saleLogId;
	}
	public void setSaleLogId(Integer saleLogId) {
		this.saleLogId = saleLogId;
	}
	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getArguments() {
		return arguments;
	}
	public void setArguments(String arguments) {
		this.arguments = arguments;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public SaleLog(Integer saleLogId, String saleId, String phone,
			String userName, String operate, String arguments, Date created) {
		super();
		this.saleLogId = saleLogId;
		this.saleId = saleId;
		this.phone = phone;
		this.userName = userName;
		this.operate = operate;
		this.arguments = arguments;
		this.created = created;
	}
	public SaleLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SaleLog [saleLogId=" + saleLogId + ", saleId=" + saleId
				+ ", phone=" + phone + ", userName=" + userName + ", operate="
				+ operate + ", arguments=" + arguments + ", created=" + created
				+ "]";
	}
	
	
	
	
}
