package com.yayiabc.http.mvc.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;

public interface CottomsPostService {
	//发布病例
	public DataWrapper<Void> addPost(CottomsPost cottomsPost,String token);
	
	//显示病例
	public DataWrapper<List<CottomsPost>> queryPost(Integer currentPage,Integer numberPerPage,String classify,Integer order);
	
	//显示草稿
	public DataWrapper<List<Map<String, Object>>> queryDraft(Integer currentPage,Integer numberPerPage,String classify,Integer order);
	
	public DataWrapper<List<Map<String, Object>>> queryComment(Integer currentPage, Integer numberPerPage);
	
	public DataWrapper<CottomsPost> cottomsDetail(CottomsPost cottomsPost,String token);
	
	//public void comment(CottomsComment cottomsComment);
	//public void reply(CottomsReply cottomsReply);
	public void postLike(CottomsPost cottomsPost);
	
	//增加阅读数
	public void postReader(CottomsPost cottomsPost);
	//public void commentsLike(CottomsComment cottomsComment);
	
	//删除病例
	public DataWrapper<Void> deletePost(String token, Integer postId);
	
	//查询
	public void see(HttpServletResponse response);

	public DataWrapper<Void> playChargePost(String token, Integer chargeNumber, Integer postId);
	
}
