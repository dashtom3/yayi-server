package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.utils.RedisClient;
import com.yayiabc.http.mvc.dao.CrawlerYellowPagesDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.DaForDentist;
import com.yayiabc.http.mvc.pojo.jpa.Sheet1;
import com.yayiabc.http.mvc.pojo.jpa.UserCollectDataforDst;
import com.yayiabc.http.mvc.service.CrawlerYellowPagesService;

import redis.clients.jedis.Jedis;
@Service
public class CrawlerYellowPagesServiceImpl implements CrawlerYellowPagesService{

	@Autowired CrawlerYellowPagesDao crawlerYellowPagesDao;

	@Autowired
	private UtilsDao utilsDao;
	@Override
	public DataWrapper<List<DaForDentist>> getMaterList(Integer currentPage, Integer numberPerpage,String keyWord,String token) {
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
		//判断用户是否登陆浏览资料
		String userId=null;
		if(token!=null){
			userId= utilsDao.getUserID(token);
			List<Integer> idList=crawlerYellowPagesDao.queryCollectId(userId);

			for(int x=0;x<idList.size();x++){
				for(int i=0;i<listhm.size();i++){
                     if(idList.get(x).equals(listhm.get(i).getId())){
                    	 listhm.get(i).setIsCollect("1");
                     }else{
                    	 listhm.get(i).setIsCollect("0");
                     }
				}
			}

		}

		int count=crawlerYellowPagesDao.queryCountTOSheet1(hm);
		dataWrapper.setPage(page, count);

		//获取浏览数
		Jedis jedis=RedisClient.getInstance().getJedis();

		for(DaForDentist dfd:listhm){
			System.out.println(dfd);
			String conetnt=dfd.getContent();
			if(conetnt.length()>55){
				dfd.setContent(conetnt.substring(0,50));	
			}else{
				dfd.setContent(conetnt.substring(0,30));	
			}
			String k=String.valueOf(jedis.zscore("Master_Browse_Num", dfd.getId()+""));
			if(k.equals("null")){
				dfd.setBrowseNumber(0+"");
			}else{
				dfd.setBrowseNumber(k);
			}
		}
		jedis.close();
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
	public DataWrapper<DaForDentist> getMaterDetail(String id, String token) {
		DataWrapper<DaForDentist> dataWrapper=new DataWrapper<DaForDentist>();
		DaForDentist daForDentist=crawlerYellowPagesDao.getMaterDetail(id);
		String userId=null;
		if(token!=null){
			userId= utilsDao.getUserID(token);
			List<Integer> idList=crawlerYellowPagesDao.queryCollectId(userId);

			for(int x=0;x<idList.size();x++){
                     if(idList.get(x).equals(daForDentist.getId())){
                    	 daForDentist.setIsCollect("1");
                     }else{
                    	 daForDentist.setIsCollect("0");
                     }
			}

		}else{
			daForDentist.setIsCollect("0");
		}

		
		System.out.println(daForDentist);
		dataWrapper.setData(daForDentist);
		//这里需要记录资料库的浏览数
		Jedis jedis=RedisClient.getInstance().getJedis();
		Double lon=jedis.zincrby("Master_Browse_Num", 1, id);
		daForDentist.setBrowseNumber(jedis.zscore("Master_Browse_Num", daForDentist.getId()+"")+"");
		jedis.close();
		return dataWrapper;
	}

	/**
	 * 资料库收藏
	 * @param token
	 * @param id
	 * @return
	 */
	@Override
	public DataWrapper<Void> collectionMater(String token, String id) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		String userId=utilsDao.getUserID(token);
		if(userId==null){
			dataWrapper.setMsg("令牌错误");
			return dataWrapper;
		}
		//一个人  同一个资料 不能收藏两次  在数据库中用复合主键实现

		int sign=crawlerYellowPagesDao.collectionMater(userId,id);
		if(sign>0){
			dataWrapper.setMsg("收藏成功");
		}else{
			dataWrapper.setMsg("收藏失败");
		}
		return dataWrapper;
	}
	/**
	 * 我的资料收藏
	 */
	@Override
	public DataWrapper<List<DaForDentist>> userCollectionList(String token) {
		// TODO Auto-generated method stub
		DataWrapper<List<DaForDentist>> dataWrapper=new DataWrapper<List<DaForDentist>>();
		String userId=utilsDao.getUserID(token);
		List<DaForDentist> dfdList=crawlerYellowPagesDao.userCollectionList(userId);
		dataWrapper.setData(dfdList);
		return dataWrapper;
	}  
}
