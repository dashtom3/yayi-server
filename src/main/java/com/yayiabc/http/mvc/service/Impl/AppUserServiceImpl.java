package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.MD5Util;
import com.yayiabc.http.mvc.dao.AppUserDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.AppUserService;
import com.yayiabc.http.mvc.service.TokenService;
import com.yayiabc.http.mvc.service.UserMyQbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by 小月亮 on 2017/8/29.
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserDao appUserDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMyQbService userMyQbService;
    @Autowired
    private TokenService tokenService;
    @Override
    public DataWrapper<Void> regiseter(User user,String code) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        if (userDao.getUserByPhone(user.getPhone()) == null) {
            String serverCode = VerifyCodeManager.getPhoneCode(user.getPhone());
            if (serverCode.equals("noCode")) {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_notExist);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            } else if (serverCode.equals("overdue")) {
                dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            } else if (serverCode.equals(code)) {
                user.setUserId(UUID.randomUUID().toString());
                user.setPwd(MD5Util.getMD5String(user.getPwd()));
                Integer count=userDao.getSaleCount(user.getPhone());
                if(count==0){
                    if (1 == userDao.register(user)) {
                        //绉婚櫎楠岃瘉鐮�
                        VerifyCodeManager.removePhoneCodeByPhoneNum(user.getPhone());
                        String token = tokenService.getToken(user.getUserId());
                        QbRecord qbRecord=new QbRecord();
                        qbRecord.setQbRget(60);
                        qbRecord.setRemark("注册送60乾币");
                        qbRecord.setQbType("qb_balance");
                        userMyQbService.add(qbRecord, token);
                        userDao.registerUserInfo(user);
                        userDao.registerUserCertification(user);
                        dataWrapper.setToken(token);
                        dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                        dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                    } else {
                        dataWrapper.setErrorCode(ErrorCodeEnum.Register_Error);
                        dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                    }
                }else{
                    dataWrapper.setErrorCode(ErrorCodeEnum.NO_POWER);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                }

            } else {
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
