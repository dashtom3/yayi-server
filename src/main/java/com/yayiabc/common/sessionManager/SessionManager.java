package com.yayiabc.common.sessionManager;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.yayiabc.http.mvc.pojo.jpa.User;

/**
 * Created by tian on 16/9/27.
 */
public class SessionManager {
    private static HashMap<String, User> USER_SESSION_MAP = new HashMap<String, User>();

    public static String newSession(User user) {
        String sessionKey = UUID.randomUUID().toString();
        USER_SESSION_MAP.put(sessionKey, user);
        return sessionKey;
    }

    public static User getSession(String key) {  //叫做getUser  更合理
        if(USER_SESSION_MAP.containsKey(key)) {
            return USER_SESSION_MAP.get(key);
        } else {
            return null;
        }
    }
    public static String getSessionByUserID(String userId){
        Set<String> set = USER_SESSION_MAP.keySet();
        for(String key :set){
            if(USER_SESSION_MAP.get(key).getUserId().equals(userId)) {
                return key;
            }
        }
        return null;
    }
    public static void removeSession(String key) {
        if (USER_SESSION_MAP.containsKey(key)) {
            USER_SESSION_MAP.remove(key);
        }
    }

    /**
     * 删除某用户的Session
     * @param userId
     */
    public static void removeSessionByUserId(String userId) {
        Iterator<Map.Entry<String, User>> iter = USER_SESSION_MAP.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, User> entry =  iter.next();
            String key = entry.getKey();
            User value = entry.getValue();
            if (value!=null && value.getUserId().equals(userId)) {
                removeSession(key);
                break;
            }
        }
    }
}
