package com.yayiabc.http.mvc.service;

import com.yayiabc.common.enums.WXPayEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WXPayService {
//    public void getChargeReturnUrl(HttpServletRequest request, HttpServletResponse response, WXPayEnum wxPayEnum) throws Exception;
//    public void getReturnUrl(HttpServletRequest request, HttpServletResponse response, WXPayEnum wxPayEnum) throws Exception;
    public void callBack(HttpServletRequest request, HttpServletResponse response, WXPayEnum wxPayEnum) throws Exception;
}
