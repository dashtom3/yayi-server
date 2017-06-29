package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UserMyQbDao;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.UserMyQbService;

@Service
public class UserMyQbServiceImpl implements UserMyQbService {

	@Autowired
	UserMyQbDao userMyQbDao;
	@Autowired
	UserDao userDao;
	
	@Override
	public DataWrapper<QbRecord> add(QbRecord qbRecord,String phone) {
		DataWrapper<QbRecord> dataWrapper=new DataWrapper<QbRecord>();
		String userId = userDao.getUserId(phone);
		if(userId == null){
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		}else{
			qbRecord.setUserId(userId);
			int id=userMyQbDao.add(qbRecord);
			if(id>0){
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
				dataWrapper.setData(qbRecord);
			}else{
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<QbRecord>> query(String phone,Integer type, Page page) {
		DataWrapper<List<QbRecord>> dataWrapper = new DataWrapper<List<QbRecord>>();
		String userId = userDao.getUserId(phone);
		if(userId == null){
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		}else{
			switch (type) {
			case 1:
				//查询全部
				List<QbRecord> list = userMyQbDao.query(page,userId);
				int totalNumber = userMyQbDao.getCount(userId);
				dataWrapper.setPage(page, totalNumber);
				dataWrapper.setData(list);
				break;
			case 2:
				//查询收入
				List<QbRecord> list1 = userMyQbDao.queryRget(page,userId);
				int totalNumber1 = userMyQbDao.getCountRget(userId);
				dataWrapper.setPage(page, totalNumber1);
				dataWrapper.setData(list1);
				break;
			case 3:
				//查询支出
				List<QbRecord> list2 = userMyQbDao.queryRout(page,userId);
				int totalNumber2 = userMyQbDao.getCountRout(userId);
				dataWrapper.setPage(page, totalNumber2);
				dataWrapper.setData(list2);
				break;
			default:
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				break;
			}
			
		}
		return dataWrapper;
	}

}
