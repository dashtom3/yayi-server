package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.MD5Util;
import com.yayiabc.http.mvc.dao.AppSaleDao;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.AppSaleService;
import com.yayiabc.http.mvc.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by 小月亮 on 2017/9/4.
 */
@Service
public class AppSaleServiceImpl implements AppSaleService{
    @Autowired
    private AppSaleDao appSaleDao;
    @Autowired
    private SaleLogDao saleLogDao;
    @Autowired
    private TokenService tokenService;
    @Override
    public DataWrapper<Void> register(SaleInfo saleInfo,String code) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        SaleInfo saleInfoGet = saleLogDao.getSaleInfoByPhone(saleInfo.getPhone());
        if (saleInfoGet == null) {
            String serverCode = VerifyCodeManager.getPhoneCode(saleInfo.getPhone());
            if (serverCode.equals("noCode")) {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_notExist);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            } else if (serverCode.equals("overdue")) {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            } else if (serverCode.equals(code)) {
                saleInfo.setSaleId(UUID.randomUUID().toString());
                saleInfo.setSalePwd(MD5Util.getMD5String(saleInfo.getSalePwd()));
                Integer count=saleLogDao.getUserCount(saleInfo.getPhone());
                if(count==0){
                    if (1 == saleLogDao.appRegister(saleInfo)) {
                        VerifyCodeManager.removePhoneCodeByPhoneNum(saleInfo.getPhone());
                        String token =tokenService.getSaleToken(saleInfo.getSaleId());
                        dataWrapper.setToken(token);
                        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                        dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                    } else {
                        dataWrapper.setErrorCode(ErrorCodeEnum.Register_Error);
                        dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                    }
                }else{
                    dataWrapper.setErrorCode(ErrorCodeEnum.NO_Auth);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                }
            } else {
                System.out.println("code:" + code);
                System.out.println("VerifyCode:" + VerifyCodeManager.getPhoneCode(saleInfo.getPhone()));
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            }
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Username_Already_Exist);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        }
        return dataWrapper;
    }

}
