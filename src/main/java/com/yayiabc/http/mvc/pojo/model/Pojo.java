package com.yayiabc.http.mvc.pojo.model;

import java.util.Arrays;

public class Pojo {
   private String name;
   private Location location;
   private DetailInfo detail_info;
   private String address;
   private String street_id;
   private String detail;
   private String uid;
   private String cityName;
  private String telephone;
   
   
   
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getCityName() {
	return cityName;
}
public void setCityName(String cityName) {
	this.cityName = cityName;
}
public DetailInfo getDetail_info() {
	return detail_info;
}
public void setDetail_info(DetailInfo detail_info) {
	this.detail_info = detail_info;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Location getLocation() {
	return location;
}
public void setLocation(Location location) {
	this.location = location;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getStreet_id() {
	return street_id;
}
public void setStreet_id(String street_id) {
	this.street_id = street_id;
}
public String getDetail() {
	return detail;
}
public void setDetail(String detail) {
	this.detail = detail;
}
public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;
}
@Override
public String toString() {
	return "Pojo [name=" + name + ", location=" + location + ", detail_info=" + detail_info + ", address=" + address
			+ ", street_id=" + street_id + ", detail=" + detail + ", uid=" + uid + ", cityName=" + cityName
			+ ", telephone=" + telephone + "]";
}




   
}
