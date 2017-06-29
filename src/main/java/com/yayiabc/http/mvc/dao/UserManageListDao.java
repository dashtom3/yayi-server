package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.model.UserAllInfo;

@Repository
public interface UserManageListDao {
	List<UserAllInfo> userlist(@Param("phone")String phone,@Param("trueName")String trueName,@Param("companyName")String companyName,@Param("isBindSale")Integer isBindSale,@Param("type")Integer type,@Param("saleName")String saleName);
	
	List<SaleInfo> salelist(@Param("salePhone")String salePhone,@Param("saleName")String saleName);
	
	int bind(@Param("salePhone")String salePhone,@Param("userPhone")String userPhone);
	
	UserAllInfo detail(@Param("userId")String userId);
}
