package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CusResources;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface FindCusService {
   //已经注册  待绑定 
	DataWrapper<List<User>> show(String state,Integer currentPage,Integer numberPerPage);
//------------
	DataWrapper<List<User>> showMyCus(String saleToken);
	
//查询出来 未注册
	DataWrapper<List<CusResources>> shows(String state,Integer currentPage,Integer numberPerPage);

}
