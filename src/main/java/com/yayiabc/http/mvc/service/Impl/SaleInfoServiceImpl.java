package com.yayiabc.http.mvc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.SaleInfoDao;
import com.yayiabc.http.mvc.dao.SaleLogDao;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.SaleInfoService;
@Service
public class SaleInfoServiceImpl implements SaleInfoService {

	@Autowired
	SaleInfoDao saleInfoDao;
	@Autowired
	SaleLogDao saleLogDao;
	
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
		if(saleId == null){
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		}else{
			SaleInfo saleInfo=saleInfoDao.query(saleId);
			dataWrapper.setData(saleInfo);
		}
		return dataWrapper;
	}

}
