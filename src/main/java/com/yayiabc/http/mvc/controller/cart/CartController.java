package com.yayiabc.http.mvc.controller.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.UserLog;
import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Cart;
import com.yayiabc.http.mvc.pojo.jpa.ItemStar;
import com.yayiabc.http.mvc.service.CartService;

@Controller
@RequestMapping("api/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	/**
	 * 获取购物车列表
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="用户获取购物车列表")
	public DataWrapper<List<Cart>> list(
			@RequestHeader(value = "token", required = true) String token
	){
		return cartService.list(token);
	}
	
	/**
	 * 删除购物车内商品
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="删除购物车内商品")
	public DataWrapper<Void> delete(
			@RequestParam(value="itemSKU",required=true)String[] itemSKU,
			@RequestHeader(value="token",required=true)String token
	){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		for(int i=0;i<itemSKU.length;i++){
			dataWrapper=cartService.delete(itemSKU[i], token);
		}
		return dataWrapper;
	}
	
	/**
	 * 清空购物车内失效商品
	 */
	@RequestMapping(value = "clear" ,method=RequestMethod.POST)
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="用户清空购物车内失效商品")
	public DataWrapper<Void> clear(
			@RequestHeader(value="token",required=true)String token
	){
		return cartService.clear(token);
	}

	/**
	 * 收藏购物车内商品
	 */
	@RequestMapping(value = "star", method = RequestMethod.POST)
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="用户收藏购物车内商品")
	public DataWrapper<ItemStar> star(
			@RequestParam(value="itemId",required=true)String[] itemId,
			@RequestParam(value="itemSKU",required=true)String[] itemSKU,
			@RequestHeader(value="token",required=true)String token
	){
		DataWrapper<ItemStar> dataWrapper=new DataWrapper<ItemStar>();
		for(int i=0;i<itemId.length;i++){
			dataWrapper=cartService.star(itemId[i], itemSKU[i], token);
		}
		return dataWrapper;
	}
	
	/**
	 * 新增商品到购物车
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="用户新增商品到购物车")
	public DataWrapper<Void> add(
			@RequestParam(value="num",required=true)Integer num,
			@RequestParam(value="itemSKU",required=true)String itemSKU,
			@RequestHeader(value="token",required=true)String token
	){
		return cartService.add(num, itemSKU, token);
	}
	
	/**
	 * 修改购物车商品数量
	 */
	@RequestMapping(value = "updateNum", method = RequestMethod.POST)
	@ResponseBody
	@UserTokenValidate
	@UserLog(description="修改购物车商品数量")
	public DataWrapper<Void> updateNum(
			@RequestParam(value="num",required=true)Integer num,
			@RequestParam(value="itemSKU",required=true)String itemSKU,
			@RequestHeader(value="token",required=true)String token
	){
		return cartService.updateNum(num, itemSKU, token);
	}
}
