package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.OrderDetailsDao;
import com.yayiabc.http.mvc.dao.UserCenterStarDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsDao orderdetailsDao;
	@Autowired
	UserDao userDao;
	@Override
	public DataWrapper<List<User>>  orderDetailsShow(HashMap<String,String> map,String newPhone
			,Integer currentPage,Integer numberPerpage){
		// TODO Auto-generated method stub
		Page page=new Page();
		if(currentPage!=null&numberPerpage!=null){
		page.setNumberPerPage(numberPerpage);
		page.setCurrentPage(currentPage);
		}else{
			page.setNumberPerPage(10);
			page.setCurrentPage(1);
		}
		
        		
		DataWrapper<List<User>> dataWrapper=new DataWrapper<List<User>>();
		String userId=userDao.getUserId(newPhone);
		map.put("phone", userId);
		map.put("currentPage", String.valueOf(page.getCurrentPage()));
		map.put("numberPerpage", String.valueOf(page.getNumberPerPage()));
		//总条数
				int count=orderdetailsDao.queryCount(map);
		List<User> orderDetailsList=orderdetailsDao.orderDetailsShow(map);
		dataWrapper.setPage(page, count);
		dataWrapper.setData(orderDetailsList);
		
		if(orderDetailsList.isEmpty()){
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("暂订单为空");
			return dataWrapper;
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			return dataWrapper;
		}
	}
}



