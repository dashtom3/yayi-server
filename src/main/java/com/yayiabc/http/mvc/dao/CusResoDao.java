package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.CusResources;

public interface CusResoDao {
	
	public List<CusResources> show(@Param("state")String state);
	
	public int insert(@Param("cus")CusResources cus);
	
	public int update(CusResources cus);
	
	public int delete(@Param("cusId")int id);
}
