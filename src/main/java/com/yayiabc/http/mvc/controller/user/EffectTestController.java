package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.RedisClient;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;

import redis.clients.jedis.Jedis;


/**
 * 上线3.0完成redis数据的统一接口，值调用一次
 * @return
 */

@Controller
@RequestMapping("once/eff")
public class EffectTestController {
   
	@Autowired
	private UtilsDao utilsDao;
	
	
	@RequestMapping("onc")
	@ResponseBody
	public DataWrapper<Void> eff(){
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		RedisClient rcQ=RedisClient.getInstance();
		Jedis jedisQ=rcQ.getJedis();
		jedisQ.select(11);
		List<Ordera> orderList=utilsDao.onceEff();
		for(Ordera order:orderList){
			jedisQ.set(order.getUserId(), "");
		}
		dataWrapper.setMsg("导入到正式服redis完成.....");
		jedisQ.close();
		return dataWrapper;
    }
}
