package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.*;
@Repository
public interface UserCenterStarDao {
	
	List<MyStar> shows(@Param("userId")String userId,@Param("page") Page page);
	int deleteStarOne(@Param("deleteOneId") String deleteOneId,@Param("userId")String userId);
	int  deleteStarAll(@Param("userId") String userId);
	int  addMyStar(@Param("userId") String userId,@Param("itemId") String itemId);
	//查询是否已经收藏
	List<Integer> queryOne(@Param("itemId")String itemId);
	
	//查询总条数
	int queryCount(@Param("tablename")String tablename);
}
