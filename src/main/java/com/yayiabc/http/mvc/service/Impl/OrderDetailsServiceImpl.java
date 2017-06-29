package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.OrderDetailsDao;
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
	public DataWrapper<List<User>>  orderDetailsShow(HashMap<String,String> map,String newPhone) {
		// TODO Auto-generated method stub
		DataWrapper<List<User>> dataWrapper=new DataWrapper<List<User>>();
		String userId=userDao.getUserId(newPhone);
		map.put("phone", userId);
		List<User> orderDetailsList=orderdetailsDao.orderDetailsShow(map);
		
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



