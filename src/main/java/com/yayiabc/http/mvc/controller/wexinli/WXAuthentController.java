package com.yayiabc.http.mvc.controller.wexinli;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.dao.WxAppDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.jpa.WXUserLink;
import com.yayiabc.http.mvc.pojo.model.SaleU;



/**
* 类名: OAuthServlet </br>
* 描述: 授权后的回调请求处理 </br>WXAuthentController
* 开发人员： souvc </br>
* 创建时间：  2015-11-27 </br>
* 发布版本：V1.0  </br>
 */
@Controller
@RequestMapping("api/WXx")
public class WXAuthentController{

	@Autowired
	private WxAppDao wxAppDao;
	@Autowired
	private UtilsDao utilsDao;
	@RequestMapping("returnSignAndMessage")
	@ResponseBody
    public DataWrapper<Object> doGet(HttpServletRequest request,
    		@RequestParam(value="code",required=false) String code
    		){
        // 用户同意授权后，能获取到code
		
        //String code = request.getParameter("code");
       // String state = request.getParameter("state");
        DataWrapper<Object> dataWrapper =new DataWrapper<Object>();
        // 用户同意授权
            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(code);
            // 网页授权接口访问凭证
            if(weixinOauth2Token!=null){
            	System.out.println(weixinOauth2Token);
            }
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();
            // 获取用户信息
            SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
            snsUserInfo.setOpenId(openId);
            System.out.println(snsUserInfo);
         //根据 openId 判断 该用户 是否绑定过
            WXUserLink wXUserLink=wxAppDao.queryIsBD(openId);
            StringBuffer sb=new StringBuffer();
           if(wXUserLink==null){
        	   sb.append(0+"");
        	   dataWrapper.setMsg("未绑定手机号");
            }else{
            	 sb.append(1+" ");
            	 dataWrapper.setMsg("已绑定手机号");
            	 //根据uid  根据 type查 绑定手机号  与 绑定的客户代表
            	 //牙医
            	 if("1".equals(wXUserLink.getType())){
            		  //根据  uid 到用户表查 phone name  select true_name,phone,sale_id from user where user_id=#{uid}
            		User user=utilsDao.queryUserByUserId(wXUserLink.getUid());
            		//判断该用户是否绑定销售员
            		if(user.getSaleId()!=null){
            			 SaleInfo saleInfo=wxAppDao.querySale(user.getSaleId());
            			 user.setSaleinfo(saleInfo);
            			 System.out.println("user  suer suer usreuser"+user);
            			 dataWrapper.setMsg("该用户的信息");
            		}else{
            			//未绑定销售员
            		}
            	 }
            	 //销售员
            	 else{
            		 SaleInfo sa=utilsDao.getSaleBySaleId(wXUserLink.getUid());
            		 dataWrapper.setData(sa);
            		 dataWrapper.setMsg("该销售员信息");
            	 }
            }
           snsUserInfo.setSign(sb.toString());
           dataWrapper.setData(snsUserInfo);
        return dataWrapper;
    }
	//点击下一步    验证是否注册过
    @RequestMapping("check")
    @ResponseBody
    public DataWrapper<Object> check(
    			@RequestParam(value="phone") String phone,
    			@RequestParam(value="type")  String type
     		){
    	DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
    	//1是牙医
    	 if(type.equals("1")){
    		 //到user表示查phone 是否存在
    		User user= utilsDao.queryUserByPhone(phone);
    		if(user==null){
    			dataWrapper.setData("该用户还没注册"); 	
    			return dataWrapper;
    		}else{
    			//检查该用户是否绑定了销售员
    			if(user.getSaleId()!=null){
    				SaleInfo sale=utilsDao.getSaleBySaleId(user.getSaleId());
    				user.setSaleinfo(sale);
    				dataWrapper.setData(user);
    			}
    			return dataWrapper;
    		}
    	 }
    	 //销售员
    	 else{
    		 //到sale_info里查 phone是否存在
    		 SaleInfo sale=utilsDao.getSaleByPhone(phone);
    		 if(sale==null){
     			dataWrapper.setData("该准销售员还没注册"); 
     			return dataWrapper;
     		}else{
     			dataWrapper.setData(sale); 
     			return dataWrapper;
     		}
    	 }
    }
}