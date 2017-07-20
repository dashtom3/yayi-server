package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public DataWrapper<Double> show(String token) {
		// TODO Auto-generated method stub
		String saleId=utis.getSaleId(token);
	DataWrapper<Double> dataWrapper=new DataWrapper<Double>();
	 List<Double> w=punRewardDao.show(saleId);
	   dataWrapper.setData(w.get(0));
	   
		return dataWrapper;
	}
	@Override
	public DataWrapper<Void> addOrDelMoney(String saleId, Integer sign,String money) {
		// TODO Auto-generated method stub
		DataWrapper<Void>  dataWrapper=new DataWrapper<Void>();
		//判断sign   1为加  2为减
		Double moneys=Double.parseDouble(money);
		if(sign==1){
			//获取销售员此时 余额
			int a=punRewardDao.addMoney(saleId,moneys, moneys+punRewardDao.show(saleId).get(0));
			if(a>0){
				dataWrapper.setMsg("增加成功");
			}else{
				dataWrapper.setMsg("增加失败");
			}
		}else if(sign==2){
			int a =punRewardDao.delMoney(saleId,moneys,punRewardDao.show(saleId).get(0)-moneys);
			if(a>0){
				dataWrapper.setMsg("扣除成功");
			}else{
				dataWrapper.setMsg("扣除失败");
			}
		}
		return dataWrapper;
	}
}
