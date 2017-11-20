package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.MessageNumber;

public interface MessageService {
    DataWrapper<MessageNumber> getNumber(String token);

    DataWrapper<Object> getDetail(String token, Integer type,Integer numberPerPage);
}
