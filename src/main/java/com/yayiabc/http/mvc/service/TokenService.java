package com.yayiabc.http.mvc.service;

/**
 * Created by 小月亮 on 2017/8/29.
 */
public interface TokenService {
    String getToken(String userId);
    String getSaleToken(String id);
    String getAdminToken(Integer adminId);
}
