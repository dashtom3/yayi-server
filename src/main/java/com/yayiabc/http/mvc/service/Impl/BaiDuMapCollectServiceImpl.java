package com.yayiabc.http.mvc.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.RedisClient;
import com.yayiabc.http.mvc.dao.BaiDuMapCollectDao;
import com.yayiabc.http.mvc.pojo.jpa.Sheet1;
import com.yayiabc.http.mvc.pojo.model.Pojo;
import com.yayiabc.http.mvc.service.BaiDuMapCollectService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
@Service
public class BaiDuMapCollectServiceImpl implements BaiDuMapCollectService {

	@Autowired
	private BaiDuMapCollectDao baiDuMapCollectDao;
	@Override
	public DataWrapper<Void> collect1() {
		// TODO Auto-generated method stub
		
		System.out.println(1234);
		 Jedis jedis=RedisClient.getInstance().getJedis();
		jedis.select(6);
		String str=jedis.get("crawler_list");
		//jedis.del("crawler_list");
		System.out.println(jedis.ping());
		JSONArray json = JSONArray.fromObject(str);
		List<ArrayList<Pojo>> orderItemList = (ArrayList<ArrayList<Pojo>>)JSONArray.toCollection(json,Pojo.class);	 
		//System.out.println(orderItemList);
		int count=0;
		for(int i=0;i<orderItemList.size();i++){
			ArrayList<Pojo> pojs=orderItemList.get(i);
			for(int x=0;x<pojs.size();x++){
				mzDetails(pojs.get(x).getUid(),pojs.get(x).getCityName());
				//System.out.println(pojs.get(x).getUid());
				count++;
				System.out.println(i);
			}
		}
		System.err.println("全国门诊个数:"+count+"。");
		
		return new DataWrapper<Void>();
	}
	//调用查看门诊详情百度接口  参数uid 
		private  void mzDetails(String uid,String cityName){
			 Document doc = null; 
			 try {
				doc = Jsoup.connect(
						 "http://api.map.baidu.com/place/v2/detail?uid="+uid+"&output=json&scope=2&ak=8lCwcMl9Xu52zceGpn41j4S14Kuc6PSx"
						 ).get();
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			if(doc!=null){
				String str=doc.toString();
				String st= str.replace("<html>","").replace("</html>", "").replace("<head></head>", "").replace("<body>", "").replace("</body>", "");
				JSONObject jsonObject = JSONObject.fromObject(st);  
	    	     //System.err.println("jsonObject   "+jsonObject);
	    	    String result=jsonObject.getString("result");
	    	    System.out.println("result" +result);
	    	    JSONObject jsonObject2=JSONObject.fromObject(result);
	    	    Pojo pojo=(Pojo)JSONObject.toBean(jsonObject2, Pojo.class);
	    	    
	    	    pojo.setCityName(cityName);
	    	   // System.out.println("jsonObject2.toString()::  "+jsonObject2);
	    	    System.out.println(pojo);
	    	    
	    	    baiDuMapCollectDao.collect1(pojo);
			}
		}
}
