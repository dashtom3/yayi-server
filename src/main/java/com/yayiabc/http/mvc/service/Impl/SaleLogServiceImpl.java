package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.MD5Util;
import com.yayiabc.common.utils.VerifiCodeValidateUtil;
import com.yayiabc.http.mvc.dao.SaleInfoDao;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.dao.WxAppDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.SaleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.TimerTask;
import java.util.UUID;

@Service
public class SaleLogServiceImpl implements SaleLogService {

    @Autowired
    private SaleLogDao saleLogDao;
    @Autowired
    WxAppDao wxAppDao;
    @Autowired
    SaleInfoDao saleInfoDao;


    @Override
    public DataWrapper<SaleInfo> register(SaleInfo sale,String password,
                                          String code, String openid) {
        DataWrapper<SaleInfo> dataWrapper = new DataWrapper<SaleInfo>();
        SaleInfo saleInfo = saleLogDao.getSaleInfoByPhone(sale.getPhone());
        if (saleInfo == null) {
            ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(sale.getPhone(),code);
            if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
                dataWrapper.setErrorCode(codeEnum);
                return dataWrapper;
            }
                SaleInfo saleInfoTwo = new SaleInfo();
                saleInfoTwo.setSaleId(UUID.randomUUID().toString());
                saleInfoTwo.setPhone(sale.getPhone());
                saleInfoTwo.setSalePwd(MD5Util.getMD5String(password));
                saleInfoTwo.setUpdated(new Date());
                saleInfoTwo.setCreated(new Date());
                if (1 == saleLogDao.register(saleInfoTwo)) {
                    VerifyCodeManager.removePhoneCodeByPhoneNum(sale.getPhone());
                    String token = getToken(saleInfoTwo.getSaleId());
                    if (openid != null) {
                        wxAppDao.addSaleUser(saleInfoTwo.getSaleId(), openid, saleInfoTwo.getPhone());
                    }
                    dataWrapper.setToken(token);
                    saleInfoDao.updateSaleInfo(sale);
                    dataWrapper.setData(sale);
                    dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                    } else {
                    dataWrapper.setErrorCode(ErrorCodeEnum.Register_Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                }
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Username_Already_Exist);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<SaleInfo> noteLogin(String phone, String code) {
        DataWrapper<SaleInfo> dataWrapper = new DataWrapper<SaleInfo>();
        SaleInfo saleInfo = saleLogDao.getSaleInfoByPhone(phone);
        if (saleInfo != null) {
            ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(phone,code);
            if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
                dataWrapper.setErrorCode(codeEnum);
                return dataWrapper;
            }
                dataWrapper.setData(saleInfo);
                dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                String saleToken = getToken(saleInfo.getSaleId());
                dataWrapper.setToken(saleToken);
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<SaleInfo> pwdLogin(String phone, String password) {
        DataWrapper<SaleInfo> dataWrapper = new DataWrapper<SaleInfo>();
        SaleInfo saleInfo = saleLogDao.getSaleInfoByPhone(phone);
        String saleToken = null;
        if (saleInfo != null) {
            if (MD5Util.getMD5String(password).equals(saleInfo.getSalePwd())) {
                dataWrapper.setData(saleInfo);
                dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                String saleId = saleInfo.getSaleId();
                if (saleId != null) {
                    saleToken = getToken(saleId);
                }
            } else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Password_error);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            }
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        }
        dataWrapper.setToken(saleToken);
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> reLogin(String token) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        saleLogDao.deleteSaleToken(token);
        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> forgetPwd(String phone, String code,
                                       String password) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        SaleInfo saleInfo = saleLogDao.getSaleInfoByPhone(phone);
        if (saleInfo != null) {
            ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(phone,code);
            if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
                dataWrapper.setErrorCode(codeEnum);
                return dataWrapper;
            }
                password = MD5Util.getMD5String(password);
                try {
                    saleLogDao.updatePwd(password, saleInfo.getSaleId());
                    dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                } catch (Exception e) {
                    e.printStackTrace();
                    dataWrapper.setErrorCode(ErrorCodeEnum.Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                }
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        }
        return dataWrapper;
    }

    private String getToken(String id) {
        String token = UUID.randomUUID().toString();
        String oldToken = saleLogDao.getTokenBySaleId(id);
        if (oldToken == null) {
            saleLogDao.addSaleToken(id, token);
        } else {
            saleLogDao.updateSaleToken(id, token);
        }
//        new Timer().schedule(new TokenTask(token), 2 * 3600 * 1000);
        return token;
    }

    private class TokenTask extends TimerTask {
        private String token;

        public TokenTask(String token) {
            this.token = token;
        }

        @Override
        public void run() {
            saleLogDao.deleteSaleToken(token);
        }
    }
}
