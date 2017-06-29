package com.yayiabc.http.mvc.pojo.model;

import java.util.List;

import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;

public class SysResult {
	private List<Classify> classifyList;
	private List<ItemBrand> itemBrandList;
	public List<Classify> getClassifyList() {
		return classifyList;
	}
	public void setClassifyList(List<Classify> classifyList) {
		this.classifyList = classifyList;
	}
	public List<ItemBrand> getItemBrandList() {
		return itemBrandList;
	}
	public void setItemBrandList(List<ItemBrand> itemBrandList) {
		this.itemBrandList = itemBrandList;
	}
	public SysResult(List<Classify> classifyList, List<ItemBrand> itemBrandList) {
		super();
		this.classifyList = classifyList;
		this.itemBrandList = itemBrandList;
	}
	public SysResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SysResult [classifyList=" + classifyList + ", itemBrandList="
				+ itemBrandList + "]";
	}
	
}
