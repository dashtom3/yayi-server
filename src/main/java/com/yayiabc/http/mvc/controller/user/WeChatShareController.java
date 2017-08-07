package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminLog;
import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.annotation.UserLog;
import com.yayiabc.common.help.WeinXinUtil;
import com.yayiabc.common.help.WinXinEntity;
import com.yayiabc.common.sdk.LogisticsMain;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.http.mvc.pojo.jpa.AdvChart;
import com.yayiabc.http.mvc.service.AdvManageService;

@Controller
@RequestMapping("api/share")
public class WeChatShareController {
   /* @Autowired
    private WeChatShareService weChatShareService;*/
    //show
    @RequestMapping("showAdv")
    @ResponseBody
   // @AdminTokenValidate(description="显示广告设置内容")
   
    public DataWrapper<WinXinEntity> showAdv(
    		HttpServletRequest request
    		){
    	
    	
    	  //微信分享
        String strUrl = "http://www.xxxxx.com"
                + request.getContextPath()   //项目名称  
                + request.getServletPath()   //请求页面或其他地址  
                + "?" + (request.getQueryString()); //参数  
        WinXinEntity wx = WeinXinUtil.getWinXinEntity(strUrl);
        //将wx的信息到给页面
        request.setAttribute("wx", wx);
       DataWrapper<WinXinEntity> da= new DataWrapper<WinXinEntity>();
       da.setData(wx);
    	return da  ;
    }
   
}
