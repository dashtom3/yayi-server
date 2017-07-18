package com.yayiabc.http.mvc.pojo.log;

import java.util.Date;

public class SaleLog {
	private Integer saleLogId;
	private String administrator;
	private String userName;
	private String operate;
	private Date created;
	public Integer getSaleLogId() {
		return saleLogId;
	}
	public void setSaleLogId(Integer saleLogId) {
		this.saleLogId = saleLogId;
	}
	public String getAdministrator() {
		return administrator;
	}
	public void setAdministrator(String administrator) {
		this.administrator = administrator;
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public SaleLog(Integer saleLogId, String administrator, String userName,
			String operate, Date created) {
		super();
		this.saleLogId = saleLogId;
		this.administrator = administrator;
		this.userName = userName;
		this.operate = operate;
		this.created = created;
	}
	public SaleLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SaleLog [saleLogId=" + saleLogId + ", administrator="
				+ administrator + ", userName=" + userName + ", operate="
				+ operate + ", created=" + created + "]";
	}
	
}
