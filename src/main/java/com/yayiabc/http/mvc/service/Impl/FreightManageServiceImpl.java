package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;

import com.yayiabc.http.mvc.dao.FreightManageDao;
import com.yayiabc.http.mvc.pojo.jpa.PostFee;
import com.yayiabc.http.mvc.pojo.jpa.FreeShipping;
import com.yayiabc.http.mvc.service.FreightManageService;
@Service
public class FreightManageServiceImpl implements FreightManageService{
	@Autowired
	private FreightManageDao freightManageDao;
	@Override
	public DataWrapper<List<PostFee>> showFreight() {
		// TODO Auto-generated method stub
		DataWrapper<List<PostFee>>  dataWrapper=new DataWrapper<List<PostFee>> ();
		dataWrapper.setData(freightManageDao.showFreight());
		return dataWrapper;
	}
	//自定义运费
	@Override
	public DataWrapper<Void> customFreight(PostFee postFee) {
		DataWrapper<Void>  dataWrapper=new DataWrapper<Void> ();
		int state=freightManageDao.customFreight(postFee);
		if(state>0){
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
	//删除
	@Override
	public DataWrapper<Void> deleteCustomFreight(Integer postFeeId) {
		// TODO Auto-generated method stub
		DataWrapper<Void>  dataWrapper=new DataWrapper<Void> ();
		int state=freightManageDao.deleteCustomFreight(postFeeId);
		if(state>0){
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
	//显示包邮数据
	@Override
	public DataWrapper<List<FreeShipping>> showFreeShipp() {
		// TODO Auto-generated method stub
		DataWrapper<List<FreeShipping>> dataWrapper=new DataWrapper<List<FreeShipping>>();
	    List<FreeShipping> list=freightManageDao.showFreeShipp();
	    dataWrapper.setData(list);
		return dataWrapper;
	}
	//包邮Add
	@Override
	public DataWrapper<Void> insertFreeShipp(FreeShipping f) {
		DataWrapper<Void>  dataWrapper=new DataWrapper<Void> ();
		int state=freightManageDao.insertFreeShipp(f);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
	//包邮update
	@Override
	public DataWrapper<Void> updateFreeShipp(FreeShipping f) {
		// TODO Auto-generated method stub
		DataWrapper<Void>  dataWrapper=new DataWrapper<Void> ();
		int state=freightManageDao.updateFreeShipp(f);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
	@Override
	//新增自定义运费
	public DataWrapper<Void> addCustomFreight(PostFee postFee) {
		// TODO Auto-generated method stub
		DataWrapper<Void>  dataWrapper=new DataWrapper<Void> ();
		int state=freightManageDao.addCustomFreight(postFee);
		if(state>0){
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
}
