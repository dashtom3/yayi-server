package com.yayiabc.http.mvc.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminTokenValidate;
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
			@RequestParam(value="itemBrandHome",required=false) String itemBrandHome,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
			){
		return itemManageService.queryItemBrand(itemBrandName,itemBrandHome,currentPage,numberPerPage);
	}
	/**
	 * 删除商品品牌
	 * @param itemBrandId
	 * @return
	 */
	@RequestMapping("deleteItemBrand")
	@ResponseBody
	@AdminTokenValidate(description="管理员删除品牌")
	public DataWrapper<Void> deleteItemBrand(
			@RequestParam(value="itemBrandId",required=true)Integer itemBrandId,
			@RequestParam(value="adminToken",required=true)String adminToken
			){
		return itemManageService.deleteItemBrand(itemBrandId);
	}
	
	/**
	 * 增加商品品牌
	 */
	@RequestMapping("addItemBrand")
	@ResponseBody
	@AdminTokenValidate(description="管理员添加品牌")
	public DataWrapper<Void> addItemBrand(
			@RequestParam(value="itemBrandName",required=true) String itemBrandName,
			@RequestParam(value="itemBrandHome",required=true) String itemBrandHome,
			@RequestParam(value="itemBrandLogo",required=true) String itemBrandLogo,
			@RequestParam(value="adminToken",required=true)String adminToken
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
	@AdminTokenValidate(description="管理员修改品牌")
	public DataWrapper<Void> updateItemBrand(
			@RequestParam(value="itemBrandId",required=true)Integer itemBrandId,
			@RequestParam(value="itemBrandName",required=true)String itemBrandName,
			@RequestParam(value="itemBrandLogo",required=true)String itemBrandLogo,
			@RequestParam(value="itemBrandHome",required=true)String itemBrandHome,
			@RequestParam(value="adminToken",required=true)String adminToken
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
			@RequestParam(value="itemPropertyName",required=false) String itemPropertyName,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
			@RequestParam(value="adminToken",required=true)String adminToken
			){
		
		return itemManageService.queryProperty(itemPropertyName,currentPage,numberPerPage);
	}
	
	/**
	 * 删除属性
	 * @param itemPropertyId
	 * @return
	 */
	@RequestMapping("deleteProperty")
	@ResponseBody
	@AdminTokenValidate(description="管理员删除属性")
	public DataWrapper<Void> deleteProperty(
			@RequestParam(value="itemPropertyId",required=true)Integer itemPropertyId,
			@RequestParam(value="adminToken",required=true)String adminToken
			){
		return itemManageService.deleteProperty(itemPropertyId);
	}
	
	/**
	 * 删除属性值
	 * @return
	 */
	@RequestMapping("deletePropertyd")
	@ResponseBody
	@AdminTokenValidate(description="管理员删除属性值")
	public DataWrapper<Void> deletePropertyd(
			@RequestParam(value="itemSKU",required=true) String itemSKU,
			@RequestParam(value="adminToken",required=true)String adminToken
			){
		return itemManageService.deletePropertydBySKU(itemSKU);
	}
	
	
	
	/**
	 * 保存修改属性
	 */
	@RequestMapping("updateProperty")
	@ResponseBody
	@AdminTokenValidate(description="管理员修改属性")
	public DataWrapper<Void> updateProperty(
			@RequestParam(value="itemPropertyId",required=true) Integer itemPropertyId,
			@RequestParam(value="itemPropertyName",required=true) String itemPropertyName,
			@RequestParam(value="itemPparamList",required=false) List<String> itemPparamList,
			@RequestParam(value="adminToken",required=true)String adminToken
			){
		return itemManageService.updateProperty(itemPropertyId,itemPropertyName,itemPparamList);
	}
	
	
	
	
	
	/**
	 * 添加属性值和属性名
	 */
	@RequestMapping("addPropertydAndPropertyName")
	@ResponseBody
	@AdminTokenValidate(description="管理员添加属性和属性值")
	public DataWrapper<Void> addPropertydAndPropertyName(
			@RequestParam(value="itemPropertyName",required=true) String itemPropertyName,
			@RequestParam(value="itemPparamList",required=false) List<String> itemPparamList,
			@RequestParam(value="adminToken",required=true)String adminToken
			){
		System.out.println(itemPparamList);
		return itemManageService.addPropertyAndPropertyName(itemPropertyName,itemPparamList);
	}
	
	/**
	 * 分类显示
	 */
	@RequestMapping("showItemClassify")
	@ResponseBody
	public DataWrapper<List<ItemClassify>> showItemClassify(
			@RequestParam(value="itemClassifyName",required=false) String itemClassifyName,
			@RequestParam(value="itemPreviousClassify",required=false) String itemPreviousClassify,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
			){
		return itemManageService.showItemClassify(itemClassifyName,itemPreviousClassify,currentPage,numberPerPage);
	}
	
	/**
	 * 删除分类
	 */
	@RequestMapping("deleteItemClassify")
	@ResponseBody
	@AdminTokenValidate(description="管理员删除分类")
	public DataWrapper<Void> deleteItemClassify(
			@RequestParam(value="itemClassifyId",required=true) Integer itemClassifyId,
			@RequestParam(value="itemClassifyName",required=true) String itemClassifyName,
			@RequestParam(value="itemClassifyGrade",required=true) Integer itemClassifyGrade,
			@RequestParam(value="adminToken",required=true)String adminToken
			){
		return itemManageService.deleteItemClassify(itemClassifyId,itemClassifyName,itemClassifyGrade);
	}
	
	/**
	 * 修改分类
	 */
	@RequestMapping("updateItemClassify")
	@ResponseBody
	@AdminTokenValidate(description="管理员修改分类")
	public DataWrapper<Void> updateItemClassify(
			@RequestParam(value="itemClassifyId",required=true) Integer itemClassifyId,
			@RequestParam(value="itemClassifyName",required=true) String itemClassifyName,
			@RequestParam(value="itemOldName",required=true) String itemOldName,
			@RequestParam(value="itemClassifyGrade",required=true) Integer itemClassifyGrade,
			@RequestParam(value="adminToken",required=true)String adminToken
			){
		return itemManageService.updateItemClassify(itemClassifyId,itemClassifyName,itemOldName,itemClassifyGrade);
	}
	
	/**
	 * 增加分类
	 */
	@RequestMapping("addItemClassify")
	@ResponseBody
	@AdminTokenValidate(description="管理员添加分类")
	public DataWrapper<Void> addItemClassify(
			@RequestParam(value="itemClassifyName",required=true) String itemClassifyName,
			@RequestParam(value="itemPreviousClassify",required=true) String itemPreviousClassify,
			@RequestParam(value="itemClassifyGrade",required=true) Integer itemClassifyGrade,
			@RequestParam(value="adminToken",required=true)String adminToken
			){
		return itemManageService.addItemClassify(itemClassifyName,itemPreviousClassify,itemClassifyGrade);
	}
	
	
}
