package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.jpa.UserWitSetUp;
import com.yayiabc.http.mvc.pojo.jpa.UserWith;

public interface UserWithdrawalsDao {
	

	int  updateUserQb(UserWith userWith);
	
	
	int submit(UserWith userWith);


	int queryCount(HashMap<String, Object> hm);


	List<UserWith> show(HashMap<String, Object> hm);


	UserWith queryFourQb(String withId);


	int determine(String withId);


	int  queryIsSetUp(String userId);


	int updateWitType(@Param("userId")String userId, @Param("accountHolder")String accountHolder, @Param("cardNumber")String cardNumber,
			
			@Param("witType")String witType, @Param("oBank")String oBank);


	int insertWitType(@Param("userId")String userId,  @Param("accountHolder")String accountHolder, @Param("cardNumber")String cardNumber,
			@Param("witType")String witType, @Param("oBank")String oBank
			);

    
	
	 UserWitSetUp witSetUpShow(String userId);


	int queryWitSign(String userId);


	User showUserQbNum(String userId);
}
