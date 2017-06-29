package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.VidManage;




public interface VideoManageService {
     //show
	DataWrapper<List<VidManage>>  showVid();
	  //update
	DataWrapper<Void> updateVid(VidManage vidManage);
	//insert 
	 DataWrapper<Void> insertVid(VidManage vidManage);
	 //delete
	 DataWrapper<Void> deleteVid(Integer viId);
 }
