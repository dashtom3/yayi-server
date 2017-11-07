package com.yayiabc.http.mvc.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CottomsComment;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;
import com.yayiabc.http.mvc.pojo.jpa.CottomsReply;

public interface CottomsPostService {
	//发布病例
	public void addPost(CottomsPost cottomsPost);
	//显示评论
	public DataWrapper<List<Map<String, Object>>> queryPost(Integer currentPage,Integer numberPerPage,String classify,Integer order);
	//显示草稿
	public DataWrapper<List<Map<String, Object>>> queryDraft(Integer currentPage,Integer numberPerPage,String classify,Integer order);
	public DataWrapper<List<CottomsPost>> queryComment(Integer currentPage, Integer numberPerPage);
	public DataWrapper<CottomsPost> cottomsDetail(CottomsPost cottomsPost);
	public void comment(CottomsComment cottomsComment);
	public void reply(CottomsReply cottomsReply);
	public void postLike(CottomsPost cottomsPost);
	public void postReader(CottomsPost cottomsPost);
	public void commentsLike(CottomsComment cottomsComment);
	//查询
	public void see(HttpServletResponse response);
}
