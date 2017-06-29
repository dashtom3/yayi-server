package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface UserCertificationListService {
	DataWrapper<List<User>> list(String phone,String trueName,String companyName,Integer type,Integer state);
	
	DataWrapper<Void> verify(String phone,Integer state,String failReason);
}
