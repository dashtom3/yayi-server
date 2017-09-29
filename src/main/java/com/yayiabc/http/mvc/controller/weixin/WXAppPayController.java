package com.yayiabc.http.mvc.controller.weixin;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.enums.WXPayEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.QbExchangeUtil;
import com.yayiabc.common.weixin.WXAppPayConfigImpl;
import com.yayiabc.common.weixin.WXPay;
import com.yayiabc.common.weixin.WXPayConstants;
import com.yayiabc.common.weixin.WXPayUtil;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.dao.WXPayDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.pojo.jpa.WXAppEntry;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.UserMyQbService;
import com.yayiabc.http.mvc.service.WXPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by 小月亮 on 2017/9/2.
 */
@Controller
@RequestMapping("api/appPay")
public class WXAppPayController {
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
    private WXPayService wxPayService;

    @Autowired
    private UtilsDao utilsDao;
    @RequestMapping("unifiedOrderReturnUrl")
    @ResponseBody
    public DataWrapper<WXAppEntry> unifiedOrderReturnUrl(
            @RequestParam("orderId") String orderId, HttpServletRequest request,
            HttpServletResponse response){
        DataWrapper<WXAppEntry> dataWrapper =new DataWrapper<WXAppEntry>();
        System.out.println("开始处理回调");
        HashMap<String, String> hashMap=aliPayService.queryY(orderId);
        String total_fee=hashMap.get("WIDtotal_fee");
        System.out.println(total_fee);
        Double total=Double.parseDouble(total_fee);
        Integer totalFee=(int)(total*100);
        String body=hashMap.get("WIDsubject");
        try {
            WXPay wxPay = new WXPay(WXAppPayConfigImpl.getInstance(), "http://47.93.48.111:6181/api/appPay/getReturnUrl");
            Map<String,String> reqData =new HashMap<String,String>();
            if(body!=null&&!"".equals(body)){
                reqData.put("body","牙医abc-"+body);//必传+
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
            reqData.put("trade_type","APP");//必传,现场扫码付
            reqData.put("product_id",System.currentTimeMillis()+"");//扫码支付时此参数必传,可以通过参数传进来,trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
            System.out.println(reqData);
            Map<String,String> respMap=wxPay.unifiedOrder(reqData);
            System.out.println(respMap);
            SortedMap<String, String> parameterMap = new TreeMap<String, String>();
            parameterMap.put("appid", WXAppPayConfigImpl.getInstance().getAppID());
            parameterMap.put("partnerid",WXAppPayConfigImpl.getInstance().getMchID());
            parameterMap.put("prepayid", respMap.get("prepay_id"));
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("noncestr", WXPayUtil.generateNonceStr());
            parameterMap.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
            String sign=WXPayUtil.generateSignature(parameterMap,WXAppPayConfigImpl.getInstance().getKey(), WXPayConstants.SignType.HMACSHA256);
            System.out.println(sign);
            WXAppEntry wxAppEntry =new WXAppEntry(parameterMap.get("appid"),Long.parseLong(parameterMap.get("timestamp")),parameterMap.get("partnerid"),parameterMap.get("prepayid"),parameterMap.get("noncestr"),sign);
            dataWrapper.setData(wxAppEntry);
        } catch (Exception e) {
            String msg="服务器错误";
            e.printStackTrace();
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
        }
        return dataWrapper;
    }

    @RequestMapping("getReturnUrl")
    @ResponseBody
    public void getReturnUrl(HttpServletRequest request,HttpServletResponse response) throws Exception{
        wxPayService.callBack(request,response,WXPayEnum.ORDER_APP);
    }

    @RequestMapping("unifiedOrderCharge")
    @ResponseBody
    public DataWrapper<WXAppEntry> unifiedOrderCharge(@RequestParam(value="money",required=true) Integer money,
                                                      @RequestParam("qbType")String qbType,
                                                      @RequestParam(value="token",required=true) String token,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response){
        DataWrapper<WXAppEntry> dataWrapper =new DataWrapper<WXAppEntry>();
        String chargeId=UUID.randomUUID().toString();
        String[] str=chargeId.split("-");
        chargeId="";
        for (String string : str) {
            chargeId+=string;
        }
        double totalMoney= QbExchangeUtil.getQbByMoney(money,qbType);
        Charge charge=new Charge();
        charge.setChargeId(chargeId);
        charge.setQbNum(money);
        String totalFee=(int)(totalMoney*100)+"";
        charge.setMoney(totalFee);
        charge.setState(1);
        charge.setToken(utilsDao.getUserID(token));
        charge.setQbType(qbType);
        wXPayDao.deleteChargeByToken(utilsDao.getUserID(token));
        wXPayDao.addCharge(charge);
        try {
            WXPay wxPay = new WXPay(WXAppPayConfigImpl.getInstance(), "http://47.93.48.111:6181/api/appPay/getChargeReturnUrl");
            Map<String,String> reqData =new HashMap<String,String>();
            reqData.put("body","乾币充值");//必传
            reqData.put("out_trade_no",chargeId);
            reqData.put("fee_type", "CNY");
            reqData.put("total_fee",totalFee);//必传,总金额,接口中单位为分,对账单中的单位为元,必须为整数,可以通过参数传进来
            reqData.put("spbill_create_ip",request.getRemoteAddr().toString());//终端ip,必传,APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
            reqData.put("trade_type","APP");//必传,现场扫码付
            reqData.put("product_id",System.currentTimeMillis()+"");//扫码支付时此参数必传,可以通过参数传进来,trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
            System.out.println(reqData);
            Map<String,String> respMap=wxPay.unifiedOrder(reqData);
            System.out.println(respMap);
            SortedMap<String, String> parameterMap = new TreeMap<String, String>();
            parameterMap.put("appid", WXAppPayConfigImpl.getInstance().getAppID());
            parameterMap.put("partnerid",WXAppPayConfigImpl.getInstance().getMchID());
            parameterMap.put("prepayid", respMap.get("prepay_id"));
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("noncestr", WXPayUtil.generateNonceStr());
            parameterMap.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
            String sign=WXPayUtil.generateSignature(parameterMap,WXAppPayConfigImpl.getInstance().getKey(), WXPayConstants.SignType.HMACSHA256);
            System.out.println(sign);
            WXAppEntry wxAppEntry =new WXAppEntry(parameterMap.get("appid"),Long.parseLong(parameterMap.get("timestamp")),parameterMap.get("partnerid"),parameterMap.get("prepayid"),parameterMap.get("noncestr"),sign);
            dataWrapper.setData(wxAppEntry);
        } catch (Exception e) {
            String msg="服务器错误";
            e.printStackTrace();
        }
        return  dataWrapper;
    }

    @RequestMapping("checkChargeState")
    @ResponseBody
    public DataWrapper<Void> checkChargeState(
            @RequestHeader(value="token",required=true) String token
            ){
        return wxPayService.checkChargeState(token);
    }

    @RequestMapping("getChargeReturnUrl")
    @ResponseBody
    public void getChargeReturnUrl(HttpServletRequest request,HttpServletResponse response) throws Exception{
        wxPayService.callBack(request,response,WXPayEnum.QB_APP);
    }


}

