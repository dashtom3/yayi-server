package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.User;

@Repository
public interface UserQbListDao {
	List<User> list(@Param("phone")String phone,@Param("startDate")String startDate,@Param("endDate")String endDate);
	
	int update(@Param("qbBalance")Integer qbBalance,@Param("phone")String phone);
}
