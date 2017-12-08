package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.utils.QbExchangeUtil;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.dao.WXPayDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.service.WxPrepareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
public class WxPrepareServiceImpl implements WxPrepareService {

    @Autowired
    private UtilsDao utilsDao;

    @Autowired
    private WXPayDao wXPayDao;

    @Override
    public Map<String, String> addToCharge(Integer money, String qbType, String token) {
        Map<String,String> map=new HashMap<String,String>();
        String chargeId= UUID.randomUUID().toString();
        String[] str=chargeId.split("-");
        chargeId="";
        for (String string : str) {
            chargeId+=string;
        }
        double totalMoney= QbExchangeUtil.getQbByMoney(money,qbType);
        Charge charge=new Charge();
        charge.setChargeId(chargeId);
        charge.setQbNum(money);
        String totalFee=(int)(totalMoney*100)+"";
        charge.setMoney(totalFee);
        charge.setState(1);
        charge.setToken(utilsDao.getUserID(token));
        charge.setQbType(qbType);
        wXPayDao.deleteChargeByToken(utilsDao.getUserID(token));
        wXPayDao.addCharge(charge);
        map.put("chargeId",chargeId);
        map.put("totalFee",totalFee);
        return map;
    }
}
