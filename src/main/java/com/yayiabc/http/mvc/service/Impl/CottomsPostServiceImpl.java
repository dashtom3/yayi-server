package com.yayiabc.http.mvc.service.Impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.ExcelUtil;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.CottomsPostDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.CottomsComment;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;
import com.yayiabc.http.mvc.pojo.jpa.CottomsReply;
import com.yayiabc.http.mvc.pojo.jpa.ExcelEntry;
import com.yayiabc.http.mvc.pojo.jpa.See;
import com.yayiabc.http.mvc.pojo.model.Classify;
import com.yayiabc.http.mvc.service.CottomsPostService;
@Service
public class CottomsPostServiceImpl implements CottomsPostService{
	@Autowired
	private CottomsPostDao cottomsPostDao;

	@Autowired
	private UtilsDao utilsDao;
	//发布病例
	@Override
	public void addPost(CottomsPost cottomsPost,String token) {
		String userId=utilsDao.getUserID(token);
		cottomsPost.setUserId(userId);
		String trueName= cottomsPostDao.gettrueName(userId);
		cottomsPost.setWriter(trueName);
		cottomsPostDao.addPost(cottomsPost);

	}

	//显示病例
	@Override
	public DataWrapper<List<Map<String,Object>>> queryPost(Integer currentPage,Integer numberPerPage,String classify,Integer order) {
		DataWrapper<List<Map<String,Object>>> dataWrapper=new DataWrapper<List<Map<String,Object>>>();
		CottomsPost cottomsPost = new CottomsPost();
		cottomsPost.setClassify(classify);
		int totalNumber=cottomsPostDao.getTotalNumber(classify);
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		List<Map<String,Object>> cottomsPosts=cottomsPostDao.queryPost(page,classify,order);
		cottomsPost.setChargeContent(null);
		cottomsPost.setFreeContent(null);
		dataWrapper.setData(cottomsPosts);
		dataWrapper.setPage(page, totalNumber);

		return dataWrapper;
	}
	//显示草稿
	@Override
	public DataWrapper<List<Map<String, Object>>> queryDraft(Integer currentPage, Integer numberPerPage, String classify,
			Integer order) {
		DataWrapper<List<Map<String,Object>>> dataWrapper=new DataWrapper<List<Map<String,Object>>>();
		int totalNumber=cottomsPostDao.getTotalNumber(classify);
		CottomsPost cottomsPost = new CottomsPost();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		cottomsPost.setClassify(classify);
		List<Map<String,Object>> cottomsPosts=cottomsPostDao.queryDraft(page,classify,order);
		dataWrapper.setData(cottomsPosts);
		dataWrapper.setPage(page, totalNumber);

		return dataWrapper;
	}
	@Override
	//查看评论
	public DataWrapper<List<CottomsPost>> queryComment(Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<CottomsPost>> dataWrapper=new DataWrapper<List<CottomsPost>>();
		int totalNumber=cottomsPostDao.getTotalCommentNumber();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		List<CottomsPost> cottomsPosts=cottomsPostDao.queryPost(page);
		dataWrapper.setData(cottomsPosts);
		dataWrapper.setPage(page, totalNumber);
		return dataWrapper;
	}
	//查看病例详情
	@Override
	public DataWrapper<CottomsPost> cottomsDetail(CottomsPost cottomsPost,String token) {
		String userId=null;
		if(token!=null){
			userId=utilsDao.getUserID(cottomsPost.getToken());
		}
		DataWrapper<CottomsPost> dataWrapper=new DataWrapper<CottomsPost>();
		List<String> postIdFees=cottomsPostDao.queryFees(cottomsPost);
		boolean userIde=false;
		for(int i=0;i<postIdFees.size();i++){
			if(postIdFees.get(i).equals(userId)){
				userIde=true;
			}
		}
		CottomsPost cottomsPostl=cottomsPostDao.cottomsDetail(cottomsPost);;
		if(token!=null&&userIde==true) {
			dataWrapper.setData(cottomsPostl);
			return dataWrapper;
		}else{
			cottomsPost.setChargeContent(null);
			dataWrapper.setData(cottomsPostl);
			return dataWrapper;
		}

	}
	//评论
	//	@Override
	//	public void comment(CottomsComment cottomsComment) {
	//		cottomsPostDao.comment(cottomsComment);
	//	}
	//回复
	//	@Override
	//	public void reply(CottomsReply cottomsReply) {
	//		cottomsPostDao.reply(cottomsReply);
	//
	//	}
	@Override
	public void postLike(CottomsPost cottomsPost) {
		int like=cottomsPostDao.postLike(cottomsPost)+1;
		cottomsPost.setPostFavour(like);
		cottomsPostDao.postLikeAdd(cottomsPost);
	}
	@Override
	public void postReader(CottomsPost cottomsPost){
		//		int reader=cottomsPostDao.postReaderNumber(cottomsPost)+1;
		//		cottomsPost.setReadNumber(reader);
		//		int commentNumber=cottomsPostDao.commentNumber(cottomsPost);
		//		cottomsPost.setReadNumber(commentNumber);
		//		cottomsPostDao.postReader(cottomsPost);
	}
	//	@Override
	//	public void commentsLike(CottomsComment cottomsComment) {
	//		int like=cottomsPostDao.commentsLike(cottomsComment)+1;
	//		cottomsComment.setCommentFavour(like);
	//		cottomsPostDao.commentsLikeAdd(cottomsComment);
	//	}
	public void see(HttpServletResponse response){

		List<See> listsee = cottomsPostDao.see();
		/*// 第一步，创建一个webbook，对应一个Excel文件  
		  Workbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
		  Sheet sheet = wb.createSheet("充值记录表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
		  Row row = sheet.createRow((short) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中  
		  CellStyle cs = wb.createCellStyle();
		HSSFCell cell = null;  
		//创建标题
		row.createCell(0).setCellValue("姓名");
		row.createCell(1).setCellValue("用户手机");
		row.createCell(2).setCellValue("收入");
		row.createCell(3).setCellValue("支出");
		row.createCell(5).setCellValue("时间");
		row.createCell(6).setCellValue("描述");*/
		//创建内容


		/*for(int i=0;i<list.size();i++){
			row = sheet.createRow(i + 1); 
			for(int j=0;j<6;j++){
				See values =list.get(j);
				row.createCell(j).setCellValue(values.getTrueName());
				row.createCell(j).setCellValue(values.getPhone());
				row.createCell(j).setCellValue(values.getQbRget());
				row.createCell(j).setCellValue(values.getQbRout());
				row.createCell(j).setCellValue(values.getQbTime());
				row.createCell(j).setCellValue(values.getRemark());
			}
		}
		return wb;*/
		String fileName="充值支出记录表";
		fileName =fileName+".xls";
		/*List<ExcelEntry> excelEntryList=benefitChangeDao.getExcelEntryList(benefitId);
		System.out.println(excelEntryList);*/
		/*for (ExcelEntry excelEntry : excelEntryList) {
			excelEntry.setBenefitNum(benefit.getBenefitQb());
		}*/
		List<Map<String,Object>> list=createExcel(listsee);
		String columnNames[]={"姓名","手机","充值","支出","时间","描述"};//列名
		String keys[]={"name","phone","qbRget","qbRout","qbTime","remark"};//map中的key
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
		} catch (IOException e) {
			e.printStackTrace();
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
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
			ServletOutputStream out = response.getOutputStream();
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;

			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
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
	}


	private List<Map<String, Object>> createExcel(List<See> sees) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "sheet1");
		listmap.add(map);
		See see=null;
		for (int j = 0; j < sees.size(); j++) {
			see=sees.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("name",see.getTrueName());
			mapValue.put("phone",see.getPhone());
			mapValue.put("qbRget",see.getQbRget());
			mapValue.put("qbRout", see.getQbRout());
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
			mapValue.put("qbTime", format.format(see.getQbTime()));
			mapValue.put("remark", see.getRemark());
			listmap.add(mapValue);
		}
		return listmap;
	}
}
