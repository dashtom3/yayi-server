package com.yayiabc.http.mvc.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;

public interface LogisticsDao {
	HashMap<String,Ordera> queryLog(@Param("userId")String userId,@Param("itemId")String itemId);
}
