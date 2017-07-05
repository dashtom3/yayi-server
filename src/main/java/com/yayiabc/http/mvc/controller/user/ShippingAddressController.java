package com.yayiabc.http.mvc.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public DataWrapper<Void> insert(
			@ModelAttribute Receiver receiver,
			@RequestParam(value=("token"),required=true) String token
			) {
		
		//request.setCharacterEncoding("UTF-8");
		DataWrapper<Void> dataWrapper=null;
	      if(receiver.getIsDefault()==true){
	    	  //根据电话号码  查询出当前登录人user_ID
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
	public  DataWrapper<Void> update(
			@ModelAttribute Receiver receiver,
			@RequestParam(value="receiverId",required=true) String receiverIds,
			@RequestParam(value="token",required=true) String token
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
			System.out.println("收货地址id"+receiverId);
			System.out.println("收receiverIdcopyid"+receiverIdcopy);
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
	   public DataWrapper<List<Receiver>> showShippingAddress(
			   @RequestParam(value="token",required=true) String token
			   ){
		      return shippingAddressService.showShoppingAddress(token);
	   }
	   //删除收货地址
	   @RequestMapping("deleteShippingAddress")
	   @ResponseBody
	   public DataWrapper<Integer>  deleteShoppingAddress(
			   @RequestParam(value="receiverId",required=true) String receiverId
			   ){
		       return shippingAddressService.deleteShoppingAddress(receiverId);
	   }
}
