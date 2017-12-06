package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import com.yayiabc.http.mvc.pojo.jpa.DaForDentistYa;
import com.yayiabc.http.mvc.pojo.jpa.Sheet1;

public interface CrawlerYellowPagesDao {

	List<Sheet1> getYellowPage(HashMap<Object, Object> hm);


	int  queryCountTOSheet1(HashMap<Object, Object> hm);


	List<DaForDentistYa> getList(HashMap<Object, Object> hm);


	int queryCountTOX(HashMap<Object, Object> hm);

}
