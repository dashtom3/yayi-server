package com.yayiabc.http.mvc.service.Impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.UserWithdrawalsDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.jpa.UserWith;
import com.yayiabc.http.mvc.pojo.model.OrderManagement;
import com.yayiabc.http.mvc.service.UserWithdrawalsService;
@Service
public class UserWithdrawalsServiceImpl implements UserWithdrawalsService {
	@Autowired
	private UserWithdrawalsDao userWithdrawalsServiceDao;
	@Autowired
	private UtilsDao utilsDao;
	
	//提现列表的显示
	@Override
	public DataWrapper<Object> show(HashMap<String, Object> hm) {
		// TODO Auto-generated method stub
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		Page page=new Page();
		int count=userWithdrawalsServiceDao.queryCount(hm);//totalnumber
		//				hMap.put("currentPage", page.getCurrentPage());
		hm.put("numberPerpage", page.getNumberPerPage());
		Integer currentNum=page.getCurrentNumber();
		hm.put("currentNum", currentNum);
		List<UserWith> userWithList=userWithdrawalsServiceDao.show(hm);
		if(userWithList.isEmpty()){
			dataWrapper.setMsg("暂无数据");
		}
			dataWrapper.setPage(page, count);
		
		dataWrapper.setData(userWithList);
		return dataWrapper;
	}
	//提交提现申请
	@Override
	public  DataWrapper<Object> submit(UserWith userWith,String token) {
		// TODO Auto-generated method stub
		//List<Integer> list=new ArrayList<Integer>();
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		User user=utilsDao.getUserByToken(token);
		userWith.setUserId(user.getUserId());
		int userQb=user.getaQb()+user.getbQb()+user.getcQb()+user.getQbBalance();
		if(userWith!=null){
          if(userQb<userWith.getaType()+userWith.getbType()+userWith.getcType()+userWith.getGiveType()){
        	  dataWrapper.setMsg("提现错误操作");
        	  throw new RuntimeException("提现错误");
          }else{
        	  //根据用户的提现选择 ，更改user表用户的钱币类型的个数
        	  userWithdrawalsServiceDao.updateUserQb(userWith);
        	  //这里做处理 保存到用户提现表中
        	  userWith.setaType(Math.round(userWith.getaType()*0.8));
        	  userWith.setbType(Math.round(userWith.getbType()*0.9));
        	  userWith.setcType(Math.round(userWith.getcType()*0.95));
        	  userWith.setGiveType(Math.round(userWith.getGiveType()));
        	  dataWrapper.setData( userWithdrawalsServiceDao.submit(userWith));
          }
		}
		 return dataWrapper;
	}
	
	//确定或者取消
	@Override
	public DataWrapper<Object> yesOrNo(String withId, Integer sign) {
		// TODO Auto-generated method stub
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		//取消
		if(sign==1){
			// withId 获取此用户 此次提现的内容
			UserWith userWith=userWithdrawalsServiceDao.queryFourQb(withId);
			  userWith.setaType(-Math.round(userWith.getaType()/0.8));
        	  userWith.setbType(-Math.round(userWith.getbType()/0.9));
        	  userWith.setcType(-Math.round(userWith.getcType()/0.95));
        	  userWith.setGiveType(-Math.round(userWith.getGiveType()));
        	  int a=userWithdrawalsServiceDao.submit(userWith);
        	  if(a>0){
        		  dataWrapper.setMsg("操作成功");
        	  }else{
        		  dataWrapper.setMsg("操作失败");
        	  }
		}
		//确定
		else{
			if(userWithdrawalsServiceDao.determine(withId)>0){
				 dataWrapper.setMsg("操作成功");
			}
		}
		return dataWrapper;
	}

}
