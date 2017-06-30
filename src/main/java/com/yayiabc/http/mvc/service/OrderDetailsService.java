package com.yayiabc.http.mvc.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface OrderDetailsService {
	DataWrapper<List<User>>  orderDetailsShow(HashMap<String,String> map,String newPhone);
}
