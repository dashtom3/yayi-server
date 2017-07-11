package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface SaleListService {
	DataWrapper<List<SaleInfo>> query(String saleId,String phone,String trueName,Integer isBindUser,Integer currentPage, Integer numberPerPage);

	DataWrapper<List<User>> userlist(String salePhone,String userPhone,String trueName,String companyName,Integer isBind,Integer currentPage, Integer numberPerPage);

	DataWrapper<SaleInfo> detail(String phone,Integer currentPage,
			Integer numberPerPage);
}
