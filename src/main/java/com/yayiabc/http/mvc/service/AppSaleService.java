package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;

/**
 * Created by 小月亮 on 2017/9/4.
 */
public interface AppSaleService {
    DataWrapper<Void> register(SaleInfo saleInfo,String code);
}
