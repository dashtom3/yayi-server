package com.yayiabc.http.mvc.pojo.model;

import java.util.Date;

import org.apache.poi.ss.usermodel.DataFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class qbRecordModel {
	private  String trueName;
	private String phone;
	private   String qbDes;
	private    String rechargeMoney;
	private   String referer;
	private Date created;
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQbDes() {
		return qbDes;
	}
	public void setQbDes(String qbDes) {
		this.qbDes = qbDes;
	}
	public String getRechargeMoney() {
		return rechargeMoney;
	}
	public void setRechargeMoney(String rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreated() {
		return created;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public void setCreated(Date created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return "qbRecordModel [trueName=" + trueName + ", phone=" + phone + ", qbDes=" + qbDes + ", rechargeMoney="
				+ rechargeMoney + ", referer=" + referer + ", created=" + created + "]";
	}
	
	
}
