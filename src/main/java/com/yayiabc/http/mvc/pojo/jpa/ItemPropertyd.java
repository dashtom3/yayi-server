package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;

public class ItemPropertyd extends BasePojo {
	private String itemSKU;

	private String itemPparam;

	private Integer isEnable;
	
	private Integer itemSkuPrice;
	
	private Integer stockNum;
	
	private Integer itemQb;
	
	private Double tiCheng;
	
	
	
	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public Integer getItemQb() {
		return itemQb;
	}

	public void setItemQb(Integer itemQb) {
		this.itemQb = itemQb;
	}

	

	public Integer getItemSkuPrice() {
		return itemSkuPrice;
	}

	public void setItemSkuPrice(Integer itemSkuPrice) {
		this.itemSkuPrice = itemSkuPrice;
	}

	public String getItemSKU() {
		return itemSKU;
	}

	public void setItemSKU(String itemSKU) {
		this.itemSKU = itemSKU;
	}



	public String getItemPparam() {
		return itemPparam;
	}

	public void setItemPparam(String itemPparam) {
		this.itemPparam = itemPparam == null ? null : itemPparam.trim();
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public ItemPropertyd() {
		super();
	}

	public Double getTiCheng() {
		return tiCheng;
	}

	public void setTiCheng(Double tiCheng) {
		this.tiCheng = tiCheng;
	}

	public ItemPropertyd(String itemSKU, String itemPparam, Integer isEnable,
			Integer itemSkuPrice, Integer stockNum, Integer itemQb,
			Double tiCheng) {
		super();
		this.itemSKU = itemSKU;
		this.itemPparam = itemPparam;
		this.isEnable = isEnable;
		this.itemSkuPrice = itemSkuPrice;
		this.stockNum = stockNum;
		this.itemQb = itemQb;
		this.tiCheng = tiCheng;
	}

	public ItemPropertyd(Date created, Date updated) {
		super(created, updated);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ItemPropertyd [itemSKU=" + itemSKU + ", itemPparam="
				+ itemPparam + ", isEnable=" + isEnable + ", itemSkuPrice="
				+ itemSkuPrice + ", stockNum=" + stockNum + ", itemQb="
				+ itemQb + ", tiCheng=" + tiCheng + "]";
	}

	
	

}