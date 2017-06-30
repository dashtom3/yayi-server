package com.yayiabc.http.mvc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.MyStar;



public interface UserCenterStarService {
	DataWrapper<List<MyStar>> shows(String phone);
	DataWrapper<Void>  deleteStarOne(int deleteOneId,String phone);
	DataWrapper<Void>  deleteStarAll(String deleteAllId);
	DataWrapper<Void>  addMyStar(String phone, String itemId);
}
