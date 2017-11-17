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
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.ExcelUtil;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.http.mvc.dao.CottomsPostDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;
import com.yayiabc.http.mvc.pojo.jpa.See;
import com.yayiabc.http.mvc.service.CottomsPostService;
import com.yayiabc.http.mvc.service.ReadNumberServer;
import com.yayiabc.http.mvc.service.RedisService;
@Service
public class CottomsPostServiceImpl implements CottomsPostService{
	@Autowired
	private CottomsPostDao cottomsPostDao;

	@Autowired
	private UtilsDao utilsDao;

	@Autowired
	private RedisService RedisService;

	@Autowired 
	ReadNumberServer readNumberServer;

	@Autowired
	RedisService redisService;
	//发布或更改病例（传过来无postId为发布，有postId为更改）
	@Override
	public DataWrapper<Void> addPost(CottomsPost cottomsPost,String token) {
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		String userId=utilsDao.getUserID(token);
		cottomsPost.setUserId(userId);
		String trueName= cottomsPostDao.gettrueName(userId);
		cottomsPost.setWriter(trueName);
		if(cottomsPost.getPostId()==0){
			cottomsPostDao.addPost(cottomsPost);
			//发布病例点赞默认为0
			RedisService.SORTSET.zadd("点赞计数列表病例:", 0, cottomsPost.getPostId()+"");
			//发布阅读数默认为0
			RedisService.SORTSET.zadd("阅读数", 0, cottomsPost.getPostId()+"");
		}else{
			cottomsPostDao.setPost(cottomsPost);
		}
		dataWrapper.setNum(cottomsPost.getPostId());
		return dataWrapper;
	}

	//显示病例
	@Override
	public DataWrapper<List<CottomsPost>> queryPost(Integer currentPage,Integer numberPerPage,String classify,Integer order,Integer postStater) {
		DataWrapper<List<CottomsPost>> dataWrapper=new DataWrapper<List<CottomsPost>>();
		CottomsPost cottomsPost = new CottomsPost();
		System.out.println(classify);
		cottomsPost.setClassify(classify);
		cottomsPost.setPostStater(postStater);
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		String key="";
		if(order==2){
			key="点赞计数列表";
		}else if(order==1){
			key="自增序列";
		}else{
			key="";
		}
		//	if(classify==null){
		int totalNumber=(int)RedisService.SORTSET.zcard("点赞计数列表病例:");
		System.err.println("totalNumber"+totalNumber);
		Set<String> set = RedisService.SORTSET.zrevrange("点赞计数列表病例:", 0, totalNumber);
		System.err.println(set);
		List<String> list =new ArrayList<String>();
		list.addAll(set);
		System.out.println(list);
		List<CottomsPost> cottomsPosts=cottomsPostDao.queryPost(page,classify,order,postStater,list);
		for (CottomsPost cottomsPost2 : cottomsPosts) {
			int readNumber = (int)RedisService.SORTSET.zscore("阅读数", cottomsPost2.getPostId()+"");//获取阅读数
			System.out.println("阅读数"+readNumber);
			int commentNumber = (int)RedisService.LISTS.llen("病例评论"+cottomsPost2.getPostId());//获取评论数
			System.out.println("评论数"+commentNumber);
			//			List<String> postId=RedisService.LISTS.lrange("2评论"+cottomsPost2.getPostId(), start, end);
			int favourNumber = (int)RedisService.SETS.scard("点赞用户病例病例:"+cottomsPost2.getPostId());//获取点赞数
			System.out.println("点赞数"+favourNumber);
			cottomsPost2.setReadNumber(readNumber);
			cottomsPost2.setCommentNumber(favourNumber);
			cottomsPost2.setCommentNumber(commentNumber);
			cottomsPost.setChargeContent(null);
			cottomsPost.setFreeContent(null);
			dataWrapper.setData(cottomsPosts);
			dataWrapper.setPage(page, totalNumber);
		}
		//		}else {
		//			//数据库获得总条数
		//			int totalNumber=cottomsPostDao.getTotalNumber(classify);
		//			//redise获取总条数
		//			//int totalNumber=(int)(RedisService.SORTSET.zcard("口腔内科")+RedisService.SORTSET.zcard("口腔外科")+RedisService.SORTSET.zcard("口腔种植")+RedisService.SORTSET.zcard("口腔修复")+RedisService.SORTSET.zcard("口腔正畸"));
		//			int start = (int)Math.floor((currentPage-1)*numberPerPage);//起始 
		//			int end = (int)Math.floor((totalNumber-1)/numberPerPage+1);//总页数
		//			Set<String> set = RedisService.SORTSET.zrevrange(classify+"点赞", start, start+numberPerPage-1);
		//			List<String> list =new ArrayList<String>();
		//			list.addAll(set);
		//			System.out.println(list);
		//			System.out.println(postStater);
		//			List<CottomsPost> cottomsPosts = cottomsPostDao.queryClassifyPost(list,postStater);
		//			for (CottomsPost cottomsPost2 : cottomsPosts) {
		//				String readNumber = RedisService.STRINGS.get(cottomsPost.getPostId()+"");//获取阅读数
		//				int commentNumber = (int)RedisService.LISTS.llen("2评论"+cottomsPost2.getPostId());//获取评论数
		//				//List<String> postId=RedisService.LISTS.lrange("2评论"+cottomsPost2.getPostId(), start, end);
		//				int favourNumber = (int)RedisService.SETS.scard("病例:"+cottomsPost2.getPostId());//获取点赞数
		//				cottomsPost2.setReadNumber(readNumber);
		//				cottomsPost2.setCommentNumber(favourNumber);
		//				cottomsPost2.setCommentNumber(commentNumber);
		//				cottomsPost.setChargeContent(null);
		//				cottomsPost.setFreeContent(null);
		//				dataWrapper.setData(cottomsPosts);
		//				dataWrapper.setPage(page, totalNumber);
		//			}
		//			System.out.println(cottomsPosts);
		//		}
		return dataWrapper;
	}
	//病例详情
	@Override
	public DataWrapper<CottomsPost> cottomsDetail(CottomsPost cottomsPost,String token) {
		RedisService.SORTSET.zincrby("阅读数", 1, cottomsPost.getPostId()+"");
		String userId=null;
		if(token!=null){
			userId=utilsDao.getUserID(token);
		}
		DataWrapper<CottomsPost> dataWrapper=new DataWrapper<CottomsPost>();

		List<String> postIdFees=cottomsPostDao.queryFees(userId);//获取本用户付费病例id
		boolean userIde=false;
		String post=cottomsPost.getPostId()+"";
		for(int i=0;i<postIdFees.size();i++){
			if(postIdFees.get(i).equals(post)){
				userIde=true;
			}
		}
		CottomsPost cottomsPost1=cottomsPostDao.cottomsDetail(cottomsPost);
		int readNumber = (int)RedisService.SORTSET.zscore("阅读数", cottomsPost1.getPostId()+"");
		int commentNumber = (int)RedisService.LISTS.llen("病例评论"+cottomsPost.getPostId());//评论数
		System.out.println(cottomsPost1);
		int favourNumber = (int)RedisService.SETS.scard("点赞用户列表病例:"+cottomsPost1.getPostId());//点赞数
		cottomsPost1.setReadNumber(readNumber);
		cottomsPost1.setPostFavour(favourNumber);
		cottomsPost1.setCommentNumber(commentNumber);
		if(token!=null&&userIde==true) {
			dataWrapper.setData(cottomsPost1);
			return dataWrapper;
		}else{
			cottomsPost1.setChargeContent(null);
			dataWrapper.setData(cottomsPost1);
			return dataWrapper;
		}
	}

	public void see(HttpServletResponse response){

		List<See> listsee = cottomsPostDao.see();
		String fileName="充值支出记录表";
		fileName =fileName+".xls";
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
	//删除病例
	@Override
	public DataWrapper<Void> deletePost(String token, Integer postId) {
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		String userId=utilsDao.getUserID(token);
		List<Integer> postIdNumber =  cottomsPostDao.queryByIdPost(userId);
		for(int i=0;i<postIdNumber.size();i++){
			if(postIdNumber.get(i)==postId){
				cottomsPostDao.deletePost(postId);
				dataWrapper.setMsg("删除成功");
				break;
			}
		}
		return dataWrapper;
	}

	//付费病例
	public DataWrapper<Void> playChargePost(String token, Integer chargeNumber, Integer postId){
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
		String userId=utilsDao.getUserID(token);
		String remark = "付费病例:支付"+chargeNumber+"个乾币。(乾币余额:userQbNum个)";
		if(payAfterOrderUtil.newQbDed(userId, chargeNumber, "", remark)!=null){
			cottomsPostDao.insertUserToPost(postId,userId);
		}
		return dataWrapper;
	}

	//导出表格
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
