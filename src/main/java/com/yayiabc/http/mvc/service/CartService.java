package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Cart;
import com.yayiabc.http.mvc.pojo.jpa.ItemStar;

public interface CartService {
	DataWrapper<List<Cart>> list(String token);
	
	DataWrapper<Void> delete(String itemSKU,String token);
	
	DataWrapper<ItemStar> star(String itemId,String itemSKU,String token);
	
	DataWrapper<Void> add(Integer num,String itemSKU,String token);
	
	DataWrapper<Void> updateNum(Integer num,String itemSKU,String token);
}
