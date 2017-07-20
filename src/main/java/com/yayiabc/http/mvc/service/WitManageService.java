package com.yayiabc.http.mvc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.With;

public interface WitManageService {

	
	DataWrapper<Void> oper(int balacceId);
	
    //查询+显示
	DataWrapper<List<With>> query(String message, String state);

	DataWrapper<Void> submitWit(String saleToken, String balanceOut);
     

}
