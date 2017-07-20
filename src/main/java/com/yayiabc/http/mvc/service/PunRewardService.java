package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.With;

public interface PunRewardService {
	DataWrapper<Double> show(String saleId);
    //  add  del
	DataWrapper<Void> addOrDelMoney(String saleId, Integer sign,String money);
}
