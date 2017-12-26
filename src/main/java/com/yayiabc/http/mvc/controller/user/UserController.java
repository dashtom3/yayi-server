package com.yayiabc.http.mvc.controller.user;

import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.UserService;

import okhttp3.MultipartBody.Part;

import org.bouncycastle.crypto.digests.Blake2bDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public DataWrapper<Void> getVerifyCode(
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value="type",required = false) Integer type
			) {
	        return userService.getVerifyCode(phone,type);
	}
	
	//用户注册
	@RequestMapping("register")
	@ResponseBody
	public DataWrapper<User> register(
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value="trueName",required = true)String trueName,
			@RequestParam(value="companyName",required=true)String companyName,
			@RequestParam(value="part",required=true)String part,
			@RequestParam(value="workAddress",required=true) String workAddress,
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "openid", required = false) String openid,
			@RequestParam(value = "id",required=false) Integer id,
			@RequestParam(value = "userType",required=true) Integer userType
			){
		return userService.register(phone,password,trueName,companyName,part,workAddress,code,openid,id,userType);
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
	@AdminTokenValidate
	public DataWrapper<Void> deleteInGrainUser(
			@RequestHeader("adminToken") String adminToken,
			@RequestParam("userId") Integer userId
	){
		return userService.deleteInGrainUser(userId);
	}
	
	//获取邀请列表
	@RequestMapping(value="inviteChart",method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<List<User>> inviterChart(
			@RequestHeader(value="token",required=true) String token,
			@RequestParam(value="userType")Integer userType,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
			){
		return userService.inviterChart(token,userType,currentPage,numberPerPage);
	}
}
