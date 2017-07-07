package com.yayiabc.http.mvc.pojo.model;

/**
 * 用户统计表
 * 
 * @author xiaojiang
 * 
 */
public class UserStatistics {
	private String userId;

	private String phone;

	private String trueName;

	private Integer bindUserNum;

	private String companyName;

	private String bindSaleTime;

	private String workAddress;

	private String orderaCount;

	private String orderaMoneyCount;

	private String latelyOrderDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getOrderaCount() {
		return orderaCount;
	}

	public void setOrderaCount(String orderaCount) {
		this.orderaCount = orderaCount;
	}

	public String getOrderaMoneyCount() {
		return orderaMoneyCount;
	}

	public void setOrderaMoneyCount(String orderaMoneyCount) {
		this.orderaMoneyCount = orderaMoneyCount;
	}

	public String getLatelyOrderDate() {
		return latelyOrderDate;
	}

	public void setLatelyOrderDate(String latelyOrderDate) {
		this.latelyOrderDate = latelyOrderDate;
	}

	public Integer getBindUserNum() {
		return bindUserNum;
	}

	public void setBindUserNum(Integer bindUserNum) {
		this.bindUserNum = bindUserNum;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBindSaleTime() {
		return bindSaleTime;
	}

	public void setBindSaleTime(String bindSaleTime) {
		this.bindSaleTime = bindSaleTime;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public UserStatistics() {
		super();
	}

	public UserStatistics(String userId, String phone, String trueName,
			Integer bindUserNum, String companyName, String bindSaleTime,
			String workAddress, String orderaCount, String orderaMoneyCount,
			String latelyOrderDate) {
		super();
		this.userId = userId;
		this.phone = phone;
		this.trueName = trueName;
		this.bindUserNum = bindUserNum;
		this.companyName = companyName;
		this.bindSaleTime = bindSaleTime;
		this.workAddress = workAddress;
		this.orderaCount = orderaCount;
		this.orderaMoneyCount = orderaMoneyCount;
		this.latelyOrderDate = latelyOrderDate;
	}

	@Override
	public String toString() {
		return "UserStatistics [userId=" + userId + ", phone=" + phone
				+ ", trueName=" + trueName + ", bindUserNum=" + bindUserNum
				+ ", companyName=" + companyName + ", bindSaleTime="
				+ bindSaleTime + ", workAddress=" + workAddress
				+ ", orderaCount=" + orderaCount + ", orderaMoneyCount="
				+ orderaMoneyCount + ", latelyOrderDate=" + latelyOrderDate
				+ "]";
	}

}
