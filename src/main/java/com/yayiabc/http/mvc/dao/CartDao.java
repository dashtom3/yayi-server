package com.yayiabc.http.mvc.dao;

import com.yayiabc.http.mvc.pojo.jpa.Cart;
import com.yayiabc.http.mvc.pojo.jpa.ItemStar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao {
	List<Cart> list(String userId);
	
	int delete(@Param("userId")String userId,@Param("itemSKU")String itemSKU);
	
	int star(ItemStar itemStar);
	
	int add(@Param("userId")String userId,@Param("num")Integer num,@Param("itemSKU")String itemSKU);
	
	int getCountItemSKU(@Param("userId")String userId,@Param("itemSKU")String itemSKU);
	
	void clear(@Param("userId")String userId);
	
	//每次数量+
	void updateOne(@Param("userId")String userId,@Param("itemSKU")String itemSKU,@Param("num")Integer num);
	
	//直接传值修改数量
	int updateNum(@Param("userId")String userId,@Param("num")Integer num,@Param("itemSKU")String itemSKU);
}
