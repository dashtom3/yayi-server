package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaojiang 销售员信息表
 */

public class SaleInfo extends BasePojo {
	private String saleId;
	
	private String salePwd;

	private String trueName;

	private String idCard;

	private Date birthday;

	private Integer money;

	private String salePic;

	private String postalType;

	private String bankName;

	private String openName;

	private String accountNumber;

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

	public String getSalePwd() {
		return salePwd;
	}

	public void setSalePwd(String salePwd) {
		this.salePwd = salePwd;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
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

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getSalePic() {
		return salePic;
	}

	public void setSalePic(String salePic) {
		this.salePic = salePic;
	}

	public String getPostalType() {
		return postalType;
	}

	public void setPostalType(String postalType) {
		this.postalType = postalType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getOpenName() {
		return openName;
	}

	public void setOpenName(String openName) {
		this.openName = openName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
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

	public Integer getIsBindUser() {
		return isBindUser;
	}

	public void setIsBindUser(Integer isBindUser) {
		this.isBindUser = isBindUser;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public List<SaleIncome> getSaleincome() {
		return saleincome;
	}

	public void setSaleincome(List<SaleIncome> saleincome) {
		this.saleincome = saleincome;
	}

	public SaleInfo(String saleId, String salePwd, String trueName,
			String idCard, Date birthday, Integer money, String salePic,
			String postalType, String bankName, String openName,
			String accountNumber, Integer type, String phone, Integer sex,
			String address, Integer bindUserNum, Integer isBindUser,
			List<User> user, List<SaleIncome> saleincome) {
		super();
		this.saleId = saleId;
		this.salePwd = salePwd;
		this.trueName = trueName;
		this.idCard = idCard;
		this.birthday = birthday;
		this.money = money;
		this.salePic = salePic;
		this.postalType = postalType;
		this.bankName = bankName;
		this.openName = openName;
		this.accountNumber = accountNumber;
		this.type = type;
		this.phone = phone;
		this.sex = sex;
		this.address = address;
		this.bindUserNum = bindUserNum;
		this.isBindUser = isBindUser;
		this.user = user;
		this.saleincome = saleincome;
	}

	public SaleInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SaleInfo(Date created, Date updated) {
		super(created, updated);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SaleInfo [saleId=" + saleId + ", salePwd=" + salePwd
				+ ", trueName=" + trueName + ", idCard=" + idCard
				+ ", birthday=" + birthday + ", money=" + money + ", salePic="
				+ salePic + ", postalType=" + postalType + ", bankName="
				+ bankName + ", openName=" + openName + ", accountNumber="
				+ accountNumber + ", type=" + type + ", phone=" + phone
				+ ", sex=" + sex + ", address=" + address + ", bindUserNum="
				+ bindUserNum + ", isBindUser=" + isBindUser + ", user=" + user
				+ ", saleincome=" + saleincome + "]";
	}

	
}
