package com.yayiabc.http.mvc.service;

import com.yayiabc.common.enums.WXPayEnum;
import com.yayiabc.common.utils.DataWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WXPayService {
    public void callBack(HttpServletRequest request, HttpServletResponse response, WXPayEnum wxPayEnum) throws Exception;

    DataWrapper<Void> checkOrderState(String out_trade_no);

    DataWrapper<Void> checkChargeState(String token);
}
