package com.yayiabc.http.mvc.controller.item;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.ItemIdUtils;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
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
			@RequestParam(value="itemSKU") String itemSKU,
			@RequestParam(value="itemId") String itemId
			){
		return itemInfoManageService.delete(itemSKU,itemId);
	}
	
	/**
	 * 商品修改
	 */
	@RequestMapping("update")
	@ResponseBody
	public DataWrapper<Void> update(
			@RequestParam(value="itemId",required=true) String itemId ,
			@RequestParam(value="itemName",required=true) String itemName,
			@RequestParam(value="itemBrandId",required=true) Integer itemBrandId,
			@RequestParam(value="itemBrandName",required=true) String itemBrandName, 
			@RequestParam(value="oneClassify",required=true) String oneClassify,
			@RequestParam(value="twoClassify",required=true) String twoClassify,
			@RequestParam(value="threeClassify",required=true) String threeClassify,
			@RequestParam(value="itemPica",required=true) String itemPica,
			@RequestParam(value="itemPicb",required=true) String itemPicb,
			@RequestParam(value="itemPicc",required=true) String itemPicc,
			@RequestParam(value="itemPicd",required=true) String itemPicd,
			@RequestParam(value="itemPice",required=true) String itemPice,
			@RequestParam(value="video",required=true) String video,
			@RequestParam(value="itemDesc",required=true) String itemDesc,
			@RequestParam(value="itemUse",required=true) String itemUse,
			@RequestParam(value="itemRange",required=true) String itemRange,
			@RequestParam(value="registerId",required=true) String registerId,
			@RequestParam(value="storeItemId",required=true) String storeItemId,
			@RequestParam(value="apparatusType",required=true) Integer apparatusType,
			@RequestParam(value="producePompany",required=true) String producePompany,
			@RequestParam(value="unit",required=true) String unit,
			@RequestParam(value="registerDate",required=true) Date registerDate,
			@RequestParam(value="itemPacking",required=true) String itemPacking,
			@RequestParam(value="itemLevels",required=true) String itemLevels,
			@RequestBody List<ItemValue> itemValueList
			){
		return itemInfoManageService.update(itemId,itemName,itemBrandId,oneClassify,itemLevels,
				twoClassify,threeClassify,itemPica,itemPicb,itemPicc,itemPicd,itemPice,
				video,itemDesc,itemUse,itemRange,registerId,storeItemId,apparatusType,unit,
				producePompany,registerDate,itemPacking,itemBrandName,itemValueList);
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
	 * 商品新增
	 */
	@RequestMapping("insert")
	@ResponseBody
	public DataWrapper<Void> insert(                                 
	@RequestParam(value="itemId",required=true) String itemId ,
	@RequestParam(value="itemName",required=true) String itemName,
	@RequestParam(value="itemBrandId",required=true) Integer itemBrandId,
	@RequestParam(value="itemBrandName",required=true) String itemBrandName, 
	@RequestParam(value="oneClassify",required=true) String oneClassify,
	@RequestParam(value="twoClassify",required=true) String twoClassify,
	@RequestParam(value="threeClassify",required=true) String threeClassify,
	@RequestParam(value="itemPica",required=true) String itemPica,
	@RequestParam(value="itemPicb",required=true) String itemPicb,
	@RequestParam(value="itemPicc",required=true) String itemPicc,
	@RequestParam(value="itemPicd",required=true) String itemPicd,
	@RequestParam(value="itemPice",required=true) String itemPice,
	@RequestParam(value="video",required=true) String video,
	@RequestParam(value="itemDesc",required=true) String itemDesc,
	@RequestParam(value="itemUse",required=true) String itemUse,
	@RequestParam(value="itemRange",required=true) String itemRange,
	@RequestParam(value="registerId",required=true) String registerId,
	@RequestParam(value="storeItemId",required=true) String storeItemId,
	@RequestParam(value="apparatusType",required=true) Integer apparatusType,
	@RequestParam(value="producePompany",required=true) String producePompany,
	@RequestParam(value="unit",required=true) String unit,
	@RequestParam(value="registerDate",required=true) Date registerDate,
	@RequestParam(value="itemPacking",required=true) String itemPacking,
	@RequestParam(value="itemLevels",required=true) String itemLevels,
	@RequestBody List<ItemValue> itemValueList
			){
		return itemInfoManageService.insert(itemId,itemName,itemBrandId,oneClassify,itemLevels,
				twoClassify,threeClassify,itemPica,itemPicb,itemPicc,itemPicd,itemPice,
				video,itemDesc,itemUse,itemRange,registerId,storeItemId,apparatusType,unit,
				producePompany,registerDate,itemPacking,itemBrandName,itemValueList);
	}
}
