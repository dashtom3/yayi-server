package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.DaForDentist;
import com.yayiabc.http.mvc.pojo.jpa.Sheet1;

public interface CrawlerYellowPagesDao {

	List<DaForDentist> getMaterList(HashMap<String, String> hm);


	int  queryCountTOSheet1(HashMap<String, String> hm);

	
	List<Sheet1> getList(HashMap<String, Object> hm);


	int queryCountTOX(HashMap<String, Object> hm);


	DaForDentist getMaterDetail(@Param("id")String id);

}
