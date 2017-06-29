package com.yayiabc.http.mvc.pojo.jpa;


public class ItemStar extends BasePojo {
	private Integer itemStarId;

	private String userId;

	private String itemId;

	public Integer getItemStarId() {
		return itemStarId;
	}

	public void setItemStarId(Integer itemStarId) {
		this.itemStarId = itemStarId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public ItemStar() {
		super();
	}

	public ItemStar(Integer itemStarId, String userId, String itemId) {
		super();
		this.itemStarId = itemStarId;
		this.userId = userId;
		this.itemId = itemId;
	}

	@Override
	public String toString() {
		return "ItemStar [itemStarId=" + itemStarId + ", userId=" + userId
				+ ", itemId=" + itemId + "]";
	}

}
