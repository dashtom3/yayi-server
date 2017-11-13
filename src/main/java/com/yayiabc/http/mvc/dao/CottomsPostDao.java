package com.yayiabc.http.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.CottomsComment;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;
import com.yayiabc.http.mvc.pojo.jpa.See;
@Repository
public interface CottomsPostDao {
	
	//获取用户名
	public String gettrueName(String userId);
	
	//发布病例
	public void addPost(CottomsPost cottomsPost);
	
	//显示病例
	public List<CottomsPost> queryPost(
			@Param("page") Page page,
			@Param("classify")String classify,
			@Param("order")Integer order,
			@Param("postStater")Integer postStater);
	
	public List<Map<String,Object>> queryPost(Page page);
	public int getTotalNumber(@Param("classify") String classify);
	public int getTotalCommentNumber();
	public CottomsPost cottomsDetail(CottomsPost cottomsPost);
	public void comment(CottomsComment cottomsComment);
	//public void reply(CottomsReply cottomsReply);
	
	public int postLike(CottomsPost cottomsPost);
	public void postLikeAdd(CottomsPost cottomsPost);
	
//	public int postReaderNumber(CottomsPost cottomsPost);
	//public int commentNumber(CottomsPost cottomsPost);
	public void postReader(CottomsPost cottomsPost);
	//public int commentsLike(CottomsComment cottomsComment);
	public void commentsLikeAdd(CottomsComment cottomsComment);
	//查询
	public List<See> see();
	public List<String> queryFees(String userId);
	public void setPost(CottomsPost cottomsPost);
	//查看用户的帖子
	public List<Integer> queryByIdPost(String userId);
	public void deletePost(Integer postId);
	public void insertUserToPost(@Param("postId")Integer postId, @Param("userId")String userId);
	
}
