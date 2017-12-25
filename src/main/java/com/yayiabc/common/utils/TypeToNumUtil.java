package com.yayiabc.common.utils;

/**
 * 前端传字符串，后端将字符串转换成数字
 */
public class TypeToNumUtil {
    public static int typeToNum(String type){
        if(type==null||"".equals(type.trim())){
            return 0;
        }
        if("视频".equals(type)){
            return 1;
        }
        if("病例".equals(type)){
            return 2;
        }
        if("培训".equals(type)){
            return 3;
        }
        if("牙医圈".equals(type)){
            return 4;
        }
        return 0;
    }
}
