package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.UnionpayService;
import com.yayiabc.http.mvc.service.UserMyQbService;
@Service
public class UnionpayServiceImpl implements UnionpayService{
	@Autowired
	AliPayDao aliPayDao;
	@Autowired
	UtilsDao utilsDao;
	@Autowired
	UserMyQbService userMyQbService;
	
	public String UnionPayJudge(String orderId, String respCode) {
		//判断是否支付成功
		if("00".equals(respCode) || "A6".equals(respCode)){
			int sign = aliPayDao.querySatetIsTwo(orderId);
			//判断该订单状态是否已完成
			if(2!=sign){						
				PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
				payAfterOrderUtil.universal(orderId,"2");
				//判断该订单是否为乾币充值订单
				System.out.println(orderId.substring(0,4));
				if("YLQB".equals(orderId.substring(0,4))){
					System.out.println("乾币");
					Charge charge = aliPayDao.queryUserId(orderId);
					if(charge.getState() != 2){
						aliPayDao.updateState(orderId);
						String token = utilsDao.getToken(charge.getToken());
						QbRecord q = new QbRecord();
						q.setQbRget(Integer.parseInt(charge.getMoney()));
						q.setQbType(charge.getQbType());
						q.setRemark(charge.getQbType()+"乾币充值（银联支付）");
						userMyQbService.add(q, token);
						return "successQB";
					}
				}
			}
			return "success";
		}else{
			return "fail";
		}
	}
}
