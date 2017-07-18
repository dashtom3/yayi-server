package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;

import com.yayiabc.http.mvc.pojo.model.Property;

/**
 * 
 * @author xiaojiang 商品信息表
 */
public class ItemInfo extends BasePojo {
	private String itemId;

	private String itemName;

	private List<ItemValue> itemValueList;
	
	private Integer isThrow;
	
	private String itemSort;
	

	public Integer getIsThrow() {
		return isThrow;
	}

	public void setIsThrow(Integer isThrow) {
		this.isThrow = isThrow;
	}

	private Integer itemStockNum;

	// private Refund refundId;

	private Double itemPrice;

	private Integer sales;

	private Integer state;

	private String oneClassify;

	private String twoClassify;

	private String threeClassify;

	private String itemPnamea;   //这里改为了String

	private String itemPnameb;

	private String itemPnamec;
	
	private ItemDetail itemDetail;

	private List<Comments> commentList;

	private ItemBrand itemBrand;
	
	private List<Property> propertyList;
	
	


	public String getItemSort() {
		return itemSort;
	}

	public void setItemSort(String itemSort) {
		this.itemSort = itemSort;
	}

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

	public List<ItemValue> getItemValueList() {
		return itemValueList;
	}

	public void setItemValueList(List<ItemValue> itemValueList) {
		this.itemValueList = itemValueList;
	}

	public Integer getItemStockNum() {
		return itemStockNum;
	}

	public void setItemStockNum(Integer itemStockNum) {
		this.itemStockNum = itemStockNum;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
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
		if(oneClassify==null||"".equals(oneClassify)){
			oneClassify=null;
		}else {
			
			this.oneClassify = oneClassify;
		}
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

	public String getItemPnamea() {
		return itemPnamea;
	}

	public void setItemPnamea(String itemPnamea) {
		this.itemPnamea = itemPnamea;
	}

	public String getItemPnameb() {
		return itemPnameb;
	}

	public void setItemPnameb(String itemPnameb) {
		this.itemPnameb = itemPnameb;
	}

	public String getItemPnamec() {
		return itemPnamec;
	}

	public void setItemPnamec(String itemPnamec) {
		this.itemPnamec = itemPnamec;
	}

	public ItemDetail getItemDetail() {
		return itemDetail;
	}

	public void setItemDetail(ItemDetail itemDetail) {
		this.itemDetail = itemDetail;
	}

	public List<Comments> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comments> commentList) {
		this.commentList = commentList;
	}

	public ItemBrand getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(ItemBrand itemBrand) {
		this.itemBrand = itemBrand;
	}

	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}

	public ItemInfo(String itemId, String itemName,
			List<ItemValue> itemValueList, Integer isThrow, String itemSort,
			Integer itemStockNum, Double itemPrice, Integer sales,
			Integer state, String oneClassify, String twoClassify,
			String threeClassify, String itemPnamea, String itemPnameb,
			String itemPnamec, ItemDetail itemDetail,
			List<Comments> commentList, ItemBrand itemBrand,
			List<Property> propertyList) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemValueList = itemValueList;
		this.isThrow = isThrow;
		this.itemSort = itemSort;
		this.itemStockNum = itemStockNum;
		this.itemPrice = itemPrice;
		this.sales = sales;
		this.state = state;
		this.oneClassify = oneClassify;
		this.twoClassify = twoClassify;
		this.threeClassify = threeClassify;
		this.itemPnamea = itemPnamea;
		this.itemPnameb = itemPnameb;
		this.itemPnamec = itemPnamec;
		this.itemDetail = itemDetail;
		this.commentList = commentList;
		this.itemBrand = itemBrand;
		this.propertyList = propertyList;
	}

	public ItemInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemInfo(Date created, Date updated) {
		super(created, updated);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ItemInfo [itemId=" + itemId + ", itemName=" + itemName
				+ ", itemValueList=" + itemValueList + ", isThrow=" + isThrow
				+ ", itemSort=" + itemSort + ", itemStockNum=" + itemStockNum
				+ ", itemPrice=" + itemPrice + ", sales=" + sales + ", state="
				+ state + ", oneClassify=" + oneClassify + ", twoClassify="
				+ twoClassify + ", threeClassify=" + threeClassify
				+ ", itemPnamea=" + itemPnamea + ", itemPnameb=" + itemPnameb
				+ ", itemPnamec=" + itemPnamec + ", itemDetail=" + itemDetail
				+ ", commentList=" + commentList + ", itemBrand=" + itemBrand
				+ ", propertyList=" + propertyList + "]";
	}

}