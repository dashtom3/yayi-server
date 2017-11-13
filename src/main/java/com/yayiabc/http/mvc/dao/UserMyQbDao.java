package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.pojo.jpa.User;

@Repository
public interface UserMyQbDao {
	int add(QbRecord qbRecord);

	List<QbRecord> query(@Param("userId") String userId,@Param("page") Page page);

	int getCount(@Param("userId") String userId);

	void updateUserQb(@Param("qbNum") String qbNum,@Param("userId") String userId
			,@Param("qbType")String qbType
			);
	
	Integer queryQbbalance(@Param("userId")String userId,@Param("qbType")String qbType);

	int updateDataToUser(@Param("list")List<Integer> listData,@Param("userId")String userId);

	
	int addMessageQbQ(@Param("dedNums")String dedNums, @Param("userId")String userId, @Param("message")String s
			,@Param("millisecond")long Mi
			);
	
	
	int addMessageQbQRget(@Param("dedNums")String dedNums, @Param("userId")String userId, @Param("message")String s
			,@Param("millisecond")long Mi
			);
	
   //获取用户钱币余额
	User getUserQbNum(String userId);
}
