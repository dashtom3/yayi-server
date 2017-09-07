package com.yayiabc.http.mvc.controller.alipay;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.yayiabc.common.alipayenclos.config.AlipayConfig;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.AppPayService;
import com.yayiabc.http.mvc.service.UserMyQbService;

@Controller
@RequestMapping("api/appPay")
public class AppPayController {
	
	@Autowired
	private AliPayService alipayService;
	
	@Autowired
	private AliPayDao alipayDao;
	@Autowired 
	private AppPayService appPayService;
	@Autowired
	private UtilsDao utilsDao;
	@Autowired
	private UserMyQbService userMyQbService;

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
                    //String trade_no = params.get("trade_no");
                    //附加数据
                    //String passback_params = URLDecoder.decode(params.get("passback_params"));
                 //  return ut(out_trade_no,amount);
                    String  sign=ut(out_trade_no,amount);
                   if(sign.equals("success")){
                	   System.out.println("成功啦");
                	   response.getWriter().write("success");
                   }else{
                	   System.out.println("失败啦");
                	   response.getWriter().write("fail");
                   }
                  /* Ordera order= alipayDao.queryOrder(out_trade_no);
                   if(order.getOrderId().equals(out_trade_no)&&order.getActualPay().equals(amount)){
                	   System.out.println("成功啦");
                	   return "success";
                   }*/
                }
            }
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("不啦啦啦");
		return "fail";
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
	//验证方法
	private String ut(String out_trade_no,String amount){
		if("zfb".equals(out_trade_no.substring(0, 3))){
			Charge charge=alipayDao.queryUserId(out_trade_no);
			System.out.println(out_trade_no);
			 System.out.println("123123123213   "+charge);
			 System.out.println(amount+"    "+charge.getMoney());
          /*  if(!amount.equals(charge.getMoney())){
            	return "fail";
            }*/
            //商户订单号
			if(charge.getState()==1){
				alipayDao.updateState(out_trade_no);
				String token=utilsDao.getToken(charge.getToken());
				QbRecord q=new QbRecord();
				q.setQbRget(charge.getQbNum());
				q.setQbType(charge.getQbType());
				q.setRemark(charge.getQbType()+"乾币充值(支付宝)");
				userMyQbService.add(q, token);
				return "success";
			}else{
				return "success";
			}
			
		}
	   //校验金额
		Ordera order=alipayDao.queryOrder(out_trade_no);
		if(!amount.equals(order.getActualPay())&&order.getOrderId().equals(out_trade_no)){
			 return "fail";
		}
					if(1==order.getState()){
						PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
						payAfterOrderUtil.universal(out_trade_no,"0");
						return "success";
					}
					return "fail";
	  }
	   //轮询接口
	@RequestMapping("polling")
	@ResponseBody
	  public DataWrapper<Void> polling(
			  @RequestParam(value="sign",required=true) String sign,
			  @RequestParam(value="id",required=true) String id
			  ){
		  DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		  if(sign.equals("orderPay")){
			 int ss= alipayDao.querySatetIsTwo(id);
			if(ss==2){
				 dataWrapper.setMsg("成功");
			 }else{
				 dataWrapper.setMsg("失败");
			 }
		  }else if(sign.equals("coinPay")){
			  int ss= alipayDao.queryChargeIsTwo(id);
			  if(ss==2){
					 dataWrapper.setMsg("成功");
				 }else{
					 dataWrapper.setMsg("失败");
				 }
		  }else{
			  dataWrapper.setMsg("NONONO");
		  }
		  return dataWrapper;
	  }
}
