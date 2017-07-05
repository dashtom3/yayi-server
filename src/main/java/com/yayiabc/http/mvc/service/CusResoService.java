package com.yayiabc.http.mvc.service;

import java.util.List;


import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CusResources;

public interface CusResoService {
     //显示
	public DataWrapper<List<CusResources>> show(String state);
  //insert
	public DataWrapper<Void> insert(CusResources cus);
	
	//update
	public DataWrapper<Void> update(CusResources cus);
     //delete
	
	public DataWrapper<Void> delete(int id);
}
