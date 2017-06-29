package com.yayiabc.http.mvc.pojo.model;

import java.util.List;

import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;

public class ItemShow {
	private String oneClassify;
	private List<ItemInfo> itemInfoList;
	public String getOneClassify() {
		return oneClassify;
	}
	public void setOneClassify(String oneClassify) {
		this.oneClassify = oneClassify;
	}
	public List<ItemInfo> getItemInfoList() {
		return itemInfoList;
	}
	public void setItemInfoList(List<ItemInfo> itemInfoList) {
		this.itemInfoList = itemInfoList;
	}
	public ItemShow(String oneClassify, List<ItemInfo> itemInfoList) {
		super();
		this.oneClassify = oneClassify;
		this.itemInfoList = itemInfoList;
	}
	public ItemShow() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ItemShow [oneClassify=" + oneClassify + ", itemInfoList="
				+ itemInfoList + "]";
	}
	
}
