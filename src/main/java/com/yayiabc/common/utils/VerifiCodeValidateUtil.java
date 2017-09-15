package com.yayiabc.common.utils;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;

/**
 * Created by 小月亮 on 2017/9/12.
 */
public class VerifiCodeValidateUtil {
    public static ErrorCodeEnum verifiCodeValidate(String phone, String code){
        String serverCode = VerifyCodeManager.getPhoneCode(phone);
        if (serverCode.equals("noCode")) {
            return ErrorCodeEnum.Verify_Code_notExist;
        } else if (serverCode.equals("overdue")) {
            return ErrorCodeEnum.Verify_Code_5min;
        } else if (serverCode.equals(code)) {
            return ErrorCodeEnum.No_Error;
        }else {
           return ErrorCodeEnum.Verify_Code_Error;
        }
    }
}
