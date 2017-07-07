package com.yayiabc.http.mvc.dao;

import java.util.HashMap;
import java.util.List;

import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface FindCusDao {
	List<SaleInfo> show(HashMap<String, String> hashMap);

	List<User> showMyCus(String saleId);
}
