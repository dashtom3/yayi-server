package com.yayiabc.http.mvc.service;

import java.util.HashMap;
import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface FindCusService {

	DataWrapper<List<SaleInfo>> show(HashMap<String, String> hashMap);
//------------
	DataWrapper<List<User>> showMyCus(String saleToken);

}
