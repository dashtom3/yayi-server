package com.yayiabc.http.mvc.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public Integer addPost(CottomsPost cottomsPost);
	
	//每天获取乾币限制
	public Integer dayGain(String userId);
	
	//按时间排序
	public List<CottomsPost> queryPostTime(
			@Param("page") Page page,
			@Param("classify")Integer classify,
			@Param("postStater")Integer postStater,
			@Param("userId") String userId,
			@Param("keyWord") String keyWord,
			@Param("type")int type
			);
	
	//每周获取乾币限制
	public Integer weekGain(String userId);
	
	//每天获取乾币次数加1，到达上限不能获取钱币
	public void addDayNumber(String userId);
	
	//每周获取乾币次数加1，到达上限不能获取钱币
	public void addWeekNumber(String userId);
		
	//发病例获取钱币
	public void addQBWith(String userId);
	
	//显示病例
	public List<CottomsPost> queryPost(
			@Param("page") Page page,
			@Param("classify")Integer classify,
			@Param("order")Integer order,
			@Param("postStater")Integer postStater,
			@Param("list") List<String> list,
			@Param("userId") String userId,
			@Param("keyWord") String keyWord,
			@Param("type")int type
			);

	public List<Map<String,Object>> queryPost(Page page);
	
	//获取总条数
	public int getTotalNumber(@Param("classify") Integer classify,@Param("keyWord")String keyWord,@Param("postStater")Integer postStater);
	
	public int getTotalCommentNumber();
	
	//查看病例详情
	public CottomsPost cottomsDetail(String postId);
	
	//阅读数加1
	public void upadteReadNum(String postId);
	
	public void comment(CottomsComment cottomsComment);
	
	//public void reply(CottomsReply cottomsReply);

	//根据type查分类
	public String getClassify(Integer postId);
	//分类查询排序
	public List<CottomsPost> queryClassifyPost(
			@Param("list") List<String> list,
			@Param("postStater") Integer postStater
			);

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
	
	//购买病例
	public void insertUserToPost(@Param("postId")Integer postId, @Param("userId")String userId);
	
	//作者获取钱币
	public void addQB(@Param("chargeNumber")Integer chargeNumber,@Param("userId")String userId);
	
	String getUserIdByPostId(Integer beCommentedId);

	//收藏病例
	public void collect(
			@Param("postId") Integer postId,
			@Param("userId")String userId
			);
	//我的收藏postId
	public List<Integer> queryMyCollect(String userId);
	//我的收藏列表
	public List<CottomsPost> myCollect(
			@Param("userId")String userId,
			@Param("page") Page page);
	
	//判断收藏是否存在
	public Integer existPostId(
			@Param("postId")String postId,
			@Param("userId")String userId
			);
	//判断是否购买
	public Integer existBuyPostId(@Param(
			"postId")Integer postId,
			@Param("userId")String userId);
	
	//我的购买病例
	public List<CottomsPost> myBuy(
			@Param("list")List<Integer> list,
			@Param("page") Page page
			);
	
	//获取购买病历PostId
	public List<Integer> queryMyBuyPostId(String userId);
	
	//查询账户余额
	public Integer queryqb(String userId);

    void addCottomsZanNum(Integer typeId);

	void delCottomsZanNum(Integer typeId);
}
