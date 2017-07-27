package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.Adminstrator;
import com.yayiabc.http.mvc.pojo.model.AdminstratorToken;
@Repository
public interface AdminstratorDao {

	void addAdminstrator(Adminstrator adminstrator);

	void deleteAdminstrator(Integer adminstratorId);

	void updateAdminstrator(Adminstrator adminstrator);

	List<Adminstrator> queryAdminstrator(@Param("phone") String phone,@Param("trueName") String trueName);

	Adminstrator loginAdminstrator(@Param("phone") String phone,@Param("adminstratorPwd") String adminstratorPwd);

	Integer getAdminstratorIdByPhone(String phone);

	void addAdminstratorToken(AdminstratorToken adminstratorToken);

	void deleteAdminstratorToken(String token);

	String getAdminstratorTokenByAdminstratorId(Integer adminstratorId);

	void updateAdminstratorToken(AdminstratorToken adminstratorToken);
}
