package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.http.mvc.dao.AdminstratorDao;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.pojo.model.AdminstratorToken;
import com.yayiabc.http.mvc.pojo.model.UserToken;
import com.yayiabc.http.mvc.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by 小月亮 on 2017/8/29.
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SaleLogDao saleLogDao;
    @Autowired
    private AdminstratorDao adminstratorDao;

    @Override
    public String getToken(String userId) {
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
        return token;
    }

    @Override
    public String getSaleToken(String id) {
        String token = UUID.randomUUID().toString();
        String oldToken = saleLogDao.getTokenBySaleId(id);
        if (oldToken == null) {
            saleLogDao.addSaleToken(id, token);
        } else {
            saleLogDao.updateSaleToken(id, token);
        }
        return token;
    }

    @Override
    public String getAdminToken(Integer adminId) {
        String token = UUID.randomUUID().toString();//生成管理员token
        AdminstratorToken adminstratorToken = new AdminstratorToken();
        adminstratorToken.setAdminstratorId(adminId);
        adminstratorToken.setAdminstratorToken(token);
        String oldToken = adminstratorDao.getAdminstratorTokenByAdminstratorId(adminId);
        if (oldToken == null) {
            adminstratorDao.addAdminstratorToken(adminstratorToken);
        } else {
            adminstratorDao.updateAdminstratorToken(adminstratorToken);
        }
        return token;
    }
}
