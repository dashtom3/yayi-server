package com.yayiabc.common.enums;

import java.io.Serializable;

/**
 * Created by TIANCHENGYUAN103 on 2015-12-04.
 */
public enum ErrorCodeEnum implements Serializable {

    No_Error("正确", 0),
    Error("服务器错误", 1),
    Login_Error("用户名或者密码错误",2),
    Username_Already_Exist("用户名已存在",3),
    Register_Error("注册失败",4),
    Verify_Code_Error("验证码错误",5),
    Verify_Code_5min("验证码5分钟",6),
    Verify_Code_notExist("验证码不存在",7),
    Username_NOT_Exist("用户不存在",8),
    Password_error("密码错误",9),
    Limitation_error("无操作权限",10),
    NO_MESSAGE("没有查找到商品",11),
    RE_LOGIN("请重新登录",12),
    ITEM_LEAVE("该分类下有商品,不能删除",13);

    private String label;
    private Integer code;

    ErrorCodeEnum() {
    }

    ErrorCodeEnum(String label, Integer code) {
        this.label = label;
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return label;
    }

    public static ErrorCodeEnum parse(int code) {
        for (ErrorCodeEnum theEnum : ErrorCodeEnum.values()) {
            if (theEnum.getCode() == code) {
                return theEnum;
            }
        }
        return No_Error;
    }
}
