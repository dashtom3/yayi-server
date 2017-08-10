package com.yayiabc.http.mvc.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.WXUserLink;
import com.yayiabc.http.mvc.pojo.model.SaleU;






/**
 * Created by Jo on 2017/8/2.
 */
@Repository
public interface WxAppDao {
    Map<String,String> getAppCode();

    Map<String,String> getUser(String openid);



    void addUser(@Param("saleId")String saleId,@Param("openid") String openid);

	void addSaleUser(@Param("saleId")String saleId,@Param("openid") String openid);


    WXUserLink queryIsBD(@Param("openId")String openId);

	int queryObject(@Param("uid")String uid);

	int queryObjects(@Param("uid")String uid);

	String queryIsSale(@Param("uid")String uid);
	SaleInfo querySale(@Param("uid")String uid);

}
