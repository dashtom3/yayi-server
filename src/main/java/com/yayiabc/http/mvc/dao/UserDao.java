package com.yayiabc.http.mvc.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.UserToken;

import java.util.Map;

@Repository
public interface UserDao {

	int register(User newUser);

	int getCartNum(User user);

	void updatePwd(@Param("phone")String phone,@Param("pwd")String pwd);
	
	String getUserId(String phone);

	String getTokenByUserId(String userId);

	void addToken(UserToken userToken);

	String getUserIdByToken(String token);

	User getUserByUserId(Integer userId);

	void updateToken(UserToken userToken);

	void deleteToken(String token);

    void addSale(String saleId);

	int bindSale(String userId, String saleId);

	Integer getSaleCount(@Param("phone")String phone);

	void updateUserInfo(User user);

	void updateCertification(User user);

	String getUserIdByPhone(@Param("phone")String phone);

	void registerUserInfo(User user);

	void registerUserCertification(User user);

	User getUserByPhone(@Param("phone")String phone);

	int getCountByUserId(@Param("userId") String userId);

    //彻底删除用户信息
    Integer deleteInGrainUser(@Param("userId")Integer userId);

    String getUserPhoneByToken(@Param("token")String token);


	Map<String,String> getTypeByOpenid(@Param("openid")String openid);


}
