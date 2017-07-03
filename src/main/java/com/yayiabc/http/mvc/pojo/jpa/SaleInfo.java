package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaojiang 销售员信息表
 */

public class SaleInfo extends BasePojo {
	private String saleId;

	private String trueName;

	private Date birthday;

	private Integer money;

	private Integer type;

	private String phone;

	private Integer sex;

	private String address;

	private Integer bindUserNum;

	private Integer isBindUser;

	private List<User> user;

	private List<SaleIncome> saleincome;

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public List<SaleIncome> getSaleincome() {
		return saleincome;
	}

	public void setSaleincome(List<SaleIncome> saleincome) {
		this.saleincome = saleincome;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getMoney() {
		return money;
	}

	public Integer getSex() {
		return sex;
	}

	public Integer getIsBindUser() {
		return isBindUser;
	}

	public void setIsBindUser(Integer isBindUser) {
		this.isBindUser = isBindUser;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getBindUserNum() {
		return bindUserNum;
	}

	public void setBindUserNum(Integer bindUserNum) {
		this.bindUserNum = bindUserNum;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public SaleInfo() {
		super();
	}

	public SaleInfo(String saleId, String trueName, Date birthday,
			Integer money, Integer type, String phone, Integer sex,
			String address, Integer bindUserNum, Integer isBindUser,
			List<User> user, List<SaleIncome> saleincome) {
		super();
		this.saleId = saleId;
		this.trueName = trueName;
		this.birthday = birthday;
		this.money = money;
		this.type = type;
		this.phone = phone;
		this.sex = sex;
		this.address = address;
		this.bindUserNum = bindUserNum;
		this.isBindUser = isBindUser;
		this.user = user;
		this.saleincome = saleincome;
	}

	@Override
	public String toString() {
		return "SaleInfo [saleId=" + saleId + ", trueName=" + trueName
				+ ", birthday=" + birthday + ", money=" + money + ", type="
				+ type + ", phone=" + phone + ", sex=" + sex + ", address="
				+ address + ", bindUserNum=" + bindUserNum + ", isBindUser="
				+ isBindUser + ", user=" + user + ", saleincome=" + saleincome
				+ "]";
	}

}
