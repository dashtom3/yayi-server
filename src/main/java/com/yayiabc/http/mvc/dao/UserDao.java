package com.yayiabc.http.mvc.dao;

import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.UserToken;








@Repository
public interface UserDao {

	User getUserByUser(User user);

	int register(User newUser);

	int getCartNum(User user);

	void updatePwd(User neUser);
	
	String getUserId(String phone);

	String getTokenByUserId(String userId);

	void addToken(UserToken userToken);
}
