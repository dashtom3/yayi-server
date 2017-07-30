package com.yayiabc.http.mvc.pojo.log;

import java.util.Date;

public class SaleLog {
	private Integer saleLogId;
	private String saleId;
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
	public SaleLog(Integer saleLogId, String saleId, String operate,
			String arguments, Date created) {
		super();
		this.saleLogId = saleLogId;
		this.saleId = saleId;
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
				+ ", operate=" + operate + ", arguments=" + arguments
				+ ", created=" + created + "]";
	}
	
	
	
}
