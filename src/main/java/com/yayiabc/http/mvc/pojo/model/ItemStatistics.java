package com.yayiabc.http.mvc.pojo.model;

/**
 * 商品统计表
 * 
 * @author xiaojiang
 * 
 */
public class ItemStatistics {
	private String itemId;

	private String itemName;

	private String itemSKU;

	private String itemBrandName;

	private double price;

	private Integer sales;

	private double salesMoney;

	private Integer refundNum;

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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemSKU() {
		return itemSKU;
	}

	public void setItemSKU(String itemSKU) {
		this.itemSKU = itemSKU;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSalesMoney() {
		return salesMoney;
	}

	public void setSalesMoney(double salesMoney) {
		this.salesMoney = salesMoney;
	}

	public Integer getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(Integer refundNum) {
		this.refundNum = refundNum;
	}

	public String getItemBrandName() {
		return itemBrandName;
	}

	public void setItemBrandName(String itemBrandName) {
		this.itemBrandName = itemBrandName;
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

	public ItemStatistics() {
		super();
	}

	public ItemStatistics(String itemId, String itemName, String itemSKU,
			String itemBrandName, double price, Integer sales,
			double salesMoney, Integer refundNum, String itemPropertyName,
			String itemPropertyInfo, String itemPropertyNameTwo,
			String itemPropertyTwoValue, String itemPropertyNameThree,
			String itemPropertyThreeValue, String itemPropertyFourName,
			String itemPropertyFourValue, String itemPropertyFiveName,
			String itemPropertyFiveValue, String itemPropertySixName,
			String itemPropertySixValue) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemSKU = itemSKU;
		this.itemBrandName = itemBrandName;
		this.price = price;
		this.sales = sales;
		this.salesMoney = salesMoney;
		this.refundNum = refundNum;
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

	@Override
	public String toString() {
		return "ItemStatistics [itemId=" + itemId + ", itemName=" + itemName
				+ ", itemSKU=" + itemSKU + ", itemBrandName=" + itemBrandName
				+ ", price=" + price + ", sales=" + sales + ", salesMoney="
				+ salesMoney + ", refundNum=" + refundNum
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
