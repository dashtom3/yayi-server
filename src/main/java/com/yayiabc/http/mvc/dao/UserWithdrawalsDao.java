package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.jpa.UserWitSetUp;
import com.yayiabc.http.mvc.pojo.jpa.UserWith;

public interface UserWithdrawalsDao {
	

	Integer  updateUserQb(UserWith userWith);
	
	
	Integer submit(UserWith userWith);


	Integer queryCount(HashMap<String, Object> hm);


	List<User> show(HashMap<String, Object> hm);


	UserWith queryFourQb(String withId);


	Integer determine(@Param("withId")String withId,@Param("sign") int sign);


	Integer  queryIsSetUp(String userId);


	Integer updateWitType(@Param("userId")String userId, @Param("accountHolder")String accountHolder, @Param("cardNumber")String cardNumber,
			
			@Param("witType")String witType, @Param("oBank")String oBank);

     //#{userId},#{witType},#{accountHolder},#{cardNumber},#{oBank},NOW()
	Integer insertWitType(@Param("userId")String userId,  @Param("witType")String witType, @Param("accountHolder")String accountHolder,
			@Param("cardNumber")String cardNumber, @Param("oBank")String oBank
			);

    
	
	 UserWitSetUp witSetUpShow(String userId);


	Integer queryWitSign(String userId);


	User showUserQbNum(String userId);
}
