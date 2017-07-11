package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
@Repository
public interface SaleListDao {
	List<SaleInfo> query(@Param("saleId")String saleId,@Param("phone")String phone,@Param("trueName")String trueName,@Param("isBindUser")Integer isBindUser,@Param("page")Page page);

	int getCount(@Param("saleId")String saleId,@Param("phone")String phone,@Param("trueName")String trueName,@Param("isBindUser")Integer isBindUser);
	
	List<User> userlist(@Param("saleId")String saleId,@Param("userPhone")String userPhone,@Param("trueName")String trueName,@Param("companyName")String companyName,@Param("isBind")Integer isBind,@Param("page")Page page); 
	
	int userlistCount(@Param("saleId")String saleId,@Param("userPhone")String userPhone,@Param("trueName")String trueName,@Param("companyName")String companyName,@Param("isBind")Integer isBind);

	SaleInfo detail(@Param("phone")String phone);
	
	List<User> bindUserList(@Param("saleId")String saleId,@Param("page")Page page);
	
	int bindUserCount(@Param("saleId")String saleId);
	
	String getSaleId(@Param("salePhone")String salePhone);
}
