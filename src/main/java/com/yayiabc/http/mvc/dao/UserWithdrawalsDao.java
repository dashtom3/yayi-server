package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.UserWith;

public interface UserWithdrawalsDao {
	

	int  updateUserQb(UserWith userWith);
	
	
	int submit(UserWith userWith);


	int queryCount(HashMap<String, Object> hm);


	List<UserWith> show(HashMap<String, Object> hm);


	UserWith queryFourQb(String withId);


	int determine(String withId);
}
