package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;

public interface WitManageService {
	DataWrapper<Void>  showWit(String phone);

	DataWrapper<Void> submitWit(String moneyNnm, String phone, String vCode);
}
