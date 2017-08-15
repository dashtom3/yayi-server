package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.CusResources;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface FindCusDao {
	List<User> show(HashMap<String, String> hashMap);

	List<User> showMyCus(String saleId);

	List<CusResources> shows(@Param("state")String state, @Param("currentNum")Integer currentPage, @Param("numberPerpage")Integer numberPerPage);

	
	//查询总条数
	Integer queryCount(@Param("state")String state);
    
	
	//注册 未绑定currentNum,numberPerpage
	List<User> showsss(@Param("state")String state, @Param("currentNum")Integer currentPage, @Param("numberPerpage")Integer numberPerPage);

	Integer queryCounts(@Param("state")String state);
}
