package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.common.utils.MD5Util;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.model.UserToken;
import com.yayiabc.http.mvc.service.SaleLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Service
public class SaleLogServiceImpl implements SaleLogService {

    @Autowired
    private SaleLogDao saleLogDao;

    @Override
    public DataWrapper<Void> getVerifyCode(String phone) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        String code = VerifyCodeManager.newPhoneCode(phone);
        if (code == null) {
            dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            return dataWrapper;
        }
        boolean result = HttpUtil.sendPhoneVerifyCode(code, phone);
        if (result) {
            dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        } else {
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<SaleInfo> register(String phone, String password,
                                          String code) {
        DataWrapper<SaleInfo> dataWrapper = new DataWrapper<SaleInfo>();
        SaleInfo saleInfo = saleLogDao.getSaleInfoByPhone(phone);
        if (saleInfo == null) {
            String serverCode = VerifyCodeManager.getPhoneCode(phone);
            if (serverCode.equals("noCode")) {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_notExist);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            } else if (serverCode.equals("overdue")) {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            } else if (serverCode.equals(code)) {
                SaleInfo saleInfoTwo = new SaleInfo();
                saleInfoTwo.setSaleId(UUID.randomUUID().toString());
                saleInfoTwo.setPhone(phone);
                saleInfoTwo.setSalePwd(MD5Util.getMD5String(password));
                saleInfoTwo.setUpdated(new Date());
                saleInfoTwo.setCreated(new Date());

                if (1 == saleLogDao.register(saleInfoTwo)) {
                    VerifyCodeManager.removePhoneCodeByPhoneNum(phone);
                    String token = getToken(saleInfo.getSaleId());
                    dataWrapper.setToken(token);
                    dataWrapper.setData(saleInfoTwo);
                    dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                } else {
                    dataWrapper.setErrorCode(ErrorCodeEnum.Register_Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                }
            } else {
                System.out.println("code:" + code);
                System.out.println("VerifyCode:" + VerifyCodeManager.getPhoneCode(phone));
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
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
            String serverCode = VerifyCodeManager.getPhoneCode(phone);
            if (serverCode.equals("noCode")) {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_notExist);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            } else if (serverCode.equals("overdue")) {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            } else if (serverCode.equals(code)) {
                dataWrapper.setData(saleInfo);
                dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                String saleToken = getToken(saleInfo.getSaleId());
                dataWrapper.setToken(saleToken);
            } else {
                System.out.println("code:" + code);
                System.out.println("VerifyCode:" + VerifyCodeManager.getPhoneCode(phone));
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            }
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
            String serverCode = VerifyCodeManager.getPhoneCode(phone);
            if (serverCode.equals("noCode")) {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_notExist);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            } else if (serverCode.equals("overdue")) {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            } else if (serverCode.equals(code)) {
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
                System.out.println("code:" + code);
                System.out.println("VerifyCode:" + VerifyCodeManager.getPhoneCode(phone));
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
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
        new Timer().schedule(new TokenTask(token), 2 * 3600 * 1000);
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
