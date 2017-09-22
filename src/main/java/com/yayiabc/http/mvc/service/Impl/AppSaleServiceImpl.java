package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.VerifiCodeValidateUtil;
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
            ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(saleInfo.getPhone(),code);
            dataWrapper.setErrorCode(codeEnum);
            if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
                return dataWrapper;
            }
            saleInfo.setSaleId(UUID.randomUUID().toString());
            if (1 == saleLogDao.appRegister(saleInfo)) {
                VerifyCodeManager.removePhoneCodeByPhoneNum(saleInfo.getPhone());
                String token =tokenService.getSaleToken(saleInfo.getSaleId());
                dataWrapper.setToken(token);
            } else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Register_Error);
            }
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Username_Already_Exist);
        }
        return dataWrapper;
    }

}
