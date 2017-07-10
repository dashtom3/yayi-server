package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.Receiver;
@Repository
public interface ShippingAddressDao {
    //新增收货地址	                   
	int addUserAddress(@Param("receiver")Receiver receiver);
	//修改收货地址
	int updateUserAddress(HashMap<String, Receiver> receiver);
    
	//验证收货地址id的唯一性
	Integer addConditions(@Param(value="userId") String userId);
	int updateIsdefault(@Param("receiverId")Integer receiverId);
	
	List<Receiver> showShoppingAddress(String userId); 
	
	int deleteShoppingAddress(@Param("receiverId")String receiverId);
	
	//
	Receiver queryShoppingAddress(@Param("receiverId")String receiverId);
}
