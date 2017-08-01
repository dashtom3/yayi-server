package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.CusResoDao;
import com.yayiabc.http.mvc.pojo.jpa.CusResources;
import com.yayiabc.http.mvc.service.CusResoService;
@Service
public class CusResoServiceImpl  implements CusResoService{
   @Autowired
   private  CusResoDao cuResoDao;
	@Override
	public DataWrapper<List<CusResources>> show(HashMap<String, String> hashMap,
			Integer currentPage,
   			Integer numberPerpage
			) {
		// TODO Auto-generated method stub
		DataWrapper<List<CusResources>>  dataWrapper=new DataWrapper<List<CusResources>>();
		Page page=new Page();
		if(currentPage!=null&numberPerpage!=null){
		page.setNumberPerPage(numberPerpage);
		page.setCurrentPage(currentPage);
		}else{
			page.setNumberPerPage(10);
			page.setCurrentPage(1);
		}
		hashMap.put("numberPerpage", String.valueOf(page.getNumberPerPage()));
		Integer currentNum=page.getCurrentNumber();
		hashMap.put("currentNum", String.valueOf(currentNum));
		//  总条数
		int count=cuResoDao.queryCount(hashMap);
		List<CusResources> list=cuResoDao.show(hashMap);
		if(list.isEmpty()){
			dataWrapper.setMsg("暂无数据");
		}else{
			dataWrapper.setPage(page, count);
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
