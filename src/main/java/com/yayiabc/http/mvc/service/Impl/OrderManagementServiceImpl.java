package com.yayiabc.http.mvc.service.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.exceptionHandler.OrderException;
import com.yayiabc.common.sdk.KDN;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.OrderDetailsDao;
import com.yayiabc.http.mvc.dao.OrderManagementDao;
import com.yayiabc.http.mvc.dao.PlaceOrderDao;
import com.yayiabc.http.mvc.dao.UserMyQbDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Invoice;
import com.yayiabc.http.mvc.pojo.jpa.ItemValue;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.model.OrderManagement;
import com.yayiabc.http.mvc.service.OrderManagementService;
import com.yayiabc.http.mvc.service.UserMyQbService;

@Service
public class OrderManagementServiceImpl implements OrderManagementService{
	@Autowired
	private OrderManagementDao orderManagementDao;
	@Autowired
	private PlaceOrderDao placeOrderDao;
	@Autowired
	private UserMyQbDao userMyQbDao;
	@Autowired
	private UtilsDao utilsDao;
	@Autowired
	private OrderDetailsDao orderDetailsDao;
	@Override
	public DataWrapper<List<OrderManagement>> showOrder(HashMap<String, Object> hMap,
			Integer currentPage,Integer numberPerpage
			) {
		Page page=new Page();

		if(currentPage!=null&&numberPerpage!=null){
			page.setNumberPerPage(numberPerpage);
			page.setCurrentPage(currentPage);
		}else{
			page.setNumberPerPage(10);
			page.setCurrentPage(1);
		}

		// TODO Auto-generated method stub
		DataWrapper<List<OrderManagement>> dataWrapper=new DataWrapper<List<OrderManagement>>();
		//总条数
		int count=orderManagementDao.queryCount(hMap);//totalnumber

		//				hMap.put("currentPage", page.getCurrentPage());
		hMap.put("numberPerpage", page.getNumberPerPage());
		Integer currentNum=page.getCurrentNumber();
		hMap.put("currentNum", currentNum);
		List<OrderManagement> userOrderList=orderManagementDao.showOrder(hMap);
		if(userOrderList.isEmpty()){
			dataWrapper.setMsg("暂无数据");
			dataWrapper.setData(userOrderList);
		}else{
			dataWrapper.setData(userOrderList);
			dataWrapper.setPage(page, count);
		}
		return dataWrapper;
	}
	//关闭交易
	@Override
	public DataWrapper<Void> closeTrading(String orderId,String flagBits) {
		// TODO Auto-generated method stub
		Integer flagBit=Integer.parseInt(flagBits);
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int a=orderManagementDao.closeTrading(orderId,flagBit);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		if(a>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}

		return dataWrapper;
	}
	//显示退款处理
	@Override
	public DataWrapper<Ordera> refundProcessing(String orderId) {
		// TODO Auto-generated method stub
		DataWrapper<Ordera> dataWrapper=new DataWrapper<Ordera>();
		Ordera ordera=orderManagementDao.refundProcessing(orderId);
		dataWrapper.setData(ordera);
		return dataWrapper;
	}
	//检查此订单是否包邮   如果是 那就是 如果不包邮就查出 该订单运费   (此处的 是否包邮应该在下订单模块里实现 这里 不需要  先暂时搁置)

	
	/*private void ut(int sign,List l,String bz){
		if(sign!=l.size()<=0){
			throw new RuntimeException(bz+"    失败");
		}
	}*/
	//操作退款数据
	@Override  
	/**
	 * 一个 orderItem 对象需要传 退货数量 num，还有sku ，orderId
	 */
	@Transactional
	public DataWrapper<HashMap<String, Object>> makeRefundData(ArrayList<OrderItem> SendorderItemList){

		// TODO Auto-generated method stub
		try {

			//存储退款
			DataWrapper<HashMap<String, Object>> dataWrapper=new DataWrapper<HashMap<String, Object>>();
			//应退款金额 ！！！
			Double  refundSumPrice=0.0;
			Double  haoCaiRefundSumMoney=0.0;
			Double  ToolRefundSumMoney=0.0;
			//标记退款商品的总数
			int countItem=0;
			//查看当前订单
			Ordera order= orderManagementDao.queryOrder(SendorderItemList.get(0).getOrderId());
			//根据当前的sku 得到 该商品价格  SendorderItemList  里面是有sku
			List<ItemValue> itemVlueList=placeOrderDao.queryAttributesl(SendorderItemList);
			//hy 根据 itemVlueList获取 对应的商品类型
			List<String> itemTypeList=placeOrderDao.queryItemsortl(itemVlueList);
			for(int i=0;i<SendorderItemList.size();i++){
				/*//根据当前的sku 得到 该商品价格
				ItemValue itemVlue=placeOrderDao.queryAttributes(SendorderItemList.get(i).getItemSKU());
				//hy
				String itemType=placeOrderDao.queryItemsort(itemVlue.getItemId());*/
				SendorderItemList.get(i).setPrice(itemVlueList.get(i).getItemSkuPrice());
				SendorderItemList.get(i).setItemId(itemVlueList.get(i).getItemId());
				if("耗材类".equals(itemTypeList.get(i))){
					haoCaiRefundSumMoney+=itemVlueList.get(i).getItemSkuPrice()*Integer.parseInt(SendorderItemList.get(i).getRefunNum());
				}else if("工具设备类".equals(itemTypeList.get(i))){
					ToolRefundSumMoney+=itemVlueList.get(i).getItemSkuPrice()*Integer.parseInt(SendorderItemList.get(i).getRefunNum());
				}

				countItem+=Integer.parseInt(SendorderItemList.get(i).getRefunNum());
			}
			//item_info  记录每个商品的退款次数
			int c=placeOrderDao.saveRefundRecords(SendorderItemList);
			if(c<=0){
				throw new OrderException(ErrorCodeEnum.REFUND_ERROR);
			}
			//把退货数量放入order_item表中
			if(orderManagementDao.saveRefundNumToOrderItems(
					SendorderItemList
					)<=0){
				throw new OrderException(ErrorCodeEnum.REFUND_ERROR);
			}
			//根据订单号获取销售员id 这里 销售员可能为null
			String saleId=utilsDao.getSaleIdByOrderId(SendorderItemList.get(0).getOrderId());

			refundSumPrice=haoCaiRefundSumMoney+ToolRefundSumMoney;
			//这里根据订单id  计算该订单退款的sku  与      退款的数量 和  购买的数量 作比较
			List<OrderItem> itemList=orderManagementDao.queryOrderItemList(SendorderItemList.get(0).getOrderId());
			int befConuntItem=0;
			for(int q=0;q<itemList.size();q++){
				befConuntItem+=itemList.get(q).getNum();
			}
			//退款后的商品总价格!!!1
			Double afterRefundSumMoney=0.0;
			//退款后的道邦商品总价格
			Double afterDaoBangSumMoney=0.0;
			//退款后耗材类商品总价格
			Double afterhaoCaiSumMoney=0.0;
			//退款后工具设备类商品总价格
			int aftergongJuSumMoney=0;
			int count=0;
			for(int x=0;x<SendorderItemList.size();x++){
				//外层退款商品详情
				for(int i=0;i<itemList.size();i++){
					//内层全部购买商品详情
					if(SendorderItemList.get(x).getItemSKU().equals(itemList.get(i).getItemSKU())){
						if(SendorderItemList.get(x).getRefunNum().equals(itemList.get(i).getNum())){
							itemList.remove(i);
							//i--;
							//为订单商品表里的refundNum 更改
						}else{
							if(itemList.get(i).getNum()<Integer.parseInt(SendorderItemList.get(x).getRefunNum())){
								throw new Exception();
							}
							itemList.get(i).setNum(
									itemList.get(i).getNum()-Integer.parseInt(SendorderItemList.get(x).getRefunNum())
									);
							//为订单商品表里的refundNum 更改
						}
					}

				}
			}
			for(int i=0;i<itemList.size();i++){
				if("上海道邦".equals(itemList.get(i).getItemBrandName())){
					afterDaoBangSumMoney+=itemList.get(i).getPrice()*itemList.get(i).getNum();
				}else{
					if("耗材类".equals(itemList.get(i).getItemType())){

						afterhaoCaiSumMoney+=itemList.get(i).getPrice()*itemList.get(i).getNum();
					}else if("工具设备类".equals(itemList.get(i).getItemType())){
						count+=itemList.get(i).getNum();
						aftergongJuSumMoney+=itemList.get(i).getPrice()*itemList.get(i).getNum();
					}
				}
			}
			//退款后的赠送钱币数
			Double refundAfterGiveQbNum=0.0;
			//首先道邦品牌
			if(afterDaoBangSumMoney>0&&afterDaoBangSumMoney<300){
				refundAfterGiveQbNum+=afterDaoBangSumMoney*0.03;
			}else if(afterDaoBangSumMoney>=300&&afterDaoBangSumMoney<600){
				refundAfterGiveQbNum+=afterDaoBangSumMoney*0.05;
			}else if(afterDaoBangSumMoney>=600&&afterDaoBangSumMoney<1200){
				refundAfterGiveQbNum+=afterDaoBangSumMoney*0.08;
			}else if(afterDaoBangSumMoney>=1200&&afterDaoBangSumMoney<2500){
				refundAfterGiveQbNum+=afterDaoBangSumMoney*0.12;
			}else if(afterDaoBangSumMoney>=2500){
				refundAfterGiveQbNum+=afterDaoBangSumMoney*0.15;
			}
			//其他品牌 耗材类
			if(afterhaoCaiSumMoney>0&&afterhaoCaiSumMoney<500){
				refundAfterGiveQbNum+=afterhaoCaiSumMoney*0.03;
			}else if(afterhaoCaiSumMoney>=500&&afterhaoCaiSumMoney<1000){
				refundAfterGiveQbNum=afterhaoCaiSumMoney*0.05;
			}else if(afterhaoCaiSumMoney>=1000&&afterhaoCaiSumMoney<3000){
				refundAfterGiveQbNum+=afterhaoCaiSumMoney*0.8;
			}else if(afterhaoCaiSumMoney>=3000){
				refundAfterGiveQbNum+=afterhaoCaiSumMoney*0.12;
			}
			//其他品牌 工具设配类
			if(count==1){
				refundAfterGiveQbNum+=aftergongJuSumMoney*0.05;
			}else if(count>=2){
				refundAfterGiveQbNum+=aftergongJuSumMoney*0.10;
			}
			//退款还原库存   ++
			int d=orderManagementDao.stillItemsListValueNums(SendorderItemList);
			//还原销量   --
			int t=orderManagementDao.addSalesLists(SendorderItemList);
			//还原 item_value 的销量
			int p=orderManagementDao.addSalesListsTOItemValue(SendorderItemList);
			if(d+t+p<3){
				throw new OrderException(ErrorCodeEnum.REFUND_ERROR);
			}
			count=0;
			//扣除钱币数
			double dedQbNums=order.getGiveQb()-refundAfterGiveQbNum;
			Integer dedQbNum=(int) Math.round(dedQbNums);
			
			String token= utilsDao.queryTokenByOrderId(SendorderItemList.get(0).getOrderId());
			//放入钱币记录表
			//userMyQbService.add(q, token);

			//退回钱币数
			if(order.getActualPay()>=refundSumPrice){
				//退钱refundSumPrice

				int states=orderManagementDao.saveRefundMessage(order.getUserId(),haoCaiRefundSumMoney,ToolRefundSumMoney,
						dedQbNum,0,SendorderItemList.get(0).getOrderId()
						);
				//退款信息保存到 sale_income 里
				if(saleId!=null){
					if(
							orderManagementDao.saveRefundMessToSaleIncome(
									saleId,SendorderItemList.get(0).getOrderId(),
									haoCaiRefundSumMoney,
									ToolRefundSumMoney
									)<=0
							){
						throw new OrderException(ErrorCodeEnum.REFUND_ERROR);
					}
				}
				/*Double nowTotaFee=order.getTotalFee()-refundSumPrice;*/
				//更新到订单表
				if(orderManagementDao.updateOrderMessage(
						SendorderItemList.get(0).getOrderId()
						)<=0){
					throw new OrderException(ErrorCodeEnum.REFUND_ERROR);
				}
				if(countItem==befConuntItem){
					//订单状态改为交易关闭
					orderDetailsDao.cancel(itemList.get(0).getOrderId(),order.getUserId());
				}
				//扣除钱币
				dedQb(dedQbNum,order);
			}else if(order.getActualPay()<refundSumPrice){
				//退钱order.getActualPay()
				//保存到退款表里

				int isReturnPostF=0;
				if(countItem==befConuntItem){
					//订单状态改为交易关闭
					if(order.getState()==2||order.getState()==5){
						isReturnPostF=Integer.parseInt(order.getPostFee().trim());
					}
					orderDetailsDao.cancel(itemList.get(0).getOrderId(),order.getUserId());
				}
				Integer a=(int) Math.round(refundSumPrice-order.getActualPay()); //退回钱币数
				int state=orderManagementDao.saveRefundMessages(order.getUserId(),haoCaiRefundSumMoney,ToolRefundSumMoney,
						dedQbNum,a+isReturnPostF,
						(order.getActualPay()),SendorderItemList.get(0).getOrderId()
						);
				if(state<=0){
					throw new OrderException(ErrorCodeEnum.REFUND_ERROR);
				}
				//退款信息保存到 sale_income 里c
				if(saleId!=null){
					int si=orderManagementDao.saveRefundMessToSaleIncome(
							saleId,SendorderItemList.get(0).getOrderId(),
							haoCaiRefundSumMoney,
							ToolRefundSumMoney
							);
					if(si<=0){
						throw new OrderException(ErrorCodeEnum.REFUND_ERROR);
					}
				}
				//更新到订单表
				orderManagementDao.updateOrderMessage(
						order.getOrderId()
						);
			     //退回乾币
				/**
				 * 1 如果发货了 便不退回乾币
				 * 2如果没发货  便退回乾币
				 */
				if(order.getState().equals("2")){
					//付款，未发货
					returnQbUtils(a,order);
				}else{
					returnQbUtils(a+isReturnPostF,order);
				}
				//扣除钱币
				dedQb(dedQbNum,order);
			}
			afterRefundSumMoney=afterDaoBangSumMoney+afterhaoCaiSumMoney+aftergongJuSumMoney;

			HashMap<String, Object> hm=new HashMap<String, Object>();
			hm.put("扣除钱币数", dedQbNum);
			hm.put("退回钱币数", Math.round((refundSumPrice-order.getActualPay())));
			dataWrapper.setData(hm);
			return dataWrapper;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	} 
   
	private void dedQb(Integer dedQbNum,Ordera order) {
		// TODO Auto-generated method stub
		QbRecord q=new QbRecord();
		
		
		q.setQbRout("\"赠\" "+dedQbNum+"个");
		q.setQbType("qb_balance");
		q.setUserId(order.getUserId());
		
		q.setMillisecond(System.nanoTime());
		userMyQbDao.updateUserQb(-dedQbNum+"",order.getUserId(),"qb_balance");
		//查询乾币余额
		Integer userQbNums=userMyQbDao.getUserQbNum(order.getUserId());
		q.setRemark("订单有退款，下单时赠送的乾币需扣除："+dedQbNum+"个乾币。（乾币余额："+userQbNums+"）订单编号:"+order.getOrderId());
		userMyQbDao.add(q);
	}
	//退款退回钱币的规则顺序
	String returnQbUtils(int rQbNum,Ordera order){
		String qbDe=order.getQbDes();
		List<Integer> list=new ArrayList<Integer>();
		String[] str=qbDe.split(","); // qb_balance   a_qb   b_qb   c_qb 
		Integer sum=0;
		String s="订单有退款，下单时使用的乾币需退回"+rQbNum+"个乾币。";
		StringBuffer sb=new StringBuffer();
		/*for(int i=0;i<str.length;i++){
			sum+=Integer.parseInt(str[i]);
		}*/
		/*if(sum==rQbNum){
			//退 完
			orderManagementDao.returnQbAll(str,order.getUserId());
			sb.append("该订单已全部退回");
			s=  rQbNum+"（该订单已全部退回）。";

		}else*/{
			for(int x=0;x<str.length;x++){
				if(rQbNum>Integer.parseInt(str[x])){
					rQbNum=rQbNum-Integer.parseInt(str[x]);
					list.add(Integer.parseInt(str[x]));
					sb.append(ut(x)+Integer.parseInt(str[x])+"个，");
				}else if(rQbNum<=Integer.parseInt(str[x])){
					list.add(rQbNum);
					sb.append(ut(x)+rQbNum+"个。");
					break;
				}
			}
			System.err.println("这里");
			System.err.println(list);
			orderManagementDao.returnQbAnyOthes(list,order.getUserId());
		}
		int returnQbNum=0;
		for(int q=0;q<list.size();q++){
			returnQbNum+=list.get(q);
		}
		//查询乾币余额
				Integer userQbNums=userMyQbDao.getUserQbNum(order.getUserId());
		s=s+/*s+sb.toString()+*/"（乾币余额："+userQbNums+"）。订单编号:"+order.getOrderId();
		
		userMyQbDao.addMessageQbQRget(sb.toString(), order.getUserId(), s, System.nanoTime());
		//---无语
		orderManagementDao.saveRefundMessageToReturnQbMsg(sb.toString(),order.getOrderId());
		return sb.toString();
	}
	String ut(int i){
		switch (i) {
		case 0:
			return "\"赠\"：";
		case 1:
			return "\"8.0折\"：";
		case 2:
			return "\"9.0折 \"：";
		case 3:
			return "\"9.5折 \"：";
		}
		return null;
	}

	//仓库发货
	@Override
	public DataWrapper<Void> warehouseDelivery(String orderId,String logisticsName,String  logisticsCode) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		//通过orderId查找到userId
		//String userId=orderManagementDao.queryUserId(orderId);
		int state =orderManagementDao.warehouseDelivery(orderId,logisticsName,logisticsCode);
		if(state>0){
			dataWrapper.setMsg("操作成功");
		}else{
			dataWrapper.setMsg("操作失败");
		}
		try {
			new KDN().orderTracesSubByJson(orderId, logisticsName,logisticsCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataWrapper;
	}
	//显示订单详情
	@Override
	public DataWrapper<Ordera> queryOrderDetails(String orderId) {
		DataWrapper<Ordera> dataWrapper=new DataWrapper<Ordera>();
		Ordera ordera= orderManagementDao.queryOrderDetails(orderId);
		dataWrapper.setData(ordera);
		return dataWrapper;
	}
	//   //显示已经退款数据的订单信息
	@Override
	public DataWrapper<Ordera> showRefundOrderMessage(String orderId) {
		DataWrapper<Ordera> dataWrapper=new DataWrapper<Ordera>();
		Ordera ordera= orderManagementDao.showRefundOrderMessage(orderId);
		dataWrapper.setData(ordera);
		return dataWrapper;
	}
	@Override
	public DataWrapper<Invoice> queryOrderInvoice(String orderId) {
		// TODO Auto-generated method stub
		DataWrapper<Invoice> dataWrapper=new DataWrapper<Invoice>();
		dataWrapper.setData(orderManagementDao.queryOrderInvoice(orderId));
		return dataWrapper;
	}
}
