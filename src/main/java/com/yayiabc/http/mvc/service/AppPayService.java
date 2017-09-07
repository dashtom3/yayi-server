package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;

public interface AppPayService {

	DataWrapper<Object> packingParameter(String orderId, String string, String string2, String string3, String string4,
			String string5);

}
