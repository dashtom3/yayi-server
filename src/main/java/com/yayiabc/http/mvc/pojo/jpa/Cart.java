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

	private Integer price;

	private String itemSKU;

	private String itemPropertyNamea;

	private String itemPropertyNameb;

	private String itemPropertyNamec;

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
		this.name = name == null ? null : name.trim();
	}

	public String getItemSKU() {
		return itemSKU;
	}

	public void setItemSKU(String itemSKU) {
		this.itemSKU = itemSKU;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
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

	public Cart() {
		super();
	}

	public Cart(Integer cartId, String userId, String itemId, String name,
			String pic, Integer num, Integer price, String itemSKU,
			String itemPropertyNamea, String itemPropertyNameb,
			String itemPropertyNamec) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.itemId = itemId;
		this.name = name;
		this.pic = pic;
		this.num = num;
		this.price = price;
		this.itemSKU = itemSKU;
		this.itemPropertyNamea = itemPropertyNamea;
		this.itemPropertyNameb = itemPropertyNameb;
		this.itemPropertyNamec = itemPropertyNamec;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", itemId="
				+ itemId + ", name=" + name + ", pic=" + pic + ", num=" + num
				+ ", price=" + price + ", itemSKU=" + itemSKU
				+ ", itemPropertyNamea=" + itemPropertyNamea
				+ ", itemPropertyNameb=" + itemPropertyNameb
				+ ", itemPropertyNamec=" + itemPropertyNamec + "]";
	}

}