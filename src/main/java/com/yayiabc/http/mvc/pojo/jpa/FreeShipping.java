package com.yayiabc.http.mvc.pojo.jpa;
  //包邮
public class FreeShipping {

	private String  postCity;
    private  Integer freePostId;
    private  Integer freeShippingMoney;
    private Integer state;
	public String getPostCity() {
		return postCity;
	}
	
	public Integer getFreeShippingMoney() {
		return freeShippingMoney;
	}
	public void setFreeShippingMoney(Integer freeShippingMoney) {
		this.freeShippingMoney = freeShippingMoney;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getFreePostId() {
		return freePostId;
	}

	public void setFreePostId(Integer freePostId) {
		this.freePostId = freePostId;
	}

	public void setPostCity(String postCity) {
		this.postCity = postCity;
	}
	
}
