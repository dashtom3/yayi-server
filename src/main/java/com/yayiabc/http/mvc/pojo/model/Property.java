package com.yayiabc.http.mvc.pojo.model;

import java.io.Serializable;
import java.util.List;

public class Property implements Serializable{
	private String propertyName;
	private List<String> propertyInfoList;
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public List<String> getPropertyInfoList() {
		return propertyInfoList;
	}
	public void setPropertyInfoList(List<String> propertyInfoList) {
		this.propertyInfoList = propertyInfoList;
	}
	public Property(String propertyName, List<String> propertyInfoList) {
		super();
		this.propertyName = propertyName;
		this.propertyInfoList = propertyInfoList;
	}
	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Property [propertyName=" + propertyName + ", propertyInfoList="
				+ propertyInfoList + "]";
	}
	
}
