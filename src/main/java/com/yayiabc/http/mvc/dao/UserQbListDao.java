package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.yayiabc.common.utils.Page;

import com.yayiabc.http.mvc.pojo.jpa.QbRecord;

@Repository
public interface UserQbListDao {
	List<QbRecord> list(@Param("phone")String phone,@Param("startDate")String startDate,@Param("endDate")String endDate,@Param("page")Page page);
	
	int update(@Param("qbBalance")Integer qbBalance,@Param("phone")String phone);
	
	int getCount(@Param("phone")String phone,@Param("startDate")String startDate,@Param("endDate")String endDate);
}
