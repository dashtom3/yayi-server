package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author xiaojiang 销售员信息表
 */

public class SaleInfo extends BasePojo {
	private String saleId;

	private String salePwd; // 密码

	private String trueName;// 姓名

	private String idCard;// 身份证号码

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;// 出生日期

	private double money;// 钱包余额

	private String salePic;// 头像

	private String postalType;// 提现类型

	private String bankName;// 银行名称

	private String openName;// 开户者

	private String accountNumber;// 支付宝账号 或 银行卡号

	private Integer type; // 类型

	private String phone;// 手机号码

	private Integer sex;// 性别

	private String address; // 地址

	private String weChar;// 微信号

	private String email;// 邮箱

	private String part;// 地区

	private String education; // 学历

	private String workUnit;// 工作单位

	private String workPosition;// 工作职位

	private Integer bindUserNum; // 绑定用户数

	private Integer isBindUser; // 是否绑定用户

	private double totalGetMoney; // 累计收入
	
	private String referrals;	//推荐人

	private List<User> user;

	private List<SaleIncome> saleincome;

	private List<With> With;

	public String getSaleId() {
		return saleId;
	}

	public String getSalePwd() {
		return salePwd;
	}

	public void setSalePwd(String salePwd) {
		this.salePwd = salePwd;
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

	public String getWeChar() {
		return weChar;
	}

	public void setWeChar(String weChar) {
		this.weChar = weChar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getWorkPosition() {
		return workPosition;
	}

	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}



	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Integer getSex() {
		return sex;
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

	public double getTotalGetMoney() {
		return totalGetMoney;
	}

	public void setTotalGetMoney(double totalGetMoney) {
		this.totalGetMoney = totalGetMoney;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	
	
	public String getReferrals() {
		return referrals;
	}

	public void setReferrals(String referrals) {
		this.referrals = referrals;
	}

	public SaleInfo() {
		super();
	}

	public SaleInfo(String saleId, String trueName, String idCard,
			Date birthday, String salePic, Integer sex, String address,
			String weChar, String email, String part, String education,
			String workUnit, String workPosition,String referrals) {
		super();
		this.saleId = saleId;
		this.trueName = trueName;
		this.idCard = idCard;
		this.birthday = birthday;
		this.salePic = salePic;
		this.sex = sex;
		this.address = address;
		this.weChar = weChar;
		this.email = email;
		this.part = part;
		this.education = education;
		this.workUnit = workUnit;
		this.workPosition = workPosition;
		this.referrals=referrals;
	}

	public SaleInfo(String saleId, String salePwd, String trueName,
			String idCard, Date birthday, double money, String salePic,
			String postalType, String bankName, String openName,
			String accountNumber, Integer type, String phone, Integer sex,
			String address, String weChar, String email, String part,
			String education, String workUnit, String workPosition,
			Integer bindUserNum, Integer isBindUser, List<User> user,
			List<SaleIncome> saleincome, double totalGetMoney,
			List<com.yayiabc.http.mvc.pojo.jpa.With> with,String referrals) {
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
		this.weChar = weChar;
		this.email = email;
		this.part = part;
		this.education = education;
		this.workUnit = workUnit;
		this.workPosition = workPosition;
		this.bindUserNum = bindUserNum;
		this.isBindUser = isBindUser;
		this.user = user;
		this.totalGetMoney = totalGetMoney;
		this.saleincome = saleincome;
		With = with;
		this.referrals=referrals;
	}

	public SaleInfo(String saleId, String trueName, String phone,
			Integer bindUserNum, Integer isBindUser, double totalGetMoney) {
		super();
		this.saleId = saleId;
		this.trueName = trueName;
		this.phone = phone;
		this.bindUserNum = bindUserNum;
		this.isBindUser = isBindUser;
		this.totalGetMoney = totalGetMoney;
	}

	public List<With> getWith() {
		return With;
	}

	public void setWith(List<With> with) {
		With = with;
	}

	@Override
	public String toString() {
		return "SaleInfo [saleId=" + saleId + ", salePwd=" + salePwd
				+ ", trueName=" + trueName + ", idCard=" + idCard
				+ ", birthday=" + birthday + ", money=" + money + ", salePic="
				+ salePic + ", postalType=" + postalType + ", bankName="
				+ bankName + ", openName=" + openName + ", accountNumber="
				+ accountNumber + ", type=" + type + ", phone=" + phone
				+ ", sex=" + sex + ", address=" + address + ", weChar="
				+ weChar + ", email=" + email + ", part=" + part
				+ ", education=" + education + ", workUnit=" + workUnit
				+ ", workPosition=" + workPosition + ", bindUserNum="
				+ bindUserNum + ", isBindUser=" + isBindUser
				+ ", totalGetMoney=" + totalGetMoney + ", user=" + user
				+ ", saleincome=" + saleincome + ", With=" + With + "]";
	}

}
