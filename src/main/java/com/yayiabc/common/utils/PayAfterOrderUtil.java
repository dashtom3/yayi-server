package com.yayiabc.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yayiabc.common.cahce.CacheUtils;
import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.exceptionHandler.OrderException;
import com.yayiabc.http.mvc.dao.AliPayDao;
import com.yayiabc.http.mvc.dao.OrderManagementDao;
import com.yayiabc.http.mvc.dao.UserMyQbDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.UserMyQbService;
import com.yayiabc.http.mvc.service.UserWithdrawalsService;
@Component
public class PayAfterOrderUtil {
	@Autowired
	private AliPayDao aliPayDao;
	@Autowired
	private UtilsDao utilsDao;
	@Autowired
	private UserMyQbService userMyQbService;
	@Autowired
	private OrderManagementDao orderManagementDao;
	
	
	@Autowired
	private UserMyQbDao userMyQbDao;
		//更改支付类型
	public  boolean universal(String orderId,String type){
		if(type!=null){
			aliPayDao.updatePayType(orderId,type);
		}
		//更改订单状态与付款时间
		/*int ax=aliPayDao.updateStateAndPayTime(orderId);*/
		
		Ordera o=aliPayDao.queryOrder(orderId);
		//QbRecord q=new QbRecord();
		if(o.getQbDed()!=0){
			//
			//更新到订单表最后一列
			int a=aliPayDao.saveLast(newQbDed(o.getUserId(),o.getQbDed(),o.getOrderId()),o.getOrderId());
			if(a<=0){
				throw new OrderException(ErrorCodeEnum.ORDER_ERROR); 
			}
		}
		
		//统计销量
		List<OrderItem> orderItemList=orderManagementDao.queryOrderItemList(orderId);
		//双休优化增加销量
		int t=aliPayDao.addSalesList(orderItemList);
		//增加 itemvalue表里面的销量信息
		int c=aliPayDao.addSalesListTOitemValue(orderItemList);
         if(t<=0&&c<=0){
        	 throw new OrderException(ErrorCodeEnum.ORDER_ERROR); 
         }
         //关于钱币
 		//查询该用户钱包余额
 		//int qbNum=utilsDao.queryUserQbNum(o.getUserId());
 		if(o.getGiveQb()!=0){
 			QbRecord q=new QbRecord();
 			
 			q.setQbRget("\"赠\"： "+o.getGiveQb()+"个");
 			
 			q.setUserId(o.getUserId()+"");
 			/*Calendar Cld = Calendar.getInstance();
 			int MI = Cld.get(Calendar.MILLISECOND);	*/
 			q.setMillisecond(System.nanoTime());
 			userMyQbDao.updateUserQb(o.getGiveQb()+"", o.getUserId(),"qb_balance");
 			//----为了获取钱币余额。。。。。
 			Integer userQbNum=userMyQbDao.getUserQbNum(o.getUserId());
 			q.setRemark("下单获得"+o.getGiveQb()+"个乾币。（乾币余额："+userQbNum+"）订单编号："+orderId);
 			userMyQbDao.add(q);
 		}
 		//判断 该用户是否绑定了销售员
 		String saleId=utilsDao.getSaleIdByOrderId(orderId);
 		if(saleId!=null&&!saleId.equals("")){
 			//清楚缓存中的orderId
			CacheUtils cache=	CacheUtils.getInstance();
			Map<String,Date> map=cache.getCacheMap();
			map.remove(orderId);
 			return SetSaleInCome(orderId,o.getUserId(),saleId);
 		}
 		//清楚缓存中的orderId
		CacheUtils cache=	CacheUtils.getInstance();
		Map<String,Date> map=cache.getCacheMap();
		map.remove(orderId);
		return true;
	}
	
	//结账时放入到SaleIncome表里的数据 并把 到账到该销售员
	private boolean SetSaleInCome(String orderId,String userId,String saleId){
		//查出此单的  supplies_sumprice    tooldevices_sumprice
		Ordera order=utilsDao.queryCalssSumPrice(orderId);
		//保存到 sale_income 表里
        
		int sign=utilsDao.insert(saleId, orderId, 0.0,0.0,order.getSupplies_sumprice(), order.getTooldevices_sumprice());
		SendToSaleMessage sendToSaleMessage= BeanUtil.getBean("SendToSaleMessage");
		//短信通知
		sendToSaleMessage.send(userId,orderId);
		
		return true;
	} 
	//下单钱币扣除规则 先。。。后。。。。。。。
	String  newQbDed(String userId,Integer  DedNum,String orderId){
		//dednum <= max used qb //钱币够用
		User u=utilsDao.queryUserByUserId(userId);
		
		List<Integer> listData=new ArrayList<Integer>();
		listData.add(u.getQbBalance()); 
		listData.add( u.getaQb());      
		listData.add( u.getbQb());      
		listData.add( u.getcQb());         
		int a=DedNum;
		String qbDes=null;
		StringBuffer sb=new StringBuffer();
		StringBuilder sb1=new StringBuilder();
		String s=null;
		for(int i=0;i<listData.size();i++){
			
			if(listData.get(i)>=DedNum){
				System.out.println(listData.get(i)+"   "+DedNum);
				sb1.append(DedNum+",");
				qbDes=sb1.toString();
				s=sb.toString()+ut(i)+DedNum+"个";
				DedNum=listData.get(i)-DedNum;
				listData.set(i, DedNum);
				break;
			}else if(listData.get(i)<DedNum){
				
				sb.append(ut(i)+listData.get(i)+"个，");
				sb1.append(listData.get(i)+",");
				DedNum=DedNum-listData.get(i);
				listData.set(i, 0);
				
			}
		} 
		System.err.println(qbDes);
		System.err.println(listData);
		/*Calendar Cld = Calendar.getInstance();
		int MI = Cld.get(Calendar.MILLISECOND);*/
		
		userMyQbService.updateDataToUser(listData,userId); //更改user 表  各种钱币类型
		//查询乾币余额
		Integer userQbNum=userMyQbDao.getUserQbNum(userId);
		userMyQbService.addMessageQbQ(s,userId,"下单使用"+a+"个乾币。（乾币余额："+userQbNum+"）订单编号："+orderId,System.nanoTime()); //新增钱币记录表   
		return mosaicString(qbDes);
	}
	
	private String ut(int i){
		 switch (i) {
		case 0:
			 return "\"赠\"：";
		case 1:
			 return "\"8.0折\"：";
		case 2:
			return "\"9.0折\"：";
		case 3:
			return "\"9.5折\"：";
		default:
			break;
		}
		 return "";
	}
	/**
	 * 安全验证方法
	 * @param out_trade_no
	 * @param amount
	 * @return
	 */
	public boolean SecurityVerification(String out_trade_no,String amount,String payType){
		//充值乾币安全检查
		if("zfb".equals(out_trade_no.substring(0, 3))||"wx".equals(out_trade_no.substring(0, 2))){
			Charge charge=aliPayDao.queryUserId(out_trade_no);
			 System.out.println(out_trade_no);
			 System.out.println("123123123213   "+charge);
			if(charge==null){
				return false;
			}
			/* //验证金额
			 Double amount1=Double.parseDouble(amount)*100;
			 Double amount2=Double.parseDouble(charge.getMoney())*100;
             if(amount1!=amount2){
            	return false;
            }*/
            //商户订单号
           System.out.println("chargechargechargechargechargecharge     "+charge);
			if(charge!=null){
				if(charge.getState()==1){
					aliPayDao.updateState(out_trade_no);
					System.out.println(1);
					//String token=utilsDao.getToken(charge.getToken());
					System.out.println(2);
					QbRecord q=new QbRecord();
					q.setUserId(charge.getToken());
					q.setQbRget(zh(charge.getQbType())+":"+String.valueOf(charge.getQbNum())+"个");
					q.setQbType(charge.getQbType());
					q.setRemark(zh(charge.getQbType())+"乾币充值"+charge.getQbNum()+"个。");
					System.out.println(q);
					
					Calendar Cld = Calendar.getInstance();
					int MI = Cld.get(Calendar.MILLISECOND);		//获取毫秒
					q.setMillisecond(MI);
					
					//这里手动的  更改用户钱币 与 增加钱币记录   1更改用户钱币   2增加钱币记录
					userMyQbDao.updateUserQb(String.valueOf(charge.getQbNum()), charge.getToken(), charge.getQbType());  //1
					
					//----为了获取钱币余额。。。。。
		 			Integer userQbNum=userMyQbDao.getUserQbNum(charge.getToken());
		 			q.setRemark(zh(charge.getQbType())+"乾币充值"+charge.getQbNum()+"个。（乾币余额："+userQbNum+"个）");
					userMyQbDao.add(q);
					//userMyQbService.add(q, token);
					System.out.println(3);
					return true;
				}/*else{
					//这里是不是应该 把state状态重置为1
					return "success";
				}*/
			}
			
		}
	   //订单支付安全检查
		Ordera order=aliPayDao.queryOrder(out_trade_no);
		if(/*!amount.equals(order.getActualPay())&&*/!order.getOrderId().equals(out_trade_no)){
			 return false;
		}
					if(order!=null){
						if(1==order.getState()){
							PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
							boolean falg=payAfterOrderUtil.universal(out_trade_no,payType);
							if(falg){
								return true;
							}else{
								return false;
							}
						}
					}
					return false;
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
		  return "非法乾币类型";
	}
	
	private String  mosaicString(String str){
		
		StringBuffer sb=new StringBuffer(str);
   		String[] orderDesArray=str.split(",");
   		System.out.println(orderDesArray.length);
   		for(int i=orderDesArray.length;i<4;i++){
   			sb.append("0,");
   		}
		return sb.toString();
	}
	
	//取出
}
