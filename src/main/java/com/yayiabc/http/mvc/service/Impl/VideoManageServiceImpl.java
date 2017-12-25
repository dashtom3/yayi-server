package com.yayiabc.http.mvc.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yayiabc.common.utils.ScoreUtil;
import com.yayiabc.http.mvc.dao.CommentDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
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

     @Autowired
	 private CommentDao commentDao;



	@Override
	public DataWrapper<Object> showVid(Integer rule, Integer videoCategory, Integer currentPage, Integer numberPerPage,String keyWord,String token) {
		//如果登录；后面要判断是否已经收藏
		String userId=null;
		if(token!=null){
			userId=utilsDao.getUserID(token);
		}
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber=videoManageDao.getVideoTotalNum(videoCategory,keyWord);
		List<VidManage> vidManageList=videoManageDao.showVid(rule,videoCategory,keyWord,page);
		for (VidManage vidManage:vidManageList
		 ) {
				String videoRout=videoManageDao.getVideoRoute(vidManage.getViId());
				ItemInfo itemInfo=videoManageDao.getVideoItem(videoRout);
				vidManage.setItemInfo(itemInfo);
				//填充收藏数
				int starNum=(int)redisService.SETS.scard("视频收藏用户列表"+vidManage.getViId());
				vidManage.setStarNumber(starNum);
				//填充是否已收藏
				if(userId!=null){
					if(redisService.SETS.sismember("视频收藏用户列表"+vidManage.getViId(),userId)){
						vidManage.setIsStar(1);
					}
				}
		}
		dataWrapper.setData(vidManageList);
		dataWrapper.setPage(page,totalNumber);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateVid(VidManage vidManage) {
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=videoManageDao.updateVid(vidManage);
		if(state==0){
			dataWrapper.setErrorCode(ErrorCodeEnum.OPERATION_ERROR);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> insertVid(VidManage vidManage) {
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		//获取视频的外链
		String vidRoute=vidManage.getVidRoute();
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
		videoManageDao.playVideo(viId);
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
		return starOrUnStar(token,viId,"视频");
	}

	@Override
	public DataWrapper<Void> starOrUnStar(String token,Integer viId,String type){
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		//根据token来获取userId
		User user=utilsDao.getUserByToken(token);
		String userId=user.getUserId();
		//判断是否已经收藏
		boolean flag=isStar(userId,viId,type);
		//如果已经收藏，则取消收藏
		if(flag){
			unStar(userId,viId,type);
			unUserStar(userId,viId,type);
			return dataWrapper;
		}
		//如果未收藏，则添加到收藏
		toStar(userId,viId,type);
		userStar(userId,viId,type);
		return dataWrapper;
	}

	@Override
	public DataWrapper<ItemInfo> videoItem(Integer viId) {
		DataWrapper<ItemInfo> dataWrapper=new DataWrapper<ItemInfo>();
		String videoRoute=videoManageDao.getVideoRoute(viId);
		ItemInfo itemInfo=videoManageDao.videoItem(videoRoute);
		dataWrapper.setData(itemInfo);
		return dataWrapper;
	}

	//获取七牛文件名称
	public String getFileName(String videoRout){
		String fileName=videoRout.substring(videoRout.lastIndexOf("/")+1);
		return fileName;
	}

	//判断是否已经收藏,维护一个收藏的userIdSet,key为收藏用户列表
	public boolean isStar(String userId,Integer viId,String type){
		return redisService.SETS.sismember(type+"收藏用户列表"+viId,userId);
	}

	/**
	 * 取消收藏
	 * @param userId
	 * @param viId
	 * @param type
	 */
	public void unStar(String userId,Integer viId,String type){
		redisService.SETS.srem(type+"收藏用户列表"+viId,userId);
	}

	/**
	 * 收藏
	 * @param userId
	 * @param viId
	 * @param type
	 */
	public void toStar(String userId,Integer viId,String type){
		redisService.SETS.sadd(type+"收藏用户列表"+viId,userId);
	}

	/**
	 * 去除个人收藏列表里面的视频Id
	 * @param userId
	 * @param viId
	 * @param type
	 */
	public void unUserStar(String userId,Integer viId,String type){
		redisService.SETS.srem(userId+type+"收藏列表",viId+"");
	}

	/**
	 * 加入个人收藏列表里面的视频id
	 * @param userId
	 * @param viId
	 * @param type
	 */
	public void userStar(String userId,Integer viId,String type){
		redisService.SETS.sadd(userId+type+"收藏列表",viId+"");
	}


}
