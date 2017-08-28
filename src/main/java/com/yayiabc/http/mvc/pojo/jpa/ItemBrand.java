package com.yayiabc.http.mvc.pojo.jpa;


import java.io.Serializable;

/**
 * 
 * @author xiaojiang 商品品牌表
 */
public class ItemBrand extends BasePojo implements Serializable {
	private Integer itemBrandId;

	private String itemBrandLogo;

	private String itemBrandHome;

	private String itemBrandName;

	

	public Integer getItemBrandId() {
		return itemBrandId;
	}

	public void setItemBrandId(Integer itemBrandId) {
		this.itemBrandId = itemBrandId;
	}

	public String getItemBrandLogo() {
		return itemBrandLogo;
	}

	public void setItemBrandLogo(String itemBrandLogo) {
		this.itemBrandLogo = itemBrandLogo == null ? null : itemBrandLogo
				.trim();
	}

	public String getItemBrandHome() {
		return itemBrandHome;
	}

	public void setItemBrandHome(String itemBrandHome) {
		if(itemBrandHome==null||"".equals(itemBrandHome)){
			this.itemBrandHome=null;
		}else{
			this.itemBrandHome=itemBrandHome;
		}
	}

	public String getItemBrandName() {
		return itemBrandName;
	}

	public void setItemBrandName(String itemBrandName) {
		if(itemBrandName==null||"".equals(itemBrandName)){
			this.itemBrandName=null;
		}else{
			this.itemBrandName=itemBrandName;
		}
	}
	public ItemBrand() {
		super();
	}

	public ItemBrand(Integer itemBrandId, String itemBrandLogo,
			String itemBrandHome, String itemBrandName) {
		super();
		this.itemBrandId = itemBrandId;
		this.itemBrandLogo = itemBrandLogo;
		this.itemBrandHome = itemBrandHome;
		this.itemBrandName = itemBrandName;
	}

	@Override
	public String toString() {
		return "ItemBrand [itemBrandId=" + itemBrandId + ", itemBrandLogo="
				+ itemBrandLogo + ", itemBrandHome=" + itemBrandHome
				+ ", itemBrandName=" + itemBrandName  + "]";
	}

}