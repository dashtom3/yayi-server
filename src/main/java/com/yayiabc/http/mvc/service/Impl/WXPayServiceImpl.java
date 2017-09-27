package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.WXPayEnum;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.common.weixin.WXAppPayConfigImpl;
import com.yayiabc.common.weixin.WXPay;
import com.yayiabc.common.weixin.WXPayConfigImpl;
import com.yayiabc.common.weixin.WXPayUtil;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.WXPayDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.AliPayService;
import com.yayiabc.http.mvc.service.UserMyQbService;
import com.yayiabc.http.mvc.service.WXPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
@Service
public class WXPayServiceImpl implements WXPayService{

    @Autowired
    private WXPayDao wXPayDao;

    @Autowired
    private AliPayDao  aliPayDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMyQbService userMyQbService;

    @Autowired
    private AliPayService aliPayService;



   /* @Override
    public void getChargeReturnUrl(HttpServletRequest request, HttpServletResponse response, WXPayEnum wxPayEnum) throws Exception {
        WXPay wxPay=null;
        if(wxPayEnum.equals(WXPayEnum.QB_PC)){
            wxPay =new WXPay(WXPayConfigImpl.getInstance());
        }else if(wxPayEnum.equals(WXPayEnum.QB_APP)){
            wxPay =new WXPay(WXAppPayConfigImpl.getInstance());
        }
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
        map= WXPayUtil.xmlToMap(sb.toString());
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
        //判断签名是否正确
        if(wxPay.isPayResultNotifySignatureValid(map)){
            //处理业务开始
            String resXml="";
            if("SUCCESS".equals((String)packageParam.get("result_code"))){

                //判断返回结果中的金额是否和数据库中查出来的订单金额一致
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
                        qbRecord.setQbRget(String.valueOf(charge.getQbNum()));
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

    @Override
    public void getReturnUrl(HttpServletRequest request, HttpServletResponse response, WXPayEnum wxPayEnum) throws Exception {
        WXPay wxPay=null;
        if(wxPayEnum.equals(WXPayEnum.ORDER_PC)){
            wxPay =new WXPay(WXPayConfigImpl.getInstance());
        }else if(wxPayEnum.equals(WXPayEnum.ORDER_APP)){
            wxPay =new WXPay(WXAppPayConfigImpl.getInstance());
        }
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
        //判断签名是否正确
        if(wxPay.isPayResultNotifySignatureValid(map)){
            //处理业务开始
            String resXml="";
            if("SUCCESS".equals((String)packageParam.get("result_code"))){
                //判断返回结果中的金额是否和数据库中查出来的订单金额一致
                String outTradeNo=(String)packageParam.get("out_trade_no");
                String orderId=wXPayDao.getOrderIdByOutTradeNo(outTradeNo);
                HashMap<String, String> hashMap=aliPayService.queryY(orderId);
                System.out.println(hashMap);
                if(aliPayDao.querySatetIsTwo(orderId)==2) {
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                }else{
                    String total_fee=hashMap.get("WIDtotal_fee");//0.01
                    Double total=Double.parseDouble(total_fee);
                    Integer totalFee=(int)(total*100);
                    Integer totalTwo=Integer.parseInt((String)packageParam.get("total_fee"));
                    if(totalFee==totalTwo){
                        PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
                        Boolean flag=payAfterOrderUtil.universal(orderId,"1");
                        if(flag){
                            //这里是支付成功
                            System.out.println("支付成功");
                            //改变订单状态
						*//*aliPayDao.updateStateAndPayTime(orderId);*//*
                            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                        }else {
                            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                        }
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
    }*/

    @Override
    public void callBack(HttpServletRequest request, HttpServletResponse response, WXPayEnum wxPayEnum) throws Exception {
        WXPay wxPay = null;
        if (wxPayEnum.equals(WXPayEnum.QB_PC)) {
            wxPay = new WXPay(WXPayConfigImpl.getInstance());
        } else if (wxPayEnum.equals(WXPayEnum.QB_APP)) {
            wxPay = new WXPay(WXAppPayConfigImpl.getInstance());
        }
        //读取参数
        InputStream inputStream;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();

        //解析XML成MAP
        Map<String, String> map = new HashMap<String, String>();
        map = WXPayUtil.xmlToMap(sb.toString());
        System.out.println(map);

        //过滤空,设置treeMap
        SortedMap<Object, Object> packageParam = new TreeMap<Object, Object>();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = map.get(parameter);
            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParam.put(parameter, v);
        }

        //账号信息
        String out_trade_no = (String) packageParam.get("out_trade_no");
        System.out.println(out_trade_no);
        //判断签名是否正确
        if (wxPay.isPayResultNotifySignatureValid(map)) {
            //处理业务开始
            String resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            if ("SUCCESS".equals((String) packageParam.get("result_code"))) {
                if (wxPayEnum.equals(WXPayEnum.ORDER_PC) || wxPayEnum.equals(WXPayEnum.ORDER_APP)) {
                    String outTradeNo = (String) packageParam.get("out_trade_no");
                    String orderId = wXPayDao.getOrderIdByOutTradeNo(outTradeNo);
                    HashMap<String, String> hashMap = aliPayService.queryY(orderId);
                    if (aliPayDao.querySatetIsTwo(orderId) == 2) {
                        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    } else {
                        String total_fee = hashMap.get("WIDtotal_fee");//0.01
                        Double total = Double.parseDouble(total_fee);
                        Integer totalFee = (int) (total * 100);
                        Integer totalTwo = Integer.parseInt((String) packageParam.get("total_fee"));
                        if (totalFee == totalTwo) {
                            PayAfterOrderUtil payAfterOrderUtil = BeanUtil.getBean("PayAfterOrderUtil");
                            Boolean flag = payAfterOrderUtil.universal(orderId, "1");
                            if (flag) {
                                //这里是支付成功
                                System.out.println("支付成功");
                                //改变订单状态
                                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                            }
                        }
                    }
                } else {
                    //判断返回结果中的金额是否和数据库中查出来的订单金额一致
                    String chargeId = (String) packageParam.get("out_trade_no");
                    Integer chargeState = wXPayDao.getChargeStateByChargeId(chargeId);
                    if (chargeState == 1) {
                        String money = wXPayDao.getMoneyByChargeId(chargeId);
                        String totalFee = (String) packageParam.get("total_fee");
                        if (money.equals(totalFee)) {
                            //给客户的钱包充值
                            Charge charge = aliPayDao.queryUserId(out_trade_no);
                            String userId = wXPayDao.getTokenByChargeId(chargeId);
                            String token = userDao.getTokenByUserId(userId);
                            QbRecord qbRecord = new QbRecord();
                            qbRecord.setQbRget(String.valueOf(charge.getQbNum()));
                            qbRecord.setQbType(charge.getQbType());
                            qbRecord.setRemark("乾币充值" + charge.getQbNum());
                            userMyQbService.add(qbRecord, token);
                            wXPayDao.updateChargeState(chargeId);
                            resXml = "<xml>" + "<return_code><" +
                                    "![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                        }
                    } else {
                        resXml = "<xml>" + "<return_code><" +
                                "![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    }
                }
            }
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } else {
            System.out.println("签名验证失败");
        }
    }
}



