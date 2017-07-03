package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.VidManage;

public interface VideoManageDao {

			List<VidManage> showVid();
		
			int insertVid(@Param("vidManage")VidManage vidManage);

			int updateVid(VidManage VidManage);
	
			int  deleteVid(@Param("viId")Integer viId);
}
