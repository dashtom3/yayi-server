package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Moment;

import java.util.List;

public interface MomentManageService {

    DataWrapper<Void> add(Moment moment,String token);

    DataWrapper<Void> delete(Integer momentId);


    DataWrapper<Moment> detail(Integer momentId, String token);

    DataWrapper<List<Moment>> getMommentList(Integer currentPage, Integer numberPerPage,String token,Integer type);
}
