package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.User;

@Repository
public interface UserCertificationListDao {
	List<User> list(@Param("phone")String phone,@Param("trueName")String trueName,@Param("companyName")String companyName,@Param("type")Integer type,@Param("state")Integer state,@Param("page")Page page);
	
	int verify(@Param("userId")String userId,@Param("state")Integer state,@Param("failReason")String failReason);
	
	int getCount(@Param("phone")String phone,@Param("trueName")String trueName,@Param("companyName")String companyName,@Param("type")Integer type,@Param("state")Integer state);
}
