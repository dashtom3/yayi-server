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
			@ModelAttribute CottomsPost cottomsPost,
			@RequestParam(value="refuseCauser",required=false)String refuseCauser
			){
		return cottomsPostService.addPost(cottomsPost,token,refuseCauser);
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
			@RequestParam(value="postId")String postId,
			@RequestParam(value="type",required=false,defaultValue="病例")String type
			){
		return cottomsPostService.cottomsDetail(postId,token,type);

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
			@RequestParam(value="postId") Integer postId
			){
		return cottomsPostService.playChargePost(token,postId);
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

	//查询
	@RequestMapping("see")
	@ResponseBody
	public void see(HttpServletResponse response){
		cottomsPostService.see(response);
	}
	
	//更改病历状态,审核
	@RequestMapping("check")
	@ResponseBody
	public DataWrapper<Void> updateStater(
			@RequestHeader("token")String token,
			@RequestParam("postId")Integer postId,
			@RequestParam("postStater")Integer postStater,
			@RequestParam("userId")Integer userId,
			@RequestParam("refuseCauser")String refuseCauser
			){
		return cottomsPostService.updateStater(token,postId,postStater,userId,refuseCauser);
	}
	
//	//我的收藏
//	@RequestMapping("queryList")
//	@ResponseBody
//	public DataWrapper<Void> queryList(
//			@RequestHeader("token")String token,
//			@RequestParam("postStater")Integer postStater,
//			@RequestParam("classfy")String refuseCauser
//			){
//		return cottomsPostService.updateStater(token,postStater,refuseCauser);
//	}
}
