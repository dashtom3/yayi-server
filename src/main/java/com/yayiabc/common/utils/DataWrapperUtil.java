package com.yayiabc.common.utils;

import com.yayiabc.common.enums.ErrorCodeEnum;

/**
 * Created by 小月亮 on 2017/9/15.
 */
public class DataWrapperUtil {

    public static DataWrapper<Object> oKModelOne(ErrorCodeEnum errorCodeEnum, Object data, String token, Integer num, String msg, Page page, Integer totalNumber){
        return new DataWrapper(errorCodeEnum,data,token,num,msg,page,totalNumber);
    }
}
