package com.yayiabc.http.mvc.dao;

import com.yayiabc.http.mvc.pojo.model.AppBanner;

import java.util.List;

/**
 * Created by 小月亮 on 2017/9/5.
 */
public interface AppBannerDao {
    Integer add(AppBanner appBanner);

    Integer delete(Integer appBannerId);

    Integer update(AppBanner appBanner);

    List<AppBanner> query();
}
