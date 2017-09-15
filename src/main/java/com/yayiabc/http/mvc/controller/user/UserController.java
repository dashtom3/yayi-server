package com.yayiabc.http.mvc.controller.user;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("api/user")
public class UserController {
	   /*
	    *  action="/api/user/register"
	    */
	@Autowired
	private UserService userService;
	
	//获取验证码
	@RequestMapping("getVerifyCode")
	@ResponseBody
	public DataWrapper<Void> getVerifyCode(@RequestParam(value = "phone", required = true) String phone) {
	        return userService.getVerifyCode(phone);
	}
	
	//用户注册
	@RequestMapping("register")
	@ResponseBody
	public DataWrapper<User> register(
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "openid", required = false) String openid
			){
		return userService.register(phone,password,code,openid);
	}
	
	
	
	//短信登录
	@RequestMapping("noteLogin")
	@ResponseBody
	public DataWrapper<User> noteLogin(
			@RequestParam(value = "phone", required = true) String phone ,
			@RequestParam(value = "code", required = true) String code
			){
		return userService.noteLogin(phone,code);

		
	}
	
	//密码登录
	@RequestMapping("pwdLogin")
	@ResponseBody
	public DataWrapper<User> pwdLogin(
			@RequestParam(value = "phone", required = true) String phone ,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request
			){
		DataWrapper<User> dauser=userService.pwdLogin(phone,password);
	
		return dauser;
	}
	
	//退出登录
	@RequestMapping("reLogin")
	@ResponseBody
	public DataWrapper<Void> reLogin(
			@RequestParam(value = "token", required = true) String token
			){
		//清除session(手工杀会话)
		//清除缓存中的 token 
		//String userId=utilsDao.getUserID(token);
		/*if(SessionManager.getSessionByUserID(userId)!=null){
			
		}*/
		return userService.reLogin(token);
	}
	
	//忘记密码
	@RequestMapping("forgetPwd")
	@ResponseBody
	public DataWrapper<Void> forgetPwd(
			@RequestParam(value = "phone", required = true) String phone ,
			@RequestParam(value = "code", required = true) String code ,
			@RequestParam(value = "password", required = true) String password
			){
		return userService.forgetPwd(phone,code,password);
	}
	//用户绑定销售员
	@RequestMapping(value = "bindSale",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> bindSale(
			@RequestParam("token") String token,
			@RequestParam("salePhone") String salePhone
	){
		return userService.bindSale(token,salePhone);
	}

	//彻底删除
	@RequestMapping(value = "deleteInGrainUser",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> deleteInGrainUser(
			//@RequestParam("token") String token,
			@RequestParam("userId") String userId
	){
		return userService.deleteInGrainUser(userId);
	}
}
