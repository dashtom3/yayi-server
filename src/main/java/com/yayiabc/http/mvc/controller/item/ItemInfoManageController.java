package com.yayiabc.http.mvc.controller.item;




import com.yayiabc.common.annotation.AdminLog;
import com.yayiabc.common.annotation.AdminTokenValidate;
import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.listener.MyServletContextListener;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.ItemIdUtils;
import com.yayiabc.http.mvc.pojo.jpa.ItemInfo;
import com.yayiabc.http.mvc.service.ItemInfoManageService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/item")
public class ItemInfoManageController {

	private Logger logger= LogManager.getLogger(ItemInfoManageController.class);
	@Autowired
	private ItemInfoManageService itemInfoManageService;
	/**
	 * 查询商品列表
	 */
	@RequestMapping("itemInfoList")
	@ResponseBody
	@AdminTokenValidate
	public DataWrapper<List<ItemInfo>> itemInfoList(
			@RequestParam(value="itemId",required=false) String itemId,
			@RequestParam(value="itemName",required=false) String itemName,
			@RequestParam(value="itemClassifyGrade",required = false)Integer itemClassifyGrade,
			@RequestParam(value="itemClassify",required=false) String itemClassify,
			@RequestParam(value="itemBrandName",required=false) String itemBrandName,
			@RequestParam(value="state",required=false) Integer state,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
			@RequestHeader(value="admintoken",required=true)String adminToken
			){
		return itemInfoManageService.itemInfoList(itemId,itemName,itemClassify,itemBrandName,state,currentPage,numberPerPage,itemClassifyGrade);
	}
	
	/**
	 * 商品上架
	 */
	@RequestMapping("up")
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="管理员上架商品")
	public DataWrapper<Void> up(
			@RequestParam(value="itemId") String itemId,
			@RequestHeader(value="adminToken",required=true)String adminToken
			){
		return itemInfoManageService.up(itemId);
	}
	
	/**
	 * 商品下架
	 */
	@RequestMapping("down")
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="管理员下架商品")
	public DataWrapper<Void> down(
			@RequestParam(value="itemId") String itemId,
			@RequestHeader(value="adminToken",required=true)String adminToken
			){
		return itemInfoManageService.down(itemId);
	}
	
	/**
	 * 商品删除
	 */
	@RequestMapping("delete")
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="管理员删除商品")
	public DataWrapper<Void> delete(
			@RequestParam(value="itemId") String itemId,
			@RequestHeader(value="adminToken",required=true)String adminToken
			){
		return itemInfoManageService.delete(itemId);
	}

	@RequestMapping("update")
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="管理员修改商品")
	public DataWrapper<Void> update(
			@RequestHeader(value="adminToken",required=true)String adminToken,
			@ModelAttribute ItemInfo itemInfo
	){
		return itemInfoManageService.updateItem(itemInfo);
	}
	
	/**
	 * 获取商品编号
	 */
	@RequestMapping("getItemId")
	@ResponseBody
	public DataWrapper<Void> getItemId(){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		String itemId;
		synchronized (this){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
			itemId= ItemIdUtils.getItemId();
		}
		dataWrapper.setMsg(itemId);
		return dataWrapper;
	}

	@RequestMapping("addItem")
	@ResponseBody
	@AdminTokenValidate
	@AdminLog(description="管理员新增商品")
	public DataWrapper<Void> addItem(
			@RequestHeader(value="adminToken",required=true)String adminToken	,
			@ModelAttribute ItemInfo itemInfo
	){
		return itemInfoManageService.addItem(itemInfo);
	}

}

	
