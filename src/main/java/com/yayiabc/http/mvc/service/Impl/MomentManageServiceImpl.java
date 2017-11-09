package com.yayiabc.http.mvc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.MomentManageDao;
import com.yayiabc.http.mvc.dao.UtilsDao;
import com.yayiabc.http.mvc.pojo.jpa.Moment;
import com.yayiabc.http.mvc.pojo.jpa.User;
import com.yayiabc.http.mvc.service.MomentManageService;
import com.yayiabc.http.mvc.service.RedisService;

@Service
public class MomentManageServiceImpl implements MomentManageService{
    @Autowired
    private MomentManageDao momentManageDao;
    @Autowired
    private UtilsDao utilsDao;
    @Autowired
    private RedisService redisService;

  /*  @Autowired
    private RedisService redisService;*/

    @Override
    public DataWrapper<Void> add(Moment moment,String token) {
        DataWrapper<Void> dataWrapper=new DataWrapper<Void>();
        User user=utilsDao.getUserByToken(token);
        moment.setUserId(user.getUserId());
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
        //TODO delete the comments of moment
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





}
