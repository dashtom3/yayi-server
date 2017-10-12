package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.MD5Util;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.utils.VerifiCodeValidateUtil;
import com.yayiabc.http.mvc.dao.AdminstratorDao;
import com.yayiabc.http.mvc.pojo.jpa.Adminstrator;
import com.yayiabc.http.mvc.service.AdminstratorService;
import com.yayiabc.http.mvc.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminstratorServiceImpl implements AdminstratorService {
    @Autowired
    private AdminstratorDao adminstratorDao;

    @Autowired
    private TokenService tokenService;

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
    public DataWrapper<List<Adminstrator>> queryAdminstrator(String phone, String trueName,Integer currentPage,Integer numberPerPage) {
        DataWrapper<List<Adminstrator>> dataWrapper = new DataWrapper<List<Adminstrator>>();
        if ("".equals(phone)) {
            phone = null;
        }
        if ("".equals(trueName)) {
            trueName = null;
        }
        Page page = new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        int totalNumber=adminstratorDao.getAdminstratorCount(phone,trueName);
        dataWrapper.setPage(page, totalNumber);
        List<Adminstrator> adminstratorList = adminstratorDao.queryAdminstrator(phone, trueName,page);
        dataWrapper.setData(adminstratorList);
        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> loginAdminstrator(String phone,
                                               String adminstratorPwd, String code) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        adminstratorPwd = MD5Util.getMD5String(adminstratorPwd);
        ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(phone,code);
        if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
            dataWrapper.setErrorCode(codeEnum);
            return dataWrapper;
        }
        System.out.println(adminstratorPwd +"           123123123              ");
            Adminstrator adminstrator = adminstratorDao.loginAdminstrator(phone, adminstratorPwd);
           System.err.println(adminstrator);
            if (adminstrator != null) {
                String token = tokenService.getAdminToken(adminstrator.getAdminstratorId());
                dataWrapper.setToken(token);
                dataWrapper.setNum(adminstrator.getState());
                dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
            } else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Login_Error);
            }
        return dataWrapper;
    }
}
