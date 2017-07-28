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

	public ItemStatistics() {
		super();
	}

	public ItemStatistics(String itemId, String itemName, String itemSKU,
			String itemBrandName, double price, Integer sales,
			double salesMoney, Integer refundNum) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemSKU = itemSKU;
		this.itemBrandName = itemBrandName;
		this.price = price;
		this.sales = sales;
		this.salesMoney = salesMoney;
		this.refundNum = refundNum;
	}

	@Override
	public String toString() {
		return "ItemStatistics [itemId=" + itemId + ", itemName=" + itemName
				+ ", itemSKU=" + itemSKU + ", itemBrandName=" + itemBrandName
				+ ", price=" + price + ", sales=" + sales + ", salesMoney="
				+ salesMoney + ", refundNum=" + refundNum + "]";
	}

}
