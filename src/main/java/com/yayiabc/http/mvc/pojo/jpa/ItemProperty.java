package com.yayiabc.http.mvc.pojo.jpa;

import java.util.List;

public class ItemProperty extends BasePojo {
	private Integer itemPropertyId;

	private String itemPropertyName;

	private List<ItemPropertyd> itempropertydList;

	public List<ItemPropertyd> getItempropertydList() {
		return itempropertydList;
	}

	public void setItempropertydList(List<ItemPropertyd> itempropertydList) {
		this.itempropertydList = itempropertydList;
	}

	public Integer getItemPropertyId() {
		return itemPropertyId;
	}

	public void setItemPropertyId(Integer itemPropertyId) {
		this.itemPropertyId = itemPropertyId;
	}

	public String getItemPropertyName() {
		return itemPropertyName;
	}

	public void setItemPropertyName(String itemPropertyName) {
		if(itemPropertyName==null||"".equals(itemPropertyName)){
			this.itemPropertyName=null;
		}else {
			this.itemPropertyName=itemPropertyName;
		}
	}

	public ItemProperty() {
		super();
	}

	public ItemProperty(Integer itemPropertyId, String itemPropertyName,
			List<ItemPropertyd> itempropertydList) {
		super();
		this.itemPropertyId = itemPropertyId;
		this.itemPropertyName = itemPropertyName;
		this.itempropertydList = itempropertydList;
	}

	@Override
	public String toString() {
		return "ItemProperty [itemPropertyId=" + itemPropertyId
				+ ", itemPropertyName=" + itemPropertyName
				+ ", itempropertydList=" + itempropertydList + "]";
	}

}