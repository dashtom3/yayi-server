package com.yayiabc.http.mvc.dao;

import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.Certification;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.UserPersonalInfo;

@Repository
public interface UserPersonalInfoDao {
	UserPersonalInfo detail(String userId);
	
	int updateCertification(Certification certification);  
	
	int updateUser(User user);
	
	String getUserIdOnC(String userId);
	
	int add(String userId);
}
