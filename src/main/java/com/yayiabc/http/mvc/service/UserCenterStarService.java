package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.MyStar;



public interface UserCenterStarService {
	DataWrapper<List<MyStar>> shows(String token,Integer currentPage,Integer numberPerpage);
	DataWrapper<Void>  deleteStarOne(String token,String deleteOneId);
	DataWrapper<Void>  deleteStarAll(String token);
	DataWrapper<Void>  addMyStar(String token,String itemId);
}
