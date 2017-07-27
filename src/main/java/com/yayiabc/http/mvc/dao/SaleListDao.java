package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
@Repository
public interface SaleListDao {
	//销售员列表
	List<SaleInfo> query(@Param("saleId")String saleId,@Param("phone")String phone,@Param("trueName")String trueName,@Param("isBindUser")Integer isBindUser,@Param("page")Page page);

	int getCount(@Param("saleId")String saleId,@Param("phone")String phone,@Param("trueName")String trueName,@Param("isBindUser")Integer isBindUser);
	
	//获取简略用户信息列表
	List<User> userlist(@Param("saleId")String saleId,@Param("userPhone")String userPhone,@Param("trueName")String trueName,@Param("companyName")String companyName,@Param("isBind")Integer isBind,@Param("page")Page page); 
	
	int userlistCount(@Param("saleId")String saleId,@Param("userPhone")String userPhone,@Param("trueName")String trueName,@Param("companyName")String companyName,@Param("isBind")Integer isBind);

	//获取销售员详情
	SaleInfo detail(@Param("phone")String phone);
	
	//已绑定用户列表
	List<User> bindUserList(@Param("saleId")String saleId,@Param("page")Page page);
	
	int bindUserCount(@Param("saleId")String saleId);
	
	String getSaleId(@Param("salePhone")String salePhone);
	
	//查询销售员钱包余额
	String queryByBalance(@Param("phone")String phone);
	
	//查询销售员累计收入
	String getTotalGetMoney(@Param("saleId")String saleId);
}
