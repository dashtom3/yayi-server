package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.AdvManageDao;
import com.yayiabc.http.mvc.pojo.jpa.AdvChart;
import com.yayiabc.http.mvc.service.AdvManageService;

@Service
public class AdvManageServiceImpl implements AdvManageService{
  @Autowired
  private AdvManageDao advManageDao;

@Override  //show
public DataWrapper<List<AdvChart>> showAdv() {
	// TODO Auto-generated method stub
	DataWrapper<List<AdvChart>> dataWrapper=new DataWrapper<List<AdvChart>>();
	dataWrapper.setData(advManageDao.showAdv());
	return dataWrapper;
	
}

@Override
public DataWrapper<Void> insertAdv(AdvChart advChart) {
	// TODO Auto-generated method stub
	DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
	int state=advManageDao.insertAdv(advChart);
	dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
	if(state>0){
		dataWrapper.setMsg("操作成功");
	}else{
		dataWrapper.setMsg("操作失败");
	}
	return dataWrapper;
}

@Override
public DataWrapper<Void> updateAdv(AdvChart advChart) {
	// TODO Auto-generated method stub
	DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
	int state=advManageDao.updateAdv(advChart);
	dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
	if(state>0){
		dataWrapper.setMsg("操作成功");
	}else{
		dataWrapper.setMsg("操作失败");
	}
	return dataWrapper;
}

@Override
public DataWrapper<Void> deleteAdv(Integer advId) {
	// TODO Auto-generated method stub
	DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
	int state=advManageDao.deleteAdv(advId);
	dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
	if(state>0){
		dataWrapper.setMsg("操作成功");
	}else{
		dataWrapper.setMsg("操作失败");
	}
	return dataWrapper;
}
}
