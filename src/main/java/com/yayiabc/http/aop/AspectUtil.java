package com.yayiabc.http.aop;

import com.yayiabc.common.sessionManager.SessionManager;
import com.yayiabc.http.mvc.pojo.jpa.User;



/**
 * Created by XiaoJiang01 on 2017/4/27.
 */
public class AspectUtil {

    public static User getUser(Object[] param){
        String token = null;
        for (int i = 0; i <param.length ; i++) {
            if(param[i] != null && param[i].toString().length() > 1){
                if ("SK".equals(param[i].toString().substring(0,2))){
                    token = param[i].toString();
                    break;
                }
            }
        }
        User user = SessionManager.getSession(token);
        return user;
    }
}
