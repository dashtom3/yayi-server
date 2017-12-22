package com.yayiabc.http.mvc.service.Impl;


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.utils.RedisClient;
import com.yayiabc.http.mvc.dao.UserMyQbDao;
import com.yayiabc.http.mvc.dao.UserWithdrawalsDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.jpa.UserWitSetUp;
import com.yayiabc.http.mvc.pojo.jpa.UserWith;
import com.yayiabc.http.mvc.pojo.model.UserWithExtend;
import com.yayiabc.http.mvc.service.UserMyQbService;
import com.yayiabc.http.mvc.service.UserWithdrawalsService;

import redis.clients.jedis.Jedis;
@Service
public class UserWithdrawalsServiceImpl implements UserWithdrawalsService {
	@Autowired
	private UserWithdrawalsDao userWithdrawalsServiceDao;
	@Autowired
	private UtilsDao utilsDao;
	@Autowired
	private UserMyQbService userMyQbService;
	@Autowired 
	private UserMyQbDao  userMyQbDao;

	//提现列表的显示
	@Override
	public DataWrapper<Object> show(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub   currentPage
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		Page page=new Page();
		page.setNumberPerPage((Integer)hm.get("numberPerpage"));
		page.setCurrentPage((Integer)hm.get("currentPage"));
		hm.remove("numberPerpage");
		hm.remove("currentPage");
		hm.put("numberPerpage", page.getNumberPerPage());
		hm.put("currentNum", page.getCurrentNumber());
		//总条数
		int count=userWithdrawalsServiceDao.queryCount(hm);//totalnumber
		//				hMap.put("currentPage", page.getCurrentPage());

		List<User> userWithList=userWithdrawalsServiceDao.show(hm);
		dataWrapper.setPage(page,count);
		if(userWithList.isEmpty()){
			dataWrapper.setMsg("暂无数据");
		}
		dataWrapper.setPage(page, count);

		dataWrapper.setData(userWithList);
		return dataWrapper;
	}
	//提交提现申请
	@Override
	@Transactional
	public  DataWrapper<Object> submit(UserWith userWith,String token,String vCode) {
		// TODO Auto-generated method stub
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		User user=utilsDao.getUserByToken(token);

		if(user==null){
			dataWrapper.setMsg("toekn验证异常");
			return dataWrapper;
		}
		if(!vCode.equals(VerifyCodeManager.getPhoneCode(user.getPhone()))){
			dataWrapper.setMsg("验证码错误");
			return dataWrapper;
		}
		//验证是否是注册赠送乾币
		double giveQb=userWith.getGiveType();
		if(giveQb!=0){

		}
		userWith.setUserId(user.getUserId());
		//校验 用户是否是提现成功状态下 发起的提现申请
		Integer sign=userWithdrawalsServiceDao.queryWitSign(user.getUserId());
		if(sign==null||sign==2||sign==3){
			int userQb=user.getaQb()+user.getcQb()+user.getQbBalance();

			if(userWith!=null){
				if(userQb<userWith.getaType()+userWith.getcType()+userWith.getGiveType()){
					dataWrapper.setMsg("提现错误操作");
					throw new RuntimeException("提现错误");
				}else{
					//根据用户的提现选择 ，更改user表用户的钱币类型的个数
					if(userWithdrawalsServiceDao.updateUserQb(userWith)<=0){
						throw new RuntimeException("提现错误");
					}
					User user2=utilsDao.getUserByToken(token);
					//这里做处理 保存到用户提现表中  String.format("%.2f", f)
					int a=(int) userWith.getaType();
					int c=(int) userWith.getcType();
					int give=(int) userWith.getGiveType();
					userWith.setaType(utilsTwo(a*0.8));    //a
					userWith.setcType(utilsTwo(c*0.95));    //c
					userWith.setGiveType(utilsTwo(userWith.getGiveType()));    //give
					dataWrapper.setData( userWithdrawalsServiceDao.submit(userWith));
					//增加钱币记录
					//用户钱币余额
					int qbbalance=user2.getQbBalance();
					int qbnotwith=user2.getQbNotwtih();
					int aqb=user2.getaQb();
					int cqb=user2.getcQb();
					int userQbNum=qbbalance+aqb+cqb+qbnotwith;
					String qbBalance="\"赠：\""+qbbalance+"个；"+"\"8.0折\""+aqb+"个；"+"\"9.5折\""+cqb+"个；";
					userMyQbService.addMessageQbQ("\"赠\"："+give+"个， \"9.5折\"："+c+"个 ，  \"8.0折\"："+a+"个",user.getUserId(),"乾币提现。（乾币余额："+userQbNum+"个）",System.nanoTime(),qbBalance); //新增钱币记录表   
				}
			}
		}else {
			dataWrapper.setMsg("NONONO");
			return dataWrapper;
		}
		return dataWrapper;
	}

	//确定或者取消   x4wQPQTAcEwC 28571
	@Override
	public DataWrapper<Object> yesOrNo(String withId, Integer sign) {
		// TODO Auto-generated method stub
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		//取消
		if(sign==3){
			// withId 获取此用户 此次提现的内容
			UserWith userWith=userWithdrawalsServiceDao.queryFourQb(withId);
			int a=(int) (userWith.getaType()/0.8);
			int c=(int) (userWith.getcType()/0.95);
			int give=(int) userWith.getGiveType();
			if(userWith!=null){
				userWith.setaType(-Math.round(a));
				userWith.setcType(-Math.round(c));
				userWith.setGiveType(-Math.round(userWith.getGiveType()));
				int q=userWithdrawalsServiceDao.updateUserQb(userWith);
				if(q>0){
					if(userWithdrawalsServiceDao.determine(withId,sign)>0){
						//增加钱币记录

						//用户钱币余额
						User user=userMyQbDao.getUserQbNum(userWith.getUserId());
						int qbnotwith=user.getQbNotwtih();
						int qbbalance=user.getQbBalance();
						int aqb=user.getaQb();
						int cqb=user.getcQb();
						int userQbNum=qbbalance+aqb+cqb+qbnotwith;
						String qbBalance="\"赠：\""+qbbalance+"个；"+"\"8.0折\""+aqb+"个；";
						userMyQbService.addMessageQbQRget("\"赠\"："+give+"个， \"9.5折\"："+c+"个 ，  \"8.0折\"："+a+"个",userWith.getUserId(),"乾币提现审核不通过。（乾币余额："+userQbNum+"个）",System.nanoTime(),qbBalance); //新增钱币记录表   
						dataWrapper.setMsg("拒绝提现申请，成功");
					}
				}else{
					dataWrapper.setMsg("拒绝提现申请，失败");
				}
			}else{
				dataWrapper.setMsg("参数错误");
			}
		}
		//确定
		else if(sign==2){
			if(userWithdrawalsServiceDao.determine(withId,sign)>0){
				dataWrapper.setMsg("打款成功");
			}else{
				dataWrapper.setMsg("打款失败");
			}
		}else{
			dataWrapper.setMsg("参数异常");
		}
		return dataWrapper;
	}
	//设置提现类型
	@Override
	public DataWrapper<Object> setUpWitType(
			String token,
			String witType, String accountHolder, String oBank, String cardNumber) {
		// TODO Auto-generated method stub
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		String userId=utilsDao.getUserID(token);
		//先查询该用户是否已经设置过提现类型   如果已  update ; 未 insert
		//int isNo=userWithdrawalsServiceDao.queryIsSetUp(userId);
		/*if(isNo>0){
			//update
			if(userWithdrawalsServiceDao.updateWitType(userId,accountHolder,cardNumber,witType,oBank)>0){
				dataWrapper.setMsg("操作成功");
			}else{
				dataWrapper.setMsg("操作失败");
			}
		}else{*/
		//insert   values(#{userId},#{witType},#{accountHolder},#{cardNumber},#{oBank},NOW())
		if(userWithdrawalsServiceDao.insertWitType(userId,witType,accountHolder,cardNumber,oBank)>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		//}
		return dataWrapper;
	}
	//显示提现设置
	@Override
	public DataWrapper<Object> witSetUpShow(String token) {
		// TODO Auto-generated method stub
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		String userId=utilsDao.getUserID(token);
		UserWitSetUp userWitSetUp=userWithdrawalsServiceDao.witSetUpShow(userId);
		if(dataWrapper==null){
			dataWrapper.setData(null);
			dataWrapper.setMsg("暂无设置信息");
		}else{
			dataWrapper.setData(userWitSetUp);
		}
		return dataWrapper;
	}
	//显示当前用户的钱币余额
	@Override
	public DataWrapper<Object> showUserQbNum(String token) {
		// TODO Auto-generated method stub
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		String userId=utilsDao.getUserID(token);
		Integer sign=userWithdrawalsServiceDao.queryWitSign(userId);
		if(userId==null){
			dataWrapper.setMsg("NONONO");
		}else{
			User user=userWithdrawalsServiceDao.showUserQbNum(userId);
			if(user!=null) {
				dataWrapper.setData(user);
			}
			String useMaxQbNum=null;
			dataWrapper.setMsg(sign+"");
			

			useMaxQbNum=user.getaQb()+user.getcQb()+user.getQbBalance()+user.getQbNotwtih()+"";
			dataWrapper.setFl(useMaxQbNum);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Object> showUserQbNumPo(String token, String sumItemsPrice) {
		// TODO Auto-generated method stub
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		String userId=utilsDao.getUserID(token);
		Integer sign=userWithdrawalsServiceDao.queryWitSign(userId);
		
		Jedis jedis=RedisClient.getInstance().getJedis();
		if(userId==null){
			dataWrapper.setMsg("NONONO");
		}else{
			User user=userWithdrawalsServiceDao.showUserQbNum(userId);
			
			String useMaxQbNum=null;
			
			if(Integer.parseInt(sumItemsPrice)>=120){
				useMaxQbNum=user.getaQb()+user.getcQb()+user.getQbBalance()+user.getQbNotwtih()+"";
				dataWrapper.setMsg("2");
			}else{
				jedis.select(11);
				if(jedis.exists(userId)){
					System.out.println("不是首单。。。。。。。。。。。。。。。。。。。。。。");
					useMaxQbNum=user.getaQb()+user.getcQb()+user.getQbBalance()+user.getQbNotwtih()+"";
					dataWrapper.setMsg("1");
				}else{
					useMaxQbNum=user.getaQb()+user.getcQb()+user.getQbBalance()+"";
					dataWrapper.setMsg("0");
				}
			}
			

			if(user!=null) {
				dataWrapper.setData(user);
			}
			
//			dataWrapper.setMsg(sign+"");
			
			dataWrapper.setFl(useMaxQbNum);
			jedis.close();
		}
		return dataWrapper;
	}
	//保留两位小数
	private double utilsTwo(double i){
		return Double.parseDouble(String.format("%.2f", i));
	}
	@Override
	public DataWrapper<Object> latelyWithRecord(String token) {
		// TODO Auto-generated method stub
		String userId=utilsDao.getUserID(token);
		DataWrapper<Object> da=new DataWrapper<Object>();
		da.setData(userWithdrawalsServiceDao.latelyWithRecord(userId));
		return da;
	}
	/**
	 * 小喇叭提示
	 */
	@Override
	public DataWrapper< List<UserWithExtend>> withHornPrompt() {
		// TODO Auto-generated method stub
		DataWrapper<List<UserWithExtend>> dataWrapper=new DataWrapper<List<UserWithExtend>>();
		List<UserWithExtend> userWithExtendList = userWithdrawalsServiceDao.withHornPrompt();
		dataWrapper.setData(userWithExtendList);
		return dataWrapper;
	}
}
