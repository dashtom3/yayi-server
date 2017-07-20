package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class Balance {
	private Integer balanceId;
	private Double balanceIn;
	private Double balanceOut;
	private Double balance;
	private Date created;
	private String describe;
	private Double haocaiMoney;
	private Double haocaiRefund;
	private Double gongjuRefund;
	private Double gongjuMoney;
	private String describey;
	private Double JZZE;
	private Double CZZE;
	

	public Double getJZZE() {
		return JZZE;
	}

	public void setJZZE(Double jZZE) {
		JZZE = jZZE;
	}

	public Double getCZZE() {
		return CZZE;
	}

	public void setCZZE(Double cZZE) {
		CZZE = cZZE;
	}

	public Integer getBalanceId() {
		return balanceId;
	}
	
	public Double getHaocaiMoney() {
		return haocaiMoney;
	}

	public void setHaocaiMoney(Double haocaiMoney) {
		this.haocaiMoney = haocaiMoney;
	}

	public Double getHaocaiRefund() {
		return haocaiRefund;
	}

	public void setHaocaiRefund(Double haocaiRefund) {
		this.haocaiRefund = haocaiRefund;
	}

	public Double getGongjuRefund() {
		return gongjuRefund;
	}

	public void setGongjuRefund(Double gongjuRefund) {
		this.gongjuRefund = gongjuRefund;
	}

	public Double getGongjuMoney() {
		return gongjuMoney;
	}

	public void setGongjuMoney(Double gongjuMoney) {
		this.gongjuMoney = gongjuMoney;
	}

	public String getDescribey() {
		return describey;
	}

	public void setDescribey(String describey) {
		this.describey = describey;
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
		return "Balance [balanceId=" + balanceId + ", balanceIn=" + balanceIn + ", balanceOut=" + balanceOut
				+ ", balance=" + balance + ", created=" + created + ", describe=" + describe + ", haocaiMoney="
				+ haocaiMoney + ", haocaiRefund=" + haocaiRefund + ", gongjuRefund=" + gongjuRefund + ", gongjuMoney="
				+ gongjuMoney + ", describey=" + describey + ", JZZE=" + JZZE + ", CZZE=" + CZZE + "]";
	}
	
}
