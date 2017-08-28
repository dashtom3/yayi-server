package com.yayiabc.http.mvc.pojo.jpa;

import java.io.Serializable;

public class ItemValue implements Serializable{
	private String itemId;
	private String itemSKU;
	private Double itemSkuPrice;
	private Double  tiChen;
	private Integer itemQb;
	private Integer stockNum;
	private Integer canUse;
	private Integer itemValueId;
	private String itemPropertyName;
	private String itemPropertyInfo;
	private String itemPropertyNameTwo;
	private String itemPropertyTwoValue;
	private String itemPropertyNameThree;
	private String itemPropertyThreeValue;
	private String itemPropertyFourName;
	private String itemPropertyFourValue;
	private String itemPropertyFiveName;
	private String itemPropertyFiveValue;
	private String itemPropertySixName;
	private String itemPropertySixValue;
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
	public Double getItemSkuPrice() {
		return itemSkuPrice;
	}
	public void setItemSkuPrice(Double itemSkuPrice) {
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
	public String getItemPropertyNameTwo() {
		return itemPropertyNameTwo;
	}
	public void setItemPropertyNameTwo(String itemPropertyNameTwo) {
		this.itemPropertyNameTwo = itemPropertyNameTwo;
	}
	public String getItemPropertyTwoValue() {
		return itemPropertyTwoValue;
	}
	public void setItemPropertyTwoValue(String itemPropertyTwoValue) {
		this.itemPropertyTwoValue = itemPropertyTwoValue;
	}
	public String getItemPropertyNameThree() {
		return itemPropertyNameThree;
	}
	public void setItemPropertyNameThree(String itemPropertyNameThree) {
		this.itemPropertyNameThree = itemPropertyNameThree;
	}
	public String getItemPropertyThreeValue() {
		return itemPropertyThreeValue;
	}
	public void setItemPropertyThreeValue(String itemPropertyThreeValue) {
		this.itemPropertyThreeValue = itemPropertyThreeValue;
	}
	public String getItemPropertyFourName() {
		return itemPropertyFourName;
	}
	public void setItemPropertyFourName(String itemPropertyFourName) {
		this.itemPropertyFourName = itemPropertyFourName;
	}
	public String getItemPropertyFourValue() {
		return itemPropertyFourValue;
	}
	public void setItemPropertyFourValue(String itemPropertyFourValue) {
		this.itemPropertyFourValue = itemPropertyFourValue;
	}
	public String getItemPropertyFiveName() {
		return itemPropertyFiveName;
	}
	public void setItemPropertyFiveName(String itemPropertyFiveName) {
		this.itemPropertyFiveName = itemPropertyFiveName;
	}
	public String getItemPropertyFiveValue() {
		return itemPropertyFiveValue;
	}
	public void setItemPropertyFiveValue(String itemPropertyFiveValue) {
		this.itemPropertyFiveValue = itemPropertyFiveValue;
	}
	public String getItemPropertySixName() {
		return itemPropertySixName;
	}
	public void setItemPropertySixName(String itemPropertySixName) {
		this.itemPropertySixName = itemPropertySixName;
	}
	public String getItemPropertySixValue() {
		return itemPropertySixValue;
	}
	public void setItemPropertySixValue(String itemPropertySixValue) {
		this.itemPropertySixValue = itemPropertySixValue;
	}
	public ItemValue(String itemId, String itemSKU, Double itemSkuPrice,
			Double tiChen, Integer itemQb, Integer stockNum, Integer canUse,
			Integer itemValueId, String itemPropertyName,
			String itemPropertyInfo, String itemPropertyNameTwo,
			String itemPropertyTwoValue, String itemPropertyNameThree,
			String itemPropertyThreeValue, String itemPropertyFourName,
			String itemPropertyFourValue, String itemPropertyFiveName,
			String itemPropertyFiveValue, String itemPropertySixName,
			String itemPropertySixValue) {
		super();
		this.itemId = itemId;
		this.itemSKU = itemSKU;
		this.itemSkuPrice = itemSkuPrice;
		this.tiChen = tiChen;
		this.itemQb = itemQb;
		this.stockNum = stockNum;
		this.canUse = canUse;
		this.itemValueId = itemValueId;
		this.itemPropertyName = itemPropertyName;
		this.itemPropertyInfo = itemPropertyInfo;
		this.itemPropertyNameTwo = itemPropertyNameTwo;
		this.itemPropertyTwoValue = itemPropertyTwoValue;
		this.itemPropertyNameThree = itemPropertyNameThree;
		this.itemPropertyThreeValue = itemPropertyThreeValue;
		this.itemPropertyFourName = itemPropertyFourName;
		this.itemPropertyFourValue = itemPropertyFourValue;
		this.itemPropertyFiveName = itemPropertyFiveName;
		this.itemPropertyFiveValue = itemPropertyFiveValue;
		this.itemPropertySixName = itemPropertySixName;
		this.itemPropertySixValue = itemPropertySixValue;
	}
	public ItemValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ItemValue [itemId=" + itemId + ", itemSKU=" + itemSKU
				+ ", itemSkuPrice=" + itemSkuPrice + ", tiChen=" + tiChen
				+ ", itemQb=" + itemQb + ", stockNum=" + stockNum + ", canUse="
				+ canUse + ", itemValueId=" + itemValueId
				+ ", itemPropertyName=" + itemPropertyName
				+ ", itemPropertyInfo=" + itemPropertyInfo
				+ ", itemPropertyNameTwo=" + itemPropertyNameTwo
				+ ", itemPropertyTwoValue=" + itemPropertyTwoValue
				+ ", itemPropertyNameThree=" + itemPropertyNameThree
				+ ", itemPropertyThreeValue=" + itemPropertyThreeValue
				+ ", itemPropertyFourName=" + itemPropertyFourName
				+ ", itemPropertyFourValue=" + itemPropertyFourValue
				+ ", itemPropertyFiveName=" + itemPropertyFiveName
				+ ", itemPropertyFiveValue=" + itemPropertyFiveValue
				+ ", itemPropertySixName=" + itemPropertySixName
				+ ", itemPropertySixValue=" + itemPropertySixValue + "]";
	}
	
	
}
