package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.MD5Util;
import com.yayiabc.http.mvc.dao.AdminstratorDao;
import com.yayiabc.http.mvc.pojo.jpa.Adminstrator;
import com.yayiabc.http.mvc.pojo.model.AdminstratorToken;
import com.yayiabc.http.mvc.service.AdminstratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Service
public class AdminstratorServiceImpl implements AdminstratorService {
    @Autowired
    private AdminstratorDao adminstratorDao;

    @Override
    public DataWrapper<Void> addAdminstrator(String phone,
                                             String adminstratorPwd, String trueName) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        Adminstrator adminstrator = new Adminstrator();
        adminstrator.setPhone(phone);
        adminstrator.setTrueName(trueName);
        adminstratorPwd = MD5Util.getMD5String(adminstratorPwd);
        adminstrator.setAdminstratorPwd(adminstratorPwd);
        adminstrator.setState(1);//1代表普通管理员2代表超级管理员
        adminstratorDao.addAdminstrator(adminstrator);

        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> deleteAdminstrator(Integer adminstratorId) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        adminstratorDao.deleteAdminstrator(adminstratorId);
        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> updateAdminstrator(Integer adminstratorId,
                                                String phone, String adminstratorPwd, String trueName) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        Adminstrator adminstrator = new Adminstrator();
        adminstrator.setAdminstratorId(adminstratorId);
        adminstrator.setPhone(phone);
        adminstrator.setTrueName(trueName);
        adminstratorPwd = MD5Util.getMD5String(adminstratorPwd);
        adminstrator.setAdminstratorPwd(adminstratorPwd);
        adminstratorDao.updateAdminstrator(adminstrator);
        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<Adminstrator>> queryAdminstrator(String phone,
                                                             String trueName) {
        DataWrapper<List<Adminstrator>> dataWrapper = new DataWrapper<List<Adminstrator>>();
        if ("".equals(phone)) {
            phone = null;
        }
        if ("".equals(trueName)) {
            trueName = null;
        }
        List<Adminstrator> adminstratorList = adminstratorDao.queryAdminstrator(phone, trueName);
        dataWrapper.setData(adminstratorList);
        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> loginAdminstrator(String phone,
                                               String adminstratorPwd, String code) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        adminstratorPwd = MD5Util.getMD5String(adminstratorPwd);
        String serverCode = VerifyCodeManager.getPhoneCode(phone);
        if (serverCode.equals("noCode")) {
            dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_notExist);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        } else if (serverCode.equals("overdue")) {
            dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        } else if (serverCode.equals(code)) {
            Adminstrator adminstrator = adminstratorDao.loginAdminstrator(phone, adminstratorPwd);
            if (adminstrator != null) {
                String token = getToken(adminstrator.getAdminstratorId());
                dataWrapper.setToken(token);
                dataWrapper.setNum(adminstrator.getState());
                dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                String msg = dataWrapper.getErrorCode().getLabel();
                dataWrapper.setMsg(msg);
            } else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Login_Error);
                String msg = dataWrapper.getErrorCode().getLabel();
                dataWrapper.setMsg(msg);
            }
        } else {
            System.out.println("code:" + code);
            System.out.println("VerifyCode:" + VerifyCodeManager.getPhoneCode(phone));
            dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
            dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
        }
        return dataWrapper;
    }

    private String getToken(int id) {
        String token = UUID.randomUUID().toString();//生成管理员token
        AdminstratorToken adminstratorToken = new AdminstratorToken();
        adminstratorToken.setAdminstratorId(id);
        adminstratorToken.setAdminstratorToken(token);
        String oldToken = adminstratorDao.getAdminstratorTokenByAdminstratorId(id);
        if (oldToken == null) {
            adminstratorDao.addAdminstratorToken(adminstratorToken);
        } else {
            adminstratorDao.updateAdminstratorToken(adminstratorToken);
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
            adminstratorDao.deleteAdminstratorToken(token);
        }
    }
}
