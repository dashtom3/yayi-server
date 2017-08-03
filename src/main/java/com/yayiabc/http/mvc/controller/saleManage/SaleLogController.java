package com.yayiabc.http.mvc.controller.saleManage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.CheckIsSignUtils;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.SaleLogService;

@Controller
@RequestMapping("api/saleLog")
public class SaleLogController {

    @Autowired
    SaleLogService saleLogService;
    @Autowired
    private UtilsDao utilsDao;
    // 获取验证码

    @RequestMapping("getVerifyCode")
    @ResponseBody
    public DataWrapper<Void> getVerifyCode(
            @RequestParam(value = "phone", required = true) String phone
    ) {
        return saleLogService.getVerifyCode(phone);
    }


    //用户注册

    @RequestMapping("register")
    @ResponseBody
    public DataWrapper<SaleInfo> register(
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "openid", required = false) String openid
    ) {
        return saleLogService.register(phone, password, code, openid);
    }


    //短信登录

    @RequestMapping("noteLogin")
    @ResponseBody
    public DataWrapper<SaleInfo> noteLogin(
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "code", required = true) String code,
            HttpServletRequest request
    ) {
        DataWrapper<SaleInfo> dauser = saleLogService.noteLogin(phone, code);
        SaleInfo SaleInfo = dauser.getData();
        if (SaleInfo != null) {
            String SaleId = SaleInfo.getSaleId();
            //根据userId查到当前用户的token
            String token = utilsDao.getSaleToken(SaleId);
            request.getSession().setAttribute("token", token);
        }
        return dauser;
        //return saleLogService.noteLogin(phone, code);
    }

    //密码登录
    @RequestMapping("pwdLogin")
    @ResponseBody
    public DataWrapper<SaleInfo> pwdLogin(
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "password", required = true) String password,
            HttpServletRequest request
    ) {
        DataWrapper<SaleInfo> dauser = saleLogService.pwdLogin(phone, password);
        SaleInfo SaleInfo = dauser.getData();
        if (SaleInfo != null) {
            String SaleId = SaleInfo.getSaleId();
            //根据userId查到当前用户的token
            String token = utilsDao.getSaleToken(SaleId);
            request.getSession().setAttribute("token", token);
        }
        return dauser;
        //return saleLogService.pwdLogin(phone, password);
    }

    //退出登录
    @RequestMapping("reLogin")
    @ResponseBody
    public DataWrapper<Void> reLogin(
            @RequestParam(value = "token", required = true) String token,
            HttpSession session
    ) {
        //清除session(手工杀会话)
        session.invalidate();
        //清除缓存中的 token
        CheckIsSignUtils.getInstance().getList().remove(token);
        return saleLogService.reLogin(token);
    }

    //忘记密码
    @RequestMapping("forgetPwd")
    @ResponseBody
    public DataWrapper<Void> forgetPwd(
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "password", required = true) String password
    ) {
        return saleLogService.forgetPwd(phone, code, password);
    }
}
