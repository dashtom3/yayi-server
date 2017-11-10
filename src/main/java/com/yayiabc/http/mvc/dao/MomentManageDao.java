package com.yayiabc.http.mvc.dao;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.Moment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MomentManageDao {

    void addLower(Moment moment);

    void addHigh(Moment moment);

    void deleteMoment(Integer momentId);


    int getMomentTotalNumber();

    List<Moment> queryList(Page page);


    Map<String,String> getMomentTitleByVedio(Integer momentContentId);

    Map<String,String> getMomentTitleByPost(Integer momentContentId);

    Map<String,String> getMomentTitleByTrain(Integer momentContentId);
}
