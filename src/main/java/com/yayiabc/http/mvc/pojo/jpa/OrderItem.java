package com.yayiabc.http.mvc.pojo.jpa;

import java.util.List;



//import com.sun.tools.javac.util.List;

/**
 * 
 * @author xiaojiang 订单商品表
 */
public class OrderItem extends BasePojo {
	private String itemId;
	
	private Ordera ordera;

	private Integer qbDed;

	private Integer num;

	private Integer price;

	private String totalFee;

	private String picPath;
	private List<Comments> commentList;
	
    private Refund refund;
	
	
	private String  itemPropertyNamea;
	private String  itemPropertyNameb;
	private String  itemPropertyNamec;
     
	
	
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
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

	@Override
	public String toString() {
		return "OrderItem [itemId=" + itemId + ", ordera=" + ordera
				+ ", qbDed=" + qbDed + ", num=" + num + ", price=" + price
				+ ", totalFee=" + totalFee + ", picPath=" + picPath + "]";
	}

	public List<Comments> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comments> commentList) {
		this.commentList = commentList;
	}

}