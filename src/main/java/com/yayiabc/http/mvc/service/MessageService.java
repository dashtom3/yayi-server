package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.MessageNumber;

public interface MessageService {
    DataWrapper<MessageNumber> getNumber(String token,String type);

    DataWrapper<Object> getDetail(String token, String type,Integer numberPerPage);
}
