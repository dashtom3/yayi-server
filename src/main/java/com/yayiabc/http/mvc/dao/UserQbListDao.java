package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;

@Repository
public interface UserQbListDao {
	List<QbRecord> list(@Param("phone")String phone,@Param("startDate")String startDate,@Param("endDate")String endDate,@Param("page")Page page);
	
	
	Integer queryQb(@Param("userPhone")String userPhone,@Param("qbType")String qbType);	//查询用户某类型乾币余额
	
	int getCount(@Param("phone")String phone,@Param("startDate")String startDate,@Param("endDate")String endDate);

	Integer queryQbBalances(@Param("userId")String userId);	//查询最近一条乾币记录信息余额
	
	int updateAdd(@Param("qbBalance")Integer qbBalance,@Param("phone")String phone,@Param("qbType")String qbType);
	int updateDed(@Param("qbBalance")Integer qbBalance,@Param("phone")String phone,@Param("qbType")String qbType);
}
