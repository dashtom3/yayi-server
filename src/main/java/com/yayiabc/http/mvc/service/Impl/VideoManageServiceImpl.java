package com.yayiabc.http.mvc.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yayiabc.common.utils.ScoreUtil;
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



	@Override
	public DataWrapper<List<VidManage>> showVid(Integer rule, Integer videoCategory, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<VidManage>> dataWrapper=new DataWrapper<List<VidManage>>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
//		int totalNumber=videoManageDao.getTotalNumber(videoCategory);
		List<VidManage> vidManageList=videoManageDao.showVid(rule,videoCategory);
		int totalNumber=vidManageList.size();
		Set<String> viIdSet=null;
		if(rule==2){//最多评论
			viIdSet=redisService.SORTSET.zrevrange("视频评论数",page.getCurrentNumber(),currentPage*numberPerPage);
		}else{//最多播放
			viIdSet=redisService.SORTSET.zrevrange("视频播放量",page.getCurrentNumber(),currentPage*numberPerPage);
		}
		System.out.println(viIdSet);
		List<VidManage> vidManages=new ArrayList<VidManage>();
		Integer id;
		for (String viId:viIdSet
			 ) {
			id=Integer.parseInt(viId);
			for (VidManage vidManage:vidManageList
				 ) {
				if(vidManage.getViId().equals(id)){
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
		String fileName=getFileName(vidManage.getVidRoute());
		videoScreenPicService.qiNiuMediaPrtScreen(fileName,"jpg");
		int index=fileName.indexOf(".");
		String vedioPic=vidManage.getVidRoute()+".jpg";
		if(index!=-1){
			vedioPic=vidManage.getVidRoute().substring(0,vidManage.getVidRoute().lastIndexOf("."))+".jpg";
		}
		vidManage.setVedioPic(vedioPic);
		videoManageDao.insertVid(vidManage);
		Integer viId=vidManage.getViId();
		//视频播放量+时间混合排序
		redisService.SORTSET.zadd("视频播放量",0,viId+"");
		//视频评论数+时间混合排序
		redisService.SORTSET.zadd("视频评论数",0,viId+"");
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteVid(Integer viId) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=videoManageDao.deleteVid(viId);
		//TODO 删除该视频下的评论

		redisService.SORTSET.zrem("视频播放量",viId+"");
		redisService.SORTSET.zrem("视频评论数",viId+"");
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

	//获取七牛文件名称
	public String getFileName(String videoRout){
		String fileName=videoRout.substring(videoRout.lastIndexOf("/"));
		return fileName;
	}

}
