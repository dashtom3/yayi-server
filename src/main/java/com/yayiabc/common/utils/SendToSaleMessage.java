package com.yayiabc.common.utils;

import com.yayiabc.http.mvc.controller.unionpay.sdk.LogUtil;
import com.yayiabc.http.mvc.dao.SaleIncomeListDao;
import com.yayiabc.http.mvc.dao.UserPersonalInfoDao;
import com.yayiabc.http.mvc.pojo.model.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
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

    public boolean send(String userId,String orderId){
        DecimalFormat df = new DecimalFormat("######0.00");
        List<String> list=userPersonalInfoDao.queryBind(userId);
        if(!list.isEmpty()){
            OrderVo orderVo=saleIncomeListDao.userOrderDetail(orderId);
            System.out.println("@@@"+df.format(orderVo.getActualMoney())+df.format(orderVo.getActualMoneyHaocai())+df.format(orderVo.getActualMoneyGongju()));
            if(orderVo!=null){
                boolean result=HttpUtil.sendToSaleNotice(list.get(2),orderVo.getUserPhone(),df.format(orderVo.getActualMoney()),df.format(orderVo.getActualMoneyHaocai()),df.format(orderVo.getActualMoneyGongju()));
                if(result){
                    LogUtil.writeLog("向销售员发送信息成功!");
                    return true;
                }
            }
        }
        LogUtil.writeLog("向销售员发送信息失败!");
        return false;
    }
}
