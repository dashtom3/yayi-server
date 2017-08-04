package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;

/**
 *
 * Created by Jo on 2017/8/2.
 */
public interface WxLoginService {
    DataWrapper<Object> login(String code);

    DataWrapper<Object> bindUser(String phone, String verifyCode, String openid, String type);
}
