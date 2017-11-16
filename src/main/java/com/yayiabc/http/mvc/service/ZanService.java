package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;

public interface ZanService {
    DataWrapper<Void> upvote(String token, String type, Integer typeId,Integer parentId,Integer presentId,String classify);
    int getZanNumber(String type,Integer typeId,Integer parentId,Integer presentId);
}
