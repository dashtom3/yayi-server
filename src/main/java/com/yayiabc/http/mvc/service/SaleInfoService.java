package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;

public interface SaleInfoService {
	DataWrapper<Void> updateSale(SaleInfo saleInfo,String token);
	
	DataWrapper<Void> updatePostal(String postalType,String bankName,
			String openName,String accountNumber,String token);
	
	DataWrapper<SaleInfo> query(String token);
}
