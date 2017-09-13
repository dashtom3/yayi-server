package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import org.apache.poi.ss.formula.functions.T;

import java.util.Map;

/**
 *
 * Created by Jo on 2017/8/2.
 */
public interface WxLoginService {
    DataWrapper<Object> login(String code);

    DataWrapper<Object> bindUser(String phone, String verifyCode, String type);

	DataWrapper<User> updateUserInfo(User user,Integer number,String openid);

	DataWrapper<Void> updateSaleInfo(SaleInfo saleInfo, Integer number,String openid);

	DataWrapper<Object> judgeOpenid(String openid, String state);
}
