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
	public DataWrapper<List<CottomsPost>> queryPost(Integer currentPage,Integer numberPerPage,String classify,Integer order,Integer postStater);
	
	//病例详情
	public DataWrapper<CottomsPost> cottomsDetail(CottomsPost cottomsPost,String token);
	
	//删除病例
	public DataWrapper<Void> deletePost(String token, Integer postId);
	
	//查询
	public void see(HttpServletResponse response);
	
	//病例付费
	public DataWrapper<Void> playChargePost(String token, Integer chargeNumber, Integer postId);
	
}
