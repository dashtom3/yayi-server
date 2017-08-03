package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

/**
 * 
 * @author xiaojiang 乾币记录表
 */
public class QbRecord extends BasePojo {
	private Integer qbRid;

	private String userId;

	private Integer qbRget;

	private Integer qbRout;

	private String qbTime;

	private Integer qbBalances;

	private String remark;

	private Integer millisecond; // 毫秒

	private User user;

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
		this.userId = userId == null ? null : userId.trim();
	}

	public Integer getQbRget() {
		return qbRget;
	}

	public void setQbRget(Integer qbRget) {
		this.qbRget = qbRget;
	}

	public Integer getQbRout() {
		return qbRout;
	}

	public void setQbRout(Integer qbRout) {
		this.qbRout = qbRout;
	}

	public String getQbTime() {
		return qbTime;
	}

	public void setQbTime(String qbTime) {
		this.qbTime = qbTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getQbBalances() {
		return qbBalances;
	}

	public void setQbBalances(Integer qbBalances) {
		this.qbBalances = qbBalances;
	}



	public Integer getMillisecond() {
		return millisecond;
	}

	public void setMillisecond(Integer millisecond) {
		this.millisecond = millisecond;
	}

	public QbRecord() {
		super();
	}

	public QbRecord(Integer qbRid, String userId, Integer qbRget,
			Integer qbRout, String qbTime, String remark, Integer qbBalances,
			Integer millisecond) {
		super();
		this.qbRid = qbRid;
		this.userId = userId;
		this.qbRget = qbRget;
		this.qbRout = qbRout;
		this.qbTime = qbTime;
		this.remark = remark;
		this.qbBalances = qbBalances;
		this.millisecond = millisecond;
	}

	public QbRecord(Integer qbRid, String userId, Integer qbRget,
			Integer qbRout, String qbTime, String remark, Integer qbBalances,
			User user) {
		super();
		this.qbRid = qbRid;
		this.userId = userId;
		this.qbRget = qbRget;
		this.qbRout = qbRout;
		this.qbTime = qbTime;
		this.remark = remark;
		this.qbBalances = qbBalances;
		this.user = user;
	}

	@Override
	public String toString() {
		return "QbRecord [qbRid=" + qbRid + ", userId=" + userId + ", qbRget="
				+ qbRget + ", qbRout=" + qbRout + ", qbTime=" + qbTime
				+ ", qbBalances=" + qbBalances + ", remark=" + remark
				+ ", millisecond=" + millisecond + ", user=" + user + "]";
	}

	public QbRecord(Date created, Date updated) {
		super(created, updated);
		// TODO Auto-generated constructor stub
	}

}