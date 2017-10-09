package com.yayiabc.http.mvc.service.Impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UserMyQbDao;
import com.yayiabc.http.mvc.dao.UserQbListDao;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.UserQbListService;

@Service
public class UserQbListServiceImpl implements UserQbListService {

	@Autowired
	UserQbListDao userQbListDao;
	@Autowired
	UserDao userDao;
	@Autowired
	UserMyQbDao userMyQbDao;
	
	@Override
	public DataWrapper<List<QbRecord>> list(String phone, String startDate,
			String endDate, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<QbRecord>> dataWrapper = new DataWrapper<List<QbRecord>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
	    page.setCurrentPage(currentPage);
		int totalNumber = userQbListDao.getCount(phone, startDate, endDate);
		dataWrapper.setPage(page, totalNumber);
		List<QbRecord> list = userQbListDao.list(phone, startDate, endDate,
				page);
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> update(Integer qbBalance, String phone,String qbType,String sign) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		String userId = userDao.getUserId(phone);
		Calendar Cld = Calendar.getInstance();
		int MI = Cld.get(Calendar.MILLISECOND);		//获取毫秒
		if (qbBalance < 0) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("请填写正确的乾币数");
		} else {
			if(sign.equals("1")){
				//减少
				 if(userQbListDao.updateDed(qbBalance, phone,qbType)>0){
					 if(!addQbRecord(userId,qbBalance,phone,qbType,sign,MI)){
						 dataWrapper.setMsg("减少钱币记录失败");
					 } 
				 }else{
					 dataWrapper.setMsg("减少失败");
				 }
				//增加钱币记录
			}else if(sign.equals("2")){
				//增加
				if(userQbListDao.updateAdd(qbBalance, phone,qbType)>0){
					if(!addQbRecord(userId,qbBalance,phone,qbType,sign,MI)){
						 dataWrapper.setMsg("增加钱币记录失败");
					 } 
				}else{
					dataWrapper.setMsg("增加失败");
				}
			}
	}
		return dataWrapper;
	}
  private boolean addQbRecord(String userId,Integer qbBalance,String phone,String qbType,String sign,int  MI){
	    QbRecord qbRecord = new QbRecord();
		qbRecord.setUserId(userId);
		//查询客户现在乾币余额
			Integer userQbNum=userMyQbDao.getUserQbNum(userId);
		if(sign.equals("1")){
			//增加 钱币减少记录
			qbRecord.setQbRout("（"+zh(qbType)+"） 乾币 "+qbBalance);
			
			qbRecord.setRemark("管理员修改乾币余额，扣除'"+qbBalance+"'乾币。（乾币余额："+userQbNum+"个。）");
		}else if(sign.equals("2")){
			//增加 钱币增加记录
			qbRecord.setQbRget("（"+zh(qbType)+"） 乾币 "+qbBalance);
			qbRecord.setRemark("管理员修改乾币余额，新增'"+qbBalance+"'乾币。（乾币余额："+userQbNum+"个。）");
		}
		//qbRecord.setQbBalances(qbBalance);
		qbRecord.setMillisecond(MI);
		if(userMyQbDao.add(qbRecord)>0){
			return true;
		}
		 return false;
  }
	@Override
	public DataWrapper<Map<String, Integer>> queryQb(String userPhone,String qbType) {
		DataWrapper<Map<String, Integer>> dataWrapper = new DataWrapper<Map<String, Integer>>();
		String userId = userDao.getUserId(userPhone);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			Integer qbBalance = userQbListDao.queryQb(userPhone,qbType);
			Map<String, Integer> map = new HashMap<String, Integer>();
			if (qbBalance == null) {
				map.put("qbBalance", 0);
			} else {
				map.put("qbBalance", qbBalance);
			}
			dataWrapper.setData(map);
		}
		return dataWrapper;
	}
	private String zh(String zh){
		  if(zh.equals("a_qb")){
			  return "\"8.0折\" ";
		  } else if(zh.equals("b_qb"))
		  {
			  return "\"9.0折\" ";
		  }else if(zh.equals("c_qb")){
			  return "\"9.5折\" ";
		  }else if(zh.equals("qb_balance")){
			  return "\"赠\" ";
		  }
		  return "非法钱币类型";
	}
}
