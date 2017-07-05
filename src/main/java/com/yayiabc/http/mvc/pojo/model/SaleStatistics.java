package com.yayiabc.http.mvc.pojo.model;

public class SaleStatistics {
	private Integer saleId;

	private String phone;

	private String trueName;

	private Integer money;

	private Integer saleAllMoney;

	private Integer bindUserNum;

	private String latelyOrderDate;

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
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

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getSaleAllMoney() {
		return saleAllMoney;
	}

	public void setSaleAllMoney(Integer saleAllMoney) {
		this.saleAllMoney = saleAllMoney;
	}

	public Integer getBindUserNum() {
		return bindUserNum;
	}

	public void setBindUserNum(Integer bindUserNum) {
		this.bindUserNum = bindUserNum;
	}

	public String getLatelyOrderDate() {
		return latelyOrderDate;
	}

	public void setLatelyOrderDate(String latelyOrderDate) {
		this.latelyOrderDate = latelyOrderDate;
	}

	public SaleStatistics() {
		super();
	}

	public SaleStatistics(Integer saleId, String phone, String trueName,
			Integer money, Integer saleAllMoney, Integer bindUserNum,
			String latelyOrderDate) {
		super();
		this.saleId = saleId;
		this.phone = phone;
		this.trueName = trueName;
		this.money = money;
		this.saleAllMoney = saleAllMoney;
		this.bindUserNum = bindUserNum;
		this.latelyOrderDate = latelyOrderDate;
	}

	@Override
	public String toString() {
		return "SaleStatistics [saleId=" + saleId + ", phone=" + phone
				+ ", trueName=" + trueName + ", money=" + money
				+ ", saleAllMoney=" + saleAllMoney + ", bindUserNum="
				+ bindUserNum + ", latelyOrderDate=" + latelyOrderDate + "]";
	}

}
