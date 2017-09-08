package com.yayiabc.http.mvc.dao;

import java.util.List;

import com.yayiabc.http.mvc.pojo.jpa.Certification;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.User;

@Repository
public interface UserCertificationListDao {
	List<User> list(@Param("phone")String phone,@Param("trueName")String trueName,@Param("companyName")String companyName,@Param("type")Integer type,@Param("state")Integer state,@Param("page")Page page);

	int getCount(@Param("phone")String phone,@Param("trueName")String trueName,@Param("companyName")String companyName,@Param("type")Integer type,@Param("state")Integer state);

	void verify(@Param("userId")String userId,@Param("state")Integer state,@Param("failReason")String failReason);

	void verifyFail(@Param("userId")String userId);	//审核未通过时清空资质信息

	Certification detail(@Param("userId") String userId);
}
