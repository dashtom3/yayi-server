package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.MD5Util;
import com.yayiabc.common.utils.VerifiCodeValidateUtil;
import com.yayiabc.http.mvc.dao.AppUserDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.pojo.jpa.Certification;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.AppUserService;
import com.yayiabc.http.mvc.service.TokenService;
import com.yayiabc.http.mvc.service.UserMyQbService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public DataWrapper<User> regiseter(User user,String code,String id,Integer userType) {
        DataWrapper<User> dataWrapper = new DataWrapper<User>();
        Certification certification= user.getCertification();
        if (userDao.getUserByPhone(user.getPhone()) == null) {
            ErrorCodeEnum codeEnum= VerifiCodeValidateUtil.verifiCodeValidate(user.getPhone(),code);
            dataWrapper.setErrorCode(codeEnum);
            if(!codeEnum.equals(ErrorCodeEnum.No_Error)){
                return dataWrapper;
            }
            user.setPwd(MD5Util.getMD5String(user.getPwd()));
            if (1 == userDao.register(user)) {
            	certification.setUserId(user.getUserId());
            	userDao.register1(certification);
                VerifyCodeManager.removePhoneCodeByPhoneNum(user.getPhone());
                String token = tokenService.getToken(user.getUserId());
                userDao.registerUserInfo(user);
                userDao.registerUserCertification(user);
                user.setCreated(new Date());
                dataWrapper.setData(user);
                String byid=user.getUserId();
                /**
                 * 注册赠送乾币
                 */
                QbRecord qbRecord=new QbRecord();
                qbRecord.setQbRget(60+"");
                qbRecord.setRemark("注册赠送60乾币");
                qbRecord.setQbType("qb_notwith");
                userMyQbService.add(qbRecord, token);
                
				if(userType==1){
					//赠送邀请人乾币
					userDao.presented(id);

					//记录邀请人和被邀请人数据
					userDao.addUser(id,byid);


				}else if(userType==2){
					int i = userDao.bindSale(byid,id+"");
				}
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
