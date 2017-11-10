package com.yayiabc.http.mvc.service.Impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
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
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.UserDao;
import com.yayiabc.http.mvc.dao.UserMyQbDao;
import com.yayiabc.http.mvc.dao.UserQbListDao;
import com.yayiabc.http.mvc.pojo.jpa.OrderItem;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.Receiver;
import com.yayiabc.http.mvc.pojo.model.qbRecordModel;
import com.yayiabc.http.mvc.service.UserQbListService;

@Service
public class UserQbListServiceImpl implements UserQbListService {

	@Autowired
	UserQbListDao userQbListDao;
	@Autowired
	UserDao userDao;
	@Autowired
	UserMyQbDao userMyQbDao;

	@Override
	public DataWrapper<List<QbRecord>> list(String phone, String startDate,
			String endDate, Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<QbRecord>> dataWrapper = new DataWrapper<List<QbRecord>>();
		Page page = new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		int totalNumber = userQbListDao.getCount(phone, startDate, endDate);
		dataWrapper.setPage(page, totalNumber);
		List<QbRecord> list = userQbListDao.list(phone, startDate, endDate,
				page);
		dataWrapper.setData(list);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> update(Integer qbBalance, String phone,String qbType,String sign) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		String userId = userDao.getUserId(phone);
		Calendar Cld = Calendar.getInstance();
		int MI = Cld.get(Calendar.MILLISECOND);		//获取毫秒
		if (qbBalance < 0) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			dataWrapper.setMsg("请填写正确的乾币数");
		} else {
			if(sign.equals("1")){
				//减少
				if(userQbListDao.updateDed(qbBalance, phone,qbType)>0){
					if(!addQbRecord(userId,qbBalance,phone,qbType,sign,MI)){
						dataWrapper.setMsg("减少钱币记录失败");
					} 
				}else{
					dataWrapper.setMsg("减少失败");
				}
				//增加钱币记录
			}else if(sign.equals("2")){
				//增加
				if(userQbListDao.updateAdd(qbBalance, phone,qbType)>0){
					if(!addQbRecord(userId,qbBalance,phone,qbType,sign,MI)){
						dataWrapper.setMsg("增加钱币记录失败");
					} 
				}else{
					dataWrapper.setMsg("增加失败");
				}
			}
		}
		return dataWrapper;
	}
	private boolean addQbRecord(String userId,Integer qbBalance,String phone,String qbType,String sign,int  MI){
		QbRecord qbRecord = new QbRecord();
		qbRecord.setUserId(userId);
		//查询客户现在乾币余额
		Integer userQbNum=userMyQbDao.getUserQbNum(userId);
		if(sign.equals("1")){
			//增加 钱币减少记录
			qbRecord.setQbRout(zh(qbType)+"："+qbBalance+"个");

			qbRecord.setRemark("管理员修改乾币余额，扣除"+qbBalance+"个乾币。（乾币余额："+userQbNum+"个。）");
		}else if(sign.equals("2")){
			//增加 钱币增加记录
			qbRecord.setQbRget(zh(qbType)+"："+qbBalance+"个");
			qbRecord.setRemark("管理员修改乾币余额，新增"+qbBalance+"个乾币。（乾币余额："+userQbNum+"个。）");
		}
		//qbRecord.setQbBalances(qbBalance);
		qbRecord.setMillisecond(MI);
		if(userMyQbDao.add(qbRecord)>0){
			return true;
		}
		return false;
	}
	@Override
	public DataWrapper<Map<String, Integer>> queryQb(String userPhone,String qbType) {
		DataWrapper<Map<String, Integer>> dataWrapper = new DataWrapper<Map<String, Integer>>();
		String userId = userDao.getUserId(userPhone);
		if (userId == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Username_NOT_Exist);
		} else {
			Integer qbBalance = userQbListDao.queryQb(userPhone,qbType);
			Map<String, Integer> map = new HashMap<String, Integer>();
			if (qbBalance == null) {
				map.put("qbBalance", 0);
			} else {
				map.put("qbBalance", qbBalance);
			}
			dataWrapper.setData(map);
		}
		return dataWrapper;
	}
	private String zh(String zh){
		if(zh.equals("a_qb")){
			return "\"8.0折\" ";
		} else if(zh.equals("b_qb"))
		{
			return "\"9.0折\" ";
		}else if(zh.equals("c_qb")){
			return "\"9.5折\" ";
		}else if(zh.equals("qb_balance")){
			return "\"赠\" ";
		}
		return "非法钱币类型";
	}
	/**
	 * 查询与导出
	 */
	@Override
	public DataWrapper<List<qbRecordModel>> queryQbRecord(String userMessage, String qbType, String payType,
			String orderCTime, String orderETime, Integer currentPage, Integer numberPerpage,
			HttpServletResponse response
			) {
		// TODO Auto-generated method stub
		DataWrapper<List<qbRecordModel>> dataWrapper=new DataWrapper<List<qbRecordModel>>();
		//如果currentPage和numberPerpage 有值 就是查询  没值 就为 导出excel
		HashMap<String, String> hm=new HashMap<String,String>();

		hm.put("userMessage", userMessage);
		hm.put("qbType", qbType);
		hm.put("payType", payType);
		hm.put("orderCTime", orderCTime);
		hm.put("orderETime", orderETime);

		if(currentPage!=null||numberPerpage!=null){
			Page page=new Page();
			page.setNumberPerPage(numberPerpage);
			page.setCurrentPage(currentPage);
			hm.put("numberPerpage", String.valueOf(page.getNumberPerPage()));
			Integer currentNum=page.getCurrentNumber();
			hm.put("currentNum", String.valueOf(currentNum));
			//总条数
			int count=userQbListDao.queryCount(hm);//totalnumber
			//查询date
			List<qbRecordModel> qbRecordList=userQbListDao.queryQbRecord(hm);
			dataWrapper.setPage(page, count);
			dataWrapper.setData(qbRecordList);
			return dataWrapper;
		}else{
			//导出excel
			QbExcel(hm,response);
		}
		return null;
	}
	/**
	 * 
	 * @param userMessage
	 * @param qbType
	 * @param payType
	 * @param orderCTime
	 * @param orderETime
	 * 导出乾币充值记录的excel表格
	 */
	private DataWrapper<List<qbRecordModel>> QbExcel(HashMap<String, String> hm,HttpServletResponse response) {
		// TODO Auto-generated method stub
		DataWrapper<List<qbRecordModel>> dataWrapper=new DataWrapper<List<qbRecordModel>>();
		List<qbRecordModel> qbRecordList=userQbListDao.queryQbRecord(hm);

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

		style.setWrapText(true);//设置自动换行
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  



		HSSFCell cell = row.createCell((short) 0);  //创建第一列
		//高度row


		cell.setCellValue("姓名");  
		cell.setCellStyle(style); 

		cell = row.createCell((short) 1);  
		cell.setCellValue("手机号");  
		cell.setCellStyle(style);  

		cell = row.createCell((short) 2);  
		cell.setCellValue("充值类型（充值个数）");  
		cell.setCellStyle(style);  

		cell = row.createCell((short) 3);  
		cell.setCellValue("充值金额（元）");
		cell.setCellStyle(style);  

		cell = row.createCell((short) 4); 
		cell.setCellValue("支付方式");  
		cell.setCellStyle(style);  

		cell = row.createCell((short) 5);  
		cell.setCellValue("充值时间");  
		cell.setCellStyle(style); 
System.err.println( qbRecordList.size());
		//写入表格
		for (int i = 0; i < qbRecordList.size(); i++)  
		{  


			row = sheet.createRow((int) i + 1);  
		
			qbRecordModel qbRecordModel=qbRecordList.get(i);

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 第四步，创建单元格，并设置值  
			row.createCell((short) 0).setCellValue(qbRecordModel.getTrueName());  
			row.createCell((short) 1).setCellValue(qbRecordModel.getPhone());  
			row.createCell((short) 2).setCellValue(qbRecordModel.getQbDes()); 
			row.createCell((short) 3).setCellValue(qbRecordModel.getRechargeMoney());  
			row.createCell((short) 4).setCellValue(transformation(qbRecordModel.getReferer()));  
			row.createCell((short) 5).setCellValue(simpleDateFormat.format(qbRecordModel.getCreated())); 
		} 
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			wb.write(os);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(hm.get("orderCTime")+"-"+hm.get("orderETime")+"充值记录.xls", "UTF-8"));
			ServletOutputStream out = response.getOutputStream();
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;

			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		} catch (IOException e) {
			e.printStackTrace();
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return dataWrapper;
	}
	/**
	 * 转换  支付方式   1 是支付宝   2 是微信公众号/微信电脑网站    3/微信app支付
	 * @param referer
	 * @return
	 */
	private String transformation(String referer) {
		// TODO Auto-generated method stub
		int i=Integer.parseInt(referer);
		switch(i) 
		{ 
		case 1: 
			return "支付宝";
		case 2: 
			return "微信支付(公众号/网站)";
		default: 
			return "微信支付（app";
		} 
	}
}
