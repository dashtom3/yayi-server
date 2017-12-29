package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.*;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.RedisService;
import com.yayiabc.http.mvc.service.ZanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ZanServiceImpl implements ZanService{

	@Autowired
	private RedisService redisService;
	@Autowired
	private CottomsPostDao cottomsPostDao;
	@Autowired
	private UtilsDao utilsDao;

	@Autowired
	private ZanDao zanDao;

	@Autowired
	private VideoManageDao videoManageDao;

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private MomentManageDao momentManageDao;

	@Autowired
	private FaqDao faqDao;



	@Override
	public DataWrapper<Void> upvote(String token, String type, Integer typeId,Integer parentId,Integer presentId) {
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		//获取用户信息
		User user = utilsDao.getUserByToken(token);
		String userId = user.getUserId();
		//判断是否已经点赞
		System.out.println(userId+":"+type+":"+typeId+":"+parentId+":"+presentId);
		int count=zanDao.getCount(userId,type,typeId,parentId,presentId);
		System.out.println("count"+count);
		if(count==0){
			if("视频".equals(type)){
				if(parentId!=null){
					if(presentId!=null){
						commentDao.addSubCommentZanNum(presentId);
					}else{//为视频一级评论点赞数+1
						commentDao.addTopCommentZanNum(parentId);
					}
				}else{//为视频的点赞数+1
					videoManageDao.addVideoZanNum(typeId);
				}
			}else if("牙医圈".equals(type)){
				if(parentId!=null){
					if(presentId!=null){
						momentManageDao.addMomentSecondZanNum(presentId);
					}else{
						momentManageDao.addMomentFirstZanNum(parentId);
					}
				}else{
					momentManageDao.addMomentZanNum(typeId);
				}
			}else if("病例".equals(type)){
				if(parentId!=null){
					if(presentId!=null){
						commentDao.addSubCommentZanNum(presentId);
					}else{
						commentDao.addTopCommentZanNum(parentId);
					}
				}else{
					cottomsPostDao.addCottomsZanNum(typeId);
				}
			}
			zanDao.addStatus(userId,type,typeId,parentId,presentId);
		}else{
			if("视频".equals(type)){
				if(parentId!=null){
					if(presentId!=null){
						commentDao.delSubCommentZanNum(presentId);
					}else{//为视频一级评论点赞数+1
						commentDao.delTopCommentZanNum(parentId);
					}
				}else{//为视频的点赞数+1
					videoManageDao.delVideoZanNum(typeId);
				}
			}else if("牙医圈".equals(type)){
				if(parentId!=null){
					if(presentId!=null){
						momentManageDao.delMomentSecondZanNum(presentId);
					}else{
						momentManageDao.delMomentFirstZanNum(parentId);
					}
				}else{
					momentManageDao.delMomentZanNum(typeId);
				}
			}else if("病例".equals(type)){
				if(parentId!=null){
					if(presentId!=null){
						commentDao.delSubCommentZanNum(presentId);
					}else{
						commentDao.delTopCommentZanNum(parentId);
					}
				}else{
					cottomsPostDao.delCottomsZanNum(typeId);
				}
			}
			zanDao.deleteStatus(userId,type,typeId,parentId,presentId);
		}
		return dataWrapper;
		
	}



}
