package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.CusResources;

public interface CusResoDao {
	
	public List<CusResources> show(HashMap<String, String> hashMap);
	
	public int insert(@Param("cus")CusResources cus);
	
	public int update(CusResources cus);
	
	public int delete(@Param("cusId")int id);

	public int queryCount(HashMap<String, String> hashMap);
}
