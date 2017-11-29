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
import java.util.LinkedList;
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
		if(token!=null){
			String userId=utilsDao.getUserID(token);
			cottomsPost.setUserId(userId);
			String trueName= cottomsPostDao.gettrueName(userId);
			cottomsPost.setWriter(trueName);
			System.out.println(cottomsPost.getPostId());
			if(cottomsPost.getPostId()==null){
				System.out.println(123);
				cottomsPostDao.addPost(cottomsPost);
				//发布病例点赞默认为0
				RedisService.SORTSET.zadd("点赞计数列表病例:", 0, cottomsPost.getPostId()+"");
				//发布阅读数默认为0
				RedisService.SORTSET.zadd("阅读数", 0, cottomsPost.getPostId()+"");
			}else{
				cottomsPostDao.setPost(cottomsPost);
			}
		}else{
			dataWrapper.setMsg("请登录");
		}
		dataWrapper.setNum(cottomsPost.getPostId());
		return dataWrapper;
	}

	//显示病例
	@Override
	public DataWrapper<Object> queryPost(Integer currentPage,Integer numberPerPage,
			Integer classify,Integer order,Integer postStater,String token,int type,String keyWord) {
		
		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		CottomsPost cottomsPost = new CottomsPost();
		String userId=utilsDao.getUserID(token);
		if (type==1) {
			userId=null;
		}
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
		int totalNumber=cottomsPostDao.getTotalNumber(classify,keyWord);
		Set<String> set = RedisService.SORTSET.zrevrange("点赞计数列表病例:", 0, totalNumber);
		List<String> list =new ArrayList<String>();
		list.addAll(set);
		System.out.println(page.getCurrentNumber());
		List<CottomsPost> cottomsPosts=cottomsPostDao.queryPost(page,classify,order,postStater,list,userId,keyWord,type);
		for (CottomsPost cottomsPost2 : cottomsPosts) {
			int readNumber = (int)RedisService.SORTSET.zscore("阅读数", cottomsPost2.getPostId()+"");//获取阅读数
			int commentNumber = (int)RedisService.LISTS.llen("病例评论"+cottomsPost2.getPostId());//获取评论数
			int favourNumber = (int)RedisService.SETS.scard("点赞用户列表病例:"+cottomsPost2.getPostId());//获取点赞数
			cottomsPost2.setReadNumber(readNumber);
			cottomsPost2.setPostFavour(favourNumber);
			cottomsPost2.setCommentNumber(commentNumber);
			cottomsPost.setChargeContent(null);
			cottomsPost.setFreeContent(null);
			cottomsPost.setUserId(userId);
			dataWrapper.setData(cottomsPosts);
			dataWrapper.setPage(page, totalNumber);
		}
		return dataWrapper;
	}
	//病例详情
	@Override
	public DataWrapper<CottomsPost> cottomsDetail(String postId,String token) {
		RedisService.SORTSET.zincrby("阅读数", 1, postId+"");
		String userId=null;
		if(token!=null){
			userId=utilsDao.getUserID(token);
		}
		DataWrapper<CottomsPost> dataWrapper=new DataWrapper<CottomsPost>();

		List<String> postIdFees=cottomsPostDao.queryFees(userId);//获取本用户付费病例id
		CottomsPost cottomsPost1=cottomsPostDao.cottomsDetail(postId);
		boolean userIde=false;
		String post=postId+"";
		for(int i=0;i<postIdFees.size();i++){
			if(postIdFees.get(i).equals(post)){
				userIde=true;
			}else if(cottomsPost1.getUserId()==userId){
				userIde=true;
			}
		}
		int readNumber = (int)RedisService.SORTSET.zscore("阅读数", postId+"");
		int commentNumber = (int)RedisService.LISTS.llen("病例评论"+postId);//评论数
		int favourNumber = (int)RedisService.SETS.scard("点赞用户列表病例:"+postId);//点赞数
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
	
//导出表格
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
			if(postIdNumber.get(i).equals(postId)){
				cottomsPostDao.deletePost(postId);
				dataWrapper.setMsg("删除成功");
				break;
			}
		}
		return dataWrapper;
	}

	//付费病例
	public DataWrapper<Void> playChargePost(String token, Integer chargeNumber, Integer postId){
		PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
		String userId=utilsDao.getUserID(token);
		String remark = "付费病例:支付"+chargeNumber+"个乾币。(乾币余额:userQbNum个)";
		DataWrapper<Void> dw =new DataWrapper<>();
		List<Integer> qb=cottomsPostDao.queryqb(userId);//查询余额
		Integer q=null;
		for(int i=0;i<qb.size();i++){
			q=qb.get(i);
			q+=q;
		}
		if(q>=chargeNumber){
			if(userId!=null){
				Integer p=cottomsPostDao.existBuyPostId(postId,userId);//判断是否已购买
				if(p==0){
					if(payAfterOrderUtil.newQbDed(userId, chargeNumber, "", remark)!=null){
						cottomsPostDao.insertUserToPost(postId,userId);
					}
					dw.setMsg("支付成功");
				}else{
					dw.setMsg("该病例已购买");
				}
			}else{
				dw.setMsg("请登录");
			}
		}else{
			dw.setMsg("余额不足");
		}
		return dw;
	}

	//收藏病例
	@Override
	public DataWrapper<Void> collect(String token,Integer postId) {
		DataWrapper<Void> dw =new DataWrapper<>();
		String userId=utilsDao.getUserID(token);
		if(userId!=null){
			Integer p=cottomsPostDao.existPostId(postId,userId);//判断收藏是否存在
			if(p==0){
				cottomsPostDao.collect(postId,userId);
				dw.setMsg("收藏成功");
			}else{
				dw.setMsg("收藏已存在");
			}
		}else{
			dw.setMsg("请登录");
		}
		return dw;
	}
	
	//我的已购病例
	public DataWrapper<List<CottomsPost>> myBuy(String token,Integer currentPage,Integer numberPerPage){
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		String userId=utilsDao.getUserID(token);
		//获取已购买postId
		List<Integer> list=cottomsPostDao.queryMyBuyPostId(userId);
		System.out.println(list);
		List<CottomsPost> cottomsPosts = cottomsPostDao.myBuy(list,page);
		System.err.println(cottomsPosts);
		DataWrapper<List<CottomsPost>> dw=new DataWrapper<>();
		for (CottomsPost cottomsPost : cottomsPosts) {
			cottomsPost.setChargeContent(null);
			int readNumber = (int)RedisService.SORTSET.zscore("阅读数",cottomsPost.getPostId()+"");//阅读数
			int commentNumber = (int)RedisService.LISTS.llen("病例评论"+cottomsPost.getPostId());//评论数
			int favourNumber = (int)RedisService.SETS.scard("点赞用户列表病例:"+cottomsPost.getPostId());//点赞数
			cottomsPost.setReadNumber(readNumber);
			cottomsPost.setPostFavour(favourNumber);
			cottomsPost.setCommentNumber(commentNumber);
		}
		dw.setData(cottomsPosts);
		return dw;
		
	}
	//我的收藏
	public DataWrapper<List<CottomsPost>> myCollect(Integer currentPage,Integer numberPerPage,String token) {
		String userId=utilsDao.getUserID(token);
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setNumberPerPage(numberPerPage);
		List<Integer> list=cottomsPostDao.queryMyCollect(userId);
		List<CottomsPost> cottomsPosts = cottomsPostDao.myCollect(list,page);
		DataWrapper<List<CottomsPost>> dw=new DataWrapper<>();
		for (CottomsPost cottomsPost : cottomsPosts) {
			cottomsPost.setChargeContent(null);
			int readNumber = (int)RedisService.SORTSET.zscore("阅读数",cottomsPost.getPostId()+"");//阅读数
			int commentNumber = (int)RedisService.LISTS.llen("病例评论"+cottomsPost.getPostId());//评论数
			int favourNumber = (int)RedisService.SETS.scard("点赞用户列表病例:"+cottomsPost.getPostId());//点赞数
			cottomsPost.setReadNumber(readNumber);
			cottomsPost.setPostFavour(favourNumber);
			cottomsPost.setCommentNumber(commentNumber);
		}
		dw.setData(cottomsPosts);
		return dw;
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
