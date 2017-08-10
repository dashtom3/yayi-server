package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.common.utils.MD5Util;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.WxAppDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.UserToken;
import com.yayiabc.http.mvc.service.WxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 *
 * Created by Jo on 2017/8/2.
 */
@Service
public class WxLoginServiceImpl implements WxLoginService {
    @Autowired
    WxAppDao wxAppDao;
    @Autowired
    UserDao userDao;
    @Autowired
    SaleLogDao saleLogDao;

    @Override
    public DataWrapper<Object> login(String code) {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();
        Map<String, String> appCode = wxAppDao.getAppCode();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" + "appid=" + appCode.get("appid") + "&secret=" + appCode.get("secret")
                + "&code=" + code + "&grant_type=authorization_code";
        Map<String, Object> response = HttpUtil.sendGet(url);
        assert response != null;
        String openid = (String) response.get("openid");
        assert openid != null;
        Map<String,String> userId = wxAppDao.getUser(openid);
        if (userId != null){
            String type = userId.get("type");
            if ("1".equals(type)){
                User user = userDao.getUserByUserId(userId.get("userId"));
                String token = getUserToken(userId.get("userId"));
                dataWrapper.setToken(token);
                dataWrapper.setData(user);
            }else if ("2".equals(type)){
                SaleInfo saleInfo = saleLogDao.getSaleInfoById(userId.get("userId"));
                String token = getSaleToken(userId.get("userId"));
                dataWrapper.setData(saleInfo);
                dataWrapper.setToken(token);
            }
        }else {
            dataWrapper.setData(openid);
            dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Object> bindUser(String phone, String verifyCode, String openid,String type) {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();
        if ("1".equals(type)){
            User user = new User();
            user.setPhone(phone);
            User seUser = userDao.getUserByUser(user);
            if (seUser != null) {
                String serverCode = VerifyCodeManager.getPhoneCode(phone);
                if (verifyCode.equals(serverCode)) {
                    dataWrapper.setData(seUser);
                    int num = userDao.getCartNum(seUser);
                    dataWrapper.setNum(num);
                    dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                    String userId = seUser.getUserId();
                    String token = getUserToken(userId);
                    dataWrapper.setToken(token);
                    wxAppDao.addUser(userId,openid);
                } else {
                    dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                }
            } else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            }
        }else if ("2".equals(type)){
            SaleInfo saleInfo = saleLogDao.getSaleInfoByPhone(phone);
            if (saleInfo != null) {
                String serverCode = VerifyCodeManager.getPhoneCode(phone);
                if (verifyCode.equals(serverCode)) {
                    dataWrapper.setData(saleInfo);
                    dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                    String saleId = saleInfo.getSaleId();
                    if (saleId != null) {
                        String token = getSaleToken(saleId);
                        dataWrapper.setToken(token);
                        wxAppDao.addSaleUser(saleId,openid);
                    }
                } else {
                    dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_Error);
                    dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
                }
            } else {
                dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
                dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
            }
        }
        return dataWrapper;
    }

    private String getUserToken(String userId) {
        String token = UUID.randomUUID().toString();
        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
        userToken.setToken(token);
        String oldToken = userDao.getTokenByUserId(userId);
        if (oldToken == null) {
            userDao.addToken(userToken);
        } else {
            userDao.updateToken(userToken);
        }
        new Timer().schedule(new TokenTask(token), 2 * 3600 * 1000);
        return token;
    }
    private String getSaleToken(String id) {
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

        TokenTask(String token) {
            this.token = token;
        }

        @Override
        public void run() {
            saleLogDao.deleteSaleToken(token);
        }
    }
}
