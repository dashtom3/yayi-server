package com.yayiabc.http.mvc.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.ItemIdUtils;
import com.yayiabc.http.mvc.pojo.jpa.ItemDetail;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.pojo.jpa.ItemProperty;
import com.yayiabc.http.mvc.pojo.jpa.ItemValue;
import com.yayiabc.http.mvc.service.ItemInfoManageService;

@Controller
@RequestMapping("api/item")
public class ItemInfoManageController {
	@Autowired
	private ItemInfoManageService itemInfoManageService;
	
	/**
	 * 查询商品列表
	 */
	@RequestMapping("itemInfoList")
	@ResponseBody
	public DataWrapper<List<ItemInfo>> itemInfoList(
			@RequestParam(value="itemId",required=false) String itemId,
			@RequestParam(value="itemName",required=false) String itemName,
			@RequestParam(value="itemClassify",required=false) String itemClassify,
			@RequestParam(value="itemBrandName",required=false) String itemBrandName,
			@RequestParam(value="state",required=false) Integer state
			){
		return itemInfoManageService.itemInfoList(itemId,itemName,itemClassify,itemBrandName,state);
	}
	
	/**
	 * 商品上架
	 */
	@RequestMapping("up")
	@ResponseBody
	public DataWrapper<Void> up(
			@RequestParam(value="itemId") String itemId
			){
		return itemInfoManageService.up(itemId);
	}
	
	/**
	 * 商品下架
	 */
	@RequestMapping("down")
	@ResponseBody
	public DataWrapper<Void> down(
			@RequestParam(value="itemId") String itemId
			){
		return itemInfoManageService.down(itemId);
	}
	
	/**
	 * 商品删除
	 */
	@RequestMapping("delete")
	@ResponseBody
	public DataWrapper<Void> delete(
			@RequestParam(value="itemId") String itemId
			){
		return itemInfoManageService.delete(itemId);
	}
	
	/**
	 * 商品修改
	 */
	@RequestMapping("update")
	@ResponseBody
	public DataWrapper<Void> update(
		@RequestParam(value="itemId",required=true) String itemId,
		@RequestParam(value="itemName",required=true) String itemName,
		@RequestParam(value="oneClassify",required=true) String oneClassify,
		@RequestParam(value="twoClassify",required=false) String twoClassify,
		@RequestParam(value="threeClassify",required=false) String threeClassify,
		@RequestParam(value="itemBrandName",required=true) String itemBrandName,
		@RequestParam(value="registerId",required=true) String registerId,
		@RequestParam(value="registerId",required=true) String Id
			){
		return itemInfoManageService.update();
	}
	
	/**
	 * 获取商品编号
	 */
	@RequestMapping("getItemId")
	@ResponseBody
	public DataWrapper<Void> getItemId(){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		String itemId=ItemIdUtils.getItemId();
		dataWrapper.setMsg(itemId);
		return dataWrapper;
	}
	
	/**
	 * 获取sku代码
	 */
	@RequestMapping("getItemSku")
	@ResponseBody
	public DataWrapper<Void> getItemSku(
			@RequestParam(value="itemId",required=true) String itemId
			){
		return itemInfoManageService.getItemSku(itemId);
	}
	
	/**
	 * 商品新增
	 */
	@RequestMapping("insert")
	@ResponseBody
	public DataWrapper<Void> insert(
			@ModelAttribute ItemInfo itemInfo
			){
		return itemInfoManageService.insert(itemInfo);
	}
}
