package com.yayiabc.http.mvc.service;

import java.util.HashMap;
import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.DaForDentist;
import com.yayiabc.http.mvc.pojo.jpa.Sheet1;

public interface CrawlerYellowPagesService {

	DataWrapper<List<DaForDentist>> getMaterList(Integer currentPage, Integer numberPerpage, String keyWord);
	
	DataWrapper<List<Sheet1> > getList(Integer currentPage, Integer numberPerpage, double lng, double lat, String cityName, String keyWord);

	DataWrapper<DaForDentist> getMaterDetail(String id);

}
