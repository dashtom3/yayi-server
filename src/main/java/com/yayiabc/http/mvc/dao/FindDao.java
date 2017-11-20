package com.yayiabc.http.mvc.dao;

import com.yayiabc.http.mvc.pojo.jpa.VidManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FindDao {
    List<VidManage> getVidList(@Param("keyWord")String keyWord,@Param("classify") Integer classify);
}
