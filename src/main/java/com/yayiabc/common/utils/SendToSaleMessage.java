package com.yayiabc.common.utils;

import com.yayiabc.http.mvc.controller.unionpay.sdk.LogUtil;
import com.yayiabc.http.mvc.dao.SaleIncomeListDao;
import com.yayiabc.http.mvc.dao.UserPersonalInfoDao;
import com.yayiabc.http.mvc.pojo.model.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 向销售员发送信息
 */
@Component
public class SendToSaleMessage {

    @Autowired
    private UserPersonalInfoDao userPersonalInfoDao;
    @Autowired
    private SaleIncomeListDao saleIncomeListDao;

    public void send(String userId,String orderId){
        List<String> list=userPersonalInfoDao.queryBind(userId);
        if(!list.isEmpty()){
            OrderVo orderVo=saleIncomeListDao.userOrderDetail(orderId);
            if(orderVo!=null){
                boolean result=HttpUtil.sendToSaleNotice(list.get(2),orderVo.getUserPhone(),String.valueOf(orderVo.getActualMoney()),String.valueOf(orderVo.getActualMoneyHaocai()),String.valueOf(orderVo.getActualMoneyGongju()));
                if(result){
                    LogUtil.writeLog("向销售员发送信息成功!");
                }
            }
        }
            LogUtil.writeLog("向销售员发送信息失败!");
    }
}
