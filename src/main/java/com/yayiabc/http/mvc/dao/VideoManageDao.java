package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.VidManage;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoManageDao {

			
		
			int insertVid(VidManage vidManage);

			int updateVid(VidManage VidManage);
	
			int  deleteVid(@Param("viId")Integer viId);

	void deleteVedioComment(Integer viId);

	int getTotalNumber(@Param("videoCategory")Integer videoCategory);

	List<VidManage> showVid(@Param("rule")Integer rule,@Param("videoCategory") Integer videoCategory);
}
