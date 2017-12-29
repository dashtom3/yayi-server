package com.yayiabc.http.mvc.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yayiabc.common.utils.ScoreUtil;
import com.yayiabc.http.mvc.dao.CommentDao;
import com.yayiabc.http.mvc.dao.CottomsPostDao;
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

     @Autowired
	 private CottomsPostDao cottomsPostDao;



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
				//填充是否已收藏
				if(userId!=null){
					if(cottomsPostDao.existCollect(vidManage.getViId()+"",userId,"视频")!=0){
						vidManage.setIsStar(1);
					}
				}
		}
		if(vidManageList==null||vidManageList.size()==0){
			vidManageList=null;
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
		String vidName=vidManage.getVidName();
		videoScreenPicService.qiNiuMediaPrtScreen(vidName,"jpg");
		int index=fileName.indexOf(".");
		String vedioPic=vidRoute+".jpg";
		if(index!=-1){
			vedioPic=vidRoute.substring(0,vidRoute.lastIndexOf("."))+".jpg";
		}
		vidManage.setVedioPic(vedioPic);
		vidManage.setVidName(vidName.substring(0,vidName.indexOf(".")));
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









}
