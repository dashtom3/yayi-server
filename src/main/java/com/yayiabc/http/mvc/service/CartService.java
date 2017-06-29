package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Cart;
import com.yayiabc.http.mvc.pojo.jpa.ItemStar;

public interface CartService {
	DataWrapper<List<Cart>> list(String phone);
	
	DataWrapper<Void> delete(String phone,String itemSKU);
	
	DataWrapper<ItemStar> star(String phone,String itemId,String itemSKU);
	
	DataWrapper<Cart> add(Cart cart,String phone);
	
	DataWrapper<Void> updateNum(String phone,Integer num,String itemSKU);
}
