package com.yayiabc.http.mvc.dao;

import com.yayiabc.api.Back.userStatisticsApi;
import com.yayiabc.http.mvc.pojo.jpa.Certification;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.UserPersonalInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserPersonalInfoDao {
	UserPersonalInfo detail(String userId);
	
	public String seeDoctorPic(String userId);
	
	int updateCertification(Certification certification);
	
	int updateUser(User user);

	void updateState(String userId);

	
	int add(String userId);
	
	List<String> queryBind(String userId);
	
	Map<String,String> querySale(String salePhone);
}
