package com.yayiabc.http.mvc.dao;

import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;

@Repository
public interface SaleLogDao {
	int register(SaleInfo saleInfo);
	
	
}
