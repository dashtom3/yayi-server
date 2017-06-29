package com.yayiabc.http.mvc.pojo.jpa;

public class ItemValue {
	private Integer itemValueId;
	private String itemPropertyName;
	private String itemPropertyInfo;
	public Integer getItemValueId() {
		return itemValueId;
	}
	public void setItemValueId(Integer itemValueId) {
		this.itemValueId = itemValueId;
	}
	public String getItemPropertyName() {
		return itemPropertyName;
	}
	public void setItemPropertyName(String itemPropertyName) {
		this.itemPropertyName = itemPropertyName;
	}
	public String getItemPropertyInfo() {
		return itemPropertyInfo;
	}
	public void setItemPropertyInfo(String itemPropertyInfo) {
		this.itemPropertyInfo = itemPropertyInfo;
	}
	
	
	
	public ItemValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemValue(Integer itemValueId, String itemPropertyName,
			String itemPropertyInfo) {
		super();
		this.itemValueId = itemValueId;
		this.itemPropertyName = itemPropertyName;
		this.itemPropertyInfo = itemPropertyInfo;
	}
	@Override
	public String toString() {
		return "ItemValue [itemValueId=" + itemValueId + ", itemPropertyName="
				+ itemPropertyName + ", itemPropertyInfo=" + itemPropertyInfo
				+ "]";
	}
	
}
