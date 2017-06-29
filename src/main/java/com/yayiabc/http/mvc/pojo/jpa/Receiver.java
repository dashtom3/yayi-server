package com.yayiabc.http.mvc.pojo.jpa;

/**
 * 
 * @author xiaojiang 收货地址表
 */
public class Receiver extends BasePojo {
	private Integer receiverId;

	private String userId;

	private String receiverName;

	private String province;

	private String city;

	private String county;

	private String receiverDetail;

	private String phone;

	private boolean isDefault;
	
	private String landlineNumber;
     
	public String getLandlineNumber() {
		return landlineNumber;
	}

	public void setLandlineNumber(String landlineNumber) {
		this.landlineNumber = landlineNumber;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getReceiverDetail() {
		return receiverDetail;
	}

	public void setReceiverDetail(String receiverDetail) {
		this.receiverDetail = receiverDetail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "Receiver [receiverId=" + receiverId + ", userId=" + userId
				+ ", receiverName=" + receiverName + ", province=" + province
				+ ", city=" + city + ", county=" + county + ", receiverDetail="
				+ receiverDetail + ", phone=" + phone + ", isDefault="
				+ isDefault + ", landlineNumber=" + landlineNumber + "]";
	}
}