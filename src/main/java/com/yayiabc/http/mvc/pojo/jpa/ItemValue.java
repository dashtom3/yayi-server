package com.yayiabc.http.mvc.pojo.jpa;

public class ItemValue {
	private String itemSKU;
	private Integer itemSkuPrice;
	private Double  tiChen;
	private Integer itemQb;
	private Integer stockNum;
	private Integer canUse;
	
	private Integer itemValueId;
	private String itemPropertyName;
	private String itemPropertyInfo;
	public String getItemSKU() {
		return itemSKU;
	}
	public void setItemSKU(String itemSKU) {
		this.itemSKU = itemSKU;
	}
	public Integer getItemSkuPrice() {
		return itemSkuPrice;
	}
	public void setItemSkuPrice(Integer itemSkuPrice) {
		this.itemSkuPrice = itemSkuPrice;
	}
	public Double getTiChen() {
		return tiChen;
	}
	public void setTiChen(Double tiChen) {
		this.tiChen = tiChen;
	}
	public Integer getItemQb() {
		return itemQb;
	}
	public void setItemQb(Integer itemQb) {
		this.itemQb = itemQb;
	}
	public Integer getStockNum() {
		return stockNum;
	}
	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}
	public Integer getCanUse() {
		return canUse;
	}
	public void setCanUse(Integer canUse) {
		this.canUse = canUse;
	}
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
	public ItemValue(String itemSKU, Integer itemSkuPrice, Double tiChen,
			Integer itemQb, Integer stockNum, Integer canUse,
			Integer itemValueId, String itemPropertyName,
			String itemPropertyInfo) {
		super();
		this.itemSKU = itemSKU;
		this.itemSkuPrice = itemSkuPrice;
		this.tiChen = tiChen;
		this.itemQb = itemQb;
		this.stockNum = stockNum;
		this.canUse = canUse;
		this.itemValueId = itemValueId;
		this.itemPropertyName = itemPropertyName;
		this.itemPropertyInfo = itemPropertyInfo;
	}
	public ItemValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ItemValue [itemSKU=" + itemSKU + ", itemSkuPrice="
				+ itemSkuPrice + ", tiChen=" + tiChen + ", itemQb=" + itemQb
				+ ", stockNum=" + stockNum + ", canUse=" + canUse
				+ ", itemValueId=" + itemValueId + ", itemPropertyName="
				+ itemPropertyName + ", itemPropertyInfo=" + itemPropertyInfo
				+ "]";
	}
	
}
