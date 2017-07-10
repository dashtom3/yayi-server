package com.yayiabc.http.mvc.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.CommentManageDao;
import com.yayiabc.http.mvc.dao.UserCenterStarDao;
import com.yayiabc.http.mvc.pojo.jpa.Ordera;
import com.yayiabc.http.mvc.service.CommentManageService;
@Service
public class CommentManageServiceImpl implements CommentManageService {
	@Autowired
	private CommentManageDao commentManageDao;
   @Autowired
   private UserCenterStarDao userCenterStarDao;
	@Override
	public DataWrapper<List<Ordera>> commentM(
			String orderId,String recoveryState,String phone,
			Integer currentPage,Integer numberPerpage
			) {
		Page page=new Page();
		if(currentPage!=null&numberPerpage!=null){
		page.setNumberPerPage(numberPerpage);
		page.setCurrentPage(currentPage);
		}else{
			page.setNumberPerPage(10);
			page.setCurrentPage(1);
		}
		//总条数
		int count=userCenterStarDao.queryCount("comments");
		System.out.println(page);
		List<Ordera> commentMList=commentManageDao.commentM(orderId,recoveryState,phone,page.getCurrentPage(),page.getNumberPerPage());
		System.out.println(" "+orderId);
		System.out.println("commentMList:"+ commentMList);
	/*	if(!commentMList.isEmpty()){
			List<OrderItem> orderItemList=commentMList.get(0).getOrderitemList();

			for(int i=0;i<orderItemList.size();i++){
				Map<String,String> m=new IdentityHashMap<String,String>();
				sb.append(orderItemList.get(i).getItemPropertyNamea());
				sb.append(orderItemList.get(i).getItemPropertyNameb());
				sb.append(orderItemList.get(i).getItemPropertyNamec());
				m.put(new String("sku"),SkuUtil.getSkuCode(sb.toString())+
						commentMList.get(0).getOrderitemList().get(i).getCommentList().get(0).getItemInfo().getItemName()
						);
				m.put(new String("nameAddAttribute"), sb.toString()+commentMList.get(0).getOrderitemList().get(i).getCommentList().get(0).getItemInfo().getItemName());
				m.put(new String("commentContent"),commentMList.get(0).getOrderitemList().get(i).getCommentList().get(0).getCommentContent() );
				m.put(new String("score"),String.valueOf(commentMList.get(0).getOrderitemList().get(i).getCommentList().get(0).getCommentGrade()));
				m.put(new String("OrderId"), commentMList.get(0).getOrderId());
				m.put(new String("userId"), commentMList.get(0).getOrderitemList().get(i).getCommentList().get(0).getUserId());
				m.put(new String("recoveryState"), commentMList.get(0).getOrderitemList().get(i).getCommentList().get(0).getRecoveryState());
				m.put(new String("recoveryContent"), commentMList.get(0).getOrderitemList().get(i).getCommentList().get(0).getReplyContent());

				containerList.add(m);
				sb.delete(0,sb.length());
			}

		}*/
		/*for(String key:containerList.get(1).keySet()){
			System.out.print(key+": "+containerList.get(1).get(key)+"  ,  ");
		}*/
		//DataWrapper<List<Map<String,String>>>  d=	new DataWrapper<List<Map<String,String>>>();
		DataWrapper<List<Ordera>> dataWrapper=new DataWrapper<List<Ordera>>();
		dataWrapper.setData(commentMList);
		dataWrapper.setPage(page,count);
		return dataWrapper;
	}
	//回复评论
	@Override
	public DataWrapper<Void> reply(String orderId, String itemId, String data) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
		int state=commentManageDao.reply(orderId,itemId,data);
		if(state>0){
			dataWrapper.setMsg("回复成功");
		}else{
			dataWrapper.setMsg("回复失败");
		}
		return dataWrapper;
	}
}
