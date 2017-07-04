package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.http.mvc.dao.WitManageDao;
import com.yayiabc.http.mvc.pojo.jpa.With;
import com.yayiabc.http.mvc.service.WitManageService;
@Service
public class WitManageServiceImpl implements WitManageService{
	@Autowired 
	private WitManageDao witManageDao;
	@Override
	public DataWrapper<With> showWit(String phone) {
		// TODO Auto-generated method stub
		DataWrapper<With> dataWrapper=new DataWrapper<With>();
				With w=witManageDao.showWit(phone);
				dataWrapper.setData(w);
		return dataWrapper ;
	}
	//提交提现数据
	@Override
	public DataWrapper<Void> submitWit(
			With with
			){ 
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=witManageDao.submitWit(with);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
	public String getVerifyCode(String phone) {
       DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
       String code = VerifyCodeManager.getPhoneCode(phone);
       System.out.println(code);
       if (code == null) {
           return code;
       }
       System.out.println(phone);
       boolean result = HttpUtil.sendPhoneVerifyCode(code, phone);
       if (result){
              System.out.println(1);
       } else {
           System.out.println(2);
       }
       return null;
	}
//操作
	@Override
	public DataWrapper<Void> oper(
			int cashId
			){ 
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=witManageDao.oper(cashId);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
	
	//查询+显示
	@Override
	public DataWrapper<List<With>> query(String message, Integer state) {
		// TODO Auto-generated method stub
		DataWrapper<List<With>> dataWrapper =new DataWrapper<List<With>>();
		List<With> l= witManageDao.query(message,state);
		if(l.isEmpty()){
			dataWrapper.setMsg("暂无数据");
		}else{
			dataWrapper.setData(l);
			
		}
		
		return dataWrapper;
	}
	
}
