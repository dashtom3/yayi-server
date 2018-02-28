package com.yayiabc.http.mvc.service.Impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.BeanUtil;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.ExcelUtil;
import com.yayiabc.common.utils.Page;
import com.yayiabc.common.utils.PayAfterOrderUtil;
import com.yayiabc.http.mvc.dao.CollectDao;
import com.yayiabc.http.mvc.dao.CommentDao;
import com.yayiabc.http.mvc.dao.CottomsPostDao;
import com.yayiabc.http.mvc.dao.TokenValidateDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;
import com.yayiabc.http.mvc.pojo.jpa.Moment;
import com.yayiabc.http.mvc.pojo.jpa.See;
import com.yayiabc.http.mvc.pojo.jpa.SubComment;
import com.yayiabc.http.mvc.service.CottomsPostService;
import com.yayiabc.http.mvc.service.MomentManageService;
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
	private CommentDao commentDao;

	@Autowired
	TokenValidateDao tokenValidateDao;

	@Autowired
	private CollectDao collectDao;

	@Autowired
	private MomentManageService momentManageService;

	//发布或更改病例（传过来无postId为发布，有postId为更改）
	@Override
	public DataWrapper<Void> addPost(CottomsPost cottomsPost,String token,String refuseCauser,String sign) {

		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		if(cottomsPost.getPostStater()!=1){
			if(token!=null){
				//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String userId=utilsDao.getUserID(token);
				cottomsPost.setUserId(userId);
				String trueName= cottomsPostDao.gettrueName(userId);
				cottomsPost.setWriter(trueName);
				if(cottomsPost.getPostId()==null){
					cottomsPostDao.addPost(cottomsPost);
					System.out.println("发表病例返回主键:"+cottomsPost.getPostId());
				}else{
					cottomsPostDao.setPost(cottomsPost);
				}
			}else{
				dataWrapper.setMsg("请登录");
			}
			dataWrapper.setNum(cottomsPost.getPostId());
		}
		return dataWrapper;
	}

	//显示病例
	@Override
	public DataWrapper<Object> queryPost(Integer currentPage,Integer numberPerPage,
			Integer classify,Integer order,Integer postStater,String token,int type,String keyWord) {

		DataWrapper<Object> dataWrapper=new DataWrapper<Object>();
		CottomsPost cottomsPost = new CottomsPost();
		String userId=utilsDao.getUserID(token);
		cottomsPost.setClassify(classify);
		cottomsPost.setPostStater(postStater);
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);

		int totalNumber=cottomsPostDao.getTotalNumber(classify,keyWord,postStater);
		//		Set<String> set = RedisService.SORTSET.zrevrange("点赞计数列表病例:", 0, totalNumber);
		List<String> list =new ArrayList<String>();
		//		list.addAll(set);
		List<CottomsPost> cottomsPosts=null;
		cottomsPosts=cottomsPostDao.queryPost(page,classify,order,postStater,list,userId,keyWord,type);
		for (CottomsPost cottomsPost2 : cottomsPosts) {
			cottomsPost.setChargeContent(null);
			cottomsPost.setFreeContent(null);
			cottomsPost.setUserId(userId);
		}
		dataWrapper.setData(cottomsPosts);
		dataWrapper.setPage(page, totalNumber);
		return dataWrapper;
	}
	//病例详情
	@Override
	public DataWrapper<CottomsPost> cottomsDetail(String postId,String token,String type) {
		cottomsPostDao.upadteReadNum(postId);
		String userId=null;
		if(token!=null){
			userId=utilsDao.getUserID(token);
		}else{
			userId="";
		}
		DataWrapper<CottomsPost> dataWrapper=new DataWrapper<CottomsPost>();

		List<String> postIdFees=cottomsPostDao.queryFees(userId);//获取本用户付费病例id
		CottomsPost cottomsPost1=cottomsPostDao.cottomsDetail(postId,userId);
		System.err.println(cottomsPost1);
		dataWrapper.setFl((utilsDao.getUserPcImgById(cottomsPost1.getUserId())));
		boolean userIde=false;
		String post=postId+"";
		for(int i=0;i<postIdFees.size();i++){
			if(postIdFees.get(i).equals(post)){
				userIde=true;
			}
		}
		if(cottomsPost1.getUserId().equals(userId)){
			userIde=true;
		}
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
	@Override
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
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			}else{
				dataWrapper.setMsg("操作错误");
			}
		}
		return dataWrapper;
	}

	//付费病例
	@Override
	public DataWrapper<Void> playChargePost(String token, Integer postId){
		String userId=utilsDao.getUserID(token);
		CottomsPost c=cottomsPostDao.cottomsDetail(postId+"",userId);
		Integer chargeNumber=c.getChargeNumber();
		if(chargeNumber==null){
			chargeNumber=0;
		}
		PayAfterOrderUtil payAfterOrderUtil= BeanUtil.getBean("PayAfterOrderUtil");
		String remark = "付费病例:支付"+chargeNumber+"个乾币。(乾币余额:userQbNum个)";
		DataWrapper<Void> dw =new DataWrapper<>();
		if(token==null){
			dw.setMsg("令牌错误");
			return dw;
		}
		Integer qb=cottomsPostDao.queryqb(userId);//查询余额
		if(qb==null){
			qb=0;
		}
		if(qb>=chargeNumber){
			if(userId!=null){
				Integer p=cottomsPostDao.existBuyPostId(postId,userId);//判断是否已购买
				if(p==0){
					if(payAfterOrderUtil.newQbDed(userId, chargeNumber, "", remark)!=null){
						//购买病例
						cottomsPostDao.insertUserToPost(postId,userId);
						//作者获取钱币
						cottomsPostDao.addQB(chargeNumber,c.getUserId());
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

	//收藏
	@Override
	public DataWrapper<Void> collect(String token,Integer postId,String type) {
		DataWrapper<Void> dw =new DataWrapper<>();
		String userId=utilsDao.getUserID(token);
		Integer p=cottomsPostDao.existCollect(postId+"",userId,type);
		Integer category=0;
		if(p==0){
			if("病例".equals(type)){
				category=collectDao.getCategoryFromPost(postId);
			}else if("视频".equals(type)){
				category=collectDao.getCategoryFromVideo(postId);
				collectDao.addCollectNumToVideo(postId);
			}else if("问答".equals(type)){
				category=collectDao.getCategoryFromFaq(postId);
			}
			cottomsPostDao.collect(postId,userId,type,category);
			dw.setMsg("收藏成功");
		}else{
			if("视频".equals(type)){
				collectDao.delCollectNumToVideo(postId);
			}
			cottomsPostDao.disCollect(postId,userId,type);
			dw.setMsg("取消收藏");
		}
		return dw;
	}

	//我的已购病例
	@Override
	public DataWrapper<List<CottomsPost>> myBuy(String token, Integer currentPage, Integer numberPerPage){
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		String userId=utilsDao.getUserID(token);
		//获取已购买postId
		List<Integer> list=cottomsPostDao.queryMyBuyPostId(userId);
		List<CottomsPost> cottomsPosts=null;
		DataWrapper<List<CottomsPost>> dw=new DataWrapper<>();
		if(list.size()>0){
			cottomsPosts = cottomsPostDao.myBuy(list,page);
			for (CottomsPost cottomsPost : cottomsPosts) {
				cottomsPost.setChargeContent(null);
			}
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

	//审核
	@Override
	public DataWrapper<Void> updateStater(String token, Integer postId, Integer postStater,Integer userId,String refuseCauser) {
		DataWrapper<Void> dataWrapper = new DataWrapper<>();
		if(tokenValidateDao.getAdminIdBytoken(token)!=null){
			CottomsPost cottomsPost1=cottomsPostDao.cottomsDetail(postId+"",userId+"");
			System.err.println(cottomsPost1);
			System.err.println(cottomsPost1.getPostStater());
			if(cottomsPost1.getPostStater()==0){
				if(postStater==1){
					//发病例获取钱币
					int day=0;
					int week=0;
					if(cottomsPostDao.dayGain(userId+"")==null){
						day=0;
					}else{
						day=cottomsPostDao.dayGain(userId+"");
					}
					if(cottomsPostDao.weekGain(userId+"")==null){
						week=0;
					}else{
						week=cottomsPostDao.weekGain(userId+"");
					}
					if(day>=5||week>=20){

					}else{
						if(day<=5){
							cottomsPostDao.addDayNumber(userId+"");
							cottomsPostDao.addWeekNumber(userId+"");
							cottomsPostDao.addQBWith(userId+"");
						}
					}
				}else if(postStater==4){
					Integer exis=cottomsPostDao.exisRefuseCauser(postId);
					if(exis!=0){
					}else{
						cottomsPostDao.refuseCauser(postId,refuseCauser);
					}
				}
				cottomsPostDao.updateStater(postId,postStater);
				dataWrapper.setMsg("操作成功");
			}	
		}

		return dataWrapper;
	}

}
