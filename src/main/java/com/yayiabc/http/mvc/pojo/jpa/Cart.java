package com.yayiabc.http.mvc.pojo.jpa;

/**
 * 
 * @author xiaojiang 购物车表
 */
public class Cart extends BasePojo {
	private Integer cartId;

	private String userId;

	private String itemId;

	private String name;

	private String pic;

	private Integer num;

	private double price;

	private String itemSKU;

	private Integer state;
	
	private Integer goodLeaveNum;
	private Integer itemQb;

	private String itemPropertyName;
	private String itemPropertyInfo;
	private String itemPropertyNameTwo;
	private String itemPropertyTwoValue;
	private String itemPropertyNameThree;
	private String itemPropertyThreeValue;
	private String itemPropertyFourName;
	private String itemPropertyFourValue;
	private String itemPropertyFiveName;
	private String itemPropertyFiveValue;
	private String itemPropertySixName;
	private String itemPropertySixValue;
	
	private String itemBrandName;
	private String itemSort;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getItemSKU() {
		return itemSKU;
	}

	public void setItemSKU(String itemSKU) {
		this.itemSKU = itemSKU;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getGoodLeaveNum() {
		return goodLeaveNum;
	}

	public void setGoodLeaveNum(Integer goodLeaveNum) {
		this.goodLeaveNum = goodLeaveNum;
	}

	public Integer getItemQb() {
		return itemQb;
	}

	public void setItemQb(Integer itemQb) {
		this.itemQb = itemQb;
	}

	public String getItemPropertyName() {
		return itemPropertyName;
	}

	public void setItemPropertyName(String itemPropertyName) {
		this.itemPropertyName = itemPropertyName;
	}

	public String getItemPropertyInfo() {
		return itemPropertyInfo;
	}

	public void setItemPropertyInfo(String itemPropertyInfo) {
		this.itemPropertyInfo = itemPropertyInfo;
	}

	public String getItemPropertyNameTwo() {
		return itemPropertyNameTwo;
	}

	public void setItemPropertyNameTwo(String itemPropertyNameTwo) {
		this.itemPropertyNameTwo = itemPropertyNameTwo;
	}

	public String getItemPropertyTwoValue() {
		return itemPropertyTwoValue;
	}

	public void setItemPropertyTwoValue(String itemPropertyTwoValue) {
		this.itemPropertyTwoValue = itemPropertyTwoValue;
	}

	public String getItemPropertyNameThree() {
		return itemPropertyNameThree;
	}

	public void setItemPropertyNameThree(String itemPropertyNameThree) {
		this.itemPropertyNameThree = itemPropertyNameThree;
	}

	public String getItemPropertyThreeValue() {
		return itemPropertyThreeValue;
	}

	public void setItemPropertyThreeValue(String itemPropertyThreeValue) {
		this.itemPropertyThreeValue = itemPropertyThreeValue;
	}

	public String getItemPropertyFourName() {
		return itemPropertyFourName;
	}

	public void setItemPropertyFourName(String itemPropertyFourName) {
		this.itemPropertyFourName = itemPropertyFourName;
	}

	public String getItemPropertyFourValue() {
		return itemPropertyFourValue;
	}

	public void setItemPropertyFourValue(String itemPropertyFourValue) {
		this.itemPropertyFourValue = itemPropertyFourValue;
	}

	public String getItemPropertyFiveName() {
		return itemPropertyFiveName;
	}

	public void setItemPropertyFiveName(String itemPropertyFiveName) {
		this.itemPropertyFiveName = itemPropertyFiveName;
	}

	public String getItemPropertyFiveValue() {
		return itemPropertyFiveValue;
	}

	public void setItemPropertyFiveValue(String itemPropertyFiveValue) {
		this.itemPropertyFiveValue = itemPropertyFiveValue;
	}

	public String getItemPropertySixName() {
		return itemPropertySixName;
	}

	public void setItemPropertySixName(String itemPropertySixName) {
		this.itemPropertySixName = itemPropertySixName;
	}

	public String getItemPropertySixValue() {
		return itemPropertySixValue;
	}

	public void setItemPropertySixValue(String itemPropertySixValue) {
		this.itemPropertySixValue = itemPropertySixValue;
	}

	public String getItemBrandName() {
		return itemBrandName;
	}

	public void setItemBrandName(String itemBrandName) {
		this.itemBrandName = itemBrandName;
	}

	public String getItemSort() {
		return itemSort;
	}

	public void setItemSort(String itemSort) {
		this.itemSort = itemSort;
	}

	@Override
	public String toString() {
		return "Cart{" +
				"cartId=" + cartId +
				", userId='" + userId + '\'' +
				", itemId='" + itemId + '\'' +
				", name='" + name + '\'' +
				", pic='" + pic + '\'' +
				", num=" + num +
				", price=" + price +
				", itemSKU='" + itemSKU + '\'' +
				", state=" + state +
				", goodLeaveNum=" + goodLeaveNum +
				", itemQb=" + itemQb +
				", itemPropertyName='" + itemPropertyName + '\'' +
				", itemPropertyInfo='" + itemPropertyInfo + '\'' +
				", itemPropertyNameTwo='" + itemPropertyNameTwo + '\'' +
				", itemPropertyTwoValue='" + itemPropertyTwoValue + '\'' +
				", itemPropertyNameThree='" + itemPropertyNameThree + '\'' +
				", itemPropertyThreeValue='" + itemPropertyThreeValue + '\'' +
				", itemPropertyFourName='" + itemPropertyFourName + '\'' +
				", itemPropertyFourValue='" + itemPropertyFourValue + '\'' +
				", itemPropertyFiveName='" + itemPropertyFiveName + '\'' +
				", itemPropertyFiveValue='" + itemPropertyFiveValue + '\'' +
				", itemPropertySixName='" + itemPropertySixName + '\'' +
				", itemPropertySixValue='" + itemPropertySixValue + '\'' +
				", itemBrandName='" + itemBrandName + '\'' +
				", itemSort='" + itemSort + '\'' +
				'}';
	}
}