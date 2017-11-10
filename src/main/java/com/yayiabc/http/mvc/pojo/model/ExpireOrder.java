package com.yayiabc.http.mvc.pojo.model;

public class ExpireOrder {
	private String  orderId;
	private String itemId;
	private String itemSKU;
	
	private int itemNum;
	
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemSKU() {
		return itemSKU;
	}
	public void setItemSKU(String itemSKU) {
		this.itemSKU = itemSKU;
	}
	@Override
	public String toString() {
		return "ExpireOrder [orderId=" + orderId + ", itemId=" + itemId + ", itemSKU=" + itemSKU + ", itemNum="
				+ itemNum + "]";
	}

}
