package com.yayiabc.http.mvc.controller.alipay;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.yayiabc.common.alipayenclos.config.AlipayConfig;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.AppPayService;

@Controller
@RequestMapping("api/appPay")
public class AppPayController {
	
	@Autowired
	private AliPayService alipayService;
	
	@Autowired
	private AliPayDao alipayDao;
	@Autowired 
	private AppPayService appPayService;
/*	@ResponseBody
	@RequestMapping(value = "aliAppPay")
		//EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
		//实例化客户端https://openapi.alipaydev.com/gateway.do
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2016022401160229", AlipayConfig.RSA_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		//model.setBody("我是测试数据");
		model.setSubject("App支付测试Java");
		model.setOutTradeNo(getOutTradeNo());
		//model.setTimeoutExpress("30m");
		model.setTotalAmount("0.01");
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl("http://47.93.48.111:6181/api/appPay/callBack");
		try {
		        //这里和普通的接口调用不同，使用的是sdkExecute
		        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
		        String a=response.getBody();
		        System.out.println(a);//就是orderString 可以直接给客户端请求，无需再做处理。
		        return a;
		    } catch (AlipayApiException e) {
		        e.printStackTrace();
		}
		return null;
    }*/
	private static String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}
	@ResponseBody
	@RequestMapping("callBack")
	public String aliPay_notify(
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
        try {
            boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY,  "utf-8", "RSA2");
            if(flag){
                if("TRADE_SUCCESS".equals(params.get("trade_status"))){
                    //付款金额
                    String amount = params.get("buyer_pay_amount");
                    //商户订单号
                    String out_trade_no = params.get("out_trade_no");
                    //支付宝交易号
                    String trade_no = params.get("trade_no");
                    //附加数据
                    String passback_params = URLDecoder.decode(params.get("passback_params"));
                   Ordera order= alipayDao.queryOrder(out_trade_no);
                   if(order.getOrderId().equals(out_trade_no)&&order.getActualPay().equals(amount)){
                	   System.out.println("成功啦");
                	   return "success";
                   }
                }
            }
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("不啦啦啦");
		return null;
    }
	//app支付
	@ResponseBody
	@RequestMapping("appPay")
	public DataWrapper<Object> apppay(String orderId){
		
        HashMap<String , String> hm=alipayService.queryY(orderId);
       
        return appPayService.packingParameter(orderId,hm.get("WIDout_trade_no"),hm.get("WIDtotal_fee"),hm.get("WIDbody"),hm.get("WIDsubject")
    		   ,"QUICK_MSECURITY_PAY"
    		   );
	}
}
