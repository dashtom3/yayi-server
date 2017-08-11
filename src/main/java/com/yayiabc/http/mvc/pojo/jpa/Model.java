package com.yayiabc.http.mvc.pojo.jpa;

import com.yayiabc.http.mvc.controller.wexinli.SNSUserInfo;

public class Model {
    private User user;
    private SNSUserInfo sNSUserInfo;
    private WXUserLink wXUserLibk;
    private SaleInfo  saleInfo;
    
	public SaleInfo getSaleInfo() {
		return saleInfo;
	}
	public void setSaleInfo(SaleInfo saleInfo) {
		this.saleInfo = saleInfo;
	}
	public WXUserLink getwXUserLibk() {
		return wXUserLibk;
	}
	public void setwXUserLibk(WXUserLink wXUserLibk) {
		this.wXUserLibk = wXUserLibk;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SNSUserInfo getsNSUserInfo() {
		return sNSUserInfo;
	}
	public void setsNSUserInfo(SNSUserInfo sNSUserInfo) {
		this.sNSUserInfo = sNSUserInfo;
	}
    
}
