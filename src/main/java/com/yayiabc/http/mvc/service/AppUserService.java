package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Certification;
import com.yayiabc.http.mvc.pojo.jpa.User;

/**
 * Created by 小月亮 on 2017/8/29.
 */
public interface AppUserService {
    DataWrapper<User> regiseter(User user,String code);
}
