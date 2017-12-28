package com.yayiabc.http.mvc.dao;

import org.apache.ibatis.annotations.Param;

public interface ZanDao {

    int getCount(@Param("userId")String userId,@Param("type") String type,@Param("typeId") Integer typeId,@Param("parentId") Integer parentId,@Param("presentId") Integer presentId);

    void addStatus(@Param("userId")String userId,@Param("type") String type,@Param("typeId") Integer typeId,@Param("parentId") Integer parentId,@Param("presentId") Integer presentId);

    void deleteStatus(@Param("userId")String userId,@Param("type") String type,@Param("typeId") Integer typeId,@Param("parentId") Integer parentId,@Param("presentId") Integer presentId);
}
