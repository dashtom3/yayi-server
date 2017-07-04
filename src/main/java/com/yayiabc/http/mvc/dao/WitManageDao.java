package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.With;

public interface WitManageDao {
	
	With showWit(String phone);

	
	//操作提现数据
	int  submitWit(With with);
	//操作
	int oper(@Param("cashId")int cashId);

     //查询+显示
	List<With> query(@Param("message")String message, @Param("state")Integer state);
}
