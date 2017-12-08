package com.yayiabc.http.mvc.service;

import com.yayiabc.http.mvc.dao.UtilsDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 对钱币充值前的预处理阶段,将充值情况填充进充值记录
 */
public interface WxPrepareService {

    public Map<String,String> addToCharge(Integer money,String qbType,String token);


}
