package com.yayiabc.http.mvc.pojo.jpa;

public class CusResources {
    private Integer cusId;
    private String unitName;
    private String unitAddress;
    private String contacts;
    private String contactsPhone;
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getContactsPhone() {
		return contactsPhone;
	}
	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	@Override
	public String toString() {
		return "CusResources [cusId=" + cusId + ", unitName=" + unitName
				+ ", unitAddress=" + unitAddress + ", contacts=" + contacts
				+ ", contactsPhone=" + contactsPhone + "]";
	}
    
}
