package com.yayiabc.http.mvc.service;

import java.util.Map;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Certification;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.UserPersonalInfo;

public interface UserPersonalInfoService {
	DataWrapper<UserPersonalInfo> detail(String token);
	
	DataWrapper<User> updateUser(User user,String token);
	
	DataWrapper<Certification> updateCertification(Certification certification,String token);
	
	DataWrapper<Map<String, String>> queryBind(Integer state,String salePhone,String token);
}
