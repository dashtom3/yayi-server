package com.yayiabc.http.mvc.controller.item;

import java.text.ParseException;
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
import com.yayiabc.common.utils.TimeUtil;
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
			@RequestParam(value="state",required=false) Integer state,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage
			){
		return itemInfoManageService.itemInfoList(itemId,itemName,itemClassify,itemBrandName,state,currentPage,numberPerPage);
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
	 * @throws ParseException 
	 */
	@RequestMapping("update")
	@ResponseBody
	public DataWrapper<Void> update(
			@RequestParam(value="itemId",required=true) String itemId ,
			@RequestParam(value="itemName",required=true) String itemName,
			@RequestParam(value="itemBrandId",required=true) Integer itemBrandId,
			@RequestParam(value="oneClassify",required=true) String oneClassify,
			@RequestParam(value="twoClassify",required=true) String twoClassify,
			@RequestParam(value="threeClassify",required=true) String threeClassify,
			@RequestParam(value="itemPica",required=true) String itemPica,
			@RequestParam(value="itemPicb",required=false) String itemPicb,
			@RequestParam(value="itemPicc",required=false) String itemPicc,
			@RequestParam(value="itemPicd",required=false) String itemPicd,
			@RequestParam(value="itemPice",required=false) String itemPice,
			@RequestParam(value="isThrow",required=true) Integer isThrow,
			@RequestParam(value="video",required=true) String video,
			@RequestParam(value="itemDesc",required=true) String itemDesc,
			@RequestParam(value="itemUse",required=true) String itemUse,
			@RequestParam(value="itemRange",required=false) String itemRange,
			@RequestParam(value="registerId",required=true) String registerId,
			@RequestParam(value="storeItemId",required=false) String storeItemId,
			@RequestParam(value="apparatusType",required=false) String apparatusType,
			@RequestParam(value="producePompany",required=false) String producePompany,
			@RequestParam(value="unit",required=false) String unit,
			@RequestParam(value="registerDate",required=false) String registerDate,
			@RequestParam(value="itemPacking",required=false) String itemPacking,
			@RequestParam(value="itemLevels",required=false) String itemLevels,
			@RequestParam(value="itemSort",required=false) String itemSort,
			@RequestParam(value="remark",required=false) String remark
			){
		if(registerDate==null||"".equals(registerDate)){
			registerDate=null;
		}
		if(itemRange==null||"".equals(itemRange)){
			itemRange=null;
		}
		if(storeItemId==null||"".equals(storeItemId)){
			storeItemId=null;
		}
		if(apparatusType==null||"".equals(apparatusType)){
			apparatusType=null;
		}
		if(producePompany==null||"".equals(producePompany)){
			producePompany=null;
		}
		if(itemLevels==null||"".equals(itemLevels)){
			itemLevels=null;
		}
		if(itemPacking==null||"".equals(itemPacking)){
			itemPacking=null;
		}
		if(itemPicb==null||"".equals(itemPicb)){
			itemPicb=null;
		}
		if(itemPicc==null||"".equals(itemPicc)){
			itemPicc=null;
		}
		if(itemPicd==null||"".equals(itemPicd)){
			itemPicd=null;
		}
		if(itemPice==null||"".equals(itemPice)){
			itemPice=null;
		}
		if(unit==null||"".equals(unit)){
			unit=null;
		}
		if(remark==null||"".equals(remark)){
			remark=null;
		}
		return itemInfoManageService.update(itemId,itemName,itemBrandId,oneClassify,itemLevels,
				twoClassify,threeClassify,itemPica,itemPicb,itemPicc,itemPicd,itemPice,isThrow,
				video,itemDesc,itemUse,itemRange,registerId,storeItemId,apparatusType,unit,
				producePompany,registerDate,itemPacking,itemSort,remark);
		
	}
	
	/**
	 * 商品属性修改
	 */
	@RequestMapping("updateItemValue")
	@ResponseBody
	public DataWrapper<Void> updateItemValue(
			@RequestBody List<ItemValue> itemValueList
			){
		return itemInfoManageService.updateItemValue(itemValueList);
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
	 * @throws ParseException 
	 */
	@RequestMapping("insert")
	@ResponseBody
	public DataWrapper<Void> insert(                                 
	@RequestParam(value="itemId",required=true) String itemId ,
	@RequestParam(value="itemName",required=true) String itemName,
	@RequestParam(value="itemBrandId",required=true) Integer itemBrandId,
	@RequestParam(value="oneClassify",required=true) String oneClassify,
	@RequestParam(value="twoClassify",required=true) String twoClassify,
	@RequestParam(value="threeClassify",required=true) String threeClassify,
	@RequestParam(value="itemPica",required=true) String itemPica,
	@RequestParam(value="itemPicb",required=false) String itemPicb,
	@RequestParam(value="itemPicc",required=false) String itemPicc,
	@RequestParam(value="itemPicd",required=false) String itemPicd,
	@RequestParam(value="itemPice",required=false) String itemPice,
	@RequestParam(value="isThrow",required=true) Integer isThrow,
	@RequestParam(value="video",required=true) String video,
	@RequestParam(value="itemDesc",required=true) String itemDesc,
	@RequestParam(value="itemUse",required=true) String itemUse,
	@RequestParam(value="itemRange",required=false) String itemRange,
	@RequestParam(value="remark",required=false) String remark,
	@RequestParam(value="registerId",required=true) String registerId,
	@RequestParam(value="storeItemId",required=false) String storeItemId,
	@RequestParam(value="apparatusType",required=false) String apparatusType,
	@RequestParam(value="producePompany",required=false) String producePompany,
	@RequestParam(value="unit",required=false) String unit,
	@RequestParam(value="registerDate",required=false) String registerDate,
	@RequestParam(value="itemPacking",required=false) String itemPacking,
	@RequestParam(value="itemLevels",required=false) String itemLevels,
	@RequestParam(value="itemSort",required=false) String itemSort
			){
		if(registerDate==null||"".equals(registerDate)){
			registerDate=null;
		}
		if(itemRange==null||"".equals(itemRange)){
			itemRange=null;
		}
		if(storeItemId==null||"".equals(storeItemId)){
			storeItemId=null;
		}
		if(apparatusType==null||"".equals(apparatusType)){
			apparatusType=null;
		}
		if(producePompany==null||"".equals(producePompany)){
			producePompany=null;
		}
		if(itemLevels==null||"".equals(itemLevels)){
			itemLevels=null;
		}
		if(itemPacking==null||"".equals(itemPacking)){
			itemPacking=null;
		}
		if(itemPicb==null||"".equals(itemPicb)){
			itemPicb=null;
		}
		if(itemPicc==null||"".equals(itemPicc)){
			itemPicc=null;
		}
		if(itemPicd==null||"".equals(itemPicd)){
			itemPicd=null;
		}
		if(itemPice==null||"".equals(itemPice)){
			itemPice=null;
		}
		if(unit==null||"".equals(unit)){
			unit=null;
		}
		if(remark==null||"".equals(remark)){
			remark=null;
		}
		
		return itemInfoManageService.insert(itemId,itemName,itemBrandId,oneClassify,itemLevels,
				twoClassify,threeClassify,itemPica,itemPicb,itemPicc,itemPicd,itemPice,isThrow,
				video,itemDesc,itemUse,itemRange,remark,registerId,storeItemId,apparatusType,unit,
				producePompany,registerDate,itemPacking,itemSort);
	
	}
	
	/**
	 * 商品属性新增
	 * @param itemValueList
	 * @return
	 */
	@RequestMapping("insertItemValue")
	@ResponseBody
	public DataWrapper<Void> insertItemValue(
			@RequestBody List<ItemValue> itemValueList
			){
		System.out.println(itemValueList);
		return itemInfoManageService.insertItemValue(itemValueList);
	}
}

	
