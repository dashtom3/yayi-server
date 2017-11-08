package com.yayiabc.http.mvc.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.yayiabc.common.alipayenclos.config.AlipayConfig;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.utils.RedisClient;
import com.yayiabc.common.utils.SerializeUtil;
import com.yayiabc.http.mvc.dao.TrainShowServiceDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Train;
import com.yayiabc.http.mvc.pojo.jpa.TrainDetail;
import com.yayiabc.http.mvc.pojo.jpa.TrainOrdera;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.TrainShowService;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

@Service
public class TrainShowServiceImpl implements TrainShowService {
	@Autowired 
	private TrainShowServiceDao trainShowServiceDao;
	@Autowired 
	private  UtilsDao utilsDao;
	@Override 
	public DataWrapper<List<Train>> show(String classly, Integer currentPage, Integer numberPerpage) {
		// TODO Auto-generated method stub
		DataWrapper< List<Train>> dataWrapper =new DataWrapper<List<Train>>();
		//到缓存中拿 前提classly为null（非条件查询）
		if(classly==null){
			List<Train> list	=getUpCache(currentPage,numberPerpage);
			if(list!=null&&!list.isEmpty()){
				dataWrapper.setData(list);
				System.out.println(12312321);
				return dataWrapper;
			}
		}
		Page page=new Page();
		page.setNumberPerPage(numberPerpage);
		page.setCurrentPage(currentPage);

		int count=trainShowServiceDao.queryCount(classly);
		//System.out.println(page.getCurrentNumber()+"     "+page.getNumberPerPage()+  classly);
		List<Train> trainList=trainShowServiceDao.show(classly,page.getCurrentNumber(),page.getNumberPerPage());
		dataWrapper.setData(trainList);
		dataWrapper.setPage(page, count);

		return dataWrapper;
	}
	private List<Train> getUpCache(Integer currentPage, Integer numberPerpage){
		//计算分页数据
		Page page=new Page();
		page.setNumberPerPage(numberPerpage);
		page.setCurrentPage(currentPage);
		
		RedisClient redisClient=RedisClient.getInstance();
		Jedis jedis=redisClient.getJedis();
		System.out.println(page.getCurrentNumber()+"  "+page.getNumberPerPage());
		Set<byte[]> set=jedis.zrevrange("train:set".getBytes(), page.getCurrentNumber(),page.getNumberPerPage());
		List<Train> catcheList=new ArrayList<Train>();
		for (byte[] str : set) { 
			Train train=(Train) SerializeUtil.unserialize(str);
			catcheList.add(train);
		}  
		redisClient.returnJedis(jedis);
		System.err.println(catcheList);
		return catcheList;
	}
	/**
	 * 发布培训
	 */
	@Override
	public DataWrapper<Void> releaseTrain(Train  train, TrainDetail  trainDetails) {
		// TODO Auto-generated method stub
		
		DataWrapper<Void>  dataWrapper=new DataWrapper<Void>();
		//插入到培训表 返回主键  在train里
		trainShowServiceDao.insertTrain(train);
		System.out.println(train.getTrainId());
		trainShowServiceDao.insertTrainDetails(train.getTrainId(),trainDetails);
		System.out.println(trainDetails.getTrainDetailsId());
		System.out.println(train);
		System.out.println();
		System.out.println(trainDetails);
		dataWrapper.setMsg("发布成功");
		//放入缓存中
		setToCache(train,trainDetails);
		return dataWrapper;
	}

	private void setToCache(Train  train,TrainDetail  trainDetails){
		//获取redisClient实例
		RedisClient redisClient=RedisClient.getInstance();
		Jedis jedis=redisClient.getJedis();
		//将列表页存入jedis list结构中
		//		redisClient.set("train:findClass:"+train.getClassly()+train.getTrainId(), SerializeUtil.serialize(train));
		//redisClient.set(("train:findClass:"+train.getClassly()+train.getTrainId()).getBytes(), SerializeUtil.serialize(train));
		//将列表页放入zset中
		jedis.zadd("train:set".getBytes(), System.currentTimeMillis(),  SerializeUtil.serialize(train));
		//详情页存入到jedis hash结构中 pass
		//redisClient.HASH.hset("TrainDetail", train.getTrainId()+"ToTrainDetails", new SerializeUtil().serialize(trainDetails));

		//详情页放入String中
		jedis.set((train.getTrainId()+"ToTrainDetails").getBytes(),SerializeUtil.serialize(trainDetails));
		redisClient.returnJedis(jedis);
	}
	/**
	 * 培训详情的显示
	 */
	@Override
	public DataWrapper<TrainDetail> trainDetails(String trainId) {
		// TODO Auto-generated method stub
		DataWrapper<TrainDetail>  dataWrapper=new DataWrapper<TrainDetail>();
		//缓存中查找
		RedisClient redisClient=RedisClient.getInstance();
		byte[] byts=redisClient.get((trainId+"ToTrainDetails").getBytes());
		if(byts!=null){
			TrainDetail trainDetail=(TrainDetail) SerializeUtil.unserialize(byts);
			System.out.println(trainDetail);
			dataWrapper.setData(trainDetail);
			return dataWrapper;
		}
		
		TrainDetail trainDetail=trainShowServiceDao.trainDetails(trainId);
		dataWrapper.setData(trainDetail);
		return dataWrapper;
	}
	/**
	 * 分享
	 */
	 public DataWrapper<Void> share(String trainId){
		RedisClient redisClient=RedisClient.getInstance();
		Jedis jedis=redisClient.getJedis();
		byte[] byts=redisClient.get((trainId+"ToTrainDetails").getBytes());
		TrainDetail trainDetail=(TrainDetail) SerializeUtil.unserialize(byts);
		trainDetail.setShareAddOne();
		//重新添加  （覆盖作用）
		jedis.set((trainId+"ToTrainDetails").getBytes(),SerializeUtil.serialize(trainDetail));
		
		redisClient.returnJedis(jedis);
		return new DataWrapper<Void>();
	}
	/**
	 * 点赞
	 * 
	 */
	 public DataWrapper<Void> spotFabulous(String trainId){
			RedisClient redisClient=RedisClient.getInstance();
			Jedis jedis=redisClient.getJedis();
			byte[] byts=redisClient.get((trainId+"ToTrainDetails").getBytes());
			TrainDetail trainDetail=(TrainDetail) SerializeUtil.unserialize(byts);
			trainDetail.setCollectionAddOne();
			//重新添加  （覆盖作用）
			jedis.set((trainId+"ToTrainDetails").getBytes(),SerializeUtil.serialize(trainDetail));
			
			redisClient.returnJedis(jedis);
			return new DataWrapper<Void>();
	 }
			
	/**
	 * 确定报名
	 */
	@Override
	public DataWrapper<Object> confirmRegistration(String token, TrainOrdera trainOrdera) {
		// TODO Auto-generated method stub
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		User user=utilsDao.getUserByToken(token);
		trainOrdera.setUserId(user.getUserId());
		//判断乾币抵扣的正确性
		System.out.println(user);
		int userTotalQbs=user.getQbBalance()+user.getaQb()+user.getbQb()+user.getcQb();
		if(trainOrdera.getQbDed()>userTotalQbs){
			dataWrapper.setMsg("乾币错误，请稍后再试");
			return dataWrapper;
		}
		//根据trainId查询出培训金额放入订单表
		Train train=trainShowServiceDao.queryTrainPrice(trainOrdera.getTrainId()+"");
		//实际付款为:
		Double actualPrice=Double.parseDouble(train.getTrainPrice())-trainOrdera.getQbDed();
		trainOrdera.setActualPrice(actualPrice+"");
		trainOrdera.setUnitPrice(train.getTrainPrice());
		//信息保存进培训订单表
		int sign=trainShowServiceDao.insertTrainOrder(trainOrdera);
		if(sign<0){
			dataWrapper.setMsg("报名失败");
			return dataWrapper;
		}
		//支付
		if(trainOrdera.getPayType().equals("0")){
			//支付宝
			return packingParameter(trainOrdera.getTrainOrderId()+"",actualPrice+"",train.getTrainName(),train.getTrainName(),"QUICK_MSECURITY_PAY");	
		}else if(trainOrdera.getPayType().equals("1")){
			//微信
		}
		return dataWrapper;
	}

	//封装支付宝支付参数
	private   DataWrapper<Object> packingParameter( String out_trade_no, String total_fee, String body, String subject,
			String QUICK_MSECURITY_PAY) {
		// TODO Auto-generated method stub
		// 实例化客户端  
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, 
				"json", AlipayConfig.input_charset, AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");  
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay  
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();  

		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。  
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(body);  
		model.setSubject(subject);  
		model.setOutTradeNo(out_trade_no);  
		model.setTimeoutExpress("30m");  
		model.setTotalAmount(total_fee);  
		model.setProductCode(QUICK_MSECURITY_PAY);  
		request.setBizModel(model);  
		request.setNotifyUrl("http://47.93.48.111:6181/api/train/callBack");//回调地址  
		//request.setReturnUrl("http://www.yayiabc.com/center/myMoney");  
		String orderInfo = null; 
		try {  
			//这里和普通的接口调用不同，使用的是sdkExecute  
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);  
			//response.getBody()就是orderString 可以直接给客户端请求，无需再做处理。  
			orderInfo = response.getBody();  
		} catch (AlipayApiException e) {  
			e.printStackTrace();  
		}  
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>(); 
		dataWrapper.setData(orderInfo);
		dataWrapper.setMsg(out_trade_no);
		return dataWrapper; 
	}


}
