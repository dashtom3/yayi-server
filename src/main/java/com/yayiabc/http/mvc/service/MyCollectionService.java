package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;

public interface MyCollectionService {
    DataWrapper<Object> queryList(String token, Integer type,Integer currentPage,Integer numberPerPage);
}
