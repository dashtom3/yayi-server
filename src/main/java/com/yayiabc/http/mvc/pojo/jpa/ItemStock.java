package com.yayiabc.http.mvc.pojo.jpa;


/**
 * 
 * @author xiaojiang 商品库存表
 */
public class ItemStock extends BasePojo {
	private String itemStockId;

	private String stockName;

	private String itemId;

	private Integer itemStockNum;

	private Integer isWarning;

	private Integer warningNum;

	public String getItemStockId() {
		return itemStockId;
	}

	public void setItemStockId(String itemStockId) {
		this.itemStockId = itemStockId == null ? null : itemStockId.trim();
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName == null ? null : stockName.trim();
	}

	public Integer getItemStockNum() {
		return itemStockNum;
	}

	public void setItemStockNum(Integer itemStockNum) {
		this.itemStockNum = itemStockNum;
	}

	public Integer getIsWarning() {
		return isWarning;
	}

	public void setIsWarning(Integer isWarning) {
		this.isWarning = isWarning;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getWarningNum() {
		return warningNum;
	}

	public void setWarningNum(Integer warningNum) {
		this.warningNum = warningNum;
	}

	public ItemStock() {
		super();
	}

	public ItemStock(String itemStockId, String stockName, String itemId,
			Integer itemStockNum, Integer isWarning, Integer warningNum) {
		super();
		this.itemStockId = itemStockId;
		this.stockName = stockName;
		this.itemId = itemId;
		this.itemStockNum = itemStockNum;
		this.isWarning = isWarning;
		this.warningNum = warningNum;
	}

	@Override
	public String toString() {
		return "ItemStock [itemStockId=" + itemStockId + ", stockName="
				+ stockName + ", itemId=" + itemId + ", itemStockNum="
				+ itemStockNum + ", isWarning=" + isWarning + ", warningNum="
				+ warningNum + "]";
	}

}