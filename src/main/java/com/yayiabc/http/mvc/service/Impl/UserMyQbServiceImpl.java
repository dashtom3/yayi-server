package com.yayiabc.http.mvc.service.Impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.UserMyQbDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.UserMyQbService;

@Service
public class UserMyQbServiceImpl implements UserMyQbService {

	@Autowired
	UserMyQbDao userMyQbDao;
	@Autowired
	UtilsDao utilsDao;

	@Override
	public DataWrapper<Void> add(QbRecord qbRecord, String token) {
		String userId=null;
		if(token.contains("userId")){
			userId=token.substring(0, 6);
		}else{
			userId= utilsDao.getUserID(token);
		}
		Calendar Cld = Calendar.getInstance();
		int MI = Cld.get(Calendar.MILLISECOND);		//获取毫秒
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		 
		if (userId == null) {
			dataWrapper.setMsg("token错误");
		} else {
			qbRecord.setUserId(userId);
			Integer qb = userMyQbDao.queryQbbalance(userId,qbRecord.getQbType());
			if (qbRecord.getQbRget() != null && qbRecord.getQbRget() > 0) {
				userMyQbDao.updateUserQb(qbRecord.getQbRget(), userId,qbRecord.getQbType());
				
				qbRecord.setMillisecond(MI);
				userMyQbDao.add(qbRecord);
			} else if (qbRecord.getQbRout() != null && qbRecord.getQbRout() < 0) {
				if (Math.abs(qbRecord.getQbRout()) > qb) { // 判断支出乾币数是否大于已有余额
					dataWrapper.setMsg("乾币增减错误！");
				} else {
					userMyQbDao.updateUserQb(qbRecord.getQbRout(), userId,qbRecord.getQbType());
					qbRecord.setQbBalances(qb+qbRecord.getQbRout());
					qbRecord.setMillisecond(MI);
					userMyQbDao.add(qbRecord);
				}
			} else  {
				dataWrapper.setMsg("请正确填写的乾币增减数额！");
			}
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<QbRecord>> query(String token,Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<QbRecord>> dataWrapper = new DataWrapper<List<QbRecord>>();
		String userId = utilsDao.getUserID(token);
		if (userId == null) {
			dataWrapper.setMsg("token错误");
		} else {
			Page page = new Page();
			page.setNumberPerPage(numberPerPage);
			page.setCurrentPage(currentPage);
			int totalNumber = userMyQbDao.getCount(userId);
			dataWrapper.setPage(page, totalNumber);
			List<QbRecord> list = userMyQbDao.query(userId,page);
			dataWrapper.setData(list);
		}
		return dataWrapper;
	}

	@Override
	public int updateDataToUser(List<Integer> listData,String userId) {
		// TODO Auto-generated method stub
		
		return userMyQbDao.updateDataToUser(listData,userId);
	}

	@Override
	public int addMessageQbQ(int dedNums, String userId, String s,int Mi) {
		// TODO Auto-generated method stub
		return userMyQbDao.addMessageQbQ(dedNums,userId,s,Mi);
	}
}
