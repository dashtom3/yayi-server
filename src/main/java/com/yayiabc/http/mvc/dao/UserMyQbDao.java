package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;

@Repository
public interface UserMyQbDao {
	int add(QbRecord qbRecord);
	
	//查询所有
	List<QbRecord> query(@Param("page")Page page,@Param("userId")String userId);
	int getCount(@Param("userId")String userId);
	
	//查询收入
	List<QbRecord> queryRget(@Param("page")Page page,@Param("userId")String userId);
	int getCountRget(@Param("userId")String userId);
	
	//查询支出
    List<QbRecord> queryRout(@Param("page")Page page,@Param("userId")String userId);
    int getCountRout(@Param("userId")String userId);
    
}
