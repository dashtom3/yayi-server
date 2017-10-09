package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * SELECT sale.true_name,sale.phone,sale.postal_type,sale.open_name,sale.bank_name,sale.account_number

,bala.balance,bala.created,bala.balance_out,bala.describey,bala.balance_id 
 * @author Administrator
 *
 */
public class With {
	
	private Integer balanceId;
	private String saleId;
	private String trueName;// 姓名


	private String postalType;// 提现类型

	private String bankName;// 银行名称

	private String openName;// 开户者

	private String accountNumber;// 支付宝账号 或 银行卡号

	private Integer type; // 类型
	private String phone;// 手机号码
	private Double balanceOut;//提现金额
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date create;    //申请时间
	private String describey;// 提现状态
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date adoptTime;
	
	
	public Date getAdoptTime() {
		return adoptTime;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public void setAdoptTime(Date adoptTime) {
		this.adoptTime = adoptTime;
	}
	public String getPostalType() {
		return postalType;
	}
	public void setPostalType(String postalType) {
		this.postalType = postalType;
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
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
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
	public Double getBalanceOut() {
		return balanceOut;
	}
	public void setBalanceOut(Double balanceOut) {
		this.balanceOut = balanceOut;
	}
	public Date getCreate() {
		return create;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public void setCreate(Date create) {
		this.create = create;
	}
	public String getDescribey() {
		return describey;
	}
	public void setDescribey(String describey) {
		this.describey = describey;
	}
	@Override
	public String toString() {
		return "With [saleId=" + saleId + ", trueName=" + trueName + ", postalType=" + postalType + ", bankName="
				+ bankName + ", openName=" + openName + ", accountNumber=" + accountNumber + ", type=" + type
				+ ", phone=" + phone + ", balanceOut=" + balanceOut + ", create=" + create + ", describey=" + describey
				+ "]";
	}
}
