package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;

public interface FindCusService {

	DataWrapper<List<SaleInfo>> show();

}
