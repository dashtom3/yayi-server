package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.Adminstrator;
@Repository
public interface AdminstratorDao {

	void addAdminstrator(Adminstrator adminstrator);

	void deleteAdminstrator(Integer adminstratorId);

	void updateAdminstrator(Adminstrator adminstrator);

	List<Adminstrator> queryAdminstrator(@Param("phone") String phone,@Param("trueName") String trueName);

	Adminstrator loginAdminstrator(@Param("phone") String phone,@Param("adminstratorPwd") String adminstratorPwd);

}
