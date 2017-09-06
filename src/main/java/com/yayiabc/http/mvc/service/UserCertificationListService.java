package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Certification;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface UserCertificationListService {
	DataWrapper<List<User>> list(String phone,String trueName,String companyName,Integer type,Integer state,Integer currentPage,Integer numberPerPage);

	DataWrapper<Certification> detail(String userId);

	DataWrapper<Void> verify(String phone,Integer state,String failReason);
}
