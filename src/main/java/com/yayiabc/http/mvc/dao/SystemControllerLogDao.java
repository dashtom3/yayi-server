package com.yayiabc.http.mvc.dao;

import org.springframework.stereotype.Repository;

import com.yayiabc.http.mvc.pojo.log.UserLog;
@Repository
public interface SystemControllerLogDao {

	void addLog(UserLog userLog);

}
