package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.CottomsPostDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.RedisService;
import com.yayiabc.http.mvc.service.ZanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ZanServiceImpl implements ZanService{

	@Autowired
	private RedisService redisService;
	@Autowired
	private CottomsPostDao cottomsPostDao;
	@Autowired
	private UtilsDao utilsDao;



	@Override
	public DataWrapper<Void> upvote(String token, String type, Integer typeId,Integer parentId,Integer presentId) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		//获取用户信息
		User user = utilsDao.getUserByToken(token);
		String userId = user.getUserId();
		//剔除空，变成空串
		String parentIdStr="";
		String presentIdStr="";
		if(parentId!=null){
			parentIdStr=":"+parentId;
			if(presentId!=null){
				presentIdStr=":"+presentId;
			}
		}
		String key=type+":"+typeId+parentIdStr+presentIdStr;
		String keyTwo=key.substring(0,key.lastIndexOf(":"));
		String member=key.substring(key.lastIndexOf(":")+1); 

		//1.判断是否已经被点赞
		boolean flag = redisService.SETS.sismember("点赞用户列表"+key, userId);
		//2.如果已经点赞,取消点赞
		if (flag) {
			redisService.SETS.srem("点赞用户列表"+key, userId);
			redisService.SORTSET.zincrby("点赞计数列表"+keyTwo, -1, member);
		} else{//3.如果未点赞,则点赞
			redisService.SETS.sadd("点赞用户列表"+key,userId);
			redisService.SORTSET.zincrby("点赞计数列表"+keyTwo,1,member);
		}
		
		return dataWrapper;
		
	}
	@Override
	public int getZanNumber(String type,Integer typeId,Integer parentId,Integer presentId){
		//剔除空，变成空串
		String parentIdStr="";
		String presentIdStr="";
		if(parentId!=null){
			parentIdStr=":"+parentId;
			if(presentId!=null){
				presentIdStr=":"+presentId;
			}
		}
		String key=type+":"+typeId+parentIdStr+presentIdStr;
		String keyTwo=key.substring(0,key.lastIndexOf(":"));
		String member=key.substring(key.lastIndexOf(":")+1);
		return (int)redisService.SORTSET.zscore("点赞计数列表"+keyTwo,member);
	}


}
