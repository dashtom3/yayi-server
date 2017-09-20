package com.yayiabc.http.mvc.dao;

import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.WXUserLink;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;






/**
 * Created by Jo on 2017/8/2.
 */
@Repository
public interface WxAppDao {
    Map<String,String> getAppCode();

    Map<String,String> getUser(String openid);



    void addUser(@Param("saleId")Integer saleId,@Param("openid") String openid,@Param("phone")String phone);

	void addSaleUser(@Param("saleId")String saleId,@Param("openid") String openid,@Param("phone")String phone);


    WXUserLink queryIsBD(@Param("openId")String openId);

	int queryObject(@Param("uid")String uid);

	int queryObjects(@Param("uid")String uid);

	String queryIsSale(@Param("uid")String uid);
	SaleInfo querySale(@Param("uid")String uid);

	Integer getPhoneCount(@Param("phone")String phone);
}
