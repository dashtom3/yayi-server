package com.yayiabc.http.mvc.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Jo on 2017/8/2.
 */
@Repository
public interface WxAppDao {
    Map<String,String> getAppCode();

    Map<String,String> getUser(String openid);

    int addUser(String saleId, String openid);
}
