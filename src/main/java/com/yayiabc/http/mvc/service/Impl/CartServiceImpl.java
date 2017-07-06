package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.CartDao;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.pojo.jpa.Cart;
import com.yayiabc.http.mvc.pojo.jpa.ItemStar;
import com.yayiabc.http.mvc.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartDao cartDao;
	@Autowired
	UserDao userDao;

	@Override
	public DataWrapper<List<Cart>> list(String phone) {
		DataWrapper<List<Cart>> dataWrapper = new DataWrapper<List<Cart>>();
		String userId = userDao.getUserId(phone);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			List<Cart> list = cartDao.list(userId);
			dataWrapper.setData(list);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> delete(String phone, String itemSKU) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		String userId = userDao.getUserId(phone);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
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
	public DataWrapper<ItemStar> star(String phone,String itemId,String itemSKU) {
		DataWrapper<ItemStar> dataWrapper = new DataWrapper<ItemStar>();
		String userId = userDao.getUserId(phone);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			ItemStar itemStar =new ItemStar();
			itemStar.setUserId(userId);
			itemStar.setItemId(itemId);
			int id = cartDao.star(itemStar);
			if (id > 0) {
				dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
				this.delete(phone, itemSKU); // 收藏后调用删除方法，从购物车中移除
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Cart> add(Cart cart, String phone) {
		DataWrapper<Cart> dataWrapper = new DataWrapper<Cart>();
		String userId = userDao.getUserId(phone);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			cart.setUserId(userId);
			int isItem = cartDao.getCountItemSKU(cart.getItemSKU());
			if (isItem == 0) {
				int id = cartDao.add(cart);
				if (id > 0) {
					dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
				} else {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else if (isItem > 0) {
				cartDao.updateOne(userId, cart.getItemSKU(), cart.getNum());
			}

		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateNum(String phone, Integer num, String itemSKU) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		String userId = userDao.getUserId(phone);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
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
