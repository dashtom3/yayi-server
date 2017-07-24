package com.yayiabc.http.mvc.pojo.model;

public class OrderInfoVo {
	private String itemName;
	private String itemPropertyNamea;
	private String itemPropertyNameb;
	private String itemPropertyNamec;
	private double price;
	private Integer num;
	private double total;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public OrderInfoVo() {
		super();
	}

	public OrderInfoVo(String itemName, String itemPropertyNamea,
			String itemPropertyNameb, String itemPropertyNamec, double price,
			Integer num, double total) {
		super();
		this.itemName = itemName;
		this.itemPropertyNamea = itemPropertyNamea;
		this.itemPropertyNameb = itemPropertyNameb;
		this.itemPropertyNamec = itemPropertyNamec;
		this.price = price;
		this.num = num;
		this.total = total;
	}

	@Override
	public String toString() {
		return "OrderInfoVo [itemName=" + itemName + ", itemPropertyNamea="
				+ itemPropertyNamea + ", itemPropertyNameb="
				+ itemPropertyNameb + ", itemPropertyNamec="
				+ itemPropertyNamec + ", price=" + price + ", num=" + num
				+ ", total=" + total + "]";
	}

}
