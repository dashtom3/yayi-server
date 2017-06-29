package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Certification;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.UserPersonalInfo;

public interface UserPersonalInfoService {
	DataWrapper<UserPersonalInfo> detail(String phone);
	
	DataWrapper<User> updateUser(User user);
	
	DataWrapper<Certification> updateCertification(Certification certification,String phone);
}
