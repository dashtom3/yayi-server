package com.yayiabc.http.mvc.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;

import aj.org.objectweb.asm.Type;

public interface CottomsPostService {
	//发布病例
	public DataWrapper<Void> addPost(CottomsPost cottomsPost,String token,String refuseCauser);
	
	//显示病例 type:1:是病例列表。2:是我的病例列表
	public DataWrapper<Object> queryPost(Integer currentPage,Integer numberPerPage,Integer classify,
			Integer order,Integer postStater,String token,int type,String keyWord);
	
	//病例详情
	public DataWrapper<CottomsPost> cottomsDetail(String postId,String token,String type);
	
	//删除病例
	public DataWrapper<Void> deletePost(String token, Integer postId);
	
	//查询
	public void see(HttpServletResponse response);
	
	//病例付费
	public DataWrapper<Void> playChargePost(String token, Integer chargeNumber, Integer postId);
	
	//收藏病例
	public DataWrapper<Void> collect(String token,Integer postId,String type);
	

	
	//我的购买病例
	public DataWrapper<List<CottomsPost>> myBuy(String token,Integer currentPage,Integer numberPerPage);
	
	//
	public DataWrapper<Void> updateStater(String token,Integer postId,Integer postStater,Integer userId,String refuseCauser);
 }
