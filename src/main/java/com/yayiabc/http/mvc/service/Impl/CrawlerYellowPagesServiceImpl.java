package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.utils.RedisClient;
import com.yayiabc.http.mvc.dao.CrawlerYellowPagesDao;
import com.yayiabc.http.mvc.pojo.jpa.DaForDentist;
import com.yayiabc.http.mvc.pojo.jpa.Sheet1;
import com.yayiabc.http.mvc.service.CrawlerYellowPagesService;

import redis.clients.jedis.Jedis;
@Service
public class CrawlerYellowPagesServiceImpl implements CrawlerYellowPagesService{
 
	@Autowired CrawlerYellowPagesDao crawlerYellowPagesDao;
	@Override
	public DataWrapper<List<DaForDentist>> getMaterList(Integer currentPage, Integer numberPerpage,String keyWord) {
		// TODO Auto-generated method stub
		DataWrapper<List<DaForDentist>> dataWrapper=new DataWrapper<List<DaForDentist>>();
		HashMap<String,String> hm=new HashMap<String,String>();
		Page page=new Page();
		
		page.setNumberPerPage(numberPerpage);
		page.setCurrentPage(currentPage);
		hm.put("numberPerpage", String.valueOf(page.getNumberPerPage()));
		Integer currentNum=page.getCurrentNumber();
		hm.put("currentNum", String.valueOf(currentNum));
		hm.put("keyWord", keyWord);
		System.out.println(hm);
		List<DaForDentist> listhm=crawlerYellowPagesDao.getMaterList(hm);
		int count=crawlerYellowPagesDao.queryCountTOSheet1(hm);
		dataWrapper.setPage(page, count);
		for(DaForDentist dfd:listhm){
			dfd.setContext(dfd.getContext().substring(0, 50));	
		}
		dataWrapper.setData(listhm);
		return dataWrapper;
	}
	
	@Override
	public DataWrapper<List<Sheet1>> getList(Integer currentPage, Integer numberPerpage, double lng, double lat, String cityName,String keyWord) {
		// TODO Auto-generated method stub
		DataWrapper<List<Sheet1>> dataWrapper=new DataWrapper<List<Sheet1>>();
		HashMap<String,Object> hm=new HashMap<String,Object>();
		Double[] ds=findNeighPosition(lng,lat);
		if(ds!=null){
		hm.put("minlng", ds[0]);
		hm.put("maxlng", ds[1]);
		hm.put("minlat", ds[2]);
		hm.put("maxlat", ds[3]);
		}
		hm.put("cityName", cityName);
		hm.put("keyWord", keyWord);
		Page page=new Page();
		
		page.setNumberPerPage(numberPerpage);
		page.setCurrentPage(currentPage);
		hm.put("numberPerpage", String.valueOf(page.getNumberPerPage()));
		Integer currentNum=page.getCurrentNumber();
		hm.put("currentNum", String.valueOf(currentNum));
		
		List<Sheet1> listhm=crawlerYellowPagesDao.getList(hm);
		int count=crawlerYellowPagesDao.queryCountTOX(hm);
		dataWrapper.setPage(page, count);
		dataWrapper.setData(listhm);
		return dataWrapper;
	}
	
	public  Double[] findNeighPosition(double lng,double lat){ 
		if(lng==0.0&&lat==0.0){
			return null;
		}
        //先计算查询点的经纬度范围  
        double r = 6371;//地球半径千米  
        double dis = 20;//20千米距离  
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(lat*Math.PI/180));  
        dlng = dlng*180/Math.PI;//角度转为弧度  
        double dlat = dis/r;  
        dlat = dlat*180/Math.PI;          
        double minlat =lat-dlat;  
        double maxlat = lat+dlat;  
        double minlng = lng -dlng;  
        double maxlng = lng + dlng;  
          
        Double[] values = {minlng,maxlng,minlat,maxlat};  
          
        return values;  
    }

	/**
	 * 资料库详情
	 * @param id
	 * @return
	 */
	@Override
	public DataWrapper<DaForDentist> getMaterDetail(String id) {
	    DataWrapper<DaForDentist> dataWrapper=new DataWrapper<DaForDentist>();
		DaForDentist daForDentist=crawlerYellowPagesDao.getMaterDetail(id);
		dataWrapper.setData(daForDentist);
		//这里需要记录资料库的浏览数
	     Jedis jedis=RedisClient.getInstance().getJedis();
		return dataWrapper;
	}  
}
