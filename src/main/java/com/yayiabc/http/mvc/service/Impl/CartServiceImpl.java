package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.CartDao;
import com.yayiabc.http.mvc.dao.UserCenterStarDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Cart;
import com.yayiabc.http.mvc.pojo.jpa.ItemStar;
import com.yayiabc.http.mvc.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartDao cartDao;
	@Autowired
	UtilsDao utilsDao;
	@Autowired
	UserCenterStarDao userCenterStarDao;

	@Override
	public DataWrapper<List<Cart>> list(String token) {
		DataWrapper<List<Cart>> dataWrapper = new DataWrapper<List<Cart>>();
		String userId=utilsDao.getUserID(token);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("token错误");
		} else {
			List<Cart> list = cartDao.list(userId);
			dataWrapper.setData(list);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> delete(String itemSKU,String token) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		String userId=utilsDao.getUserID(token);
		
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("token错误");
		} else {
			int sign = cartDao.delete(userId, itemSKU);
			if (sign > 0) {
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<ItemStar> star(String itemId,String itemSKU,String token) {
		DataWrapper<ItemStar> dataWrapper = new DataWrapper<ItemStar>();
		String userId=utilsDao.getUserID(token);
		List<Integer> list=userCenterStarDao.queryOne(itemId,userId); //查询我的收藏中商品是否已存在
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("token错误");
		} else {
			if(list.size()==0){		//我的收藏商品不存在
				ItemStar itemStar = new ItemStar();
				itemStar.setUserId(userId);
				itemStar.setItemId(itemId);
				int id = cartDao.star(itemStar);
				if (id > 0) {
					dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
					this.delete(itemSKU, token); // 收藏后调用删除方法，从购物车中移除
				} else {		
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			}else{		//我的收藏商品已存在
					this.delete(itemSKU, token);
			}
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> add(Integer num,String itemSKU,String token) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		String userId=utilsDao.getUserID(token);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("token错误");
		} else {
			int isItem = cartDao.getCountItemSKU(userId,itemSKU);	//判断购物车内是否已存在该商品
			if (isItem == 0) {
				int id = cartDao.add(userId, num, itemSKU);
				if (id > 0) {
					dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
				} else {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else if (isItem > 0) {
				cartDao.updateOne(userId, itemSKU, num);
			}

		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateNum(Integer num,String itemSKU,String token) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		String userId=utilsDao.getUserID(token);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("token错误");
		} else {
			int sign = cartDao.updateNum(userId, num, itemSKU);
			if (sign > 0) {
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		}
		return dataWrapper;
	}

}
