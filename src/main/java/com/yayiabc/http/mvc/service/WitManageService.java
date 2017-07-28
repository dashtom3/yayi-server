package com.yayiabc.http.mvc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.With;

public interface WitManageService {

	
	DataWrapper<Void> oper(int balacceId);
	
    //查询+显示
	DataWrapper<List<With>> query(String message, String state,
			Integer currentPage,
			Integer numberPerpage
			);

	DataWrapper<Void> submitWit(String saleToken, String balanceOut);
     

}
