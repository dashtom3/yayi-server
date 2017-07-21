package com.yayiabc.http.mvc.pojo.jpa;

public class Charge {
	private String chargeId;
	private String token;
	private Integer money;
	private Integer state;//1表示未支付 2表示已支付
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Charge(String chargeId, String token, Integer money, Integer state) {
		super();
		this.chargeId = chargeId;
		this.token = token;
		this.money = money;
		this.state = state;
	}
	public Charge() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Charge [chargeId=" + chargeId + ", token=" + token + ", money="
				+ money + ", state=" + state + "]";
	}
	
}
