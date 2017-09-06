package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.sessionManager.VerifyCodeManager;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.HttpUtil;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.dao.WitManageDao;
import com.yayiabc.http.mvc.pojo.jpa.Balance;
import com.yayiabc.http.mvc.pojo.jpa.With;
import com.yayiabc.http.mvc.pojo.model.SaleWitModel;
import com.yayiabc.http.mvc.service.WitManageService;
@Service
public class WitManageServiceImpl implements WitManageService{
	@Autowired 
	private WitManageDao witManageDao;
	@Autowired
	private UtilsDao utilsDao;

	//提交提现数据
	@Override
	public DataWrapper<Void> submitWit(
			String saleToken,String balanceOut
			){ 
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		String saleId=utilsDao.getSaleId(saleToken);
		//根据saleId  得到该sale的钱包余额
		//防止 非法用户直接调接口提现
	List<SaleWitModel> saleNowMoney=witManageDao.queryMoney(saleId);
		if(saleNowMoney.get(0).getDescribey().substring(0,6).equals("提现申请中")){
			dataWrapper.setMsg("NONONO");
			return dataWrapper;
		}
	if(!saleNowMoney.isEmpty()){
		int state=0;
		if(saleNowMoney.get(0).getBalance()>=Double.parseDouble(balanceOut)){
			state=witManageDao.submitWit(saleId,Double.parseDouble(balanceOut),saleNowMoney.get(0).getBalance()
					,"提现申请中:"+balanceOut
					);
			if(state>0){
				dataWrapper.setMsg("请求已发送");
				return dataWrapper;
			}
			
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("账户余额不足");
			return dataWrapper;
		}
	}
		/*//根据saleId  得到sale
		SaleInfo saleInfo=utilsDao.getSaleBySaleId(saleId);*/
		
	    
		
		       
		 dataWrapper.setMsg("该用户钱包余额为空");
		return dataWrapper;
	}
	public String getVerifyCode(String phone) {
		String code = VerifyCodeManager.getPhoneCode(phone);
		if (code == null) {
			return code;
		}
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
			int balacceId
			){
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		Balance balance=witManageDao.queryBalance(balacceId);
		Double dPrice=balance.getBalance()-balance.getBalanceOut();
		int sign=witManageDao.oper(balacceId,dPrice,"出账提现成功:"+balance.getBalanceOut());
		if(sign>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("操作失败");
		}
				return dataWrapper;
	}

	//查询+显示
	@Override
	public DataWrapper<List<With>> query(String message, String state,
			Integer currentPage,
			Integer numberPerpage
			) {
		// TODO Auto-generated method stub
		DataWrapper<List<With>> dataWrapper =new DataWrapper<List<With>>();
		Page page=new Page();

		if(currentPage!=null&numberPerpage!=null){
			page.setNumberPerPage(numberPerpage);
			page.setCurrentPage(currentPage);
		}else{
			page.setNumberPerPage(10);
			page.setCurrentPage(1);
		}
		//总条数
		int count=witManageDao.queryCounts(message,state);
		Integer currentNum=page.getCurrentNumber();
		
		List<With> l= witManageDao.query(message,state,String.valueOf(currentNum),String.valueOf(page.getNumberPerPage()));
		if(l.isEmpty()){
			dataWrapper.setData(l);
			dataWrapper.setMsg("暂无数据");
		}else{
			dataWrapper.setPage(page, count);
			dataWrapper.setData(l);
		}
		return dataWrapper;
	}

}
