package com.yayiabc.http.mvc.controller.saleManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.With;
import com.yayiabc.http.mvc.service.PunRewardService;

//销售员的惩罚奖励
@Controller
@RequestMapping("api/PW")
public class PunRewardController {
    @Autowired
    private PunRewardService punRewardService;
    @ResponseBody
    @RequestMapping("show")
    DataWrapper<Double> show(
    		 @RequestParam(value="token",required=true) String token
    		 ){
       	
		return punRewardService.show(token);
    }
    //增加或减少余额
    @RequestMapping("addOrDelMoney")
    @ResponseBody
     DataWrapper<Void> addOrDelMoney(
    		 @RequestParam(value="saleId",required=true) String saleId,
    		 @RequestParam(value="sign",required=true) Integer sign,
    		 @RequestParam(value="money",required=true) String money
    		 ){
    	
    	return punRewardService.addOrDelMoney(saleId,sign,money);
     }
}
