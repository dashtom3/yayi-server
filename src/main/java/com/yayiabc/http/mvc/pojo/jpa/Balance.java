package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class Balance {
	private Integer balanceId;
	private Double balanceIn;
	private Double balanceOut;
	private Double balance;
	private Date created;
	private String describe;
	public Integer getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(Integer balanceId) {
		this.balanceId = balanceId;
	}
	public Double getBalanceIn() {
		return balanceIn;
	}
	public void setBalanceIn(Double balanceIn) {
		this.balanceIn = balanceIn;
	}
	public Double getBalanceOut() {
		return balanceOut;
	}
	public void setBalanceOut(Double balanceOut) {
		this.balanceOut = balanceOut;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Balance(Integer balanceId, Double balanceIn, Double balanceOut,
			Double balance, Date created, String describe) {
		super();
		this.balanceId = balanceId;
		this.balanceIn = balanceIn;
		this.balanceOut = balanceOut;
		this.balance = balance;
		this.created = created;
		this.describe = describe;
	}
	public Balance() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Balance [balanceId=" + balanceId + ", balanceIn=" + balanceIn
				+ ", balanceOut=" + balanceOut + ", balance=" + balance
				+ ", created=" + created + ", describe=" + describe + "]";
	}
	
	
}
