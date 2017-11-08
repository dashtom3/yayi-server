package com.yayiabc.http.mvc.controller.train;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.yayiabc.common.alipayenclos.config.AlipayConfig;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.http.mvc.pojo.jpa.Train;
import com.yayiabc.http.mvc.pojo.jpa.TrainDetail;
import com.yayiabc.http.mvc.pojo.jpa.TrainOrdera;
import com.yayiabc.http.mvc.service.TrainShowService;

@Controller
@RequestMapping("api/train")
public class TrainShowController {
    @Autowired
   private TrainShowService trainShowService;
    //show
    /*
     * 培训列表的显示
     */
    @RequestMapping("show")
    @ResponseBody
    public DataWrapper<List<Train>> show(
    		@RequestParam(value="classly",required=false)String classly,
    		@RequestParam(value="currentPage",required=false,defaultValue="1")Integer currentPage,
   		    @RequestParam(value="numberPerpage",required=false,defaultValue="3")Integer numberPerpage
    		){
    	return trainShowService.show(classly,currentPage,numberPerpage);
    }
    /**
     * 培训详情
     * @param trainId
     * @return
     */
    @RequestMapping("trainDetails")
    @ResponseBody
    public DataWrapper<TrainDetail> trainDetails(
    		@RequestParam(value="trainId",required=true)String trainId
    		){
    	return trainShowService.trainDetails(trainId);
    }
    /**
     * 发布培训
     * @param trainPic
     * @param trainCtime
     * @param trainEtime
     * @param trainName
     * @param teacherName
     * @param classly
     * @param setUpETime
     * @param trainType
     * @param trainContent
     * @param teacherIntroduce
     * @param sponsorTwoCode
     * @return
     */
    @RequestMapping("releaseTrain")
    @ResponseBody
    public DataWrapper<Void> releaseTrain(
    	//	@RequestHeader(value="AdminToken",required=false)String AdminToken,
    		@ModelAttribute Train  train,
    		@ModelAttribute TrainDetail  trainDetails
    		){
    	
    	return trainShowService.releaseTrain(train,trainDetails);
    }
    
    
    /**
     * 分享
     * @param trainId
     * @return
     */
    @RequestMapping("share")
    @ResponseBody
    public DataWrapper<Void> share(
    	//	@RequestHeader(value="AdminToken",required=false)String AdminToken,
    		@RequestParam(value="trainId",required=true) String trainId
    		){
    	
    	return trainShowService.share(trainId);
    }
    
    /**
     * 收藏 
     * @param trainId
     * @return
     */
    @RequestMapping("spotFabulous")
    @ResponseBody
    public DataWrapper<Void> spotFabulous(
    	//	@RequestHeader(value="AdminToken",required=false)String AdminToken,
    		@RequestParam(value="trainId",required=true) String trainId
    		){
    	
    	return trainShowService.spotFabulous(trainId);
    }
    /**
     * 确定报名
     * @param token
     * @param trainId
     * @param weChatNumer
     * @param phoneNumber
     * @param qbDed
     * @param payType
     * @return
     */
    @RequestMapping("confirmRegistration")
    @ResponseBody
    public DataWrapper<Object> confirmRegistration(
    		@RequestParam(value="token",required=true) String token,
    		/*	@RequestParam(value="trainId",required=true)String trainId,
    		@RequestParam(value="weChatNumer",required=true)String weChatNumer,
    		@RequestParam(value="phoneNumber",required=true)String phoneNumber,
    		@RequestParam(value="qbDed",required=true)Integer qbDed,
    		@RequestParam(value="payType",required=true)String payType*/
    		//0是支付宝  1是微信
    		@ModelAttribute TrainOrdera trainOrdera
    		){
    	return trainShowService.confirmRegistration(token,trainOrdera);
    }
    
    @ResponseBody
	@RequestMapping("callBack")
	public void aliPay_notify(
			HttpServletRequest request,
			HttpServletResponse response
			){
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。   alipay.trade.app.pay
		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
		PrintWriter printWriter=null;
		try {
			printWriter =response.getWriter();
			boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY,  "utf-8", "RSA2");
			if(flag){
				if("TRADE_SUCCESS".equals(params.get("trade_status"))){
					//付款金额
					String amount = params.get("buyer_pay_amount");
					//商户订单号
					String out_trade_no = params.get("out_trade_no");
					//支付宝交易号
					//String trade_no = params.get("trade_no");
					//附加数据
					//String passback_params = URLDecoder.decode(params.get("passback_params"));

					PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
					if( payAfterOrderUtil.trainOrderCallBackUtils(out_trade_no,amount)){
						System.out.println("成功啦");
						printWriter.write("success");
					}else{
						System.out.println("失败啦");
						printWriter.write("fail");
					}
				}
			}else{
				printWriter.write("fail");
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			printWriter.close();
		}
	}
}
