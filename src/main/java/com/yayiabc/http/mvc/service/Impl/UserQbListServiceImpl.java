package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.UserQbListDao;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.UserQbListService;

@Service
public class UserQbListServiceImpl implements UserQbListService {

	@Autowired
	UserQbListDao userQbListDao;

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
		if(list.size()==0){
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}else if(list.size()!=0){
			dataWrapper.setData(list);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> update(Integer qbBalance, String phone) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		int i = userQbListDao.update(qbBalance, phone);
		if (i > 0) {
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

}
