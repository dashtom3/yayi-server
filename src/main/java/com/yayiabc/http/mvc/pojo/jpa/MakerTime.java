package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class MakerTime {
	private Integer id;
	private String saleId;
	private Date universalTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public Date getUniversalTime() {
		return universalTime;
	}
	public void setUniversalTime(Date universalTime) {
		this.universalTime = universalTime;
	}
	@Override
	public String toString() {
		return "MakerTime [id=" + id + ", saleId=" + saleId + ", universalTime=" + universalTime + "]";
	}
}
