package com.yayiabc.http.mvc.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.ItemBrand;
import com.yayiabc.http.mvc.pojo.jpa.ItemClassify;
import com.yayiabc.http.mvc.pojo.jpa.ItemProperty;
import com.yayiabc.http.mvc.service.ItemManageService;

@Controller
@RequestMapping("api/item")
public class ItemManageController {
	@Autowired
	private ItemManageService itemManageService;
	
	/**
	 * 根据商品品牌名称和品牌产地来查询品牌
	 * @param itemBrandName
	 * @param itemBrandHome
	 * @return
	 */
	@RequestMapping("queryItemBrand")
	@ResponseBody
	public DataWrapper<List<ItemBrand>> queryItemBrand(
			@RequestParam(value="itemBrandName",required=false) String itemBrandName,
			@RequestParam(value="itemBrandHome",required=false) String itemBrandHome
			){
		return itemManageService.queryItemBrand(itemBrandName,itemBrandHome);
	}
	/**
	 * 删除商品品牌
	 * @param itemBrandId
	 * @return
	 */
	@RequestMapping("deleteItemBrand")
	@ResponseBody
	public DataWrapper<Void> deleteItemBrand(
			@RequestParam(value="itemBrandId",required=true)Integer itemBrandId
			){
		return itemManageService.deleteItemBrand(itemBrandId);
	}
	
	/**
	 * 增加商品品牌
	 */
	@RequestMapping("addItemBrand")
	@ResponseBody
	public DataWrapper<Void> addItemBrand(
			@RequestParam(value="itemBrandName",required=true) String itemBrandName,
			@RequestParam(value="itemBrandHome",required=true) String itemBrandHome,
			@RequestParam(value="itemBrandLogo",required=true) String itemBrandLogo
			){
		return itemManageService.addItemBrand(itemBrandName,itemBrandHome,itemBrandLogo);
	}
	
	/**
	 * 修改商品品牌
	 * @param itemBrandId
	 * @param itemBrandName
	 * @param itemBrandLogo
	 * @param itemBrandHome
	 * @return
	 */
	@RequestMapping("updateItemBrand")
	@ResponseBody
	public DataWrapper<Void> updateItemBrand(
			@RequestParam(value="itemBrandId",required=true)Integer itemBrandId,
			@RequestParam(value="itemBrandName",required=true)String itemBrandName,
			@RequestParam(value="itemBrandLogo",required=true)String itemBrandLogo,
			@RequestParam(value="itemBrandHome",required=true)String itemBrandHome
			){
	return itemManageService.updateItemBrand(itemBrandId,itemBrandName,itemBrandLogo,itemBrandHome);
	}
	
	/**
	 * 查询属性
	 * @return
	 */
	@RequestMapping("queryProperty")
	@ResponseBody
	public DataWrapper<List<ItemProperty>> queryProperty(
			@RequestParam(value="itemPropertyName",required=false) String itemPropertyName
			){
		return itemManageService.queryProperty(itemPropertyName);
	}
	
	/**
	 * 删除属性
	 * @param itemPropertyId
	 * @return
	 */
	@RequestMapping("deleteProperty")
	@ResponseBody
	public DataWrapper<Void> deleteProperty(
			@RequestParam(value="itemPropertyId",required=true)Integer itemPropertyId
			){
		return itemManageService.deleteProperty(itemPropertyId);
	}
	
	/**
	 * 删除属性值
	 * @return
	 */
	@RequestMapping("deletePropertyd")
	@ResponseBody
	public DataWrapper<Void> deletePropertyd(
			@RequestParam(value="itemSKU",required=true) String itemSKU
			){
		return itemManageService.deletePropertydBySKU(itemSKU);
	}
	
	/**
	 * 修改属性值
	 */
	@RequestMapping("updatePropertyd")
	@ResponseBody
	public DataWrapper<Void> updatePropertyd(
			@RequestParam(value="itemSKU",required=true) String itemSKU,
			@RequestParam(value="itemPparam",required=true) String itemPparam
			){
		return itemManageService.updatePropertyd(itemSKU,itemPparam);
	}
	
	/**
	 * 保存修改属性
	 */
	@RequestMapping("updateProperty")
	@ResponseBody
	public DataWrapper<Void> updateProperty(
			@RequestParam(value="itemPropertyId",required=true) Integer itemPropertyId,
			@RequestParam(value="itemPropertyName",required=true) String itemPropertyName
			){
		return itemManageService.updateProperty(itemPropertyId,itemPropertyName);
	}
	
	/**
	 * 添加属性
	 */
	@RequestMapping("addProperty")
	@ResponseBody
	public DataWrapper<Void> addProperty(
				@RequestParam(value="itemPropertyName",required=true) String itemPropertyName
			){
		return itemManageService.addProperty(itemPropertyName);
	}
	
	/**
	 * 添加属性值
	 */
	@RequestMapping("addPropertyd")
	@ResponseBody
	public DataWrapper<Void> addPropertyd(
			@RequestParam(value="itemPropertyId",required=true) Integer itemPropertyId,
			@RequestParam(value="itemPparam",required=true) String itemPparam
			){
		return itemManageService.addPropertyd(itemPropertyId,itemPparam);
	}
	
	/**
	 * 添加属性值和属性名
	 */
	@RequestMapping("addPropertydAndPropertyName")
	@ResponseBody
	public DataWrapper<Void> addPropertydAndPropertyName(
			@RequestParam(value="itemPropertyName",required=true) String itemPropertyName,
			@RequestBody List<String> itemPparamList
			){
		return itemManageService.addPropertyAndPropertyName(itemPropertyName,itemPparamList);
	}
	
	/**
	 * 分类显示
	 */
	@RequestMapping("showItemClassify")
	@ResponseBody
	public DataWrapper<List<ItemClassify>> showItemClassify(
			@RequestParam(value="itemClassifyName",required=false) String itemClassifyName,
			@RequestParam(value="itemPreviousClassify",required=false) String itemPreviousClassify
			){
		return itemManageService.showItemClassify(itemClassifyName,itemPreviousClassify);
	}
	
	/**
	 * 删除分类
	 */
	@RequestMapping("deleteItemClassify")
	@ResponseBody
	public DataWrapper<Void> deleteItemClassify(
			@RequestParam(value="itemClassifyId",required=true) Integer itemClassifyId
			){
		return itemManageService.deleteItemClassify(itemClassifyId);
	}
	
	/**
	 * 修改商品分类
	 */
	@RequestMapping("updateItemClassify")
	@ResponseBody
	public DataWrapper<Void> updateItemClassify(
			@RequestParam(value="itemClassifyId",required=true) Integer itemClassifyId,
			@RequestParam(value="itemClassifyName",required=true) String itemClassifyName,
			@RequestParam(value="itemPreviousClassify",required=true) String itemPreviousClassify
			){
		return itemManageService.updateItemClassify(itemClassifyId,itemClassifyName,itemPreviousClassify);
	}
	
	/**
	 * 增加商品分类
	 */
	@RequestMapping("addItemClassify")
	@ResponseBody
	public DataWrapper<Void> addItemClassify(
			@RequestParam(value="itemClassifyName",required=true) String itemClassifyName,
			@RequestParam(value="itemPreviousClassify",required=true) String itemPreviousClassify
			){
		return itemManageService.addItemClassify(itemClassifyName,itemPreviousClassify);
	}
	
	
}
