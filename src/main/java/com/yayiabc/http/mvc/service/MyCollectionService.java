package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;

public interface MyCollectionService {
    DataWrapper<Object> queryList(String type,Integer currentPage,Integer numberPerPage,String userId,Integer category);
}
