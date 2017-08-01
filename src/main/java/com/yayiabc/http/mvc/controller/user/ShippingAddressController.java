package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.annotation.AdminLog;
import com.yayiabc.common.annotation.UserLog;
import com.yayiabc.common.annotation.UserTokenValidate;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Receiver;
import com.yayiabc.http.mvc.service.ShippingAddressService;

@Controller
@RequestMapping("api/shoppingAdress")
public class ShippingAddressController {
	@Autowired 
	private ShippingAddressService shippingAddressService;
	@RequestMapping("insert")
	@ResponseBody
	@UserTokenValidate
	 @UserLog(description="新增收货地址")
	public DataWrapper<Void> insert(
			@ModelAttribute Receiver receiver,
			@RequestHeader(value=("token"),required=true) String token
			) {
		
		//request.setCharacterEncoding("UTF-8");
		DataWrapper<Void> dataWrapper=null;
	      if(receiver.getIsDefault()==true){
	    	  //根据token  查询出当前登录人user_ID
	    	  Integer receiverId=shippingAddressService.addConditions(token);
	    	  System.out.println(receiverId);
	    	  if(receiverId==null||receiverId==0){
	  	    	dataWrapper=shippingAddressService.addUserAdress(receiver,token);
	  	    	return dataWrapper;
	  	    }else{
	  	    	int state=shippingAddressService.updateIsdefault(receiverId);
	  	    	if(state>0){
	  	    		dataWrapper=shippingAddressService.addUserAdress(receiver,token);
	  	    	}
	  	    	return dataWrapper;
	  	    }
	      }else{
	    	  dataWrapper=shippingAddressService.addUserAdress(receiver,token);
	      }
		return dataWrapper;
	}
	@RequestMapping("update")
	@ResponseBody
	@UserTokenValidate
	 @UserLog(description="更改收货地址")
	public  DataWrapper<Void> update(
			@ModelAttribute Receiver receiver,
			@RequestParam(value="receiverId",required=true) String receiverIds,
			@RequestHeader(value="token",required=true) String token
			){
		/*System.out.println(receiver);
		DataWrapper<Void> dataWrapper=shippingAddressService.updateUserAddress(receiver);
		return dataWrapper; */
		//request.setCharacterEncoding("UTF-8");
		Integer receiverId=Integer.parseInt(receiverIds);
		receiver.setReceiverId(receiverId);
		System.out.println(receiver);
		DataWrapper<Void> dataWrapper=null;
		if(receiver.getIsDefault()==true){
			//if y
			Integer receiverIdcopy=shippingAddressService.addConditions(token);
			 if(receiverIdcopy==null){
				 dataWrapper = shippingAddressService.updateUserAddress(receiver,token);
				 return dataWrapper;
			 }else{
				     shippingAddressService.updateIsdefault(receiverIdcopy);
					 dataWrapper = shippingAddressService.updateUserAddress(receiver,token);
					 return dataWrapper;
			 }
		}else{
			dataWrapper = shippingAddressService.updateUserAddress(receiver,token); 
			return dataWrapper;
		}
	}
	//xianshi默认收货地址逻辑
	   @RequestMapping("showShippingAddress")
	   @ResponseBody
	   @UserTokenValidate
	   @UserLog(description="显示收货地址")
	   public DataWrapper<List<Receiver>> showShippingAddress(
			   @RequestHeader(value="token",required=true) String token
			   ){
		      return shippingAddressService.showShoppingAddress(token);
	   }
	   //删除收货地址
	   @RequestMapping("deleteShippingAddress")
	   @ResponseBody
	   @UserTokenValidate
	   @UserLog(description="删除收货地址")
	   public DataWrapper<Integer>  deleteShoppingAddress(
			   @RequestHeader(value="token",required=true) String token,
			   @RequestParam(value="receiverId",required=true) String receiverId
			   ){
		       return shippingAddressService.deleteShoppingAddress(receiverId);
	   }
}
