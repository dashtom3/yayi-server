package com.yayiabc.http.mvc.controller.wexinli;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.common.utils.WX;
import com.yayiabc.http.mvc.dao.WxAppDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class AdvancedUtil {
	/**
     * 获取网页授权凭证
     * 
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
	 * @param secret 
	 * @param appid 
     * @return WeixinAouth2Token
     */
    public static   WeixinOauth2Token getOauth2AccessToken(String code, String appid, String secret) {
        WeixinOauth2Token wat = null;
        // 拼接请求地址
 
       System.out.println("codecodecodecodecodecodecodecode:::::::::::::"+code);
      String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
       requestUrl = requestUrl.replace("APPID", appid);
        requestUrl = requestUrl.replace("SECRET", secret);
        requestUrl = requestUrl.replace("CODE", code);
        System.out.println(requestUrl);
        // 获取网页授权凭证
        Map<String, Object> response = HttpUtil.sendGet(requestUrl);
        for(String key:response.keySet()){
        	System.out.println("key:"+key +"  value:"+response.get(key));
        }
        //JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (!response.isEmpty()) {
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken((String)response.get("access_token"));
                wat.setExpiresIn(  (Integer)response.get("expires_in"));
                wat.setRefreshToken((String)response.get("refresh_token"));
                wat.setOpenId((String)response.get("openid"));
                wat.setScope((String)response.get("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = (int) response.get("errcode");
                String errorMsg = (String) response.get("errmsg");
                System.out.println("获取网页授权凭证失败 errcode:{} errmsg:{}"+errorCode+errorMsg);
               /* log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);*/
            }
        }
       /* DataWrapper<WeixinOauth2Token> dataWrapper=new DataWrapper<WeixinOauth2Token>();
        dataWrapper.setData(wat);*/
        return wat;
    }
    /**
     * 通过网页授权获取用户信息
     * 
     * @param accessToken 网页授权接口调用凭证
     * @param openId 用户标识
     * @return SNSUserInfo
     */
	/*@RequestMapping("api/getUserInfo")
	@ResponseBody*/
    public static SNSUserInfo   getSNSUserInfo(String accessToken, String openId) {
        SNSUserInfo snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                dataWrapper.setData(errorCode+" "+errorMsg);
                System.out.println("获取用户信息失败 errcode:{} errmsg:{}"+errorCode+errorMsg);
            }
        }
       
        /*dataWrapper.setData(snsUserInfo);*/
        return snsUserInfo;
    }
}
