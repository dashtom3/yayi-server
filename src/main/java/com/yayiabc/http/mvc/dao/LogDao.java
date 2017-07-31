package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.yayiabc.http.mvc.pojo.log.AdminstratorLog;
import com.yayiabc.http.mvc.pojo.log.SaleLog;
import com.yayiabc.http.mvc.pojo.log.UserLog;

public interface LogDao {

	List<UserLog> showUserLogList(@Param("operate")String operate,@Param("phone")String phone,@Param("currentNumber")Integer currentNumber,@Param("numberPerPage")Integer numberPerPage);

	Integer getUserLogTotalNumber(@Param("operate")String operate,@Param("phone")String phone);

	Integer getSaleLogTotalNumber(@Param("operate")String operate,@Param("phone")String phone);

	List<SaleLog> showSaleLogList(@Param("operate")String operate,@Param("phone")String phone,@Param("currentNumber")Integer currentNumber,@Param("numberPerPage")Integer numberPerPage);

	Integer getAdminstratorLogTotalNumber(@Param("operate")String operate,@Param("phone")String phone);

	List<AdminstratorLog> showAdminstratorLogList(@Param("operate")String operate,@Param("phone")String phone,@Param("currentNumber")Integer currentNumber,@Param("numberPerPage")Integer numberPerPage);

}
