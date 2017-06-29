package com.yayiabc.http.mvc.service.Impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.ShippingAddressDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.pojo.jpa.Receiver;
import com.yayiabc.http.mvc.service.ShippingAddressService;
@Service
public class ShippingAdressServiceImpl implements ShippingAddressService{
	@Autowired
	private ShippingAddressDao shippingAddressDao;
	@Autowired
	UserDao userDao;
	@Override
	//新增收货地址
	public DataWrapper<Void> addUserAdress(Receiver receiver,String newPhone) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
	     String userId=userDao.getUserId(newPhone);
	     receiver.setUserId(userId);
		int state= shippingAddressDao.addUserAddress(receiver);
		if(state>0){
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作成功");
			return dataWrapper;
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作失败");
			return dataWrapper;
		}

	}
	//修改收货地址
	@Override
	public DataWrapper<Void> updateUserAddress(Receiver receiver,String newPhone) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		HashMap<String, Receiver> hMap= new HashMap<String,Receiver>();
		String userId=userDao.getUserId(newPhone);
		receiver.setUserId(userId);
		hMap.put("receiver",receiver );
		int sign=shippingAddressDao.updateUserAddress(hMap);
		if(sign>0){
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作成功");
			return dataWrapper;
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			dataWrapper.setMsg("操作失败");
			return dataWrapper;
		}
	}
	@Override
	public Integer addConditions(String newPhone) {
		String userId=userDao.getUserId(newPhone);
		Integer sign=shippingAddressDao.addConditions(userId);
		return sign;
	}
	@Override
	public int updateIsdefault(Integer receiverId) {
		// TODO Auto-generated method stub
		int state=shippingAddressDao.updateIsdefault(receiverId);
		return state;
	}
	@Override
	//显示收货地址
	public DataWrapper<List<Receiver>> showShoppingAddress(String phone) {
	 String uid=	userDao.getUserId(phone);
	 List<Receiver> receiverList= shippingAddressDao.showShoppingAddress(uid);
	 DataWrapper<List<Receiver>> dataWrapper=new DataWrapper<List<Receiver>>();
	 dataWrapper.setData(receiverList);
	 
	   if(!receiverList.isEmpty()){
		   dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		   return  dataWrapper;
	   }else{
		   dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		   dataWrapper.setMsg("暂无收货地址");
		   return  dataWrapper;
	   }
	}
	@Override
	public DataWrapper<Integer> deleteShoppingAddress(String receiverId) {
		// TODO Auto-generated method stub
     int state=shippingAddressDao.deleteShoppingAddress(receiverId);
     DataWrapper<Integer> dataWrapper=new DataWrapper<Integer>();
     dataWrapper.setData(state);
     if(state>0){
    	 dataWrapper.setMsg("删除成功");
     }else{
    	 dataWrapper.setMsg("删除失败");
     }
		return dataWrapper;
	}
}
