package com.yayiabc.http.mvc.service.Impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.exceptionHandler.OrderException;
import com.yayiabc.common.sdk.KDN;
import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.FolderTOZip;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.utils.PayAfterOrderUtil;
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
import com.yayiabc.http.mvc.pojo.jpa.Receiver;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.pojo.model.OrderManagement;
import com.yayiabc.http.mvc.service.OrderManagementService;

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
								throw new RuntimeException();
							}
							itemList.get(i).setNum(
									itemList.get(i).getNum()-Integer.parseInt(SendorderItemList.get(x).getRefunNum())
									);
							//为订单商品表里的refundNum 更改
						}
					}

				}
			}
			System.out.println(itemList);
			
			//获取商品赠送的铅笔数
			List<ItemValue> itemQbList=orderManagementDao.getItemQb(itemList);
			System.out.println(itemQbList);
			double refundAfterGiveQbNum=0;
			  for(int i=0;i<itemList.size();i++){
				  for(int x=0;x<itemQbList.size();x++){
					  if(itemList.get(i).getItemSKU().equals(itemQbList.get(x).getItemSKU())){
						  refundAfterGiveQbNum+=itemQbList.get(x).getItemQb()*itemList.get(i).getNum();
					  }
				  }
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
			int dedQbNums=(int) (order.getGiveQb()-refundAfterGiveQbNum);
			
			
			System.out.println("扣除乾币数："+dedQbNums);
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
		User user=userMyQbDao.getUserQbNum(order.getUserId());
		int qbbalance=user.getQbBalance();
		
		//维护 赠送铅笔书
		/*PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
		payAfterOrderUtil.limitWithQb(order.getUserId(), -qbbalance);*/
		
		int aqb=user.getaQb();
		int cqb=user.getcQb();
		int userQbNums=qbbalance+aqb+cqb;
		q.setQbBalances("\"赠：\""+qbbalance+"个；"+"\"8.0折\""+aqb+"个；"+"\"9.5折\""+cqb+"个；");
		q.setRemark("订单有退款，下单时赠送的乾币需扣除："+dedQbNum+"个乾币。（乾币余额："+userQbNums+"）订单编号:"+order.getOrderId());
		userMyQbDao.add(q);
	}
	//退款退回钱币的规则顺序
	String returnQbUtils(int rQbNum,Ordera order){
		String qbDe=order.getQbDes();
		List<Integer> list=new ArrayList<Integer>();
		String[] str=qbDe.split(","); // qb_balance   a_qb   b_qb   c_qb 
		
		PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
		payAfterOrderUtil.limitWithQb(order.getUserId(), Integer.parseInt(str[0]));
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
			System.err.println(list);
			orderManagementDao.returnQbAnyOthes(list,order.getUserId());
		}
		int returnQbNum=0;
		for(int q=0;q<list.size();q++){
			returnQbNum+=list.get(q);
		}
		//查询乾币余额
		User user=userMyQbDao.getUserQbNum(order.getUserId());
		int qbbalance=user.getQbBalance();
		int aqb=user.getaQb();
		int cqb=user.getcQb();
		int userQbNums=qbbalance+aqb+cqb;
		s=s+/*s+sb.toString()+*/"（乾币余额："+userQbNums+"）。订单编号:"+order.getOrderId();
		String qbBalance="\"赠：\""+qbbalance+"个；"+"\"8.0折\""+aqb+"个；"+"\"9.5折\""+cqb+"个；";
		userMyQbDao.addMessageQbQRget(sb.toString(), order.getUserId(), s, System.nanoTime(),qbBalance);
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
	@Override
	public DataWrapper<Void> exportExcel(String orderId, String buyerInfo, String orderState, 
			String orderCTime, String orderETime, String isRefund,HttpServletResponse response) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();

		/**
		 * 检查打包文件夹是否存在   不存在就创建
		 */
		File file = new File("C:/yayi");
		if(!file.exists()){
			file.mkdirs();
		}else{
			if(deleteDir(file)){
				File fileZip = new File("C:/后台订单详情.zip");
				fileZip.delete();
				file.mkdirs();
			}
		}

		//根据条件到数据库查询数据
		HashMap<String, String> hMap=new HashMap<String,String>();
		hMap.put("orderId", orderId);
		hMap.put("buyerInfo", buyerInfo);
		hMap.put("orderState", orderState);
		hMap.put("orderCTime", orderCTime);
		hMap.put("orderETime", orderETime);
		hMap.put("isRefund", isRefund);
		hMap.put("numberPerpage",null);
		hMap.put("currentNum", null);
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		List<Ordera> OrderManagementList= orderDetailsDao.orderDetailsShow(hMap);
		// 第一步，得到excel工作簿对象
		HSSFWorkbook wb = new HSSFWorkbook();  
		// 第二步 得到excel工作表对象中  
		HSSFSheet sheet = wb.createSheet("-后台订单列表");  
		//第一行宽高
		sheet.setColumnWidth(0,  20 * 256); 
		sheet.autoSizeColumn(1, true);
		// 第三步，创建工作表的行
		HSSFRow row = sheet.createRow((int) 0);  //创建工作表的行
		// 第四步，创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();  

		CellRangeAddress region = new CellRangeAddress(1, OrderManagementList.size(),0 , 0); // 参数都是从O开始   
		sheet.addMergedRegion(region);   

		style.setWrapText(true);//设置自动换行
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  



		HSSFCell cell = row.createCell((short) 0);  //创建第一列
		//高度row


		cell.setCellValue("查询时间");  
		cell.setCellStyle(style); 

		cell = row.createCell((short) 1);  
		cell.setCellValue("下单时间");  
		cell.setCellStyle(style);  

		cell = row.createCell((short) 2);  
		cell.setCellValue("订单编号");  
		cell.setCellStyle(style); 

		cell = row.createCell((short) 3);  
		cell.setCellValue("收件人");  
		cell.setCellStyle(style);  

		cell = row.createCell((short) 4);  
		cell.setCellValue("收件人手机号");
		cell.setCellStyle(style);  

		cell = row.createCell((short) 5); 
		cell.setCellValue("所在地区");  
		cell.setCellStyle(style);  

		cell = row.createCell((short) 6);  
		cell.setCellValue("详细地址");  
		cell.setCellStyle(style);  

		cell = row.createCell((short) 7);  
		cell.setCellValue("商品名称");  
		cell.setCellStyle(style);  

		cell = row.createCell((short) 8);  
		cell.setCellValue("单价（元）");  
		cell.setCellStyle(style);  

		cell = row.createCell((short) 9);  
		cell.setCellValue("数量");  
		cell.setCellStyle(style);  

		cell = row.createCell((short) 10);  
		cell.setCellValue("商品总价（元）");  
		cell.setCellStyle(style);  

		cell = row.createCell((short) 11);  
		cell.setCellValue("商品合计（元）");  
		cell.setCellStyle(style);

		cell = row.createCell((short) 12);  
		cell.setCellValue("运费");  
		cell.setCellStyle(style);

		cell = row.createCell((short) 13);  
		cell.setCellValue("乾币抵扣");  
		cell.setCellStyle(style);

		cell = row.createCell((short) 14);  
		cell.setCellValue("乾币抵扣明细");  
		cell.setCellStyle(style);

		cell = row.createCell((short) 15);  
		cell.setCellValue("实际付款（元）");  
		cell.setCellStyle(style);

		cell = row.createCell((short) 16);  
		cell.setCellValue("支付方式");  
		cell.setCellStyle(style);

		cell = row.createCell((short) 17);  
		cell.setCellValue("订单状态");  
		cell.setCellStyle(style);

		cell = row.createCell((short) 18);  
		cell.setCellValue("订单留言");  
		cell.setCellStyle(style);

		cell = row.createCell((short) 19);  
		cell.setCellValue("是否申请发票");  
		cell.setCellStyle(style);

		cell = row.createCell((short) 20);  
		cell.setCellValue("是否需要产品注册证");  
		cell.setCellStyle(style);

		int x=0;
		int upperOrderItemListSize=0;
		//写入表格
		for (int i = 0; i < OrderManagementList.size(); i++)  
		{  

			x++;
			row = sheet.createRow((int)x);  

			Ordera order=OrderManagementList.get(i);
			Receiver receiver=order.getReceiver();

			//导出发票信息到txt文档
			try {
				writer(order);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 第四步，创建单元格，并设置值  
			row.createCell((short) 0).setCellValue(orderCTime+"-"+orderETime);  
			row.createCell((short) 1).setCellValue( simpleDateFormat.format(order.getCreated()));  
			row.createCell((short) 2).setCellValue(order.getOrderId()); 
			row.createCell((short) 3).setCellValue(receiver.getReceiverName());
			row.createCell((short) 4).setCellValue(order.getUser().getPhone());  
			row.createCell((short) 5).setCellValue(receiver.getCounty()+"/"+receiver.getCity()+"/"+receiver.getProvince());  
			row.createCell((short) 6).setCellValue(receiver.getReceiverDetail()); 
			//获取订单里的商品 判断商品种类 个数
			List<OrderItem> orderItemList=order.getOrderitemList();
			//这里判断 orderItemList长度 是否大于1
			if(!orderItemList.isEmpty()){
				if(orderItemList.size()==1){ 
					OrderItem orderItem =orderItemList.get(0);
					row.createCell((short) 7).setCellValue(orderItem.getItemName());
					row.createCell((short) 8).setCellValue(orderItem.getPrice());
					row.createCell((short) 9).setCellValue(orderItem.getNum());
					row.createCell((short) 10).setCellValue(orderItem.getNum()*orderItem.getPrice());
				}else{

					HSSFRow rows=null;
					for(int q=0;q<orderItemList.size();q++){
						OrderItem orderItem =orderItemList.get(q);
						if(q==0){
							row.createCell((short) 7).setCellValue(orderItem.getItemName());
							row.createCell((short) 8).setCellValue(orderItem.getPrice());
							row.createCell((short) 9).setCellValue(orderItem.getNum());
							row.createCell((short) 10).setCellValue(orderItem.getNum()*orderItem.getPrice());
						}else{
							rows = sheet.createRow((int) ++x);
							rows.createCell((short) 7).setCellValue( orderItem.getItemName());
							rows.createCell((short) 8).setCellValue(orderItem.getPrice());
							rows.createCell((short) 9).setCellValue(orderItem.getNum());
							rows.createCell((short) 10).setCellValue(orderItem.getNum()*orderItem.getPrice());
						}


					}

				}
			}


			row.createCell((short) 11).setCellValue(order.getTotalFee());
			row.createCell((short) 12).setCellValue(order.getPostFee());
			row.createCell((short) 13).setCellValue(order.getQbDed());
			row.createCell((short) 14).setCellValue(QbDes(order.getQbDes()));
			row.createCell((short) 15).setCellValue(order.getActualPay());

			row.createCell((short) 16).setCellValue(showPayType(order.getPayType()));

			row.createCell((short) 17).setCellValue(showOrderState(order.getState()));

			row.createCell((short) 18).setCellValue(order.getBuyerMessage());
			row.createCell((short) 19).setCellValue(isNeedInv(order.getInvoiceHand()));
			cell = row.createCell((short) 20); 
			//new SimpleDateFormat("yyyy-mm-dd").format(order.getActualPay())
			cell.setCellValue(isNeedIsg(order.getIsRegister()));  

			style.setWrapText(true);//设置自动换行
		} 

		try  
		{ 
			FileOutputStream fout = new FileOutputStream("C:/yayi/order.xls");  
			wb.write(fout);  
			fout.close();
			//删除待压缩文件夹里的空发票文件
			File files = new File("C:/yayi");
			File[] filess = file.listFiles();
			for(int i=0; i<filess.length; i++){
				if(filess[i].getName().contains("不需要发票")){
					filess[i].delete();
				}
			}
			
			//压缩
			FolderTOZip ftz=new FolderTOZip();
			ftz.zip("C:/yayi", "C:/后台订单详情.zip");
			
			/**
			 * 把包发送给浏览器
			 */
			File fil = new File("C:/后台订单详情.zip"); 

			System.out.println(fil);
			FileInputStream fis = new FileInputStream(fil);  
			byte [] buffer = new byte[fis.available()];
			System.out.println("buffer.length"+buffer.length);
			fis.read(buffer);  
			fis.close();  

			response.reset();  
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(orderCTime+"~~"+orderCTime+" 后台订单.zip", "UTF-8"));  
			response.addHeader("Content-Length", "" + file.length());  
			OutputStream ous = new BufferedOutputStream(response.getOutputStream());  
			response.setContentType("application/octet-stream");  
			ous.write(buffer);  
			ous.flush();  
			ous.close();

		}  
		catch (Exception e)  
		{  
			e.printStackTrace();  
		}finally {

		}
		return dataWrapper;
	}
	/**
	 * 显示订单状态
	 * @param state
	 * @return
	 */
	private String showOrderState(Integer state) {
		// TODO Auto-generated method stub
		switch(state){
		case 0:
			return "交易关闭";
		case 1:
			return "等待买家付款";
		case 2:
			return "买家已付款";
		case 3:
			return "卖家已发货";
		case 4:
			return "待评价（交易成功）";
		case 5:
			return "订单已确认";
		case 9:
			return "买家已评论";
		}

		return null;
	}
	/**
	 * 显示支付类型
	 * @param payType
	 * @return
	 */
	private String showPayType(Integer payType) {
		// TODO Auto-generated method stub
		if(payType==null){
			return "订单未付款";
		}
		switch(payType){
		case 0:
			return "支付宝支付";
		case 1:
			return "微信支付";
		case 2:
			return "银联支付";
		case 3:
			return "乾币支付";
		case 4:
			return "微信支付（公众号/网站）";
		case 5:
			return "微信支付（app）";
		}

		return null;
	}
	private String isNeedIsg(Integer isRegister) {
		// TODO Auto-generated method stub
		if(isRegister==1){
			return "是";
		}else{
			return "否";
		}
	}
	/**
	 * 是否需要发票
	 * @param invoiceHand
	 * @return
	 */
	private String isNeedInv(String invoiceHand) {
		// TODO Auto-generated method stub
		if("1".equals(invoiceHand)){
			return "是";
		}else{
			return "否";
		}
	}
	/**
	 * 抵扣明细
	 * @param qbDes
	 * @return
	 */
	private String QbDes(String qbDes) {
		// TODO Auto-generated method stub'
		if("暂无".equals(qbDes)){
			return  "'赠' 0个；'9.5折' 0个；'9.0折' 0个；'8.0折' 0个；";
		}


		String[] str=qbDes.split(",");
		return "'赠' "+str[0]+"个；'9.5折' "+str[1]+"个；'9.0折' "+str[2]+"个；'8.0折' "+str[3]+"个；";
	}
	/*
	 * 发票写入txt方法
	 */
	private void writer(Ordera  ordera) throws IOException{

		FileWriter writer = null;
		try {
			if("1".equals(ordera.getInvoiceHand())){
				writer=new FileWriter("C:/yayi/"+ordera.getOrderId()+"订单发票信息.txt");
				Invoice invoice	=utilsDao.getInvoiceByOrderId(ordera.getOrderId());
				writer.write(invoice.toString());
			}else{
				writer=new FileWriter("C:/yayi/"+ordera.getOrderId()+"不需要发票");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(writer!=null){
				writer.flush();
				writer.close();
			}
		}
	}
	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * @param dir 将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful.
	 *                 If a deletion fails, the method stops attempting to
	 *                 delete and returns "false".
	 */
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();

			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}
	/**
	 * 后台订单点击 显示该用户的充值记录
	 */
	@Override
	public DataWrapper<QbRecord> queryUserQbList(String userId) {
		// TODO Auto-generated method stub
		DataWrapper<QbRecord> dataWrapper=new DataWrapper<QbRecord>();
		QbRecord qr=orderManagementDao.queryUserQbList(userId);
		dataWrapper.setData(qr);
		return dataWrapper;
	}
	@Override
	public String electronicSheet() {
		// TODO Auto-generated method stub
		try {
			return orderOnlineByJson();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//电商ID
		private String EBusinessID="1292696";
		//电商加密私钥，快递鸟提供，注意保管，不要泄漏
		private String AppKey="e1cae6b8-ca6f-4a29-953b-0c41dd461ffb";
		//请求url, 正式环境地址：http://api.kdniao.cc/api/Eorderservice    测试环境地址：http://testapi.kdniao.cc:8081/api/EOrderService
		private String ReqURL="http://testapi.kdniao.cc:8081/api/EOrderService";	
		

		/**
	     * Json方式 电子面单
		 * @throws Exception 
	     */
		public String orderOnlineByJson() throws Exception{
			String requestData= "{'OrderCode': '012657700387'," +
	                "'ShipperCode':'SF'," +
	                "'PayType':1," +
	                "'ExpType':1," +
	                "'Cost':1.0," +
	                "'OtherCost':1.0," +
	                "'Sender':" +
	                "{" +
	                "'Company':'LV','Name':'Taylor','Mobile':'15018442396','ProvinceName':'上海','CityName':'上海','ExpAreaName':'青浦区','Address':'明珠路73号'}," +
	                "'Receiver':" +
	                "{" +
	                "'Company':'GCCUI','Name':'Yann','Mobile':'15018442396','ProvinceName':'北京','CityName':'北京','ExpAreaName':'朝阳区','Address':'三里屯街道雅秀大厦'}," +
	                "'Commodity':" +
	                "[{" +
	                "'GoodsName':'鞋子','Goodsquantity':1,'GoodsWeight':1.0}]," +
	                "'Weight':1.0," +
	                "'Quantity':1," +
	                "'Volume':0.0," +
	                "'Remark':'小心轻放'," +
	                "'IsReturnPrintTemplate':1}";
			Map<String, String> params = new HashMap<String, String>();
			params.put("RequestData", urlEncoder(requestData, "UTF-8"));
			params.put("EBusinessID", EBusinessID);
			params.put("RequestType", "1007");
			String dataSign=encrypt(requestData, AppKey, "UTF-8");
			params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
			params.put("DataType", "2");
			
			String result=sendPost(ReqURL, params);	
			
			//根据公司业务处理返回的信息......
			
			return result;
		}
		/**
	     * MD5加密
	     * @param str 内容       
	     * @param charset 编码方式
		 * @throws Exception 
	     */
		@SuppressWarnings("unused")
		private String MD5(String str, String charset) throws Exception {
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(str.getBytes(charset));
		    byte[] result = md.digest();
		    StringBuffer sb = new StringBuffer(32);
		    for (int i = 0; i < result.length; i++) {
		        int val = result[i] & 0xff;
		        if (val <= 0xf) {
		            sb.append("0");
		        }
		        sb.append(Integer.toHexString(val));
		    }
		    return sb.toString().toLowerCase();
		}
		
		/**
	     * base64编码
	     * @param str 内容       
	     * @param charset 编码方式
		 * @throws UnsupportedEncodingException 
	     */
		private String base64(String str, String charset) throws UnsupportedEncodingException{
			String encoded = Base64.encode(str.getBytes(charset));
			return encoded;    
		}	
		
		@SuppressWarnings("unused")
		private String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
			String result = URLEncoder.encode(str, charset);
			return result;
		}
		
		/**
	     * 电商Sign签名生成
	     * @param content 内容   
	     * @param keyValue Appkey  
	     * @param charset 编码方式
		 * @throws UnsupportedEncodingException ,Exception
		 * @return DataSign签名
	     */
		@SuppressWarnings("unused")
		private String encrypt (String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception
		{
			if (keyValue != null)
			{
				return base64(MD5(content + keyValue, charset), charset);
			}
			return base64(MD5(content, charset), charset);
		}
		
		 /**
	     * 向指定 URL 发送POST方法的请求     
	     * @param url 发送请求的 URL    
	     * @param params 请求的参数集合     
	     * @return 远程资源的响应结果
	     */
		@SuppressWarnings("unused")
		private String sendPost(String url, Map<String, String> params) {
	        OutputStreamWriter out = null;
	        BufferedReader in = null;        
	        StringBuilder result = new StringBuilder(); 
	        try {
	            URL realUrl = new URL(url);
	            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // POST方法
	            conn.setRequestMethod("POST");
	            // 设置通用的请求属性
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	            conn.connect();
	            // 获取URLConnection对象对应的输出流
	            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	            // 发送请求参数            
	            if (params != null) {
			          StringBuilder param = new StringBuilder(); 
			          for (Map.Entry<String, String> entry : params.entrySet()) {
			        	  if(param.length()>0){
			        		  param.append("&");
			        	  }	        	  
			        	  param.append(entry.getKey());
			        	  param.append("=");
			        	  param.append(entry.getValue());		        	  
			        	//  System.out.println(entry.getKey()+":"+entry.getValue());
			          }
			          //System.out.println("param:"+param.toString());
			          out.write(param.toString());
	            }
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result.append(line);
	            }
	        } catch (Exception e) {            
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result.toString();
	    }
}
