package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.dao.WitManageDao;
import com.yayiabc.http.mvc.pojo.jpa.With;
import com.yayiabc.http.mvc.service.WitManageService;
@Service
public class WitManageServiceImpl implements WitManageService{
	@Autowired 
	private WitManageDao witManageDao;
	@Autowired
	private UtilsDao utilsDao;
	@Override
	public DataWrapper<With> showWit(String token) {
		// TODO Auto-generated method stub
		DataWrapper<With> dataWrapper=new DataWrapper<With>();
		String userId=utilsDao.getUserID(token);
		String phone=witManageDao.getPhone(userId);
		With w=witManageDao.showWit(phone);
		dataWrapper.setData(w);
		return dataWrapper ;
	}
	//提交提现数据
	@Override
	public DataWrapper<Void> submitWit(
			With with,String saleToken
			){ 
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=witManageDao.submitWit(with);
		//保存时间到    makerTime表里
		String saleId=utilsDao.getSaleId(saleToken);
		
		       int s=witManageDao.setTime(saleId);
		if(state>0&&s>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		return dataWrapper;
	}
	public String getVerifyCode(String phone) {
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
		//根据cashId  查询提现金额，
		//查
		With w=witManageDao.queryMessage(cashId);
		/**
		 *提现管理表状态改变，该销售员 钱包余额减少，
		 */
	  
		  //查询当前销售员 钱包余额
		int sign=0;
		Integer nowMoney=witManageDao.queryMoney(w.getSaleId());
		if(nowMoney!=null){
			if(nowMoney>w.getCashMoney()){
				  sign=witManageDao.deleteMoney(w.getCashId(),w.getCashMoney());	
			}
		}
		 //钱包余额减少，
		
		//放
		//witManageDao.insertMoney(w);
		if(state>0&&sign>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("操作失败,该销售员钱包余额小于此次提现余额！");
		}
		return dataWrapper;
	}

	//查询+显示
	@Override
	public DataWrapper<List<With>> query(String message, String state) {
		// TODO Auto-generated method stub
		DataWrapper<List<With>> dataWrapper =new DataWrapper<List<With>>();
		List<With> l= witManageDao.query(message,state);
		if(l.isEmpty()){
			dataWrapper.setMsg("暂无数据");
		}else{
			System.out.println(l);
			dataWrapper.setData(l);
		}

		return dataWrapper;
	}

}
