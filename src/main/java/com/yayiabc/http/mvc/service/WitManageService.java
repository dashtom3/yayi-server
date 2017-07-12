package com.yayiabc.http.mvc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.With;

public interface WitManageService {
	DataWrapper<With>  showWit(String token);

	DataWrapper<Void> submitWit(With with);
	//操作
	
	DataWrapper<Void> oper(@Param("cashId")int cashId);
	
    //查询+显示
	DataWrapper<List<With>> query(String message, String state);
     

}
