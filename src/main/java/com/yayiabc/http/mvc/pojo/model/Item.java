package com.yayiabc.http.mvc.pojo.model;

import java.util.List;

import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemDetail;
import com.yayiabc.http.mvc.pojo.jpa.ItemProperty;
import com.yayiabc.http.mvc.pojo.jpa.ItemValue;

public class Item {
	private String itemId;
	private String itemName;
	private ItemBrand itemBrand;
	private Integer itemStockNum;
	private Integer sales;
	private Integer state;
	private String oneClassify;
	private String twoClassify;
	private String threeClassify;
	private List<ItemValue> itemValueList;
	private ItemDetail itemDetail;
	private List<ItemProperty> itemPropertyList;
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
	public ItemBrand getItemBrand() {
		return itemBrand;
	}
	public void setItemBrand(ItemBrand itemBrand) {
		this.itemBrand = itemBrand;
	}
	public Integer getItemStockNum() {
		return itemStockNum;
	}
	public void setItemStockNum(Integer itemStockNum) {
		this.itemStockNum = itemStockNum;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getOneClassify() {
		return oneClassify;
	}
	public void setOneClassify(String oneClassify) {
		this.oneClassify = oneClassify;
	}
	public String getTwoClassify() {
		return twoClassify;
	}
	public void setTwoClassify(String twoClassify) {
		this.twoClassify = twoClassify;
	}
	public String getThreeClassify() {
		return threeClassify;
	}
	public void setThreeClassify(String threeClassify) {
		this.threeClassify = threeClassify;
	}
	public List<ItemValue> getItemValueList() {
		return itemValueList;
	}
	public void setItemValueList(List<ItemValue> itemValueList) {
		this.itemValueList = itemValueList;
	}
	public ItemDetail getItemDetail() {
		return itemDetail;
	}
	public void setItemDetail(ItemDetail itemDetail) {
		this.itemDetail = itemDetail;
	}
	public List<ItemProperty> getItemPropertyList() {
		return itemPropertyList;
	}
	public void setItemPropertyList(List<ItemProperty> itemPropertyList) {
		this.itemPropertyList = itemPropertyList;
	}
	public Item(String itemId, String itemName, ItemBrand itemBrand,
			Integer itemStockNum, Integer sales, Integer state,
			String oneClassify, String twoClassify, String threeClassify,
			List<ItemValue> itemValueList, ItemDetail itemDetail,
			List<ItemProperty> itemPropertyList) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemBrand = itemBrand;
		this.itemStockNum = itemStockNum;
		this.sales = sales;
		this.state = state;
		this.oneClassify = oneClassify;
		this.twoClassify = twoClassify;
		this.threeClassify = threeClassify;
		this.itemValueList = itemValueList;
		this.itemDetail = itemDetail;
		this.itemPropertyList = itemPropertyList;
	}
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName
				+ ", itemBrand=" + itemBrand + ", itemStockNum=" + itemStockNum
				+ ", sales=" + sales + ", state=" + state + ", oneClassify="
				+ oneClassify + ", twoClassify=" + twoClassify
				+ ", threeClassify=" + threeClassify + ", itemValueList="
				+ itemValueList + ", itemDetail=" + itemDetail
				+ ", itemPropertyList=" + itemPropertyList + "]";
	}
	
	
}
