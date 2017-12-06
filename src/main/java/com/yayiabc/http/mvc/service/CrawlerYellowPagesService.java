package com.yayiabc.http.mvc.service;

import java.util.HashMap;
import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Sheet1;

public interface CrawlerYellowPagesService {

	DataWrapper<List<Sheet1>> getYellowPage(Integer currentPage, Integer numberPerpage);

	DataWrapper<List<HashMap<Object,Object>>> getList(Integer currentPage, Integer numberPerpage);

}
