package com.yayiabc.http.mvc.controller.train;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.yayiabc.http.mvc.dao.TrainShowServiceDao;
import com.yayiabc.http.mvc.pojo.jpa.Train;
import com.yayiabc.http.mvc.pojo.jpa.TrainDetail;
import com.yayiabc.http.mvc.service.RedisService;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

public class FilledCacheContainer/* implements InitializingBean*/{
    /**
     * 项目启动 加载培训信息
     */
	@Autowired
	private RedisService rediService;
	
	@Autowired 
	private TrainShowServiceDao trainShowServiceDao;
	
	/*@Override*/
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		Jedis jedis=rediService.getJedis();
		//获取培训列表加入到reids中
		List<Train> trainList=trainShowServiceDao.show(null,null, null);
		Map<String,String> hm=new HashMap<String,String>();
		for(Train train:trainList){
			JSONArray jsonArray =JSONArray.fromObject(train);
			hm.put(train.getTrainId()+"", jsonArray.toString());
		}
		jedis.hmset("allTrain",hm);
		
		List<TrainDetail> trainDetailList=trainShowServiceDao.getAllTrainDetails();
		for(TrainDetail trainDetail:trainDetailList){
			JSONArray jsonArray =JSONArray.fromObject(trainDetail);
			hm.put(trainDetail.getTrainId()+"", jsonArray.toString());
		}
		jedis.hmset("allTrainDetail",hm);
		jedis.close();
		System.err.println("培训信息加载完毕");
	}
}
