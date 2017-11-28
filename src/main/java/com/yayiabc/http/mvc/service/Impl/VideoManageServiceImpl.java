package com.yayiabc.http.mvc.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yayiabc.common.utils.ScoreUtil;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.VideoScreenPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.utils.SerializeUtil;
import com.yayiabc.http.mvc.dao.VideoManageDao;
import com.yayiabc.http.mvc.pojo.jpa.VidManage;
import com.yayiabc.http.mvc.service.RedisService;
import com.yayiabc.http.mvc.service.VideoManageService;
@Service
public class VideoManageServiceImpl implements VideoManageService {
     @Autowired
     private VideoManageDao videoManageDao;

     @Autowired
	 private RedisService redisService;

     @Autowired
	 private VideoScreenPicService videoScreenPicService;

     @Autowired
	 private UtilsDao utilsDao;



	@Override
	public DataWrapper<Object> showVid(Integer rule, Integer videoCategory, Integer currentPage, Integer numberPerPage,String keyWord,String token) {
		//如果登录；后面要判断是否已经收藏
		String userId=null;
		if(token!=null){
			User user=utilsDao.getUserByToken(token);
			userId=user.getUserId();
		}
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
//		int totalNumber=videoManageDao.getTotalNumber(videoCategory);
		List<VidManage> vidManageList=videoManageDao.showVid(rule,videoCategory,keyWord);
		int totalNumber=vidManageList.size();
		System.out.println(totalNumber);
		Set<String> viIdSet=null;
		System.out.println("vidManangeList"+vidManageList);
		//添加id进zset中
		for (VidManage vidManage:vidManageList
			 ) {
			redisService.SORTSET.zincrby("视频评论数",0,vidManage.getViId()+"");
			redisService.SORTSET.zincrby("视频播放量",0,vidManage.getViId()+"");
			if(redisService.SORTSET.zscore("视频时间倒叙",vidManage.getViId()+"")==0){
				redisService.SORTSET.zincrby("视频时间倒叙",vidManage.getVedioTime().getTime(),vidManage.getViId()+"");
			}
		}
		System.out.println(redisService.SORTSET.zrevrange("视频播放量",0,-1));
		System.out.println(page.getCurrentNumber());
		System.out.println(currentPage*numberPerPage);
		if(rule==2){//最多评论
			viIdSet=redisService.SORTSET.zrevrange("视频评论数",page.getCurrentNumber(),currentPage*numberPerPage-1);
		}else if(rule==1){//最多播放
			viIdSet=redisService.SORTSET.zrevrange("视频播放量",page.getCurrentNumber(),currentPage*numberPerPage-1);
		}else{//时间倒叙
			viIdSet=redisService.SORTSET.zrevrange("视频时间倒叙",page.getCurrentNumber(),currentPage*numberPerPage-1);
		}
		System.out.println(viIdSet);
		List<VidManage> vidManages=new ArrayList<VidManage>();
		Integer id;
		for(String viId:viIdSet){
			id=Integer.parseInt(viId);
			for (VidManage vidManage:vidManageList
			 ) {
				if(vidManage.getViId().equals(id)){
					//填充评论数
					int commentNum=(int)redisService.SORTSET.zscore("视频评论数",viId);
					vidManage.setVedioCommentNumber(commentNum);
					//填充收藏数
					int starNum=(int)redisService.SETS.scard("视频收藏用户列表"+viId);
					vidManage.setStarNumber(starNum);
					//填充是否已收藏
					if(userId!=null){
						if(redisService.SETS.sismember("视频收藏用户列表"+id,userId)){
							vidManage.setIsStar(1);
						}
					}
					vidManages.add(vidManage);
					break;
				}
			}
		}
		dataWrapper.setData(vidManages);
		dataWrapper.setPage(page,totalNumber);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateVid(VidManage vidManage) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=videoManageDao.updateVid(vidManage);
		if(state==0){
			dataWrapper.setErrorCode(ErrorCodeEnum.OPERATION_ERROR);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> insertVid(VidManage vidManage) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		String vidRoute=vidManage.getVidRoute();//获取视频的外链
		String fileName=getFileName(vidRoute);
		videoScreenPicService.qiNiuMediaPrtScreen(fileName,"jpg");
		int index=fileName.indexOf(".");
		String vedioPic=vidRoute+".jpg";
		if(index!=-1){
			vedioPic=vidRoute.substring(0,vidRoute.lastIndexOf("."))+".jpg";
		}
		vidManage.setVedioPic(vedioPic);
		videoManageDao.insertVid(vidManage);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteVid(Integer viId) {
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=videoManageDao.deleteVid(viId);
		//TODO 删除该视频下的评论

		redisService.SORTSET.zrem("视频播放量",viId+"");
		redisService.SORTSET.zrem("视频评论数",viId+"");
		redisService.SORTSET.zrem("视频时间倒叙",viId+"");
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> play(Integer viId) {
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		redisService.SORTSET.zincrby("视频播放量",1,viId+"");
		return dataWrapper;
	}

	@Override
	public DataWrapper<VidManage> detail(Integer viId) {
		DataWrapper<VidManage> dataWrapper=new DataWrapper<VidManage>();
		VidManage vidManage=videoManageDao.detail(viId);
		dataWrapper.setData(vidManage);
		return dataWrapper;
	}


	@Override
	public DataWrapper<Void> star(String token, Integer viId) {
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		//根据token来获取userId
		User user=utilsDao.getUserByToken(token);
		String userId=user.getUserId();
		//判断是否已经收藏
		boolean flag=isStar(userId,viId);
		//如果已经收藏，则取消收藏
		if(flag){
			unStar(userId,viId);
			unUserStar(userId,viId);
			return dataWrapper;
		}
		//如果未收藏，则添加到收藏
		toStar(userId,viId);
		userStar(userId,viId);
		return dataWrapper;
	}

	//获取七牛文件名称
	public String getFileName(String videoRout){
		String fileName=videoRout.substring(videoRout.lastIndexOf("/")+1);
		return fileName;
	}

	//判断是否已经收藏,维护一个收藏的userIdSet,key为收藏用户列表
	public boolean isStar(String userId,Integer viId){
		return redisService.SETS.sismember("视频收藏用户列表"+viId,userId);
	}

	//如果已经收藏了，则取消收藏
	public void unStar(String userId,Integer viId){
		redisService.SETS.srem("视频收藏用户列表"+viId,userId);
	}

	//如果未收藏了,则收藏
	public void toStar(String userId,Integer viId){
		redisService.SETS.sadd("视频收藏用户列表"+viId,userId);
	}

	//去除个人收藏列表里面的视频Id
	public void unUserStar(String userId,Integer viId){
		redisService.SETS.srem(userId+"视频收藏列表",viId+"");
	}

	//加入个人收藏列表里面的视频id
	public void userStar(String userId,Integer viId){
		redisService.SETS.sadd(userId+"视频收藏列表",viId+"");
	}


}
