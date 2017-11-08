package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;

public interface ZanService {
    DataWrapper<Void> upvote(String token, Integer type, Integer typeId);
    long getZanNumber(Integer type,Integer typeId);
}
