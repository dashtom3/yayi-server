package com.yayiabc.http.mvc.dao;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.FaqQuestion;
import com.yayiabc.http.mvc.pojo.jpa.VidManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectDao {
    int getMyCollectTotalNo(@Param("userId") String userId,@Param("type") String type,@Param("category")Integer category);

    List<VidManage> getMyCollectVideoList(@Param("page")Page page,@Param("userId") String userId,@Param("type")String type,@Param("category")Integer category);

    List<FaqQuestion> getMyCollectFaqList(@Param("page")Page page,@Param("userId") String userId,@Param("type")String type,@Param("category")Integer category);

    Integer getCategoryFromPost(Integer postId);

    Integer getCategoryFromVideo(Integer postId);

    Integer getCategoryFromFaq(Integer postId);
}
