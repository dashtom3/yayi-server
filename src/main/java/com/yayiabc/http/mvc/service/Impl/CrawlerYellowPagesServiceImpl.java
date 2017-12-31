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
		//System.out.println(hm);

		List<DaForDentist> listhm=crawlerYellowPagesDao.getMaterList(hm);
		//判断用户是否登陆浏览资料
		//System.out.println(listhm);
		String userId=null;
		if(token!=null){
			userId= utilsDao.getUserID(token);
			List<Integer> idList=crawlerYellowPagesDao.queryCollectId(userId);
			System.out.println(idList);
			/*for(int x=0;x<idList.size();x++){
				for(int i=0;i<listhm.size();i++){
					if(idList.get(x).equals(listhm.get(i).getId())){
						System.out.println("id为:"+x+" 被收藏");
						listhm.get(i).setIsCollect("1");
					}else{
						listhm.get(i).setIsCollect("0");
					}
				}
			}*/
			if(idList.isEmpty()){
				 for(int i=0;i<listhm.size();i++){
					 listhm.get(i).setIsCollect("0");
				 }
			}else{
				/*for(int  i=0;i<listhm.size();i++){
					for(int x=0;x<idList.size();x++){
						if(idList.get(x).equals(listhm.get(i).getId())){
							//System.out.println("id为:"+listhm.get(i).getId()+" 被收藏");
							listhm.get(i).setIsCollect("1");
					        System.out.println(listhm.get(i));
						}else{
							listhm.get(i).setIsCollect("0");
						}
					}
				}*/
				for(DaForDentist dfd:listhm){
					for(Integer id:idList){
						if(dfd.getId()==id){
							dfd.setIsCollect("1");
					       break;
						}else{
							dfd.setIsCollect("0");
						}
					}
				}
			}
		}
		//System.out.println(listhm);
		int count=crawlerYellowPagesDao.queryCountTOSheet1(hm);
		dataWrapper.setPage(page, count);
		
		//获取浏览数
		Jedis jedis=RedisClient.getInstance().getJedis();
      
		for(DaForDentist dfd:listhm){
			//	System.out.println(dfd);
			String conetnt=dfd.getContent();
			if(conetnt.length()>55){
				dfd.setContent(conetnt.substring(0,50));	
			}else{
				dfd.setContent(conetnt.substring(0,30));	
			}
			Double k=jedis.zscore("Master_Browse_Num", dfd.getId()+"");
			if(null==k){
				dfd.setBrowseNumber(0);
			}else{
				dfd.setBrowseNumber((int) (k+0));
			}
		}
		 
	
		dataWrapper.setData(listhm);
		jedis.close();
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
		System.out.println("u12t3123j213213hj2v312hj3v12jh312hj31v2hj31v2j3v21j3hv12");
		
		DataWrapper<DaForDentist> dataWrapper=new DataWrapper<DaForDentist>();
		DaForDentist daForDentist=crawlerYellowPagesDao.getMaterDetail(id);
		if(daForDentist==null){
			dataWrapper.setMsg("资料库无此资料信息");
			return dataWrapper;
		}
		String userId=null;
		if(token!=null){
			System.out.println(111);
			userId= utilsDao.getUserID(token);
			List<Integer> idList=crawlerYellowPagesDao.queryCollectId(userId);
             System.out.println(idList);
			if(idList.isEmpty()){
				daForDentist.setIsCollect("0");
			}else{
				for(int x=0;x<idList.size();x++){
					if(idList.get(x).equals(daForDentist.getId())){
						daForDentist.setIsCollect("1");
						break;
					}else{
						daForDentist.setIsCollect("0");
					}
				}
			}
		}else{
			System.out.println(000);
			daForDentist.setIsCollect("0");
		}


		System.out.println(daForDentist);
		dataWrapper.setData(daForDentist);
		//这里需要记录资料库的浏览数
		Jedis jedis=RedisClient.getInstance().getJedis();
		Double lon=jedis.zincrby("Master_Browse_Num", 1, id);
		Double BN=jedis.zscore("Master_Browse_Num", daForDentist.getId()+"");
		daForDentist.setBrowseNumber((int) (BN+0));
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
		int qqq=crawlerYellowPagesDao.queryIsCollect(userId,id);
		if(qqq==0){
			int sign=crawlerYellowPagesDao.collectionMater(userId,id);
			dataWrapper.setMsg("收藏成功");
		}else{
			crawlerYellowPagesDao.deleteCollect(userId,id);
			dataWrapper.setMsg("取消收藏成功");
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
