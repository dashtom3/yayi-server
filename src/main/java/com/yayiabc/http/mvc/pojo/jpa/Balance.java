package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class Balance {
	private Integer balanceId;
	private String saleId;
	private Double balanceIn;
	private Double balanceOut;
	private Double balance;
	private Date created;
	private Double haocaiMoney;
	private Double haocaiRefund;
	private Double gongjuRefund;
	private Double gongjuMoney;
	private String describey;
	private Double JZZE;
	private Double CZZE;
	private double haoCaiIncome;
	private double gongJuIncome;
	public Double getHaoCaiIncome() {
		return haoCaiIncome;
	}
	public void setHaoCaiIncome(Double haoCaiIncome) {
		this.haoCaiIncome = haoCaiIncome;
	}
	public Double getGongJuIncome() {
		return gongJuIncome;
	}
	public void setGongJuIncome(Double gongJuIncome) {
		this.gongJuIncome = gongJuIncome;
	}
	public Double getGongjuMoney() {
		return gongjuMoney;
	}
	public Integer getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(Integer balanceId) {
		this.balanceId = balanceId;
	}
	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
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
	public Double balance() {
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
	public Balance(Integer balanceId, String saleId, Double balanceIn,
			Double balanceOut, Double balance, Date created,
			Double haocaiMoney, Double haocaiRefund, Double gongjuRefund,
			Double gongjuMoney, String describey, Double jZZE, Double cZZE) {
		super();
		this.balanceId = balanceId;
		this.saleId = saleId;
		this.balanceIn = balanceIn;
		this.balanceOut = balanceOut;
		this.balance = balance;
		this.created = created;
		this.haocaiMoney = haocaiMoney;
		this.haocaiRefund = haocaiRefund;
		this.gongjuRefund = gongjuRefund;
		this.gongjuMoney = gongjuMoney;
		this.describey = describey;
		JZZE = jZZE;
		CZZE = cZZE;
	}
	public Balance() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Balance [balanceId=" + balanceId + ", saleId=" + saleId + ", balanceIn=" + balanceIn + ", balanceOut="
				+ balanceOut + ", balance=" + balance + ", created=" + created + ", haocaiMoney=" + haocaiMoney
				+ ", haocaiRefund=" + haocaiRefund + ", gongjuRefund=" + gongjuRefund + ", gongjuMoney=" + gongjuMoney
				+ ", describey=" + describey + ", JZZE=" + JZZE + ", CZZE=" + CZZE + ", haoCaiIncome=" + haoCaiIncome
				+ ", gongJuIncome=" + gongJuIncome + "]";
	}
	
}
