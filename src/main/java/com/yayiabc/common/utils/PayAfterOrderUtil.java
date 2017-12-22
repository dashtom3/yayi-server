package com.yayiabc.common.utils;

import java.util.ArrayList;
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
import com.yayiabc.http.mvc.dao.TrainShowServiceDao;
import com.yayiabc.http.mvc.dao.UserMyQbDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Charge;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.TrainOrdera;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.UserMyQbService;

import redis.clients.jedis.Jedis;
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
	private TrainShowServiceDao trainShowServiceDao;
	
	@Autowired
	private UserMyQbDao userMyQbDao;
	//更改支付类型
	public  boolean universal(String orderId,String type){
		
		
	
		
		/**
		 * 如果redis里没有该订单 就说明该订单已经关闭
		 */
		RedisClient rc=RedisClient.getInstance();
		Jedis jedis=rc.getJedis();
		jedis.select(1);
		if(!jedis.exists("expireOrder"+orderId)){
			System.out.println("redis里不存在该订单，可能是订单已经过期...");
		     return false;	
		}
		
		if(type!=null){
			aliPayDao.updatePayType(orderId,type);
		}
		//更改订单状态与付款时间
		/*int ax=aliPayDao.updateStateAndPayTime(orderId);*/

		Ordera o=aliPayDao.queryOrder(orderId);
		
		RedisClient rcQ=RedisClient.getInstance();
		Jedis jedisQ=rcQ.getJedis();
		jedisQ.select(11);
		jedisQ.set(o.getUserId(), "");
		jedisQ.close();
		//QbRecord q=new QbRecord();
		if(o.getQbDed()!=0){
			//
			//更新到订单表最后一列
			int a=aliPayDao.saveLast(newQbDed(o.getUserId(),o.getQbDed(),o.getOrderId()
					,"下单使用"+o.getQbDed()+"个乾币。（乾币余额：userQbNum）订单编号："+orderId
					),o.getOrderId());
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
		if(o.getGiveQb()!=0){
			QbRecord q=new QbRecord();

			q.setQbRget("\"赠\"： "+o.getGiveQb()+"个");

			q.setUserId(o.getUserId()+"");
			
			q.setMillisecond(System.nanoTime());
			userMyQbDao.updateUserQb(o.getGiveQb()+"", o.getUserId(),"qb_balance");
			//查询乾币余额
			User user=userMyQbDao.getUserQbNum(o.getUserId());
			int qbbalance=user.getQbBalance();
			int aqb=user.getaQb();
			int cqb=user.getcQb();
			int qbNotwith=user.getQbNotwtih();
			int userQbNum=qbbalance+aqb+cqb+qbNotwith;
			
			//放入redis 维护赠送乾币数
			//limitWithQb(o.getUserId(),user.getQbBalance());
			
			q.setQbBalances("\"赠：\""+qbbalance+"个；"+"\"8.0折\""+aqb+"个；"+"\"9.5折\""+cqb+"个；");
			q.setRemark("下单获得"+o.getGiveQb()+"个乾币。（乾币余额："+userQbNum+"）订单编号："+orderId);
			userMyQbDao.add(q);
		}
		//判断 该用户是否绑定了销售员
		String saleId=utilsDao.getSaleIdByOrderId(orderId);
		if(saleId!=null&&!"".equals(saleId)){
			//删除redis exprice key
			jedis.del("expireOrder"+orderId);
			jedis.close();
			return SetSaleInCome(orderId,o.getUserId(),saleId);
		}
		//删除redis exprice key
		jedis.del("expireOrder"+orderId);
		jedis.close();
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
	public String  newQbDed(String userId,Integer  DedNum,String orderId
			,String remark
			){
	
		
		//dednum <= max used qb //钱币够用
		User u=utilsDao.queryUserByUserIdYa(userId);
         System.out.println(orderId+"orderIdorderIdorderIdorderIdorderIdorderId");
		//这里检查是否可以使用注册赠送的60钱币
				Jedis jedis =RedisClient.getInstance().getJedis();
				jedis.select(11);
				String sign=jedis.hget("isFirstOrders", orderId);
					if("Y".equals(sign)){
						u.setQbNotwtih(0);
					}
					System.out.println(u);
		List<Integer> listData=new ArrayList<Integer>();
		listData.add(u.getQbNotwtih());
		listData.add(u.getQbBalance()); 
		listData.add( u.getaQb());      
		listData.add( u.getcQb());   
		//放入redis 维护赠送乾币数
//		limitWithQb(userId,-u.getQbBalance());
		int a=DedNum;
		String qbDes="";
		StringBuffer sb=new StringBuffer();
		StringBuilder sb1=new StringBuilder();
		String qbRout=null;
		for(int i=0;i<listData.size();i++){

			if(listData.get(i)>=DedNum){
				System.out.println(listData.get(i)+"   "+DedNum);
				sb1.append(DedNum+",");
				qbDes=sb1.toString();
				qbRout=sb.toString()+ut(i)+DedNum+"个";
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
		QbRecord qr=new QbRecord();
		qr.setQbRout(qbRout);
		//根据orderId  来判断这个方法的使用。---代加 
		qr.setRemark(remark);
		qr.setMillisecond(System.nanoTime());
		System.out.println(listData);
		
		//这里检查是否可以使用注册赠送的60钱币
		//这里 首单支付  商品价格小于120  不扣除 注册赠送钱币，
		if("Y".equals(jedis.hget("isFirstOrders", orderId))){
			listData.remove(0);
			listData.add(0,60);
		}
		System.out.println(listData);
		addQbRecord(listData,userId,qr);
		//userMyQbService.addMessageQbQ(qbRout,userId,"下单使用"+a+"个乾币。（乾币余额："+userQbNum+"）订单编号："+orderId,System.nanoTime()); //新增钱币记录表   
		return mosaicString(qbDes);
	}

	/**
	 * 增加乾币记录工具类（更改用户乾币）
	 * listData，0下标是qb_balance，1下标是a_qb，2下标是c_qb
	 * userId，用户id
	 * 
	 * @return
	 */
	public  boolean addQbRecord(List<Integer> listData,String userId,QbRecord qr){
		//更改用户乾币
		System.out.println(listData.size());
		int i=userMyQbService.updateDataToUser(listData,userId);
		//查询乾币余额
		User user=userMyQbDao.getUserQbNum(userId);
		int qbbalance=user.getQbBalance();
		int aqb=user.getaQb();
		int cqb=user.getcQb();
		int qbNotwith=user.getQbNotwtih();
		int userQbNum=qbbalance+aqb+cqb+qbNotwith;
		
		String qbBalance="\"赠：\""+qbbalance+qbNotwith+"个；"+"\"8.0折\""+aqb+"个；"+"\"9.5折\""+cqb+"个；";

		int iii=userMyQbService.addMessageQbQ(qr.getQbRout(),userId,qr.getRemark().replace("userQbNum",userQbNum+""),qr.getMillisecond(),qbBalance);
		if(i+iii>=2){
			return true;
		}
		return false;
	}
	private String ut(int i){
		switch (i) {
		case 0:
			return "\"赠（不可提现）\"：";
		case 1:
			return "\"赠（可提现）\"：";
		case 2:
			return "\"8.0折\"：";
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
		if("zfb".equals(out_trade_no.substring(0, 3))/*||"wx".equals(out_trade_no.substring(0, 2))*/){
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
					QbRecord q=new QbRecord();
					q.setUserId(charge.getToken());
					q.setChargeId(out_trade_no);
					q.setQbRget(zh(charge.getQbType())+":"+String.valueOf(charge.getQbNum())+"个");
					q.setQbType(charge.getQbType());
					q.setRemark(zh(charge.getQbType())+"乾币充值"+charge.getQbNum()+"个。");
					System.out.println(q);

					//获取毫秒
					q.setMillisecond(System.nanoTime());

					//这里手动的  更改用户钱币 与 增加钱币记录   1更改用户钱币   2增加钱币记录
					userMyQbDao.updateUserQb(String.valueOf(charge.getQbNum()), charge.getToken(), charge.getQbType());  //1

					//----为了获取钱币余额。。。。。
					User user=userMyQbDao.getUserQbNum(charge.getToken());
					int qbbalance=user.getQbBalance();
					int aqb=user.getaQb();
					int cqb=user.getcQb();
					int qbNotwith=user.getQbNotwtih();
					int userQbNum=qbbalance+aqb+cqb+qbNotwith;
					q.setRemark(zh(charge.getQbType())+"乾币充值"+charge.getQbNum()+"个。（乾币余额："+userQbNum+"个）");
					//支付宝乾币充值
					q.setQbBalances("\"赠：\""+qbbalance+"个；"+"\"8.0折\""+aqb+"个；"+"\"9.5折\""+cqb+"个；");
					q.setReferer(1);
					userMyQbDao.add(q);
					//userMyQbService.add(q, token);
					return true;
				}/*else{
					//这里是不是应该 把state状态重置为1
					return "success";
				}*/
			}

		}
		//订单支付安全检查
		Ordera order=aliPayDao.queryOrder(out_trade_no);
		if(order!=null){
			if(/*!amount.equals(order.getActualPay())&&*/!order.getOrderId().equals(out_trade_no)){
				return false;
			}
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
		if("a_qb".equals(zh)){
			return "\"8.0折\" ";
		} else if("b_qb".equals(zh))
		{
			return "\"9.0折\" ";
		}else if("c_qb".equals(zh)){
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

	//
	public boolean trainOrderCallBackUtils(String trainOrderaId, String amount){
		TrainOrdera trainOrdera=utilsDao.queryTrainOrder(trainOrderaId);
		if(trainOrdera==null){
			return false;
		}
		System.out.println(amount+"     "+trainOrdera.getActualPrice());
		if(!trainOrderaId.equals(trainOrdera.getTrainOrderId())||!amount.equals(trainOrdera.getActualPrice())){
			return false;
		}
		
		//更改用户乾币
		if(trainOrdera.getQbDed()!=null&&trainOrdera.getQbDed()>0){
			newQbDed(trainOrdera.getUserId(),trainOrdera.getQbDed(),trainOrdera.getTrainOrderId()+""
					,"报名培训使用"+trainOrdera.getQbDed()+"个乾币。（乾币余额：userQbNum）"
					);
		}
		//更改培训订单表订单 状态
		int s=trainShowServiceDao.updateTrainOrderaState(trainOrderaId);
		if(s<0){
			return false;
		}
		return true;
	}
	/**
	 * 项目前期 注册送60乾币，限制用户这60乾币 不能直接提现
	 */
	/*public void  limitWithQb(String userId,int qb){
		
		RedisClient rc=RedisClient.getInstance();
		Jedis jedis=rc.getJedis();
		if(jedis.hexists("userGiveQbNums", userId)){
			//有
			jedis.hincrBy("userGiveQbNums", userId, qb);
		}else{
			 jedis.hset("userGiveQbNums", userId,qb+"");
		}
		jedis.close();
	}*/
}
