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
//    Verify_Code_5min("验证码5分钟",6),
    Verify_Code_5min("验证码10分钟",6),
    Verify_Code_notExist("验证码不存在",7),
    Username_NOT_Exist("用户不存在",8),
    Password_error("密码错误",9),
    Limitation_error("无操作权限",10),
    NO_MESSAGE("没有查找到商品",11),
    RE_LOGIN("请重新登录",12),
    RE_LOGIN_SALE("请重新登录",16),
    RE_LOGIN_ADMIN("请重新登录",17),
    ITEM_LEAVE("该分类下有商品,不能删除",13),
    NO_POWER("已成为销售员,不能再注册成为客户",14),
    NO_Auth("已成为客户,不能再注册成为销售员",15),
    PROPERTY_ALREADY_EXIST("该属性名已经存在,请不要重复添加",15),
	CAN_CEL("取消订单失败,原因库存未全部还原",16),
	QBDED_Error("取消订单失败,原因库存未全部还原",17),
	ITEMSTOCK_Error("取消订单失败,原因库存未全部还原",18),
	ORDER_ERROR("订单错误",19),
	OPENID_NOT_EXIST("openid不存在",20),
	REFUND_ERROR("退款错误",21),
	INSUFFICIENT_BALANCE_ERROR("余额不足",22),
    OPERATION_ERROR("操作错误",50);
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
