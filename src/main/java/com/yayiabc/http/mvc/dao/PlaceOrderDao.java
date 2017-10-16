package com.yayiabc.http.mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yayiabc.http.mvc.pojo.jpa.Cart;
import com.yayiabc.http.mvc.pojo.jpa.FreeShipping;
import com.yayiabc.http.mvc.pojo.jpa.Invoice;
import com.yayiabc.http.mvc.pojo.jpa.ItemValue;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.PostFee;
import com.yayiabc.http.mvc.pojo.jpa.Receiver;
import com.yayiabc.http.mvc.pojo.model.FinalList;


public interface PlaceOrderDao {
	//购物车
	List<Cart> buyNows(@Param("userId")String userId,@Param("itemSKUs")String[] itemSKUs);
	
     
	//查询订货地址
	Receiver  queryReceiver(Integer receiverId);
   //查询包邮表数据
	List<FreeShipping> queryPostFree();
    //查询自定义运费表的数据
	List<PostFee> query();
     //用户钱币剩余
	int  ded(@Param("userId")String userId);
    //把商品同步到订单商品表
	int synchronization(@Param("cart")OrderItem cart,@Param("orderId")String orderId);
     //更新单个商品到订单商品表
	int synchronizationOne(@Param("orderItem")OrderItem orderItem);

	  //订单数据保存到订单表
	int  saveMessage(
			Ordera order
			/*@Param("orderId")String orderId, @Param("inHead")String inHead, @Param("registerNum")String registerNum,
			@Param("orderMessage")String orderMessage,@Param("phone") String phone*/);
  
	//伪清空购物车
	int  emptyCart(@Param("userId")String userId);
	
    //创建订单  并插入订单数据
	int  createOrder(@Param("orderId")String orderId,@Param("userId")String userId,
			@Param("order")Ordera order
			);	  
	
	//用户不用默认  使用其他收货地址时
	public Receiver  upateAddress(@Param("receiverId")Integer receiverId);

     //查看钱币赠送百分比
	Integer queryQbPercentage(@Param("itemSKU")String itemSKU);
   
	// 根据传过来的  商品sku 与 商品购买数目 查找对应的 其他参数（已知sku  num,orderId.,qbDed,create,update） 
				//需要item_name,qbded,price,totalfee,picpath, a,b,c 属性，
	ItemValue queryAttributes(@Param("itemSKU")String itemSKU);

	//根据sku 查找商品名称 业户说的 
	String queryItemName(@Param("itemId")String itemId);

	//根据sku查找商品的图片路径
	String queryItemPicPath(String itemId);

     //放一件轻易间
	int cleanCart(@Param("userId")String userId,@Param("itemSKU")String itemSKU);

    //放入实际付款
	int  insertActualPay(@Param("orderId")String orderId,@Param("actualPay") String actualPay);

    //  查询商品库存数量
	int queryItemInventNum(@Param("itemSKU")String itemSKU);

	//更改库存数量
	int updateInventNum(@Param("NUM")String NUM,@Param("itemSKU")String itemSKU);

     //根据itemid 取商品表里的商品类型
	String queryItemsort(@Param("itemId")String itemId);

     //将 本单的商品分类的总价格放入 订单表
	int insertClassItemsSumMoney(@Param("suppliesSumPrice")String suppliesSumPrice,
			@Param("tooldevicesSumPrice")String tooldevicesSumPrice
			,@Param("orderId")String orderId
			);

	//新 function 本单获得钱币数新规则
	String queryItemBrandNameByItemId(@Param("itemId")String itemId);

	//本单赠送钱币数保存到数据库
	int saveGiveQbNum(
			@Param("giveQbNum")String giveQbNum,
			@Param("postFee")	String postFee,
			@Param("sumPrice") String sumPrice,
			@Param("orderId")String orderId,
			@Param("suppliesSumPrice")String suppliesSumPrice,
			@Param("tooldevicesSumPrice")String tooldevicesSumPrice,
			@Param("actualPay")String actualPay
			);

    //退款记录放入 iteminfo中
	int saveRefundRecord(@Param("itemId")String itemId, @Param("refunNum")Integer refunNum);

	//保存该订单的退款商品分类金额到 sale_info中 
	int saveRefundMessageToSaleIncome(
			@Param("saleId")String saleId,
			@Param("orderId")String orderId, 
			@Param("haoCaiRefundSumMoney")Double haoCaiRefundSumMoney,
			@Param("toolRefundSumMoney")Double toolRefundSumMoney);

   //保存发票性质
	int saveInvoiced(Invoice invoice);

     
	Invoice queryLastInvoice(@Param("userId")String userId);

	

	List<ItemValue> queryAttributesList(List<OrderItem> orderItemList);


	List<OrderItem> queryItemIdListByitemValueList(List<ItemValue> itemValueList);


	int batchSynchronization(List<OrderItem> orderItemList);


	int cleanCartList(@Param("userId")String userId, @Param("orderItemLists")List<OrderItem> orderItemList);


	List<ItemValue> queryAttributesl(ArrayList<OrderItem> sendorderItemList);


	List<String> queryItemsortl(List<ItemValue> itemVlueList);


	int saveRefundRecords(ArrayList<OrderItem> sendorderItemList);


	int updateInventNums(List<OrderItem> orderItemList);


	List<FinalList> queryFinalList(List<OrderItem> orderItemList);


	int dedItemStockNum(List<OrderItem> orderItemList);


}
