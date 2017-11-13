package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

/**
 * 
 * @author xiaojiang 乾币记录表
 */
public class QbRecord extends BasePojo {
	private String chargeId;
  private Integer qbRid;
	private String userId;

	private String qbRget;

	private String qbRout;

	private String qbTime;

	private Integer referer; //充值的  支付方式

	private String remark;

	private long millisecond; // 毫秒

	private User user;
	private String qbType;
	
	private String qbBalances;
	
	
	public String getQbBalances() {
		return qbBalances;
	}
	public void setQbBalances(String qbBalances) {
		this.qbBalances = qbBalances;
	}
	public Integer getQbRid() {
		return qbRid;
	}
	public void setQbRid(Integer qbRid) {
		this.qbRid = qbRid;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
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

	public String getQbRout() {
		return qbRout;
	}
	public void setQbRout(String qbRout) {
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
		this.remark = remark;
	}
	public long getMillisecond() {
		return millisecond;
	}
	public void setMillisecond(long millisecond) {
		this.millisecond = millisecond;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getQbType() {
		return qbType;
	}
	public void setQbType(String qbType) {
		this.qbType = qbType;
	}
	public Integer getReferer() {
		return referer;
	}
	public void setReferer(Integer referer) {
		this.referer = referer;
	}
	@Override
	public String toString() {
		return "QbRecord [chargeId=" + chargeId + ", userId=" + userId + ", qbRget=" + qbRget + ", qbRout=" + qbRout
				+ ", qbTime=" + qbTime + ", referer=" + referer + ", remark=" + remark + ", millisecond=" + millisecond
				+ ", user=" + user + ", qbType=" + qbType + "]";
	}

}