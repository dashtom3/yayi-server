package com.yayiabc.http.mvc.service.Impl;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.dao.AppBannerDao;
import com.yayiabc.http.mvc.pojo.model.AppBanner;
import com.yayiabc.http.mvc.service.AppBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 小月亮 on 2017/9/5.
 */
@Service
public class AppBannerServiceImpl implements AppBannerService {

    @Autowired
    private AppBannerDao appBannerDao;
    @Override
    public DataWrapper<Void> add(AppBanner appBanner) {
        DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
        //通过设置上传到七牛的附加参数进行图片大小的设置
        String appBannerAddress=appBanner.getAppBannerAddress();
        //限定缩略图的长边最多为<LongEdge>，短边最多为<ShortEdge>，进行等比缩放，不裁剪。
        // 如果只指定 w 参数则表示限定长边（短边自适应），只指定 h 参数则表示限定短边（长边自适应）。
        appBanner.setAppBannerAddress(appBannerAddress+"/0/w/<LongEdge>/h/<ShortEdge>");
        Integer effectRows=appBannerDao.add(appBanner);
        if(effectRows==1){
            dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        }else{
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> delete(Integer appBannerId) {
        DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
        Integer effectRows=appBannerDao.delete(appBannerId);
        if(effectRows==1){
            dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        }else{
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Void> update(AppBanner appBanner) {
        DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
        String appBannerAddress=appBanner.getAppBannerAddress();
        //限定缩略图的长边最多为<LongEdge>，短边最多为<ShortEdge>，进行等比缩放，不裁剪。
        // 如果只指定 w 参数则表示限定长边（短边自适应），只指定 h 参数则表示限定短边（长边自适应）。
        appBanner.setAppBannerAddress(appBannerAddress+"/0/w/<LongEdge>/h/<ShortEdge>");
        Integer effectRows=appBannerDao.update(appBanner);
        if(effectRows==1){
            dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        }else{
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<AppBanner>> query() {
        DataWrapper<List<AppBanner>> dataWrapper =new DataWrapper<List<AppBanner>>();
        List<AppBanner> appBannerList=appBannerDao.query();
        if(appBannerList==null){
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
        }else{
            dataWrapper.setData(appBannerList);
            dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        }
        return dataWrapper;
    }
}
