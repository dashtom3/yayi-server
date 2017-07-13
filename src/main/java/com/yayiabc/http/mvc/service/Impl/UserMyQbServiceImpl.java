package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
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
	public DataWrapper<Void> add(QbRecord qbRecord,String token) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		String userId=utilsDao.getUserID(token);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("token错误");
		} else {
			
			qbRecord.setUserId(userId);
			if (qbRecord.getQbRget() != null && qbRecord.getQbRget() > 0) {
				userMyQbDao.updateUserQb(qbRecord.getQbRget(), userId);
			} else if (qbRecord.getQbRout() != null && qbRecord.getQbRout() < 0) {
				userMyQbDao.updateUserQb(qbRecord.getQbRout(), userId);
			}
			
			qbRecord.setQbBalances(userMyQbDao.queryQbbalance(userId));
			int id = userMyQbDao.add(qbRecord);
			if (id > 0) {
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<QbRecord>> query(String token,Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<QbRecord>> dataWrapper = new DataWrapper<List<QbRecord>>();
		String userId = utilsDao.getUserID(token);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
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

}
