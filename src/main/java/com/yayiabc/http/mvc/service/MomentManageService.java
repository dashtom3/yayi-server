package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Moment;

import java.util.List;

public interface MomentManageService {

    DataWrapper<Void> add(Moment moment,String token);

    DataWrapper<Void> delete(Integer momentId);

    DataWrapper<List<Moment>> queryList(Integer currentPage, Integer numberPerPage,String token);


    DataWrapper<List<Moment>> myMoment(Integer currentPage, Integer numberPerPage, String token);
}
