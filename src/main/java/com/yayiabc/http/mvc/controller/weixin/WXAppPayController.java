package com.yayiabc.http.mvc.controller.weixin;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.common.utils.QbExchangeUtil;
import com.yayiabc.common.weixin.*;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.dao.WXPayDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.WXAppEntry;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.UserMyQbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private UtilsDao utilsDao;
    @RequestMapping("unifiedOrderReturnUrl")
    @ResponseBody
    public DataWrapper<WXAppEntry> unifiedOrderReturnUrl(
            @RequestParam("orderId") String orderId, HttpServletRequest request,
            HttpServletResponse response){
        DataWrapper<WXAppEntry> dataWrapper =new DataWrapper<WXAppEntry>();
        System.out.println("开始处理回调");
        HashMap<String, String> hashMap=aliPayService.queryY(orderId);
        String total_fee=hashMap.get("WIDtotal_fee");//0.01
        Double total=Double.parseDouble(total_fee);
        Integer totalFee=(int)(total*100);
        String body=hashMap.get("WIDsubject");
        try {
            WXPay wxPay = new WXPay(WXAppPayConfigImpl.getInstance(), "http://47.93.48.111:6181/api/appPay/getReturnUrl");
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

        WXPay wxPay =new WXPay(WXAppPayConfigImpl.getInstance());
        //读取参数
        InputStream inputStream;
        StringBuffer sb=new StringBuffer();
        inputStream=request.getInputStream();
        String s;
        BufferedReader in=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        while((s=in.readLine())!=null){
            sb.append(s);
        }
        in.close();
        inputStream.close();

        //解析XML成MAP
        Map<String, String> map =new HashMap<String,String>();
        map=WXPayUtil.xmlToMap(sb.toString());
        System.out.println(map);

        //过滤空,设置treeMap
        SortedMap<Object, Object> packageParam=new TreeMap<Object, Object>();
        Iterator it=map.keySet().iterator();
        while(it.hasNext()){
            String parameter=(String)it.next();
            String parameterValue=map.get(parameter);
            String v="";
            if(null!=parameterValue){
                v=parameterValue.trim();
            }
            packageParam.put(parameter, v);
        }

        //账号信息
        String out_trade_no =(String)packageParam.get("out_trade_no");
        System.out.println(out_trade_no);
        System.out.println("开始判断签名是否正确");
        //判断签名是否正确
        if(wxPay.isPayResultNotifySignatureValid(map)){
            System.out.println("签名验证成功");
            //处理业务开始
            String resXml="";
            if("SUCCESS".equals((String)packageParam.get("result_code"))){
                System.out.println("返回结果成功");
                //判断返回结果中的金额是否和数据库中查出来的订单金额一致
                String outTradeNo=(String)packageParam.get("out_trade_no");
                String orderId=wXPayDao.getOrderIdByOutTradeNo(outTradeNo);
                HashMap<String, String> hashMap=aliPayService.queryY(orderId);
                System.out.println(hashMap);
                if(aliPayDao.querySatetIsTwo(orderId)==2) {
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                }else{
                    System.out.println("开始处理流程");
                    String total_fee=hashMap.get("WIDtotal_fee");//0.01
                    Double total=Double.parseDouble(total_fee);
                    Integer totalFee=(int)(total*100);
                    Integer totalTwo=Integer.parseInt((String)packageParam.get("total_fee"));
                    if(totalFee==totalTwo){
                        System.out.println("付款金额与实际消费金额一致");
                        PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
                        payAfterOrderUtil.universal(orderId,"1");
                        //这里是支付成功
                        System.out.println("支付成功");
                        //改变订单状态
						/*aliPayDao.updateStateAndPayTime(orderId);*/
                        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    }else{
                        resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                    }
                }
            }else{
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        }else{
            System.out.println("签名验证失败");
        }
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
        String totalFee=(int)(Math.round(totalMoney)*100)+"";
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
        DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
        String userId=userDao.getUserIdByToken(token);
        Integer num=wXPayDao.getStateByToken(userId);
        dataWrapper.setNum(num);
        return dataWrapper;
    }

    @RequestMapping("getChargeReturnUrl")
    @ResponseBody
    public void getChargeReturnUrl(HttpServletRequest request,HttpServletResponse response) throws Exception{
        System.out.println("开始处理回掉");
        WXPay wxPay =new WXPay(WXAppPayConfigImpl.getInstance());
        //读取参数
        InputStream inputStream;
        StringBuffer sb=new StringBuffer();
        inputStream=request.getInputStream();
        String s;
        BufferedReader in=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        while((s=in.readLine())!=null){
            sb.append(s);
        }
        in.close();
        inputStream.close();

        //解析XML成MAP
        Map<String, String> map =new HashMap<String,String>();
        map=WXPayUtil.xmlToMap(sb.toString());
        System.out.println(map);

        //过滤空,设置treeMap
        SortedMap<Object, Object> packageParam=new TreeMap<Object, Object>();
        Iterator it=map.keySet().iterator();
        while(it.hasNext()){
            String parameter=(String)it.next();
            String parameterValue=map.get(parameter);
            String v="";
            if(null!=parameterValue){
                v=parameterValue.trim();
            }
            packageParam.put(parameter, v);
        }

        //账号信息
        String out_trade_no =(String)packageParam.get("out_trade_no");
        System.out.println(out_trade_no);
        System.out.println("开始验证签名");
        System.out.println(wxPay.isPayResultNotifySignatureValid(map));
        //判断签名是否正确
        if(wxPay.isPayResultNotifySignatureValid(map)){
            System.out.println("签名验证成功");
            //处理业务开始
            String resXml="";
            if("SUCCESS".equals((String)packageParam.get("result_code"))){
                System.out.println("返回成功");
                //判断返回结果中的金额是否和数据库中查出来的订单金额一致
                String chargeId=(String)packageParam.get("out_trade_no");
                System.out.println(chargeId);
                Integer chargeState=wXPayDao.getChargeStateByChargeId(chargeId);
                if(chargeState==1){
                    System.out.println("状态成功");
                    String money=wXPayDao.getMoneyByChargeId(chargeId);
                    System.out.println(money);
                    String totalFee=(String)packageParam.get("total_fee");
                    System.out.println(totalFee);
                    if(money.equals(totalFee)){
                        //这里是支付成功
                        System.out.println("支付成功");
                        //给客户的钱包充值
                        Charge charge=aliPayDao.queryUserId(out_trade_no);
                        System.out.println(charge);
                        String userId=wXPayDao.getTokenByChargeId(chargeId);
                        String token=userDao.getTokenByUserId(userId);
                        System.out.println("处理回掉成功");
                        QbRecord qbRecord =new QbRecord();
                        qbRecord.setQbRget(charge.getQbNum());
                        qbRecord.setQbType(charge.getQbType());
                        qbRecord.setRemark("乾币充值"+charge.getQbNum());
                        userMyQbService.add(qbRecord, token);
                        System.out.println("开始改变订单状态");
                        wXPayDao.updateChargeState(chargeId);
                        System.out.println("改变订单状态成功");
                        resXml = "<xml>" + "<return_code><" +
                                "![CDATA[SUCCESS]]></return_code>"+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    }else{
                        resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                    }
                }else{
                    resXml = "<xml>" + "<return_code><" +
                            "![CDATA[SUCCESS]]></return_code>"+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                }

            }else{
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        }else{
            System.out.println("签名验证失败");
        }
    }


}

