package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Adminstrator;

public interface AdminstratorService {

	DataWrapper<Void> addAdminstrator(String phone, String adminstratorPwd,
			String trueName);

	DataWrapper<Void> deleteAdminstrator(Integer adminstratorId);

	DataWrapper<Void> updateAdminstrator(Integer adminstratorId, String phone,
			String adminstratorPwd, String trueName);

	DataWrapper<List<Adminstrator>> queryAdminstrator(String phone,
			String trueName,Integer currentPage,Integer numberPerPage);

	DataWrapper<Void> loginAdminstrator(String phone, String adminstratorPwd,String code);

}
