package com.yayiabc.http.mvc.dao;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.Moment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MomentManageDao {

    void addLower(Moment moment);

    void addHigh(Moment moment);

    void deleteMoment(Integer momentId);

    void deleteMomentComment(Integer momentId);

    int getMomentTotalNumber();

    List<Moment> queryList(Page page);




}
