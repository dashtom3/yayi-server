package com.yayiabc.http.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.model.UserAllInfo;

@Repository
public interface UserManageListDao {
	List<Map<String,String>> userlist(@Param("phone")String phone,@Param("trueName")String trueName,@Param("companyName")String companyName,@Param("isBindSale")Integer isBindSale,@Param("type")Integer type,@Param("saleName")String saleName,@Param("page")Page page);
	
	List<SaleInfo> salelist(@Param("salePhone")String salePhone,@Param("saleName")String saleName,@Param("page")Page page);
	
	int getSalelistCount(@Param("salePhone")String salePhone,@Param("saleName")String saleName);
																	
	int bind(@Param("salePhone")String salePhone,@Param("userPhone")String userPhone);	//绑定
	
	int disBind(@Param("salePhone")String salePhone,@Param("userPhone")String userPhone);	//取消绑定
	
	void bindUpdateNum(@Param("saleId")String saleId);	//绑定或取消绑定后更新绑定数量
	
	UserAllInfo detail(@Param("userId")String userId);
	
	int getCount(@Param("phone")String phone,@Param("trueName")String trueName,@Param("companyName")String companyName,@Param("isBindSale")Integer isBindSale,@Param("type")Integer type,@Param("saleName")String saleName);
}
