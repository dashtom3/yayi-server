package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;

/**
 *
 * Created by Jo on 2017/8/2.
 */
public interface WxLoginService {
    DataWrapper<Object> login(String code);

    DataWrapper<Object> bandUser(String phone, String password, String openid, String type);
}
