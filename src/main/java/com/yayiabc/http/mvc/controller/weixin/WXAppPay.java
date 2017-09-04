package com.yayiabc.http.mvc.controller.weixin;

import com.yayiabc.common.utils.GetQCode;
import com.yayiabc.common.utils.QbExchangeUtil;
import com.yayiabc.common.weixin.WXPay;
import com.yayiabc.common.weixin.WXPayConfigImpl;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.dao.WXPayDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.UserMyQbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 小月亮 on 2017/9/2.
 */
@Controller
@RequestMapping("api/appPay")
public class WXAppPay {
    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private AliPayDao aliPayDao;

    @Autowired
    private WXPayDao wXPayDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMyQbService userMyQbService;

    @Autowired
    private UtilsDao utilsDao;
    @RequestMapping("unifiedOrderReturnUrl")
    @ResponseBody

    public void unifiedOrderReturnUrl(
            @RequestParam("orderId") String orderId, HttpServletRequest request,
            HttpServletResponse response){
        System.out.println("开始处理回调");
        HashMap<String, String> hashMap=aliPayService.queryY(orderId);
        String total_fee=hashMap.get("WIDtotal_fee");//0.01
        Double total=Double.parseDouble(total_fee);
        Integer totalFee=(int)(total*100);
        String body=hashMap.get("WIDsubject");
        try {
            WXPay wxPay = new WXPay(WXPayConfigImpl.getInstance(), "http://47.93.48.111:6181/api/weixin/getReturnUrl");
            Map<String,String> reqData =new HashMap<String,String>();
            if(body!=null&&!"".equals(body)){
                reqData.put("body","牙医abc-"+body);//必传
            }else{
                reqData.put("body", "牙医abc-"+"商品");
            }
            wXPayDao.deleteOrderRecord(orderId);
            String outTradeNo= UUID.randomUUID().toString();
            String[] str=outTradeNo.split("-");
            outTradeNo="";
            for (String string : str) {
                outTradeNo+=string;
            }
            wXPayDao.addOrderRecord(orderId,outTradeNo);
            reqData.put("out_trade_no",outTradeNo);
            reqData.put("fee_type", "CNY");
            if(totalFee!=null&&!"".equals(totalFee)){
                reqData.put("total_fee",totalFee+"");//必传,总金额,接口中单位为分,对账单中的单位为元,必须为整数,可以通过参数传进来
            }else {
                reqData.put("total_fee","1");
            }
            reqData.put("spbill_create_ip",request.getRemoteAddr().toString());//终端ip,必传,APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
//			reqData.put("notify_url","http://47.93.48.111:8080/api/item/getItemId");//通知地址,接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
            reqData.put("trade_type","APP");//必传,现场扫码付
            reqData.put("product_id",System.currentTimeMillis()+"");//扫码支付时此参数必传,可以通过参数传进来,trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
            System.out.println(reqData);
            Map<String,String> respMap=wxPay.unifiedOrder(reqData);
            System.out.println(respMap);
        } catch (Exception e) {
            String msg="服务器错误";
            e.printStackTrace();
        }
    }

    @RequestMapping("unifiedOrderCharge")
    @ResponseBody
    public void unifiedOrderCharge(@RequestParam(value="money",required=true) Integer money,
                                   @RequestParam("qbType")String qbType,
                                   @RequestParam(value="token",required=true) String token,
                                   HttpServletRequest request,
                                   HttpServletResponse response){
        String chargeId=UUID.randomUUID().toString();
        String[] str=chargeId.split("-");
        chargeId="";
        for (String string : str) {
            chargeId+=string;
        }
        Charge charge=new Charge();
        charge.setChargeId(chargeId);
        charge.setMoney(money);
        charge.setState(1);
        charge.setToken(utilsDao.getUserID(token));
        charge.setQbType(qbType);
        wXPayDao.deleteChargeByToken(token);
        wXPayDao.addCharge(charge);
        double totalMoney= QbExchangeUtil.getQbByMoney(money,qbType);
        money=(int)Math.round(totalMoney);
        try {
            WXPay wxPay = new WXPay(WXPayConfigImpl.getInstance(), "http://47.93.48.111:8080/api/weixin/getChargeReturnUrl");
            Map<String,String> reqData =new HashMap<String,String>();
            reqData.put("body","乾币充值");//必传
            reqData.put("out_trade_no",chargeId);
            reqData.put("fee_type", "CNY");
            Integer totalFee=money*100;
            reqData.put("total_fee",totalFee.toString());//必传,总金额,接口中单位为分,对账单中的单位为元,必须为整数,可以通过参数传进来
            reqData.put("spbill_create_ip",request.getRemoteAddr().toString());//终端ip,必传,APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
            reqData.put("trade_type","APP");//必传,现场扫码付
            reqData.put("product_id",System.currentTimeMillis()+"");//扫码支付时此参数必传,可以通过参数传进来,trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
            System.out.println(reqData);
            Map<String,String> respMap=wxPay.unifiedOrder(reqData);
            System.out.println(respMap);
            String urlCode=(String)respMap.get("code_url");
            GetQCode.getqCode(urlCode, response);
        } catch (Exception e) {
            String msg="服务器错误";
            e.printStackTrace();
        }
    }
}
