package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.http.mvc.dao.WitManageDao;
import com.yayiabc.http.mvc.service.WitManageService;
@Service
public class WitManageServiceImpl implements WitManageService{
	@Autowired 
	private WitManageDao witManageDao;
	@Override
	public DataWrapper<Void> showWit(String phone) {
		// TODO Auto-generated method stub
		
		return  witManageDao.showWit(phone);
	}
	//提交提现数据
	public DataWrapper<Void> submitWit(String moneyNnm, String phone,
			String vCode){
		
		/*String serverCode = VerifyCodeManager.getPhoneCode(phone);
		System.out.println(serverCode);
		if(serverCode.equals(vCode)){
			System.out.println("验证成功");
		}*/
		getVerifyCode(phone)
;		return null;
	}
	
	//
	public DataWrapper<Void> getVerifyCode(String phone) {
       DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
       String code = VerifyCodeManager.getPhoneCode(phone);
       System.out.println(code);
       if (code == null) {
           dataWrapper.setErrorCode(ErrorCodeEnum.Verify_Code_5min);
           dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
           return dataWrapper;
       }
       boolean result = HttpUtil.sendPhoneVerifyCode(code, phone);
       if (result) {
           dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
           dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
       } else {
           dataWrapper.setErrorCode(ErrorCodeEnum.Error);
           dataWrapper.setMsg(dataWrapper.getErrorCode().getLabel());
       }
       return dataWrapper;
	}
}
