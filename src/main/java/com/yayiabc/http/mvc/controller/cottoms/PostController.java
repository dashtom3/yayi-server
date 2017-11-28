package com.yayiabc.http.mvc.controller.cottoms;

import java.util.List;

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
 * 病例
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
			@ModelAttribute CottomsPost cottomsPost){
		return cottomsPostService.addPost(cottomsPost,token);
	}

	//病例列表
	@RequestMapping("queryPost")
	@ResponseBody
	public DataWrapper<Object> queryPost(
			@RequestHeader(value="token",required=false)String token,
			@RequestParam(value="currentPage",required=false,defaultValue="1") Integer currentPage,
			@RequestParam(value="numberPerPage",required=false,defaultValue="10") Integer numberPerPage,
			@RequestParam(value="classify",required=false) Integer classify,//分类1:外科2内科3修复4种植5正畸
			@RequestParam(value="order",required=false,defaultValue="0") Integer order,//0:按时间排序1:按阅读数排序3:暗点赞数排序
			@RequestParam(value="postStater",required=true,defaultValue="1") Integer postStater,//病例状态1：发布2：草稿3：删除
			@RequestParam(value="type",required=false,defaultValue="1") int type,//1:病例列表。2:我的病例列表
			@RequestParam(value="keyWord",required=false) String keyWord
			){
		return cottomsPostService.queryPost(currentPage,numberPerPage,classify,order,postStater,token,type,keyWord);
	}

	//查看病例详情
	@RequestMapping("cottomsDetail")
	@ResponseBody
	public DataWrapper<CottomsPost> cottomsDetail(
			@RequestHeader(value="token",required=false) String token,
			@RequestParam(value="postId")String postId
			){
		return cottomsPostService.cottomsDetail(postId,token);

	}
	//删除病例
	@RequestMapping("deletePost")
	@ResponseBody
	public DataWrapper<Void> deletePost(
			@RequestHeader(value="token") String token,
			@RequestParam(value="postId") Integer postId
			){
		return cottomsPostService.deletePost(token,postId);

	}

	//病例付费
	@RequestMapping("playChargePost")
	@ResponseBody
	public DataWrapper<Void> playChargePost(
			@RequestHeader(value="token") String token,
			@RequestParam(value="chargeNumber") Integer chargeNumber,
			@RequestParam(value="postId") Integer postId
			){
		return cottomsPostService.playChargePost(token,chargeNumber,postId);
	}

	//收藏病例
	@RequestMapping("collect")
	@ResponseBody
	public DataWrapper<Void> collect(
			@RequestHeader(value="token") String token,
			@RequestParam(value="postId")Integer postId
			){
		return cottomsPostService.collect(token,postId);

	}

	//我的收藏
	@RequestMapping("myCollect")
	@ResponseBody
	public DataWrapper<List<CottomsPost>> myCollect(
			@RequestHeader("token")String token,
			@RequestParam("currentPage")Integer currentPage,
			@RequestParam("numberPerPage")Integer numberPerPage
			){
		return cottomsPostService.myCollect(currentPage,numberPerPage,token);

	}
	//我的购买病例
	@RequestMapping("myBuy")
	@ResponseBody
	public DataWrapper<List<CottomsPost>> myBuy(
			@RequestHeader("token")String token,
			@RequestParam(value="currentPage",required=true,defaultValue="1")Integer currentPage,
			@RequestParam(value="numberPerPage",required=true,defaultValue="10" )Integer numberPerPage
			){
		return cottomsPostService.myBuy(token,currentPage,numberPerPage);

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
	//	@RequestMapping("postReader")//病例阅读数
	//	@ResponseBody
	//	public void postReader(CottomsPost cottomsPost){
	//		cottomsPostService.postReader(cottomsPost);
	//	}
	//查询
	@RequestMapping("see")
	@ResponseBody
	public void see(HttpServletResponse response){
		cottomsPostService.see(response);
	}
}
