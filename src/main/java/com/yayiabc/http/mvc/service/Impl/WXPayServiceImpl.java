package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.WXPayEnum;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.common.weixin.*;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UserMyQbDao;
import com.yayiabc.http.mvc.dao.WXPayDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.User;
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

    @Autowired
    private UserMyQbDao userMyQbDao;

    @Override
    public void callBack(HttpServletRequest request, HttpServletResponse response, WXPayEnum wxPayEnum) throws Exception {
        System.out.println("开始处理回掉请求");
        //获取支付方式
        int type=getReferer(wxPayEnum);
        WXPay wxPay = null;
        if (wxPayEnum.equals(WXPayEnum.QB_PC)||wxPayEnum.equals(WXPayEnum.ORDER_PC)) {
            wxPay = new WXPay(WXPayConfigImpl.getInstance());
        } else if (wxPayEnum.equals(WXPayEnum.QB_APP)||wxPayEnum.equals(WXPayEnum.ORDER_APP)) {
            wxPay = new WXPay(WXAppPayConfigImpl.getInstance());
        }else if(wxPayEnum.equals(WXPayEnum.QB_JS)||wxPayEnum.equals(WXPayEnum.ORDER_JS)){
            wxPay=new WXPay(WXPayConfigImpl.getInstance(),true,true);
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
        System.out.println("微信返回参数为"+map);
        Boolean validate=true;
        String key="xiaojiangxiaojiangxiaojiangjiang";
        if(wxPayEnum.equals(WXPayEnum.QB_JS)||wxPayEnum.equals(WXPayEnum.ORDER_JS)){
            validate=WXPayUtil.isSignatureValid(map, key, WXPayConstants.SignType.MD5);
        }else{
            validate=WXPayUtil.isSignatureValid(map, key, WXPayConstants.SignType.HMACSHA256);
        }
        //判断签名是否正确
        if (validate) {
            //处理业务开始
            String resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            if ("SUCCESS".equals((String) packageParam.get("result_code"))) {
                if (wxPayEnum.equals(WXPayEnum.ORDER_PC) || wxPayEnum.equals(WXPayEnum.ORDER_APP)||wxPayEnum.equals(WXPayEnum.ORDER_JS)) {
                    String outTradeNo = (String) packageParam.get("out_trade_no");
                    String orderId = wXPayDao.getOrderIdByOutTradeNo(outTradeNo);
                    HashMap<String, String> hashMap = aliPayService.queryY(orderId);
                    System.out.println("查询出来的hashMap"+hashMap);
                    System.out.println("开始判断订单的状态");
                    if (aliPayDao.querySatetIsTwo(orderId) == 2) {
                        System.out.println("正确返回");
                        resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    } else {
                        String total_fee = hashMap.get("WIDtotal_fee");//0.01
                        Double total = Double.parseDouble(total_fee);
                        Integer totalFee = (int) (total * 100);
                        System.out.println("totalFee"+totalFee);
                        Integer totalTwo = Integer.parseInt((String) packageParam.get("total_fee"));
                        System.out.println("totalTwo"+totalTwo);
                        System.out.println("开始判断金额是否正确");
                        if (totalFee .equals(totalTwo)) {
                            System.out.println("金额相等");
                            PayAfterOrderUtil payAfterOrderUtil = BeanUtil.getBean("PayAfterOrderUtil");
                            System.out.println("payAfterOrderUtil:"+payAfterOrderUtil);
                            Boolean flag = payAfterOrderUtil.universal(orderId, type+"");//1网页扫码/微信公众号 2.APP
                            System.out.println("flag"+flag);
                            if (flag) {
                                //这里是支付成功
                                System.out.println("支付成功"+wxPayEnum);
                                //改变订单状态
                                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                            }
                        }
                    }
                } else {
                    type-=2;//支付类型自增,方便下面进行传参
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
//                            qbRecord.setQbRget(String.valueOf(charge.getQbNum()));
                            qbRecord.setQbType(charge.getQbType());
                            qbRecord.setUserId(userId);
                            qbRecord.setMillisecond(System.nanoTime());
                            String zh=zh(charge.getQbType());
                            qbRecord.setQbRget(zh+":"+String.valueOf(charge.getQbNum())+"个");
                            qbRecord.setRemark(zh(charge.getQbType())+"乾币充值"+charge.getQbNum()+"个。");
                            userMyQbDao.updateUserQb(String.valueOf(charge.getQbNum()), charge.getToken(), charge.getQbType());
//                            qbRecord.setRemark(zh+"乾币充值" + charge.getQbNum()+"个");
                            //----为了获取钱币余额
                            User user=userMyQbDao.getUserQbNum(userId);
                    		int qbbalance=user.getQbBalance();
                    		int aqb=user.getaQb();
                    		int bqb=user.getbQb();
                    		int cqb=user.getcQb();
                    		int userQbNum=qbbalance+aqb+bqb+cqb;
                    		qbRecord.setQbBalances("\"赠：\""+qbbalance+"个；"+"\"8.0折\""+aqb+"个；"+"\"9.0折\""+bqb+"个；"+"\"9.5折\""+cqb+"个；");
                            qbRecord.setReferer(type);
                            qbRecord.setRemark(zh+"乾币充值"+charge.getQbNum()+"个。（乾币余额："+userQbNum+"个）");
                            userMyQbDao.add(qbRecord);//refer :2.网页扫码/微信公众号 3.APP
//                            userMyQbService.add(qbRecord, token);
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

    //通过枚举
    public int getReferer(WXPayEnum wxPayEnum){
        int number=4;
        if(wxPayEnum.equals(WXPayEnum.ORDER_APP)||wxPayEnum.equals(WXPayEnum.QB_APP)){
            number=5;
        }
        return number;
    }
}



