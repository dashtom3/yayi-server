package com.yayiabc.http.mvc.dao;

import org.apache.ibatis.annotations.Param;

public interface UtilsDao {

	String getUserID(@Param("token")String token);
}
