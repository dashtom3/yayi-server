package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.PunRewardDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.service.PunRewardService;
@Service
public class PunRewardServiceImpl implements PunRewardService {
	@Autowired
	private PunRewardDao punRewardDao;
	@Autowired
	private UtilsDao utis;
	@Override
	public DataWrapper<Object> show(String token) {
		// TODO Auto-generated method stub
		String saleId=utis.getSaleId(token);
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		List<Object> w=punRewardDao.show(saleId);
		List<String> list=punRewardDao.pd(saleId);
		if(!w.isEmpty()&&!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				if(list.get(i).contains("申请中")){
					dataWrapper.setData(list.get(i)+","+w.get(0));
					return dataWrapper;
				}
			}
			dataWrapper.setData("出账提现成功"+","+w.get(0));
		}
		return dataWrapper;
	}
	@Override
	public DataWrapper<Void> addOrDelMoney(String saleId, Integer sign,String money) {
		// TODO Auto-generated method stub
		DataWrapper<Void>  dataWrapper=new DataWrapper<Void>();
		//判断sign   1为加  2为减
		//  String saleId=utis.getSaleId(saleToken);
				Double moneys=Double.parseDouble(money);
		Integer count =punRewardDao.shows(saleId);
		if(count==0){
			   if(sign==1){
				   punRewardDao.addMoney(saleId,moneys, moneys); 
			   }else{
				   dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				   dataWrapper.setMsg("余额不足");
				   return dataWrapper;
			   }
		}else{
			 if(sign==1) {
                 punRewardDao.addMoney(saleId, moneys, (Double) punRewardDao.show(saleId).get(0) + moneys);
             } else {
                 punRewardDao.delMoney(saleId, moneys, (Double) punRewardDao.show(saleId).get(0) - moneys);
             }
		}
		return dataWrapper;
	}
	@Override
	public DataWrapper<Object> shows(String saleId) {
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		// TODO Auto-generated method stub
		List<Object> w=punRewardDao.show(saleId);
		if(!w.isEmpty()){
			dataWrapper.setData(w.get(0));
			return dataWrapper;
		}else{
			dataWrapper.setMsg("为空");
			return dataWrapper;
		}
	}
}
