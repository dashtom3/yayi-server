package com.yayiabc.http.mvc.controller.cottoms;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;
import com.yayiabc.http.mvc.service.CottomsPostService;
/**
 * 严强 2017/10/23 12:13
 * 发病例
 */
@Controller
@RequestMapping("api/cottoms")
public class PostController {
	@Autowired
	private CottomsPostService cottomsPostService;
	//发布病例
	@RequestMapping("add")
	@ResponseBody
	public DataWrapper<Void> addPost(
			@RequestHeader String token,
			@ModelAttribute(value="CottomsPost") CottomsPost cottomsPost){
		cottomsPost.setToken(token);
		return cottomsPostService.addPost(cottomsPost,token);
	}
	//显示病例
	@RequestMapping("queryPost")
	@ResponseBody
	public DataWrapper<List<CottomsPost>> queryPost(
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
			@RequestParam(value="classify",required=false) String classify,//分类
			@RequestParam(value="order",required=false,defaultValue="0") Integer order
			){
		return cottomsPostService.queryPost(currentPage,numberPerPage,classify,order);
	}
	//显示草稿
	@RequestMapping("queryDraft")
	@ResponseBody
	public DataWrapper<List<Map<String,Object>>> queryDraft(
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
			@RequestParam(value="classify",required=false) String classify,//分类
			@RequestParam(value="order",required=false,defaultValue="0") Integer order
			){
		return cottomsPostService.queryDraft(currentPage,numberPerPage,classify,order);
	}
	//查看病例详情
	@RequestMapping("cottomsDetail")
	@ResponseBody
	public DataWrapper<CottomsPost> cottomsDetail(
			@RequestHeader(value="token") String token,
			@ModelAttribute(value="CottomsPost")CottomsPost cottomsPost
			){
		cottomsPostService.postReader(cottomsPost);
		return cottomsPostService.cottomsDetail(cottomsPost,token);
		
	}
//	@RequestMapping("comment")//评论
//	@ResponseBody
//	public void comment(
//			@RequestHeader String token,
//			@ModelAttribute(value="CottomsComment")CottomsComment cottomsComment
//			){
//		cottomsComment.setToken(token);
//		cottomsPostService.comment(cottomsComment);
//	}
//	@RequestMapping("reply")//回复
//	@ResponseBody
//	public void reply(
//			@RequestHeader String token,
//			@ModelAttribute(value="CottomsReply")CottomsReply cottomsReply
//			){
//		cottomsReply.setToken(token);
//		cottomsPostService.reply(cottomsReply);
//	}
//	@RequestMapping("postLike")//病例点赞
//	@ResponseBody
//	public void postList(CottomsPost cottomsPost){
//		cottomsPostService.postLike(cottomsPost);
//	}
//	
//	@RequestMapping("commentsLike")//评论点赞
//	@ResponseBody
//	public void commentsLike(CottomsComment cottomsComment){
//		cottomsPostService.commentsLike(cottomsComment);
//	}
	@RequestMapping("postReader")//病例阅读数
	@ResponseBody
	public void postReader(CottomsPost cottomsPost){
		cottomsPostService.postReader(cottomsPost);
	}
	//查询
	@RequestMapping("see")
	@ResponseBody
	public void see(HttpServletResponse response){
		cottomsPostService.see(response);
	}
}
