package com.yayiabc.http.mvc.controller.wexinli;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.dao.WxAppDao;
import com.yayiabc.http.mvc.pojo.jpa.Model;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.jpa.WXUserLink;
import com.yayiabc.http.mvc.pojo.model.UserToken;
import com.yayiabc.http.mvc.service.TokenService;

import net.sf.json.JSONObject;



/**
 * 类名: OAuthServlet </br>
 * 描述: 授权后的回调请求处理 </br>WXAuthentController
 * 开发人员： souvc </br>
 * 创建时间：  2015-11-27 </br>
 *  wxd342cb43ba1b1e6f
 * de7d6594c39476a0e45e8acf4bb6c9f7
 * 发布版本：V1.0  </br>
 */
@Controller
@RequestMapping("api/WXx")
public class WXAuthentController{

	@Autowired
	private WxAppDao wxAppDao;
	@Autowired
	private UtilsDao utilsDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SaleLogDao saleLogDao;
	@Autowired 
	private TokenService tokenService;
	static HashMap<String, String> hm=new HashMap<String,String>();
	@RequestMapping("returnSignAndMessage")
	@ResponseBody
	public DataWrapper<Object> doGet(HttpServletRequest request,
			@RequestParam(value="code",required=true) String code
			){
		// 用户同意授权后，能获取到code
		String appid=null;
		String secret=null;
		//String code = request.getParameter("code");
		// String state = request.getParameter("state");
		if(hm.isEmpty()){
			Map<String, String> appCode = wxAppDao.getAppCode();
			appid=appCode.get("appid");
			secret=appCode.get("secret");
			hm.put("appid", appid);
			hm.put("secret", secret);
		}else{
			appid=hm.get("appid");
			secret=hm.get("secret");
		}
		DataWrapper<Object> dataWrapper =new DataWrapper<Object>();
		// 用户同意授权
		User user=null;
		SNSUserInfo snsUserInfo=null;
		WXUserLink  wXUserLink=null;
		SaleInfo sa=null;
		// 获取网页授权access_token
		WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(code,appid,secret);
		// 网页授权接口访问凭证
		System.out.println("12312312312312i93879k7ikumk uyiyijsadbnaskdvasbh dasgjdasgjdvjasdgvjdadgvbasjdgvbyasjdgvjsad");
		if(weixinOauth2Token==null){
			dataWrapper.setMsg("code错误");
			System.out.println("code错误code错误code错误code错误code错误");
			return dataWrapper;
		}
		String accessToken = weixinOauth2Token.getAccessToken();
		// 用户标识
		String openId = weixinOauth2Token.getOpenId();
		
		String unionid=weixinOauth2Token.getUnionid();
		// 获取用户信息
		snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, unionid);
		snsUserInfo.setUnionid(unionid);
		snsUserInfo.setOpenId(unionid);
		//snsUserInfo.setOpenId(openId);
		//根据 openId 判断 该用户 是否绑定过
		wXUserLink=wxAppDao.queryIsBD(unionid); //这里数据库里的openid实际是 unionid，因为时间仓促 改动较大 这里便不更改了
		System.out.println("openId  openId   openId   "+openId);
		System.out.println("unionid  unionid   unionid   "+unionid);
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
				user=utilsDao.queryUserByUserId(wXUserLink.getUid());
				//判断该用户是否绑定销售员
				String token = getToken(wXUserLink.getUid());
				dataWrapper.setToken(token);
				if(user.getSaleId()!=null){
					SaleInfo saleInfo=wxAppDao.querySale(user.getSaleId());
					user.setSaleinfo(saleInfo);
					dataWrapper.setMsg("该用户的信息");
				}else{
					//未绑定销售员
				}
			}
			//销售员
			else{
				sa=utilsDao.getSaleBySaleId(wXUserLink.getUid());
				dataWrapper.setData(sa);
				dataWrapper.setMsg("该销售员信息");
				String saleToken = getSaleToken(wXUserLink.getUid());
				dataWrapper.setToken(saleToken);
			}
		}
		Model model=new Model();
		model.setwXUserLibk(wXUserLink);
		model.setUser(user);
		model.setSaleInfo(sa);
		model.setsNSUserInfo(snsUserInfo);
		snsUserInfo.setSign(sb.toString());
		dataWrapper.setData(model);
		return dataWrapper;
	}
	@RequestMapping("getUnionid")
	@ResponseBody
	public String getUnionid(String code){
		//通过code获取access_token
		String requestUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd342cb43ba1b1e6f&secret=de7d6594c39476a0e45e8acf4bb6c9f7&code="+code+"&grant_type=authorization_code";
		 Map<String, Object> response = HttpUtil.sendGet(requestUrl);
		 String accessToken=(String) response.get("access_token");
		 String openid=(String) response.get("openid");
		//通过access_token获取用户的所有信息
		 String getUnionUrl="https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openid+"";
		 JSONObject jsonObject = CommonUtil.httpsRequest(getUnionUrl, "GET", null);
		// String unionid=jsonObject.getString("unionid");
		 String jsonString =jsonObject.toString();
		 return jsonString;
	}
/*	@RequestMapping("getUnionIdByOpenId")
	@ResponseBody
	public String getUnionIdByOpenId(String openId){
		//通过code获取access_token
		String requestUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd342cb43ba1b1e6f&secret=de7d6594c39476a0e45e8acf4bb6c9f7&code="+code+"&grant_type=authorization_code";
		 Map<String, Object> response = HttpUtil.sendGet(requestUrl);
		 String accessToken=(String) response.get("access_token");
		 String openid=(String) response.get("openid");
		//通过access_token获取用户的所有信息
		 String getUnionUrl="https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openid+"";
		 JSONObject jsonObject = CommonUtil.httpsRequest(getUnionUrl, "GET", null);
		// String unionid=jsonObject.getString("unionid");
		 String jsonString =jsonObject.toString();
		 return jsonString;
	}*/
	//点击下一步    验证是否注册过
	@RequestMapping("check")
	@ResponseBody
	public DataWrapper<Object> check(
			@RequestParam(value="phone") String phone,
			@RequestParam(value="type")  String type
			){
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		//1是牙医
		if("1".equals(type)){
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

	private String getToken(String userId) {
		String token = tokenService.getToken(userId);
		UserToken userToken = new UserToken();
		userToken.setUserId(userId);
		userToken.setToken(token);
		return token;
	}

	

	private String getSaleToken(String id) {
		String token = tokenService.getSaleToken(id);
		return token;
	}

	@RequestMapping("getQRCode")
	@ResponseBody
	public DataWrapper<Object> getQRCode(){
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		dataWrapper.setData("https://open.weixin.qq.com/connect/qrconnect?appid=wxd342cb43ba1b1e6f&redirect_uri=http%3a%2f%2fwww.yayiabc.com&response_type=code&scope=snsapi_login&state=123#wechat_redirect");
		return dataWrapper;
	}
	@RequestMapping("appWXLoginCode")
	@ResponseBody
	public DataWrapper<Object> appWXLoginCode(){
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		dataWrapper.setData("http://www.yayiabc.com/get-weixin-code.html?appid=wx4b1a6fde77626a32&scope=snsapi_userinfo&redirect_uri=http%3a%2f%2fwww.yayiabc.com%3a100");
		return dataWrapper;
	}

	/**
	 * 第一步：用户同意授权，获取code(引导关注者打开如下页面：)
	 *  获取 code、state
	 */
	@RequestMapping("getStartURLToGetCode")
	@ResponseBody
	public static String getStartURLToGetCode() {
		String takenUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		takenUrl= takenUrl.replace("APPID", hm.get("appid"));
		try {
			takenUrl= takenUrl.replace("REDIRECT_URI",URLEncoder.encode("http://www.yayiabc.com:99/#/wx_user", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FIXME ： snsapi_userinfo
		takenUrl= takenUrl.replace("SCOPE", "snsapi_userinfo");
		System.out.println(takenUrl);
		return takenUrl;
	}
}