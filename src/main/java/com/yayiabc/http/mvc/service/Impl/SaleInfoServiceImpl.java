package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.http.mvc.dao.PunRewardDao;
import com.yayiabc.http.mvc.dao.SaleListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.SaleInfoDao;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.SaleInfoService;

import java.util.List;

@Service
public class SaleInfoServiceImpl implements SaleInfoService {

	@Autowired
	SaleInfoDao saleInfoDao;
	@Autowired
	SaleLogDao saleLogDao;
	@Autowired
	SaleListDao saleListDao;
	@Autowired
	PunRewardDao punRewardDao;
	
	@Override
	public DataWrapper<Void> updateSale(SaleInfo saleInfo,String token) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		String saleId=saleLogDao.getSaleIdByToken(token);
		if(saleId == null){
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		}else{
			saleInfo.setSaleId(saleId);
			int sign=saleInfoDao.updateSale(saleInfo);
			if (sign > 0) {
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updatePostal(String postalType, String bankName,
			String openName, String accountNumber, String token) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		String saleId=saleLogDao.getSaleIdByToken(token);
		if(saleId == null){
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		}else{
			int sign=saleInfoDao.updatePostal(postalType, bankName, openName, accountNumber, saleId);
			if (sign > 0) {
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<SaleInfo> query(String token) {
		DataWrapper<SaleInfo> dataWrapper=new DataWrapper<SaleInfo>();
		String saleId=saleLogDao.getSaleIdByToken(token);
		SaleInfo saleInfo=new SaleInfo();
		if(saleId == null){
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		}else{
			saleInfo=saleInfoDao.query(saleId);
			String money=saleListDao.queryByBalance(null,saleId);
			if(money==null){
				saleInfo.setMoney(0);
			}else{
				saleInfo.setMoney(Double.parseDouble(money));
			}
			List<Object> w=punRewardDao.show(saleId);
			List<String> list=punRewardDao.pd(saleId);
			if(!w.isEmpty() && !list.isEmpty()){
						saleInfo.setDescribey(list.get(0)+","+w.get(0));
			}
			dataWrapper.setData(saleInfo);
		}
		return dataWrapper;
	}

}
