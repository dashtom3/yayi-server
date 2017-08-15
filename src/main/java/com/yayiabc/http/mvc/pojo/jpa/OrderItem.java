package com.yayiabc.http.mvc.pojo.jpa;

import java.util.Date;
import java.util.List;



//import com.sun.tools.javac.util.List;

/**
 * 
 * @author xiaojiang 订单商品表
 */
public class OrderItem extends BasePojo {
	private String itemId;
	private String itemName;
	private String orderId;
	private Ordera ordera;
	private String itemSKU;
	private Integer qbDed;
	/**
	 * cartId,userId
	 */
	private Integer num;

	private Double price;

	private String totalFee;

	private String picPath;
	private Comments commentList;
	private Refund refund;
	private String  itemPropertyNamea;
	private String  itemPropertyNameb;
	private String  itemPropertyNamec;
	private String itemType;//商品类型
	private String refunNum;

	private String itemBrandName;//商品品牌
	
	public String getItemBrandName() {
		return itemBrandName;
	}

	


	public String getRefunNum() {
		return refunNum;
	}




	public void setRefunNum(String refunNum) {
		this.refunNum = refunNum;
	}




	public void setItemBrandName(String itemBrandName) {
		this.itemBrandName = itemBrandName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemSKU() {
		return itemSKU;
	}

	public void setItemSKU(String itemSKU) {
		this.itemSKU = itemSKU;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Refund getRefund() {
		return refund;
	}

	public void setRefund(Refund refund) {
		this.refund = refund;
	}


	public String getItemPropertyNamea() {
		return itemPropertyNamea;
	}

	public void setItemPropertyNamea(String itemPropertyNamea) {
		this.itemPropertyNamea = itemPropertyNamea;
	}

	public String getItemPropertyNameb() {
		return itemPropertyNameb;
	}

	public void setItemPropertyNameb(String itemPropertyNameb) {
		this.itemPropertyNameb = itemPropertyNameb;
	}

	public String getItemPropertyNamec() {
		return itemPropertyNamec;
	}

	public void setItemPropertyNamec(String itemPropertyNamec) {
		this.itemPropertyNamec = itemPropertyNamec;
	}

	private ItemInfo itemInfo;//加了一个这个

	public ItemInfo getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(ItemInfo itemInfo) {
		this.itemInfo = itemInfo;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getQbDed() {
		return qbDed;
	}

	public void setQbDed(Integer qbDed) {
		this.qbDed = qbDed;
	}

	public Ordera getOrdera() {
		return ordera;
	}

	public void setOrdera(Ordera ordera) {
		this.ordera = ordera;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee == null ? null : totalFee.trim();
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath == null ? null : picPath.trim();
	}



	public Comments getCommentList() {
		return commentList;
	}


	public void setCommentList(Comments commentList) {
		this.commentList = commentList;
	}


	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(Date created, Date updated) {
		super(created, updated);
		// TODO Auto-generated constructor stub
	}




	@Override
	public String toString() {
		return "OrderItem [itemId=" + itemId + ", itemName=" + itemName + ", orderId=" + orderId + ", ordera=" + ordera
				+ ", itemSKU=" + itemSKU + ", qbDed=" + qbDed + ", num=" + num + ", price=" + price + ", totalFee="
				+ totalFee + ", picPath=" + picPath + ", commentList=" + commentList + ", refund=" + refund
				+ ", itemPropertyNamea=" + itemPropertyNamea + ", itemPropertyNameb=" + itemPropertyNameb
				+ ", itemPropertyNamec=" + itemPropertyNamec + ", refunNum=" + refunNum + ", itemType=" + itemType
				+ ", itemBrandName=" + itemBrandName + ", itemInfo=" + itemInfo + "]";
	}

}