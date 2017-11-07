package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.exceptionHandler.DBException;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.MomentManageDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.*;
import com.yayiabc.http.mvc.service.MomentManageService;
import com.yayiabc.http.mvc.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MomentManageServiceImpl implements MomentManageService{
    @Autowired
    private MomentManageDao momentManageDao;
    @Autowired
    private UtilsDao utilsDao;

    @Autowired
    private RedisService redisService;

    @Override
    public DataWrapper<Void> add(Moment moment,String token) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        User user=utilsDao.getUserByToken(token);
        moment.setUserId(user.getUserId());
        moment.setUserName(user.getTrueName());
        try {
            if (moment.getMomentType() < 3) {
                momentManageDao.addLower(moment);
            } else {
                momentManageDao.addHigh(moment);
            }
        }catch (Exception e){
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> delete(Integer momentId) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        momentManageDao.deleteMoment(momentId);
        momentManageDao.deleteMomentComment(momentId);
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<Moment>> queryList(Integer currentPage, Integer numberPerPage) {
        DataWrapper<List<Moment>> dataWrapper =new DataWrapper<List<Moment>>();
        Page page=new Page();
        page.setNumberPerPage(numberPerPage);
        page.setCurrentPage(currentPage);
        System.out.println(page);
        int totalNumber=momentManageDao.getMomentTotalNumber();
        dataWrapper.setPage(page, totalNumber);
        List<Moment> momentList=momentManageDao.queryList(page);
//        System.out.println(momentList.get(0).getMomentCommentList());
        dataWrapper.setData(momentList);
        return dataWrapper;
    }


    @Override
    public DataWrapper<Void> upvote(Integer momentId,String token) {
        DataWrapper<Void> dataWrapper =new DataWrapper<Void>();

        return null;
    }


}
