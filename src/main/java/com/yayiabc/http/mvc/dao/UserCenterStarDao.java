package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.*;
@Repository
public interface UserCenterStarDao {
	
	List<MyStar> shows(String userId);
	int deleteStarOne(@Param("deleteOneId") int deleteOneId,@Param("userId")String userId);
	int  deleteStarAll(@Param("userId") String userId);
	int  addMyStar(@Param("userId") String userId,@Param("itemId") String itemId);
}
