package com.yayiabc.http.mvc.dao;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.model.Pojo;

public interface BaiDuMapCollectDao {

	int  collect1(@Param("pojo")Pojo pojo);

}
