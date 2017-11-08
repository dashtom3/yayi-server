package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

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

    /* @Autowired
     @Qualifier("redisService")*/
	 private RedisService redisService;



	@Override
	public DataWrapper<List<VidManage>> showVid(Integer rule, Integer videoCategory, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<VidManage>> dataWrapper=new DataWrapper<List<VidManage>>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber=videoManageDao.getTotalNumber(videoCategory);
		List<VidManage> vidManageList=videoManageDao.showVid(rule,videoCategory,page.getCurrentNumber(),page.getNumberPerPage());
		System.out.println(vidManageList);
		dataWrapper.setData(vidManageList);
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
		videoManageDao.insertVid(vidManage);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteVid(Integer viId) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=videoManageDao.deleteVid(viId);
		videoManageDao.deleteVedioComment(viId);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
}
