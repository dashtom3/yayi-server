package com.yayiabc.http.mvc.pojo.model;

public class SaleWitModel {
	 private Double balance;
	 private String describey;
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getDescribey() {
		return describey;
	}
	public void setDescribey(String describey) {
		this.describey = describey;
	}
	@Override
	public String toString() {
		return "SaleWitModel [balance=" + balance + ", describey=" + describey + "]";
	}
	 
}
