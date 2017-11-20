package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;

public interface FindService {
    DataWrapper<Object> findList(String keyWord, Integer type, Integer classify,Integer currentPage,Integer numberPerPage);
}
