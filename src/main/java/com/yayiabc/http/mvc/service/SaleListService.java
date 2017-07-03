package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;

public interface SaleListService {
	DataWrapper<List<SaleInfo>> query(String saleId,String phone,String trueName,Integer isBindUser,Integer currentPage, Integer numberPerPage);
}
