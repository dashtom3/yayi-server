package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.VideoManageDao;
import com.yayiabc.http.mvc.pojo.jpa.VidManage;
import com.yayiabc.http.mvc.service.VideoManageService;
@Service
public class VideoManageServiceImpl implements VideoManageService {
     @Autowired
     private VideoManageDao videoManageDao;

	@Override
	public DataWrapper<List<VidManage>> showVid() {
		// TODO Auto-generated method stub
		 DataWrapper<List<VidManage>> dataWrapper=new DataWrapper<List<VidManage>>();
		    dataWrapper.setData(videoManageDao.showVid());
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateVid(VidManage vidManage) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=videoManageDao.updateVid(vidManage);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> insertVid(VidManage vidManage) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=videoManageDao.updateVid(vidManage);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteVid(Integer viId) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=videoManageDao.deleteVid(viId);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
}
