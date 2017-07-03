package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.User;

public interface OrderDetailsDao {
	List<User> orderDetailsShow(
			HashMap<String,String> map
			);

	int queryCount(HashMap<String, String> map);
}
