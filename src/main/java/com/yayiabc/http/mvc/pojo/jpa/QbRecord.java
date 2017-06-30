package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author xiaojiang 乾币记录表
 */
public class QbRecord extends BasePojo {
	private Integer qbRid;

	private String userId;

	private Integer qbRget;

	private Integer qbRout;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date qbTime;

	private Integer qbBalances;

	private String remark;

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

	public Date getQbTime() {
		return qbTime;
	}

	public void setQbTime(Date qbTime) {
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

	public QbRecord() {
		super();
	}

	public QbRecord(Integer qbRid, String userId, Integer qbRget,
			Integer qbRout, Date qbTime, String remark, Integer qbBalances) {
		super();
		this.qbRid = qbRid;
		this.userId = userId;
		this.qbRget = qbRget;
		this.qbRout = qbRout;
		this.qbTime = qbTime;
		this.remark = remark;
		this.qbBalances = qbBalances;
	}

	public QbRecord(Integer qbRid, String userId, Integer qbRget,
			Integer qbRout, Date qbTime, String remark, Integer qbBalances,
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
				+ ", qbBalances=" + qbBalances + ", remark=" + remark + ", user="
				+ user + "]";
	}

}