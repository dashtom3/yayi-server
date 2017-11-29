package com.yayiabc.http.mvc.pojo.model;

import java.util.Date;

import org.apache.poi.ss.usermodel.DataFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yayiabc.http.mvc.pojo.jpa.User;

public class qbRecordModel {
	private  String trueName;
	private String phone;
	
	 private String  rechargeMoney;
	private Date created;
	
	private String chargeId;
	
	private Integer qbRid;
	
	private String userId;

	private String qbRget;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private String qbTime;

	private String referer; //

	private String remark;

	private long millisecond; // 毫秒

	private String qbType;

	private String qbBalances;

	public String getTrueName() {
		return trueName;
	}

	public String getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(String rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public Integer getQbRid() {
		return qbRid;
	}

	public void setQbRid(Integer qbRid) {
		this.qbRid = qbRid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQbRget() {
		return qbRget;
	}

	public void setQbRget(String qbRget) {
		this.qbRget = qbRget;
	}

	public String getQbTime() {
		return qbTime;
	}

	public void setQbTime(String qbTime) {
		this.qbTime = qbTime;
	}


	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getMillisecond() {
		return millisecond;
	}

	public void setMillisecond(long millisecond) {
		this.millisecond = millisecond;
	}

	public String getQbType() {
		return qbType;
	}

	public void setQbType(String qbType) {
		this.qbType = qbType;
	}

	public String getQbBalances() {
		return qbBalances;
	}

	public void setQbBalances(String qbBalances) {
		this.qbBalances = qbBalances;
	}

	@Override
	public String toString() {
		return "qbRecordModel [trueName=" + trueName + ", phone=" + phone + ", created=" + created + ", chargeId="
				+ chargeId + ", qbRid=" + qbRid + ", userId=" + userId + ", qbRget=" + qbRget + ", qbTime=" + qbTime
				+ ", referer=" + referer + ", remark=" + remark + ", millisecond=" + millisecond + ", qbType=" + qbType
				+ ", qbBalances=" + qbBalances + "]";
	}
	
	
}
