package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.CusResoDao;
import com.yayiabc.http.mvc.pojo.jpa.CusResources;
import com.yayiabc.http.mvc.service.CusResoService;
@Service
public class CusResoServiceImpl  implements CusResoService{
   @Autowired
   private  CusResoDao cuResoDao;
	@Override
	public DataWrapper<List<CusResources>> show(String state) {
		// TODO Auto-generated method stub
		DataWrapper<List<CusResources>>  dataWrapper=new DataWrapper<List<CusResources>>();
		List<CusResources> list=cuResoDao.show(state);
		if(list.isEmpty()){
			dataWrapper.setMsg("暂无数据");
		}else{
			dataWrapper.setData(list);
		}
		return dataWrapper;
	}
	//insert
	@Override
	public DataWrapper<Void> insert(CusResources cus) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=cuResoDao.insert(cus);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
	//update
	@Override
	public DataWrapper<Void> update(CusResources cus) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=cuResoDao.update(cus);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
	
	
	//delete
		@Override
		public DataWrapper<Void> delete(int id) {
			// TODO Auto-generated method stub
			DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
			int state=cuResoDao.delete(id);
			if(state>0){
				dataWrapper.setMsg("操作成功");
			}else{
				dataWrapper.setMsg("操作失败");
			}
			return dataWrapper;
		}
}
