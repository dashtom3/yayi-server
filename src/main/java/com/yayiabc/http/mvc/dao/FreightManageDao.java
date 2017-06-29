package com.yayiabc.http.mvc.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.PostFee;
import com.yayiabc.http.mvc.pojo.jpa.FreeShipping;

public interface FreightManageDao {
	//显示运费
   List<PostFee> showFreight();
   //自定义运费
   int customFreight(PostFee postFee);
    int deleteCustomFreight(@Param("postFeeId")Integer postFeeId);
    //包邮
    List<FreeShipping> showFreeShipp();
    //add
    int insertFreeShipp(@Param("freeShipping")FreeShipping f);
    //update
    int updateFreeShipp(FreeShipping FreeShipping);
}
