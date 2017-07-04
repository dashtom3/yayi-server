package com.yayiabc.http.mvc.dao;

import org.apache.ibatis.annotations.Param;

public interface UtilsDao {
   
	//根据token得到 userID  1
	String getUserID(@Param("token")String token);
}
