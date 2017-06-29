package com.yayiabc.http.mvc.controller.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public DataWrapper<List<Cart>> list(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value = "token", required = true) String token
			
	){
		return cartService.list(phone);
	}
	
	/**
	 * 删除购物车内商品
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> delete(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="itemSKU",required=true)String itemSKU,
			@RequestParam(value="token",required=true)String token
	){
		return cartService.delete(phone, itemSKU);
	}
	

	/**
	 * 收藏购物车内商品
	 */
	@RequestMapping(value = "star", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<ItemStar> star(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="itemId",required=true)String itemId,
			@RequestParam(value="itemSKU",required=true)String itemSKU,
			@RequestParam(value="token",required=true)String token
	){
		return cartService.star(phone,itemId,itemSKU);
	}
	
	/**
	 * 新增商品到购物车
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Cart> add(
			@ModelAttribute Cart cart,
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="token",required=true)String token
	){
		return cartService.add(cart, phone);
	}
	
	/**
	 * 修改购物车商品数量
	 */
	@RequestMapping(value = "updateNum", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper<Void> updateNum(
			@RequestParam(value="phone",required=true)String phone,
			@RequestParam(value="num",required=true)Integer num,
			@RequestParam(value="itemSKU",required=true)String itemSKU,
			@RequestParam(value="token",required=true)String token
	){
		return cartService.updateNum(phone, num, itemSKU);
	}
}
