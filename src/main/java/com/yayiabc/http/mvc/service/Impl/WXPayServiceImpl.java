package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.WXPayEnum;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
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

    @Override
    public void callBack(HttpServletRequest request, HttpServletResponse response, WXPayEnum wxPayEnum) throws Exception {
        WXPay wxPay = null;
        if (wxPayEnum.equals(WXPayEnum.QB_PC)||wxPayEnum.equals(WXPayEnum.ORDER_PC)) {
            wxPay = new WXPay(WXPayConfigImpl.getInstance());
        } else if (wxPayEnum.equals(WXPayEnum.QB_APP)||wxPayEnum.equals(WXPayEnum.ORDER_APP)) {
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
                            String zh=zh(charge.getQbType());
                            qbRecord.setRemark(zh+"乾币充值" + charge.getQbNum()+"个");
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

    private String zh(String zh){
        if(zh.equals("a_qb")){
            return "\"8.0折\" ";
        } else if(zh.equals("b_qb"))
        {
            return "\"9.0折\" ";
        }else if(zh.equals("c_qb")){
            return "\"9.5折\" ";
        }
        return "非法钱币类型";
    }

    @Override
    public DataWrapper<Void> checkOrderState(String out_trade_no) {
        DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		Integer num=aliPayDao.querySatetIsTwo(out_trade_no);
		dataWrapper.setNum(num);
		return dataWrapper;
    }

    @Override
    public DataWrapper<Void> checkChargeState(String token) {
        DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		String userId=userDao.getUserIdByToken(token);
		Integer num=wXPayDao.getStateByToken(userId);
		dataWrapper.setNum(num);
		return dataWrapper;
    }
}



